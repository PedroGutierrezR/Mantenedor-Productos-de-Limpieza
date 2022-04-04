package com.desafiolatam.service.impl;

import com.desafiolatam.dao.LoginDao;
import com.desafiolatam.dao.impl.LoginDaoImpl;
import com.desafiolatam.dto.UsuarioLoginDto;
import com.desafiolatam.service.LoginService;

public class LoginServiceImpl implements LoginService{

	private LoginDao loginDao;
	private UsuarioLoginDto usuarioLoginDto;
	
	public LoginServiceImpl() {
		this.loginDao  = new LoginDaoImpl();
		this.usuarioLoginDto = new UsuarioLoginDto();
	}
	
	@Override
	public UsuarioLoginDto dameUsuarioLogin() {
		
		usuarioLoginDto.setUsuarioLogin(loginDao.dameUsuarioLogin());
		return usuarioLoginDto;
	}

}
