package com.projetoIntegrador4Texugos.projetoIntegrador4.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UsuarioDTOIn {
	private int idUsuario;
	
	@NotBlank(message = "Nome deve ser preenchido")
	private String nomeUsuario;

	@NotBlank(message = "CPF deve ser preenchido")
	@Max(value = 11, message = "CPF deve conter 11 caracteres no máximo")
	@Min(value = 11, message = "CPF deve conter 11 caracteres no mínimo")
	private String cpf;

	@NotBlank(message = "Email deve ser preenchido")
	private String email;

	@NotBlank(message = "Telefone deve ser preenchido")
	private String telefone;

	@NotBlank(message = "Senha deve ser preenchido")
	private String senha;

	
	//NÃO FUNFA
	/*
	 * public UsuarioDTO toEntity() { ModelMapper modelMapper = new ModelMapper();
	 * return modelMapper.map(this, UsuarioDTO.class); }
	 */
}