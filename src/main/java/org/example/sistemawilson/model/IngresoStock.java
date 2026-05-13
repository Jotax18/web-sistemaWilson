package org.example.sistemawilson.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class IngresoStock {
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private int idIngresoStock;
    private String loteStock;
    private Usuario usuario;
    private String fechaIngreso = LocalDate.now().format(formato);

    public IngresoStock() {

    }

    public int getIdIngresoStock() {
        return idIngresoStock;
    }

    public void setIdIngresoStock(int idIngresoStock) {
        this.idIngresoStock = idIngresoStock;
    }

    public String getLoteStock() {
        return loteStock;
    }

    public void setLoteStock(String loteStock) {
        this.loteStock = loteStock;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
