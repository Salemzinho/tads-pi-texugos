package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
		ClienteModel cliente = new ClienteModel();
		EnderecoModel endereco = new EnderecoModel();
		endereco.setCliente(cliente);
		cliente.setEndereco(endereco);
		model.addAttribute("clienteModel", cliente );
		return "cadastro";
    }
	
	@PostMapping("/cadastro/novo")
	public String cadastroClienteNovo(@Valid @ModelAttribute("clienteModel") ClienteModel clienteModel, BindingResult result) {
		try {
			
			if (result.hasErrors()) {
				List<ObjectError> erros = result.getAllErrors();
				erros.forEach((erro) -> System.out.println(erro.toString()));
				return "cadastro";
			}
			
			String senhaCriptografada = new BCryptPasswordEncoder().encode(clienteModel.getSenha());
			clienteModel.setSenha(senhaCriptografada);
			ClienteModel cli = cliService.save(clienteModel);
			EnderecoModel end = clienteModel.getEndereco();
			String aa = end != null ? "\nnao ta nulo\n" : "\nta nulo sim\n";
			System.out.println(aa);
			if (end.getLogradouro() != null) {
				end.setCliente(cli);
				endService.save(end);
			}

			return "redirect:/login";
		} catch (Exception e) {
			return "redirect:/home";
		}
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
