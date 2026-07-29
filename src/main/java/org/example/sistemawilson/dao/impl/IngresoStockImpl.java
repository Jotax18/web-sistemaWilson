package org.example.sistemawilson.dao.impl;

import org.example.sistemawilson.dao.IngresoStockDAO;
import org.example.sistemawilson.model.*;
import org.example.sistemawilson.util.MySQLConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                u.nombres,
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
            String sql = "INSERT INTO ingreso_stock (lote_stock, id_usuario) VALUES (?,?)";
            psm = cn.prepareStatement(sql);
            psm.setString(1, ingresoStock.getLoteStock());
            psm.setInt(2, ingresoStock.getUsuario().getIdUsuario());
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
    public boolean actualizarStock(IngresoStock actualizarStock) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "INSERT INTO ingreso_stock (lote_stock, id_usuario) VALUES (?,?)";
            psm = cn.prepareStatement(sql);
            psm.setString(1, actualizarStock.getLoteStock());
            psm.setInt(2, actualizarStock.getUsuario().getIdUsuario());
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
    public boolean actualizarEstadoStock(int estado, int idIngresoStock){
        try {
            cn = MySQLConexion.getConnection();
            String sql = "INSERT INTO ingreso_stock (lote_stock, id_usuario) VALUES (?,?)";
            psm = cn.prepareStatement(sql);
            psm.setInt(1, estado);
            psm.setInt(2, idIngresoStock);
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
