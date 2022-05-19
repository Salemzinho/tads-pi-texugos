package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.ClienteModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Compra;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.ItensCompraModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Produto;
import com.projetoIntegrador4Texugos.projetoIntegrador4.repository.CompraRepository;
import com.projetoIntegrador4Texugos.projetoIntegrador4.repository.ItensCompraRepository;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.ProdutoService;


@Controller
public class CarrinhoController {

	@Autowired
	private ProdutoService prodService;

	@Autowired
	private CompraRepository compraRepo;

	@Autowired
	private ItensCompraRepository itensRepo;

	private List<ItensCompraModel> itensCompra = new ArrayList<ItensCompraModel>();
	private Compra compra = new Compra();
	private ClienteModel cliente;

	private void calcularTotal(){
		compra.setValorTotal(0.);
		for(ItensCompraModel it: itensCompra){
			compra.setValorTotal(compra.getValorTotal() + it.getValorTotal());
		}
	}

	@GetMapping("/entrega")
	public ModelAndView entrega(Model model) {
		ModelAndView mv = new ModelAndView("entrega");

		calcularTotal();

		mv.addObject("compra", compra);
		mv.addObject("listaItens", itensCompra); 
		mv.addObject("cliente", cliente);
		return mv;
	}

	@GetMapping("/pagamento")
	public ModelAndView pagamento(Model model) {
		ModelAndView mv = new ModelAndView("pagamento");

		calcularTotal();

		mv.addObject("compra", compra);
		mv.addObject("listaItens", itensCompra); 
		mv.addObject("cliente", cliente);
		return mv;
	}

	@PostMapping("/finalizar/confirmar")
	public ModelAndView confirmarCompra(String formaPagamento) {
		ModelAndView mv = new ModelAndView("pagamento");
		compra.setClienteModel(cliente);
		compra.setFormaPagamento(formaPagamento);
		compraRepo.saveAndFlush(compra);

		for(ItensCompraModel c:itensCompra){
			c.setCompra(compra);
			itensRepo.saveAndFlush(c);
		}
		itensCompra = new ArrayList<>();
		compra = new Compra();

		return mv;
	}

	@GetMapping("/carrinho")
	public ModelAndView carrinho(Model model) {
		ModelAndView mv = new ModelAndView("carrinho");

		calcularTotal();

		mv.addObject("compra", compra);
		mv.addObject("listaItens", itensCompra); 
		return mv;
	}

	
	@GetMapping("/alterarQuantidade/{id}/{acao}") 
	public String alterarQuantidade (@PathVariable Integer id, @PathVariable Integer acao){
		ModelAndView mv = new ModelAndView("carrinho");

		for (ItensCompraModel it : itensCompra) { 
			if (it.getProduto().getIdProd().equals(id)) { 
				if (acao.equals(1)) {
					compra.setValorTotal(0.);
					it.setQuantidade(it.getQuantidade () + 1);
					it.setValorTotal(it.getValorTotal() +  it.getValorUnitario());
				} 
				else if (acao == 0) {
					if (it.getQuantidade() > 0) {
						compra.setValorTotal(0.);
						it.setQuantidade(it.getQuantidade() - 1);
						it.setValorTotal(it.getValorTotal() - it.getValorUnitario());
					}
				}
				break;
			}
		}

		return "redirect:/carrinho";
	}

	@GetMapping("/frete/{frete}") 
	public String frete (@PathVariable Integer frete){
		ModelAndView mv = new ModelAndView("carrinho");
		compra.setValorFrete(frete.doubleValue());

		return "redirect:/carrinho";
	}

	@GetMapping("/removerProduto/{id}") 
	public String removerProdutoCarrinho (@PathVariable Integer id){
		ModelAndView mv = new ModelAndView("carrinho");

		for (ItensCompraModel it : itensCompra) { 
			if(it.getProduto().getIdProd().equals(id)){
				itensCompra.remove(it);
				break;
			}
		}

		mv.addObject("listaItens", itensCompra); 
		return "redirect:/carrinho";
	}
	

	@GetMapping("/adicionarCarrinho/{id}") 
	public String adicionarCarrinho(@PathVariable int id) throws Exception {
		ModelAndView mv = new ModelAndView("carrinho");

		Produto prod = prodService.findOne(id);
		ItensCompraModel item = new ItensCompraModel(); 

		int controle = 0;
		for(ItensCompraModel it:itensCompra){
			if(it.getProduto().getIdProd().equals(prod.getIdProd())){
				it.setQuantidade(it.getQuantidade() + 1);
				it.setValorTotal(0.);
				it.setValorTotal(it.getValorTotal() + (it.getQuantidade() * it.getValorUnitario()));	
				controle = 1;
				break;
			}
		}
		if(controle == 0){
			item.setProduto(prod); 
			item.setValorUnitario(prod.getPrecoUnitProd()); 
			item.setQuantidade(item.getQuantidade() + 1); 
			item.setValorTotal(item.getValorTotal() + (item.getQuantidade() * item.getValorUnitario())); 
			itensCompra.add(item);
		}

		return "redirect:/carrinho";
	}
}
