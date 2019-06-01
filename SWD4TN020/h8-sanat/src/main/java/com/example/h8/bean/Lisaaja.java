package com.example.h8.bean;

public class Lisaaja {

	private String tunnus;
	private String etunimi;
	private String sukunimi;
	private int lisatyt;
	
	public Lisaaja() {
		super();
	}

	public Lisaaja(String tunnus, String etunimi, String sukunimi, int lisatyt) {
		super();
		this.tunnus = tunnus;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.lisatyt = lisatyt;
	}

	public String getTunnus() {
		return tunnus;
	}

	public String getEtunimi() {
		return etunimi;
	}

	public String getSukunimi() {
		return sukunimi;
	}

	public int getLisatyt() {
		return lisatyt;
	}

	public void setTunnus(String tunnus) {
		this.tunnus = tunnus;
	}

	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}

	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}

	public void setLisatyt(int lisatyt) {
		this.lisatyt = lisatyt;
	}

	@Override
	public String toString() {
		return "Lisaaja [tunnus=" + tunnus + ", etunimi=" + etunimi + ", sukunimi=" + sukunimi + ", lisatyt=" + lisatyt
				+ "]";
	}
	
}
