package com.projetoIntegrador4Texugos.projetoIntegrador4.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.ClienteModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.ClienteService;

@Component
public class AuthClienteProviderService implements AuthenticationProvider {

    @Autowired
    private ClienteService clienteService;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String login = auth.getName();
        String senha = auth.getCredentials().toString();
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

        ClienteModel clienteBd = clienteService.findByEmail(login);

		if (clienteBd != null) {
			if (BCrypt.checkpw(senha, clienteBd.getSenha())) {
				if (clienteAtivo(clienteBd)) {
					Collection<? extends GrantedAuthority> authorities = clienteBd.getAuthorities();
					return new UsernamePasswordAuthenticationToken(login, senha, authorities);
				} else {
					throw new BadCredentialsException("Este usuário está desativado.");
				}
			}
			throw new UsernameNotFoundException("Login e/ou Senha inválidos.");
		}
		throw new UsernameNotFoundException("Login e/ou Senha inválidos.");
	}

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }

    private boolean clienteAtivo(ClienteModel cliente) {
        if (cliente != null) {
        	return true;
        }
        return false;
    }

}
