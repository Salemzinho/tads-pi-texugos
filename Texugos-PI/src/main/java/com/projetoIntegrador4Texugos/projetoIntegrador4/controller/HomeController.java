package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@GetMapping
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping
	@RequestMapping("/")
	public String blank() {
		return "redirect:/home"; 
	}
}
