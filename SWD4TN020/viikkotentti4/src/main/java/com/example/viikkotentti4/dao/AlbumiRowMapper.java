package com.example.viikkotentti4.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.viikkotentti4.bean.Albumi;

public class AlbumiRowMapper implements RowMapper<Albumi> {

	public Albumi mapRow(ResultSet rs, int rowNum) throws SQLException {
		Albumi a = new Albumi();
		a.setId(rs.getInt("id"));
		a.setNimi(rs.getString("nimi"));;
		a.setArtisti(rs.getString("artisti"));;
		a.setJulkaisuvuosi(rs.getInt("julkaisuvuosi"));;
		a.setGenre(rs.getString("genre"));
		a.setFormaatti(rs.getString("formaatti"));
		
		return a;
	}
	
}
