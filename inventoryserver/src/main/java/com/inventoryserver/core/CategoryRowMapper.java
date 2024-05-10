package com.inventoryserver.core;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CategoryRowMapper implements RowMapper<Category>{
	
	@Override
    public Category mapRow(ResultSet row, int rowNum) throws SQLException {
	  Category u = new Category();
	  
	  u.setCategoryName(row.getString("category_name"));
	  u.setCategoryID(row.getInt("category_id"));
	  
	  return u;

  }

}
