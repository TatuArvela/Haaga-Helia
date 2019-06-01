package com.example.h4.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.h4.dao.LoginDAO;
import com.example.h4.dao.PistetietoDAO;

@Controller
@RequestMapping (value={"/", "login"})
public class LoginController {

	@Inject
	private LoginDAO logindao;
	
	public LoginDAO loginDao() {
		return logindao;
	}

	public void setDao(LoginDAO logindao) {
		this.logindao = logindao;
	}
	
	@Inject
	private PistetietoDAO pistetietodao;
	
	@RequestMapping(method=RequestMethod.GET)
	public String get(Model model) {
		return "login";
	}
	
	// KIRJAUDU SISÄÄN
	@RequestMapping(method=RequestMethod.POST)
	public String post(Model model,	RedirectAttributes redir, @RequestParam("kayttajatunnus") String kayttaja, @RequestParam("salasana") String salasana) {
		int status = logindao.kirjaudu(kayttaja, salasana);
		// OPETTAJA
		if (status == 1) {
			return "redirect:muokkaus";
		}
		// OPISKELIJA
		else if (status == 0) {
			redir.addFlashAttribute("pisteet", pistetietodao.haeTunnuksella(kayttaja));
			return "redirect:pisteet";
		}
		// KIRJAUTUMINEN EPÄONNISTUI
		else {
			model.addAttribute("error", 1);
			return "login";
		}
	}

}
