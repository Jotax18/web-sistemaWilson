package org.example.sistemawilson.dao;

import org.example.sistemawilson.model.SerieComprobante;

import java.sql.Connection;

public interface SerieComprobanteDAO {
    public SerieComprobante obtenerYActualizarCorrelativo(Connection cn, long idSerieComprobante);
}
