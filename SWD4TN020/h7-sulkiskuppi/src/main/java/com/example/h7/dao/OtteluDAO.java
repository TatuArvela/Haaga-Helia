package com.example.h7.dao;

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

import com.example.h7.bean.Ottelu;

@Repository
public class OtteluDAO {

	@Inject
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void lisaa(Ottelu o) {
		Date pvm = o.getPvm();
		final java.sql.Timestamp sqlpvm = new java.sql.Timestamp(pvm.getTime());
		final int joukkue1_pelaaja1 = o.getJoukkue1_pelaaja1().getId();
		final int joukkue1_pelaaja2 = o.getJoukkue1_pelaaja2().getId();
		final int joukkue2_pelaaja1 = o.getJoukkue2_pelaaja1().getId();
		final int joukkue2_pelaaja2 = o.getJoukkue2_pelaaja2().getId();
		final int joukkue1_pisteet = o.getJoukkue1_pisteet();
		final int joukkue2_pisteet = o.getJoukkue2_pisteet();
		
		final String sql = "INSERT INTO ottelu (pvm, "
				+ "joukkue1_pelaaja1, joukkue1_pelaaja2, "
				+ "joukkue2_pelaaja1, joukkue2_pelaaja2, "
				+ "joukkue1_pisteet, joukkue2_pisteet) "
				+ "VALUES (?,?,?,?,?,?,?)";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setTimestamp(1, sqlpvm);
				ps.setInt(2, joukkue1_pelaaja1);
				ps.setInt(3, joukkue1_pelaaja2);
				ps.setInt(4, joukkue2_pelaaja1);
				ps.setInt(5, joukkue2_pelaaja2);
				ps.setInt(6, joukkue1_pisteet);
				ps.setInt(7, joukkue2_pisteet);
				return ps;
			}
		});
	}
	
	public List<Ottelu> haeKaikki() {
		// Haetaan ottelut ja pelaajien tiedot
		String sql = "SELECT ottelu.*, "
				+ "j1p1.etunimi AS joukkue1_pelaaja1_etunimi, "
				+ "j1p1.sukunimi AS joukkue1_pelaaja1_sukunimi, "
				+ "j1p2.etunimi AS joukkue1_pelaaja2_etunimi, "
				+ "j1p2.sukunimi AS joukkue1_pelaaja2_sukunimi, "
				+ "j2p1.etunimi AS joukkue2_pelaaja1_etunimi, "
				+ "j2p1.sukunimi AS joukkue2_pelaaja1_sukunimi, "
				+ "j2p2.etunimi AS joukkue2_pelaaja2_etunimi, "
				+ "j2p2.sukunimi AS joukkue2_pelaaja2_sukunimi "
				+ "FROM ottelu "
				+ "JOIN pelaaja AS j1p1 ON joukkue1_pelaaja1=j1p1.id "
				+ "JOIN pelaaja AS j1p2 ON joukkue1_pelaaja2=j1p2.id "
				+ "JOIN pelaaja AS j2p1 ON joukkue2_pelaaja1=j2p1.id "
				+ "JOIN pelaaja AS j2p2 ON joukkue2_pelaaja2=j2p2.id "
				+ "ORDER BY GREATEST(joukkue1_pisteet, joukkue2_pisteet) DESC";
		RowMapper<Ottelu> mapper = new OtteluRowMapper();
		List<Ottelu> ottelut = jdbcTemplate.query(sql, mapper);
		return ottelut;
	}
	
}
