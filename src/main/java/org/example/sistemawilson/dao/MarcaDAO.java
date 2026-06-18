package org.example.sistemawilson.dao;

import org.example.sistemawilson.model.Marca;
import java.util.List;

public interface MarcaDAO {
    public List<Marca> listaMarca();
    public boolean registrarMarca(Marca marca);
    public boolean actualizarMarca(Marca marca);
    public boolean actualizarEstadoMarca(int idMarca, int estado);
}
