package org.example.sistemawilson.model;

public class RolPermiso {
    private Rol rol;
    private PermisoUsuario permisoUsuario;

    public RolPermiso() {

    }

    public RolPermiso(Rol rol, PermisoUsuario permisoUsuario) {
        this.rol = rol;
        this.permisoUsuario = permisoUsuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public PermisoUsuario getPermisoUsuario() {
        return permisoUsuario;
    }

    public void setPermisoUsuario(PermisoUsuario permisoUsuario) {
        this.permisoUsuario = permisoUsuario;
    }
}
