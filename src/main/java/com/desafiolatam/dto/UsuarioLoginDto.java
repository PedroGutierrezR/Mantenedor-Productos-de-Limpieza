package com.desafiolatam.dto;

import com.desafiolatam.dao.modelo.UsuarioLogin;

public class UsuarioLoginDto {

	private UsuarioLogin usuarioLogin;

	public UsuarioLoginDto() {
		this.usuarioLogin = new UsuarioLogin(); 
	}
	
	public UsuarioLogin getUsuarioLogin() {
		return usuarioLogin;
	}

	public void setUsuarioLogin(UsuarioLogin usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}
	
	
	
	
}
