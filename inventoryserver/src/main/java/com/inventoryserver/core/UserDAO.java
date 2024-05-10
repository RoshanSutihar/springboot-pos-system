package com.inventoryserver.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.inventoryserver.services.PasswordService;



@Repository
public class UserDAO {

	
	@Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired 
    PasswordService passwordService;
	
	
	public String save(User user) {
	    // First make sure this is not a duplicate
	    String sql = "SELECT * FROM users WHERE user_name=?";
	    RowMapper<User> rowMapper = new UserRowMapper();
	    
	    
	    User old = null;
	    try {
	        old = jdbcTemplate.queryForObject(sql, rowMapper, user.getUserName());
	    } catch(Exception ex) {
	
	    }
	    if(old != null)
	        return "Duplicate";
	
	    // Have MySQL generate a unique id
	    String idSQL = "select uuid()";
	    String key = null;
	    try {
	        key = jdbcTemplate.queryForObject(idSQL, String.class);
	    } catch(Exception ex) {
	        key = "Error";
	    }
	    if(key.equals("Error"))
	        return key;
	
	    String hash = passwordService.hashPassword(user.getUserPassword());
	    String insertSQL = "insert into users(user_id,user_name,user_password) values (?,?,?)";
	    jdbcTemplate.update(insertSQL,key,user.getUserName(),hash);
	    return key;
	}
	
	
	
	public String findUserNameByNameAndPassword(String name, String password) {
	    String sql = "SELECT user_name, user_password FROM users WHERE user_name=?";
	    RowMapper<User> rowMapper = (rs, rowNum) -> {
	        User user = new User();
	        user.setUserName(rs.getString("user_name"));
	        user.setUserPassword(rs.getString("user_password"));
	        return user;
	    };

	    User result = null;
	    try {
	        result = jdbcTemplate.queryForObject(sql, rowMapper, name);
	    } catch (Exception ex) {
	        // Handle case when no user is found
	        return null;
	    }

	    if (result != null && passwordService.verifyHash(password, result.getUserPassword())) {
	        return result.getUserName(); // Return user name upon successful login
	    } else {
	        return null; // Return null if login is unsuccessful
	    }
	}
	
	
	
	
	
// atend the resturant
	public String attendRestaurant(String userName, String resName) {
		
		String userIdQuery = "SELECT COUNT(*) FROM users WHERE user_id = ?";
		int userCount;
        try {
        	userCount = jdbcTemplate.queryForObject(userIdQuery, Integer.class, userName);
        } catch (Exception e) {
            
            return "UserError";
        }

   
        String resIdQuery = "SELECT COUNT(*) FROM restaurant WHERE res_id = ?";
        int resCount;
        try {
        	 resCount = jdbcTemplate.queryForObject(resIdQuery, Integer.class, resName);
        } catch (Exception e) {
        
            return "ResError";
        }

 
        String insertQuery = "INSERT INTO attandance (user_id, res_id) VALUES (?, ?)";
        try {
            jdbcTemplate.update(insertQuery, userName, resName);
            return "Success you have attended the restaurant";
        } catch (Exception e) {
            
            return "InsertError";
        }
    }
		
	
	
	
	
}
