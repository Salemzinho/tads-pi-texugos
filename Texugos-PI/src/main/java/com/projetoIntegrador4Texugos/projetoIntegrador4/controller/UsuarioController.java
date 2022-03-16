package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projetoIntegrador4Texugos.projetoIntegrador4.dto.UsuarioInput;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.TipoUsuario;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Usuario;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.UsuarioService;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuService;
	
	@GetMapping("form")
	public String formulario(Usuario usuario) {
		return "usuario/cadastro";
	}
	
	
	@PostMapping("novo")
	public String novo(@Valid Usuario usuario, BindingResult result, Principal principal) {

		if (result.hasErrors()) {
			return "usuario/cadastro?erro=1";
		}

		try {
			Usuario usuarioLogado = usuService.findByEmail(principal.getName());
			if (usuarioLogado.getTipo().compareTo(TipoUsuario.ADMINISTRADOR) == 0) {
				String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
				usuario.setSenha(senhaCriptografada);

				usuService.salvar(usuario);
				return "redirect:/usuario";
			}
		} catch (Exception ex) {

			return "usuario/cadastro?erro=2";
		}
		return "home";
	}
	
	@PutMapping("/{id}")
	public String inativarUsuario(@PathVariable("id") int id, Principal principal) {
		
		Usuario usuarioLogado = usuService.findByEmail(principal.getName());

		if(usuarioLogado.getTipo().compareTo(TipoUsuario.ADMINISTRADOR)==0) {
			Usuario usu = usuService.findOne(id);
			usu.setIsAtivo(!usu.getIsAtivo());
			usuService.update(id, usu);
			return "redirect:/usuario";
		}
		
		else {
			return "redirect:/usuario?erro=1";
		}
		
	}
	
	@GetMapping("")
	public String listarUsuarios(Model model) {
		List<Usuario> usuarios = usuService.findAll();
	    model.addAttribute("usuarios", usuarios);
		
		return "painel.html";
	}
	
	@ExceptionHandler(Exception.class)
	public String onError() {
		return "redirect:/usuario";
	}
	
	/* O NAGA É FODA ! 
	 * 
	@GetMapping("")
	public ResponseEntity<List<Usuario>> findAll() {
		return ResponseEntity.ok().body( this.usuService.findAll());
	}
	
	@GetMapping("/{idUsuario}")
	public ResponseEntity<Usuario> findOne(@PathVariable int idUsuario) {
		return ResponseEntity.ok().body( this.usuService.findOne(idUsuario));
	}

	@PostMapping
	public ResponseEntity<Usuario> save(@Valid @RequestBody Usuario novoUsuario) {
		return ResponseEntity.ok().body(this.usuService.salvar(novoUsuario));
	}

	@PatchMapping("/{idUsuario}")
	public ResponseEntity<Usuario> update(@Valid @PathVariable int idUsuario,
			@RequestBody Usuario updateUsuario) {
		return ResponseEntity.ok().body(this.usuService.update(idUsuario, updateUsuario));
	}

	@DeleteMapping("/{idUsuario}")
	public void delete(@PathVariable int idUsuario) {
		this.usuService.delete(idUsuario);
	}

	@DeleteMapping("many/{idUsuario}")
	public void deleteMany(@PathVariable int[] id) {

		this.usuService.deleteMany(id);
	}
	*/
}
