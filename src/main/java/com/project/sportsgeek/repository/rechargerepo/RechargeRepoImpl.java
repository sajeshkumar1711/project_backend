package com.project.sportsgeek.repository.rechargerepo;

import com.project.sportsgeek.mapper.RechargeRowMapper;
import com.project.sportsgeek.model.Recharge;
import com.project.sportsgeek.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "rechargeRepo")
public class RechargeRepoImpl implements RechargeRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    private QueryGenerator<Recharge> queryGenerator = new QueryGenerator<Recharge>();

    @Override
    public List<Recharge> findAllRecharge() {
        String sql = "SELECT Recharge.UserId as UserId,UserName,Points,RechargeDatetime,RechargeId FROM Recharge Inner Join User on User.UserId = Recharge.UserId";
        return jdbcTemplate.query(sql,new RechargeRowMapper());
    }

    @Override
    public List<Recharge> findRechargeByRechargeId(int id) throws Exception {
        String sql = "SELECT Recharge.UserId as UserId,UserName,Points,RechargeDatetime,RechargeId FROM Recharge Inner Join User on User.UserId = Recharge.UserId WHERE RechargeId=" + id;
        return jdbcTemplate.query(sql,new RechargeRowMapper());
    }

    @Override
    public List<Recharge> findRechargeByUserId(int id) throws Exception {
//        String sql = "SELECT * FROM Recharge WHERE UserId=" + i;
        String sql = "SELECT Recharge.UserId as UserId,UserName,Points,RechargeDatetime,RechargeId FROM Recharge Inner Join User on User.UserId = Recharge.UserId WHERE Recharge.UserId=" + id;
        return jdbcTemplate.query(sql,new RechargeRowMapper());
    }

    @Override
    public int addRecharge(Recharge recharge) throws Exception {
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            String sql = "INSERT  INTO Recharge(UserId,Points) VALUES("+recharge.getUserId()+",'"+recharge.getPoints()+"')";
            jdbcTemplate.update(sql,new BeanPropertySqlParameterSource(recharge), holder);
            String update_user ="Update User SET AvailablePoints=AvailablePoints+"+recharge.getPoints()+" WHERE UserId="+recharge.getUserId();
            int n = jdbcTemplate.update(update_user,new BeanPropertySqlParameterSource(recharge));
            if(n > 0) {
                return holder.getKey().intValue();
            }else {
                return 0;
            }
        }catch (Exception e) {
            return 0;
        }
    }

    @Override
    public boolean updateRecharge(int id, Recharge recharge) throws Exception {
        try{
            // Get old recharge points
//            String sql = "SELECT * FROM Recharge WHERE RechargeId="+id;
            String sql = "SELECT Recharge.UserId as UserId,UserName,Points,RechargeDatetime,RechargeId FROM Recharge Inner Join User on User.UserId = Recharge.UserId WHERE RechargeId=" + id;
            int oldRechargePoints = jdbcTemplate.query(sql,new RechargeRowMapper()).get(0).getPoints();
            // Update user available points
            int difference = recharge.getPoints() - oldRechargePoints;
            String update_user ="Update User SET AvailablePoints=AvailablePoints+"+difference+" WHERE UserId="+recharge.getUserId();
            jdbcTemplate.update(update_user,new BeanPropertySqlParameterSource(recharge));
            // Update recharge table
            recharge.setRechargeId(id);
            sql = "UPDATE Recharge set "
                    + "`UserId` = :userId, `Points` = :points where `RechargeId`=:rechargeId";
            return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(recharge)) > 0;
        }catch(Exception e){
//            System.out.println("Exception : " + e.toString());
//            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int deleteRecharge(int id) throws Exception {
        try{
            String sql = "SELECT Recharge.UserId as UserId,UserName,Points,RechargeDatetime,RechargeId FROM Recharge Inner Join User on User.UserId = Recharge.UserId WHERE RechargeId=" + id;
            Recharge recharge = jdbcTemplate.query(sql,new RechargeRowMapper()).get(0);
            // Update user available points
            String update_user ="Update User SET AvailablePoints=AvailablePoints-"+recharge.getPoints()+" WHERE UserId="+recharge.getUserId();
            jdbcTemplate.update(update_user,new BeanPropertySqlParameterSource(recharge));
            // Update recharge table
            recharge.setRechargeId(id);
            sql = "DELETE FROM Recharge WHERE RechargeId =" + id;
            return  jdbcTemplate.update(sql,new BeanPropertySqlParameterSource(id));
        }catch(Exception e){
            return 0;
        }
    }
}
