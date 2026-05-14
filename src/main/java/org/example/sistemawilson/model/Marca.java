package org.example.sistemawilson.model;

public class Marca {
    private Integer idMarca;
    private String nombre;
    private int estado;

    public Marca() {
    }

    public Marca(Integer idMarca, String nombre, int estado) {
        this.idMarca = idMarca;
        this.nombre = nombre;
        this.estado = estado;
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
}
