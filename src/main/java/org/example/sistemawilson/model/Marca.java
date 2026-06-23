package org.example.sistemawilson.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Marca {
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Integer idMarca;
    private String nombre;
    private int estado;
    private String fechaCreacion = LocalDate.now().format(formato);

    public Marca() {
    }

    public Marca(Integer idMarca, String nombre, int estado, String fechaCreacion) {
        this.idMarca = idMarca;
        this.nombre = nombre;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
