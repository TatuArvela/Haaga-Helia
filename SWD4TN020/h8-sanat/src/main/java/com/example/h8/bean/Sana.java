package com.example.h8.bean;

import javax.validation.constraints.Size;

import com.example.h8.bean.validation.Kieli;

public class Sana {

	private int id;
	
	@Size(min = 1, max = 100, message = "Sana voi olla enint��n 100 merkki� pitk�")
	private String sana;
	
	private String seloste;
	
	@Kieli(message = "Kielen tulee olla pienill� kirjaimilla, ja yksi seuraavista: suomi, ruotsi, englanti, saksa, viro, japani, kiina")
	private String kieli;
	
	private String lisaajaTunnus;
	private String lisaajaEtunimi;
	private String lisaajaSukunimi;
	
	public Sana() {
		super();
	}

	public Sana(int id, String sana, String seloste, String kieli, String lisaajaTunnus, String lisaajaEtunimi,
			String lisaajaSukunimi) {
		super();
		this.id = id;
		this.sana = sana;
		this.seloste = seloste;
		this.kieli = kieli;
		this.lisaajaTunnus = lisaajaTunnus;
		this.lisaajaEtunimi = lisaajaEtunimi;
		this.lisaajaSukunimi = lisaajaSukunimi;
	}

	public int getId() {
		return id;
	}

	public String getSana() {
		return sana;
	}

	public String getSeloste() {
		return seloste;
	}

	public String getKieli() {
		return kieli;
	}

	public String getLisaajaTunnus() {
		return lisaajaTunnus;
	}

	public String getLisaajaEtunimi() {
		return lisaajaEtunimi;
	}

	public String getLisaajaSukunimi() {
		return lisaajaSukunimi;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSana(String sana) {
		this.sana = sana;
	}

	public void setSeloste(String seloste) {
		this.seloste = seloste;
	}

	public void setKieli(String kieli) {
		this.kieli = kieli;
	}

	public void setLisaajaTunnus(String lisaajaTunnus) {
		this.lisaajaTunnus = lisaajaTunnus;
	}

	public void setLisaajaEtunimi(String lisaajaEtunimi) {
		this.lisaajaEtunimi = lisaajaEtunimi;
	}

	public void setLisaajaSukunimi(String lisaajaSukunimi) {
		this.lisaajaSukunimi = lisaajaSukunimi;
	}

	@Override
	public String toString() {
		return "Sana [id=" + id + ", sana=" + sana + ", seloste=" + seloste + ", kieli=" + kieli + ", lisaajaTunnus="
				+ lisaajaTunnus + ", lisaajaEtunimi=" + lisaajaEtunimi + ", lisaajaSukunimi=" + lisaajaSukunimi + "]";
	}
	
}
