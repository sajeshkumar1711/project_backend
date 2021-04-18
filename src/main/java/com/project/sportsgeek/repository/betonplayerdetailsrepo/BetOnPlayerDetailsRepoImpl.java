package com.project.sportsgeek.repository.betonplayerdetailsrepo;

import com.project.sportsgeek.model.BetOnPlayerDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "betOnPlayerDetailsRepo")
public class BetOnPlayerDetailsRepoImpl implements BetOnPlayerDetailsRepo {
    @Override
    public List<BetOnPlayerDetails> findAllPlayerDetails() {
        return null;
    }

    @Override
    public List<BetOnPlayerDetails> findBetPlayerDetailsByBetPlayerId(int betPlayerId) throws Exception {
        return null;
    }

    @Override
    public List<BetOnPlayerDetails> findAllBetPlayerDetailsByUserId(int userId) throws Exception {
        return null;
    }

    @Override
    public int addBetPlayerDetails(BetOnPlayerDetails betOnPlayerDetails) throws Exception {
        return 0;
    }

    @Override
    public int updateBetPlayerDetails(int betPlayerId, BetOnPlayerDetails betOnPlayerDetails) throws Exception {
        return 0;
    }

    @Override
    public int deleteBetPlayerDetails(int betPlayerId) throws Exception {
        return 0;
    }
}
