package com.projetoIntegrador4Texugos.projetoIntegrador4.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	
	@Column(length = 200)
    @Size(min = 1, max = 200)
	private String nomeProd;
	private String descricaoProduto;
	private double precoUnitProd;
	private int qtdeEstoqueProd;
	
	@Min(0)
	@Max(5)
	private double avaliacao;
	
	private String pathImagem;
	
	@Transient
	List<ImagemModel> imagens;

	@NotNull
	private Boolean isAtivo;
}