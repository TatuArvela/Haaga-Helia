package com.example.h3.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.h3.bean.Henkilo;
import com.example.h3.bean.HenkiloImpl;
import com.example.h3.dao.HenkiloDAO;

@Controller
@RequestMapping (value="/")
public class HenkiloController {

	@Inject
	private HenkiloDAO dao;
	
	public HenkiloDAO getDao() {
		return dao;
	}

	public void setDao(HenkiloDAO dao) {
		this.dao = dao;
	}
	
	// HAE KAIKKI
	@RequestMapping(method=RequestMethod.GET)
	public String get(Model model) {
		List<Henkilo> henkilot = dao.haeKaikki();
		model.asMap().clear();
		model.addAttribute("henkilot", henkilot);
		return "korttiohjelmisto";
	}
	
	// LIS�� / MUOKKAA / POISTA
	@RequestMapping(method=RequestMethod.POST, params = { "lisaa" })
	public String lisaa( @ModelAttribute(value="henkilo") HenkiloImpl henkilo) {
		dao.lisaa(henkilo);
		return "redirect:/";
	}
	
	@RequestMapping(method=RequestMethod.POST, params = { "muuta" })
	public String muuta( @ModelAttribute(value="henkilo") HenkiloImpl henkilo) {
		dao.muuta(henkilo);
		return "redirect:/";
	}
	
	@RequestMapping(method=RequestMethod.POST, params = { "poista" })
	public String poista( @ModelAttribute(value="henkilo") HenkiloImpl henkilo) {
		dao.poista(henkilo);
		return "redirect:/";
	}

}
