package com.example.autovuokraus.kohdeluokat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Auto {
	private String rekno;
	private String merkki;
	private String malli;
	private double vrkhinta;
	public Date huoltopvm;
	
	public Auto() {
	}
	public Auto(String rekno, String merkki, String malli, double vrkhinta,
			Date huoltopvm) {
		this.rekno = rekno;
		this.merkki = merkki;
		this.malli = malli;
		this.vrkhinta = vrkhinta;
		this.huoltopvm = huoltopvm;
	}
	
	public String getRekno() {
		return rekno;
	}
	public void setRekno(String rekno) {
		this.rekno = rekno;
	}
	public String getMerkki() {
		return merkki;
	}
	public void setMerkki(String merkki) {
		this.merkki = merkki;
	}
	public String getMalli() {
		return malli;
	}
	public void setMalli(String malli) {
		this.malli = malli;
	}
	public double getVrkhinta() {
		return vrkhinta;
	}
	public void setVrkhinta(double vrkhinta) {
		this.vrkhinta = vrkhinta;
	}
	public Date getHuoltopvmB() {
		Date apu = null;
		if (huoltopvm != null)
			apu = (Date) huoltopvm.clone();
		return apu;
	}
	public String getHuoltopvm() {
		SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
		String pvm = null;
		if (huoltopvm != null)
			pvm = f.format(huoltopvm);
		return pvm;
	}
	public void setHuoltopvm(Date huoltopvm) {
		this.huoltopvm = huoltopvm;
	}

	public String toString() {
		return "Auto [rekno=" + rekno + ", merkki=" + merkki + ", malli=" + malli + ", vrkhinta=" + vrkhinta
				+ ", huoltopvm=" + huoltopvm + "]";
	}
}