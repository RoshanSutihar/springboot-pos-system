package com.inventoryserver.core;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;



public class CustomerRowMapper implements RowMapper<Customer>{

	@Override
    public Customer mapRow(ResultSet row, int rowNum) throws SQLException {
        Customer u = new Customer();
        u.setCustomerAdd(row.getString("cus_add"));
        u.setCustomerPhone(row.getString("cus_phone"));
        u.setCustomerEmail(row.getString("cus_email"));
        u.setCustomerName(row.getString("cus_name"));
        u.setCustomerID(row.getInt("cus_id"));
        return u;
    }
	
}
