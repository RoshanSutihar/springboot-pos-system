package com.inventoryserver.core;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OrderRowMapper implements RowMapper<Order> {

	
	@Override
    public Order mapRow(ResultSet row, int rowNum) throws SQLException {
		
		Order u = new Order();
		  u.setOrderID(row.getInt("order_id"));
		u.setCustomerID(row.getString("customer_id"));
		u.setOrderDate(row.getString("order_date"));
		u.setShipDate(row.getString("ship_date"));
		u.setTotalAmount(row.getDouble("total_amount"));
		u.setOrderStatus(row.getString("order_status"));
		u.setPaymentType(row.getString("payment_type"));
		u.setOrigin(row.getString("order_origin"));
		return u;
		
		
	}
}
