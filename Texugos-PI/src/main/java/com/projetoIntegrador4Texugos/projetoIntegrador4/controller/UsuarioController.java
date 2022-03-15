package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projetoIntegrador4Texugos.projetoIntegrador4.dto.UsuarioInput;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Usuario;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.UsuarioService;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuService;
	
	@GetMapping("form")
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
		
		return "redirect:/usuario";
		
	}
	
	@GetMapping("")
	public String listar() {
		//implementar
		
		return "painel";
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
