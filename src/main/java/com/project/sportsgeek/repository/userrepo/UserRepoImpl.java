package com.project.sportsgeek.repository.userrepo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.project.sportsgeek.mapper.GenderRowMapper;
import com.project.sportsgeek.mapper.UserRowMapper;
import com.project.sportsgeek.mapper.UserRowMapper2;
import com.project.sportsgeek.mapper.UserWithPasswordRowMapper;
import com.project.sportsgeek.mapper.UserWithWinningPointsRowMapper;
import com.project.sportsgeek.mapper.userWithLoosingPointsRowMapper;
import com.project.sportsgeek.model.profile.User;
import com.project.sportsgeek.model.profile.UserAtLogin;
import com.project.sportsgeek.model.profile.UserForLoginState;
import com.project.sportsgeek.model.profile.UserWinningAndLossingPoints;
import com.project.sportsgeek.model.profile.UserWithNewPassword;
import com.project.sportsgeek.model.profile.UserWithOtp;
import com.project.sportsgeek.model.profile.UserWithPassword;
import com.project.sportsgeek.query.QueryGenerator;

@Repository(value = "userRepo")
public class UserRepoImpl implements UserRepository{

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	private QueryGenerator<UserWithPassword> queryGenerator = new QueryGenerator<UserWithPassword>();
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public List<User> findAllUsers() {
		String sql = "SELECT User.UserId as UserId, FirstName, LastName, GenderId, RoleId, Username, AvailablePoints, ProfilePicture, Status, EmailContact.EmailId as Email, MobileContact.MobileNumber as MobileNumber " +
				"FROM User inner join EmailContact on User.UserId=EmailContact.UserID inner join MobileContact on User.UserId=MobileContact.UserId WHERE User.Status=1 ";
		return jdbcTemplate.query(sql,new UserRowMapper());
	}

	@Override
	public List<User> findUserByUserId(int id) throws Exception {
		String sql = "SELECT User.UserId as UserId, FirstName, LastName, GenderId, RoleId, Username, AvailablePoints, ProfilePicture, Status, EmailContact.EmailId as Email, MobileContact.MobileNumber as MobileNumber " +
                "FROM User inner join EmailContact on User.UserId=EmailContact.UserId inner join MobileContact on User.UserId=MobileContact.UserId WHERE User.UserId="+id;
		return jdbcTemplate.query(sql, new UserRowMapper());
	}

	@Override
	public List<UserWithPassword> findUserByUserName(String userName) throws Exception {
		String sql = "SELECT UserName,Password FROM User WHERE UserName='"+userName+"'";
        return jdbcTemplate.query(sql,new UserWithPasswordRowMapper());
	}

	@Override
	public List<User> findAllUsersByRole(int role) throws Exception {
		String sql = "SELECT * FROM User WHERE RoleId="+role;
        return jdbcTemplate.query(sql,new UserRowMapper());
	}

	@Override
	public List<User> findUserByEmailId(User user) throws Exception {
		String sql = "SELECT User.UserId as UserId, FirstName, LastName, GenderId, RoleId, Username, AvailablePoints, ProfilePicture, Status, EmailContact.EmailId as Email, MobileContact.MobileNumber as MobileNumber " +
                "FROM User inner join EmailContact on User.UserId=EmailContact.UserId inner join MobileContact on User.UserId=MobileContact.UserId WHERE EmailContact.EmailId='"+ user.getEmail() +"' AND MobileContact.MobileNumber='"+  user.getMobileNumber()+"' ";
        return jdbcTemplate.query(sql, new UserRowMapper());
	}

	@Override
	public List<UserWinningAndLossingPoints> findLoosingPointsByUserId(int userId) throws Exception {
		String sql = "SELECT SUM(BetPoints) as LoosingPoints,bot.UserId \n" +
                "FROM BetOnTeam as bot INNER JOIN Matches as m ON bot.MatchId = m.MatchId\n" +
                "WHERE WinningPoints=0 AND m.ResultStatus=1 AND UserId="+userId;
        return jdbcTemplate.query(sql,new userWithLoosingPointsRowMapper());
	}

	@Override
	public List<UserWinningAndLossingPoints> findWinningPointsByUserId(int userId) throws Exception {
		String sql = "select sum(WinningPoints) as WinningPoints,UserId from BetOnTeam where UserId="+userId;
        return jdbcTemplate.query(sql, new UserWithWinningPointsRowMapper());
	}

