package com.inventoryserver.core.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventoryserver.core.Return;
import com.inventoryserver.core.ReturnDAO;
import com.inventoryserver.core.Supplier;

@RestController
@RequestMapping("/returns")
@CrossOrigin(origins = "*")
public class ReturnController {
	
	public ReturnDAO retDao;
	
	
	public ReturnController(ReturnDAO retDao) {
		this.retDao = retDao;
	}
	
	
	@PostMapping("/newreturn")
	public ResponseEntity<String> createUser(@RequestBody  Return newreturn) {
		
		if(newreturn.getProductId().isBlank()|| newreturn.getReturnReason().isBlank()  ||
				newreturn.getQtyReturned()  ==0 || newreturn.getOrderId() ==0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some fields missing please check");
			}
			
			String key = retDao.save(newreturn);
	        if (key.equals("Duplicate")) {
	        	
	            return ResponseEntity.status(HttpStatus.CONFLICT).body("This supplier already exists");
	        } else if (key.equals("Error")) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can not generate key");
	        }
	        return ResponseEntity.ok().body(key);
		
	}

}
