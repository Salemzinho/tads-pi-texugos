package com.projetoIntegrador4Texugos.projetoIntegrador4.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	private Integer clienteId;
	@NotBlank
	private String CEP;
	@NotBlank
	private String logradouro;
	@NotNull
	private Integer numero; //<- esse input para editar
	@NotBlank
	private String complemento; //<- esse input para editar
	@NotBlank
	private String bairro;
	@NotBlank
	private String localidade;
	@NotBlank
	private String UF;
	@NotNull
	private Boolean isFaturamento;
	@NotNull
	private Boolean isPadrao;
	
	
	@Override
	public String toString() {
		return "EnderecoModel [clienteId=" + clienteId + ", CEP=" + CEP + ", logradouro=" + logradouro + ", numero="
				+ numero + ", complemento=" + complemento + ", bairro=" + bairro + ", localidade=" + localidade
				+ ", UF=" + UF + ", isFaturamento=" + isFaturamento + ", isPadrao=" + isPadrao + "]";
	}
	
	
	
}
