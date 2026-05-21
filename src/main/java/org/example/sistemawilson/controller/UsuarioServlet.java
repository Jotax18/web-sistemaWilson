package org.example.sistemawilson.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.sistemawilson.dao.UsuarioDAO;
import org.example.sistemawilson.dao.impl.UsuarioDAOImpl;
import org.example.sistemawilson.model.Rol;
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

    private Usuario construirUsuarioDesdeRequest (HttpServletRequest req){
        //Traer los valores que el usuario ingresa en la vista y guardarlo en una variable
        String nom = req.getParameter("txtNombres");
        String ape = req.getParameter("txtApellidos");
        String dni = req.getParameter("txtDni");
        String cel = req.getParameter("txtCelular");
        String user = req.getParameter("txtUsuario");
        String email  = req.getParameter("txtEmail");
        String pass = req.getParameter("txtClave");
        int idRolSeleccionado = Integer.parseInt(req.getParameter("cboRol"));
        Rol r = new Rol();
        r.setIdRol(idRolSeleccionado);

        Usuario u = new Usuario();
        u.setNombres(nom);
        u.setApellidos(ape);
        u.setDni(dni);
        u.setCelular(cel);
        u.setUsername(user);
        u.setEmail(email);
        u.setClave(pass);
        u.setRol(r);
        u.setEstado(1);

        return u;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        Usuario u;
        switch (action){
            case "registrarUsuario":
                u = construirUsuarioDesdeRequest(req);
                boolean registrar = daoUsuario.registrarUsuario(u);

                if (registrar){
                    resp.sendRedirect("UsuarioServlet?action=listar");
                } else {
                    req.setAttribute("error", "Hubo un problema al registrar");
                    req.getRequestDispatcher("formulario_usuario.jsp").forward(req, resp);
                }
                break;
            case "actualizarUsuario":
                u = construirUsuarioDesdeRequest(req);
                u.setIdUsuario(Integer.parseInt(req.getParameter("txtIdUsuario")));
                boolean actualizar = daoUsuario.actualizarPerfilUsuario(u);

                if (actualizar){
                    resp.sendRedirect("UsuarioServlet?action=listar");
                } else{
                    req.setAttribute("error", "Hubo un problema al actualizar");
                    req.getRequestDispatcher("formulario_usuario.jsp").forward(req, resp);
                }
                break;
            case "cambiarEstadoUsuario":
                int idUsuario = Integer.parseInt(req.getParameter("txtIdUsuario"));
                int estado = Integer.parseInt(req.getParameter("cboEstado"));
                boolean cambiarEstado = daoUsuario.actualizarEstadoUsuario(idUsuario, estado);

                if (cambiarEstado){
                    resp.sendRedirect("UsuarioServlet?action=listar");
                } else{
                    req.setAttribute("error", "Hubo un problema al actualizar");
                    req.getRequestDispatcher("formulario_usuario.jsp").forward(req, resp);
                }
                break;
            default:
                resp.sendRedirect("error.jsp");
        }
    }
}
