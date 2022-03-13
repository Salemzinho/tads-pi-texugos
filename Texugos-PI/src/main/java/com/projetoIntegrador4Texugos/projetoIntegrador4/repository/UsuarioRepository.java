package com.projetoIntegrador4Texugos.projetoIntegrador4.repository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
	
	public Usuario findByEmail(String email);
	
	
}
