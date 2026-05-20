package org.example.sistemawilson.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.sistemawilson.dao.UsuarioDAO;
import org.example.sistemawilson.dao.impl.UsuarioDAOImpl;
import org.example.sistemawilson.model.Usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {

    public UsuarioServlet(){

    }

    UsuarioDAO daoUsuario = new UsuarioDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null){
            action = "listar";
        }

        switch (action) {
            case "listar":
                List<Usuario> lista = daoUsuario.listarUsuario();
                req.setAttribute("listaUsuario", lista);
                req.getRequestDispatcher("lista_usuario.jsp").forward(req, resp);
                break;
            case "listarUsuarioDni":
                String dni = req.getParameter("txtDni");
                Usuario userDni = daoUsuario.buscarPorDni(dni);
                req.setAttribute("usuarioDni", userDni);
                req.getRequestDispatcher("lista_usuario.jsp").forward(req,resp);
                break;
            default:
                resp.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String nom = req.getParameter("txtNombres");
        String ape = req.getParameter("txtApellidos");
        String dni = req.getParameter("txtDni");
        String cel = req.getParameter("txtCelular");
        String user = req.getParameter("txtUsuario");
        String email  = req.getParameter("txtEmail");
        String pass = req.getParameter("txtClave");
        String rol = req.getParameter("cboRol");

        switch (action){
            case "registrarUsuario":
                Usuario u = new Usuario();
                u.setNombres(nom);
                u.setApellidos(ape);
                u.setDni(dni);
                break;
            case "actualizarUsuario":
                break;
            case "cambiarEstadoUsuario":
                break;
            default:
        }
    }
}
