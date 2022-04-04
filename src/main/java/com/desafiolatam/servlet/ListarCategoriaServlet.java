package com.desafiolatam.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.desafiolatam.facade.CategoriaFacade;
import com.desafiolatam.facade.impl.CategoriaFacadeImpl;

@WebServlet("/procesaListarCategorias")
public class ListarCategoriaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CategoriaFacade categoriaFacade;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.categoriaFacade = new CategoriaFacadeImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		if(session.getAttribute("logged") != null && (boolean)session.getAttribute("logged") == true) {
			
			if(req.getAttribute("modificacionExitosa") != null) {
				req.setAttribute("listaCategorias",categoriaFacade.listarTodosCategorias().getListaCategorias());
				req.getServletContext().getRequestDispatcher("/listarCategorias.jsp").forward(req, resp);
			}else {
				req.setAttribute("modificacionExitosa", "no se ha podido completar la solicitud");
				req.setAttribute("listaCategorias",categoriaFacade.listarTodosCategorias().getListaCategorias());
				req.getServletContext().getRequestDispatcher("/listarCategorias.jsp").forward(req, resp);
			}
			

		} else {
			resp.sendRedirect("/productos_limpieza/login.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}	
	
	
	
}
