package com.desafiolatam.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.desafiolatam.dao.modelo.Categoria;
import com.desafiolatam.dto.CategoriaDto;
import com.desafiolatam.facade.CategoriaFacade;
import com.desafiolatam.facade.impl.CategoriaFacadeImpl;

@WebServlet("/procesaModificarCategoria")
public class ModificarCategoriaServlet extends HttpServlet{

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
			if(req.getParameter("id") != null) {
				CategoriaDto categoriaDto = categoriaFacade.buscarCategoria(req.getParameter("id"));
				Categoria categoria = categoriaDto.getListaCategorias().get(0);

				req.setAttribute("categoria", categoria);
				req.getServletContext().getRequestDispatcher("/modificarCategoria.jsp").forward(req, resp);
			} else {
				req.getServletContext().getRequestDispatcher("/procesaListarCategorias").forward(req, resp);
			}

		} else {
			resp.sendRedirect("/productos_limpieza/login.jsp");
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String idCategoria= req.getParameter("idCategoria");
		String nombreCategoria = req.getParameter("nombreCategoria");
		boolean resultado = categoriaFacade.modificarCategoria(idCategoria, nombreCategoria);

		if(resultado) {
			req.setAttribute("modificacionExitosa", "modificacionExistosa");
			req.getServletContext().getRequestDispatcher("/procesaListarCategorias").forward(req, resp);
		} else {
			resp.sendRedirect("principal.jsp");
		}
		
	}
	
	
	
	
}
