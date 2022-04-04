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

@WebServlet("/procesaEliminarProducto")
public class EliminarProductoServlet extends HttpServlet{

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
			
			if(req.getParameter("id") != null) {
				boolean resultado = productoFacade.eliminarProducto(req.getParameter("id"));
				
				if(resultado) {
					req.setAttribute("exito", "Eliminado correctamente");
					req.getServletContext().getRequestDispatcher("/procesaListarProductos").forward(req, resp);
				} else {
					resp.sendRedirect("principal.jsp");
				}
			} else {
				req.getServletContext().getRequestDispatcher("/procesaListarProductos").forward(req, resp);
			}
		

		} else {
			resp.sendRedirect("/productos_limpieza/login.jsp");
		}

		
	}
	
}
