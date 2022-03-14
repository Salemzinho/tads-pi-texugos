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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
	private UUID id;
	@NotBlank
	private String nome;
	
	@NotBlank
	@Email
	@Column(unique = true)
	private String email;
	private Long telefone;
	@NotBlank
	private String senha;
	
	@Column(unique = true)
	@NotBlank
	private String CPF;
	@NotBlank
	private Date dataNascimento;
	
	@NotNull
	private Boolean isAtivo;
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;	
	
}
