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

import com.inventoryserver.core.Customer;
import com.inventoryserver.core.CustomerDAO;
import com.inventoryserver.core.dtos.CustomerDTO;

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
	
	 @GetMapping("/count")
	    public int getTotalCustomerCount() {
	        return cusDao.getTotalCustomerCount();
	    }
	 
	 
	 @GetMapping("/getnames")
	    public List<CustomerDTO> getAllCustomers() {
	        return cusDao.getAllCustomers();
	    }
	 
	 
	 @GetMapping("/getalldetails")
	    public List<Customer> getAllCustomersDetails() {
	        return cusDao.getAllCustomersdetails();
	    }
	 
	 @PostMapping("/delete/{cusid}")
	 public ResponseEntity<String> deleteCustomer(@PathVariable String cusid) {
	    
	     if (cusid == null || cusid.isEmpty()) {
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cusid is missing. Please provide a valid customer ID.");
	     }
	     
	    
	     boolean deleted = cusDao.deleteCustomer(cusid);
	     
	    
	     if (deleted) {
	         return ResponseEntity.ok().body("Customer with ID " + cusid + " deleted successfully.");
	     } else {
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with ID " + cusid + " not found.");
	     }
	 }


}
