package com.inventoryserver.core.interfaces;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventoryserver.core.Category;
import com.inventoryserver.core.CategoryDAO;
import com.inventoryserver.core.Product;
import com.inventoryserver.core.ProductDAO;
import com.inventoryserver.core.Supplier;
import com.inventoryserver.core.dtos.CategoryDTO;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

	public ProductDAO proDao;
	public CategoryDAO catDao;
	
	
	public ProductController(ProductDAO proDao,CategoryDAO catDao) {
		
		this.proDao = proDao;
		this.catDao = catDao;
		
	}
	
	
	@PostMapping("/newproduct")
	public ResponseEntity<String> createUser(@RequestBody  Product newproduct) {
		
		if(newproduct.getProductName().isBlank()||newproduct.getProductDesc().isBlank()|| newproduct.getCategoryId().isBlank()
				|| newproduct.getProductQty() == 0 || newproduct.getSupplierId() .isBlank()) {
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
	
	@GetMapping("/getproducts")
    public List<Product> getAllProducts() {
        return proDao.getAllProducts();
    }
	
	@PostMapping("/addcategory")
	public ResponseEntity<String> createCategory(@RequestBody  Category newcategory) {
		
		if(newcategory.getCategoryName().isBlank()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some fields missing please check");
			}
			
			String key = catDao.save(newcategory);
	        if (key.equals("Duplicate")) {
	        	
	            return ResponseEntity.status(HttpStatus.CONFLICT).body("This product already exists");
	        } else if (key.equals("Error")) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can not generate key");
	        }
	        return ResponseEntity.ok().body("success");
		
		
	}
	
	@GetMapping("/getcategories")
    public List<CategoryDTO> getAllCategories() {
        return catDao.getAllCategories();
    }
	
	
	@GetMapping("/count")
    public int getTotalSupplier() {
        return proDao.getTotalproductCount();
    }
	
}
