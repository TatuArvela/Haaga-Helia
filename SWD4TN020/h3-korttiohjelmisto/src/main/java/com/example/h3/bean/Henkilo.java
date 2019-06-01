package com.example.h3.bean;

public interface Henkilo {

	public abstract int getId();

	public abstract void setId(int id);

	public abstract String getEtunimi();

	public abstract void setEtunimi(String etunimi);
	
	public abstract String getSukunimi();
	
	public abstract void setSukunimi(String sukunimi);

	public abstract String getPuhelin();

	public abstract void setPuhelin(String puhelin);
	
	public abstract String getEmail();

	public abstract void setEmail(String email);
	
	public abstract String getOsoite();

	public abstract void setOsoite(String osoite);

	public abstract String getPostinumero();

	public abstract void setPostinumero(String postinumero);

	public abstract String getPostitoimipaikka();

	public abstract void setPostitoimipaikka(String postitoimipaikka);
	
}
