package com.example.h4.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.h4.bean.Opiskelija;

public class OpiskelijaRowMapper implements RowMapper<Opiskelija> {

	public Opiskelija mapRow(ResultSet rs, int rowNum) throws SQLException {
		Opiskelija o = new Opiskelija();
		o.setTunnus(rs.getString("tunnus"));
		o.setEtunimi(rs.getString("etunimi"));
		o.setSukunimi(rs.getString("sukunimi"));
		
		return o;
	}
	
}
