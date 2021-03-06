package com.project.sportsgeek.repository.statisticsrepo;


import com.project.sportsgeek.mapper.FutureBetsRowMapper;
import com.project.sportsgeek.mapper.StatisticsRowMapper;
import com.project.sportsgeek.model.BetOnTeam;
import com.project.sportsgeek.model.Statistics;
import com.project.sportsgeek.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "statRepo")
public class StatisticsRepoImpl implements  StatisticsRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    private QueryGenerator<Statistics> queryGenerator = new QueryGenerator<Statistics>();
    @Override
    public List<Statistics> findUserStatistics() {
//        String sql = "select u.UserId,FirstName,LastName,UserName,sum(WinningPoints) as TotalWinningPoints " +
//                "from BetOnTeam as bot inner join User as u on bot.UserId=u.UserId group by bot.UserId" +
//                " order by TotalWinningPoints desc";
//        String sql = "SELECT UserId, FirstName, LastName, UserName,AvailablePoints as TotalWinningPoints FROM User ORDER BY AvailablePoints DESC";

        String sql = "select u.UserId,FirstName,LastName,UserName,AvailablePoints as TotalWinningPoints " +
                "from BetOnTeam as bot inner join User as u on bot.UserId=u.UserId WHERE u.Status=1 group by bot.UserId" +
                " order by AvailablePoints desc,TotalWinningPoints desc";
        return jdbcTemplate.query(sql, new StatisticsRowMapper());
    }

    @Override
    public List<BetOnTeam> findFutureBetPoints() {
//        String sql = "SELECT BetPoints FROM BetOnTeam WHERE UserId="+userId+" AND ResultStatus IS NULL "
        String sql = "SELECT UserId, SUM(BetPoints) as TotalBetPoints\n" +
                "FROM BetOnTeam as bot inner join Matches as m on bot.MatchId=m.MatchId\n" +
                "WHERE ResultStatus IS NULL \n" +
                "GROUP BY UserId ORDER BY UserId";
       return jdbcTemplate.query(sql , new FutureBetsRowMapper());
    }
}
