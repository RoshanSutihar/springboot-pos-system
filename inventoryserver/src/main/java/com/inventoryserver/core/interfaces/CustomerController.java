package com.inventoryserver.core.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventoryserver.core.Customer;
import com.inventoryserver.core.CustomerDAO;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "*")
public class CustomerController {
	
	public CustomerDAO cusDao;
	
	public CustomerController(CustomerDAO cusDao) {
		this.cusDao = cusDao;
		
	}
	
	@PostMapping("/newcustomer")
	public ResponseEntity<String> createUser(@RequestBody Customer newcustomer) {
		
		if(newcustomer.getCustomerName().isBlank()|| newcustomer.getCustomerPhone().isBlank()||newcustomer.getCustomerAdd().isBlank() ||
				newcustomer.getCustomerEmail().isBlank() ) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some fields missing please check");
			}
			
			String key = cusDao.save(newcustomer);
	        if (key.equals("Duplicate")) {
	        	
	            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this phone numner already exists");
	        } else if (key.equals("Error")) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can not generate key");
	        }
	        return ResponseEntity.ok().body(key);
		
		
	}

}
