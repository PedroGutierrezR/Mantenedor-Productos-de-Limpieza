package com.desafiolatam.facade;

import com.desafiolatam.dto.ProductoDto;

public interface ProductoFacade {

	public ProductoDto listarTodosProductos();
	public ProductoDto buscarProducto(String id);
	public boolean modificarProducto(String idProducto, String nombre, String precio, String descripcion, String nombreCategoria);
	public boolean agregarProducto(String nombre, String precio, String descripcion, String nombreCategoria);
	public boolean eliminarProducto(String id);
	
}
