package org.example.sistemawilson.model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Venta {
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private int idVenta;
    private SerieComprobante serieComprobante;
    private Usuario usuario;
    private Cliente cliente;
    private String fechaEmision = LocalDate.now().format(formato);
    private double subtotalTotal;
    private double igvTotal;
    private double importeTotal;
    private double descuentoGloval;
    private int numeroCorrelativo;
    private int estado;
    private String tipoMoneda;

    public Venta() {

    }

    public Venta(int idVenta, SerieComprobante serieComprobante, Usuario usuario, Cliente cliente, String fechaEmision, double subtotalTotal,
                 double igvTotal, double importeTotal, double descuentoGloval, int numeroCorrelativo, int estado, String tipoMoneda) {
        this.idVenta = idVenta;
        this.serieComprobante = serieComprobante;
        this.usuario = usuario;
        this.cliente = cliente;
        this.fechaEmision = fechaEmision;
        this.subtotalTotal = subtotalTotal;
        this.igvTotal = igvTotal;
        this.importeTotal = importeTotal;
        this.descuentoGloval = descuentoGloval;
        this.numeroCorrelativo = numeroCorrelativo;
        this.estado = estado;
        this.tipoMoneda = tipoMoneda;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public SerieComprobante getSerieComprobante() {
        return serieComprobante;
    }

    public void setSerieComprobante(SerieComprobante serieComprobante) {
        this.serieComprobante = serieComprobante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getSubtotalTotal() {
        return subtotalTotal;
    }

    public void setSubtotalTotal(double subtotalTotal) {
        this.subtotalTotal = subtotalTotal;
    }

    public double getIgvTotal() {
        return igvTotal;
    }

    public void setIgvTotal(double igvTotal) {
        this.igvTotal = igvTotal;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public double getDescuentoGloval() {
        return descuentoGloval;
    }

    public void setDescuentoGloval(double descuentoGloval) {
        this.descuentoGloval = descuentoGloval;
    }

    public int getNumeroCorrelativo() {
        return numeroCorrelativo;
    }

    public void setNumeroCorrelativo(int numeroCorrelativo) {
        this.numeroCorrelativo = numeroCorrelativo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }
}
