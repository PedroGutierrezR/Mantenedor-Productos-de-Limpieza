package com.desafiolatam.dao;

import java.util.List;

import com.desafiolatam.dao.modelo.Producto;

public interface ProductoDao {

	public List<Producto> buscarProducto(int id);
	public List<Producto> listarProductos();
	public boolean agregarProducto(Producto producto);
	public boolean modificarProducto(Producto producto);
	public boolean eliminarProducto(int id);
	
}
