package com.example.h3.dao;

import java.util.List;

import com.example.h3.bean.*;

public interface HenkiloDAO {

	public abstract void lisaa(Henkilo henkilo);
	
	public abstract void muuta(Henkilo henkilo);
	
	public abstract void poista(Henkilo henkilo);
	
	public abstract List<Henkilo> haeKaikki();
	
}
