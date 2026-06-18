package org.example.sistemawilson.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.sistemawilson.dao.CategoriaDAO;
import org.example.sistemawilson.dao.MarcaDAO;
import org.example.sistemawilson.dao.ProductoDAO;
import org.example.sistemawilson.dao.impl.CategoriaDAOImpl;
import org.example.sistemawilson.dao.impl.MarcaDAOImpl;
import org.example.sistemawilson.dao.impl.ProductoDAOImpl;
import org.example.sistemawilson.model.Categoria;
import org.example.sistemawilson.model.Marca;
import org.example.sistemawilson.model.Producto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ProductoServlet")
public class ProductoServlet extends HttpServlet {

    ProductoDAO daoProducto = new ProductoDAOImpl();
    MarcaDAO daoMarca = new MarcaDAOImpl();
    CategoriaDAO daoCategoria = new CategoriaDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null){
            action = "listar";
        }

        switch (action){
            case "listar":
                List<Producto> listaProd = daoProducto.listarProducto();
                req.setAttribute("listaProducto", listaProd);
                req.getRequestDispatcher("lista_producto.jsp").forward(req, resp);
                break;
            case "cargarFormularioRegistrar":
                List<Marca> listaMarRe = daoMarca.listaMarca();
                List<Categoria> listaCatRe = daoCategoria.listaCategoria();
                req.setAttribute("listaMarcas", listaMarRe);
                req.setAttribute("listaCategorias", listaCatRe);
                req.getRequestDispatcher("formulario_producto.jsp").forward(req, resp);
                break;
            case "cargarFormularioActualizar":
                int id = Integer.parseInt(req.getParameter("id"));
                Producto productoEncontrado = daoProducto.buscarProductoId(id);
                List<Marca> listaMarAc = daoMarca.listaMarca();
                List<Categoria> listaCatAc = daoCategoria.listaCategoria();
                req.setAttribute("listaMarcas", listaMarAc);
                req.setAttribute("listaCategorias", listaCatAc);
                req.setAttribute("producto", productoEncontrado);
                req.getRequestDispatcher("formulario_producto.jsp").forward(req, resp);
                break;
            case "buscarProductoGeneral":
                String prodGeneral = req.getParameter("txtProductoGeneral");
                List<Producto> listaGeneral = daoProducto.buscarProductoGeneral(prodGeneral);
                req.setAttribute("listGeneral", listaGeneral);
                req.getRequestDispatcher("lista_producto.jsp").forward(req, resp);
                break;
            default:
                resp.sendRedirect("error.jsp");
        }
    }

    private Producto construirProductoDesdeReq (HttpServletRequest req){
        String sku = req.getParameter("txtSku");
        String nom = req.getParameter("txtNombreProd");
        int mar = Integer.parseInt(req.getParameter("cboMarca"));
        Marca m = new Marca();
        m.setIdMarca(mar);
        String mod = req.getParameter("txtModelo");
        int cat = Integer.parseInt(req.getParameter("cboCategoria"));
        Categoria c = new Categoria();
        c.setIdCategoria(cat);
        String des = req.getParameter("txtDescripcion");
        double preCom = Double.parseDouble(req.getParameter("txtPrecioCompra"));
        double preVen = Double.parseDouble(req.getParameter("txtPrecioVenta"));
        int stkAct = Integer.parseInt(req.getParameter("txtStockActual"));
        int stkMin = Integer.parseInt(req.getParameter("txtStockMinimo"));


        Producto prod = new Producto();
        prod.setCodigoSku(sku);
        prod.setNombre(nom);
        prod.setMarca(m);
        prod.setModelo(mod);
        prod.setCategoria(c);
        prod.setDescripcion(des);
        prod.setPrecioCompra(preCom);
        prod.setPrecioVenta(preVen);
        prod.setStockActual(stkAct);
        prod.setStockMinimo(stkMin);
        return prod;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null){
            action = "listar";
        }

        Producto p;

        switch (action){
            case "listar":
                break;
            case "registrarProducto":
                p = construirProductoDesdeReq(req);
                boolean registrar = daoProducto.registrarProducto(p);
                if (!registrar){
                    req.setAttribute("error", "Hubo un problema al registrar!");
                    req.getRequestDispatcher("formulario_producto.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("ProductoServlet?action=listar");
                    System.out.println("Se registro el producto" + p);
                }
                break;
            case "actualizarProducto":
                p = construirProductoDesdeReq(req);
                p.setIdProducto(Integer.parseInt(req.getParameter("txtId")));
                boolean actualizar = daoProducto.actualizarProducto(p);
                if (!actualizar){
                    req.setAttribute("error", "Hubo un problema al actualizar!");
                    req.setAttribute("producto", p);
                    req.getRequestDispatcher("formulario_producto.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("ProductoServlet?action=listar");
                    System.out.println("Se actualizo el producto" + p);
                }
                break;
            case "actualizarEstado":
                int idProducto = Integer.parseInt(req.getParameter("id"));
                int estado = Integer.parseInt(req.getParameter("estado"));
                boolean cambiarEstado = daoProducto.actualizarEstadoProducto(idProducto, estado);

                if (cambiarEstado){
                    resp.sendRedirect("ProductoServlet?action=listar");
                } else{
                    req.setAttribute("error", "Hubo un problema al actualizar");
                    req.getRequestDispatcher("formulario_producto.jsp").forward(req, resp);
                }
                break;
            default:
                resp.sendRedirect("error.jsp");
        }
    }
}
