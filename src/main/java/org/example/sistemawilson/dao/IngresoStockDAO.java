package org.example.sistemawilson.dao;

import org.example.sistemawilson.model.DetalleIngresoStock;
import org.example.sistemawilson.model.IngresoStock;

import java.util.List;

public interface IngresoStockDAO {
    public boolean registrarStock(IngresoStock ingresoStock);
    public boolean actualizarStock(IngresoStock actualizarStock);
}
