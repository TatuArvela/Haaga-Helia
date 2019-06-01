package com.example.viikkotentti3.kello;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class GmtIsoKello implements Kello {

	private KelloLahde lahde;
	
	private SimpleDateFormat aikaMuotoilija = new SimpleDateFormat("HH:mm:ss");
	private SimpleDateFormat pvmMuotoilija = new SimpleDateFormat("yyyy-MM-dd");
	private TimeZone greenwichMeanTime = TimeZone.getTimeZone("GMT");
	
	public GmtIsoKello(KelloLahde lahde) {
		this.lahde = lahde;
		this.aikaMuotoilija.setTimeZone(greenwichMeanTime);
		this.pvmMuotoilija.setTimeZone(greenwichMeanTime);
	}
	
	public String getAika() {
		return aikaMuotoilija.format(lahde.haeAjanhetki());
	}
	
	public String getPvm() {
		return pvmMuotoilija.format(lahde.haeAjanhetki());
	}

}
