package com.project.sportsgeek.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.project.sportsgeek.model.profile.UserWinningAndLossingPoints;

public class UserWithWinningPointsRowMapper implements RowMapper<UserWinningAndLossingPoints> {

	@Override
	public UserWinningAndLossingPoints mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserWinningAndLossingPoints userWinningPoints = new UserWinningAndLossingPoints();
        userWinningPoints.setWinningPoints(rs.getInt("WinningPoints"));
        userWinningPoints.setUserId(rs.getInt("UserId"));
        return userWinningPoints;
	}

}
