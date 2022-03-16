package com.projetoIntegrador4Texugos.projetoIntegrador4.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.TipoUsuario;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Usuario;

import lombok.Data;

@Data
public class UsuarioInput {
	//@NotBlank
	private String nome;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@org.hibernate.validator.constraints.br.CPF
	private String CPF;
	
	private String telefone;
	
	@NotBlank
	private String dataNascimento;
	
	private String tipo;
	
	@NotBlank
	private String senha;
	
	
	public Usuario toUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNome(this.nome);
		usuario.setEmail(this.email);
		usuario.setCPF(this.CPF);
		usuario.setTelefone(Long.parseLong(this.telefone));
		usuario.setDataNascimento(dataNascimento);
		usuario.setSenha(senha);
		usuario.setTipo(TipoUsuario.valueOf(this.tipo.toUpperCase()));
		usuario.setIsAtivo(true);
		
		return usuario;
	}
	
	
	
}
