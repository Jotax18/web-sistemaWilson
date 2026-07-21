package org.example.sistemawilson.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class IngresoStock {
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Integer idIngresoStock;
    private String loteStock;
    private Usuario usuario;
    private String fechaIngreso = LocalDate.now().format(formato);
    private List<DetalleIngresoStock> listaDetalle;

    public IngresoStock() {

    }

    public IngresoStock(Integer idIngresoStock, String loteStock, Usuario usuario, List<DetalleIngresoStock> listaDetalle, String fechaIngreso) {
        this.idIngresoStock = idIngresoStock;
        this.loteStock = loteStock;
        this.usuario = usuario;
        this.listaDetalle = listaDetalle;
        this.fechaIngreso = fechaIngreso;
    }

    public List<DetalleIngresoStock> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(List<DetalleIngresoStock> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }

    public Integer getIdIngresoStock() {
        return idIngresoStock;
    }

    public void setIdIngresoStock(Integer idIngresoStock) {
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
