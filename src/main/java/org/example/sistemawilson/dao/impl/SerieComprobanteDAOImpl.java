package org.example.sistemawilson.dao.impl;

import org.example.sistemawilson.dao.SerieComprobanteDAO;
import org.example.sistemawilson.model.SerieComprobante;
import org.example.sistemawilson.util.MySQLConexion;

import java.sql.*;

public class SerieComprobanteDAOImpl implements SerieComprobanteDAO {

    ResultSet rs = null;

    @Override
    public SerieComprobante obtenerYActualizarCorrelativo(Connection cn, long idSerieComprobante) {
        SerieComprobante sc = new SerieComprobante();
        try {
            cn.setAutoCommit(false);
            String sqlSelect = "SELECT * FROM serie_comprobante WHERE id_serie = ? FOR UPDATE";
            PreparedStatement psmSelect = cn.prepareStatement(sqlSelect);
            psmSelect.setLong(1, idSerieComprobante);
            rs = psmSelect.executeQuery();
            psmSelect.close();

            if (rs.next()){
                int idSerie = rs.getInt( "id_serie");
                int numeroActual = rs.getInt("numero_actual");
                String serie = rs.getString("serie");

                String sqlUpdate = "UPDATE serie_comprobante SET numero_actual = ? + 1 WHERE id_serie = ?";
                PreparedStatement psmUpdate = cn.prepareStatement(sqlUpdate);
                psmUpdate.setInt(1, numeroActual);
                psmUpdate.setInt(2, idSerie);
                psmUpdate.executeUpdate();
                sc.setNumeroActual(numeroActual + 1);
                sc.setSerie(serie);
                psmUpdate.close();
            } else {
                throw new SQLException("No se pudo obtener el valor de la id_serie o el numero actual");
            }

        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Error al adicionar numero correlativo, transaccion revertida");
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.setAutoCommit(true);
                    MySQLConexion.closeConexion(cn);
                }
                if (rs != null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sc;
    }
}
