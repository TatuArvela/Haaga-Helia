package com.example.h8.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.h8.bean.Sana;

public class SanaRowMapper implements RowMapper<Sana> {

	public Sana mapRow(ResultSet rs, int rowNum) throws SQLException {
		Sana s = new Sana();

	    s.setId(rs.getInt("id"));
		s.setSana(rs.getString("sana"));
	    s.setSeloste(rs.getString("seloste"));
	    s.setKieli(rs.getString("kieli"));
	    s.setLisaajaTunnus(rs.getString("lisaaja"));
	    s.setLisaajaEtunimi(rs.getString("lisaaja_etunimi"));
	    s.setLisaajaSukunimi(rs.getString("lisaaja_sukunimi"));
		
		return s;
	}
	
}
