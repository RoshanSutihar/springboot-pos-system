package com.inventoryserver.core;

import org.springframework.beans.factory.annotation.Autowired;
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


		    String insertSQL = "insert into products(product_name, product_desc, product_qty, product_cat, product_supplier, product_price) values (?,?,?, ?,?,?)";
		    Long lastInsertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
		    
		    jdbcTemplate.update(insertSQL,newproduct.getProductName(), newproduct.getProductDesc(), newproduct.getProductQty(), newproduct.getCategoryId(), newproduct.getSupplierId(), newproduct.getProductPrice() );
		    return "Success";
		}
	
	
}
