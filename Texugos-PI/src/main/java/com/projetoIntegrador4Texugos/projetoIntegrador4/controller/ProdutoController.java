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
	public String produto() {
		return "produto/cadastro-produto";
	}

	@GetMapping("")
	public String produtoPainel() {
		return "produto/produto-list";
	}
	
<<<<<<< HEAD

	@PostMapping("/{id}/status")
=======
	@PostMapping("/{id}/statusProduto")
>>>>>>> 6a87ae68b583f6a64e960477e4a7aca14e9fe2d7
	public String inativarProduto(@PathVariable int id, Principal principal) {
		
		Usuario usuario = usuService.findByEmail(principal.getName());

		if(usuario.getTipo().compareTo(TipoUsuario.ADMINISTRADOR)==0) {
			Produto prod = null;
			try {
				prod = prodService.findOne(id);
				prod.setIsAtivo(!prod.getIsAtivo());
				prodService.update(id, prod);
				
			} catch (Exception e) {
				System.out.println("Erro de leitura");
			}
			return "redirect:/produto/produto-list";	
		}
		
		else {
			return "redirect:/usuario?erro=unauthorized";
		}
	}

	
}

