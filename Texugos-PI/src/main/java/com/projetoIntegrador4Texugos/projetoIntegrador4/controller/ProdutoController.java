package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Produto;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.TipoUsuario;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Usuario;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.ProdutoService;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.UsuarioService;

@Controller
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private UsuarioService usuService;

	@GetMapping
	@RequestMapping("/produto/form")
	public String produto() {
		return "produto/cadastro-produto";
	}

	@GetMapping
	@RequestMapping("/produto")
	public String produtoPainel() {
		return "produto/produto-list";
	}
	
	@PostMapping("/{id}/status")
	public String inativarProduto(@PathVariable int id, Principal principal) {
		
		Usuario usuario = usuService.findByEmail(principal.getName());

		if(usuario.getTipo().compareTo(TipoUsuario.ADMINISTRADOR)==0) {
			Produto prod;
			try {
				prod = produtoService.findOne(id);
				prod.setIsAtivo(!prod.getIsAtivo());
				produtoService.update(id, prod);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:/usuario";	
		}
		
		else {
			return "redirect:/usuario?erro=unauthorized";
		}
	}
	
}

