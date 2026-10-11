package org.example.sistemawilson.dao.impl;

import org.example.sistemawilson.dao.VentaDAO;
import org.example.sistemawilson.model.Venta;
import org.example.sistemawilson.util.MySQLConexion;

import javax.print.attribute.standard.PresentationDirection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VentaDAOImpl implements VentaDAO {

    Connection cn = null;
    PreparedStatement psm = null;
    ResultSet rs = null;

    public List<Venta> listarVenta() {
        List<Venta> lista = new ArrayList<>();
        /*try {
            cn = MySQLConexion.getConnection();
            String sql = ""
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
        return lista;
    }

    @Override
    public boolean registrarVenta(Venta venta) {
        return false;
    }

    @Override
    public boolean anularVenta(int idVenta) {
        return false;
    }

    @Override
    public List<Venta> buscarVenta(String terminoBusqueda) {
        return List.of();
    }

    @Override
    public Venta buscarVentaPorId(int idVenta) {
        return null;
    }
}
