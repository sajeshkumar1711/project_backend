package com.project.sportsgeek.repository.userrepo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.sportsgeek.model.profile.User;
import com.project.sportsgeek.model.profile.UserAtLogin;
import com.project.sportsgeek.model.profile.UserForLoginState;
import com.project.sportsgeek.model.profile.UserWinningAndLossingPoints;
import com.project.sportsgeek.model.profile.UserWithNewPassword;
import com.project.sportsgeek.model.profile.UserWithOtp;
import com.project.sportsgeek.model.profile.UserWithPassword;

@Repository(value = "userRepo")
public interface UserRepository {
	
    public List<User> findAllUsers();
    public List<User> findUserByUserId(int id) throws Exception;
    public List<UserWithPassword> findUserByUserName(String userName) throws Exception;
    public List<User> findAllUsersByRole(int role) throws Exception;
    public List<User> findUserByEmailId(User user) throws Exception;
    public List<UserWinningAndLossingPoints> findLoosingPointsByUserId(int userId) throws Exception;
    public List<UserWinningAndLossingPoints> findWinningPointsByUserId(int userId) throws Exception;
    public List<User> findUsersByStatus(boolean status) throws Exception;
    public int addUser(UserWithPassword userWithPassword) throws Exception;
    public boolean updateUser(int id, User user) throws Exception;
    public boolean updateStatus(int id,boolean status) throws Exception;
    public int updateUserPassword(UserWithNewPassword userWithNewPassword) throws Exception;
    public int updateForgetPassword(UserWithOtp userWithOtp) throws Exception;
    public int updateUserRole(int userId, int role) throws Exception;
    public boolean updateUserProfilePicture(String profilePicture) throws Exception;
    public boolean updateUserAvailablePoints(int availablePoints) throws Exception;
    public UserForLoginState authenticate(UserAtLogin userAtLogin) throws Exception;
    public int deleteUser(int id) throws Exception;

}
