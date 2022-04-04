package com.desafiolatam.dto;

import java.util.ArrayList;
import java.util.List;

import com.desafiolatam.dao.modelo.Producto;

public class ProductoDto {

	private List<Producto> listaProductos;
	
	public ProductoDto() {
		this.listaProductos = new ArrayList<Producto>();
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

}
