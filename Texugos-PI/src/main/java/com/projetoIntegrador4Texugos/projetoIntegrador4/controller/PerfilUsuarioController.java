package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.ClienteModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.EnderecoModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.TipoUsuario;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.ClienteService;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.EnderecoService;

@Controller
@RequestMapping("/perfil")
public class PerfilUsuarioController {

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/")
	public String perfilUsuario(Model model) {
		List<EnderecoModel> enderecos = enderecoService.findAll();
		model.addAttribute("enderecos", enderecos);
		return "/perfil";
	}

	@GetMapping("/")
	public String perfilUsuario() {
		return "perfil";
	}

	@PostMapping("/inserirEndereco")
	public String insereEndereco(ClienteModel cliente, Principal principal) {
		ClienteModel clienteLogado = clienteService.findByEmail(principal.getName());
		if (clienteLogado.getTipo().compareTo(TipoUsuario.CLIENTE) == 0) {
			for (int i = 0; i < cliente.getEndereco().size(); i++) {
				if (cliente.getEndereco().get(i).getIsPadrao()) {
					enderecoService.updateAddress(cliente.getId(), cliente.getEndereco().get(i));
				}
			}
			return "/perfil";
		}
		return "perfil";
	}

}
