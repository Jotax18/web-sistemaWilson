package org.example.sistemawilson.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cliente {
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private int idCliente;
    private String nombreRazonSocial;
    private String tipoDocumento;
    private String fechaNacimiento = LocalDate.now().format(formato);
    private String email;
    private String celular;

    public Cliente() {
    }

    public Cliente(int idCliente, String nombreRazonSocial, String tipoDocumento, String fechaNacimiento, String email, String celular) {
        this.idCliente = idCliente;
        this.nombreRazonSocial = nombreRazonSocial;
        this.tipoDocumento = tipoDocumento;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.celular = celular;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
