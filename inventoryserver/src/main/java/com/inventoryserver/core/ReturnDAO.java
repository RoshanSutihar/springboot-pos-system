package com.inventoryserver.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReturnDAO {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	 LocalDateTime now = LocalDateTime.now();
	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	 
	 String formattedDateTime = now.format(formatter);
	
	 public String save(Return newReturn) {
		    String insertReturnSql = "INSERT INTO returns (return_date, return_orderid, return_product, return_qty, return_reason) VALUES (?, ?, ?, ?, ?)";
		    String updateProductSql = "UPDATE products SET product_qty = product_qty + ? WHERE product_name = ?";
		  
		    try {
		        jdbcTemplate.execute("START TRANSACTION");

		        // Check if the order exists and contains the product ID
		        Integer orderCount = jdbcTemplate.queryForObject(
		            "SELECT COUNT(*) FROM orders WHERE order_id = ? AND EXISTS (SELECT 1 FROM orderdetails WHERE order_id = ? AND product_name = ?)", 
		            Integer.class, 
		            newReturn.getOrderId(), 
		            newReturn.getOrderId(), 
		            newReturn.getProductId()
		        );

		        if (orderCount == null || orderCount == 0) {
		            jdbcTemplate.execute("ROLLBACK");
		            return "Error: Order does not exist or does not contain the specified product.";
		        }

		        // Check the quantity of the product ordered
		        int orderedQty = jdbcTemplate.queryForObject(
		            "SELECT product_qty FROM orderdetails WHERE order_id = ? AND product_name = ?", 
		            Integer.class, 
		            newReturn.getOrderId(), 
		            newReturn.getProductId()
		        );

		        if (newReturn.getQtyReturned() > orderedQty) {
		            jdbcTemplate.execute("ROLLBACK");
		            return "Error: Return quantity cannot be greater than ordered quantity.";
		        }

		        // Insert the return record
		        jdbcTemplate.update(insertReturnSql, formattedDateTime, newReturn.getOrderId(), newReturn.getProductId(), newReturn.getQtyReturned(), 
		                 newReturn.getReturnReason());

		        // Update product quantity
		        jdbcTemplate.update(updateProductSql, newReturn.getQtyReturned(), newReturn.getProductId());

		        jdbcTemplate.execute("COMMIT");

		        return "Success";
		    } catch(Exception ex) {
		        jdbcTemplate.execute("ROLLBACK");
		        ex.printStackTrace();
		        return "Error";
		    }
		}


	
}
