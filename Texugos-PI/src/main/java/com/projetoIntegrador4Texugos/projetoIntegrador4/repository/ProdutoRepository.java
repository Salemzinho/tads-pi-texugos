package com.projetoIntegrador4Texugos.projetoIntegrador4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	/*
	@Query("select * from tab_produto Where nome_prod like %?1%")
    List<Produto> findProdutoByName(String nome);
	*/
}