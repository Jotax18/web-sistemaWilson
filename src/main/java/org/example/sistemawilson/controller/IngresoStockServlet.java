package org.example.sistemawilson.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.sistemawilson.dao.IngresoStockDAO;
import org.example.sistemawilson.dao.ProductoDAO;
import org.example.sistemawilson.dao.UsuarioDAO;
import org.example.sistemawilson.dao.impl.IngresoStockImpl;
import org.example.sistemawilson.dao.impl.ProductoDAOImpl;
import org.example.sistemawilson.dao.impl.UsuarioDAOImpl;
import org.example.sistemawilson.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/IngresoStockServlet")
public class IngresoStockServlet extends HttpServlet {

    IngresoStockDAO daoIngreso = new IngresoStockImpl();
    IngresoStockImpl daoImp = new IngresoStockImpl();
    UsuarioDAO daoUsuario = new UsuarioDAOImpl();
    ProductoDAO daoProducto = new ProductoDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            action = "listar";
        }

        switch (action) {
            case "listar":
                List<IngresoStock> lista = daoImp.listarIngresoStock();
                req.setAttribute("lista", lista);
                req.getRequestDispatcher("lista_ingreso_stock.jsp").forward(req, resp);
                break;
            case "cargarFormularioRegistrar":
                List<Producto> listaProductos = daoProducto.listarProducto();
                req.setAttribute("listaProductos", listaProductos);
                req.getSession().getAttribute("usuarioSession");
                req.getRequestDispatcher("formulario_ingreso_stock.jsp").forward(req, resp);
                break;
            case "cargarFormularioActualizar":
                int id = Integer.parseInt(req.getParameter("id"));
                IngresoStock ingresoEncontrado = daoIngreso.buscarIngresoStockPorId(id);
                //List<Proveedor> listProve = daoProveedor.listarProveedor();
                //req.setAttribute("listaProveedor", listProve);
                List<Usuario> listUsuario = daoUsuario.listarUsuario();
                req.setAttribute("listUsuario", listUsuario);
                req.setAttribute("ingresoEncontrado", ingresoEncontrado);
                req.getRequestDispatcher("formulario_ingreso_stock.jsp").forward(req, resp);
                break;
            case "buscarIngreso":
                String ingresoGeneral = req.getParameter("txtIngresoGeneral");
                List<IngresoStock> listaGeneral = daoIngreso.buscarIngresoStock(ingresoGeneral);
                req.setAttribute("listGeneral", listaGeneral);
                req.getRequestDispatcher("lista_ingreso_stock.jsp").forward(req, resp);
                break;
            default:
                resp.sendRedirect("error.jsp");
        }
    }

    private IngresoStock construirIngresoStockDesdeReq (HttpServletRequest req){
        String lote = req.getParameter("txtLoteStock");
        //JAVA SABE QUE DEVOLVERA EL ID DE ESE USUARIO DE LA SESSION POR EL ATRIBUTO "usuarioSession"
        //porque ya fue declarada antes en loginServlet y hay una cookie que esta guardando la session con ese nombre y la podremos
        //recuperar pidiendola con getAtributte("ususarioSession")
        Usuario usuSesion = (Usuario) req.getSession().getAttribute("usuarioSession");

        IngresoStock ingStock = new IngresoStock();
        ingStock.setLoteStock(lote);
        ingStock.setUsuario(usuSesion);
        return ingStock;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null) {
            action = "listar";
        }

        switch (action) {
            case "registrarIngreso":
                // 1. Armar la cabecera desde la sesión y el formulario
                String lote = req.getParameter("txtLoteStock");
                Usuario usuarioSesion = (Usuario) req.getSession().getAttribute("usuarioSesion");

                IngresoStock ingreso = new IngresoStock();
                ingreso.setLoteStock(lote);
                ingreso.setUsuario(usuarioSesion);

                // 2. Leer los arrays del detalle
                String[] idProductos = req.getParameterValues("idProducto");
                String[] cantidades = req.getParameterValues("cantidad");
                String[] costos = req.getParameterValues("costoUnitario");
                String[] subtotales = req.getParameterValues("subtotal");

                // 3. Armar la listaDetalle
                List<DetalleIngresoStock> listaDetalle = new ArrayList<>();
                for (int i = 0; i < idProductos.length; i++) {
                    Producto p = new Producto();
                    p.setIdProducto(Integer.parseInt(idProductos[i]));

                    DetalleIngresoStock detalle = new DetalleIngresoStock();
                    detalle.setProducto(p);
                    detalle.setCantidad(Integer.parseInt(cantidades[i]));
                    detalle.setPrecioUnitario(Double.parseDouble(costos[i]));
                    detalle.setSubtotal(Double.parseDouble(subtotales[i]));

                    listaDetalle.add(detalle);
                }

                // 4. Asignar la lista a la cabecera
                ingreso.setListaDetalle(listaDetalle);

                // 5. Llamar al DAO
                boolean registrar = daoIngreso.registrarStock(ingreso);
                if (!registrar) {
                    req.setAttribute("error", "Hubo un problema al registrar el ingreso.");
                    req.setAttribute("listaProductos", daoProducto.listarProducto());
                    req.getRequestDispatcher("formulario_ingreso_stock.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("IngresoStockServlet?action=listar");
                }
                break;

            case "cambiarEstadoIngreso":
                int id = Integer.parseInt(req.getParameter("txtIdIngresoStock"));
                int estado = Integer.parseInt(req.getParameter("estado"));
                boolean cambiarEstado = daoIngreso.anularIngresoStock(id, estado);
                if (!cambiarEstado) {
                    req.setAttribute("error", "Hubo un problema al anular el ingreso.");
                    req.getRequestDispatcher("lista_ingreso_stock.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("IngresoStockServlet?action=listar");
                }
                break;

            default:
                resp.sendRedirect("error.jsp");
        }
    }
}
