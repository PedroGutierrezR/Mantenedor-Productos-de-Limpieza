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

@WebServlet("/procesaAgregarCategoria")
public class AgregarCategoriaServlet extends HttpServlet {

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
			if(req.getAttribute("exito") != null) {
				req.getServletContext().getRequestDispatcher("/agregarCategorias.jsp").forward(req, resp);
			} else {
				req.setAttribute("exito", "no se ha podido completar la solicitud");
				req.getServletContext().getRequestDispatcher("/agregarCategorias.jsp").forward(req, resp);
			}
			
		} else {
			resp.sendRedirect("/productos_limpieza/login.jsp");
		}	
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nombreCategoria = req.getParameter("nombreCategoria");
		boolean resultado = categoriaFacade.agregarCategoria(nombreCategoria);
		
		if (resultado) {
			req.setAttribute("exito", "se guardó exitosamente");
			doGet(req, resp);
		} else {
			resp.sendRedirect("principal.jsp");
		}

	}

	@Override
	public void destroy() {
		super.destroy();
	}

}
