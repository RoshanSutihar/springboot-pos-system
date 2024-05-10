package com.inventoryserver.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet row, int rowNum) throws SQLException {
        User u = new User();
        u.setUserId(row.getString("user_id"));
        u.setUserName(row.getString("user_name"));
        u.setUserPassword(row.getString("user_password"));
  
        return u;
    }
}
