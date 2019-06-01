package com.example.viikkotentti4.bean;

public class Albumi {
	
	private int id;
	private String nimi;
	private String artisti;
	private int julkaisuvuosi;
	private String genre;
	private String formaatti;
	
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
	public String getArtisti() {
		return artisti;
	}
	public void setArtisti(String artisti) {
		this.artisti = artisti;
	}
	public int getJulkaisuvuosi() {
		return julkaisuvuosi;
	}
	public void setJulkaisuvuosi(int julkaisuvuosi) {
		this.julkaisuvuosi = julkaisuvuosi;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getFormaatti() {
		return formaatti;
	}
	public void setFormaatti(String formaatti) {
		this.formaatti = formaatti;
	}
	@Override
	public String toString() {
		return "Albumi [id=" + id + ", nimi=" + nimi + ", artisti=" + artisti + ", julkaisuvuosi=" + julkaisuvuosi
				+ ", genre=" + genre + ", formaatti=" + formaatti + "]";
	}
	
}
