package com.example.h4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.h4.bean.Pistetieto;

@Repository
public class PistetietoDAO {

	@Inject
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void lisaa(Pistetieto p) {
		final String sql = "INSERT INTO pistetieto "
				+ "(opiskelijatunnus, kurssi, tehtava, pisteet) "
				+ "VALUES (?,?,?,?)";
		
		final String opiskelijatunnus = p.getOpiskelijatunnus();
		final String kurssi = p.getKurssi();
		final String tehtava = p.getTehtava();
		final int pisteet = p.getPisteet();
				
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, opiskelijatunnus);
				ps.setString(2, kurssi);
				ps.setString(3, tehtava);
				ps.setInt(4, pisteet);
				return ps;
			}
		});
	}
	
	public void muuta(Pistetieto p) {
		final String sql = "UPDATE pistetieto "
				+ "SET "
				+ "kurssi=?, "
				+ "tehtava=?, "
				+ "pisteet=? "
				+ "WHERE id=?";
		
		final String kurssi = p.getKurssi();
		final String tehtava = p.getTehtava();
		final int pisteet = p.getPisteet();
		final int id = p.getId();
				
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, kurssi);
				ps.setString(2, tehtava);
				ps.setInt(3, pisteet);
				ps.setInt(4, id);
				return ps;
			}
		});
	}
	
	public void poista(Pistetieto p) {
		final String sql = "DELETE FROM pistetieto "
				+ "WHERE id=?";
		
		final int id = p.getId();
				
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, id);
				return ps;
			}
		});
	}
	
	public List<Pistetieto> haeKaikki() {
		String sql = "SELECT pistetieto.*, kayttaja.etunimi, kayttaja.sukunimi "
				+ "FROM pistetieto "
				+ "INNER JOIN kayttaja "
				+ "ON pistetieto.opiskelijatunnus=kayttaja.tunnus";
		RowMapper<Pistetieto> mapper = new PistetietoRowMapper();
		List<Pistetieto> pistetieto = jdbcTemplate.query(sql, mapper);
		return pistetieto;
	}
	
	public List<Pistetieto> haeTunnuksella(final String tunnus) {
		String sql = "SELECT pistetieto.*, kayttaja.etunimi, kayttaja.sukunimi "
				+ "FROM pistetieto "
				+ "INNER JOIN kayttaja "
				+ "ON pistetieto.opiskelijatunnus=kayttaja.tunnus "
				+ "WHERE tunnus=?";
		RowMapper<Pistetieto> mapper = new PistetietoRowMapper();
		List<Pistetieto> pistetieto = jdbcTemplate.query(sql,
			new PreparedStatementSetter() {
				public void setValues(PreparedStatement preparedStatement) throws
				SQLException {
					preparedStatement.setString(1, tunnus);
				}
        }, mapper);
		return pistetieto;
	}
}
