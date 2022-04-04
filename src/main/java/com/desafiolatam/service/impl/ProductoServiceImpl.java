package com.desafiolatam.service.impl;

import java.util.List;

import com.desafiolatam.dao.CategoriaDao;
import com.desafiolatam.dao.ProductoDao;
import com.desafiolatam.dao.impl.CategoriaDaoImpl;
import com.desafiolatam.dao.impl.ProductoDaoImpl;
import com.desafiolatam.dao.modelo.Categoria;
import com.desafiolatam.dao.modelo.Producto;
import com.desafiolatam.dto.ProductoDto;
import com.desafiolatam.service.ProductoService;

public class ProductoServiceImpl implements ProductoService {

	private ProductoDao productoDao;
	private ProductoDto productoDto;
	private CategoriaDao categoriaDao;
	
	public ProductoServiceImpl() {
		this.productoDao = new ProductoDaoImpl();
		this.productoDto = new ProductoDto();
		this.categoriaDao = new CategoriaDaoImpl();
	}

	@Override
	public ProductoDto listarTodosProductos() {
		productoDto.setListaProductos(productoDao.listarProductos());
		return productoDto;
	}

	@Override
	public ProductoDto buscarProducto(String id) {
		int idProducto = Integer.parseInt(id);
		productoDto.setListaProductos(productoDao.buscarProducto(idProducto));
		return productoDto;
	}

	@Override
	public boolean modificarProducto(String idProducto, String nombre, String precio, String descripcion, String nombreCategoria) {
		
		Producto producto = new Producto();
		producto.setId(Integer.parseInt(idProducto));
		producto.setNombre(nombre);
		producto.setPrecio(Integer.parseInt(precio));
		producto.setDescripcion(descripcion);
		
		List<Categoria> listaCategorias = categoriaDao.listarCategorias();
		
		for (Categoria categoria : listaCategorias) {
			if(nombreCategoria.equals(categoria.getNombreCategoria())) {
				producto.setCategoria(categoria);
			}
		}
		
		return productoDao.modificarProducto(producto); 
	}

	@Override
	public boolean agregarProducto(String nombre, String precio, String descripcion, String nombreCategoria) {
		
		Producto producto = new Producto();
		producto.setNombre(nombre);
		producto.setPrecio(Integer.parseInt(precio));
		producto.setDescripcion(descripcion);
		
		List<Categoria> listaCategorias = categoriaDao.listarCategorias();
		
		for (Categoria categoria : listaCategorias) {
			if(nombreCategoria.equals(categoria.getNombreCategoria())) {
				producto.setCategoria(categoria);
			}
		}
		
		return productoDao.agregarProducto(producto);
		
	}

	@Override
	public boolean eliminarProducto(String id) {	

		int idProducto = Integer.parseInt(id);	
		return productoDao.eliminarProducto(idProducto);
		
	}
}
