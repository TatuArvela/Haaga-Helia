package com.example.h3.bean;

public class HenkiloImpl implements Henkilo {
	
	private int id;
	private String etunimi;
	private String sukunimi;
	private String puhelin;
	private String email;
	private String osoite;
	private String postinumero;
	private String postitoimipaikka;
	
	public HenkiloImpl() {}

	public HenkiloImpl(int id, String etunimi, String sukunimi, String puhelin, String email, String osoite,
			String postinumero, String postitoimipaikka) {
		super();
		this.id = id;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.puhelin = puhelin;
		this.email = email;
		this.osoite = osoite;
		this.postinumero = postinumero;
		this.postitoimipaikka = postitoimipaikka;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEtunimi() {
		return etunimi;
	}

	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}

	public String getSukunimi() {
		return sukunimi;
	}

	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}

	public String getPuhelin() {
		return puhelin;
	}

	public void setPuhelin(String puhelin) {
		this.puhelin = puhelin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOsoite() {
		return osoite;
	}

	public void setOsoite(String osoite) {
		this.osoite = osoite;
	}

	public String getPostinumero() {
		return postinumero;
	}

	public void setPostinumero(String postinumero) {
		this.postinumero = postinumero;
	}

	public String getPostitoimipaikka() {
		return postitoimipaikka;
	}

	public void setPostitoimipaikka(String postitoimipaikka) {
		this.postitoimipaikka = postitoimipaikka;
	}

	@Override
	public String toString() {
		return "Henkilo [id=" + id + ", etunimi=" + etunimi + ", sukunimi=" + sukunimi + ", puhelin=" + puhelin
				+ ", email=" + email + ", osoite=" + osoite + ", postinumero=" + postinumero + ", postitoimipaikka="
				+ postitoimipaikka + "]";
	}
	
}
