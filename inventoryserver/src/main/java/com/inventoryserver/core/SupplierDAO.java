package com.inventoryserver.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SupplierDAO {
	
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	 LocalDateTime now = LocalDateTime.now();
	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	 
	 String formattedDateTime = now.format(formatter);
	 
	 public String save(Supplier newsupplier) {
			
		    String sql = "SELECT * FROM suppliers WHERE supplier_name=?";
		    RowMapper<Supplier> rowMapper = new SupplierRowMapper();
		    Supplier old = null;
		    try {
		        old = jdbcTemplate.queryForObject(sql, rowMapper, newsupplier.getSupplierContact());
		    } catch(Exception ex) {
		
		    }
		    if(old != null) 
		        return "Duplicate";


		    String insertSQL = "insert into suppliers( supplier_name, supplier_phone, supplier_add, supplier_email) values (?,?,?, ?)";
		    Long lastInsertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
		    
		    jdbcTemplate.update(insertSQL,newsupplier.getSupplierName(), newsupplier.getSupplierContact(), newsupplier.getSupplierAddress(), newsupplier.getSupplierEmail() );
		    return "Success";
		}

}
