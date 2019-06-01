package com.example.h7.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.h7.bean.Ottelu;
import com.example.h7.bean.Pelaaja;

public class OtteluRowMapper implements RowMapper<Ottelu> {

	public Ottelu mapRow(ResultSet rs, int rowNum) throws SQLException {
		Ottelu o = new Ottelu();

		o.setId(rs.getInt("id"));
	    o.setPvm(rs.getTimestamp("pvm"));
	    o.setJoukkue1_pelaaja1(
	    		new Pelaaja(
	    				rs.getInt("joukkue1_pelaaja1"),
	    				rs.getString("joukkue1_pelaaja1_etunimi"),
	    				rs.getString("joukkue1_pelaaja1_sukunimi")
	    		)
	    );
	    o.setJoukkue1_pelaaja2(
	    		new Pelaaja(
	    				rs.getInt("joukkue1_pelaaja2"),
	    				rs.getString("joukkue1_pelaaja2_etunimi"),
	    				rs.getString("joukkue1_pelaaja2_sukunimi")
	    		)
	    );
	    o.setJoukkue2_pelaaja1(
	    		new Pelaaja(
	    				rs.getInt("joukkue2_pelaaja1"),
	    				rs.getString("joukkue2_pelaaja1_etunimi"),
	    				rs.getString("joukkue2_pelaaja1_sukunimi")
	    		)
	    );
	    o.setJoukkue2_pelaaja2(
	    		new Pelaaja(
	    				rs.getInt("joukkue2_pelaaja1"),
	    				rs.getString("joukkue2_pelaaja2_etunimi"),
	    				rs.getString("joukkue2_pelaaja2_sukunimi")
	    		)
	    );
	    o.setJoukkue1_pisteet(rs.getInt("joukkue1_pisteet"));
	    o.setJoukkue2_pisteet(rs.getInt("joukkue2_pisteet"));
		
		return o;
	}
}
