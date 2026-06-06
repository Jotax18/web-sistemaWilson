package org.example.sistemawilson.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.sistemawilson.dao.UsuarioDAO;
import org.example.sistemawilson.dao.impl.UsuarioDAOImpl;
import org.example.sistemawilson.model.Usuario;
import org.example.sistemawilson.model.Utils;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login_usuario.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("txtUsername");
        String pass = req.getParameter("txtPassword");

        Usuario usuario = new Usuario();
        usuario.setUsername(user);
        usuario.setClave(pass);

        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        Usuario userValida = usuarioDAO.validarLogin(usuario);

        if (userValida != null) {

            HttpSession sesion = req.getSession();
            sesion.setAttribute("usuarioSesion", userValida);
            sesion.setAttribute("msje", "Bienvenido al sistema");

            if (userValida.getRol().getNombreRol().equals(Utils.ROL_ADMIN)) {
                resp.sendRedirect("UsuarioServlet?action=listar");

            } else if (userValida.getRol().getNombreRol().equals(Utils.ROL_VENDEDOR)) {
                resp.sendRedirect("PrincipalServlet?action=cotizaProducto");

            } else {
                req.setAttribute("error", "Rol de usuario no autorizado");
                req.getRequestDispatcher("login_usuario.jsp").forward(req, resp);
            }

        } else {
            req.setAttribute("error", "Credenciales Inválidas");
            req.getRequestDispatcher("login_usuario.jsp").forward(req, resp);
        }
    }
}
