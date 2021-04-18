package com.project.sportsgeek.repository.betonteamrepo;

import com.project.sportsgeek.model.BetOnTeam;
import com.project.sportsgeek.model.BetOnTeamWithResult;
import com.project.sportsgeek.model.BetOnTeamWithUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "betOnTeamsRepo")
public interface BetOnTeamRepository {
    public List<BetOnTeam> findAllBets();
    public List<BetOnTeam> findAllBetsByUser(int id) throws Exception;
    public List<BetOnTeam> findAllBetsByUserAndMatch(int userid, int matchid) throws Exception;
    //    public List<BetOnTeamWithUser> findAllBetsByMatch(int id) throws Exception;
    public List<BetOnTeamWithUser> findAllContestByMatchId(int matchId) throws Exception;
    public List<BetOnTeamWithResult> findContestResultByMatchId(int matchId) throws Exception;
    public List<BetOnTeam> findAllBetsByTeam(int id) throws Exception;
    public List<BetOnTeam> findAllBetPointsByUser(int id) throws Exception;
    public List<BetOnTeam> findAllWinningPointsByUser(int id) throws Exception;
    public int addBetOnTeam(BetOnTeam betOnTeam) throws Exception;
    public boolean updateBetOnTeam(int id, BetOnTeam betOnTeam) throws Exception;
    public int updateBetPoints(int id, int betPoints) throws Exception;
    public int updateWinningPoints(int id, int WinningPoints) throws Exception;
    public int deleteBetOnTeam(int id) throws Exception;
}
