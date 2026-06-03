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
        HttpSession sesion;
        String user = req.getParameter("txtUsername");
        String pass = req.getParameter("txtPassword");

        Usuario usuario = new Usuario();
        usuario.setUsername(user);
        usuario.setClave(pass);

        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        Usuario userValida = usuarioDAO.validarLogin(usuario);

        if (userValida != null && userValida.getRol().getIdRol() == Utils.ROL_ADMIN){
            //sesion.setAttribute("usuario", userValida);
        }
    }
}
