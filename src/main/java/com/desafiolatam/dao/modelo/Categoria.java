package com.desafiolatam.dao.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

	private int idCategoria;
	private String nombreCategoria;
	private List<Producto> listaProductos;
	
	public Categoria() {
		this.listaProductos = new ArrayList<Producto>();
	}
	
	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	
}
