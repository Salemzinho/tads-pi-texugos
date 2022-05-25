package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.ClienteModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Produto;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.ClienteService;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.ProdutoService;

@Controller
public class LandingPageController {
	
	@Autowired
	private ProdutoService prodService;
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	@RequestMapping("/")
	public String landingPage(Model model, Principal principal ) {
		if(principal != null) {
			ClienteModel cliente = clienteService.findByEmail(principal.getName());
			if(cliente != null) {
				model.addAttribute("currentUser", cliente);
			}
		}
		List<Produto> produtos = prodService.findAll();
		model.addAttribute("produtos", produtos);
		    
		return "landingpage";
	}
	
	
}
