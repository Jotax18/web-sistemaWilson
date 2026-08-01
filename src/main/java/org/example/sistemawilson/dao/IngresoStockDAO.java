package org.example.sistemawilson.dao;

import org.example.sistemawilson.model.DetalleIngresoStock;
import org.example.sistemawilson.model.IngresoStock;

import java.util.List;

public interface IngresoStockDAO {
    public List<IngresoStock> listarIngresoStock();
    public boolean registrarStock(IngresoStock ingresoStock);
    public boolean anularIngresoStock(int idIngresoStock);
    public List<IngresoStock> buscarIngresoStock(String terminoBusqueda);
    public IngresoStock buscarIngresoStockPorId(int idIngresoStock);
}
