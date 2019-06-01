package com.example.h3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.h3.bean.*;

@Repository
public class HenkiloDAOSpringJdbcImpl implements HenkiloDAO {

	@Inject
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void lisaa(Henkilo h) {
		final String sql = "INSERT INTO henkilo "
				+ "(etunimi, sukunimi, puhelin, email, osoite, postinumero, postitoimipaikka) "
				+ "VALUES (?,?,?,?,?,?,?)";
		
		final String etunimi = h.getEtunimi();
		final String sukunimi = h.getSukunimi();
		final String puhelin = h.getPuhelin();
		final String email = h.getEmail();
		final String osoite = h.getOsoite();
		final String postinumero = h.getPostinumero();
		final String postitoimipaikka = h.getPostitoimipaikka();
				
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, etunimi);
				ps.setString(2, sukunimi);
				ps.setString(3, puhelin);
				ps.setString(4, email);
				ps.setString(5, osoite);
				ps.setString(6, postinumero);
				ps.setString(7, postitoimipaikka);
				return ps;
			}
		});
		
	}

	public void muuta(Henkilo h) {
		final String sql = "UPDATE henkilo "
				+ "SET "
				+ "etunimi=?, "
				+ "sukunimi=?, "
				+ "puhelin=?, "
				+ "email=?, "
				+ "osoite=?, "
				+ "postinumero=?, "
				+ "postitoimipaikka=? "
				+ "WHERE id=?";
		
		final int id = h.getId();
		final String etunimi = h.getEtunimi();
		final String sukunimi = h.getSukunimi();
		final String puhelin = h.getPuhelin();
		final String email = h.getEmail();
		final String osoite = h.getOsoite();
		final String postinumero = h.getPostinumero();
		final String postitoimipaikka = h.getPostitoimipaikka();
				
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, etunimi);
				ps.setString(2, sukunimi);
				ps.setString(3, puhelin);
				ps.setString(4, email);
				ps.setString(5, osoite);
				ps.setString(6, postinumero);
				ps.setString(7, postitoimipaikka);
				ps.setInt(8, id);
				return ps;
			}
		});
		
	}

	public void poista(Henkilo h) {
		final String sql = "DELETE FROM henkilo "
				+ "WHERE id=?";
		
		final int id = h.getId();
				
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, id);
				return ps;
			}
		});
	}

	public List<Henkilo> haeKaikki() {

		String sql = "select * from henkilo";
		RowMapper<Henkilo> mapper = new HenkiloRowMapper();
		List<Henkilo> henkilot = jdbcTemplate.query(sql, mapper);

		return henkilot;
	}

}
