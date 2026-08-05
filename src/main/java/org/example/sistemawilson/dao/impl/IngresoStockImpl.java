package org.example.sistemawilson.dao.impl;

import org.example.sistemawilson.dao.IngresoStockDAO;
import org.example.sistemawilson.model.*;
import org.example.sistemawilson.util.MySQLConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngresoStockImpl implements IngresoStockDAO {

    Connection cn = null;
    PreparedStatement psm = null;
    ResultSet rs = null;

    @Override
    public List<IngresoStock> listarIngresoStock() {
        List<IngresoStock> lista = new ArrayList<>();
        try {
            cn = MySQLConexion.getConnection();
            String sql = """
            SELECT
                i.id_ingreso_stock,
                i.lote_stock,
                i.fecha_ingreso,
                u.id_usuario,
                u.nombres
            FROM ingreso_stock i
            INNER JOIN usuario u
            ON i.id_usuario = u.id_usuario
            WHERE i.estado = 1
            ORDER BY i.id_ingreso_stock DESC
            """;
            psm = cn.prepareStatement(sql);
            rs = psm.executeQuery();
            while (rs.next()){
                IngresoStock ingreso = new IngresoStock();
                ingreso.setIdIngresoStock(rs.getInt("id_ingreso_stock"));
                ingreso.setLoteStock(rs.getString("lote_stock"));
                Usuario usu = new Usuario();
                usu.setIdUsuario(rs.getInt("id_usuario"));
                ingreso.setUsuario(usu);
                ingreso.setFechaIngreso(rs.getTimestamp("fecha_ingreso").toString());
                lista.add(ingreso);
            }
        } catch (Exception e) {
            System.out.println("Error al listar:" + e.getMessage());
        } finally {
            try {
                if (cn!= null) MySQLConexion.closeConexion(cn);
                if (psm!= null) psm.close();
                if (rs!= null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    @Override
    public boolean registrarStock(IngresoStock ingresoStock) {
        try {
            cn = MySQLConexion.getConnection();
            cn.setAutoCommit(false);
            String sql = "INSERT INTO ingreso_stock (lote_stock, id_usuario) VALUES (?,?)";
            // Permite recuperar el ID autogenerado por la base de datos
            // después de insertar la cabecera del ingreso de stock.
            psm = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psm.setString(1, ingresoStock.getLoteStock());
            psm.setInt(2, ingresoStock.getUsuario().getIdUsuario());
            psm.executeUpdate();
            rs = psm.getGeneratedKeys();
            int idIngreso = 0;
            if (rs.next()){
                idIngreso = rs.getInt(1);
            }
            if(idIngreso == 0){
                throw new SQLException("No se pudo obtener el ID del ingreso.");
            }
            for(DetalleIngresoStock d : ingresoStock.getListaDetalle()){
                String sql1 = "INSERT INTO detalle_ingreso_stock (id_ingreso_stock, id_producto, cantidad, costo_unitario, subtotal) VALUES (?,?,?,?,?)";
                PreparedStatement psmDetalle = cn.prepareStatement(sql1);
                psmDetalle.setInt(1, idIngreso);
                psmDetalle.setInt(2, d.getProducto().getIdProducto());
                psmDetalle.setInt(3, d.getCantidad());
                psmDetalle.setDouble(4, d.getPrecioUnitario());
                psmDetalle.setDouble(5, d.getSubtotal());
                psmDetalle.executeUpdate();
                String sql2 = "UPDATE producto SET stock_actual = stock_actual + ? WHERE id_producto = ?";
                PreparedStatement psmProducto = cn.prepareStatement(sql2);
                psmProducto.setInt(1, d.getCantidad());
                psmProducto.setInt(2, d.getProducto().getIdProducto());
                psmProducto.executeUpdate();
                psmDetalle.close();
                psmProducto.close();
            }
            cn.commit();
            return true;
        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            System.out.println("Error al ingresar stock; transacción revertida");
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (cn != null) {
                    cn.setAutoCommit(true);
                    MySQLConexion.closeConexion(cn);
                }

                if (psm != null) psm.close();
                if (rs != null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean anularIngresoStock (int idIngresoStock){
        try {
            cn = MySQLConexion.getConnection();
            cn.setAutoCommit(false);
            String sql = "SELECT id_ingreso_stock FROM ingreso_stock WHERE id_ingreso_stock = ? AND estado = 1";
            psm = cn.prepareStatement(sql);
            psm.setInt(1, idIngresoStock);
            rs = psm.executeQuery();
            if (!rs.next()){
                throw new SQLException("El ingreso no existe o ya fue anulado");
            }
            String selectDetalleIngreso = "SELECT id_producto, cantidad FROM detalle_ingreso_stock WHERE id_ingreso_stock = ?";
            psm = cn.prepareStatement(selectDetalleIngreso);
            psm.setInt(1, idIngresoStock);
            rs = psm.executeQuery();
            while (rs.next()){
                int idProducto = rs.getInt("id_producto");
                int cantidad = rs.getInt("cantidad");

                String sql1 = "UPDATE producto SET stock_actual = stock_actual - ? WHERE id_producto = ?";
                PreparedStatement psmUpdate = cn.prepareStatement(sql1);
                psmUpdate.setInt(1, cantidad);
                psmUpdate.setInt(2, idProducto);
                psmUpdate.executeUpdate();
                psmUpdate.close();
            }
            String sql2 = "UPDATE ingreso_stock SET estado = 0 WHERE id_ingreso_stock = ?";
            psm = cn.prepareStatement(sql2);
            psm.setInt(1, idIngresoStock);
            psm.executeUpdate();
            cn.commit();
            return true;
        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Error al anular ingreso Stock, transaccion revertida");
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.setAutoCommit(true);
                    MySQLConexion.closeConexion(cn);
                }

                if (psm != null) psm.close();
                if (rs != null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<IngresoStock> buscarIngresoStock(String terminoBusqueda) {
        ArrayList<IngresoStock> lista = new ArrayList<>();
       try {
           cn = MySQLConexion.getConnection();
           String sql = """
            SELECT
                i.id_ingreso_stock,
                i.lote_stock,
                i.fecha_ingreso,
                u.id_usuario,
                u.nombres,
            FROM ingreso_stock i
            INNER JOIN usuario u
            ON i.id_usuario = u.id_usuario
            WHERE i.estado = 1 AND (i.lote_stock LIKE ?)
            ORDER BY i.id_ingreso_stock DESC
            """;
           psm = cn.prepareStatement(sql);
           psm.setString(1, "%" + terminoBusqueda + "%");
           rs = psm.executeQuery();
           while (rs.next()){
                IngresoStock ingreso = new IngresoStock();
                ingreso.setIdIngresoStock(rs.getInt("id_ingreso_stock"));
                ingreso.setFechaIngreso(rs.getString("lote_stock"));

                Usuario usu = new Usuario();
                usu.setNombres(rs.getString("id_usuario"));
                ingreso.setUsuario(usu);

                ingreso.setFechaIngreso(rs.getString("fecha_ingreso"));
                ingreso.setEstado(rs.getInt("estado"));
                lista.add(ingreso);
           }
       } catch (Exception e) {
           System.out.println("Error al bucar por termino de busqueda: "+ e.getMessage());
       } finally {
           try {
               if (rs != null) rs.close();
               if (psm != null) psm.close();
               if (cn != null) MySQLConexion.closeConexion(cn);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       return  lista;
    }

    @Override
    public IngresoStock buscarIngresoStockPorId(int idIngresoStock) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "SELECT * FROM ingreso_stock WHERE id_ingreso_stock = ? AND estado = 1";
            psm = cn.prepareStatement(sql);
            psm.setInt(1, idIngresoStock);
            rs = psm.executeQuery();

            while (rs.next()){
                IngresoStock ingreso = new IngresoStock();
                ingreso.setIdIngresoStock(rs.getInt("id_ingreso_stock"));
                ingreso.setFechaIngreso(rs.getString("lote_stock"));

                Usuario usu = new Usuario();
                usu.setNombres(rs.getString("id_usuario"));
                ingreso.setUsuario(usu);

                ingreso.setFechaIngreso(rs.getString("fecha_ingreso"));
                ingreso.setEstado(rs.getInt("estado"));

                return ingreso;
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
