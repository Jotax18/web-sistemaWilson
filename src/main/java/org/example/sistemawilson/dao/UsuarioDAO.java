package org.example.sistemawilson.dao;

import org.example.sistemawilson.model.Usuario;
import java.util.List;

public interface UsuarioDAO {
    public boolean registrarUsuario(Usuario usuario);
    public boolean actualizarPerfilUsuario(int idUsuario, int idRol, int estado);
    public boolean eliminarUsuario(Integer idUsuario);
    public List<Usuario> listarUsuario();
    public List<Usuario> buscarPorDni(String dniUsuario);
    public List<Usuario> buscarPorId(int idUsuario);
}
