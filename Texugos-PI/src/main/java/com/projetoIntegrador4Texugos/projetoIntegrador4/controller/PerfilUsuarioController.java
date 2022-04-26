package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PerfilUsuarioController {
	
	@GetMapping("/perfil")
	public String perfilUsuario() {
		return "perfil";
    }
	

}
