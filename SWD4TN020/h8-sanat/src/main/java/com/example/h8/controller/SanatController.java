package com.example.h8.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.h8.bean.Lisaaja;
import com.example.h8.bean.Sana;
import com.example.h8.dao.LisaajaDAO;
import com.example.h8.dao.SanaDAO;

@Controller
@RequestMapping (value="/")
public class SanatController {
	
	@Inject
	private SanaDAO sanadao;
	
	public SanaDAO getSanaDao() {
		return sanadao;
	}

	public void setSanaDao(SanaDAO sanadao) {
		this.sanadao = sanadao;
	}
	
	@Inject
	private LisaajaDAO lisaajadao;
	
	public LisaajaDAO getLisaajaDao() {
		return lisaajadao;
	}

	public void setLisaajaDao(LisaajaDAO lisaajadao) {
		this.lisaajadao = lisaajadao;
	}
	
	// HAE KAIKKI
	@RequestMapping(method=RequestMethod.GET)
	public String get(Model model) {
		List<Sana> sanat = sanadao.haeKaikki();
		List<Lisaaja> lisaajat = lisaajadao.haeKaikki();
		model.addAttribute("sanat", sanat);
		model.addAttribute("lisaajat", lisaajat);
		return "index";
	}
	
	@RequestMapping(value="/loginfail", method = RequestMethod.GET)
	public String loginerror(Model model) {
		model.addAttribute("loginerror", "true");
		return get(model);
	}
 
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(Model model) {
		model.addAttribute("loggedout", "true");
		return get(model);
	}
	
	// LISÄÄ / POISTA
	@RequestMapping(method=RequestMethod.POST, params = {"lisaa"})
	public String lisaa(@ModelAttribute(value="sana") @Valid Sana sana, BindingResult result, Model model) {
		// Varmistetaan, että käyttäjä on kirjautunut sisään
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth.isAuthenticated()) {
	    	String name = auth.getName();
			// Varmistetaan, että yritetty lisääjä on kyseinen käyttäjä
	    	if (name.equals(sana.getLisaajaTunnus())) {
	    		// Jos virheitä ei ole
	    		if (result.hasErrors()) {
	    			System.err.println("POST: Sana on virheellinen");
	    			model.addAttribute("posterror", "B");
	    		} else {
	    			sanadao.lisaa(sana);
	    		}
		    } else {
		    	System.err.println("POST: Väärä tunnus " + name + " " + sana.getLisaajaTunnus());
		    	model.addAttribute("posterror", "A");
		    }
	    } else {
	    	System.err.println("POST: Kirjautumista ei voida varmentaa");
	    	model.addAttribute("posterror", "A");
	    }
	    return get(model);
	}
	
	@RequestMapping(method=RequestMethod.POST, params = {"poista"})
	public String poista(@ModelAttribute(value="sana") Sana sana) {
		// Varmistetaan, että käyttäjä saa poistaa sanan
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    if (name.equals(sana.getLisaajaTunnus())) {
	    	sanadao.poista(sana);
	    }
		return "redirect:/";
	}

}
