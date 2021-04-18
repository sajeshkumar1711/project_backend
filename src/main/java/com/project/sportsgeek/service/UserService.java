package com.project.sportsgeek.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.sportsgeek.exception.ResultException;
import com.project.sportsgeek.model.profile.User;
import com.project.sportsgeek.model.profile.UserAtLogin;
import com.project.sportsgeek.model.profile.UserForLoginState;
import com.project.sportsgeek.model.profile.UserWinningAndLossingPoints;
import com.project.sportsgeek.model.profile.UserWithNewPassword;
import com.project.sportsgeek.model.profile.UserWithOtp;
import com.project.sportsgeek.model.profile.UserWithPassword;
import com.project.sportsgeek.repository.userrepo.UserRepository;
import com.project.sportsgeek.response.Result;

import lombok.SneakyThrows;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    private int otp;
    private int sendOtp;


    public Result<List<User>> findAllUsers() {
        List<User> userList = userRepository.findAllUsers();
        return new Result<>(200,userList);
    }

    public Result<User> findUserByUserId(int id) throws Exception {
        List<User> userList = userRepository.findUserByUserId(id);
        if (userList.size() > 0) {
            return new Result<>(200, userList.get(0));
        }
        else {
            throw new ResultException(new Result<>(404, "no user's found, please try again!",
                    new ArrayList<>(Arrays.asList(new Result.SportsGeekSystemError((id + "").hashCode(),
                            "user with given id('" + id + "') does not exists")))));
        }
    }
    public Result<User> findUserByEmailId(User user) throws Exception {
        List<User> userList = userRepository.findUserByEmailId(user);
        if (userList.size() > 0) {
            sendOtp = generateOTP();
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(userList.get(0).getEmail());
            msg.setSubject("Forgot Password OTP Verification!!");
            msg.setText("Hello "+userList.get(0).getFirstName()+" "+userList.get(0).getLastName()+"\n Your OTP For Password Change.\n" +
                    "OTP:"+ sendOtp +"");
            javaMailSender.send(msg);
            return new Result<>(200, userList.get(0));
        }
        else {
            return new Result<>(404, "Email Id And Mobile Number Not Found");

        }
    }
    public int generateOTP(){

        Random random = new Random();
         otp = 100000 + random.nextInt(900000);
        return otp;
    }
    public Result<UserWinningAndLossingPoints> findUserLoosingPoints(int id) throws Exception {
        List<UserWinningAndLossingPoints> userList = userRepository.findLoosingPointsByUserId(id);
        if (userList.size() > 0) {
            return new Result<>(200, userList.get(0));
        }
        else {
            throw new ResultException(new Result<>(404, "no user's found, please try again!",
                    new ArrayList<>(Arrays.asList(new Result.SportsGeekSystemError((id + "").hashCode(),
                            "user with given id('" + id + "') does not exists")))));
        }
    }
    public Result<UserWinningAndLossingPoints> findUserWinningPoints(int id) throws Exception {
        List<UserWinningAndLossingPoints> userList = userRepository.findWinningPointsByUserId(id);
        if (userList.size() > 0) {
            return new Result<>(200, userList.get(0));
        }
        else {
            throw new ResultException(new Result<>(404, "no user's found, please try again!",
                    new ArrayList<>(Arrays.asList(new Result.SportsGeekSystemError((id + "").hashCode(),
                            "user with given id('" + id + "') does not exists")))));
        }
    }

    public Result<UserWithPassword> findUserByUserName(String userName) throws Exception {
        List<UserWithPassword> userList = userRepository.findUserByUserName(userName);
        if (userList.size() > 0) {
            return new Result<>(200, userList.get(0));
        }
        else {
            throw new ResultException(new Result<>(404, "no user's found, please try again!",
                    new ArrayList<>(Arrays.asList(new Result.SportsGeekSystemError((userName + "").hashCode(),
                            "user with given Username('" + userName + "') does not exists")))));
        }
    }
    public Result<User> addUser(UserWithPassword userWithPassword) throws Exception {
        int id = userRepository.addUser(userWithPassword);
        userWithPassword.setUserId(id);
        if (id > 0) {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(userWithPassword.getEmail());
            msg.setSubject("New User Registration!!");
            msg.setText("Hello "+userWithPassword.getFirstName()+" "+userWithPassword.getLastName()+"\n Welcome to SportsGeek.\n" +
                    "Your account is pending for approval by Admin. Wait For the Response of the approval of account.");
            javaMailSender.send(msg);
            SimpleMailMessage adminmsg = new SimpleMailMessage();
            adminmsg.setTo("admn.sportsgeek@gmail.com");
            adminmsg.setSubject("New User Registration Approval!!");
            adminmsg.setText("Hello Admin \n New user With Name:"+userWithPassword.getFirstName()+" "+userWithPassword.getLastName()+" and username: "+userWithPassword.getUsername()+" " +
                    " has Registered for SportsGeek, Please Approve if he/she is a valid user.\n" +
                    "Thanking you\n" +
                    "SportsGeek Team");
            javaMailSender.send(adminmsg);
            return new Result<>(201, new User(userWithPassword));
        }
        else if (id == -1)
        {
            return new Result<>(400, "Email Already Exists");
        }
        else {
            return new Result<>(404, "UserName Already Exists");
        }

    }

    public Result<User> updateUser(int id, User user) throws Exception {
        if (userRepository.updateUser(id, user)) {
            return new Result<>(201, (User) user);
        }

            return new Result<>(400, "Given User Id does not exists");
    }
    public Result<User> deleteUser(int id) throws Exception {
        int result = userRepository.deleteUser(id);
        if (result>0) {
            return new Result<>(201,"User Status Updated Successfully!!");
        }

        return new Result<>(400, "Given User Id does not exists");
    }
    public Result<User> updateStatus(int id,boolean status) throws Exception {
        if (userRepository.updateStatus(id,status)) {
            return new Result<>(201, "status of given id(" + id + ") has been succefully updated");
        }
        return new Result<>(400, "No User's Found, Please try again!");
    }

    public Result<List<User>> findUsersByStatus(boolean status) throws Exception {
        List<User> userList = userRepository.findUsersByStatus(status);
        if (userList.size() > 0) {
            return new Result<>(200, userList);
        }
        else {

            return new Result<>(400, "No User's Found, Please try again!");
        }
    }
    
    public Result<String> updatePassword(UserWithNewPassword userWithNewPassword) throws Exception {
        int result = userRepository.updateUserPassword(userWithNewPassword);
        if (result > 0) {
            return new Result<>(200, "password has been succefully updated");
        } else if (result == -1) {

            return new Result<>(400, "Old Password does not match!");
        } else {

            return new Result<>(500, "Internal Server error!, Unable to update the password");
        }
    }

    public Result<String> updateForgetPassword(UserWithOtp userWithOtp) throws Exception {
        System.out.println("Send OTP in Update Password Service:"+sendOtp);
        System.out.println("Otp Received by service"+userWithOtp.getOtp());
        if (userWithOtp.getOtp() == sendOtp) {
            int result = userRepository.updateForgetPassword(userWithOtp);
            if (result > 0) {
                return new Result<>(200, "password has been successfully Changed");
            }else {

                return new Result<>(500, "Internal Server error!, Unable to update the password");
            }
        }
        else {
                return new Result<>(404,"OTP Not Match");
        }
    }

    public Result<String> updateUserRole(int userId,int role) throws Exception {
        int result = userRepository.updateUserRole(userId,role);
        if(result > 0) {
            return new Result<>(201, "Successfully Assigned Role to User");
        }
        else {
            return new Result<>(500, "Internal Server error!, Unable to update the Role");
        }
    }

    public Result<UserForLoginState> authenticateStatus(UserAtLogin userAtLogin) throws Exception {
        UserForLoginState userForLoginState =userRepository.authenticate(userAtLogin);
        System.out.println("UserForLoginState:"+userForLoginState);
        if (userForLoginState != null) {
            if (userForLoginState.isStatus() == false) {
                return new Result<>(401, "Sorry! you have been blocked by the admin");
            } else {
                return new Result<>(200, userForLoginState);
            }
        }

        return new Result<>(400, "Invalid username or password!");
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<UserWithPassword> userList = userRepository.findUserByUserName(s);

        if (userList == null) {
            throw new UsernameNotFoundException("User not found with username: " + s);
        }
        else {
            return new org.springframework.security.core.userdetails.User(userList.get(0).getUsername(),userList.get(0).getPassword(),
                    new ArrayList<>());
        }
    }
}
