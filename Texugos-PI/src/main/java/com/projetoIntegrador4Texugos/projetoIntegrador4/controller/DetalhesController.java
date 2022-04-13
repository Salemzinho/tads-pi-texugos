package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Produto;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.ProdutoService;

@Controller
public class DetalhesController {

	@Autowired
	private ProdutoService prodService;
	
    @GetMapping
	@RequestMapping("/detalhes/{id}")
	public String detalhes(Model model) {

		return "detalhes";
	}

	@GetMapping("/detalhes")
	public String detalhesPorId(Model model) {

		return "detalhes";
	}
}
