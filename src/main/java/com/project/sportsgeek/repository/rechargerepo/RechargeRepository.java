package com.project.sportsgeek.repository.rechargerepo;

import com.project.sportsgeek.model.Recharge;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "rechargeRepo")
public interface RechargeRepository {
    public List<Recharge> findAllRecharge();
    public List<Recharge> findRechargeByRechargeId(int i) throws Exception;
    public List<Recharge> findRechargeByUserId(int i) throws Exception;
    public int addRecharge(Recharge Recharge) throws Exception;
    public boolean updateRecharge(int id, Recharge Recharge) throws Exception;
    public int deleteRecharge(int id) throws Exception;
}
