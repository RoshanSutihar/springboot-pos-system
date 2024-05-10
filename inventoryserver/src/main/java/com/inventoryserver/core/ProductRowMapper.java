package com.inventoryserver.core;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ProductRowMapper implements RowMapper<Product>{
	
	
	  @Override
	    public Product mapRow(ResultSet row, int rowNum) throws SQLException {
		  Product u = new Product();
		  
		  u.setProductName(row.getString("product_name"));
		  u.setProductId(row.getInt("product_id"));
		  u.setProductDesc(row.getString("product_desc"));
		  u.setProductQty(row.getInt("product_qty"));
		  u.setSupplierId(row.getInt("product_supplier"));
		  u.setCategoryId(row.getInt("product_cat"));
		  u.setProductPrice(row.getDouble("product_price"));
		  
		  
		  return u;
	
	  }
}
