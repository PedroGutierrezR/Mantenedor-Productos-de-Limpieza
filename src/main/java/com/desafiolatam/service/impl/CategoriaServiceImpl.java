package com.desafiolatam.service.impl;

import com.desafiolatam.dao.CategoriaDao;
import com.desafiolatam.dao.impl.CategoriaDaoImpl;
import com.desafiolatam.dao.modelo.Categoria;
import com.desafiolatam.dto.CategoriaDto;
import com.desafiolatam.service.CategoriaService;

public class CategoriaServiceImpl implements CategoriaService {

	private CategoriaDao categoriaDao;
	private CategoriaDto categoriaDto;
	
	public CategoriaServiceImpl() {
		this.categoriaDao = new CategoriaDaoImpl();
		this.categoriaDto = new CategoriaDto();
	}
	
	@Override
	public CategoriaDto listarTodosCategorias() {
		categoriaDto.setListaCategorias(categoriaDao.listarCategorias());
		return categoriaDto;
		
	}

	@Override
	public CategoriaDto buscarCategoria(String id) {
		int idInt = Integer.parseInt(id);
		categoriaDto.setListaCategorias(categoriaDao.buscarCategoria(idInt));
		return categoriaDto;
	}

	@Override
	public boolean modificarCategoria(String idCategoria, String nombreCategoria) {	
		
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(Integer.parseInt(idCategoria));
		categoria.setNombreCategoria(nombreCategoria);
		return categoriaDao.modificarCategoria(categoria);
	}

	@Override
	public boolean agregarCategoria(String nombreCategoria) {
		return categoriaDao.agregarCategoria(nombreCategoria);
	}

	

}
