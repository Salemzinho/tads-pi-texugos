package com.projetoIntegrador4Texugos.projetoIntegrador4.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	/*
	 * @GeneratedValue( strategy = GenerationType.SEQUENCE,
	 * generator="produto_seq_generator")
	 * 
	 * @SequenceGenerator(name="produto_seq_generator", sequenceName="produto_seq",
	 * allocationSize = 1)
	 */
	@Column(name = "id_prod")
	private Integer idProd;
	@Column(name = "nome_prod")
	private String nomeProd;
	@Column(name = "preco_unit_prod")
	private double precoUnitProd;
	@Column(name = "qtde_estoque_prod")
	private int qtdeEstoqueProd;
}