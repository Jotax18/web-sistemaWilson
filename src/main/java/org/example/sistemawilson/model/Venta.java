package org.example.sistemawilson.model;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Venta {

    private long idVenta;
    private long numeroCorrelativo;
    private long estado;
    private SerieComprobante serieComprobante;
    private Usuario usuario;
    private Cliente cliente;
    private LocalDate fechaEmision;
    private BigDecimal subtotalTotal;
    private BigDecimal igvTotal;
    private BigDecimal importeTotal;
    private BigDecimal descuentoGlobal;
    private String tipoMoneda;

    private List<DetalleVenta> listaDetalleVenta;

    public Venta() {

    }

    public Venta(long idVenta, long numeroCorrelativo, long estado, SerieComprobante serieComprobante, Usuario usuario,
                 Cliente cliente, LocalDate fechaEmision, BigDecimal subtotalTotal, BigDecimal igvTotal,
                 BigDecimal importeTotal, BigDecimal descuentoGlobal, String tipoMoneda, List<DetalleVenta> listaDetalleVenta) {
        this.idVenta = idVenta;
        this.numeroCorrelativo = numeroCorrelativo;
        this.estado = estado;
        this.serieComprobante = serieComprobante;
        this.usuario = usuario;
        this.cliente = cliente;
        this.fechaEmision = fechaEmision;
        this.subtotalTotal = subtotalTotal;
        this.igvTotal = igvTotal;
        this.importeTotal = importeTotal;
        this.descuentoGlobal = descuentoGlobal;
        this.tipoMoneda = tipoMoneda;
        this.listaDetalleVenta = listaDetalleVenta;
    }

    public long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(long idVenta) {
        this.idVenta = idVenta;
    }

    public long getNumeroCorrelativo() {
        return numeroCorrelativo;
    }

    public void setNumeroCorrelativo(long numeroCorrelativo) {
        this.numeroCorrelativo = numeroCorrelativo;
    }

    public long getEstado() {
        return estado;
    }

    public void setEstado(long estado) {
        this.estado = estado;
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

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public BigDecimal getSubtotalTotal() {
        return subtotalTotal;
    }

    public void setSubtotalTotal(BigDecimal subtotalTotal) {
        this.subtotalTotal = subtotalTotal;
    }

    public BigDecimal getIgvTotal() {
        return igvTotal;
    }

    public void setIgvTotal(BigDecimal igvTotal) {
        this.igvTotal = igvTotal;
    }

    public BigDecimal getDescuentoGlobal() {
        return descuentoGlobal;
    }

    public void setDescuentoGlobal(BigDecimal descuentoGlobal) {
        this.descuentoGlobal = descuentoGlobal;
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public List<DetalleVenta> getListaDetalleVenta() {
        return listaDetalleVenta;
    }

    public void setListaDetalleVenta(List<DetalleVenta> listaDetalleVenta) {
        this.listaDetalleVenta = listaDetalleVenta;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    //METOOD PARA CALCULAR EL SUBTOTAL
    public BigDecimal calcularSubtotal(){
        BigDecimal subtotal = BigDecimal.ZERO;
        for(DetalleVenta d : listaDetalleVenta){
            subtotal = subtotal.add(d.calcularBaseFila());
        }
        return subtotal;
    }

    //METODO PARA CALCULAR EL IGV TOTAL
    public BigDecimal calcularIgvTotal(){
        BigDecimal igvTotal = BigDecimal.ZERO;
        for (DetalleVenta d : listaDetalleVenta){
            igvTotal = igvTotal.add(d.calcularIgvFila());
        }
        return igvTotal;
    }

    //METODO PARA CALCULAR EL TOTAL IMPORTE TOTAL
    public BigDecimal calcularImporteTotal(){
        return  calcularSubtotal().add(calcularIgvTotal());
    }

    //METODO PARA ACTUALIZAR TOTALES E INDTRODUCIR LOS VALROES DIRECTAMENTE A LA VARIABLE CONTRUCTOR
    public void actualizarTotales() {
        this.subtotalTotal = calcularSubtotal();
        this.igvTotal = calcularIgvTotal();
        this.importeTotal = calcularImporteTotal();
    }
}
