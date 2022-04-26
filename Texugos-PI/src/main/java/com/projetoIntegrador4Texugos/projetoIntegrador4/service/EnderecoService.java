package com.projetoIntegrador4Texugos.projetoIntegrador4.service;


import com.projetoIntegrador4Texugos.projetoIntegrador4.repository.EnderecoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.EnderecoModel;

import java.util.List;


@Service
public class EnderecoService {

    @Autowired
	private EnderecoRepository enderecoRepo;


    public List<EnderecoModel> findAll() {
		List<EnderecoModel> list = this.enderecoRepo.findAll();
		return list;
	}
	
	
}
