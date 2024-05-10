package com.inventoryserver.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.inventoryserver.core.dtos.CategoryDTO;





@Repository
public class CategoryDAO {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	public String save(Category newcategory) {
		
		String sql = "SELECT * FROM categories WHERE category_name=?";
	    RowMapper<Category> rowMapper = new CategoryRowMapper();
	    Category old = null;
	    try {
	        old = jdbcTemplate.queryForObject(sql, rowMapper, newcategory.getCategoryName());
	    } catch(Exception ex) {
	
	    }
	    if(old != null) 
	        return "Duplicate";


	    String insertSQL = "insert into categories(category_name) values (?)";
	    Long lastInsertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
	    
	    jdbcTemplate.update(insertSQL,newcategory.getCategoryName() );
	    return "Success";
	}
	
	
	 public List<CategoryDTO> getAllCategories() {
			
		 String sql = "SELECT category_name FROM categories";
	        RowMapper<CategoryDTO> rowMapper = new BeanPropertyRowMapper<>(CategoryDTO.class);
	        return jdbcTemplate.query(sql, rowMapper);
	 }
}
