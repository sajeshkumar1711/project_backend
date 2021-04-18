package com.project.sportsgeek.repository.betonplayerrepo;

import com.project.sportsgeek.config.ServiceProperties;
import com.project.sportsgeek.mapper.BetOnPlayerRowMapper;
import com.project.sportsgeek.model.BetOnPlayer;
import com.project.sportsgeek.model.BetOnPlayerResponse;
import com.project.sportsgeek.model.Player;
import com.project.sportsgeek.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "betOnPlayerRepo")
public class BetOnPlayerRepoImpl implements BetOnPlayerRepository{

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    private final QueryGenerator<BetOnPlayer> queryGenerator = new QueryGenerator<>();

    @Autowired
    ServiceProperties serviceProperties;
    @Override
    public List<BetOnPlayerResponse> findAllBetOnPlayer() {
//        return jdbcTemplate.query(serviceProperties.getDbQueries().getListAllBetPlayer(), new BetOnPlayerRowMapper());
        String sql = "SELECT  b.BetPlayerId as BetPlayerId, u.Username as UserName,u.FirstName as FirstName,u.LastName as LastName, t.ShortName as Team1, t1.ShortName as Team2, m.StartDatetime as StartDateTime, b.TotalGamePoints as TotalGamePoints " +
                "FROM BetOnPlayer as b INNER JOIN User as u on b.UserId = u.UserId INNER JOIN Matches as m on b.MatchId = m.MatchId INNER JOIN Team as t on m.Team1 = t.TeamId  INNER JOIN Team as t1 on m.Team2 = t1.TeamId";
        return jdbcTemplate.query(sql,new BetOnPlayerRowMapper());
    }

    @Override
    public List<BetOnPlayerResponse> findBetOnPlayerByBetPlayerId(int betPlayerId) throws Exception {
        String sql = "SELECT b.BetPlayerId as BetPlayerId, u.Username as UserName,u.FirstName as FirstName,u.LastName as LastName, t.ShortName as Team1, t1.ShortName as Team2, m.StartDatetime as StartDateTime, b.TotalGamePoints as TotalGamePoints " +
                "FROM BetOnPlayer as b INNER JOIN User as u on b.UserId = u.UserId INNER JOIN Matches as m on b.MatchId = m.MatchId INNER JOIN Team as t on m.Team1 = t.TeamId  INNER JOIN Team as t1 on m.Team2 = t1.TeamId " +
                "WHERE b.BetPlayerId="+betPlayerId;
        return jdbcTemplate.query(sql,new BetOnPlayerRowMapper());
    }

    @Override
    public List<BetOnPlayerResponse> findBetOnPlayerByUserId(int userId) throws Exception {
        String sql = "SELECT b.BetPlayerId as BetPlayerId, u.Username as UserName,u.FirstName as FirstName,u.LastName as LastName, t.ShortName as Team1, t1.ShortName as Team2, m.StartDatetime as StartDateTime, b.TotalGamePoints as TotalGamePoints " +
                "FROM BetOnPlayer as b INNER JOIN User as u on b.UserId = u.UserId INNER JOIN Matches as m on b.MatchId = m.MatchId INNER JOIN Team as t on m.Team1 = t.TeamId  INNER JOIN Team as t1 on m.Team2 = t1.TeamId " +
                "WHERE u.UserId="+userId;
        return jdbcTemplate.query(sql,new BetOnPlayerRowMapper());
    }

    @Override
    public List<BetOnPlayerResponse> findAllBetOnPlayerByMatchId(int matchId) throws Exception {
        String sql = "SELECT b.BetPlayerId as BetPlayerId, u.Username as UserName,u.FirstName as FirstName,u.LastName as LastName, t.ShortName as Team1, t1.ShortName as Team2, m.StartDatetime as StartDateTime, b.TotalGamePoints as TotalGamePoints " +
                "FROM BetOnPlayer as b INNER JOIN User as u on b.UserId = u.UserId INNER JOIN Matches as m on b.MatchId = m.MatchId INNER JOIN Team as t on m.Team1 = t.TeamId  INNER JOIN Team as t1 on m.Team2 = t1.TeamId " +
                "WHERE m.MatchId="+matchId;
        return jdbcTemplate.query(sql,new BetOnPlayerRowMapper());
    }

    @Override
    public int addBetOnPlayer(BetOnPlayer player) throws Exception {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(queryGenerator.generatePreparedStatementInsertQuery("BetOnPlayer", player),
                new BeanPropertySqlParameterSource(player), holder);
        return holder.getKey().intValue();
    }

    @Override
    public int updateBetOnPlayer(int betPlayerId, BetOnPlayer player) throws Exception {
        String sql = "UPDATE betonplayer SET UserId="+player.getUserId()+",MatchId="+player.getMatchId()+",TotalGamePoints="+player.getTotalGamePoints()+" WHERE BetPlayerId="+betPlayerId;
        player.setBetPlayerId(betPlayerId);
        return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(betPlayerId));
    }

    @Override
    public int updateTotalGamePoints(int betPlayerId, int points) throws Exception {
        String sql = "UPDATE betonplayer SET TotalGamePoints="+points+" WHERE BetPlayerId="+betPlayerId;
        return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(betPlayerId));
    }

    @Override
    public int deleteBetOnPlayer(int betPlayerId) throws Exception {
       String sql = "DELETE FROM BetOnPlayer WHERE BetPlayerId="+betPlayerId;
       return  jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(betPlayerId));
    }
}
