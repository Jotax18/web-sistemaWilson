package org.example.sistemawilson.dao.impl;

import org.example.sistemawilson.dao.UsuarioDAO;
import org.example.sistemawilson.model.Usuario;

import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {
    @Override
    public boolean registrarUsuario(Usuario usuario) {
        return false;
    }

    @Override
    public boolean modificarUsuario(Usuario usuario) {
        return false;
    }

    @Override
    public boolean eliminarUsuario(Integer idUsuario) {
        return false;
    }

    @Override
    public List<Usuario> listaUsuario() {
        return List.of();
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
