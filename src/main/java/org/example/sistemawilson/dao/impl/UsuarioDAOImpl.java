package org.example.sistemawilson.dao.impl;

import org.example.sistemawilson.dao.UsuarioDAO;
import org.example.sistemawilson.model.Rol;
import org.example.sistemawilson.model.Usuario;
import org.example.sistemawilson.model.Utils;
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

    @Override
    public Usuario validarLogin(Usuario u) {
        Usuario usuario = null;
        try {
            cn = MySQLConexion.getConnection();
            String sql = "SELECT u.id_usuario, r.nombre_rol, u.username " +
                    "FROM usuario u " +
                    "INNER JOIN rol AS r ON u.id_rol = r.id_rol " +
                    "WHERE u.estado = 1 AND u.username = ? AND u.clave = ?";
            psm = cn.prepareStatement(sql);
            psm.setString(1, u.getUsername());
            psm.setString(2, u.getClave());
            rs = psm.executeQuery();
            if (rs.next()) {
                Usuario user = new Usuario();
                usuario = user;
                user.setIdUsuario(rs.getInt(1));
                Rol rol = new Rol();
                rol.setNombreRol(rs.getString(2));
                user.setRol(rol);
                user.setUsername(rs.getString(3));
                user.setEstado(Utils.ESTADO_ACTIVO);
            }
        } catch (Exception e) {
            System.out.println("Error al validar el Login: " + e.getMessage());
        } finally {
            try {
                if (cn != null) MySQLConexion.closeConexion(cn);
                if (psm != null) psm.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return usuario;
    }

    @Override
    public boolean registrarUsuario(Usuario usuario) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "INSERT INTO usuario (nombres, apellidos, dni, celular, username, email, clave, id_rol, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            psm = cn.prepareStatement(sql);
            psm.setString(1, usuario.getNombres());
            psm.setString(2, usuario.getApellidos());
            psm.setString(3, usuario.getDni());
            psm.setString(4, usuario.getCelular());
            psm.setString(5, usuario.getUsername());
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
    public boolean actualizarPerfilUsuario(Usuario usuario) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "UPDATE usuario SET nombres=?, apellidos=?, dni=?, celular=?, username= ?, email=?, clave=?, id_rol=? WHERE id_usuario=?";
            psm = cn.prepareStatement(sql);
            psm.setString(1, usuario.getNombres());
            psm.setString(2, usuario.getApellidos());
            psm.setString(3, usuario.getDni());
            psm.setString(4, usuario.getCelular());
            psm.setString(5, usuario.getUsername());
            psm.setString(6, usuario.getEmail());
            psm.setString(7, usuario.getClave());
            psm.setInt(8, usuario.getRol().getIdRol());
            psm.setInt(9, usuario.getIdUsuario());
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
    public boolean actualizarEstadoUsuario(int idUsuario, int estado) {
        try {
            if (estado != 0 && estado != 1){
                return false;
            }
            cn = MySQLConexion.getConnection();
            String sql = "UPDATE usuario SET estado = ? WHERE id_usuario = ?";
            psm = cn.prepareStatement(sql);
            psm.setInt(1, estado);
            psm.setInt(2, idUsuario);
            psm.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar estado" + e.getMessage());
        } finally {
            try {
                if (cn!= null) MySQLConexion.closeConexion(cn);
                if (psm!= null) psm.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean coompararPassword(String clave) {

        return false;
    }

    @Override
    public List<Usuario> listarUsuario() {
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            cn = MySQLConexion.getConnection();
            String sql = "SELECT u.id_usuario, u.nombres, u.apellidos, u.dni, u.celular, u.username, u.email, u.clave, r.nombre_rol , u.estado, u.fecha_creacion " +
                    "FROM usuario AS u " +
                    "INNER JOIN rol AS r " +
                    "ON u.id_rol = r.id_rol";
            psm = cn.prepareStatement(sql);
            rs = psm.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setIdUsuario(rs.getInt(1));
                user.setNombres(rs.getString(2));
                user.setApellidos(rs.getString(3));
                user.setDni(rs.getString(4));
                user.setCelular(rs.getString(5));
                user.setUsername(rs.getString(6));
                user.setEmail(rs.getString(7));
                user.setClave(rs.getString(8));
                Rol rol = new Rol();
                rol.setNombreRol(rs.getString(9));
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
                if (rs != null) rs.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return  lista;
    }

    @Override
    public Usuario buscarPorDni(String dniUsuario) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "SELECT u.id_usuario, u.nombres, u.apellidos, u.dni, u.celular, u.username, u.email, u.clave, r.nombre_rol , u.estado, u.fecha_creacion " +
                    "FROM usuario AS u " +
                    "INNER JOIN rol AS r " +
                    "ON u.id_rol = r.id_rol WHERE u.dni=?";
            psm = cn.prepareStatement(sql);
            psm.setString(1, dniUsuario);
            rs = psm.executeQuery();
            if (rs.next()){
                Usuario user = new Usuario();
                user.setIdUsuario(rs.getInt(1));
                user.setNombres(rs.getString(2));
                user.setApellidos(rs.getString(3));
                user.setDni(rs.getString(4));
                user.setCelular(rs.getString(5));
                user.setUsername(rs.getString(6));
                user.setEmail(rs.getString(7));
                user.setClave(rs.getString(8));
                Rol rol = new Rol();
                rol.setNombreRol(rs.getString(9));
                user.setRol(rol);
                user.setEstado(rs.getInt(10));
                user.setFecha_creacion(rs.getTimestamp(11).toLocalDateTime());
                return user;
            }
        } catch (Exception e) {
            System.out.println("Error al listar" + e.getMessage());
        } finally {
            try {
                if (cn != null) MySQLConexion.closeConexion(cn);
                if (psm != null) psm.close();
                if (rs != null) rs.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Usuario buscarPorId(int idUsuario) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "SELECT u.id_usuario, u.nombres, u.apellidos, u.dni, u.celular, u.username, u.email, u.clave, r.nombre_rol , u.estado, u.fecha_creacion " +
                    "FROM usuario AS u " +
                    "INNER JOIN rol AS r " +
                    "ON u.id_rol = r.id_rol WHERE u.id_usuario=?";
            psm = cn.prepareStatement(sql);
            psm.setInt(1, idUsuario);
            rs = psm.executeQuery();
            if (rs.next()){
                Usuario user = new Usuario();
                user.setIdUsuario(rs.getInt(1));
                user.setNombres(rs.getString(2));
                user.setApellidos(rs.getString(3));
                user.setDni(rs.getString(4));
                user.setCelular(rs.getString(5));
                user.setUsername(rs.getString(6));
                user.setEmail(rs.getString(7));
                user.setClave(rs.getString(8));
                Rol rol = new Rol();
                rol.setNombreRol(rs.getString(9));
                user.setRol(rol);
                user.setEstado(rs.getInt(10));
                user.setFecha_creacion(rs.getTimestamp(11).toLocalDateTime());
                return user;
            }
        } catch (Exception e) {
            System.out.println("Error al listar" + e.getMessage());
        } finally {
            try {
                if (cn != null) MySQLConexion.closeConexion(cn);
                if (psm != null) psm.close();
                if (rs != null) rs.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

}
