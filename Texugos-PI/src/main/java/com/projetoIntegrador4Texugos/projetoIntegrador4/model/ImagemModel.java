package com.projetoIntegrador4Texugos.projetoIntegrador4.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "imagens")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImagemModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idImagem;
	
	private Integer idProduto;
	
	@NotBlank
	private String pathImagem;
	
	private Boolean principal;

}
