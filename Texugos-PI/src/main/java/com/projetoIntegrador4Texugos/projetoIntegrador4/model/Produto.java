package com.projetoIntegrador4Texugos.projetoIntegrador4.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tab_produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProd; 
	private String nomeProd;
	private String descricaoProduto;
	private double precoUnitProd;
	private int qtdeEstoqueProd;
	private double avaliacao;
	
	private String pathImagem;
	
	@Transient
	List<ImagemModel> imagens;

	@NotNull
	private Boolean isAtivo;
}