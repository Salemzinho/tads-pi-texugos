package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@GetMapping
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping
	@RequestMapping("cadastro")
	public String cadastro() {
		return "usuario/cadastro";
	}
	
	@GetMapping
	@RequestMapping("/")
	public String painel() {
		return "redirect:/painel"; //TIRAR DAQUI
	}
	
	
}
