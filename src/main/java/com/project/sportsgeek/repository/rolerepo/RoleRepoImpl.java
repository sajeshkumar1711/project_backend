package com.project.sportsgeek.repository.rolerepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.project.sportsgeek.mapper.RoleRowMapper;
import com.project.sportsgeek.model.profile.Role;
import com.project.sportsgeek.query.QueryGenerator;

@Repository(value = "roleRepo")
public class RoleRepoImpl implements RoleRepository{

	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    private QueryGenerator<Role> queryGenerator = new QueryGenerator<Role>();
	@Override
	public List<Role> findAllRole() {
		 String sql = "SELECT * FROM Role";
	        return jdbcTemplate.query(sql,new RoleRowMapper());
	}
	@Override
	public List<Role> findRoleById(int id) throws Exception {
		 String sql = "SELECT * FROM Role WHERE RoleId="+id;
	        return jdbcTemplate.query(sql,new RoleRowMapper());
	}
	@Override
	public int addRole(Role role) throws Exception {
		KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(queryGenerator.generatePreparedStatementInsertQuery("Role", role),
                new BeanPropertySqlParameterSource(role), holder);
        return holder.getKey().intValue();
	}
	@Override
	public boolean updateRole(int id, Role role) throws Exception {
		String sql = "UPDATE `" + "Role" + "` set "
                + "`Name` = :name where `RoleId`=:RoleId";
        role.setRoleId(id);
        return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(role)) > 0;
	}
	@Override
	public int deleteRole(int id) throws Exception {
		String sql = "DELETE FROM Role WHERE RoleId =" + id;
        return  jdbcTemplate.update(sql,new BeanPropertySqlParameterSource(id));
	}
}
