package com.desafiolatam.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.desafiolatam.facade.LoginFacade;
import com.desafiolatam.facade.impl.LoginFacadeImpl;

@WebServlet("/procesaLoginServlet")
public class ProcesaLoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LoginFacade loginFacade;
	private String usuarioLogin;
	private String passwordLogin;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.loginFacade = new LoginFacadeImpl();
		this.usuarioLogin = loginFacade.dameUsuarioLogin().getUsuarioLogin().getNombre();
		this.passwordLogin = loginFacade.dameUsuarioLogin().getUsuarioLogin().getPassword();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session.getAttribute("logged") != null && (boolean)session.getAttribute("logged") == true) {
			req.getServletContext().getRequestDispatcher("/principal.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("/productos_limpieza/login.jsp");
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		HttpSession session = req.getSession();
		//Rescatar lo que ingresó el usuario
		String userRecibido = req.getParameter("login");
		String passwordRecibida = req.getParameter("pass");
		
		if(!usuarioLogin.equals(userRecibido) || !passwordLogin.equals(passwordRecibida)) {
			session.invalidate();
			req.setAttribute("mensajeLoginIncorrecto", "Ingrese datos correctos");
			req.getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
			
		} else {
			session.setAttribute("usuarioConectado", userRecibido);
			session.setAttribute("logged", true);
			req.getServletContext().getRequestDispatcher("/principal.jsp").forward(req, resp);
		}
		
	}

	@Override
	public void destroy() {

		super.destroy();
	}

	
}
