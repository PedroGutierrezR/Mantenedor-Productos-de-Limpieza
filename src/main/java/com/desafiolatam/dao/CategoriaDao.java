package com.desafiolatam.dao;

import java.util.List;

import com.desafiolatam.dao.modelo.Categoria;

public interface CategoriaDao {

	public List<Categoria> buscarCategoria(int id);
	public List<Categoria> listarCategorias();
	public boolean agregarCategoria(String nombreCategoria);
	public boolean modificarCategoria(Categoria categoria);
	
}
