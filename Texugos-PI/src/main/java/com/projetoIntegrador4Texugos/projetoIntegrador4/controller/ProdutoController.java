package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Produto;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.TipoUsuario;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Usuario;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.ProdutoService;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.UsuarioService;

/*
 * Referencia
 * https://codebun.com/spring-boot-upload-and-download-file-example-using-thymeleaf/
 * https://www.bezkoder.com/spring-boot-file-upload/
 * https://www.baeldung.com/spring-file-upload
 * */

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
  /*
	@GetMapping("")
	public String produtoPainel() {
		return "produto/produto-list";
	}
	*/
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
	public String novo(Produto produto, @RequestParam("file") MultipartFile imagem , Principal principal) {

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

	@GetMapping("")
	public String listarProdutos(Model model) {
		List<Produto> produtos = prodService.findAll();
	    model.addAttribute("produtos", produtos);
		
		return "produto/produto-list";
	}

}
