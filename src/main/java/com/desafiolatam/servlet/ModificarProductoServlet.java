package com.desafiolatam.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.desafiolatam.dao.modelo.Categoria;
import com.desafiolatam.dao.modelo.Producto;
import com.desafiolatam.dto.CategoriaDto;
import com.desafiolatam.dto.ProductoDto;
import com.desafiolatam.facade.CategoriaFacade;
import com.desafiolatam.facade.ProductoFacade;
import com.desafiolatam.facade.impl.CategoriaFacadeImpl;
import com.desafiolatam.facade.impl.ProductoFacadeImpl;

@WebServlet("/procesaModificarProducto")
public class ModificarProductoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProductoFacade productoFacade;
	private CategoriaFacade categoriaFacade;
	@Override
	public void init() throws ServletException {
		super.init();
		this.productoFacade = new ProductoFacadeImpl();
		this.categoriaFacade = new CategoriaFacadeImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		HttpSession session = req.getSession();
		if(session.getAttribute("logged") != null && (boolean)session.getAttribute("logged") == true) {
			if(req.getParameter("id") != null) {
				ProductoDto productoDto = productoFacade.buscarProducto(req.getParameter("id"));
				Producto producto = productoDto.getListaProductos().get(0);
				
				req.setAttribute("producto", producto);
				
				CategoriaDto categoriaDto = categoriaFacade.listarTodosCategorias();
				List<Categoria> listaCategorias = categoriaDto.getListaCategorias();
				
				req.setAttribute("listaCategorias", listaCategorias);
				req.getServletContext().getRequestDispatcher("/modificarProducto.jsp").forward(req, resp);
			} else {
				req.getServletContext().getRequestDispatcher("/procesaListarProductos").forward(req, resp);	
			}

		} else {
			resp.sendRedirect("/productos_limpieza/login.jsp");
		}	
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String idProducto = req.getParameter("idProducto");
		String nombreProducto = req.getParameter("nombreProducto");
		String precioProducto = req.getParameter("precioProducto");
		String descripcionProducto = req.getParameter("descripcionProducto");
		String nombreCategoria = req.getParameter("categoriaProducto");	
		boolean resultado = productoFacade.modificarProducto(idProducto, nombreProducto,precioProducto,descripcionProducto,nombreCategoria);

		if(resultado) {
			req.setAttribute("modificacionExitosa", "modificacionExistosa");
			req.getServletContext().getRequestDispatcher("/procesaListarProductos").forward(req, resp);	
		} else {
			resp.sendRedirect("principal.jsp");
		}
				
	}

	
	
}
