package com.projetoIntegrador4Texugos.projetoIntegrador4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoIntegrador4Texugos.projetoIntegrador4.exception.RegraNegocioException;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Usuario;
import com.projetoIntegrador4Texugos.projetoIntegrador4.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	public Usuario salvar(Usuario usuario) {
	
		Usuario usuarioExistente = usuarioRepo.findByEmail(usuario.getEmail());
		
		if(usuarioExistente != null && usuarioExistente.equals(usuario)) {
			throw new RegraNegocioException("Já existe um usuario cadastrado com este e-mail.");
		}
			
		return usuarioRepo.save(usuario);
	}
	
}
