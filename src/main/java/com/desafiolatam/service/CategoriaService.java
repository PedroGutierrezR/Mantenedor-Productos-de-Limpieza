package com.desafiolatam.service;

import com.desafiolatam.dto.CategoriaDto;

public interface CategoriaService {

	public CategoriaDto listarTodosCategorias();
	public CategoriaDto buscarCategoria(String id);
	public boolean modificarCategoria(String idCategoria, String nombreCategoria);
	public boolean agregarCategoria(String nombreCategoria);
	
}
