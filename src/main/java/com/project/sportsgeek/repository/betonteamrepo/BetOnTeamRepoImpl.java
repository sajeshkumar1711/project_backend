package com.project.sportsgeek.repository.betonteamrepo;

import com.project.sportsgeek.mapper.BetOnTeamRowMapper;
import com.project.sportsgeek.mapper.BetOnTeamWithMatchRowMapper;
import com.project.sportsgeek.mapper.ContestWithUsersResultRowMapper;
import com.project.sportsgeek.model.BetOnTeam;
import com.project.sportsgeek.model.BetOnTeamWithResult;
import com.project.sportsgeek.model.BetOnTeamWithUser;
import com.project.sportsgeek.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "betOnTeamsRepo")
public class BetOnTeamRepoImpl implements BetOnTeamRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    private QueryGenerator<BetOnTeam> queryGenerator = new QueryGenerator<BetOnTeam>();

    @Override
    public List<BetOnTeam> findAllBets() {
        return null;
    }

    @Override
    public List<BetOnTeam> findAllBetsByUser(int id) throws Exception {
        return null;
    }

    @Override
    public List<BetOnTeam> findAllBetsByUserAndMatch(int userid, int matchid) throws Exception {
        String sql = "SELECT BetTeamId, UserId,MatchId,TeamId,BetPoints,WinningPoints from BetOnTeam WHERE UserId = "+userid+" and MatchId="+matchid;
        return jdbcTemplate.query(sql,new BetOnTeamWithMatchRowMapper());
    }

    @Override
    public List<BetOnTeamWithUser> findAllContestByMatchId(int matchId) throws Exception {
//        String sql= "SELECT BetOnTeam.BetTeamId as BetTeamId, BetOnTeam.UserId as UserId, BetOnTeam.MatchId as MatchId, BetOnTeam.TeamId as TeamId, Team.ShortName as TeamShortName, User.FirstName as FirstName, User.LastName as LastName, User.Username as UserName, User.ProfilePicture as ProfilePicture, BetOnTeam.BetPoints as BetPoints, BetOnTeam.WinningPoints as WinningPoints" +
//                " FROM BetOnTeam inner join User on BetOnTeam.UserId = User.UserId inner join Team on BetOnTeam.TeamId = Team.TeamId WHERE BetOnTeam.MatchId="+matchId;

//        String sql= "SELECT BetOnTeam.BetTeamId as BetTeamId, Team.ShortName as TeamShortName, User.Username as UserName, BetOnTeam.BetPoints as BetPoints" +
//                " FROM BetOnTeam inner join User on BetOnTeam.UserId = User.UserId inner join Team on BetOnTeam.TeamId = Team.TeamId WHERE BetOnTeam.MatchId="+matchId;

//        String sql = "SELECT BetTeamId, UserId,MatchId,TeamId,BetPoints,WinningPoints from BetOnTeam WHERE  MatchId="+matchId;
//        String sql = "SELECT BetTeamId, bot.UserId as UserId, MatchId, bot.TeamId as TeamId, t.ShortName as TeamShortName, FirstName, LastName, Username, ProfilePicture, BetPoints, WinningPoints" +
//                 " FROM BetOnTeam as bot inner join User as u on bot.UserId = u.UserId inner join Team as t on bot.TeamId = t.TeamId WHERE MatchId="+matchId;

        String sql = "SELECT BetTeamId, bot.UserId as UserId, MatchId, bot.TeamId as TeamId, t.ShortName as TeamShortName, FirstName, LastName, Username, ProfilePicture, BetPoints, WinningPoints " +
                "FROM BetOnTeam as bot, User as u, Team as t " +
                "WHERE bot.TeamId=t.TeamId AND bot.UserId=u.UserId AND MatchId="+matchId;

        List<BetOnTeamWithUser> betOnTeamWithUsers = jdbcTemplate.query(sql, new BetOnTeamRowMapper());
        System.out.println(betOnTeamWithUsers);
        return betOnTeamWithUsers;
    }

    @Override
    public List<BetOnTeamWithResult> findContestResultByMatchId(int matchId) throws Exception {
        String sql = "SELECT BetTeamId, t.ShortName as TeamShortName, FirstName, LastName, Username, ProfilePicture, BetPoints, WinningPoints FROM BetOnTeam as bot inner join User as u on bot.UserId=u.UserId inner join Team as t on bot.TeamId=t.TeamId " +
                "WHERE MatchId="+matchId+" ORDER BY WinningPoints desc, BetPoints desc;";
        return jdbcTemplate.query(sql, new ContestWithUsersResultRowMapper());
    }

