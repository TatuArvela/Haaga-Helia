package com.example.h3.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.h3.bean.Henkilo;
import com.example.h3.bean.HenkiloImpl;

public class HenkiloRowMapper implements RowMapper<Henkilo> {

	public Henkilo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Henkilo h = new HenkiloImpl();
		h.setId(rs.getInt("id"));
		h.setEtunimi(rs.getString("etunimi"));
		h.setSukunimi(rs.getString("sukunimi"));
		h.setPuhelin(rs.getString("puhelin"));
		h.setEmail(rs.getString("email"));
		h.setOsoite(rs.getString("osoite"));
		h.setPostinumero(rs.getString("postinumero"));
		h.setPostitoimipaikka(rs.getString("postitoimipaikka"));
		
		return h;
	}
	
}
