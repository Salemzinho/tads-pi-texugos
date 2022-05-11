package com.projetoIntegrador4Texugos.projetoIntegrador4.model;

public enum TipoUsuario {
	ADMINISTRADOR("ROLE_ADMINISTRADOR"), ESTOQUISTA("ROLE_ESTOQUISTA"), CLIENTE("ROLE_CLIENTE"); 
	private String tipo;
	
	private TipoUsuario(String tipo){
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return this.tipo;
	}
	
}
