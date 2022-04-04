package com.desafiolatam.facade;

import com.desafiolatam.dto.CategoriaDto;

public interface CategoriaFacade {

	public CategoriaDto listarTodosCategorias();
	public CategoriaDto buscarCategoria(String id);
	public boolean modificarCategoria(String idCategoria, String nombre);
	public boolean agregarCategoria(String nombreCategoria);
	
}
