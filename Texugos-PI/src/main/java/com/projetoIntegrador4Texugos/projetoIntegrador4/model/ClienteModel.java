package com.projetoIntegrador4Texugos.projetoIntegrador4.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
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
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteModel implements UserDetails{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	@Email
	@Column(unique = true)
	private String email;
	@NotBlank
	private String senha;
	
	@Column(unique = true)
	@NotBlank
	private String CPF;
	
	@NotBlank
	private String dataNascimento;
	
	@NotNull
	private String genero;
	
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo = TipoUsuario.CLIENTE;
	
	@Transient
	private List<EnderecoModel> enderecos;
	@Transient
	private EnderecoModel endereco = new EnderecoModel();

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
		return true;
	}

	@Override
	public String toString() {
		return "ClienteModel [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", CPF=" + CPF
				+ ", dataNascimento=" + dataNascimento + ", genero=" + genero + ", tipo=" + tipo + ", enderecos="
				+ enderecos.size() + "]";
	}	
	
	
	
}
