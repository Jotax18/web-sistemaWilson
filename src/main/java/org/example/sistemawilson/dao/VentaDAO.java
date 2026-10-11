package org.example.sistemawilson.dao;

import org.example.sistemawilson.model.IngresoStock;
import org.example.sistemawilson.model.Venta;

import java.util.List;

public interface VentaDAO {
    public List<Venta> listarVenta();
    public boolean registrarVenta(Venta venta);
    public boolean anularVenta(int idVenta);
    public List<Venta> buscarVenta(String terminoBusqueda);
    public Venta buscarVentaPorId(int idVenta);
}
