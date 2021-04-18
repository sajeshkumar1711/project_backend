package com.project.sportsgeek.mapper;

import com.project.sportsgeek.model.BetOnPlayerDetails;
import com.project.sportsgeek.model.BetPlayerDetailsResponse;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BetPlayerDetailRowMapper implements RowMapper<BetPlayerDetailsResponse> {
    @Override
    public BetPlayerDetailsResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        BetPlayerDetailsResponse betPlayerDetailsResponse = new BetPlayerDetailsResponse();
        betPlayerDetailsResponse.setPlayerNo(rs.getInt("PlayerNo"));
        betPlayerDetailsResponse.setBetPlayerId(rs.getInt("BetPlayerId"));
        betPlayerDetailsResponse.setName(rs.getString("Name"));
        betPlayerDetailsResponse.setProfilePicture(rs.getString("ProfilePicture"));
        betPlayerDetailsResponse.setTeam(rs.getString("TeamName"));
        betPlayerDetailsResponse.setType(rs.getString("TypeName"));
        betPlayerDetailsResponse.setFirstName(rs.getString("FirstName"));
        betPlayerDetailsResponse.setLastName(rs.getString("LastName"));
        betPlayerDetailsResponse.setUserName(rs.getString("UserName"));
        betPlayerDetailsResponse.setPlayerPoints(rs.getInt("PlayerPoints"));
        return betPlayerDetailsResponse;
    }
}
