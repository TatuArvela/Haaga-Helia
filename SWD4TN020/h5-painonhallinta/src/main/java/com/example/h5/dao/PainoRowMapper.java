package com.example.h5.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.h5.bean.Paino;

public class PainoRowMapper implements RowMapper<Paino> {
	
	public Paino mapRow(ResultSet rs, int rowNum) throws SQLException {
		Paino p = new Paino();
		p.setPvm(rs.getString("pvm"));
		p.setPaino(rs.getDouble("paino"));
		
		return p;
	}
}
