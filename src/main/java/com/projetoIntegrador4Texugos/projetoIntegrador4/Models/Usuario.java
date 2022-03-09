package com.projetoIntegrador4Texugos.projetoIntegrador4.Models;

import java.util.Date;
import java.util.UUID;

import javax.print.attribute.standard.DateTimeAtCompleted;

import lombok.Data;


@Data
public class Usuario {

	private UUID id;
	private String nome;
	private String email;
	private String senha;
	private String CPF;
	private Date dataNascimento;
	private TipoUsuario tipo;
	
}
