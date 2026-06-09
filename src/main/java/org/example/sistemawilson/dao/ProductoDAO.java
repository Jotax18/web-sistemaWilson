package org.example.sistemawilson.dao;

import org.example.sistemawilson.model.Producto;
import java.util.List;

public interface ProductoDAO {
    public List<Producto> listarProducto();
    public boolean registrarProducto(Producto producto);
    public boolean actualizarProducto(Producto producto);
    public boolean actualizarEstadoProducto(int idProducto, int estado);
    public List<Producto> buscarProductoGeneral(String terminoBusqueda);
    public Producto buscarProductoId(int idProducto);

}
