package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import java.security.Principal;
import java.util.List;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.ClienteModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Compra;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.EnderecoModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.repository.CompraRepository;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.ClienteService;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.CompraService;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.EnderecoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PedidosController {
	
    @Autowired
	private EnderecoService enderecoService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private CompraService compraService;
	
	@Autowired
	private CompraRepository compraRepo;

    private Compra compra = new Compra();

	@GetMapping
	@RequestMapping("/admin/pedidos")
	public String pedidosPainel(String statusPagamento, EnderecoModel endereco, Principal principal, Model model) throws Exception {
        ClienteModel clienteLogado = clienteService.findByEmail(principal.getName());
		model.addAttribute("currentUser", clienteLogado);
		
		List<Compra> compra = compraService.findAll();
	    model.addAttribute("compra", compra);
	    
		List<EnderecoModel> enderecos = enderecoService.findByCodCliente(clienteLogado.getId());
		model.addAttribute("enderecos", enderecos);

		return "pedidos/painel-pedidos.html";
	}

    @PostMapping("/admin/pedidos/{id}")
	public String editarPedido(@PathVariable int id, String statusPagamento, EnderecoModel endereco, Principal principal, Model model) throws Exception {
        ClienteModel clienteLogado = clienteService.findByEmail(principal.getName());
		model.addAttribute("currentUser", clienteLogado);
		
		List<EnderecoModel> enderecos = enderecoService.findByCodCliente(clienteLogado.getId());
		model.addAttribute("enderecos", enderecos);
		

        compra.setStatusPagamento(statusPagamento);
		compraService.update(id, compra);
		
		compra = new Compra();
		
		List<Compra> compra = compraService.findAll();
	    model.addAttribute("compra", compra);

		return "pedidos/painel-pedidos.html";
	}
	
}
