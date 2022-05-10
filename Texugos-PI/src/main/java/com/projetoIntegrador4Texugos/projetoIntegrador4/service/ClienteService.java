package com.projetoIntegrador4Texugos.projetoIntegrador4.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.ClienteModel;
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
	
    public ClienteModel findByEmail(String email) {
		return this.clienteRepo.findByEmail(email);
	}
    
    public ClienteModel save(ClienteModel cli) {
    	return this.clienteRepo.save(cli);
    }
}
