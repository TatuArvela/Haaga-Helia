package com.example.autovuokraus.kohdeluokat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Vuokraus {
	private int numero;
	private Date vuokrauspvm;
	private Date paattymispvm;
	private double kokonaishinta;
	private Date maksupvm;
	private Asiakas vuokraaja;
	private Auto vuokrakohde;
	
	public Vuokraus() {
	}
	public Vuokraus(int numero, Date vuokrauspvm, Date paattymispvm,
			double kokonaishinta, Date maksupvm, Asiakas vuokraaja,
			Auto vuokrakohde) {
		this.numero = numero;
		this.vuokrauspvm = vuokrauspvm;
		this.paattymispvm = paattymispvm;
		this.kokonaishinta = kokonaishinta;
		this.maksupvm = maksupvm;
		this.vuokraaja = vuokraaja;
		this.vuokrakohde = vuokrakohde;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Date getVuokrauspvmB() {
		if (vuokrauspvm != null)
			return (Date)vuokrauspvm.clone();
		else
			return null;
	}
	public String getVuokrauspvm() {
		SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
		return f.format(vuokrauspvm);
	}
	public void setVuokrauspvm(Date vuokrauspvm) {
		this.vuokrauspvm = vuokrauspvm;
	}
	public Date getPaattymispvmB() {
		if (paattymispvm != null)
			return (Date)paattymispvm.clone();
		else
			return null;
	}
	public String getPaattymispvm() {
		SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
		return f.format(paattymispvm);
	}
	public void setPaattymispvm(Date paattymispvm) {
		this.paattymispvm = paattymispvm;
	}
	public double getKokonaishinta() {
		return kokonaishinta;
	}
	public void setKokonaishinta(double kokonaishinta) {
		this.kokonaishinta = kokonaishinta;
	}
	public Date getMaksupvmB() {
		if (maksupvm != null)
			return (Date)maksupvm.clone();
		else
			return null;
	}
	public String getMaksupvm() {
		SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
		if (maksupvm != null)
			return f.format(maksupvm);
		else
			return null;
	}
	public void setMaksupvm(Date maksupvm) {
		this.maksupvm = maksupvm;
	}
	public Asiakas getVuokraaja() {
		return vuokraaja;
	}
	public void setVuokraaja(Asiakas vuokraaja) {
		this.vuokraaja = vuokraaja;
	}
	public Auto getVuokrakohde() {
		return vuokrakohde;
	}
	public void setVuokrakohde(Auto vuokrakohde) {
		this.vuokrakohde = vuokrakohde;
	}

	public String toString() {
		return "Vuokraus [numero=" + numero + ", vuokrauspv=" + vuokrauspvm
				+ ", paattymispv=" + paattymispvm + ", kokonaishinta="
				+ kokonaishinta + ", maksupvm=" + maksupvm + ", vuokraaja="
				+ vuokraaja + ", vuokrakohde=" + vuokrakohde + "]";
	}
	
}
