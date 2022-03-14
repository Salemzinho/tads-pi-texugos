package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projetoIntegrador4Texugos.projetoIntegrador4.dto.UsuarioInput;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Usuario;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.UsuarioService;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuService;
	
	@GetMapping
	public String formulario() {
		return "usuario/cadastro";
	}
	
	
	@PostMapping
	public String novo(@Valid UsuarioInput usuarioInput, BindingResult result ) {
		
		if(result.hasErrors()) {
			return "usuario/cadastro";
		}
		
		Usuario usuario = usuarioInput.toUsuario();
		usuService.salvar(usuario);
		
		return "painel";
		
	}
	
}
