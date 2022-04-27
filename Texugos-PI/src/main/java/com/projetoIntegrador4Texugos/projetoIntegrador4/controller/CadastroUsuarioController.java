package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.ClienteModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.EnderecoModel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CadastroUsuarioController {
	
	@GetMapping("/cadastro")
	public String cadastroUsuario(ClienteModel clienteModel, Model model) {
		model.addAttribute("endereco", new EnderecoModel());
		return "cadastro";
    }
	
	@PostMapping("/cadastro/novo")
	public String cadastroUsuarioNovo(ClienteModel clienteModel) {
		
		System.out.println(clienteModel);
		
		return "cadastro";
    }
	
	@PostMapping("/cadastro/novoEndereco")
	public String cadastroUsuarioNovo(ClienteModel clienteModel, EnderecoModel endereco) {
		
		
		return "cadastro";
    }
}
