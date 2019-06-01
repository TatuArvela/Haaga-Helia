package com.example.h6.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.h6.bean.Tyotunnit;

public class TyotunnitRowMapper implements RowMapper<Tyotunnit> {

	public Tyotunnit mapRow(ResultSet rs, int rowNum) throws SQLException {
		Tyotunnit t = new Tyotunnit();

		// Java.sql.Date sis�lt�� vain p�iv�yksen, joten valitaan p�iv�n ja ajan sis�lt�v� java.sql.Timestamp.
	    t.setAloitusaika(rs.getTimestamp("aloitusaika"));
	    t.setLopetusaika(rs.getTimestamp("lopetusaika"));
		
		return t;
	}
	
}
