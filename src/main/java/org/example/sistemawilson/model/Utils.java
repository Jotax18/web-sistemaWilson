package org.example.sistemawilson.model;

public final class Utils {
    private Utils(){
        throw new UnsupportedOperationException("Esta es una clase de Utildad y no puede ser isntanciada");
    }
    //ESTADO USUARIO
    public static final int ESTADO_INACTIVO = 0;
    public static final int ESTADO_ACTIVO = 1;

    //ROL USUARIO
    public static final String ROL_ADMIN = "admin";
    public static final String ROL_VENDEDOR = "vendedor";

}
