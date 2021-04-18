package com.project.sportsgeek.repository.genderrepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.project.sportsgeek.mapper.GenderRowMapper;
import com.project.sportsgeek.model.Gender;
import com.project.sportsgeek.query.QueryGenerator;

@Repository(value = "genderRepo")
public class GenderRepositoryImpl implements GenderRepository{

	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    private QueryGenerator<Gender> queryGenerator = new QueryGenerator<Gender>();
    
	@Override
	public List<Gender> findAllGender() {
		String sql = "SELECT * FROM Gender";
        return jdbcTemplate.query(sql,new GenderRowMapper());
	}

	@Override
	public List<Gender> findGenderById(int id) throws Exception {
		 String sql = "SELECT * FROM Gender WHERE GenderId="+id;
	        return jdbcTemplate.query(sql,new GenderRowMapper());
	}

	@Override
	public int addGender(Gender gender) throws Exception {
		KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(queryGenerator.generatePreparedStatementInsertQuery("Gender", gender),
                new BeanPropertySqlParameterSource(gender), holder);
        return holder.getKey().intValue();
	}

	@Override
	public boolean updateGender(int id, Gender gender) throws Exception {
		String sql = "UPDATE `" + "Gender" + "` set "
                + "`Name` = :name where `GenderId`=:GenderId";
        gender.setGenderId(id);
        return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(gender)) > 0;
	}

	@Override
	public int deleteGender(int id) throws Exception {
		String sql = "DELETE FROM Gender WHERE GenderId =" + id;
        return  jdbcTemplate.update(sql,new BeanPropertySqlParameterSource(id));
	}

}
