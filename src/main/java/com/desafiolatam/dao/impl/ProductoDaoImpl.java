package com.desafiolatam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.desafiolatam.dao.ProductoDao;
import com.desafiolatam.dao.modelo.Categoria;
import com.desafiolatam.dao.modelo.Producto;
import com.desafiolatam.dao.utils.ConnectionUtil;

public class ProductoDaoImpl implements ProductoDao {

	private ConnectionUtil connectionUtil = new ConnectionUtil();

	@Override
	public List<Producto> buscarProducto(int id) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT p.id_producto, p.nombre_producto, p.precio_producto, p.descripcion_producto, c.id_categoria, c.nombre_categoria FROM producto p INNER JOIN categoria c ON p.id_producto = ?";
		Producto producto = new Producto();
		List<Producto> listaProductos = new ArrayList<Producto>();
		try {
			conn = connectionUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				Categoria categoria = new Categoria();
				producto.setId(rs.getInt("id_producto"));
				producto.setNombre(rs.getString("nombre_producto"));
				producto.setPrecio(rs.getInt("precio_producto"));
				producto.setDescripcion(rs.getString("descripcion_producto"));
				categoria.setIdCategoria(rs.getInt("id_categoria"));
				categoria.setNombreCategoria(rs.getString("nombre_categoria"));
				producto.setCategoria(categoria);
				listaProductos.add(producto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaProductos;
	}

	@Override
	public List<Producto> listarProductos() {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT p.id_producto, p.nombre_producto, p.precio_producto, p.descripcion_producto, c.id_categoria, c.nombre_categoria "
				+ "FROM producto p INNER JOIN categoria c ON p.id_categoria = c.id_categoria "
				+ "ORDER BY p.id_producto";
		List<Producto> listaProductos = new ArrayList<Producto>();

		try {
			conn = connectionUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {

				Producto producto = new Producto();
				Categoria categoria = new Categoria();

				producto.setId(rs.getInt("id_producto"));
				producto.setNombre(rs.getString("nombre_producto"));
				producto.setPrecio(rs.getInt("precio_producto"));
				producto.setDescripcion(rs.getString("descripcion_producto"));
				categoria.setIdCategoria(rs.getInt("id_categoria"));
				categoria.setNombreCategoria(rs.getString("nombre_categoria"));
				producto.setCategoria(categoria);
				listaProductos.add(producto);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaProductos;
	}

	@Override
	public boolean modificarProducto(Producto producto) {

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE producto SET nombre_producto = ?, precio_producto = ?, descripcion_producto = ?, id_categoria = ? WHERE id_producto = ?";

		try {
			conn = connectionUtil.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, producto.getNombre());
			ps.setInt(2, producto.getPrecio());
			ps.setString(3, producto.getDescripcion());
			ps.setInt(4, producto.getCategoria().getIdCategoria());
			ps.setInt(5, producto.getId());

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
	public boolean agregarProducto(Producto producto) {

		Connection conn = null;
		PreparedStatement ps = null;
		int ultimoId = getLastId();
		String sql = "INSERT INTO producto(id_producto, nombre_producto, precio_producto, descripcion_producto, id_categoria) VALUES (?, ? , ? , ? , ?)";

		try {
			conn = connectionUtil.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setInt(1, (ultimoId + 1));
			ps.setString(2, producto.getNombre());
			ps.setInt(3, producto.getPrecio());
			ps.setString(4, producto.getDescripcion());
			ps.setInt(5, producto.getCategoria().getIdCategoria());

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
	public boolean eliminarProducto(int id) {

		Connection conn = null;
		PreparedStatement ps = null;

		String sql = "DELETE FROM producto WHERE id_producto = ?";

		try {
			conn = connectionUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
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
			PreparedStatement pt = cn.prepareStatement("SELECT MAX(id_producto) AS max FROM producto");
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
