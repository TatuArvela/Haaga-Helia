package com.example.h6.bean;

import java.util.Date;

public class Tyotunnit {

	private int id;
	private Date aloitusaika;
	private Date lopetusaika;
	
	public Tyotunnit() {
		super();
	}
	
	public Tyotunnit(int id, Date aloitusaika, Date lopetusaika) {
		super();
		this.id = id;
		this.aloitusaika = aloitusaika;
		this.lopetusaika = lopetusaika;
	}
	
	public Tyotunnit(Date aloitusaika, Date lopetusaika) {
		super();
		this.aloitusaika = aloitusaika;
		this.lopetusaika = lopetusaika;
	}
	
	public int getId() {
		return id;
	}
	
	public Date getAloitusaika() {
		return aloitusaika;
	}
	
	public Date getLopetusaika() {
		return lopetusaika;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setAloitusaika(Date aloitusaika) {
		this.aloitusaika = aloitusaika;
	}
	
	public void setLopetusaika(Date lopetusaika) {
		this.lopetusaika = lopetusaika;
	}
	
	@Override
	public String toString() {
		return "Tyotunnit [id=" + id + ", aloitusaika=" + aloitusaika + ", lopetusaika=" + lopetusaika + "]";
	}
	
	
}
