package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.ClienteModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.EnderecoModel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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
