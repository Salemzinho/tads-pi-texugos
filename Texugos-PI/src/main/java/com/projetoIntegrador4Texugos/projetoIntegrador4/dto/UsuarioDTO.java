package com.projetoIntegrador4Texugos.projetoIntegrador4.dto;

import java.util.Date;
import java.util.UUID;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.TipoUsuario;

import lombok.Data;

@Data
public class UsuarioDTO {

	
	private UUID id;

	private String nome;

	private String email;

	private Long telefone;

	private String senha;
	
	private String CPF;
	
	private Date dataNascimento;
	
	private Boolean isAtivo;
	
	private TipoUsuario tipo;	
}

