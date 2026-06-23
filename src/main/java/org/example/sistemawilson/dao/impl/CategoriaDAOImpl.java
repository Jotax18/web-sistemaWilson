package org.example.sistemawilson.dao.impl;

import org.example.sistemawilson.dao.CategoriaDAO;
import org.example.sistemawilson.model.Categoria;
import org.example.sistemawilson.model.Marca;
import org.example.sistemawilson.util.MySQLConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImpl implements CategoriaDAO {

    Connection cn = null;
    PreparedStatement psm = null;
    ResultSet rs = null;

    @Override
    public List<Categoria> listaCategoria() {
        List<Categoria> lista = new ArrayList<>();
        try {
            cn = MySQLConexion.getConnection();
            String sql = "SELECT * FROM categoria WHERE estado = 1";
            psm = cn.prepareStatement(sql);
            rs = psm.executeQuery();
            while (rs.next()){
                Categoria cat = new Categoria();
                cat.setIdCategoria(rs.getInt("id_categoria"));
                cat.setNombre(rs.getString("nombre"));
                cat.setDescripcion(rs.getString("descripcion"));
                cat.setEstado(rs.getInt("estado"));
                cat.setFechaCreacion(rs.getTimestamp("fecha_creacion").toString());
                lista.add(cat);
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
    public Categoria buscarCategoriaId(int idCategoria) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "SELECT * FROM categoria WHERE id_categoria = ?";
            psm = cn.prepareStatement(sql);
            psm.setInt(1, idCategoria);
            rs = psm.executeQuery();
            if (rs.next()){
                Categoria cat = new Categoria();
                cat.setNombre(rs.getString("nombre"));
                cat.setDescripcion(rs.getString("descripcion"));
                cat.setEstado(rs.getInt("estado"));
                cat.setFechaCreacion(rs.getTimestamp("fecha_creacion").toString());
                return cat;
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
    public boolean registrarCategoria(Categoria categoria) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "INSERT INTO categoria (nombre, descripcion) VALUES (?,?)";
            psm = cn.prepareStatement(sql);
            psm.setString(1, categoria.getNombre());
            psm.setString(2, categoria.getDescripcion());
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
    public boolean actualizarCategoria(Categoria categoria) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "UPDATE categoria SET nombre=?, descripcion=? WHERE id_categoria=?";
            psm = cn.prepareStatement(sql);
            psm.setString(1, categoria.getNombre());
            psm.setString(2, categoria.getDescripcion());
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
    public boolean actualizarEstadoCategoria(int idCategoria, int estado) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "UPDATE categoria SET estado=? WHERE id_categoria=?";
            psm = cn.prepareStatement(sql);
            psm.setInt(1, estado);
            psm.setInt(2, idCategoria);
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
