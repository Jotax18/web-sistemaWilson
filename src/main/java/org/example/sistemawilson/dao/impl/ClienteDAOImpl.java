package org.example.sistemawilson.dao.impl;

import org.example.sistemawilson.dao.ClienteDAO;
import org.example.sistemawilson.model.Cliente;
import org.example.sistemawilson.util.MySQLConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ClienteDAOImpl implements ClienteDAO {

    Connection cn = null;
    PreparedStatement psm = null;
    ResultSet rs =  null;

    @Override
    public List<Cliente> listarCliente() {
        ArrayList<Cliente> lista = new ArrayList<>();
        try {
            cn = MySQLConexion.getConnection();
            String sql = "SELECT * FROM cliente ORDER BY id_cliente DESC";
            psm = cn.prepareStatement(sql);
            rs = psm.executeQuery();
            while (rs.next()){
                Cliente cli = new Cliente();
                cli.setIdCliente(rs.getLong("id_cliente"));
                cli.setNombreRazonSocial(rs.getString("nombre_razon_social"));
                cli.setTipoDocumento(rs.getString("tipo_documento"));
                cli.setNumeroDocumento(rs.getString("numero_documento"));
                Date fechaSql = rs.getDate("fecha_nacimiento");
                if (fechaSql != null) {
                    cli.setFechaNacimiento(fechaSql.toLocalDate());
                }
                cli.setEmail(rs.getString("email"));
                cli.setCelular(rs.getString("celular"));
                lista.add(cli);
            }
        } catch (Exception e) {
            System.out.println("Eroor al listar cliente: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (psm != null) psm.close();
                if (cn != null) MySQLConexion.closeConexion(cn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    @Override
    public boolean registrarCliente(Cliente cliente) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "INSERT INTO cliente (nombre_razon_social, tipo_documento, numero_documento, fecha_nacimiento, email, celular) VALUES (?,?,?,?,?,?)";
            psm = cn.prepareStatement(sql);
            psm.setString(1, cliente.getNombreRazonSocial());
            psm.setString(2, cliente.getTipoDocumento());
            psm.setString(3, cliente.getNumeroDocumento());
            psm.setString(4, cliente.getFechaNacimiento() != null ? cliente.getFechaNacimiento().toString() : null);
            psm.setString(5, cliente.getEmail());
            psm.setString(6, cliente.getCelular());
            psm.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Eroor al registrar:" + e.getMessage());
            return false;
        } finally {
            try {
                if (psm != null) psm.close();
                if (cn != null) MySQLConexion.closeConexion(cn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean actualizarCliente(Cliente cliente) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "UPDATE cliente SET nombre_razon_social = ?, tipo_documento = ?, numero_documento = ?, fecha_nacimiento = ?, email = ?, celular = ? WHERE id_cliente = ?";
            psm = cn.prepareStatement(sql);
            psm.setString(1, cliente.getNombreRazonSocial());
            psm.setString(2, cliente.getTipoDocumento());
            psm.setString(3, cliente.getNumeroDocumento());
            psm.setString(4, cliente.getFechaNacimiento() != null ? cliente.getFechaNacimiento().toString() : null);
            psm.setString(5, cliente.getEmail());
            psm.setString(6, cliente.getCelular());
            psm.setLong(7, cliente.getIdCliente());
            psm.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Eroor al actualizar:" + e.getMessage());
            return false;
        } finally {
            try {
                if (psm != null) psm.close();
                if (cn != null) MySQLConexion.closeConexion(cn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Cliente> buscarCliente(String terminoBusqueda) {
        ArrayList<Cliente> lista = new ArrayList<>();
        try {
            cn = MySQLConexion.getConnection();
            String sql = "SELECT * FROM cliente WHERE nombre_razon_social LIKE ?  OR numero_documento = ?";
            psm = cn.prepareStatement(sql);
            psm.setString(1, "%" + terminoBusqueda + "%");
            psm.setString(2, terminoBusqueda);
            rs = psm.executeQuery();
            while (rs.next()){
                Cliente cli = new Cliente();
                cli.setIdCliente(rs.getLong("id_cliente"));
                cli.setNombreRazonSocial(rs.getString("nombre_razon_social"));
                cli.setTipoDocumento(rs.getString("tipo_documento"));
                cli.setNumeroDocumento(rs.getString("numero_documento"));
                Date fechaSql = rs.getDate("fecha_nacimiento");
                if (fechaSql != null) {
                    cli.setFechaNacimiento(fechaSql.toLocalDate());
                }
                cli.setEmail(rs.getString("email"));
                cli.setCelular(rs.getString("celular"));
                lista.add(cli);
            }
        } catch (Exception e) {
            System.out.println("Eroor al buscar cliente general: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (psm != null) psm.close();
                if (cn != null) MySQLConexion.closeConexion(cn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    @Override
    public Cliente buscarClienteId(int idCliente) {
        ArrayList<Cliente> lista = new ArrayList<>();
        try {
            cn = MySQLConexion.getConnection();
            String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
            psm = cn.prepareStatement(sql);
            psm.setInt(1, idCliente);
            rs = psm.executeQuery();
            while (rs.next()){
                Cliente cli = new Cliente();
                cli.setIdCliente(rs.getLong("id_cliente"));
                cli.setNombreRazonSocial(rs.getString("nombre_razon_social"));
                cli.setTipoDocumento(rs.getString("tipo_documento"));
                cli.setNumeroDocumento(rs.getString("numero_documento"));
                Date fechaSql = rs.getDate("fecha_nacimiento");
                if (fechaSql != null) {
                    cli.setFechaNacimiento(fechaSql.toLocalDate());
                }
                cli.setEmail(rs.getString("email"));
                cli.setCelular(rs.getString("celular"));
                return cli;
            }
        } catch (Exception e) {
            System.out.println("Eroor al buscar cliente por id: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (psm != null) psm.close();
                if (cn != null) MySQLConexion.closeConexion(cn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
