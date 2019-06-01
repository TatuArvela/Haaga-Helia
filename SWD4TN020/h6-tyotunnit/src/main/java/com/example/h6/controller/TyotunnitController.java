package com.example.h6.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.h6.bean.Tyotunnit;
import com.example.h6.dao.TyotunnitDAO;

@Controller
@RequestMapping (value="/")
public class TyotunnitController {

	@Inject
	private TyotunnitDAO dao;
	
	public TyotunnitDAO getDao() {
		return dao;
	}

	public void setDao(TyotunnitDAO dao) {
		this.dao = dao;
	}
	
	// HAE KAIKKI
	@RequestMapping(method=RequestMethod.GET)
	public String get(Model model) {
		List<Tyotunnit> tyotunnit = dao.haeKaikki();
		model.addAttribute("tyotunnit", tyotunnit);
		return "index";
	}
	
	// LIS��	
	@RequestMapping(method=RequestMethod.POST)
	public String post(@ModelAttribute("aloitusaika") @DateTimeFormat(pattern="dd.MM.yyyy HH.mm") Date aloitusaika,
			@ModelAttribute("lopetusaika") @DateTimeFormat(pattern="dd.MM.yyyy HH.mm") Date lopetusaika) {
		Tyotunnit tyotunnit = new Tyotunnit(aloitusaika, lopetusaika);
		dao.lisaa(tyotunnit);
		return "redirect:/";
	}
}
