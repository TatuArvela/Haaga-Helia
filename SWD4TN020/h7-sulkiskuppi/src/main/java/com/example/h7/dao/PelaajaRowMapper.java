package com.example.h7.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.h7.bean.Pelaaja;


public class PelaajaRowMapper implements RowMapper<Pelaaja> {
	
	public Pelaaja mapRow(ResultSet rs, int rowNum) throws SQLException {
		Pelaaja p = new Pelaaja();
		p.setId(rs.getInt("id"));
		p.setEtunimi(rs.getString("etunimi"));
		p.setSukunimi(rs.getString("sukunimi"));
		
		return p;
	}
}