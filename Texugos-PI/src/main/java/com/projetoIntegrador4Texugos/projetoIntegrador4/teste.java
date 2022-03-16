package com.projetoIntegrador4Texugos.projetoIntegrador4;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class teste {
	public static void main(String[] args) {
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		String senha = bc.encode("teste1234");
		System.out.println(senha);
	}
}
