package com.example.h4.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.h4.bean.Pistetieto;

public class PistetietoRowMapper implements RowMapper<Pistetieto> {

	public Pistetieto mapRow(ResultSet rs, int rowNum) throws SQLException {
		Pistetieto pt = new Pistetieto();
		pt.setId(rs.getInt("id"));
		pt.setOpiskelijatunnus(rs.getString("opiskelijatunnus"));
		pt.setOpiskelijanEtunimi(rs.getString("etunimi"));
		pt.setOpiskelijanSukunimi(rs.getString("sukunimi"));
		pt.setKurssi(rs.getString("kurssi"));
		pt.setTehtava(rs.getString("tehtava"));
		pt.setPisteet(rs.getInt("pisteet"));
		
		return pt;
	}
	
}