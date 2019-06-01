package com.example.h5.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.h5.bean.Paino;
import com.example.h5.dao.PainoDAO;

@Controller
@RequestMapping (value="/")
public class PainoController {
	
	@Inject
	private PainoDAO dao;
	
	public PainoDAO getDao() {
		return dao;
	}

	public void setDao(PainoDAO dao) {
		this.dao = dao;
	}
	
	// HAE KAIKKI
	@RequestMapping(method=RequestMethod.GET)
	public String get(Model model) {
		List<Paino> painot = dao.haeKaikki();
		model.addAttribute("painot", painot);
		return "index";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String lisaa(@ModelAttribute Paino paino) {
		dao.lisaa(paino);
		return "redirect:/";
	}
	
}
