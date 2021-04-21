package com.project.sportsgeek.service;

import com.project.sportsgeek.exception.ResultException;
import com.project.sportsgeek.model.BetOnTeam;
import com.project.sportsgeek.model.BetOnTeamWithResult;
import com.project.sportsgeek.model.BetOnTeamWithUser;
import com.project.sportsgeek.repository.betonteamrepo.BetOnTeamRepository;
import com.project.sportsgeek.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BetOnTeamService {
    @Autowired
    @Qualifier("betOnTeamsRepo")
    BetOnTeamRepository betonTeamRepository;

    public Result<BetOnTeam> addContest(BetOnTeam betonteam) throws Exception {
        int id = betonTeamRepository.addBetOnTeam(betonteam);
        betonteam.setBetTeamId(id);
        if (id > 0) {
            return new Result<>(201,betonteam);
        }
        throw new ResultException(new Result<>(400, "Error!, please try again!", new ArrayList<>(Arrays
                .asList(new Result.SportsGeekSystemError(betonteam.hashCode(), "unable to add the given Contest")))));
    }

    public Result<List<BetOnTeamWithUser>> findContestById(int id) throws Exception {
//	    System.out.println("Service : " + id);
        List<BetOnTeamWithUser> contestList = betonTeamRepository.findAllContestByMatchId(id);
        if (contestList.size() > 0) {
            System.out.println("Contest Service Called");
            return new Result<>(200, contestList);
        }
        else {
            return new Result(404,"No Contest's found,please try again","Match with id=('"+ id +"') not found");
        }
    }
    public Result<List<BetOnTeamWithResult>> findContestResultByMatchId(int id) throws Exception {
//	    System.out.println("Service : " + id);
        List<BetOnTeamWithResult> contestList = betonTeamRepository.findContestResultByMatchId(id);
        if (contestList.size() > 0) {
            return new Result<>(200, contestList);
        }
        else {
            return new Result(404,"No Contest's found,please try again","Match with id=('"+ id +"') not found");
        }
    }
    public Result<BetOnTeam> findContestByUserAndMatch(int userid,int matchid) throws Exception {
        List<BetOnTeam> contestList = betonTeamRepository.findAllBetsByUserAndMatch(userid,matchid);
        if (contestList.size() > 0) {
            return new Result<>(200, contestList.get(0));
        }
        else {
//            throw new ResultException((new Result<>(404,"No Contest's found,please try again","Contest with id=('"+ userid +"') not found")));
            return new Result<>(500, "No Data Found!");
        }
    }
    public Result<BetOnTeam> updateContest(int id, BetOnTeam betOnTeam) throws Exception {
        if (betonTeamRepository.updateBetOnTeam(id,betOnTeam)) {
            return new Result<>(201,betOnTeam);
        }
        throw new ResultException(new Result<>(400, "Unable to update the given gender details! Please try again!", new ArrayList<>(Arrays
                .asList(new Result.SportsGeekSystemError(betOnTeam.hashCode(), "given genderId('"+id+"') does not exists")))));
//            return new Result<>(500,"Not Found");
    }
}
