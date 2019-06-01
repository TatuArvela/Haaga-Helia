package com.example.h7.bean;

public class Pelaaja {

	private int id;
	private String etunimi;
	private String sukunimi;
	
	public Pelaaja() {
		super();
	}

	public Pelaaja(int id, String etunimi, String sukunimi) {
		super();
		this.id = id;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
	}

	public int getId() {
		return id;
	}

	public String getEtunimi() {
		return etunimi;
	}

	public String getSukunimi() {
		return sukunimi;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}

	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}

	@Override
	public String toString() {
		return "Pelaaja [id=" + id + ", etunimi=" + etunimi + ", sukunimi=" + sukunimi + "]";
	}
	
}
