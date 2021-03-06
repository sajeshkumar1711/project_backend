package com.project.sportsgeek.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.project.sportsgeek.model.profile.UserWinningAndLossingPoints;

public class userWithLoosingPointsRowMapper implements RowMapper<UserWinningAndLossingPoints> {

	@Override
	public UserWinningAndLossingPoints mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		UserWinningAndLossingPoints userLossingPoints = new UserWinningAndLossingPoints();
        userLossingPoints.setLoosingPoints(rs.getInt("LoosingPoints"));
        userLossingPoints.setUserId(rs.getInt("UserId"));
        return userLossingPoints;
	}

}
