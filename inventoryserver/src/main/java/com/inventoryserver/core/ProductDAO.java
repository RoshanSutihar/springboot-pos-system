package com.inventoryserver.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;



@Repository
public class ProductDAO {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	 public String save(Product newproduct) {
			
		    String sql = "SELECT * FROM suppliers WHERE product_name=?";
		    RowMapper<Product> rowMapper = new ProductRowMapper();
		    Product old = null;
		    try {
		        old = jdbcTemplate.queryForObject(sql, rowMapper, newproduct.getProductName());
		    } catch(Exception ex) {
		
		    }
		    if(old != null) 
		        return "Duplicate";


		    String insertSQL = "insert into products(product_name, product_desc, product_qty, product_cat, product_supp, product_price) values (?,?,?, ?,?,?)";
		    Long lastInsertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
		    
		    jdbcTemplate.update(insertSQL,newproduct.getProductName(), newproduct.getProductDesc(), newproduct.getProductQty(), newproduct.getCategoryId(), newproduct.getSupplierId(), newproduct.getProductPrice() );
		    return "Success";
		}
	 
	 
	 
	 
	 public String update(Product newProduct) {
	        String sql = "UPDATE products SET product_price = ?, product_desc = ?  WHERE product_id = ?";
	        int rowsAffected = jdbcTemplate.update(
	            sql, 
	            newProduct.getProductPrice(), 
	            newProduct.getProductDesc(), 
	            
	            newProduct.getProductId()
	        );

	        if (rowsAffected > 0) {
	            return "Product updated successfully.";
	        } else {
	            return "Product update failed.";
	        }
	    }
	 
	 
	 public List<Product> getAllProducts() {
			
		 String sql = "SELECT * FROM products";
	        RowMapper<Product> rowMapper = new ProductRowMapper();
	        return jdbcTemplate.query(sql, rowMapper);
	 }
	 
	 
	 public List<Product> getProduct(int productId) {
			
		 String sql = "SELECT * FROM products WHERE product_id = ?";
	        RowMapper<Product> rowMapper = new ProductRowMapper();
	        return jdbcTemplate.query(sql, rowMapper,  productId);
	 }
	 
	 public List<Product> getLowProducts() {
			
		 String sql = "SELECT * FROM products where product_qty < 20";
	        RowMapper<Product> rowMapper = new ProductRowMapper();
	        return jdbcTemplate.query(sql, rowMapper);
	 }
	 
	 
	 
	 public boolean deleteCustomer(String customerId) {
		    String sql = "DELETE FROM products WHERE product_id = ?";
		    int rowsAffected = jdbcTemplate.update(sql, customerId);
		    return rowsAffected > 0;
		}
	 
	 
	 
	 
	 
	 public int getTotalproductCount()
	 {
		 String sql = "SELECT SUM(product_qty) AS total_quantity FROM products";
	        Integer totalQuantity = jdbcTemplate.queryForObject(sql, Integer.class);
	        return totalQuantity != null ? totalQuantity : 0;
	 }
	 
	 public String restock(int proID, int qty) {
		    try {
		        
		        String checkSql = "SELECT product_qty FROM products WHERE product_id = ?";
		        int currentQty = jdbcTemplate.queryForObject(checkSql, Integer.class, proID);
		        if (currentQty >= 20) {
		            return "qtyerror"; 
		        }

		        
		        String restockSql = "UPDATE products SET product_qty = product_qty + ? WHERE product_id = ?";
		        int rowsAffected = jdbcTemplate.update(restockSql, qty, proID);
		        
		        if (rowsAffected > 0) {
		            return "success";
		        } else {
		            return "error"; 
		        }
		    } catch (DataAccessException e) {
		        e.printStackTrace();
		        return "error"; 
		    }
		}


	
}
