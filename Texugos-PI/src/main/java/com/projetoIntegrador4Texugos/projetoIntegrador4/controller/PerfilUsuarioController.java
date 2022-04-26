package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.ClienteModel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class PerfilUsuarioController {
	
	@GetMapping("/perfil")
	public String perfilUsuario() {
		return "perfil";
    }
	

}
