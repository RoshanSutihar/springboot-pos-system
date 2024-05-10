package com.inventoryserver.core.interfaces;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventoryserver.core.User;
import com.inventoryserver.core.UserDAO;



@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
	private final UserDAO userDao;
	
	
    public UserController(UserDAO userDao) {
        this.userDao = userDao;
    }
	
	// create new user
	@PostMapping("/create")
	public ResponseEntity<String> createUser(@RequestBody User newuser) {
		
		if (newuser.getUserName().isBlank() || newuser.getUserPassword().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty user name or password");
        }

        String key = userDao.save(newuser);
        if (key.equals("Duplicate")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this name already exists");
        } else if (key.equals("Error")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can not generate key");
        }
        return ResponseEntity.ok().body(key);
       // return ResponseEntity.ok("Created successfully");
    }
	
	@PostMapping("/login")
    public ResponseEntity<String> checkLogin(@RequestBody User user) {
        User result = userDao.findByNameAndPassword(user.getUserName(), user.getUserPassword());
        if (result == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("failure");
        }
        return ResponseEntity.ok().body("success");
    }
	
	
	@PostMapping("/{userid}/attend/{resid}")
	public ResponseEntity<String> attendRestaurant(@PathVariable("userid") String userId,
	                                               @PathVariable("resid") String restaurantId) {

	    // Pass userId and restaurantId to a function in UserDAO
	    String key = userDao.attendRestaurant(userId, restaurantId);
	    if (key.equals("Duplicate")) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this ID already attended this restaurant");
	    } else if (key.equals("Error")) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can not attend the restaurant");
	    } else if (key.equals("UserError")) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("cannot find user in the DB");
	    } else if (key.equals("ResError")) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("cannot find Restaurant in the DB");
	    } else if (key.equals("InsertError")) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("cannot insert in the DB");
	    } 
	    return ResponseEntity.ok().body(key);
	}
	
	
	

}
