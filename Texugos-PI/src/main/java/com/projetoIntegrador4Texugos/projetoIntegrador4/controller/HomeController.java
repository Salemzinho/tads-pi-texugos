package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
	
	@ExceptionHandler(Exception.class)
	public String onError() {
		return "redirect:/home";
	}
}
