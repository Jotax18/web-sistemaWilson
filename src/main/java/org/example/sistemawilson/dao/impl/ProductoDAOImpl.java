package org.example.sistemawilson.dao.impl;

import org.example.sistemawilson.dao.ProductoDAO;
import org.example.sistemawilson.model.Categoria;
import org.example.sistemawilson.model.Marca;
import org.example.sistemawilson.model.Producto;
import org.example.sistemawilson.util.MySQLConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {

    Connection cn = null;
    PreparedStatement psm = null;
    ResultSet rs = null;

    @Override
    public List<Producto> listarProducto() {
        ArrayList<Producto> lista = new ArrayList<>();
        try {
            cn = MySQLConexion.getConnection();
            String sql = "SELECT p.id_producto, p.codigo_sku, p.nombre AS nombre_producto, " +
                    "m.id_marca, m.nombre AS nombre_marca, p.modelo, " +
                    "c.id_categoria, c.nombre AS nombre_categoria, " +
                    "p.descripcion, p.precio_compra, p.precio_venta, " +
                    "p.stock_actual, p.stock_minimo, p.estado, p.fecha_creacion " +
                    "FROM producto AS p " +
                    "INNER JOIN marca_producto AS m ON p.id_marca = m.id_marca " +
                    "INNER JOIN categoria AS c ON p.id_categoria = c.id_categoria " +
                    "WHERE p.estado = 1";
            psm = cn.prepareStatement(sql);
            rs = psm.executeQuery();

            while (rs.next()){
                Producto prod = new Producto();
                prod.setIdProducto(rs.getInt("id_producto"));
                prod.setCodigoSku(rs.getString("codigo_sku"));
                prod.setNombre(rs.getString("nombre_producto"));

                Marca marca = new Marca();
                marca.setIdMarca(rs.getInt("id_marca"));
                marca.setNombre(rs.getString("nombre_marca"));
                prod.setMarca(marca);

                prod.setModelo(rs.getString("modelo"));

                Categoria cat = new Categoria();
                cat.setIdCategoria(rs.getInt("id_categoria"));
                cat.setNombre(rs.getString("nombre_categoria"));
                prod.setCategoria(cat);

                prod.setDescripcion(rs.getString("descripcion"));
                prod.setPrecioCompra(rs.getDouble("precio_compra"));
                prod.setPrecioVenta(rs.getDouble("precio_venta"));
                prod.setStockActual(rs.getInt("stock_actual"));
                prod.setStockMinimo(rs.getInt("stock_minimo"));
                prod.setEstado(rs.getInt("estado"));
                prod.setFechaCreacion(rs.getTimestamp("fecha_creacion").toString());

                lista.add(prod);
            }
        } catch (Exception e) {
            System.out.println("Error al listar: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (psm != null) psm.close();
                if (cn != null) MySQLConexion.closeConexion(cn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    @Override
    public boolean registrarProducto(Producto producto) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "INSERT INTO producto (codigo_sku, nombre, id_marca, modelo, id_categoria, descripcion, precio_compra, precio_venta, stock_actual, stock_minimo) VALUES (?,?,?,?,?,?,?,?,?,?)";
            psm = cn.prepareStatement(sql);
            psm.setString(1, producto.getCodigoSku());
            psm.setString(2, producto.getNombre());
            psm.setInt(3, producto.getMarca().getIdMarca());
            psm.setString(4, producto.getModelo());
            psm.setInt(5, producto.getCategoria().getIdCategoria());
            psm.setString(6, producto.getDescripcion());
            psm.setDouble(7, producto.getPrecioCompra());
            psm.setDouble(8, producto.getPrecioVenta());
            psm.setInt(9, producto.getStockActual());
            psm.setInt(10, producto.getStockMinimo());

            psm.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al registrar: " + e.getMessage() );
            return false;
        } finally {
            try {
                if (psm != null) psm.close();
                if (cn != null) MySQLConexion.closeConexion(cn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean actualizarProducto(Producto producto) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "UPDATE producto SET codigo_sku=?, nombre=?, id_marca=?, modelo=?, id_categoria=?, descripcion=?, precio_compra=?, precio_venta=?, stock_actual=?, stock_minimo=? WHERE id_producto=?";
            psm = cn.prepareStatement(sql);
            psm.setString(1, producto.getCodigoSku());
            psm.setString(2, producto.getNombre());
            psm.setInt(3, producto.getMarca().getIdMarca());
            psm.setString(4, producto.getModelo());
            psm.setInt(5, producto.getCategoria().getIdCategoria());
            psm.setString(6, producto.getDescripcion());
            psm.setDouble(7, producto.getPrecioCompra());
            psm.setDouble(8, producto.getPrecioVenta());
            psm.setInt(9, producto.getStockActual());
            psm.setInt(10, producto.getStockMinimo());
            psm.setInt(11, producto.getIdProducto());

            psm.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar: " + e.getMessage() );
            return false;
        } finally {
            try {
                if (psm != null) psm.close();
                if (cn != null) MySQLConexion.closeConexion(cn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean actualizarEstadoProducto(int estado, int idProducto) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "UPDATE producto SET estado = ? WHERE id_producto = ?";
            psm = cn.prepareStatement(sql);
            psm.setInt(1, estado);
            psm.setInt(2, idProducto);

            psm.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar estado: " + e.getMessage() );
            return false;
        } finally {
            try {
                if (psm != null) psm.close();
                if (cn != null) MySQLConexion.closeConexion(cn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Producto> buscarProductoGeneral(String terminoBusqueda) {
        ArrayList<Producto> lista = new ArrayList<>();
        try {
            cn = MySQLConexion.getConnection();
            String sql = "SELECT p.id_producto, p.codigo_sku, p.nombre AS nombre_producto, " +
                    "m.id_marca, m.nombre AS nombre_marca, p.modelo, " +
                    "c.id_categoria, c.nombre AS nombre_categoria, " +
                    "p.descripcion, p.precio_compra, p.precio_venta, " +
                    "p.stock_actual, p.stock_minimo, p.estado, p.fecha_creacion " +
                    "FROM producto AS p " +
                    "INNER JOIN marca_producto AS m ON p.id_marca = m.id_marca " +
                    "INNER JOIN categoria AS c ON p.id_categoria = c.id_categoria " +
                    "WHERE p.estado = 1 AND (p.nombre LIKE ? OR p.codigo_sku = ?)";
            psm = cn.prepareStatement(sql);
            psm.setString(1, "%" + terminoBusqueda + "%");
            psm.setString(2, terminoBusqueda);
            rs = psm.executeQuery();

            while (rs.next()){
                Producto prod = new Producto();
                prod.setIdProducto(rs.getInt("id_producto"));
                prod.setCodigoSku(rs.getString("codigo_sku"));
                prod.setNombre(rs.getString("nombre_producto"));

                Marca marca = new Marca();
                marca.setIdMarca(rs.getInt("id_marca"));
                marca.setNombre(rs.getString("nombre_marca"));
                prod.setMarca(marca);

                prod.setModelo(rs.getString("modelo"));

                Categoria cat = new Categoria();
                cat.setIdCategoria(rs.getInt("id_categoria"));
                cat.setNombre(rs.getString("nombre_categoria"));
                prod.setCategoria(cat);

                prod.setDescripcion(rs.getString("descripcion"));
                prod.setPrecioCompra(rs.getDouble("precio_compra"));
                prod.setPrecioVenta(rs.getDouble("precio_venta"));
                prod.setStockActual(rs.getInt("stock_actual"));
                prod.setStockMinimo(rs.getInt("stock_minimo"));
                prod.setEstado(rs.getInt("estado"));
                prod.setFechaCreacion(rs.getTimestamp("fecha_creacion").toString());

                lista.add(prod);
            }
        } catch (Exception e) {
            System.out.println("Error al buscar general: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (psm != null) psm.close();
                if (cn != null) MySQLConexion.closeConexion(cn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    @Override
    public Producto buscarProductoId(int idProducto) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "SELECT p.id_producto, p.codigo_sku, p.nombre AS nombre_producto, " +
                    "m.id_marca, m.nombre AS nombre_marca, p.modelo, " +
                    "c.id_categoria, c.nombre AS nombre_categoria, " +
                    "p.descripcion, p.precio_compra, p.precio_venta, " +
                    "p.stock_actual, p.stock_minimo, p.estado, p.fecha_creacion " +
                    "FROM producto AS p " +
                    "INNER JOIN marca_producto AS m ON p.id_marca = m.id_marca " +
                    "INNER JOIN categoria AS c ON p.id_categoria = c.id_categoria " +
                    "WHERE p.id_producto = ?";
            psm = cn.prepareStatement(sql);
            psm.setInt(1, idProducto);
            rs = psm.executeQuery();

            while (rs.next()){
                Producto prod = new Producto();
                prod.setIdProducto(rs.getInt("id_producto"));
                prod.setCodigoSku(rs.getString("codigo_sku"));
                prod.setNombre(rs.getString("nombre_producto"));

                Marca marca = new Marca();
                marca.setIdMarca(rs.getInt("id_marca"));
                marca.setNombre(rs.getString("nombre_marca"));
                prod.setMarca(marca);

                prod.setModelo(rs.getString("modelo"));

                Categoria cat = new Categoria();
                cat.setIdCategoria(rs.getInt("id_categoria"));
                cat.setNombre(rs.getString("nombre_categoria"));
                prod.setCategoria(cat);

                prod.setDescripcion(rs.getString("descripcion"));
                prod.setPrecioCompra(rs.getDouble("precio_compra"));
                prod.setPrecioVenta(rs.getDouble("precio_venta"));
                prod.setStockActual(rs.getInt("stock_actual"));
                prod.setStockMinimo(rs.getInt("stock_minimo"));
                prod.setEstado(rs.getInt("estado"));
                prod.setFechaCreacion(rs.getTimestamp("fecha_creacion").toString());

                return prod;
            }
        } catch (Exception e) {
            System.out.println("Error al buscar por ID: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (psm != null) psm.close();
                if (cn != null) MySQLConexion.closeConexion(cn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}