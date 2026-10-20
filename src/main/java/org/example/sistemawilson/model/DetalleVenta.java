package org.example.sistemawilson.model;

import java.math.BigDecimal;

public class DetalleVenta {
    private Venta venta;
    private Producto producto;
    private int cantidad;
    private BigDecimal precioVentaUnitario;
    private BigDecimal precioCompraUnitario;
    private BigDecimal descuentoUnitario;
    private BigDecimal totalIgv;

    public DetalleVenta() {
    }

    public DetalleVenta(Venta venta, Producto producto, int cantidad, BigDecimal precioVentaUnitario, BigDecimal precioCompraUnitario, BigDecimal descuentoUnitario, BigDecimal totalIgv) {
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

    public BigDecimal getPrecioVentaUnitario() {
        return precioVentaUnitario;
    }

    public void setPrecioVentaUnitario(BigDecimal precioVentaUnitario) {
        this.precioVentaUnitario = precioVentaUnitario;
    }

    public BigDecimal getPrecioCompraUnitario() {
        return precioCompraUnitario;
    }

    public void setPrecioCompraUnitario(BigDecimal precioCompraUnitario) {
        this.precioCompraUnitario = precioCompraUnitario;
    }

    public BigDecimal getDescuentoUnitario() {
        return descuentoUnitario;
    }

    public void setDescuentoUnitario(BigDecimal descuentoUnitario) {
        this.descuentoUnitario = descuentoUnitario;
    }

    public BigDecimal getTotalIgv() {
        return totalIgv;
    }

    public void setTotalIgv(BigDecimal totalIgv) {
        this.totalIgv = totalIgv;
    }
}
