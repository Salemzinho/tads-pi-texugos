package com.projetoIntegrador4Texugos.projetoIntegrador4.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}
