package org.example.sistemawilson.dao;

import org.example.sistemawilson.model.Categoria;
import java.util.List;

public interface CategoriaDAO {
    public List<Categoria> listaCategoria();
    public boolean registrarCategoria(Categoria categoria);
    public boolean actualizarCategoria(Categoria categoria);
    public boolean actualizarEstadoCategoria(int idCategoria, int estado);
}
