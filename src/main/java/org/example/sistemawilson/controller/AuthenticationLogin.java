package org.example.sistemawilson.controller;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.sistemawilson.model.Usuario;
import org.example.sistemawilson.model.Utils;

import java.io.IOException;

@WebFilter(filterName = "AuthenticationLogin", urlPatterns = {"/*"})
public class AuthenticationLogin implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;
        HttpSession sesion = httpReq.getSession(false);

        String reqURI = httpReq.getRequestURI();
        String contextPath = httpReq.getContextPath();

        // Rutas públicas (Ajusta los nombres según tus archivos)
        String loginURI = contextPath + "/LoginServlet";
        String loginJSP = contextPath + "/login_usuario.jsp";

        boolean isStaticResource = reqURI.startsWith(contextPath + "/css/") ||
                reqURI.startsWith(contextPath + "/js/") ||
                reqURI.contains("bootstrap");

        boolean isLoginReq = reqURI.equals(loginURI) || reqURI.equals(loginJSP);
        boolean isLoggedIn = (sesion != null && sesion.getAttribute("usuarioSesion") != null);

        if (isLoginReq || isStaticResource) {
            chain.doFilter(req, resp);
            return;
        }

        if (!isLoggedIn){
            httpResp.sendRedirect(loginJSP);
            return;
        }

        Usuario usuarioSesion  = (Usuario) sesion.getAttribute("usuarioSesion");
        String nombreRol = usuarioSesion.getRol().getNombreRol();


        if (nombreRol.equals(Utils.ROL_ADMIN)){
            chain.doFilter(req, resp);
            return;
        }

        String[] modulosProhibidos = {
                "/UsuarioServlet",
                "lista_usuario.jsp",
                "formulario_usuario.jsp"
        };

        boolean intentoEntrarZonaProhibida = false;
        for (String zona : modulosProhibidos) {
            if (reqURI.contains(zona)) {
                intentoEntrarZonaProhibida = true;
                break;
            }
        }

        if (intentoEntrarZonaProhibida) {
            httpResp.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso Denegado: Exclusivo para administradores.");
        } else {
            chain.doFilter(req, resp);
        }
    }

}
