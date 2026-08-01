package org.example.sistemawilson.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.sistemawilson.dao.IngresoStockDAO;
import org.example.sistemawilson.dao.UsuarioDAO;
import org.example.sistemawilson.dao.impl.IngresoStockImpl;
import org.example.sistemawilson.dao.impl.UsuarioDAOImpl;
import org.example.sistemawilson.model.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/IngresoStockServlet")
public class IngresoStockServlet extends HttpServlet {

    IngresoStockDAO daoIngreso = new IngresoStockImpl();
    UsuarioDAO daoUsuario = new UsuarioDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            action = "listar";
        }

        switch (action) {
            case "listar":
                List<IngresoStock> lista = daoIngreso.listarIngresoStock();
                req.setAttribute("lista", lista);
                req.getRequestDispatcher("lista_ingreso_stock.jsp").forward(req, resp);
                break;
            case "cargarFormularioRegistrar":
                req.getRequestDispatcher("formulario_ingreso_stock.jsp").forward(req, resp);
                break;
            case "cargarFomularioActualizar":
                int id = Integer.parseInt(req.getParameter("id"));
                IngresoStock ingresoEncontrado = daoIngreso.buscarIngresoStockPorId(id);
                //List<Proveedor> listProve = daoProveedor.listarProveedor();
                //req.setAttribute("listaProveedor", listProve);
                List<Usuario> listUsuario = daoUsuario.listarUsuario();
                req.setAttribute("listUsuario", listUsuario);
                req.setAttribute("ingresoEncontrado", ingresoEncontrado);
                req.getRequestDispatcher("formulario_ingreso_stock.jsp").forward(req, resp);
                break;
            case "cargarFormularioActualizar":
                String ingresoGeneral = req.getParameter("txtIngresoGeneral");
                List<IngresoStock> listaGeneral = daoIngreso.buscarIngresoStock(ingresoGeneral);
                req.setAttribute("listGeneral", listaGeneral);
                req.getRequestDispatcher("lista_ingreso_stock.jsp").forward(req, resp);
            default:
                resp.sendRedirect("error.jsp");
        }
    }

    private IngresoStock construirIngresoStockDesdeReq (HttpServletRequest req){
        String lote = req.getParameter("txtLoteStock");
        String usu = req.getParameter("cboUsuario");
        Usuario u = new Usuario();
        u.setNombres(usu);

        IngresoStock ingStock = new IngresoStock();
        ingStock.setLoteStock(lote);
        ingStock.setUsuario(u);
        return ingStock;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
