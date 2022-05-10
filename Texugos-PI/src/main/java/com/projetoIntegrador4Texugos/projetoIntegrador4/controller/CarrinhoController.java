package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.ItemPedidoModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Produto;
import com.projetoIntegrador4Texugos.projetoIntegrador4.repository.ProdutoRepository;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.ProdutoService;

@Controller
public class CarrinhoController {


	private List<ItemPedidoModel> itensCompra = new ArrayList<ItemPedidoModel>();

	@Autowired
	private ProdutoRepository repoProduto;

	@Autowired
	private ProdutoService prodService;
	
	
	@GetMapping("/carrinho")
	public String venda(Model model) {
		ModelAndView mv = new ModelAndView("/carrinho");

		List<Produto> produtos = prodService.findAll();
		model.addAttribute("produtos", produtos);
		mv.addObject("listProdutos", itensCompra);
		    
		return "carrinho";
	}

	@GetMapping("/adicionarCarrinho/{id}")
	public String adicionarCarrinho(@PathVariable int id, Model model) throws Exception {

		Produto produto = prodService.findOne(id);
		ItemPedidoModel item = new ItemPedidoModel();
		item.setProduto(produto);
		item.setQtdeItemPedido(item.getQtdeItemPedido());

		// mv.addObject("listProdutos", itensCompra);

		return "carrinho";
		/*
		Optional<Produto> prod = repoService.findById(idProduto);
		Produto produto = prod.get();
		*/
	}
}
