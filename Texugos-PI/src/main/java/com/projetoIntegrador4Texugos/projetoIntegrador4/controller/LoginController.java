package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@GetMapping
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping
	@RequestMapping("cadastro")
	public String cadastro() {
		return "usuario/cadastro";
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public String onUsernameNotFoundException() {
		return "redirect:/login?error=1";
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public String onBadCredentialsException() {
		return "redirect:/login?error=2";
	}
	
}
