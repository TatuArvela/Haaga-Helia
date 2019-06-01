package com.example.h8.dao;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.h8.bean.Lisaaja;
import com.example.h8.dao.LisaajaRowMapper;

@Repository
public class LisaajaDAO {

	@Inject
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Lisaaja> haeKaikki() {
		String sql = "SELECT lisaaja AS tunnus, kayttaja.firstname AS etunimi, kayttaja.lastname AS sukunimi, count(*) AS lisatyt "
				+ "FROM sana "
				+ "JOIN kayttaja_sanat AS kayttaja ON lisaaja=kayttaja.username "
				+ "GROUP BY lisaaja "
				+ "ORDER BY lisatyt DESC";
		RowMapper<Lisaaja> mapper = new LisaajaRowMapper();
		List<Lisaaja> lisaajat = jdbcTemplate.query(sql, mapper);
		return lisaajat;
	}
	
}
