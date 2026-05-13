package org.example.sistemawilson.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Usuario {
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private int idUsuario;
    private String nombre;
    private String apellido;
    private String usuario;
    private String email;
    private String clave;
    private Rol rol;
    private int estado;
    private String fecha_creacion = LocalDate.now().format(formato);

    public Usuario() {
        
    }

    public Usuario(int idUsuario, String nombre, String apellido, String usuario, String email,
                   String clave, Rol rol, int estado, String fecha_creacion) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.email = email;
        this.clave = clave;
        this.rol = rol;
        this.estado = estado;
        this.fecha_creacion = fecha_creacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
}
