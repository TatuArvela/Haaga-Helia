package com.example.h8.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.h8.bean.Sana;
import com.example.h8.dao.SanaRowMapper;

@Repository
public class SanaDAO {

	@Inject
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void lisaa(Sana s) {
		final String sql = "INSERT INTO sana "
				+ "(sana, seloste, kieli, lisaaja) "
				+ "VALUES (?,?,?,?)";
		
		final String sana = s.getSana();
		final String seloste = s.getSeloste();
		final String kieli = s.getKieli();
		final String lisaaja = s.getLisaajaTunnus();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, sana);
				ps.setString(2, seloste);
				ps.setString(3, kieli);
				ps.setString(4, lisaaja);
				return ps;
			}
		});
	}
	
	public void poista(Sana s) {
		final String sql = "DELETE FROM sana "
				+ "WHERE id=?";
		
		final int id = s.getId();
				
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, id);
				return ps;
			}
		});
	}
	
	public List<Sana> haeKaikki() {
		String sql = "SELECT sana.*, "
				+ "kayttaja.firstname AS lisaaja_etunimi, "
				+ "kayttaja.lastname AS lisaaja_sukunimi "
				+ "FROM sana "
				+ "JOIN kayttaja_sanat AS kayttaja ON lisaaja=kayttaja.username "
				+ "ORDER BY kieli, sana";
		RowMapper<Sana> mapper = new SanaRowMapper();
		List<Sana> sanat = jdbcTemplate.query(sql, mapper);
		return sanat;
	}
	
}
