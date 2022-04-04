package com.desafiolatam.facade.impl;

import com.desafiolatam.dto.UsuarioLoginDto;
import com.desafiolatam.facade.LoginFacade;
import com.desafiolatam.service.LoginService;
import com.desafiolatam.service.impl.LoginServiceImpl;

public class LoginFacadeImpl implements LoginFacade {

	private LoginService loginService;

	public LoginFacadeImpl() {
		this.loginService = new LoginServiceImpl();
	}
	
	@Override
	public UsuarioLoginDto dameUsuarioLogin() {
		return loginService.dameUsuarioLogin();	
	}

}
