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
	
	@GetMapping("/getProduct/{proid}")
    public List<Product> getProduct(@PathVariable int proid) {
        return proDao.getProduct(proid);
    }
	
	@GetMapping("/getproducts")
    public List<Product> getAllProducts() {
        return proDao.getAllProducts();
    }
	
	@GetMapping("/getlowproducts")
    public List<Product> getLowProducts() {
        return proDao.getLowProducts();
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
	
	@PostMapping("/{proid}/restock/{qty}")
    public ResponseEntity<String> restockItem(@PathVariable("proid") int productId, @PathVariable("qty") int quantity) {
        if (productId == 0 || quantity <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid productId or quantity");
        }
        
        String key = proDao.restock(productId, quantity);
        if (key.equals("qtyerror")) {
        	
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This product cant be restocked");
            
        } else if (key.equals("Error")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something wrong");
            
        }

        return ResponseEntity.ok().body("Item restocked successfully");
    }
	
	
	@PostMapping("/updateproduct")
	public ResponseEntity<String> updateProduct(@RequestBody  Product newproduct) {
		
		if(newproduct.getProductDesc().isBlank()|| newproduct.getProductPrice()==0 || newproduct.getProductId()==0
				|| newproduct.getSupplierId() .isBlank()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some fields missing please check");
			}
			
			String key = proDao.update(newproduct);
	        if (key.equals("Product update failed.")) {
	        	
	            return ResponseEntity.status(HttpStatus.CONFLICT).body("Failure");
	        } else if (key.equals("Error")) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can not generate key");
	        }
	        return ResponseEntity.ok().body("success");
		
		
	}
}
