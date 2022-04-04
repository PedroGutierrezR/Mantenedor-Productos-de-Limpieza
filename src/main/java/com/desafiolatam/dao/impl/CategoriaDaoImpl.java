package com.desafiolatam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.desafiolatam.dao.CategoriaDao;
import com.desafiolatam.dao.modelo.Categoria;
import com.desafiolatam.dao.modelo.Producto;
import com.desafiolatam.dao.utils.ConnectionUtil;

public class CategoriaDaoImpl implements CategoriaDao {

	private ConnectionUtil connectionUtil = new ConnectionUtil();

	@Override
	public List<Categoria> listarCategorias() {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT c.id_categoria, c.nombre_categoria, p.id_producto, p.nombre_producto, p.precio_producto, p.descripcion_producto, p.id_categoria FROM categoria c "
				+ "FULL JOIN producto p ON  c.id_categoria = p.id_categoria " + "ORDER BY c.id_categoria";

		List<Producto> listaProductos = new ArrayList<Producto>();
		List<Categoria> listaCategorias = new ArrayList<Categoria>();
		
		try {
			conn = connectionUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			int idAnterior = 0;
			
			while (rs.next()) {
				
				if (idAnterior != rs.getInt("id_categoria")) {
					Categoria categoria = new Categoria();
					categoria.setIdCategoria(rs.getInt("id_categoria"));
					categoria.setNombreCategoria(rs.getString("nombre_categoria"));
					listaCategorias.add(categoria);
					idAnterior = rs.getInt("id_categoria");
				}

				Producto producto = new Producto();
				
				producto.setId(rs.getInt("id_producto"));
				producto.setNombre(rs.getString("nombre_producto"));
				producto.setPrecio(rs.getInt("precio_producto"));
				producto.setDescripcion(rs.getString("descripcion_producto"));
				producto.setCategoria(listaCategorias.get(idAnterior - 1));
				listaProductos.add(producto);
				listaCategorias.get(idAnterior - 1).setListaProductos(listaProductos);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCategorias;
	}
	
	@Override
	public List<Categoria> buscarCategoria(int id) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT c.id_categoria, c.nombre_categoria, p.id_producto, p.nombre_producto, p.precio_producto, p.descripcion_producto \r\n"
				+ "FROM categoria c \r\n" + "FULL JOIN producto p \r\n"
				+ "ON  c.id_categoria = p.id_categoria WHERE c.id_categoria = ?";

		Categoria categoria = new Categoria();
		List<Producto> listaProductos = new ArrayList<Producto>();
		List<Categoria> listaCategorias = new ArrayList<Categoria>();

		try {
			conn = connectionUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {

				if (categoria.equals(null)) {
					categoria.setIdCategoria(rs.getInt("id_categoria"));
					categoria.setNombreCategoria(rs.getString("nombre_categoria"));
				}

				Producto producto = new Producto();
				producto.setId(rs.getInt("id_producto"));
				producto.setNombre(rs.getString("nombre_producto"));
				producto.setPrecio(rs.getInt("precio_producto"));
				producto.setDescripcion(rs.getString("descripcion_producto"));
				categoria.setIdCategoria(rs.getInt("id_categoria"));
				categoria.setNombreCategoria(rs.getString("nombre_categoria"));
				producto.setCategoria(categoria);

				listaProductos.add(producto);

			}

			categoria.setListaProductos(listaProductos);
			listaCategorias.add(categoria);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCategorias;

	}

	@Override
	public boolean agregarCategoria(String nombreCategoria) {

		Connection conn = null;
		PreparedStatement ps = null;
		int ultimoId = getLastId();
		String sql = "INSERT INTO categoria(id_categoria, nombre_categoria) VALUES (? , ?)";

		try {
			conn = connectionUtil.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, (ultimoId + 1));
			ps.setString(2, nombreCategoria);

			int resultado = ps.executeUpdate();

			if (resultado == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	@Override
	public boolean modificarCategoria(Categoria categoria) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE categoria SET nombre_categoria = ? WHERE id_categoria = ?";

		try {
			conn = connectionUtil.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, categoria.getNombreCategoria());
			ps.setInt(2, categoria.getIdCategoria());

			int resultado = ps.executeUpdate();

			if (resultado == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public int getLastId() {

		Connection cn = null;
		int lastId = 0;

		try {
			cn = connectionUtil.getConnection();
			PreparedStatement pt = cn.prepareStatement("SELECT MAX(id_categoria) AS max FROM categoria");
			ResultSet rset = pt.executeQuery();

			if (rset.next()) {
				lastId = rset.getInt("max");
			}

			rset.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lastId;
	}
	
}
