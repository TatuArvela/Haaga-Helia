package com.example.h7.dao;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.h7.bean.Pelaaja;

@Repository
public class PelaajaDAO {

	@Inject
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Pelaaja> haeKaikki() {
		String sql = "SELECT * from pelaaja "
				+ "WHERE id <> 1 "
				+ "ORDER BY id ASC";
		RowMapper<Pelaaja> mapper = new PelaajaRowMapper();
		List<Pelaaja> pelaajat = jdbcTemplate.query(sql, mapper);
		return pelaajat;
	}
	
}
