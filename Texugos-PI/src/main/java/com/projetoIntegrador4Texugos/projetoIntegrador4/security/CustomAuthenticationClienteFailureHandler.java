package com.projetoIntegrador4Texugos.projetoIntegrador4.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationClienteFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		System.out.println(exception.getClass());
		System.out.println(exception.getMessage());
		System.out.println(request.getContextPath());
		if (exception instanceof BadCredentialsException ) {
			request.setAttribute("error", "Login e/ou Senha inválidos.");
		}else if(exception instanceof DisabledException) {
			request.setAttribute("error", "Este usuário está desativado.");
		}
		request.getRequestDispatcher("/cliente/login").forward(request, response);
	}

}
