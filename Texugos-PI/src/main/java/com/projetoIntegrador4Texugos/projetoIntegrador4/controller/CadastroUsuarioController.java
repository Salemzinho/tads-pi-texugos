package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.ClienteModel;


@Controller
public class CadastroUsuarioController {
	
	@GetMapping("/cadastro")
	public String cadastroUsuario(ClienteModel clienteModel) {
		return "cadastro";
    }
	
	@PostMapping("/cadastro/novo")
	public String cadastroUsuarioNovo(ClienteModel clienteModel) {
		return "cadastro";
    }

}
