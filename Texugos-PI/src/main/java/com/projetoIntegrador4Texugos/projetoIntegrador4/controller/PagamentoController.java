package com.projetoIntegrador4Texugos.projetoIntegrador4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagamentoController {
	
	@GetMapping
	@RequestMapping("/pagamento")
	public String viewPagamento() {
		return "pagamento";
	}

}
