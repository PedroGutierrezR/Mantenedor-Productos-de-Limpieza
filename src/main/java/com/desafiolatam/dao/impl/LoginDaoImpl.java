package com.desafiolatam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.desafiolatam.dao.LoginDao;
import com.desafiolatam.dao.modelo.UsuarioLogin;
import com.desafiolatam.dao.utils.ConnectionUtil;

public class LoginDaoImpl implements LoginDao{

	private ConnectionUtil connectionUtil = new ConnectionUtil();
	
	@Override
	public UsuarioLogin dameUsuarioLogin() {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT nombre, password FROM admin";
		UsuarioLogin usuarioLogin = new UsuarioLogin();
		try {
			conn = connectionUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				
				usuarioLogin.setNombre(rs.getString("nombre"));
				usuarioLogin.setPassword(rs.getString("password"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarioLogin;
	}

}
