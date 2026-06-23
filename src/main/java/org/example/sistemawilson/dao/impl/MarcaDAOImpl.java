package org.example.sistemawilson.dao.impl;

import org.example.sistemawilson.dao.MarcaDAO;
import org.example.sistemawilson.model.Categoria;
import org.example.sistemawilson.model.Marca;
import org.example.sistemawilson.util.MySQLConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarcaDAOImpl implements MarcaDAO {

    Connection cn;
    PreparedStatement psm;
    ResultSet rs;

    @Override
    public List<Marca> listaMarca() {
        List<Marca> lista = new ArrayList<>();
        try {
            cn = MySQLConexion.getConnection();
            String sql = "SELECT * FROM marca_producto WHERE estado = 1";
            psm = cn.prepareStatement(sql);
            rs = psm.executeQuery();
            while (rs.next()){
                Marca mar = new Marca();
                mar.setIdMarca(rs.getInt("id_marca"));
                mar.setNombre(rs.getString("nombre"));
                mar.setEstado(rs.getInt("estado"));
                lista.add(mar);
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
    public Marca buscarMarcaId(int idMarca) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "SELECT * FROM marca_producto WHERE id_marca = ?";
            psm = cn.prepareStatement(sql);
            psm.setInt(1, idMarca);
            rs = psm.executeQuery();
            if (rs.next()){
                Marca mar = new Marca();
                mar.setNombre(rs.getString("nombre"));
                mar.setEstado(rs.getInt("estado"));
                mar.setFechaCreacion(rs.getTimestamp("fecha_creacion").toString());
                return mar;
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar" + e.getMessage());
        } finally {
            try {
                if (cn!= null) MySQLConexion.closeConexion(cn);
                if (psm!= null) psm.close();
                if (rs!= null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean registrarMarca(Marca marca) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "INSERT INTO marca_producto (nombre) VALUES (?)";
            psm = cn.prepareStatement(sql);
            psm.setString(1, marca.getNombre());
            psm.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al registrar:" + e.getMessage());
            return false;
        } finally {
            try {
                if (cn!= null) MySQLConexion.closeConexion(cn);
                if (psm!= null) psm.close();
                if (rs!= null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean actualizarMarca(Marca marca) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "UPDATE marca_producto SET nombre=? WHERE id_marca=?";
            psm = cn.prepareStatement(sql);
            psm.setString(1, marca.getNombre());
            psm.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar:" + e.getMessage());
            return false;
        } finally {
            try {
                if (cn!= null) MySQLConexion.closeConexion(cn);
                if (psm!= null) psm.close();
                if (rs!= null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean actualizarEstadoMarca(int idMarca, int estado) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "UPDATE marca_producto SET estado=? WHERE id_marca=?";
            psm = cn.prepareStatement(sql);
            psm.setInt(1, estado);
            psm.setInt(2, idMarca);
            psm.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar estado:" + e.getMessage());
            return false;
        } finally {
            try {
                if (cn!= null) MySQLConexion.closeConexion(cn);
                if (psm!= null) psm.close();
                if (rs!= null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
