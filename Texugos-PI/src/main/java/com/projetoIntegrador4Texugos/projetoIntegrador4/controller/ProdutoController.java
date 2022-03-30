package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.projetoIntegrador4Texugos.projetoIntegrador4.dto.UsuarioInput;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.ImagemModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Produto;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.TipoUsuario;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Usuario;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.ProdutoService;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.UploadImagemService;
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

	@Autowired
	private UploadImagemService imgService;

	@GetMapping("/form")
	public String form(Produto produto) {
		return "produto/cadastro-produto";
	}
	@GetMapping("/form/#")
	public String produtoReload(Produto produto, Model model) {
		model.addAttribute("produto", produto);
		
		return "produto/cadastro-produto";
	}
	
	@GetMapping("/{id}")
	public String form(@PathVariable int id, Principal principal, Model model) {
		return "produto/produto-editar";
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
	public String novo(Produto produto, Principal principal) {
		System.out.println("NOVO PRODUTO");
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
	
	@PostMapping("/imagem/temp")
	public String addImagemTemp(Produto produto, @RequestParam("file") MultipartFile file, Model model, Principal principal) {
		try {
			Usuario usuarioLogado = usuService.findByEmail(principal.getName());
			if (usuarioLogado.getTipo().compareTo(TipoUsuario.ADMINISTRADOR) == 0) {
				imgService.armazenarTemp(file);
				List<String> imagensPaths = imgService.loadAllTemp().map( path -> path.getFileName().toString()).collect(Collectors.toList());
				List<ImagemModel> imagens = new ArrayList<>();
				if(produto.getImagens() == null) {
					produto.setImagens(new ArrayList<>());
				}
				for (String pathImg : imagensPaths) {
					ImagemModel img = new ImagemModel();
					img.setPathImagem(pathImg);
					img.setPrincipal(false);
					Boolean fileExists = false;
					for(ImagemModel imagem : produto.getImagens()) {
						if(imagem.getPathImagem().equals(img.getPathImagem())) {
							fileExists = true;
							break;
						}
					}
					
					if(!fileExists) {
						produto.getImagens().add(img);
					}
					
					if(produto.getImagens().size() == 1) {
						produto.getImagens().get(0).setPrincipal(true);
					}
				}
				
				model.addAttribute("produto", produto);
				return "produto/cadastro-produto";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "redirect:/produto/form";
		}
		return "redirect:/produto/";
	}

	@GetMapping("")
	public String listarProdutos(Model model) {
		List<Usuario> usuarios = usuService.findAll();
	    model.addAttribute("usuarios", usuarios);
	    
		List<Produto> produtos = prodService.findAll();
		
	    model.addAttribute("produtos", produtos);
		
		return "produto/produto-list";
	}
	
	@PostMapping("/{id}/editarProduto")
	public String editarProduto(@PathVariable int id, Principal principal, Produto produto) {
		Usuario usuarioLogado = usuService.findByEmail(principal.getName());
		if(usuarioLogado.getTipo().compareTo(TipoUsuario.ESTOQUISTA)==0) {
			produto.setIdProd(id);
			prodService.update(id, produto);
			return "redirect:/produto/";
		}
		else {
			return "redirect:/usuario?erro=unauthorized";
		}
	}
	
	@PostMapping("/{id}/deletar")
	public String removerProduto(@PathVariable int id, Principal principal) {
		
		Usuario usuarioLogado = usuService.findByEmail(principal.getName());

		if(usuarioLogado.getTipo().compareTo(TipoUsuario.ADMINISTRADOR)==0) {
				prodService.delete(id);
			return "redirect:/produto/";	
		}
		
		else {
			return "redirect:/usuario?erro=unauthorized";
		}
	}

}
