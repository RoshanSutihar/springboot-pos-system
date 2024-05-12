package com.inventoryserver.core;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OrderDetailRowMapper implements RowMapper<OrderDetail>{
	
	@Override
    public OrderDetail mapRow(ResultSet row, int rowNum) throws SQLException {
		
		OrderDetail u = new OrderDetail();
		
		u.setOrderId(row.getInt("order_id"));
		u.setProductId(row.getString("product_name"));
		u.setProductQty(row.getInt("product_qty"));
		u.setUnitPrice(row.getInt("product_unitprice"));
		u.setProductTotal(row.getDouble("product_total"));
		
		
		return u;
	}
}
