package com.projetoIntegrador4Texugos.projetoIntegrador4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.PedidoModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.repository.PedidoRepository;
import com.projetoIntegrador4Texugos.projetoIntegrador4.repository.UsuarioRepository;



@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidosRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<PedidoModel> findAll() {
		List<PedidoModel> list = this.pedidosRepository.findAll();

		return list;
	}

	/*
	 * public PedidoModel save(PedidoNewDTO newPedido) { int idUsuario =
	 * newPedido.getUsuario().getIdUsuario(); Optional<Usuario> optObj =
	 * this.usuarioRepository.findById(idUsuario);
	 * 
	 * if(optObj.isPresent()) { PedidoModel model = newPedido;
	 * model.setUsuario(optObj.get());
	 * 
	 * return this.pedidosRepository.save(model); }else { // Descomentar quando o
	 * tratamento de erro estiver pronto //throw new
	 * ObjetoNaoEncontradoException("Usuário não encontrado"); return null; //
	 * Apagar depois do trat. de erro }
	 * 
	 * 
	 * }
	 */

	public PedidoModel finOne(int idPedido) throws Exception {
		return this.pedidosRepository.findById(idPedido).orElseThrow(() -> new Exception("Matrícula não localizada"));
	}
	
	

	public void delete(int idPedido) {
		this.pedidosRepository.deleteById(idPedido);
	}

	public PedidoModel update(int id, PedidoModel newObj) {
		Optional<PedidoModel> optObj = this.pedidosRepository.findById(id);
		
		if (optObj.isPresent()) {
			PedidoModel objFromDatabase = optObj.get();			

			objFromDatabase.setTotalPedido(newObj.getTotalPedido());
			
			return this.pedidosRepository.save(objFromDatabase);
			
		}

		else {
			throw new RuntimeException("Pedido não encontrado");
		}
		//change later
	}
	
	/*
	 * public Page<PedidoModel> paginacao(int pagina, int registros) { PageRequest
	 * pageRequest = PageRequest.of(pagina, registros); // later.. add a new
	 * parameter to search for user name the orders
	 * 
	 * Page<PedidoModel> pageModel = this.pedidosRepository.findAll(pageRequest);
	 * 
	 * Page<PedidoModel> pageDTO = pageModel( new Function<PedidoModel,
	 * PedidoModel>(){ public PedidoModel apply(PedidoModel model) { return model; }
	 * } ); return pageDTO; }
	 */
}