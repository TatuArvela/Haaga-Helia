package com.example.viikkotentti4.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.viikkotentti4.bean.Albumi;

public class AlbumiDAOSpringJdbcImpl implements AlbumiDAO {

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Albumi> haeKaikki() {
		
		String sql = "SELECT * FROM albumi";
		RowMapper<Albumi> mapper = new AlbumiRowMapper();
		List<Albumi> albumit = jdbcTemplate.query(sql, mapper);
		
		return albumit;
	}
	
}
