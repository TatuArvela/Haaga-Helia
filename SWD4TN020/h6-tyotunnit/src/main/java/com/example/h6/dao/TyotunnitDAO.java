package com.example.h6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.h6.bean.Tyotunnit;

@Repository
public class TyotunnitDAO {

	@Inject
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void lisaa(Tyotunnit t) {
		final String sql = "INSERT INTO tyotunti "
				+ "(aloitusaika, lopetusaika) "
				+ "VALUES (?,?)";
		
		// Springin @DateTimeFormat ei tue java.sql.Datea. Tästä syystä muussa koodissa käytetään java.util.Datea, 
		// joka muunnetaan tässä kohtaa PreparedStatementin vaatimaan java.sql.* -muotoon.
		// java.sql.Date sisältää vain päiväyksen, joten valitaan päivän ja ajan sisältävä java.sql.Timestamp.
		Date aloitusaika = t.getAloitusaika();
		final java.sql.Timestamp sqlaloitusaika = new java.sql.Timestamp(aloitusaika.getTime()); 
	    Date lopetusaika = t.getLopetusaika();
	    final java.sql.Timestamp sqllopetusaika = new java.sql.Timestamp(lopetusaika.getTime()); 
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setTimestamp(1, sqlaloitusaika);
				ps.setTimestamp(2, sqllopetusaika);
				return ps;
			}
		});
	}
	
	public List<Tyotunnit> haeKaikki() {
		String sql = "SELECT * FROM tyotunti";
		RowMapper<Tyotunnit> mapper = new TyotunnitRowMapper();
		List<Tyotunnit> tyotunnit = jdbcTemplate.query(sql, mapper);
		return tyotunnit;
	}
	
}
