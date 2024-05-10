package com.inventoryserver.core.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventoryserver.core.Product;
import com.inventoryserver.core.ProductDAO;
import com.inventoryserver.core.Supplier;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

	public ProductDAO proDao;
	
	
	public ProductController(ProductDAO proDao) {
		
		this.proDao = proDao;
		
	}
	
	
	@PostMapping("/newproduct")
	public ResponseEntity<String> createUser(@RequestBody  Product newproduct) {
		
		if(newproduct.getProductName().isBlank()||newproduct.getProductDesc().isBlank()|| newproduct.getCategoryId()==0
				|| newproduct.getProductQty() == 0 || newproduct.getSupplierId() ==0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some fields missing please check");
			}
			
			String key = proDao.save(newproduct);
	        if (key.equals("Duplicate")) {
	        	
	            return ResponseEntity.status(HttpStatus.CONFLICT).body("This product already exists");
	        } else if (key.equals("Error")) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can not generate key");
	        }
	        return ResponseEntity.ok().body("success");
		
		
	}
	
	
	
}
