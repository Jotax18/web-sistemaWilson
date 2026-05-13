package org.example.sistemawilson.model;

public class SerieComprobante {
    private int idSerie;
    private TipoComprobante tipoComprobante;
    private String serie;
    private int numeroActual;

    public SerieComprobante() {

    }

    public SerieComprobante(int idSerie, TipoComprobante tipoComprobante, String serie, int numeroActual) {
        this.idSerie = idSerie;
        this.tipoComprobante = tipoComprobante;
        this.serie = serie;
        this.numeroActual = numeroActual;
    }

    public int getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(int idSerie) {
        this.idSerie = idSerie;
    }

    public TipoComprobante getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(TipoComprobante tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getNumeroActual() {
        return numeroActual;
    }

    public void setNumeroActual(int numeroActual) {
        this.numeroActual = numeroActual;
    }
}
