package org.example.sistemawilson.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class IngresoStock {
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Integer idIngresoStock;
    private String loteStock;
    private Usuario usuario;
    private int estado;
    private List<DetalleIngresoStock> listaDetalle;
    private String fechaIngreso = LocalDate.now().format(formato);

    public IngresoStock() {

    }

    public IngresoStock(Integer idIngresoStock, String loteStock, Usuario usuario, List<DetalleIngresoStock> listaDetalle, int estado, String fechaIngreso) {
        this.idIngresoStock = idIngresoStock;
        this.loteStock = loteStock;
        this.usuario = usuario;
        this.estado = estado;
        this.listaDetalle = listaDetalle;
        this.fechaIngreso = fechaIngreso;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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
