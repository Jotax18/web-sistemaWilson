package org.example.sistemawilson.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/PrincipalServlet")
public class PrincipalServlet extends HttpServlet {

    public PrincipalServlet(){
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String action = req.getParameter("action");

       if (action == null){
           action = "cotizaProducto";
       }

       switch (action){
           case "cotizaProducto":
               req.getRequestDispatcher("cotizaciones_ventas.jsp").forward(req, resp);
               break;
       }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
