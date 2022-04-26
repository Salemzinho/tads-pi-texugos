package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
@RequestMapping("admin/produto")
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
	public String produtoReload(@ModelAttribute("produto") Produto produto, Model model, RedirectAttributes redirectAttributes) {
	
		model.addAttribute("produto", produto); 
		return "produto/cadastro-produto";
	}
	
	@GetMapping("/{id}")
	public String formUpdateProduct(@PathVariable int id, Principal principal, Model model) throws Exception {
		Usuario usuarioLogado = usuService.findByEmail(principal.getName());

		if(usuarioLogado.getTipo().compareTo(TipoUsuario.ADMINISTRADOR)==0) {
			Produto produto = prodService.findOne(id);
			model.addAttribute("produto", produto);
			return "produto/editar-produto";	
		}
		
		else {
			return "redirect:/usuario?erro=unauthorized";
		}
	}
	
	@PostMapping("/{id}/editarProduto")
	public String editarProduto(@PathVariable int id, Principal principal, Produto produto) {
		Usuario usuarioLogado = usuService.findByEmail(principal.getName());
		if(usuarioLogado.getTipo().compareTo(TipoUsuario.ESTOQUISTA)==0) {
			produto.setIdProd(id);
			prodService.update(id, produto);
			return "redirect:/admin/produto/";
		}
		else {
			return "redirect:/usuario?erro=unauthorized";
		}
	}
	
	@PostMapping("/{id}/statusProduto")
	public String inativarProduto(@PathVariable int id, Principal principal) throws Exception {
		
		Usuario usuario = usuService.findByEmail(principal.getName());

		if(usuario.getTipo().compareTo(TipoUsuario.ADMINISTRADOR)==0) {
			Produto prod = prodService.findOne(id);
				prod.setIsAtivo(!prod.getIsAtivo());
				prodService.update(id, prod);
				
			return "redirect:/admin/produto";	
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
				Produto prod = prodService.save(produto);
				produto.getImagens().forEach(img -> img.setIdProduto(prod.getIdProd()));
				imgService.armazenar(produto.getImagens());
				
				return "redirect:/admin/produto/";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "redirect:/admin/produto/form";
		}
		return "redirect:/admin/produto/";
	}
	
	@PostMapping("/imagem/temp")
	public String addImagemTemp(Produto produto, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, Principal principal) {
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
					Boolean fileExists = false;
					for(ImagemModel imagem : produto.getImagens()) {
						if(imagem.getPathImagem().equals(img.getPathImagem())) {
							fileExists = true;
							break;
						}
					}
					
					if(!fileExists) {
						System.out.println("img : " + img.getPathImagem());
						produto.getImagens().add(img);
					}
					
					if(produto.getImagens().size() == 1) {
						produto.getImagens().get(0).setPrincipal(true);
						produto.setPathImagem(produto.getImagens().get(0).getPathImagem());
					}
				}
				if (produto.getPathImagem() != null) {
					produto.getImagens().forEach(img -> {
						if (img.getPathImagem().contains(produto.getPathImagem())) {
							img.setPrincipal(true);
						}
					});
				} else {
					produto.getImagens().forEach(img -> {
						if (img.getPrincipal()) {
							produto.setPathImagem(img.getPathImagem());
						}
					});
				}
				
				redirectAttributes.addFlashAttribute("produto", produto);
				return "redirect:/admin/produto/form/#";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "redirect:/admin/produto/form";
		}
		return "redirect:/admin/produto/";
	}

	@GetMapping("")
	public String listarProdutos(Model model, Principal principal) {
		Usuario usuario = usuService.findByEmail(principal.getName());
	    model.addAttribute("principal", usuario);
	    
		List<Produto> produtos = prodService.findAll();
		
	    model.addAttribute("produtos", produtos);
		
		return "produto/painel-produto";
	}
	
/*
	@PostMapping("{nomepesquisa}/")
    public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) throws Exception {
        ModelAndView modelAndView = new ModelAndView("cadastro/cadastroproduto");
        modelAndView.addObject("produto", prodService.findOneByName(nomepesquisa));
        modelAndView.addObject(nomepesquisa, new Produto());
        return modelAndView;
    }
*/


	@PostMapping("/{id}/deletar")
	public String removerProduto(@PathVariable int id, Principal principal) {
		
		Usuario usuarioLogado = usuService.findByEmail(principal.getName());

		if(usuarioLogado.getTipo().compareTo(TipoUsuario.ADMINISTRADOR)==0) {
				prodService.delete(id);
			return "redirect:/admin/produto/";	
		}
		
		else {
			return "redirect:/usuario?erro=unauthorized";
		}
	}

}
