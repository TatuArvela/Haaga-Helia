package com.example.h4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping (value="pisteet")
public class PisteetController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String get(Model model) {
		return "pisteet";
	}
}
