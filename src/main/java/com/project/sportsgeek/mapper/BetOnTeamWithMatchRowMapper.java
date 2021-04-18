package com.project.sportsgeek.mapper;

import com.project.sportsgeek.model.BetOnTeam;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BetOnTeamWithMatchRowMapper implements RowMapper<BetOnTeam> {
    @Override
    public BetOnTeam mapRow(ResultSet rs, int rowNum) throws SQLException {
        BetOnTeam betOnTeam = new BetOnTeam();
        betOnTeam.setBetTeamId(rs.getInt("BetTeamId"));
        betOnTeam.setTeamId(rs.getInt("TeamId"));
        betOnTeam.setBetPoints(rs.getInt("BetPoints"));
        betOnTeam.setMatchId(rs.getInt("MatchId"));
        betOnTeam.setUserId(rs.getInt("UserId"));
        betOnTeam.setWinningPoints(rs.getInt("WinningPoints"));
        return betOnTeam;
    }
}
