package com.projetoIntegrador4Texugos.projetoIntegrador4.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer>{
    	
	@Override
	default <S extends Compra> S saveAndFlush(S entity) {
		S result = save(entity);
		flush();

		return result;
	}

}
