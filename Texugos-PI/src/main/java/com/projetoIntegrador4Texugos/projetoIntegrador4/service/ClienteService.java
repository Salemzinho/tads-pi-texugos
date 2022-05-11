package com.projetoIntegrador4Texugos.projetoIntegrador4.service;


import java.util.Optional;

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
    
    public ClienteModel update(int idCliente, ClienteModel cliente) {
		Optional<ClienteModel> clienteOptional = this.clienteRepo.findById(idCliente);
		if (clienteOptional.isPresent()) {
			ClienteModel clienteExistente = clienteOptional.get();
			clienteExistente.setId(cliente.getId());
			clienteExistente.setNome(cliente.getNome());
			clienteExistente.setCPF(cliente.getCPF());
			clienteExistente.setDataNascimento(cliente.getDataNascimento());
			clienteExistente.setEmail(cliente.getEmail());
			if(cliente.getSenha() != null && !cliente.getSenha().trim().isEmpty()) {
				clienteExistente.setSenha(cliente.getSenha());
			}					

			return this.clienteRepo.save(clienteExistente);
		} else {
			throw new RuntimeException("Cliente nao encontrado");
		}
	}
}
