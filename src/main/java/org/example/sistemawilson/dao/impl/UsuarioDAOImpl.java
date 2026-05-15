package org.example.sistemawilson.dao.impl;

import org.example.sistemawilson.dao.UsuarioDAO;
import org.example.sistemawilson.model.Rol;
import org.example.sistemawilson.model.Usuario;
import org.example.sistemawilson.util.MySQLConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

   Connection cn = null;
   PreparedStatement psm = null;
   ResultSet rs = null;
   Usuario user = new Usuario();
   ArrayList<Usuario> lista = new ArrayList<>();

    @Override
    public boolean registrarUsuario(Usuario usuario) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "INSERT INTO usuario (nombres, apellidos, dni, celular, username, email, clave, id_rol, estado) VALUES (?, ?, ?, ?, ?, ? ?, ?, ?)";
            psm = cn.prepareStatement(sql);
            psm.setString(1, usuario.getNombres());
            psm.setString(2, usuario.getApellidos());
            psm.setString(3, usuario.getUsername());
            psm.setString(4, usuario.getDni());
            psm.setString(5, usuario.getCelular());
            psm.setString(6, usuario.getEmail());
            psm.setString(7, usuario.getClave());
            psm.setInt(8, usuario.getRol().getIdRol());
            psm.setInt(9, usuario.getEstado());
            psm.executeUpdate();
            return true;
        } catch (Exception e){
            System.out.println("Error al validar" + e.getMessage());
            return false;
        } finally {
            try {
                if (cn != null) MySQLConexion.closeConexion(cn);
                if (psm != null) psm.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean actualizarPerfilUsuario(int idUsuario, int idRol, int estado) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "UPDATE usuario SET nombres=?, apellidos=?, dni=?, celular=?, username= ?, email=?, clave=?, id_rol=?, estado=? WHERE id_usuario=?";
            psm = cn.prepareStatement(sql);
            psm.setString(1, user.getNombres());
            psm.setString(2, user.getApellidos());
            psm.setString(3, user.getUsername());
            psm.setString(4, user.getDni());
            psm.setString(5, user.getCelular());
            psm.setString(6, user.getEmail());
            psm.setString(7, user.getClave());
            psm.setInt(8, user.getRol().getIdRol());
            psm.setInt(9, user.getEstado());
            psm.executeUpdate();
            System.out.println("Se actualizo Correctamente");
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar" + e.getMessage());
            return false;
        } finally {
            try {
                if (cn!= null) MySQLConexion.closeConexion(cn);
                if (psm!= null) psm.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean eliminarUsuario(Integer idUsuario) {
        return false;
    }

    @Override
    public List<Usuario> listarUsuario() {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "SELECT u.id_usuario, u.nombres, u.apellidos, u.dni, u.celular, u.username, u.email, u.clave, r.nombre_rol , u.estado, u.fecha_creacion " +
                    "FROM usuario AS u" +
                    "INNER JOIN rol AS r" +
                    "ON u.id_rol = r.id_rol";
            psm = cn.prepareStatement(sql);
            rs = psm.executeQuery();
            while (rs.next()) {
                user.setIdUsuario(rs.getInt(1));
                user.setNombres(rs.getString(2));
                user.setApellidos(rs.getString(3));
                user.setDni(rs.getString(4));
                user.setCelular(rs.getString(5));
                user.setUsername(rs.getString(6));
                user.setEmail(rs.getString(7));
                user.setClave(rs.getString(8));
                Rol rol = new Rol();
                user.getRol().setNombreRol(rs.getString(9));
                user.setRol(rol);
                user.setEstado(rs.getInt(10));
                user.setFecha_creacion(rs.getTimestamp(11).toLocalDateTime());
                lista.add(user);
            }
        } catch (Exception e) {
            System.out.println("Error al listar" + e.getMessage());
        } finally {
            try {
                if (cn != null) MySQLConexion.closeConexion(cn);
                if (psm != null) psm.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return  lista;
    }

    @Override
    public List<Usuario> buscarPorDni(String dniUsuario) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "SELECT u.id_usuario, u.nombres, u.apellidos, u.dni, u.celular, u.username, u.email, u.clave, r.nombre_rol , u.estado, u.fecha_creacion " +
                    "FROM usuario AS u" +
                    "INNER JOIN rol AS r" +
                    "ON u.id_rol = r.id_rol WHERE u.dni=?";
            psm = cn.prepareStatement(sql);
            rs = psm.executeQuery();
            while (rs.next()) {
                user.setIdUsuario(rs.getInt(1));
                user.setNombres(rs.getString(2));
                user.setApellidos(rs.getString(3));
                user.setDni(rs.getString(4));
                user.setCelular(rs.getString(5));
                user.setUsername(rs.getString(6));
                user.setEmail(rs.getString(7));
                user.setClave(rs.getString(8));
                Rol rol = new Rol();
                user.getRol().setNombreRol(rs.getString(9));
                user.setRol(rol);
                user.setEstado(rs.getInt(10));
                user.setFecha_creacion(rs.getTimestamp(11).toLocalDateTime());
                lista.add(user);
            }
        } catch (Exception e) {
            System.out.println("Error al listar" + e.getMessage());
        } finally {
            try {
                if (cn != null) MySQLConexion.closeConexion(cn);
                if (psm != null) psm.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return  lista;
    }

    @Override
    public List<Usuario> buscarPorId(int idUsuario) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "SELECT u.id_usuario, u.nombre, u.apellido, u.dni, u.celular, u.username, u.email, u.clave, r.nombre_rol , u.estado, u.fecha_creacion " +
                    "FROM usuario AS u" +
                    "INNER JOIN rol AS r" +
                    "ON u.id_rol = r.id_rol WHERE u.id_usuario=?";
            psm = cn.prepareStatement(sql);
            rs = psm.executeQuery();
            while (rs.next()) {
                user.setIdUsuario(rs.getInt(1));
                user.setNombres(rs.getString(2));
                user.setApellidos(rs.getString(3));
                user.setDni(rs.getString(4));
                user.setCelular(rs.getString(5));
                user.setUsername(rs.getString(6));
                user.setEmail(rs.getString(7));
                user.setClave(rs.getString(8));
                Rol rol = new Rol();
                user.getRol().setNombreRol(rs.getString(9));
                user.setRol(rol);
                user.setEstado(rs.getInt(10));
                user.setFecha_creacion(rs.getTimestamp(11).toLocalDateTime());
                lista.add(user);
            }
        } catch (Exception e) {
            System.out.println("Error al listar" + e.getMessage());
        } finally {
            try {
                if (cn != null) MySQLConexion.closeConexion(cn);
                if (psm != null) psm.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return  lista;
    }
}
