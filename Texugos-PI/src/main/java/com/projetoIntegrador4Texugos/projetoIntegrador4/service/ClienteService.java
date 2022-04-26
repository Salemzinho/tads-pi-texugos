package com.projetoIntegrador4Texugos.projetoIntegrador4.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoIntegrador4Texugos.projetoIntegrador4.repository.ClienteRepository;


@Service
public class ClienteService {

    @Autowired
	private ClienteRepository clienteRepo;

/*
    public List<ClienteModel> findAll() {
		List<ClienteModel> list = this.clienteRepo.findAll();
		return list;
	}
	*/
	
}
