package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



import com.projetoIntegrador4Texugos.projetoIntegrador4.model.EnderecoModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.EnderecoService;

@Controller
public class PerfilUsuarioController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	
	@GetMapping("/perfil")
	public String perfilUsuario(Model model) {
		List<EnderecoModel> enderecos = enderecoService.findAll();
		model.addAttribute("enderecos", enderecos);
		
		return "perfil";
    }
	

}
