package org.example.sistemawilson.model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Categoria {
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private int idCategoria;
    private String nombre;
    private String descripcion;
    private int estado;
    private String fechaCreacion = LocalDate.now().format(formato);

    public Categoria() {

    }

    public Categoria(int idCategoria, String nombre, String descripcion, int estado, String fechaCreacion) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
