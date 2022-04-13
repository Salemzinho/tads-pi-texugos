package com.projetoIntegrador4Texugos.projetoIntegrador4.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
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
/*
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
*/
	@NotBlank
	private String dataNascimento;
	
	@NotNull
	private Boolean isAtivo;
	
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(getTipo().toString()));
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.isAtivo;
	}	
	
	
	//NÃO FUNFA
/*	public UsuarioDTO toDTO() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, UsuarioDTO.class);*/
	
}




