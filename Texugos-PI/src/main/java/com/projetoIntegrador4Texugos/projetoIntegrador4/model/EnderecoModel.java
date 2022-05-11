package com.projetoIntegrador4Texugos.projetoIntegrador4.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "endereco")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private ClienteModel cliente;
	@NotBlank
	private String CEP;
	@NotBlank
	private String logradouro;
	@NotNull
	private Integer numero; //<- esse input para editar
	@Nullable
	private String complemento; //<- esse input para editar
	@NotBlank
	private String bairro;
	@NotBlank
	private String localidade;
	@NotBlank
	private String UF;
	@NotNull
	private Boolean isPadrao;
	
	
	@Override
	public String toString() {
		return "EnderecoModel [clienteId=" + cliente.getId() + ", CEP=" + CEP + ", logradouro=" + logradouro + ", numero="
				+ numero + ", complemento=" + complemento + ", bairro=" + bairro + ", localidade=" + localidade
				+ ", UF=" + UF + ", isPadrao=" + isPadrao + "]";
	}
	
	
	
}
