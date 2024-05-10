package com.inventoryserver.core.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventoryserver.core.Customer;
import com.inventoryserver.core.CustomerDAO;
import com.inventoryserver.core.Supplier;
import com.inventoryserver.core.SupplierDAO;

@RestController
@RequestMapping("/suppliers")
@CrossOrigin(origins = "*")
public class SupplierController {
	
	public SupplierDAO supDao;
	
	
	public SupplierController(SupplierDAO supDao) {
		
		this.supDao = supDao;
	}
	
	@PostMapping("/newsupplier")
	public ResponseEntity<String> createUser(@RequestBody  Supplier newsupplier) {
		
		if(newsupplier.getSupplierName().isBlank()|| newsupplier.getSupplierContact().isBlank()||newsupplier.getSupplierAddress().isBlank() ||
				newsupplier.getSupplierEmail().isBlank() ) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some fields missing please check");
			}
			
			String key = supDao.save(newsupplier);
	        if (key.equals("Duplicate")) {
	        	
	            return ResponseEntity.status(HttpStatus.CONFLICT).body("This supplier already exists");
	        } else if (key.equals("Error")) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can not generate key");
	        }
	        return ResponseEntity.ok().body(key);
		
		
	}
	
	
	
	@GetMapping("/count")
    public int getTotalSupplierCount() {
        return supDao.getTotalSuppliersCount();
    }


}
