package org.example.sistemawilson.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.sistemawilson.dao.ClienteDAO;
import org.example.sistemawilson.dao.impl.ClienteDAOImpl;
import org.example.sistemawilson.model.Cliente;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/ClienteServlet")
public class ClienteServlet extends HttpServlet {

    ClienteDAO daoCliente = new ClienteDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null){
            action = "listar";
        }

        switch (action){
            case "listar":
                List<Cliente> lista = daoCliente.listarCliente();
                req.setAttribute("lista", lista);
                req.getRequestDispatcher("lista_cliente.jsp").forward(req, resp);
                break;
            case "cargarFormularioRegistrar":
                req.getRequestDispatcher("formulario_cliente.jsp").forward(req, resp);
                break;
            case "cargarFormularioActualizar":
                int id = Integer.parseInt(req.getParameter("idCliente"));
                Cliente clienteEncontrado = daoCliente.buscarClienteId(id);
                req.setAttribute("clienteEncontrado", clienteEncontrado);
                req.getRequestDispatcher("formulario_cliente.jsp").forward(req, resp);
                break;
            case "buscarCliente":
                String terminoBusqueda = req.getParameter("txtBuscarCliente");
                List<Cliente> listaBusqueda = daoCliente.buscarCliente(terminoBusqueda != null ? terminoBusqueda.trim() : "");
                req.setAttribute("lista", listaBusqueda);
                req.getRequestDispatcher("lista_cliente.jsp").forward(req, resp);
                break;
        }
    }

    private Cliente construirClienteDesdeReq(HttpServletRequest req){
        String nombreRazonSocial = req.getParameter("txtNombreRazonSocial");
        String tipoDocumento = req.getParameter("txtTipoDocumento");
        String numeroDocumento = req.getParameter("txtNumeroDocumento");
        String fechaNacimiento = req.getParameter("txtFechaNacimiento");
        String email = req.getParameter("txtEmail");
        String celular = req.getParameter("txtCelular");

        Cliente cli = new Cliente();
        cli.setNombreRazonSocial(nombreRazonSocial);
        cli.setTipoDocumento(tipoDocumento);
        cli.setNumeroDocumento(numeroDocumento);
        if (fechaNacimiento != null && !fechaNacimiento.trim().isEmpty()) {
            LocalDate fechaLocal = LocalDate.parse(fechaNacimiento.trim());
            cli.setFechaNacimiento(fechaLocal);
        }
        cli.setEmail(email);
        cli.setCelular(celular);
        return cli;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null){
            action = "listar";
        }

        Cliente c;

        switch (action){
            case "registrarCliente":
                c = construirClienteDesdeReq(req);
                boolean registrar = daoCliente.registrarCliente(c);
                if (!registrar){
                    req.setAttribute("error", "Hubo un error al registrar!");
                    req.getRequestDispatcher("formulario_cliente.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("ClienteServlet?action=listar");
                    System.out.println("Se registro el cliente" + c);
                }
                break;
            case "actualizarCliente":
                c = construirClienteDesdeReq(req);
                String idParam = req.getParameter("txtIdCliente");
                if (idParam == null || idParam.isEmpty()) {
                    idParam = req.getParameter("idCliente");
                }
                c.setIdCliente(Long.parseLong(idParam));
                boolean actualizar = daoCliente.actualizarCliente(c);
                if (!actualizar){
                    req.setAttribute("error", "Hubo un error al actualizar!");
                    req.setAttribute("clienteEncontrado", c);
                    req.getRequestDispatcher("formulario_cliente.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("ClienteServlet?action=listar");
                    System.out.println("Se actualizo el cliente!" + c);
                }
                break;
        }
    }

}
