package com.desafiolatam.facade.impl;

import com.desafiolatam.dto.CategoriaDto;
import com.desafiolatam.facade.CategoriaFacade;
import com.desafiolatam.service.CategoriaService;
import com.desafiolatam.service.impl.CategoriaServiceImpl;

public class CategoriaFacadeImpl implements CategoriaFacade{

	private CategoriaService categoriaService;
	
	public CategoriaFacadeImpl() {
		this.categoriaService = new CategoriaServiceImpl();
	}
	
	@Override
	public CategoriaDto listarTodosCategorias() {		
		return categoriaService.listarTodosCategorias();
	}

	@Override
	public CategoriaDto buscarCategoria(String id) {
		return categoriaService.buscarCategoria(id);
	}

	@Override
	public boolean modificarCategoria(String idCategoria, String nombreCategoria) {
		return categoriaService.modificarCategoria(idCategoria, nombreCategoria);
	}

	@Override
	public boolean agregarCategoria(String nombreCategoria) {
		return categoriaService.agregarCategoria(nombreCategoria);
	}

}