	@Override
	public List<User> findUsersByStatus(boolean status) throws Exception {
		String sql = "SELECT User.UserId as UserId, FirstName, LastName, GenderId, RoleId, Username, AvailablePoints, ProfilePicture, Status, EmailContact.EmailId as Email, MobileContact.MobileNumber as MobileNumber " +
                "FROM User inner join EmailContact on User.UserId=EmailContact.UserId inner join MobileContact on User.UserId=MobileContact.UserId WHERE User.Status="+status;
        return jdbcTemplate.query(sql,new UserRowMapper());
	}

	@Override
	public int addUser(UserWithPassword userWithPassword) throws Exception {
		RowCountCallbackHandler countCallback = new RowCountCallbackHandler();
        jdbcTemplate.query("select * from User WHERE UserName='"+userWithPassword.getUsername()+"'", countCallback);
        int rowCount = countCallback.getRowCount();
        jdbcTemplate.query("select * from EmailContact WHERE EmailId='"+userWithPassword.getEmail()+"'", countCallback);
        int emailCount = countCallback.getRowCount();

        System.out.println("UserName Count"+rowCount);
        if(rowCount > 0){
            return 0;
        }
        else if(emailCount > 0){
            return -1;
        }
        else {
            String gender_sql = "SELECT * from Gender WHERE GenderId=" + userWithPassword.getGenderId();
            int genderid = jdbcTemplate.query(gender_sql, new GenderRowMapper()).get(0).getGenderId();
            System.out.println(genderid);

            String insert_sql = "INSERT INTO User (FirstName,LastName,GenderId,Username,Password,ProfilePicture,RoleId,AvailablePoints,Status)" +
                    "values ('" + userWithPassword.getFirstName() + "','" + userWithPassword.getLastName() + "'," + genderid + ",'" + userWithPassword.getUsername() + "'" +
                    ",'" + userWithPassword.getPassword() + "','" + userWithPassword.getProfilePicture() + "'," + userWithPassword.getRoleId() + "," + userWithPassword.getAvailablePoints() + "," + userWithPassword.isStatus() + ")";
            jdbcTemplate.update(insert_sql, new BeanPropertySqlParameterSource(userWithPassword));
            String sql = "SELECT * from User WHERE UserName = '" + userWithPassword.getUsername() + "'";
            List<User> userList = jdbcTemplate.query(sql, new UserRowMapper2());
            int userid = userList.get(0).getUserId();
            System.out.println(userid);
            String email_sql = "INSERT INTO EmailContact (UserId,EmailId) values (" + userid + ", '" + userWithPassword.getEmail() + "')";
            jdbcTemplate.update(email_sql, new BeanPropertySqlParameterSource(userWithPassword));
            String mobile_sql = "INSERT INTO MobileContact (UserId,MobileNumber) values (" + userid + ", '" + userWithPassword.getMobileNumber() + "')";
            jdbcTemplate.update(mobile_sql, new BeanPropertySqlParameterSource(userWithPassword));
            return 1;
        }
	}

