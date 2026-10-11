package org.example.sistemawilson.dao;

import org.example.sistemawilson.model.Cliente;
import org.example.sistemawilson.model.Producto;

import java.util.List;

public interface ClienteDAO {
    public List<Cliente> listarCliente();
    public boolean registrarCliente(Cliente cliente);
    public boolean actualizarCliente(Cliente cliente);
    public List<Cliente> buscarCliente(String terminoBusqueda);
    public Cliente buscarClienteId(int idCliente);
}
