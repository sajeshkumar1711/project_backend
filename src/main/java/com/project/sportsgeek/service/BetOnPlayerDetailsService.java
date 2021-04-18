package com.project.sportsgeek.service;

import com.project.sportsgeek.exception.ResultException;
import com.project.sportsgeek.model.BetOnPlayer;
import com.project.sportsgeek.model.BetOnPlayerDetails;
import com.project.sportsgeek.model.BetOnPlayerResponse;
import com.project.sportsgeek.model.BetPlayerDetailsResponse;
import com.project.sportsgeek.repository.betonplayerdetailsrepo.BetOnPlayerDetailsRepo;
import com.project.sportsgeek.repository.betonplayerrepo.BetOnPlayerRepository;
import com.project.sportsgeek.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BetOnPlayerDetailsService {

    @Autowired
    @Qualifier("betOnPlayerDetailsRepo")
    BetOnPlayerDetailsRepo betOnPlayerDetailsRepo;

    public Result<List<BetPlayerDetailsResponse>> findAllBetOnPlayerDetails() {
        List<BetPlayerDetailsResponse> playerList = betOnPlayerDetailsRepo.findAllPlayerDetails();
        return new Result<>(200,"Bet On Player's Details Retrieved Successfully",playerList);
    }
    public Result<BetPlayerDetailsResponse> findBetPlayerDetailsByBetPlayerId(int betPlayerId) throws Exception {
        List<BetPlayerDetailsResponse> playerList = betOnPlayerDetailsRepo.findBetPlayerDetailsByBetPlayerId(betPlayerId);
        if (playerList.size() > 0) {
            return new Result<>(200,"Bet On Player Details Retrieved Successfully", playerList.get(0));
        }
        else {
            throw new ResultException((new Result<>(404,"No Bet's found,please try again","BetPlayer with id=('"+ betPlayerId +"') not found")));
        }
    }
    public Result<BetPlayerDetailsResponse> findBetPlayerDetailsByUserId(int userId) throws Exception {
        List<BetPlayerDetailsResponse> playerList = betOnPlayerDetailsRepo.findAllBetPlayerDetailsByUserId(userId);
        if (playerList.size() > 0) {
            return new Result<>(200,"Bet On Player Detail Retrieved Successfully", playerList.get(0));
        }
        else {
            throw new ResultException((new Result<>(404,"No Bet's found,please try again","User with id=('"+ userId +"') not found")));
        }
    }
    public Result<BetOnPlayerDetails> addBetOnPlayerDetails(BetOnPlayerDetails player) throws Exception {
        int id = betOnPlayerDetailsRepo.addBetPlayerDetails(player);
        if (id > 0) {
            return new Result<>(201,"Bet On Player Details Added Successfully",player);
        }
        throw new ResultException(new Result<>(400, "Error!, please try again!", new ArrayList<>(Arrays
                .asList(new Result.SportsGeekSystemError(player.hashCode(), "unable to add the given Bet Player Details")))));
    }
    public Result<BetOnPlayerDetails> updateBetPlayerDetails(int betPlayerId, int playerNo, BetOnPlayerDetails player) throws Exception {
        int result = betOnPlayerDetailsRepo.updateBetPlayerDetails(betPlayerId,playerNo, player);
        if (result > 0) {
            return new Result<>(201,"BetPlayer Details Updated Successfully",player);
        }
        throw new ResultException(new Result<>(400, "Unable to update the given BetPlayer details! Please try again!", new ArrayList<>(Arrays
                .asList(new Result.SportsGeekSystemError(player.hashCode(), "given BetPlayerId('"+betPlayerId+"') does not exists")))));
    }
    public Result<BetOnPlayerDetails> updatePlayerPoints(int betPlayerId, int playerNo, int playerPoints) throws Exception {
        int result = betOnPlayerDetailsRepo.updatePlayerPoints(betPlayerId, playerNo, playerPoints);
        if (result > 0) {
            return new Result<>(201,"BetPlayer Details Updated Successfully");
        }
        return new Result<>(400,"BetPlayerId Not Found");
    }
    public Result<Integer> deleteBetPlayerDetails(int betPlayerId) throws Exception{
        int data = betOnPlayerDetailsRepo.deleteBetPlayerDetails(betPlayerId);
        if (data > 0) {
            return new Result<>(200,"Bet Player Details Deleted Successfully",data);
        }
        else {
            throw new ResultException((new Result<>(404,"No BetPlayer found to delete,please try again","BetPlayer with id=('"+ betPlayerId +"') not found")));
        }
    }
}
