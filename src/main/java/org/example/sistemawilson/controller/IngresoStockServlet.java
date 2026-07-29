package org.example.sistemawilson.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import org.example.sistemawilson.dao.IngresoStockDAO;
import org.example.sistemawilson.dao.impl.IngresoStockImpl;

@WebServlet("/IngresoStockServlet")
public class IngresoStockServlet extends HttpServlet {

    IngresoStockDAO daoIngreso = new IngresoStockImpl();

}
