package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projetoIntegrador4Texugos.projetoIntegrador4.dto.UsuarioInput;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.TipoUsuario;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Usuario;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.UsuarioService;

@Controller
@RequestMapping("admin/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuService;
	
	@GetMapping("form")
	public String formulario(UsuarioInput usuarioInput) {
		return "usuario/cadastro-usuario";
	}

	@PostMapping("novo")
	public String novo(@Valid UsuarioInput usuarioInput, BindingResult result, Principal principal) {
		List<FieldError> errors = result.getFieldErrors();
			
		if (result.hasErrors()) {
			List<ObjectError> erros = result.getAllErrors();
			erros.forEach((erro) -> System.out.println(erro.toString()));
			return "usuario/cadastro-usuario";
		}

		try {
			Usuario usuarioLogado = usuService.findByEmail(principal.getName());
			if (usuarioLogado.getTipo().compareTo(TipoUsuario.ADMINISTRADOR) == 0) {
				String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioInput.getSenha());
				Usuario usuario = usuarioInput.toUsuario();
				usuario.setSenha(senhaCriptografada);

				usuService.salvar(usuario);
				return "redirect:/admin/usuario";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "redirect:/admin/usuario/form";
		}
		return "home";
	}
	
	@PostMapping("/{id}/status")
	public String inativarUsuario(@PathVariable int id, Principal principal) {
		
		Usuario usuarioLogado = usuService.findByEmail(principal.getName());

		if(usuarioLogado.getTipo().compareTo(TipoUsuario.ADMINISTRADOR)==0) {
			Usuario usu = usuService.findOne(id);
			usu.setIsAtivo(!usu.getIsAtivo());
			usuService.update(id, usu);
			return "redirect:/admin/usuario";	
		}
		
		else {
			return "redirect:/admin/usuario?erro=unauthorized";
		}
	}
	
	@GetMapping("/{id}")
	public String formUpdateUsuario(@PathVariable int id, Principal principal, Model model) {
		Usuario usuarioLogado = usuService.findByEmail(principal.getName());

		if(usuarioLogado.getTipo().compareTo(TipoUsuario.ADMINISTRADOR)==0) {
			Usuario usuario = usuService.findOne(id);
			model.addAttribute("usuario", usuario);
			return "usuario/editar-usuario";	
		}
		
		else {
			return "redirect:/admin/usuario?erro=unauthorized";
		}
	}
	
	@PostMapping("/{id}/editar")
	public String editarUsuario(@PathVariable int id, @Valid UsuarioInput usuarioInput, BindingResult result, Principal principal) {
		
		if (result.hasErrors()) {
			if(result.getFieldErrorCount() == 1 && !result.getFieldError().getField().contains("senha")) {
				List<ObjectError> erros = result.getAllErrors();
				erros.forEach((erro) -> System.out.println(erro.toString()));
				return "redirect:/admin/usuario/"+id;
			}
		}
		
		Usuario usuarioLogado = usuService.findByEmail(principal.getName());

		if(usuarioLogado.getTipo().compareTo(TipoUsuario.ADMINISTRADOR)==0) {
			Usuario usuario = usuarioInput.toUsuario();
			usuario.setId(id);
			System.out.println("usuarioANTES"+usuario.getSenha());
			if(usuarioInput.getSenha() != null && !usuarioInput.getSenha().trim().isEmpty()) {
				String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioInput.getSenha());
				usuario.setSenha(senhaCriptografada);
			}
			
			System.out.println("usuarioDEPOIS"+usuario.getSenha());
			System.out.println("usuINP"+usuarioInput.getSenha());
			usuService.update(id, usuario);
			return "redirect:/admin/usuario";
		}
		
		else {
			return "redirect:/admin/usuario?erro=unauthorized";
		}
	}
	
	@GetMapping("")
	public String listarUsuarios(Model model) {
		List<Usuario> usuarios = usuService.findAll();
	    model.addAttribute("usuarios", usuarios);
		
		return "usuario/painel-usuario";
	}
}
