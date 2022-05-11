package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.ClienteModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.EnderecoModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.ClienteService;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.EnderecoService;


@Controller
public class CadastroUsuarioController {
	
	@Autowired
	private ClienteService cliService;
	@Autowired
	private EnderecoService endService;
	
	@GetMapping("/cadastro")
	public String formCadastroCliente(Model model) {
		model.addAttribute("clienteModel", new ClienteModel());
		return "cadastro";
    }
	
	@PostMapping("/cadastro/novo")
	public String cadastroClienteNovo(@ModelAttribute("clienteModel") ClienteModel clienteModel) {
		
		String senhaCriptografada = new BCryptPasswordEncoder().encode(clienteModel.getSenha());
		clienteModel.setSenha(senhaCriptografada);
		ClienteModel cli = cliService.save(clienteModel);
		EnderecoModel end = clienteModel.getEndereco();
		if(end.getLogradouro() != null) {
			end.setCliente(cli);
			endService.save(end);	
		}
		
		return "redirect:/home";
    }
	
	@PostMapping("/cadastro/novoEndereco")
	public String adicionarEndereco(ClienteModel clienteModel) {
		System.out.println(clienteModel.getEndereco());
		if(clienteModel.getEnderecos() == null) {
			System.out.println("entrou");
			clienteModel.setEnderecos(new ArrayList<EnderecoModel>());
			clienteModel.getEndereco().setIsPadrao(true);
		}
		clienteModel.getEnderecos().add(clienteModel.getEndereco());
		clienteModel.setEndereco(null);
		System.out.println(clienteModel.getEnderecos().size());
		System.out.println(clienteModel.getEnderecos());
		System.out.println("-------------------------------------");
		System.out.println();
		return "cadastro";
    }
}
