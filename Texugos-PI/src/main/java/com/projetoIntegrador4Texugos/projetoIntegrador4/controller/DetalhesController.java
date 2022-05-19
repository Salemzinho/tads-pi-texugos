package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.ClienteModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Produto;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.ClienteService;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.EnderecoService;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.ProdutoService;

@Controller
@RequestMapping("/detalhes")
public class DetalhesController {

	@Autowired
	private ProdutoService prodService;

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/{id}")
	public String viewProduct(@PathVariable int id, Model model, Principal principal) throws Exception {
		Produto produto = prodService.findOne(id);

	
		ClienteModel clienteLogado = clienteService.findByEmail(principal.getName());
		model.addAttribute("currentUser", clienteLogado);
		

		model.addAttribute("produto", produto);
		return "detalhes";	
	}

    @GetMapping("/detalhes")
    public String detalhesPorId(Model model, Principal principal) {
		if(principal != null) {
			ClienteModel clienteLogado = clienteService.findByEmail(principal.getName());
			model.addAttribute("currentUser", clienteLogado);
		}

        return "detalhes";
    }
    
}
