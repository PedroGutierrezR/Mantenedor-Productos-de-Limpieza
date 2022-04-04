package com.desafiolatam.facade.impl;

import com.desafiolatam.dto.ProductoDto;
import com.desafiolatam.facade.ProductoFacade;
import com.desafiolatam.service.ProductoService;
import com.desafiolatam.service.impl.ProductoServiceImpl;

public class ProductoFacadeImpl implements ProductoFacade{

	private ProductoService productoService;
	
	public ProductoFacadeImpl() {
		this.productoService = new ProductoServiceImpl();
	}
	
	@Override
	public ProductoDto listarTodosProductos() {
		return productoService.listarTodosProductos();
	}

	@Override
	public ProductoDto buscarProducto(String id) {	
		return productoService.buscarProducto(id);
	}

	@Override
	public boolean modificarProducto(String idProducto, String nombre, String precio, String descripcion, String nombreCategoria) {
		return productoService.modificarProducto(idProducto, nombre,precio,descripcion,nombreCategoria);	
	}

	@Override
	public boolean agregarProducto(String nombre, String precio, String descripcion, String nombreCategoria) {	
		return productoService.agregarProducto(nombre,precio,descripcion,nombreCategoria);
	}

	@Override
	public boolean eliminarProducto(String id) {
		return productoService.eliminarProducto(id);
	}

}
