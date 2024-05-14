package com.inventoryserver.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.inventoryserver.core.dtos.CategoryDTO;
import com.inventoryserver.core.dtos.SupplierDTO;

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
	 
	 public int getTotalSuppliersCount() {
		 
		 String sql = "SELECT COUNT(*) AS total_suppliers FROM suppliers";
	        return jdbcTemplate.queryForObject(sql, Integer.class);
		 
	 }
	 
	

	public List<SupplierDTO> getAllNames() {
		String sql = "SELECT supplier_name FROM suppliers";
        RowMapper<SupplierDTO> rowMapper = new BeanPropertyRowMapper<>(SupplierDTO.class);
        return jdbcTemplate.query(sql, rowMapper);
	}
	
	
	 public List<Supplier> getAllSuppliersdetails() {
		    String query = "SELECT * FROM suppliers";
		    List<Supplier> customers = jdbcTemplate.query(query, new SupplierRowMapper());
		    return customers;
		}

	 public boolean deleteCustomer(String customerId) {
		    String sql = "DELETE FROM suppliers WHERE supplier_id = ?";
		    int rowsAffected = jdbcTemplate.update(sql, customerId);
		    return rowsAffected > 0;
		}

	 public List<Supplier> getSupplierdetails(int userId) {
		    String query = "SELECT * FROM suppliers WHERE supplier_id = ?";
		    List<Supplier> customers = jdbcTemplate.query(query, new Object[]{userId}, new SupplierRowMapper());
		    return customers;
		}

	 
	 public String update(Supplier updatedCustomer, int number) {
		    String updateSQL = "UPDATE suppliers SET supplier_name=?, supplier_add=?, supplier_email=?, supplier_phone=? WHERE supplier_id=?";
		    
		    int rowsAffected = jdbcTemplate.update(updateSQL,
		            updatedCustomer.getSupplierName(),
		            updatedCustomer.getSupplierAddress(),
		            updatedCustomer.getSupplierEmail(),
		            updatedCustomer.getSupplierContact(),
		            number);

		    if (rowsAffected > 0) {
		        return "Success"; // Update successful
		    } else {
		        return "Error"; // Failed to update
		    }
		}

}
