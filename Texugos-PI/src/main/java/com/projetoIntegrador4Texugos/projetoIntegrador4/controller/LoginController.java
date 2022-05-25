package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import java.security.Principal;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.ClienteModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	@RequestMapping("/cliente/login")
	public String login(Principal principal, Model model) {
		if(principal != null) {
			ClienteModel cliente = clienteService.findByEmail(principal.getName());
			model.addAttribute("currentUser", cliente);
		}

		return "login";
	}
	
	@GetMapping
	@RequestMapping("/admin/login")
	public String loginBackOffice() {
		return "admin/login";
	}
}
