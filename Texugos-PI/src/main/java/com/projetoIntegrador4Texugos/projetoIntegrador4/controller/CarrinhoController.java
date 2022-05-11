package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.ItensCompraModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Produto;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.TipoUsuario;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Usuario;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.ProdutoService;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.UsuarioService;


@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

	@Autowired
	private ProdutoService prodService;
	
	@Autowired
	private UsuarioService usuService;

	private List<ItensCompraModel> itensCompra = new ArrayList<ItensCompraModel>();
	
	private ModelAndView mv = new ModelAndView("carrinho");
	
	@GetMapping("/")
	public ModelAndView carrinho(Model model) {
		
		mv.addObject("listaItens", itensCompra); 
		    
		return mv;
	}

	@GetMapping("/adicionarCarrinho/{id}") 
	public ModelAndView adicionarCarrinho(@PathVariable int id) throws Exception {

		Produto prod = prodService.findOne(id);
		//Produto produto = prod.get(); 
		ItensCompraModel item = new ItensCompraModel(); 

		item.setProduto(prod); 
		item.setValorUnitario(prod.getPrecoUnitProd()); 
		item.setQuantidade(item.getQuantidade() + 1); 
		item.setValorTotal(item.getQuantidade()*item.getValorUnitario()); 
		itensCompra.add(item);
		mv.addObject("listaItens", itensCompra); 

		return mv;
	}
	
	@GetMapping("/removerCarrinho/{id}") 
	public ModelAndView removerCarrinho(@PathVariable int id) throws Exception {

		Produto prod = prodService.findOne(id);
		ItensCompraModel item = new ItensCompraModel(); 

		item.setProduto(prod); 
		item.setQuantidade(item.getQuantidade() - 1); 
		if(item.getQuantidade()==0) {
			itensCompra.remove(item);
		}
//		mv.addObject("listaItens", itensCompra); 
		return mv;
	}
	
	@GetMapping("/finalizarPedido")
	public String finalizarPedido( Principal principal) {
		Usuario usuarioLogado = usuService.findByEmail(principal.getName());
		if(usuarioLogado.getTipo().compareTo(TipoUsuario.CLIENTE)==0) {
			return "redirect:/pagamento/";
		}
		else {
			return "redirect:/usuario?erro=unauthorized";
		}
	}
	
	
}
