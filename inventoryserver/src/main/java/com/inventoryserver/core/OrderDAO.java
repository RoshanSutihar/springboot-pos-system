package com.inventoryserver.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAO {
	
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	 LocalDateTime now = LocalDateTime.now();
	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	 
	 String formattedDateTime = now.format(formatter);
	 
	 

public String save(Order newOrder) {
    String insertSql = "INSERT INTO orders (order_date, customer_id, ship_date, total_amount) VALUES (?, ?, ?, ?)";
    String insertDetailSql = "INSERT INTO orderdetails (order_id, product_name, product_qty, product_unitprice, product_total) VALUES (?, ?, ?, ?, ?)";
    String updateProductSql = "UPDATE products SET product_qty = product_qty - ? WHERE product_name = ?";
    
    try {
        // Start a transaction
        jdbcTemplate.execute("START TRANSACTION");

        // Insert the order
        jdbcTemplate.update(insertSql, formattedDateTime, newOrder.getCustomerID(), newOrder.getShipDate(), newOrder.getTotalAmount());

        // Get the ID of the inserted order
        int orderId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        // Insert order details and update product quantities
        for (OrderDetail detail : newOrder.getOrderDetails()) {
            jdbcTemplate.update(insertDetailSql, orderId, detail.getProductId(), detail.getProductQty(), detail.getUnitPrice(), detail.getProductTotal());
            jdbcTemplate.update(updateProductSql, detail.getProductQty(), detail.getProductId());
        }

        // Commit the transaction
        jdbcTemplate.execute("COMMIT");

        return "Success";
    } catch(Exception ex) {
        
        jdbcTemplate.execute("ROLLBACK");
        ex.printStackTrace();
        return "Error";
    }
}
	 
	 
	 public int getTotalValue()
	 {
		 String sql = "SELECT SUM(total_amount) AS total_amnt FROM orders";
	        Integer totalQuantity = jdbcTemplate.queryForObject(sql, Integer.class);
	        return totalQuantity != null ? totalQuantity : 0;
	 }

	 
	 public Order getOrderById(int orderId) {
	        String query = "SELECT * FROM orders WHERE order_id = ?";
	        try {
	            List<Order> orders = jdbcTemplate.query(query, new Object[]{orderId}, new OrderRowMapper());
	            if (!orders.isEmpty()) {
	                Order order = orders.get(0);
	                List<OrderDetail> orderDetails = getOrderDetailsByOrderId(orderId);
	                order.setOrderDetails(orderDetails);
	                return order;
	            }
	            return null;
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return null;
	        }
	    }

	    private List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
	        String query = "SELECT * FROM orderdetails WHERE order_id = ?";
	        try {
	            List<OrderDetail> orderDetails = jdbcTemplate.query(query, new Object[]{orderId}, new OrderDetailRowMapper());
	            return orderDetails;
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return null;
	        }
	    }
}
