package org.example.sistemawilson.model;

public class PermisoUsuario {

    private Integer idPermiso;
    private String nombrePermiso;
    private String descripcionPermiso;

    public PermisoUsuario(){

    }

    public PermisoUsuario(Integer idPermiso, String nombrePermiso, String descripcionPermiso) {
        this.idPermiso = idPermiso;
        this.nombrePermiso = nombrePermiso;
        this.descripcionPermiso = descripcionPermiso;
    }

    public Integer getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getNombrePermiso() {
        return nombrePermiso;
    }

    public void setNombrePermiso(String nombrePermiso) {
        this.nombrePermiso = nombrePermiso;
    }

    public String getDescripcionPermiso() {
        return descripcionPermiso;
    }

    public void setDescripcionPermiso(String descripcionPermiso) {
        this.descripcionPermiso = descripcionPermiso;
    }
}
