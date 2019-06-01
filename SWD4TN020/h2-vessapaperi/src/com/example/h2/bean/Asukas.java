package com.example.h2.bean;

public class Asukas {

	private int id;
	private String nimi;
	private int maksetutvuorot;
	private boolean vuoro;
	
	public Asukas(int id, String nimi, int maksetutvuorot, boolean vuoro) {
		super();
		this.id = id;
		this.nimi = nimi;
		this.maksetutvuorot = maksetutvuorot;
		this.vuoro = vuoro;
	}
	
	public Asukas() {
		super();
	}
	
	public Asukas(String nimi) {
		super();
		this.nimi = nimi;
	}
	
	public Asukas(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public int getMaksetutvuorot() {
		return maksetutvuorot;
	}

	public void setMaksetutvuorot(int maksetutvuorot) {
		this.maksetutvuorot = maksetutvuorot;
	}

	public boolean isVuoro() {
		return vuoro;
	}

	public void setVuoro(boolean vuoro) {
		this.vuoro = vuoro;
	}

	@Override
	public String toString() {
		return "Asukas [id=" + id + ", nimi=" + nimi + ", maksetutvuorot=" + maksetutvuorot + ", vuoro=" + vuoro
				+ "]";
	}
	
}
