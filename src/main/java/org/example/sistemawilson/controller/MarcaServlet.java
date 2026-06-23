package org.example.sistemawilson.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.sistemawilson.dao.MarcaDAO;
import org.example.sistemawilson.dao.impl.MarcaDAOImpl;
import org.example.sistemawilson.model.Marca;

import java.io.IOException;
import java.util.List;

@WebServlet("/MarcaServlet")
public class MarcaServlet extends HttpServlet {

    MarcaDAO daoMarca = new MarcaDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            action = "listar";
        }

        switch (action) {
            case "listar":
                List<Marca> lista = daoMarca.listaMarca();
                req.setAttribute("lista", lista);
                req.getRequestDispatcher("lista_marca.jsp").forward(req, resp);
                break;
            case "cargarFormularioRegistrar":
                req.getRequestDispatcher("formulario_marca.jsp").forward(req, resp);
                break;
            case "cargarFormularioActualizar":
                int id = Integer.parseInt(req.getParameter("id"));
                Marca marcaEncontrada = daoMarca.buscarMarcaId(id);
                req.setAttribute("marca", marcaEncontrada);
                req.getRequestDispatcher("formulario_marca.jsp").forward(req, resp);
                break;
        }
    }

    private Marca construirMarcaDesdeReq(HttpServletRequest req){
        String nombre = req.getParameter("txtNombreMar");

        Marca mar = new Marca();
        mar.setNombre(nombre);

        return mar;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null){
            action = "listar";
        }

        Marca m;

        switch (action){
            case "registrarMarca":
                m = construirMarcaDesdeReq(req);
                boolean registrar = daoMarca.registrarMarca(m);
                if (!registrar){
                    req.setAttribute("error", "Hubo un problema al registrar!");
                    req.getRequestDispatcher("formulario_marca.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("MarcaServlet?action=listar");
                    System.out.println("Se registro la marca"+ m);
                }
                break;
            case "actualizarMarca":
                m = construirMarcaDesdeReq(req);
                m.setIdMarca(Integer.parseInt(req.getParameter("txtId")));
                boolean actualizar = daoMarca.actualizarMarca(m);
                if (!actualizar){
                    req.setAttribute("error", "Hubo un problema al actualizar!");
                    req.setAttribute("marca", m);
                    req.getRequestDispatcher("formulario_marca.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("MarcaServlet?action=listar");
                    System.out.println("Se actualizo la marca"+ m);
                }
                break;
            case "actualizarEstado":
                int idMarca = Integer.parseInt(req.getParameter("id"));
                int estado = Integer.parseInt(req.getParameter("estado"));
                boolean cambiarEstado = daoMarca.actualizarEstadoMarca(idMarca, estado);

                if (cambiarEstado){
                    resp.sendRedirect("MarcaServlet?action=listar");
                } else {
                    req.setAttribute("error", "Hubo un problema al actualizar");
                    req.getRequestDispatcher("formulario_marca.jsp").forward(req, resp);
                }
                break;
            default:
                resp.sendRedirect("error.jsp");
        }
    }
}
