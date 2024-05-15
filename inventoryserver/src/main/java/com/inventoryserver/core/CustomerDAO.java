package com.inventoryserver.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.inventoryserver.core.dtos.CustomerDTO;

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
	 
	 
	 public String update(Customer updatedCustomer, int number) {
		    String updateSQL = "UPDATE cutomers SET cus_name=?, cus_add=?, cus_email=?, cus_phone=? WHERE cus_id=?";
		    
		    int rowsAffected = jdbcTemplate.update(updateSQL,
		            updatedCustomer.getCustomerName(),
		            updatedCustomer.getCustomerAdd(),
		            updatedCustomer.getCustomerEmail(),
		            updatedCustomer.getCustomerPhone(),
		            number);

		    if (rowsAffected > 0) {
		        return "Success"; // Update successful
		    } else {
		        return "Error"; // Failed to update
		    }
		}

	 
	 public int getTotalCustomerCount()
	 {
		 String sql = "SELECT COUNT(*) AS total_customers FROM cutomers";
	        return jdbcTemplate.queryForObject(sql, Integer.class);
	 }
	 
	 
	 public List<CustomerDTO> getAllCustomers() {
		 String query = "SELECT * FROM cutomers";
	        List<Customer> customers = jdbcTemplate.query(query, new CustomerRowMapper());

	        // Convert Customer objects to CustomerDTO objects
	        return customers.stream()
	                .map(this::convertToDto)
	                .collect(Collectors.toList());
	    }
	 
	 
	 private CustomerDTO convertToDto(Customer customer) {
	        CustomerDTO dto = new CustomerDTO();
	        // Set DTO properties based on Customer properties
	        
	        dto.setCustomerName(customer.getCustomerName());
	        // Set other properties as needed
	        return dto;
	    }
	 
	 public List<Customer> getAllCustomersdetails() {
		    String query = "SELECT * FROM cutomers";
		    List<Customer> customers = jdbcTemplate.query(query, new CustomerRowMapper());
		    return customers;
		}
	 
	 public List<Customer> getCustomersdetails(int userId) {
		    String query = "SELECT * FROM cutomers WHERE cus_id = ?";
		    List<Customer> customers = jdbcTemplate.query(query, new Object[]{userId}, new CustomerRowMapper());
		    return customers;
		}

	 public boolean deleteCustomer(String customerId) {
		    String sql = "DELETE FROM cutomers WHERE cus_id = ?";
		    int rowsAffected = jdbcTemplate.update(sql, customerId);
		    return rowsAffected > 0;
		}
	
}
