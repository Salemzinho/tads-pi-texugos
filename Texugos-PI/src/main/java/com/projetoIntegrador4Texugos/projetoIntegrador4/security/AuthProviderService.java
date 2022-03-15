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
import org.springframework.stereotype.Component;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Usuario;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.UsuarioService;

@Component
public class AuthProviderService implements AuthenticationProvider {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String login = auth.getName();
        String senha = auth.getCredentials().toString();

        Usuario usuarioBd = usuarioService.findByEmail(login);

        if (usuarioBd != null) {
            if (usuarioAtivo(usuarioBd)) {
                Collection<? extends GrantedAuthority> authorities = usuarioBd.getAuthorities();
                return new UsernamePasswordAuthenticationToken(login, senha, authorities);
            } else {
                throw new BadCredentialsException("Este usuário está desativado.");
            }
        }

        throw new UsernameNotFoundException("Login e/ou Senha inválidos.");
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }

    private boolean usuarioAtivo(Usuario usuario) {
        if (usuario != null) {
            if (usuario.getIsAtivo() == true) {
                return true;
            }
        }
        return false;
    }

}
