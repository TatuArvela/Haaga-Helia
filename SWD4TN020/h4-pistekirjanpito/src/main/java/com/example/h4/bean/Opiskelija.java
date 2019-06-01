package com.example.h4.bean;

public class Opiskelija {

	private String tunnus;
	private String etunimi;
	private String sukunimi;
	
	public Opiskelija() {
		super();
	}

	public Opiskelija(String tunnus, String etunimi, String sukunimi) {
		super();
		this.tunnus = tunnus;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
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

	public void setTunnus(String tunnus) {
		this.tunnus = tunnus;
	}

	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}

	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}

	@Override
	public String toString() {
		return "Opiskelija [tunnus=" + tunnus + ", etunimi=" + etunimi + ", sukunimi=" + sukunimi + "]";
	}
	
}
