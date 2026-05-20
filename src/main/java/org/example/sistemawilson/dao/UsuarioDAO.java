package org.example.sistemawilson.dao;

import org.example.sistemawilson.model.Usuario;
import java.util.List;

public interface UsuarioDAO {
    public boolean registrarUsuario(Usuario usuario);
    public boolean actualizarPerfilUsuario(Usuario usuario);
    public boolean actualizarEstadoUsuario(int idUsuario, int estado);
    public List<Usuario> listarUsuario();
    public Usuario buscarPorDni(String dniUsuario);
    public Usuario buscarPorId(int idUsuario);
}
