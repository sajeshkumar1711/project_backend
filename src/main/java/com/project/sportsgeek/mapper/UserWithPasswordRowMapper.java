package com.project.sportsgeek.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.project.sportsgeek.model.profile.UserWithPassword;

public class UserWithPasswordRowMapper implements RowMapper<UserWithPassword>{

	@Override
	public UserWithPassword mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserWithPassword userWithPassword = new UserWithPassword();
        userWithPassword.setUsername(rs.getString("UserName"));
        userWithPassword.setPassword(rs.getString("Password"));
        return userWithPassword;
	}

}
