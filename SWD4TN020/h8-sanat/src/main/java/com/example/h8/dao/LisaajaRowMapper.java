package com.example.h8.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.h8.bean.Lisaaja;

public class LisaajaRowMapper implements RowMapper<Lisaaja> {

	public Lisaaja mapRow(ResultSet rs, int rowNum) throws SQLException {
		Lisaaja l = new Lisaaja();

	    l.setTunnus(rs.getString("tunnus"));
	    l.setEtunimi(rs.getString("etunimi"));
	    l.setSukunimi(rs.getString("sukunimi"));
		l.setLisatyt(rs.getInt("lisatyt"));
		
		return l;
	}
	
}
