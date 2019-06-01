package com.example.h7.bean;

import java.util.Date;

public class Ottelu {

	private int id;
	private Date pvm;
	private Pelaaja joukkue1_pelaaja1;
	private Pelaaja joukkue1_pelaaja2;
	private Pelaaja joukkue2_pelaaja1;
	private Pelaaja joukkue2_pelaaja2;
	private int joukkue1_pisteet;
	private int joukkue2_pisteet;
	
	public Ottelu() {
		super();
	}

	public Ottelu(int id, Date pvm, Pelaaja joukkue1_pelaaja1, Pelaaja joukkue1_pelaaja2, Pelaaja joukkue2_pelaaja1,
			Pelaaja joukkue2_pelaaja2, int joukkue1_pisteet, int joukkue2_pisteet) {
		super();
		this.id = id;
		this.pvm = pvm;
		this.joukkue1_pelaaja1 = joukkue1_pelaaja1;
		this.joukkue1_pelaaja2 = joukkue1_pelaaja2;
		this.joukkue2_pelaaja1 = joukkue2_pelaaja1;
		this.joukkue2_pelaaja2 = joukkue2_pelaaja2;
		this.joukkue1_pisteet = joukkue1_pisteet;
		this.joukkue2_pisteet = joukkue2_pisteet;
	}

	public int getId() {
		return id;
	}

	public Date getPvm() {
		return pvm;
	}

	public Pelaaja getJoukkue1_pelaaja1() {
		return joukkue1_pelaaja1;
	}

	public Pelaaja getJoukkue1_pelaaja2() {
		return joukkue1_pelaaja2;
	}

	public Pelaaja getJoukkue2_pelaaja1() {
		return joukkue2_pelaaja1;
	}

	public Pelaaja getJoukkue2_pelaaja2() {
		return joukkue2_pelaaja2;
	}

	public int getJoukkue1_pisteet() {
		return joukkue1_pisteet;
	}

	public int getJoukkue2_pisteet() {
		return joukkue2_pisteet;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPvm(Date pvm) {
		this.pvm = pvm;
	}

	public void setJoukkue1_pelaaja1(Pelaaja joukkue1_pelaaja1) {
		this.joukkue1_pelaaja1 = joukkue1_pelaaja1;
	}

	public void setJoukkue1_pelaaja2(Pelaaja joukkue1_pelaaja2) {
		this.joukkue1_pelaaja2 = joukkue1_pelaaja2;
	}

	public void setJoukkue2_pelaaja1(Pelaaja joukkue2_pelaaja1) {
		this.joukkue2_pelaaja1 = joukkue2_pelaaja1;
	}

	public void setJoukkue2_pelaaja2(Pelaaja joukkue2_pelaaja2) {
		this.joukkue2_pelaaja2 = joukkue2_pelaaja2;
	}

	public void setJoukkue1_pisteet(int joukkue1_pisteet) {
		this.joukkue1_pisteet = joukkue1_pisteet;
	}

	public void setJoukkue2_pisteet(int joukkue2_pisteet) {
		this.joukkue2_pisteet = joukkue2_pisteet;
	}

	@Override
	public String toString() {
		return "Ottelu [id=" + id + ", pvm=" + pvm + ", joukkue1_pelaaja1=" + joukkue1_pelaaja1 + ", joukkue1_pelaaja2="
				+ joukkue1_pelaaja2 + ", joukkue2_pelaaja1=" + joukkue2_pelaaja1 + ", joukkue2_pelaaja2="
				+ joukkue2_pelaaja2 + ", joukkue1_pisteet=" + joukkue1_pisteet + ", joukkue2_pisteet="
				+ joukkue2_pisteet + "]";
	}
	
}
