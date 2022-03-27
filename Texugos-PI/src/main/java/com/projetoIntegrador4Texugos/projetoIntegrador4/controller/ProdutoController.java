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
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService prodService;
	
	@Autowired
	private UsuarioService usuService;

	@GetMapping("/form")
	public String produto(Produto produto) {
		return "produto/cadastro-produto";
	}

	@GetMapping("")
	public String produtoPainel() {
		return "produto/produto-list";
	}
	
	@PostMapping("/{id}/statusProduto")
	public String inativarProduto(@PathVariable int id, Principal principal) throws Exception {
		
		Usuario usuario = usuService.findByEmail(principal.getName());

		if(usuario.getTipo().compareTo(TipoUsuario.ADMINISTRADOR)==0) {
			Produto prod = prodService.findOne(id);
				prod.setIsAtivo(!prod.getIsAtivo());
				prodService.update(id, prod);
				
			return "redirect:/produto/produto-list";	
		}
		
		else {
			return "redirect:/usuario?erro=unauthorized";
		}
	}
	
	@PostMapping("/novoProduto")
	public String novo(Produto produto, Principal principal) {

		try {
			Usuario usuarioLogado = usuService.findByEmail(principal.getName());
			if (usuarioLogado.getTipo().compareTo(TipoUsuario.ADMINISTRADOR) == 0) {
				prodService.save(produto);
				return "redirect:/produto/";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "redirect:/produto/form";
		}
		return "redirect:/produto/";
	}
	
}

