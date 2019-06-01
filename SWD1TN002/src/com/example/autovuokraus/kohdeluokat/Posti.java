package com.example.autovuokraus.kohdeluokat;

public class Posti {
	private String postinro;
	private String postitmp;
	
	public Posti()
	{
		postinro=postitmp = null;
	}
	public Posti (String postinro, String postitmp)
	{
		if (postinro != null && postinro.matches("\\d{5}"))
		{
			this.postinro = postinro;
		}
		
		if (postitmp != null && postitmp.trim().length()>0)
		{
			postitmp = postitmp.trim();
			postitmp = postitmp.replaceAll("\\s+"," ");
			postitmp = postitmp.toUpperCase();
			this.postitmp = postitmp;
		}
	}
	
	public String getPostinro() {
		return postinro;
	}
	public void setPostinro(String postinro) {
		if (postinro != null && postinro.matches("\\d{5}"))
		{
			this.postinro = postinro;
		}
	}
	public String getPostitmp() {
		return postitmp;
	}
	public void setPostitmp(String postitmp) {
		if (postitmp != null && postitmp.trim().length()>0)
		{
			postitmp = postitmp.trim();
			postitmp = postitmp.replaceAll("\\s+"," ");
			postitmp = postitmp.toUpperCase();
			this.postitmp = postitmp;
		}
	}

	public String toString() {
		return "Posti [postinro=" + postinro + ", postitmp=" + postitmp + "]";
	}
}
