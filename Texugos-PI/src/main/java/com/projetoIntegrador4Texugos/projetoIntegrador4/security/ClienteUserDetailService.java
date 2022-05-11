package com.projetoIntegrador4Texugos.projetoIntegrador4.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.ClienteModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.repository.ClienteRepository;


@Component
public class ClienteUserDetailService implements UserDetailsService {

	@Autowired
	ClienteRepository cliRepo;
	
	
	@Override
	public ClienteModel loadUserByUsername(String email) throws UsernameNotFoundException {
		
		ClienteModel cliente = cliRepo.findByEmail(email); 
		
		if (cliente == null) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }

        return cliente;
	}
	
	public Collection<? extends GrantedAuthority> authorities(ClienteModel cliente) {
        return cliente.getAuthorities(); 
    }

}
