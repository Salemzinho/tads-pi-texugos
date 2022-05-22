package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.ClienteModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Compra;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.EnderecoModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.TipoUsuario;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.ClienteService;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.CompraService;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.EnderecoService;

@Controller
@RequestMapping("/perfil")
public class PerfilUsuarioController {

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private CompraService compraService;

	@GetMapping("/pedidos/{id}")
	public String maisDetalhes(@PathVariable Integer id, EnderecoModel endereco, Principal principal, Model model) throws Exception {
		//List<Compra> compra = compraService.findAll();

		Compra compra = compraService.findOne(id);
	    model.addAttribute("compra", compra);
		ClienteModel clienteLogado = clienteService.findByEmail(principal.getName());
		model.addAttribute("currentUser", clienteLogado);
		List<EnderecoModel> enderecos = enderecoService.findByCodCliente(clienteLogado.getId());
		model.addAttribute("enderecos", enderecos);

		return "detalhes-pedido";
	}

	@GetMapping("/pedidos")
	public String listaPedido(EnderecoModel endereco, Principal principal, Model model) {
		List<Compra> compra = compraService.findAll();
	    model.addAttribute("compra", compra);
		ClienteModel clienteLogado = clienteService.findByEmail(principal.getName());
		model.addAttribute("currentUser", clienteLogado);
		List<EnderecoModel> enderecos = enderecoService.findByCodCliente(clienteLogado.getId());
		model.addAttribute("enderecos", enderecos);

		return "pedidos";
	}
	
	@PostMapping("/inserirEndereco")
	public String insereEndereco(ClienteModel currentUser, Principal principal, Model model) {
		ClienteModel clienteLogado = clienteService.findByEmail(principal.getName());
		model.addAttribute("currentUser", clienteLogado);
		if (clienteLogado.getTipo().compareTo(TipoUsuario.CLIENTE) == 0) {
			for (int i = 0; i < currentUser.getEnderecos().size(); i++) {
				if (currentUser.getEnderecos().get(i).getIsPadrao()) {
					enderecoService.updateAddress(currentUser.getId(), currentUser.getEnderecos().get(i));
					break;
				}
			}
			return "perfil";
		}
		return "perfil";
	}
	
	@PostMapping("/endereco/{id}")
	public String deletaEndereco(@PathVariable int id, Model model, Principal principal) {
		
		ClienteModel clienteLogado = clienteService.findByEmail(principal.getName());
		model.addAttribute("currentUser", clienteLogado);
		List<EnderecoModel> enderecos = enderecoService.findByCodCliente(clienteLogado.getId());
		model.addAttribute("enderecos", enderecos);
		
		enderecoService.delete(id);
		
		return "perfil";
	}
	
	@GetMapping("/endereco")
	public String adicionarEnderecoForm(EnderecoModel endereco, Principal principal, Model model) {
		if(principal != null) {
			ClienteModel cliente = clienteService.findByEmail(principal.getName());
			model.addAttribute("currentUser", cliente);
			endereco.setCliente(cliente);
			model.addAttribute("endereco",endereco);
		}
		return "add-endereco";
	}
	
	@PostMapping("/endereco/add")
	public String adicionarEndereco(EnderecoModel endereco, Principal principal, Model model) {
		enderecoService.save(endereco);
		if(endereco.getIsPadrao() != null && endereco.getIsPadrao()) {
			enderecoService.mudarEnderecoPadrao(endereco.getId(), endereco.getCliente().getId());
		}
		ClienteModel cliente = clienteService.findByEmail(principal.getName());
		model.addAttribute("currentUser", cliente);
		return "redirect:/perfil";
	}
	
	@GetMapping("/endereco/{id}")
	public String editarEnderecoForm(@PathVariable int id, Model model, Principal principal) {
		if(principal != null) {
			ClienteModel cliente = clienteService.findByEmail(principal.getName());
			model.addAttribute("currentUser", cliente);
			EnderecoModel endereco = enderecoService.findById(id);
			model.addAttribute("endereco", endereco);
		}
		return "editar-endereco";
	}

	@GetMapping("")
	public String perfilUsuario(Model model, Principal principal) {
		if(principal != null) {
			ClienteModel cliente = clienteService.findByEmail(principal.getName());
			model.addAttribute("currentUser", cliente);
			List<EnderecoModel> enderecos = enderecoService.findByCodCliente(cliente.getId());
			model.addAttribute("enderecos", enderecos);
		}
		return "perfil";
	}
	
	@PostMapping("/endereco/editar")
	public String editarEndereco(EnderecoModel endereco) {
		EnderecoModel end = enderecoService.findById(endereco.getId());
		end.setIsPadrao(endereco.getIsPadrao());
		enderecoService.updateAddress(end.getId(), end);
		if(endereco.getIsPadrao()) {
			enderecoService.mudarEnderecoPadrao(endereco.getId(), endereco.getCliente().getId());
		}
		return "redirect:/perfil";
	}
	
	
	@PostMapping("/editarClienteForm")
	public String formUpdateCliente(ClienteModel cliente, Principal principal, Model model) {
		ClienteModel clienteModel = clienteService.findByEmail(principal.getName());
		if (clienteModel.getTipo().compareTo(TipoUsuario.CLIENTE) == 0) {	
			cliente.setId(clienteModel.getId());
			if(cliente.getSenha() != null && !cliente.getSenha().trim().isEmpty()) {
				String senhaCriptografada = new BCryptPasswordEncoder().encode(cliente.getSenha());
				cliente.setSenha(senhaCriptografada);
			}
			model.addAttribute("currentUser", cliente);
			clienteService.update(cliente.getId(), cliente);
			return "perfil";
		} else {
			return "redirect:/admin/usuario?erro=unauthorized";
		}
	}
}