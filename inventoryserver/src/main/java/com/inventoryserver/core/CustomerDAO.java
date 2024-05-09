package com.inventoryserver.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAO {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	 LocalDateTime now = LocalDateTime.now();
	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	 
	 String formattedDateTime = now.format(formatter);
	 
	 
	 public String save(Customer newcustomer) {
			
		    String sql = "SELECT * FROM cutomers WHERE cus_phone=?";
		    RowMapper<Customer> rowMapper = new CustomerRowMapper();
		    Customer old = null;
		    try {
		        old = jdbcTemplate.queryForObject(sql, rowMapper, newcustomer.getCustomerPhone());
		    } catch(Exception ex) {
		
		    }
		    if(old != null) 
		        return "Duplicate";


		    String insertSQL = "insert into cutomers( cus_name, cus_phone, cus_add, cus_email) values (?,?,?, ?)";
		    Long lastInsertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
		    
		    jdbcTemplate.update(insertSQL,newcustomer.getCustomerName(), newcustomer.getCustomerPhone(), newcustomer.getCustomerAdd(), newcustomer.getCustomerEmail() );
		    return "Success";
		}

}
