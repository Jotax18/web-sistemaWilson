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

    @Override
    public boolean registrarUsuario(Usuario usuario) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "INSERT INTO usuario (nombre, apellido, username, email, clave, id_rol, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
            psm = cn.prepareStatement(sql);
            psm.setString(1, usuario.getNombre());
            psm.setString(2, usuario.getApellido());
            psm.setString(3, usuario.getUsername());
            psm.setString(4, usuario.getEmail());
            psm.setString(5, usuario.getClave());
            psm.setInt(6, usuario.getRol().getIdRol());
            psm.setInt(7, usuario.getEstado());
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
            String sql = "UPDATE usuario SET nombre=?, apellido=?, username= ?, email=?, clave=?, id_rol=?, estado=? WHERE id_usuario=?";
            psm = cn.prepareStatement(sql);
            psm.setString(1, user.getNombre());
            psm.setString(2, user.getApellido());
            psm.setString(3, user.getUsername());
            psm.setString(4, user.getEmail());
            psm.setString(5, user.getClave());
            psm.setInt(6, user.getRol().getIdRol());
            psm.setInt(7, user.getEstado());
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
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            cn = MySQLConexion.getConnection();
            String sql = "SELECT u.id_usuario, u.nombre, u.apellido, u.username, u.email, u.clave, r.nombre_rol , u.estado, u.fecha_creacion " +
                    "FROM usuario AS u" +
                    "INNER JOIN rol AS r" +
                    "ON u.id_rol = r.id_rol";
            psm = cn.prepareStatement(sql);
            rs = psm.executeQuery();
            while (rs.next()) {
                user.setIdUsuario(rs.getInt(1));
                user.setNombre(rs.getString(2));
                user.setApellido(rs.getString(3));
                user.setUsername(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setClave(rs.getString(6));
                Rol rol = new Rol();
                user.getRol().setNombreRol(rs.getString(7));
                user.setRol(rol);
                user.setEstado(rs.getInt(8));
                user.setFecha_creacion(rs.getTimestamp(9).toLocalDateTime());
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
        return List.of();
    }

    @Override
    public List<Usuario> buscarPorId(int idUsuario) {
        return List.of();
    }
}
