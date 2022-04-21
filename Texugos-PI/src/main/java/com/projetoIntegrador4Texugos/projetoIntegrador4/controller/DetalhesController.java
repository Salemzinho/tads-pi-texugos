package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Produto;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.ProdutoService;

@Controller
@RequestMapping("/detalhes")
public class DetalhesController {

	@Autowired

	private ProdutoService prodService;
	
	@GetMapping("/{id}")
	public String viewProduct(@PathVariable int id, Model model) throws Exception {
			Produto produto = prodService.findOne(id);
			model.addAttribute("produto", produto);
			return "detalhes";	
	}

    @GetMapping("/detalhes")
    public String detalhesPorId(Model model) {

        return "detalhes";
    }
}
