package com.projetoIntegrador4Texugos.projetoIntegrador4.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.attribute.standard.DateTimeAtCompleted;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
	private UUID id;
	private String nome;
	
	@Column(unique = true)
	private String email;
	
	private String senha;
	
	@Column(unique = true)
	private String CPF;
	private Date dataNascimento;
	
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;
	
}
