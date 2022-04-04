package com.desafiolatam.service;

import com.desafiolatam.dto.ProductoDto;

public interface ProductoService {

	public ProductoDto listarTodosProductos();
	public ProductoDto buscarProducto(String id);
	public boolean modificarProducto(String idProducto, String nombre, String precio, String descripcion, String nombreCategoria);
	public boolean agregarProducto(String nombre, String precio, String descripcion, String nombreCategoria);
	public boolean eliminarProducto(String id);
	
}
