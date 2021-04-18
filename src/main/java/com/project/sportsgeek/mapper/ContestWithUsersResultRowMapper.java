package com.project.sportsgeek.mapper;

import com.project.sportsgeek.model.BetOnTeamWithResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContestWithUsersResultRowMapper implements RowMapper<BetOnTeamWithResult> {
    @Override
    public BetOnTeamWithResult mapRow(ResultSet rs, int rowNum) throws SQLException {

        BetOnTeamWithResult betOnTeamWithResult = new BetOnTeamWithResult();
        betOnTeamWithResult.setBetTeamId(rs.getInt("BetTeamId"));
        betOnTeamWithResult.setBetPoints(rs.getInt("BetPoints"));
        betOnTeamWithResult.setFirstName(rs.getString("FirstName"));
        betOnTeamWithResult.setLastName(rs.getString("LastName"));
        betOnTeamWithResult.setTeamShortName(rs.getString("TeamShortName"));
        betOnTeamWithResult.setWinningPoints(rs.getInt("WinningPoints"));
        betOnTeamWithResult.setProfilePicture(rs.getString("ProfilePicture"));
        betOnTeamWithResult.setUsername(rs.getString("Username"));
        return  betOnTeamWithResult;
    }
}
