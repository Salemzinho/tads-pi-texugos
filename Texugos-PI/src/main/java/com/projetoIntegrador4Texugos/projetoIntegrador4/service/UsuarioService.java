package com.projetoIntegrador4Texugos.projetoIntegrador4.service;

import java.util.List;
import java.util.Optional;

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
	
	public Usuario findByEmail(String email) {
		return this.usuarioRepo.findByEmail(email);
	}
	
	public List<Usuario> findAll() {
		List<Usuario> list = this.usuarioRepo.findAll();
		return list;//list.stream().collect(Collectors.toCollection(ArrayList::new));
	}
	
	public Usuario findOne(int idUsuario) {
		return this.usuarioRepo.findById(idUsuario)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
	}	
		
	
	public void delete(int id) {
		this.usuarioRepo.deleteById(id);
	}

	// deletar VARAIOS
	public void deleteMany(int[] ids) {
		for (int i : ids) {
			this.usuarioRepo.deleteById(i);
		}		
	}
	
	
	public Usuario update(int idUsuario, Usuario updateUsuario) {
		Optional<Usuario> usuarioOptional = this.usuarioRepo.findById(idUsuario);
		if (usuarioOptional.isPresent()) {
			Usuario usuarioExistente = usuarioOptional.get();
			usuarioExistente.setId(updateUsuario.getId());
			usuarioExistente.setNome(updateUsuario.getNome());
			usuarioExistente.setCPF(updateUsuario.getCPF());
			usuarioExistente.setDataNascimento(updateUsuario.getDataNascimento());
			usuarioExistente.setEmail(updateUsuario.getEmail());
			usuarioExistente.setIsAtivo(updateUsuario.getIsAtivo());
			usuarioExistente.setSenha(updateUsuario.getSenha());
			usuarioExistente.setTelefone(updateUsuario.getTelefone());
			usuarioExistente.setTipo(updateUsuario.getTipo());
					

			return this.usuarioRepo.save(usuarioExistente);
		} else {
			throw new RuntimeException("Usuario(a) nao encontrado(a)");
		}
	}
	
}
