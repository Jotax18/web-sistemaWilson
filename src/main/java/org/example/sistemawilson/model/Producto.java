package org.example.sistemawilson.model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Producto {
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Integer idProducto;
    private String codigoSku;
    private String nombre;
    private Marca marca;
    private Categoria categoria;
    private String modelo;
    private String descripcion;
    private double precioCompra;
    private double precioVenta;
    private int stockActual;
    private int stockMinimo;
    private int estado;
    private String fechaCreacion = LocalDate.now().format(formato);

    public Producto(){

    }

    public Producto(Integer idProducto, String codigoSku, String nombre, Marca marca, Categoria categoria,String modelo,
                    String descripcion, double precioCompra, double precioVenta, int stockActual, int stockMinimo,
                    int estado, String fechaCreacion) {
        this.idProducto = idProducto;
        this.codigoSku = codigoSku;
        this.nombre = nombre;
        this.marca = marca;
        this.categoria = categoria;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigoSku() {
        return codigoSku;
    }

    public void setCodigoSku(String codigoSku) {
        this.codigoSku = codigoSku;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
