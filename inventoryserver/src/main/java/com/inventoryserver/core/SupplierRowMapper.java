package com.inventoryserver.core;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class SupplierRowMapper implements RowMapper<Supplier>{
	
	
	@Override
    public Supplier mapRow(ResultSet row, int rowNum) throws SQLException {
	
		 Supplier u = new Supplier();
		 
		 u.setSupplierName(row.getString("supplier_name"));
		 u.setSupplierAddress(row.getString("supplier_add"));
		 u.setSupplierContact(row.getString("supplier_phone"));
		 u.setSupplierEmail(row.getString("supplier_email"));
		 
		 return u;
	}
	

}