	@Override
	public boolean updateUser(int id, User user) throws Exception {
		 String update_email = "UPDATE EmailContact SET EmailId = '"+user.getEmail()+"' WHERE UserId = "+id;
	        jdbcTemplate.update(update_email,new BeanPropertySqlParameterSource(user));

	        String update_mobile = "UPDATE MobileContact SET MobileNumber='"+user.getMobileNumber()+"' WHERE UserId="+id;
	        jdbcTemplate.update(update_mobile,new BeanPropertySqlParameterSource(user));
	        String sql = "UPDATE `" + "User" + "` set "
	                + "`FirstName` = :firstName, `LastName` = :lastName, `GenderId` = :genderId where UserId="+id;
	        user.setUserId(id);
	        return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(user)) > 0;
	}

	@Override
	public boolean updateStatus(int id, boolean status) throws Exception {
		String fetch_user = "SELECT User.UserId as UserId, FirstName, LastName, GenderId, RoleId, Username, AvailablePoints, ProfilePicture, Status, EmailContact.EmailId as Email, MobileContact.MobileNumber as MobileNumber " +
                "FROM User inner join EmailContact on User.UserId=EmailContact.UserId inner join MobileContact on User.UserId=MobileContact.UserId WHERE User.UserId="+id;
        List<User> userList =  jdbcTemplate.query(fetch_user,new UserRowMapper());
        if (userList.size() > 0 ) {
            User user = userList.get(0);
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(user.getEmail());
            msg.setSubject("Account Approved!!");
            msg.setText("Congratulations "+user.getFirstName()+" "+user.getLastName()+",\n\n" +
                    "Your account is approved by the Admin. \n" +
                    "Your username is \"" + user.getUsername() + "\".\n" +
                    "Now, You can login to the app and enjoy SportsGeek.\n" +
                    "\n" +
                    "Thanking you\n" +
                    "SportsGeek Team");
            javaMailSender.send(msg);
        }
        String sql = "UPDATE `" + "User" + "` set "
                + "`Status` = "+status+" where `UserId`="+id;
        return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(id)) > 0;
	}

	@Override
	public int updateUserPassword(UserWithNewPassword userWithNewPassword) throws Exception {
		String encodedPassword = bCryptPasswordEncoder.encode(userWithNewPassword.getOldPassword());

      String  select_password = "SELECT * FROM User Where UserId="+userWithNewPassword.getUserId();
      List<UserWithPassword> userWithNewPasswords = jdbcTemplate.query(select_password,new UserWithPasswordRowMapper());
      if (userWithNewPasswords.size() > 0 && bCryptPasswordEncoder.matches(userWithNewPassword.getOldPassword(),
              userWithNewPasswords.get(0).getPassword()))
      {
          userWithNewPassword.setNewPassword(bCryptPasswordEncoder.encode(userWithNewPassword.getNewPassword()));
          String sql = "UPDATE User SET Password = :newPassword WHERE UserId = :UserId";
          return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(userWithNewPassword));
      }

      return -1;
	}

	@Override
	public int updateForgetPassword(UserWithOtp userWithOtp) throws Exception {
		System.out.println("Password : " + userWithOtp.getPassword());
        String encodedPassword = bCryptPasswordEncoder.encode(userWithOtp.getPassword());
        String sql = "UPDATE User SET Password ='"+encodedPassword+"' WHERE UserId='"+userWithOtp.getUserId()+"'";
        return jdbcTemplate.update(sql,new BeanPropertySqlParameterSource(userWithOtp));
	}

	@Override
	public int updateUserRole(int userId, int role) throws Exception {
		 String sql = "UPDATE User SET RoleId="+role+" WHERE UserId="+userId;
	        return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(userId));
	}

	@Override
	public boolean updateUserProfilePicture(String profilePicture) throws Exception {
		String sql = "UPDATE `" + "User" + "` set "
                + "`ProfilePicture` = :profilePicture where `UserId`=:UserId";
        return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(profilePicture)) > 0;
	}

	@Override
	public boolean updateUserAvailablePoints(int availablePoints) throws Exception {
		String sql = "UPDATE `" + "User" + "` set "
                + "`AvailablePoints` = :availablePoints where `UserId`=:UserId";
        return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(availablePoints)) > 0;
	}

	@Override
	public UserForLoginState authenticate(UserAtLogin userAtLogin) throws Exception {
		 String sql = "select `UserId`,`UserName`, `Name` as `Role`,`Status` from User inner join Role on User.RoleId = Role.RoleId where `UserName`=:username";

       List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,
               new BeanPropertySqlParameterSource(userAtLogin));
       System.out.println(list.size());
       if (list.size() > 0) {

               return new UserForLoginState(Integer.parseInt(list.get(0).get("UserId") + ""),
                       list.get(0).get("UserName") + "", list.get(0).get("Role") + "", Boolean.parseBoolean(list.get(0).get("Status").toString()));

       }
       return null;
	}

	@Override
	public int deleteUser(int id) throws Exception {
		String delete_emailContact = "DELETE FROM EmailContact WHERE UserId="+id;
        jdbcTemplate.update(delete_emailContact, new BeanPropertySqlParameterSource(id));
        String delete_mobileContact = "DELETE FROM MobileContact WHERE UserId="+id;
        jdbcTemplate.update(delete_mobileContact, new BeanPropertySqlParameterSource(id));
        String delete_Recharge = "DELETE FROM Recharge WHERE UserId="+id;
        jdbcTemplate.update(delete_Recharge, new BeanPropertySqlParameterSource(id));
        String delete_betOnTeam = "DELETE FROM BetOnTeam Where UserId="+id;
        jdbcTemplate.update(delete_betOnTeam, new BeanPropertySqlParameterSource(id));

        String delete_user = "DELETE FROM User WHERE UserId="+id;
        return jdbcTemplate.update(delete_user, new BeanPropertySqlParameterSource(id));
	}

}
