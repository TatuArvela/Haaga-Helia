package com.example.h4.bean;

public class Pistetieto {

	private int id;
	private String opiskelijatunnus;
	private String opiskelijanEtunimi;
	private String opiskelijanSukunimi;
	private String kurssi;
	private String tehtava;
	private int pisteet;
	
	public Pistetieto() {
		super();
	}

	public Pistetieto(int id, String opiskelijatunnus, String opiskelijanEtunimi, String opiskelijanSukunimi,
			String kurssi, String tehtava, int pisteet) {
		super();
		this.id = id;
		this.opiskelijatunnus = opiskelijatunnus;
		this.opiskelijanEtunimi = opiskelijanEtunimi;
		this.opiskelijanSukunimi = opiskelijanSukunimi;
		this.kurssi = kurssi;
		this.tehtava = tehtava;
		this.pisteet = pisteet;
	}

	public int getId() {
		return id;
	}

	public String getOpiskelijatunnus() {
		return opiskelijatunnus;
	}

	public String getOpiskelijanEtunimi() {
		return opiskelijanEtunimi;
	}

	public String getOpiskelijanSukunimi() {
		return opiskelijanSukunimi;
	}

	public String getKurssi() {
		return kurssi;
	}

	public String getTehtava() {
		return tehtava;
	}

	public int getPisteet() {
		return pisteet;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setOpiskelijatunnus(String opiskelijatunnus) {
		this.opiskelijatunnus = opiskelijatunnus;
	}

	public void setOpiskelijanEtunimi(String opiskelijanEtunimi) {
		this.opiskelijanEtunimi = opiskelijanEtunimi;
	}

	public void setOpiskelijanSukunimi(String opiskelijanSukunimi) {
		this.opiskelijanSukunimi = opiskelijanSukunimi;
	}

	public void setKurssi(String kurssi) {
		this.kurssi = kurssi;
	}

	public void setTehtava(String tehtava) {
		this.tehtava = tehtava;
	}

	public void setPisteet(int pisteet) {
		this.pisteet = pisteet;
	}

	@Override
	public String toString() {
		return "Pistetieto [id=" + id + ", opiskelijatunnus=" + opiskelijatunnus + ", opiskelijanEtunimi="
				+ opiskelijanEtunimi + ", opiskelijanSukunimi=" + opiskelijanSukunimi + ", kurssi=" + kurssi
				+ ", tehtava=" + tehtava + ", pisteet=" + pisteet + "]";
	}
	
}
