package com.projetoIntegrador4Texugos.projetoIntegrador4.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		System.out.print("HIHU");
		System.out.println(exception.getClass());
		System.out.println(exception.getMessage());
		if (exception instanceof BadCredentialsException || exception instanceof UsernameNotFoundException) {
			System.out.print(exception.getMessage());
			request.setAttribute("error", exception.getMessage());
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

}
