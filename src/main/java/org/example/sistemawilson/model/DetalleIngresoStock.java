package org.example.sistemawilson.model;

public class DetalleIngresoStock {
    private IngresoStock ingresoStock;
    private Producto producto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public DetalleIngresoStock() {

    }

    public DetalleIngresoStock(IngresoStock ingresoStock, Producto producto, int cantidad, double precioUnitario, double subtotal) {
        this.ingresoStock = ingresoStock;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
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
