package com.desafiolatam.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.desafiolatam.facade.ProductoFacade;
import com.desafiolatam.facade.impl.ProductoFacadeImpl;

@WebServlet("/procesaListarProductos")
public class ListarProductoSevlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProductoFacade productoFacade;
	@Override
	public void init() throws ServletException {
		super.init();
		this.productoFacade = new ProductoFacadeImpl();
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		if(session.getAttribute("logged") != null && (boolean)session.getAttribute("logged") == true) {
			
			if(req.getAttribute("exito") != null) {
				req.setAttribute("modificacionExitosa", "no se ha podido completar la solicitud");
				req.setAttribute("listaProductos", productoFacade.listarTodosProductos().getListaProductos());
				req.getServletContext().getRequestDispatcher("/listarProductos.jsp").forward(req, resp);
			} else {
				req.setAttribute("exito", "no se ha podido completar la solicitud");
				if(req.getAttribute("modificacionExitosa") != null) {
					req.setAttribute("listaProductos", productoFacade.listarTodosProductos().getListaProductos());
					req.getServletContext().getRequestDispatcher("/listarProductos.jsp").forward(req, resp);
				} else {
					req.setAttribute("modificacionExitosa", "no se ha podido completar la solicitud");
					req.setAttribute("listaProductos", productoFacade.listarTodosProductos().getListaProductos());
					req.getServletContext().getRequestDispatcher("/listarProductos.jsp").forward(req, resp);
				}
				
			}
			
		} else {
			resp.sendRedirect("/productos_limpieza/login.jsp");
		}	
		
	
	}
	
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	public void destroy() {
		super.destroy();
	}
	
}
