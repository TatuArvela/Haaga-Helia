package com.example.h4.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.h4.bean.Pistetieto;
import com.example.h4.dao.OpiskelijaDAO;
import com.example.h4.dao.PistetietoDAO;

@Controller
@RequestMapping (value="muokkaus")

public class MuokkausController {

	@Inject
	private PistetietoDAO pistetietodao;
	
	public PistetietoDAO pistetietoDao() {
		return pistetietodao;
	}
	
	@Inject
	private OpiskelijaDAO opiskelijadao;
	
	public OpiskelijaDAO opiskelijaDao() {
		return opiskelijadao;
	}
	
	// HAE KAIKKI
	@RequestMapping(method=RequestMethod.GET)
	public String get(Model model) {
		model.addAttribute("pisteet", pistetietodao.haeKaikki());
		model.addAttribute("opiskelijat", opiskelijadao.haeKaikki());
		return "muokkaus";
	}
	
	// LISÄÄ / MUOKKAA / POISTA
	@RequestMapping(method=RequestMethod.POST, params = {"lisaa"})
	public String lisaa(Model model, @ModelAttribute Pistetieto pistetieto) {
		pistetietodao.lisaa(pistetieto);
		return get(model);
	}
	
	@RequestMapping(method=RequestMethod.POST, params = {"muuta"})
	public String muuta(Model model, @ModelAttribute Pistetieto pistetieto) {
		pistetietodao.muuta(pistetieto);
		return get(model);
	}
	
	@RequestMapping(method=RequestMethod.POST, params = {"poista"})
	public String poista(Model model, @ModelAttribute Pistetieto pistetieto) {
		pistetietodao.poista(pistetieto);
		return get(model);
	}
}