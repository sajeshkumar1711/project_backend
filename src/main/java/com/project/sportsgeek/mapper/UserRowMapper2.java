package com.project.sportsgeek.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.project.sportsgeek.model.profile.User;

public class UserRowMapper2 implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
        user.setUserId(rs.getInt("UserId"));
        user.setFirstName(rs.getString("FirstName"));
        user.setGenderId(rs.getInt("GenderId"));
        user.setUsername(rs.getString("UserName"));
        user.setAvailablePoints(rs.getInt("AvailablePoints"));
        user.setLastName(rs.getString("LastName"));
        user.setRoleId(rs.getInt("RoleId"));
        user.setStatus(rs.getBoolean("Status"));
        user.setProfilePicture(rs.getString("ProfilePicture"));
        return user;
	}

}
