package com.projetoIntegrador4Texugos.projetoIntegrador4.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
	@Column(name = "id_prod")
	private Integer idProd;
	@Column(name = "nome_prod")
	private String nomeProd;
	@Column(name = "preco_unit_prod")
	private double precoUnitProd;
	@Column(name = "qtde_estoque_prod")
	private int qtdeEstoqueProd;
	
	@NotNull
	private Boolean isAtivo;
}