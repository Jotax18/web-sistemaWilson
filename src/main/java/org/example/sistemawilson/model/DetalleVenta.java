package org.example.sistemawilson.model;

public class DetalleVenta {
    private Venta venta;
    private Producto producto;
    private int cantidad;
    private double precioVentaUnitario;
    private double precioCompraUnitario;
    private double descuentoUnitario;
    private double totalIgv;

    public DetalleVenta() {
    }

    public DetalleVenta(Venta venta, Producto producto, int cantidad, double precioVentaUnitario, double precioCompraUnitario, double descuentoUnitario, double totalIgv) {
        this.venta = venta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioVentaUnitario = precioVentaUnitario;
        this.precioCompraUnitario = precioCompraUnitario;
        this.descuentoUnitario = descuentoUnitario;
        this.totalIgv = totalIgv;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
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

    public double getPrecioVentaUnitario() {
        return precioVentaUnitario;
    }

    public void setPrecioVentaUnitario(double precioVentaUnitario) {
        this.precioVentaUnitario = precioVentaUnitario;
    }

    public double getPrecioCompraUnitario() {
        return precioCompraUnitario;
    }

    public void setPrecioCompraUnitario(double precioCompraUnitario) {
        this.precioCompraUnitario = precioCompraUnitario;
    }

    public double getDescuentoUnitario() {
        return descuentoUnitario;
    }

    public void setDescuentoUnitario(double descuentoUnitario) {
        this.descuentoUnitario = descuentoUnitario;
    }

    public double getTotalIgv() {
        return totalIgv;
    }

    public void setTotalIgv(double totalIgv) {
        this.totalIgv = totalIgv;
    }
}