//    @Override
//    public List<BetOnTeamWithUser> findAllBetsByMatch(int id) throws Exception {
//        System.out.println("Repo Impl : " + id);
////        	String sql = "SELECT BetTeamId, bot.UserId as UserId, MatchId, bot.TeamId as TeamId, t.ShortName as TeamShortName, FirstName, LastName, Username, ProfilePicture, BetPoints, WinningPoints" +
////                    " FROM BetOnTeam as bot inner join User as u on bot.UserId = u.UserId inner join Team as t on bot.TeamId = t.TeamId WHERE MatchId="+id;
//
////        	String sql = "SELECT bot.BetTeamId as BetTeamId, bot.MatchId as MatchId, bot.TeamId as TeamId, t.ShortName as TeamShortName, u.UserId as UserId, u.Username as UserName, u.FirstName as FirstName, u.LastName as LastName, u.ProfilePicture as ProfilePicture, bot.BetPoints as BetPoints, bot.WinningPoints as WinningPoints " +
////                    "FROM BetOnTeam as bot inner join Team as t on bot.TeamId=t.TeamId " +
////                    "inner join User as u on bot.UserId=u.UserId " +
////                    "WHERE bot.MatchId = " + id;
//        String sql = "SELECT BetTeamId, UserId,MatchId,TeamId,BetPoints,WinningPoints from BetOnTeam WHERE MatchId="+id;
////        return jdbcTemplate.query(sql,new BetOnTeamWithMatchRowMapper());
////        	String sql = "SELECT BetTeamId, MatchId, bot.TeamId, t.Name as TeamShortName, u.UserId as UserId, Username, FirstName, LastName, ProfilePicture, BetPoints, WinningPoints " +
////                    "FROM BetOnTeam as bot, Team as t, User as u " +
////                    "WHERE bot.TeamId=t.TeamId AND bot.UserId=u.UserId AND MatchId="+id;
////            System.out.println(sql);
//        	return jdbcTemplate.query(sql,new BetOnTeamsRowMapper());
//    }

    @Override
    public List<BetOnTeam> findAllBetsByTeam(int id) throws Exception {
        return null;
    }

    @Override
    public List<BetOnTeam> findAllBetPointsByUser(int id) throws Exception {
        return null;
    }

    @Override
    public List<BetOnTeam> findAllWinningPointsByUser(int id) throws Exception {
        return null;
    }

    @Override
    public int addBetOnTeam(BetOnTeam betOnTeam) throws Exception {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(queryGenerator.generatePreparedStatementInsertQuery("BetOnTeam",betOnTeam),
                new BeanPropertySqlParameterSource(betOnTeam), holder);
        int Userid = betOnTeam.getUserId();
        String sql = "UPDATE User SET AvailablePoints = AvailablePoints - "+betOnTeam.getBetPoints()+" WHERE UserId = "+Userid;
        jdbcTemplate.update(sql,new BeanPropertySqlParameterSource(betOnTeam));
        return holder.getKey().intValue();
    }

    @Override
    public boolean updateBetOnTeam(int id, BetOnTeam betOnTeam) throws Exception {
        betOnTeam.setBetTeamId(id);
        String select_betpoints = "SELECT BetTeamId, UserId,MatchId,TeamId,BetPoints,WinningPoints FROM BetOnTeam WHERE BetTeamId="+id;
        int betpoints = jdbcTemplate.query(select_betpoints,new BetOnTeamWithMatchRowMapper()).get(0).getBetPoints();
        String update_betpoints = "Update User SET AvailablePoints = AvailablePoints + "+(betpoints-betOnTeam.getBetPoints())+" WHERE UserId="+betOnTeam.getUserId();
        jdbcTemplate.update(update_betpoints,new BeanPropertySqlParameterSource(betOnTeam));
        String sql = "Update BetOnTeam SET TeamId ="+betOnTeam.getTeamId()+", BetPoints= "+betOnTeam.getBetPoints()+" WHERE BetTeamId="+id;
        return jdbcTemplate.update(sql,new BeanPropertySqlParameterSource(betOnTeam))>0;
    }

    @Override
    public int updateBetPoints(int id, int betPoints) throws Exception {
        return 0;
    }

    @Override
    public int updateWinningPoints(int id, int WinningPoints) throws Exception {
        return 0;
    }

    @Override
    public int deleteBetOnTeam(int id) throws Exception {
        return 0;
    }
}
