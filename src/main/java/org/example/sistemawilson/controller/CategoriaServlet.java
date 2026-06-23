package org.example.sistemawilson.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.sistemawilson.dao.CategoriaDAO;
import org.example.sistemawilson.dao.impl.CategoriaDAOImpl;
import org.example.sistemawilson.model.Categoria;

import java.io.IOException;
import java.util.List;

@WebServlet("/CategoriaServlet")
public class CategoriaServlet extends HttpServlet {

    CategoriaDAO daoCategoria = new CategoriaDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            action = "listar";
        }

        switch (action) {
            case "listar":
                List<Categoria> lista = daoCategoria.listaCategoria();
                req.setAttribute("lista", lista);
                req.getRequestDispatcher("lista_categoria.jsp").forward(req, resp);
                break;
            case "cargarFormularioRegistrar":
                req.getRequestDispatcher("formulario_categoria.jsp").forward(req, resp);
                break;
            case "cargarFormularioActualizar":
                int id = Integer.parseInt(req.getParameter("id"));
                Categoria categoriaEncontrada = daoCategoria.buscarCategoriaId(id);
                req.setAttribute("categoria", categoriaEncontrada);
                req.getRequestDispatcher("formulario_categoria.jsp").forward(req, resp);
                break;
        }
    }

    private Categoria construirCategoriaDesdeReq(HttpServletRequest req){
        String nombre = req.getParameter("txtNombreCat");
        String descripcion = req.getParameter("txtDescripcion");

        Categoria cat = new Categoria();
        cat.setNombre(nombre);
        cat.setDescripcion(descripcion);

        return cat;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null){
            action = "listar";
        }

        Categoria c;

        switch (action){
            case "registrarCategoria":
                c = construirCategoriaDesdeReq(req);
                boolean registrar = daoCategoria.registrarCategoria(c);
                if (!registrar){
                    req.setAttribute("error", "Hubo un problema al registrar!");
                    req.getRequestDispatcher("formulario_categoria.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("CategoriaServlet?action=listar");
                    System.out.println("Se registro la categoria"+ c);
                }
                break;
            case "actualizarCategoria":
                c = construirCategoriaDesdeReq(req);
                c.setIdCategoria(Integer.parseInt(req.getParameter("txtId")));
                boolean actualizar = daoCategoria.actualizarCategoria(c);
                if (!actualizar){
                    req.setAttribute("error", "Hubo un problema al actualizar!");
                    req.setAttribute("categoria", c);
                    req.getRequestDispatcher("formulario_categoria.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("CategoriaServlet?action=listar");
                    System.out.println("Se actualizo la categoria"+ c);
                }
                break;
            case "actualizarEstado":
                int idCategoria = Integer.parseInt(req.getParameter("id"));
                int estado = Integer.parseInt(req.getParameter("estado"));
                boolean cambiarEstado = daoCategoria.actualizarEstadoCategoria(idCategoria, estado);

                if (cambiarEstado){
                    resp.sendRedirect("CategoriaServlet?action=listar");
                } else {
                    req.setAttribute("error", "Hubo un problema al actualizar");
                    req.getRequestDispatcher("formulario_categoria.jsp").forward(req, resp);
                }
                break;
            default:
                resp.sendRedirect("error.jsp");
        }
    }
}
