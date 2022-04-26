package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

<<<<<<< HEAD

import java.util.List;

=======
import java.security.Principal;

>>>>>>> 08c4d557e6865c930319596ad34aec813c682e6a
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD



import com.projetoIntegrador4Texugos.projetoIntegrador4.model.EnderecoModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.EnderecoService;
=======
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.ClienteModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.TipoUsuario;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.ClienteService;
>>>>>>> 08c4d557e6865c930319596ad34aec813c682e6a

@Controller
@RequestMapping("/perfil")
public class PerfilUsuarioController {
	
	@Autowired

	private EnderecoService enderecoService;
	
	
	@GetMapping("/perfil")
	public String perfilUsuario(Model model) {
		List<EnderecoModel> enderecos = enderecoService.findAll();
		model.addAttribute("enderecos", enderecos);
		
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
