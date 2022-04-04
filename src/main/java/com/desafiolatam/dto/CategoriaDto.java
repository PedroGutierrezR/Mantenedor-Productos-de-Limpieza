package com.desafiolatam.dto;

import java.util.ArrayList;
import java.util.List;

import com.desafiolatam.dao.modelo.Categoria;

public class CategoriaDto {
	
	private List<Categoria> listaCategorias;
	
	public CategoriaDto() {
		this.setListaCategorias(new ArrayList<Categoria>());
	}

	public List<Categoria> getListaCategorias() {
		return listaCategorias;
	}

	public void setListaCategorias(List<Categoria> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}
	
	
	
}
