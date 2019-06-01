package com.example.h4.dao;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAO {

	@Inject
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int kirjaudu(final String tunnus, final String salasana) {
		int status = -1;
		try {
			status = this.jdbcTemplate.queryForObject(
				"SELECT onopettaja FROM kayttaja WHERE tunnus=? AND salasana=?",
				Integer.class, tunnus, salasana);
		} catch(EmptyResultDataAccessException e) {
			status = -1;
		}
		return status;
	}
	
}
