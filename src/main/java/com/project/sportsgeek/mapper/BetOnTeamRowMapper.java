package com.project.sportsgeek.mapper;

import com.project.sportsgeek.model.BetOnTeamWithUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BetOnTeamRowMapper implements RowMapper<BetOnTeamWithUser> {

    @Override
    public BetOnTeamWithUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        BetOnTeamWithUser betonteam = new BetOnTeamWithUser();
        betonteam.setBetTeamId(rs.getInt("BetTeamId"));
        betonteam.setMatchId(rs.getInt("MatchId"));
        betonteam.setUserId(rs.getInt(rs.getInt("UserId")));
        betonteam.setBetPoints(rs.getInt("BetPoints"));
        betonteam.setUsername(rs.getString("Username"));
        betonteam.setTeamId(rs.getInt("TeamId"));
        betonteam.setShortName(rs.getString("TeamShortName"));
//	        betonteam.setShortName(rs.getString("TeamShortName"));
//	        betonteam.setShortName(rs.getString("TeamShortName"));
        betonteam.setProfilePicture(rs.getString("ProfilePicture"));
        betonteam.setFirstName(rs.getString("FirstName"));
        betonteam.setLastName(rs.getString("LastName"));
        betonteam.setWinningPoints(rs.getInt("WinningPoints"));
        return betonteam;
    }
}
