package com.project.sportsgeek.repository.betonplayerdetailsrepo;

import com.project.sportsgeek.model.BetOnPlayerDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "betOnPlayerDetailsRepo")
public interface BetOnPlayerDetailsRepo {

    public List<BetOnPlayerDetails> findAllPlayerDetails();
    public List<BetOnPlayerDetails> findBetPlayerDetailsByBetPlayerId(int betPlayerId) throws Exception;
    public List<BetOnPlayerDetails> findAllBetPlayerDetailsByUserId(int userId) throws Exception;
    public int addBetPlayerDetails(BetOnPlayerDetails betOnPlayerDetails) throws Exception;
    public int updateBetPlayerDetails(int betPlayerId,BetOnPlayerDetails betOnPlayerDetails) throws Exception;
    public int deleteBetPlayerDetails(int betPlayerId) throws Exception;
}
