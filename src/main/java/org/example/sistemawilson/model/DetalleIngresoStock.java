package org.example.sistemawilson.model;

public class DetalleIngresoStock {
    private IngresoStock ingresoStock;
    private Producto producto;
    private int cantidad;
    private double precioUnitario;

    public DetalleIngresoStock() {

    }

    public DetalleIngresoStock(IngresoStock ingresoStock, Producto producto, int cantidad, double precioUnitario) {
        this.ingresoStock = ingresoStock;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public IngresoStock getIngresoStock() {
        return ingresoStock;
    }

    public void setIngresoStock(IngresoStock ingresoStock) {
        this.ingresoStock = ingresoStock;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
