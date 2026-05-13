package org.example.sistemawilson.model;

public class TipoComprobante {
    private int idTipoComprobante;
    private int codigo;
    private String nombre;

    public TipoComprobante() {

    }

    public TipoComprobante(int idTipoComprobante, int codigo, String nombre) {
        this.idTipoComprobante = idTipoComprobante;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(int idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
