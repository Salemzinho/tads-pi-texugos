package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	@PostMapping("/inserirEndereco")
	public String insereEndereco(ClienteModel currentUser, Principal principal, Model model) {
		ClienteModel clienteLogado = clienteService.findByEmail(principal.getName());
		model.addAttribute("currentUser", clienteLogado);
		if (clienteLogado.getTipo().compareTo(TipoUsuario.CLIENTE) == 0) {
			for (int i = 0; i < currentUser.getEnderecos().size(); i++) {
				if (currentUser.getEnderecos().get(i).getIsPadrao()) {
					enderecoService.updateAddress(currentUser.getId(), currentUser.getEnderecos().get(i));
					break;
				}
			}
			return "perfil";
		}
		return "perfil";
	}

	@GetMapping("")
	public String perfilUsuario(Model model, Principal principal) {
		if(principal != null) {
			ClienteModel cliente = clienteService.findByEmail(principal.getName());
			model.addAttribute("currentUser", cliente);
		}
		List<EnderecoModel> enderecos = enderecoService.findAll();
		model.addAttribute("enderecos", enderecos);
		return "perfil";
	}
	
	@PostMapping("/editarClienteForm")
	public String formUpdateCliente(ClienteModel cliente, Principal principal, Model model) {
		ClienteModel clienteModel = clienteService.findByEmail(principal.getName());
		if (clienteModel.getTipo().compareTo(TipoUsuario.CLIENTE) == 0) {	
			cliente.setId(clienteModel.getId());
			if(cliente.getSenha() != null && !cliente.getSenha().trim().isEmpty()) {
				String senhaCriptografada = new BCryptPasswordEncoder().encode(cliente.getSenha());
				cliente.setSenha(senhaCriptografada);
			}
			model.addAttribute("currentUser", cliente);
			clienteService.update(cliente.getId(), cliente);
			return "perfil";
		} else {
			return "redirect:/admin/usuario?erro=unauthorized";
		}
	}
}
