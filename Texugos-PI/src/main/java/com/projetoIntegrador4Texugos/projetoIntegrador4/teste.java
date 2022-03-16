package com.projetoIntegrador4Texugos.projetoIntegrador4;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class teste {
	public static void main(String[] args) {
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		String sen = "teste1234";
		String senha = bc.encode(sen);
		System.out.println(senha);
		
		System.out.println(BCrypt.checkpw(sen, senha));
		
	}
}
