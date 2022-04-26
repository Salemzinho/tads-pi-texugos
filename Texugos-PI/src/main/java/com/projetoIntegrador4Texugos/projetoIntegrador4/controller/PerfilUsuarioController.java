package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.ClienteModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.TipoUsuario;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.ClienteService;

@Controller
@RequestMapping("/perfil")
public class PerfilUsuarioController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/")
	public String perfilUsuario() {
		return "perfil";
    }
	
	@PostMapping("/inserirEndereco")
	public String insereEndereco (ClienteModel cliente, Principal principal) {
		ClienteModel clienteLogado = clienteService.findByEmail(principal.getName());
		if (clienteLogado.getTipo().compareTo(TipoUsuario.CLIENTE) == 0) {
//			Produto prod = prodService.save(produto);
//			imgService.armazenar(produto.getImagens());
			
			//Terminar a logica de inserção do endereço
			
			return "redirect:/admin/produto/";
		}
		return "perfil";
	}
	

}
