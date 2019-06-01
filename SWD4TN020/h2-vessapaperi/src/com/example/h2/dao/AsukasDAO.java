package com.example.h2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.h2.bean.Asukas;

public class AsukasDAO {
	
	public AsukasDAO() throws DAOPoikkeus {
		try {
			Class.forName(DBConnectionProperties.getInstance().getProperty("driver")).newInstance();
		} catch(Exception e) {
			throw new DAOPoikkeus("Tietokannan ajuria ei kyetty lataamaan.", e);
		}
	}
	
	private Connection avaaYhteys() throws DAOPoikkeus {
		try {
			return DriverManager.getConnection(
					DBConnectionProperties.getInstance().getProperty("url"), 
					DBConnectionProperties.getInstance().getProperty("username"),
					DBConnectionProperties.getInstance().getProperty("password"));
		} catch (Exception e) {
			throw new DAOPoikkeus("Tietokantayhteyden avaaminen ep�onnistui", e);
		}
	}
	
	private void suljeYhteys(Connection yhteys) throws DAOPoikkeus {
		try {
			if (yhteys != null && !yhteys.isClosed())
				yhteys.close();
		} catch(Exception e) {
			throw new DAOPoikkeus("Tietokantayhteys ei jostain syyst� sulkeudu.", e);
		}
	}
	
	public void edistaVuoro() throws DAOPoikkeus{
		boolean suoritetaan = true;
		do {
			// Haetaan asukasta, jolla vuoro = true
			Asukas asukas = new Asukas();
			Connection yhteys = avaaYhteys();
			try {
				String sql = "SELECT * FROM asukas WHERE vuoro = true";
				Statement haku = yhteys.createStatement();
				ResultSet tulokset = haku.executeQuery(sql);
				// Jos asukas l�ytyy, asetetaan asukkaan tiedot
				if (tulokset.isBeforeFirst()) {
					while(tulokset.next()) {
						int id = tulokset.getInt("id");
						String nimi = tulokset.getString("nimi");
						int maksetutvuorot = tulokset.getInt("maksetutvuorot");
						boolean vuoro = tulokset.getBoolean("vuoro");
						asukas = new Asukas(id, nimi, maksetutvuorot, vuoro);
					}
				}
			} catch(Exception e) {
				throw new DAOPoikkeus("Tietokantahaku aiheutti virheen", e);
			} finally {
				suljeYhteys(yhteys);
			}
			
			// Jos vuorollista asukasta ei l�ydy, asetetaan ensimm�iselle asukkaalle vuoro = true
			if (asukas.getId() == 0) {
				yhteys = avaaYhteys();
				try {
					String sql = "UPDATE asukas SET vuoro = true ORDER BY id LIMIT 1;";
					PreparedStatement lause = yhteys.prepareStatement(sql);
					lause.executeUpdate();
				} catch(Exception e) {
					throw new DAOPoikkeus("Vuoron asetusyritys aiheutti virheen", e);
				} finally {
					suljeYhteys(yhteys);
				}
			}
			
			// Jos vuorollisen asukkaan maksetut vuorot > 0
			if (asukas.getMaksetutvuorot() > 0) {
				
				// V�hennet��n maksettu vuoro ja asetetaan vuoro = false
				yhteys = avaaYhteys();
				try {
					String sql = "UPDATE asukas SET maksetutvuorot = maksetutvuorot-1, vuoro = false WHERE id=?";
					PreparedStatement lause = yhteys.prepareStatement(sql);
					lause.setInt(1, asukas.getId());
					lause.executeUpdate();
				} catch(Exception e) {
					throw new DAOPoikkeus("Tietokantahaku aiheutti virheen", e);
				} finally {
					suljeYhteys(yhteys);
				}
				
				// Asetetaan vuoro seuraavalle ID:lle
				yhteys = avaaYhteys();
				try {
					String sql = "UPDATE asukas SET vuoro = true WHERE id=?";
					PreparedStatement lause = yhteys.prepareStatement(sql);
					lause.setInt(1, asukas.getId()+1);
					lause.executeUpdate();
				} catch(Exception e) {
					throw new DAOPoikkeus("Tietokantahaku aiheutti virheen", e);
				} finally {
					suljeYhteys(yhteys);
				}
				
			// Jos vuorollinen asukas on kuitenkin olemassa
			} else if (asukas.getId() > 0) {
				suoritetaan = false;
			}
		} while (suoritetaan == true);
	}
	
	public List<Asukas> haeAsukkaat() throws DAOPoikkeus{		
		ArrayList<Asukas> asukkaat = new ArrayList<Asukas>();
		Connection yhteys = avaaYhteys();
		try {
			String sql = "select * from asukas";
			Statement haku = yhteys.createStatement();
			ResultSet tulokset = haku.executeQuery(sql);
			while(tulokset.next()) {
				int id = tulokset.getInt("id");
				String nimi = tulokset.getString("nimi");
				int maksetutvuorot = tulokset.getInt("maksetutvuorot");
				boolean vuoro = tulokset.getBoolean("vuoro");
				Asukas a = new Asukas(id, nimi, maksetutvuorot, vuoro);
				asukkaat.add(a);
			}	
		} catch(Exception e) {
			throw new DAOPoikkeus("Tietokantahaku aiheutti virheen", e);
		}finally {
			suljeYhteys(yhteys);
		}
		//System.out.println("HAETTIIN TIETOKANNASTA ASUKKAAT: " +asukkaat.toString());
		return asukkaat;
	}
	
	public void maksaVuoro(Asukas a) throws DAOPoikkeus{
		Connection yhteys = avaaYhteys();
		try {
			String sql = "UPDATE asukas SET maksetutvuorot = maksetutvuorot+1 WHERE id = ?;";
			PreparedStatement lause = yhteys.prepareStatement(sql);
			lause.setInt(1, a.getId());
			lause.executeUpdate();
			//System.out.println("LIS�TTIIN ASUKKAALLE "+a+" MAKSETTU VUORO");
		} catch(Exception e) {
			throw new DAOPoikkeus("Maksetun vuoron lis��misyritys aiheutti virheen", e);
		} finally {
			suljeYhteys(yhteys);
		}
	}
	
	public void poistaVuoro(Asukas a) throws DAOPoikkeus{
		Connection yhteys = avaaYhteys();
		try {
			String sql = "UPDATE asukas SET maksetutvuorot = maksetutvuorot-1 WHERE id = ?;";
			PreparedStatement lause = yhteys.prepareStatement(sql);		
			lause.setInt(1, a.getId());
			lause.executeUpdate();
			//System.out.println("POISTETTIIN ASUKKAALTA "+a+" MAKSETTU VUORO");
		} catch(Exception e) {
			throw new DAOPoikkeus("Maksetun vuoron poistoyritys aiheutti virheen", e);
		} finally {
			suljeYhteys(yhteys);
		}
	}
}
