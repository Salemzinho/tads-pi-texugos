package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.ClienteModel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CadastroUsuarioController {
	
	@GetMapping
	@RequestMapping("cadastro")
	public String cadastroUsuario(ClienteModel clientemodel) {
	
		return "cadastro";
    }

}
