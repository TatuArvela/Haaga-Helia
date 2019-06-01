package com.example.h5.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.h5.bean.Paino;

@Repository
public class PainoDAO {

	@Inject
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void lisaa(Paino p) {
		final String sql = "INSERT INTO paino (paino) "
				+ "VALUES (?)";
		final double paino = p.getPaino();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setDouble(1, paino);
				return ps;
			}
		});
	}
	
	public List<Paino> haeKaikki() {
		String sql = "SELECT * from paino "
				+ "ORDER BY pvm DESC";
		RowMapper<Paino> mapper = new PainoRowMapper();
		List<Paino> painot = jdbcTemplate.query(sql, mapper);
		return painot;
	}
	
}
