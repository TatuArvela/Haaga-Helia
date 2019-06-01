package com.example.h4.dao;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.h4.bean.Opiskelija;

@Repository
public class OpiskelijaDAO {
	
	@Inject
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Opiskelija> haeKaikki() {
		String sql = "SELECT tunnus, etunimi, sukunimi "
				+ "FROM kayttaja "
				+ "WHERE onopettaja=0";
		RowMapper<Opiskelija> mapper = new OpiskelijaRowMapper();
		List<Opiskelija> opiskelijat = jdbcTemplate.query(sql, mapper);
		return opiskelijat;
	}
}
