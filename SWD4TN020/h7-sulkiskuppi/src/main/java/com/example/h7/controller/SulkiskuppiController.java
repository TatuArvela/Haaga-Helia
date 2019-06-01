package com.example.h7.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.h7.bean.*;
import com.example.h7.dao.OtteluDAO;
import com.example.h7.dao.PelaajaDAO;

@Controller
@RequestMapping (value="/")
public class SulkiskuppiController {

	@Inject
	private PelaajaDAO pdao;
	
	public PelaajaDAO getPDao() {
		return pdao;
	}

	public void setPDao(PelaajaDAO pdao) {
		this.pdao = pdao;
	}
	
	@Inject
	private OtteluDAO odao;
	
	public OtteluDAO getODao() {
		return odao;
	}

	public void setODao(OtteluDAO odao) {
		this.odao = odao;
	}
	
	// HAE KAIKKI
	@RequestMapping(method=RequestMethod.GET)
	public String get(Model model) {
		List<Pelaaja> pelaajat = pdao.haeKaikki();
		List<Ottelu> ottelut = odao.haeKaikki();
		model.addAttribute("pelaajat", pelaajat);
		model.addAttribute("ottelut", ottelut);
		return "index";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String lisaa(@ModelAttribute("pvm") @DateTimeFormat(pattern="dd.MM.yyyy HH.mm") Date pvm,
			@ModelAttribute("joukkue1_pelaaja1") int joukkue1_pelaaja1,
			@ModelAttribute("joukkue1_pelaaja2") int joukkue1_pelaaja2,
			@ModelAttribute("joukkue2_pelaaja1") int joukkue2_pelaaja1,
			@ModelAttribute("joukkue2_pelaaja2") int joukkue2_pelaaja2,
			@ModelAttribute("joukkue1_pisteet") int joukkue1_pisteet,
			@ModelAttribute("joukkue2_pisteet") int joukkue2_pisteet,
			Model model) {
		
		// Nimillä ei ole tässä kohtaa merkitystä
		Pelaaja j1p1 = new Pelaaja(joukkue1_pelaaja1, "", "");
		Pelaaja j1p2 = new Pelaaja(joukkue1_pelaaja2, "", "");
		Pelaaja j2p1 = new Pelaaja(joukkue2_pelaaja1, "", "");
		Pelaaja j2p2 = new Pelaaja(joukkue2_pelaaja2, "", "");
		
		Ottelu ottelu = new Ottelu (0, pvm, j1p1, j1p2, j2p1, j2p2, joukkue1_pisteet, joukkue2_pisteet);
		System.err.println(ottelu.toString());
		odao.lisaa(ottelu);
		
		model.asMap().clear();
		return "redirect:/";
	}
	
}
