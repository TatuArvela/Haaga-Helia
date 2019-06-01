package com.example.viikkotentti6.dao;

import java.util.List;

import com.example.viikkotentti6.bean.Henkilo;

public interface HenkiloDAO {

	public abstract void talleta(Henkilo henkilo);

	public abstract Henkilo etsi(int id);

	public abstract List<Henkilo> haeKaikki();

}