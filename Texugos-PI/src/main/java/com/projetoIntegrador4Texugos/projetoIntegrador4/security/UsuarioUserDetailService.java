package com.projetoIntegrador4Texugos.projetoIntegrador4.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Usuario;
import com.projetoIntegrador4Texugos.projetoIntegrador4.repository.UsuarioRepository;


@Component
public class UsuarioUserDetailService implements UserDetailsService {

	@Autowired
	UsuarioRepository usuRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Usuario usuario = usuRepo.findByEmail(email); 
		
		if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }

        return usuario;
	}
	
	public Collection<? extends GrantedAuthority> authorities(Usuario usuario) {
        return usuario.getAuthorities(); 
    }

}
