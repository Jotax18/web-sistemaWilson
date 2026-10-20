package org.example.sistemawilson.dao.impl;

import org.example.sistemawilson.dao.IngresoStockDAO;
import org.example.sistemawilson.model.*;
import org.example.sistemawilson.util.MySQLConexion;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class IngresoStockImpl implements IngresoStockDAO {

    Connection cn = null;
    PreparedStatement psm = null;
    ResultSet rs = null;

    @Override
    public List<IngresoStock> listarIngresoStock() {
        List<IngresoStock> lista = new ArrayList<>();
        try {
            cn = MySQLConexion.getConnection();
            String sql = """
            SELECT
                i.id_ingreso_stock,
                i.lote_stock,
                i.estado,
                i.fecha_ingreso,
                u.id_usuario,
                u.nombres
            FROM ingreso_stock i
            INNER JOIN usuario u
            ON i.id_usuario = u.id_usuario
            WHERE i.estado IN (0, 1)
            ORDER BY i.id_ingreso_stock DESC
            """;
            psm = cn.prepareStatement(sql);
            rs = psm.executeQuery();
            while (rs.next()){
                IngresoStock ingreso = new IngresoStock();
                ingreso.setIdIngresoStock(rs.getInt("id_ingreso_stock"));
                ingreso.setLoteStock(rs.getString("lote_stock"));
                ingreso.setEstado(rs.getInt("estado"));
                Usuario usu = new Usuario();
                usu.setIdUsuario(rs.getInt("id_usuario"));
                usu.setNombres(rs.getString("nombres"));
                ingreso.setUsuario(usu);
                ingreso.setFechaIngreso(rs.getTimestamp("fecha_ingreso")
                        .toLocalDateTime()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                lista.add(ingreso);
            }
        } catch (Exception e) {
            System.out.println("Error al listar:" + e.getMessage());
        } finally {
            try {
                if (cn!= null) MySQLConexion.closeConexion(cn);
                if (psm!= null) psm.close();
                if (rs!= null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    @Override
    public boolean registrarStock(IngresoStock ingresoStock) {
        try {
            // hacemos la conexion con el driver jdbc
            cn = MySQLConexion.getConnection();
            // ponemos la transaccion para que todavia no guarde cambios
            cn.setAutoCommit(false);
            // creamos el query para insertar en la cabecera
            // los datos que siempre estaran en cada transaccion para cada ingreso de stock(ingreso_stock)
            String sql = "INSERT INTO ingreso_stock (lote_stock, id_usuario) VALUES (?,?)";
            // Permite recuperar el ID autogenerado por la base de datos
            // después de insertar la cabecera del ingreso de stock por ejemplo id_ingreso_stock = 1.
            psm = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // le decimos al psm que prepare la consulta de sql pero a la vez le decimos que hay un id autogenerado
            // por lo que RETURN_GENERATED_KEYS nos permitira recuperarlo despues
            psm.setString(1, ingresoStock.getLoteStock());
            psm.setInt(2, ingresoStock.getUsuario().getIdUsuario());
            // ejecutamos executeUpdate para registrar y actualizar la informacion
            psm.executeUpdate();
            // le decimos a resulset que queremos que tenga la informacion de psm y ademas el id autogenerado de psm
            // para que luego nos devuelva las filas de cada columna
            rs = psm.getGeneratedKeys();

            // declaramos idIngreso = 0 y lo inicializamos con 0 para darle un valor mas adenlante
            int idIngreso = 0;
            //le decimos al resulset que pase al siguiente
            if (rs.next()){
                //le damos el valor a idIngreso del resulset de la primera columna que es el idIngesoStock autogenerado de hace un rato
                idIngreso = rs.getInt(1);
            }
            if(idIngreso == 0){
                throw new SQLException("No se pudo obtener el ID del ingreso.");
            }
            // hacemos un for a DetalleIngresoStock para setear y agregar de la misma forma dentro del for cuantas veces el usuario
            // quira agregar detalles al ingreso
            for(DetalleIngresoStock d : ingresoStock.getListaDetalle()){
                //hacemos un query para insertar ahora si en el detalle
                String sql1 = "INSERT INTO detalle_ingreso_stock (id_ingreso_stock, id_producto, cantidad, costo_unitario, subtotal) VALUES (?,?,?,?,?)";
                PreparedStatement psmDetalle = cn.prepareStatement(sql1);
                psmDetalle.setInt(1, idIngreso);
                psmDetalle.setInt(2, d.getProducto().getIdProducto());
                psmDetalle.setInt(3, d.getCantidad());
                psmDetalle.setDouble(4, d.getPrecioUnitario());
                psmDetalle.setDouble(5, d.getSubtotal());
                psmDetalle.executeUpdate();
                String sql2 = "UPDATE producto SET stock_actual = stock_actual + ? WHERE id_producto = ?";
                PreparedStatement psmProducto = cn.prepareStatement(sql2);
                psmProducto.setInt(1, d.getCantidad());
                psmProducto.setInt(2, d.getProducto().getIdProducto());
                psmProducto.executeUpdate();
                psmDetalle.close();
                psmProducto.close();
            }
            cn.commit();
            return true;
        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            System.out.println("Error al ingresar stock; transacción revertida");
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (cn != null) {
                    cn.setAutoCommit(true);
                    MySQLConexion.closeConexion(cn);
                }

                if (psm != null) psm.close();
                if (rs != null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean anularIngresoStock (int idIngresoStock, int estado){
        try {
            cn = MySQLConexion.getConnection();
            //0. siempre cuando es transaccion y comienza le decimos a la conexion que desactive cambios para prevenir
            cn.setAutoCommit(false);
            //1. verificamos si el ingreso de stock existe, si existe obtenemos el id sino existe lanzamos una exception
            String sql = "SELECT id_ingreso_stock FROM ingreso_stock WHERE id_ingreso_stock = ? AND estado = 1";
            psm = cn.prepareStatement(sql);
            psm.setInt(1, idIngresoStock);
            rs = psm.executeQuery();
            if (!rs.next()){
                throw new SQLException("El ingreso no existe o ya fue anulado");
            }
            //2. select para obtener el detalle del ingreso de stock, con el id_ingreso_stock que ya teniamos previamente
            //especificamente le hacemos select al detalle y obtnemos cuantos productos y que cantidades tiene ese detalle de ingreso
            //para saber lo que se tiene que restar de cada producto
            String selectDetalleIngreso = "SELECT id_producto, cantidad FROM detalle_ingreso_stock WHERE id_ingreso_stock = ?";
            psm = cn.prepareStatement(selectDetalleIngreso);
            psm.setInt(1, idIngresoStock);
            rs = psm.executeQuery();
            //3. obtenemos y guardamos en una variable lo que proximamente querremos restar
            while (rs.next()){
                int idProducto = rs.getInt("id_producto");
                int cantidad = rs.getInt("cantidad");
                //4. revertimos (actualizamos) el stock de cada producto con las variables previamente declaradas
                String sql1 = "UPDATE producto SET stock_actual = stock_actual - ? WHERE id_producto = ?";
                PreparedStatement psmUpdate = cn.prepareStatement(sql1);
                psmUpdate.setInt(1, cantidad);
                psmUpdate.setInt(2, idProducto);
                psmUpdate.executeUpdate();
                psmUpdate.close();
            }
            //4. Anulamos la cabecera del ingreso_stock del cual estabamos obteniendo el detalle
            String sql2 = "UPDATE ingreso_stock SET estado = 0 WHERE id_ingreso_stock = ?";
            psm = cn.prepareStatement(sql2);
            psm.setInt(1, idIngresoStock);
            psm.executeUpdate();
            cn.commit();
            //5. en caso esta bien la transaccion hacemos return true
            return true;
        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Error al anular ingreso Stock, transaccion revertida");
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (cn != null) {
                    cn.setAutoCommit(true);
                    MySQLConexion.closeConexion(cn);
                }

                if (psm != null) psm.close();
                if (rs != null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<IngresoStock> buscarIngresoStock(String terminoBusqueda) {
        ArrayList<IngresoStock> lista = new ArrayList<>();
       try {
           cn = MySQLConexion.getConnection();
           String sql = """
            SELECT
                i.id_ingreso_stock,
                i.lote_stock,
                i.fecha_ingreso,
                u.id_usuario,
                u.nombres,
            FROM ingreso_stock i
            INNER JOIN usuario u
            ON i.id_usuario = u.id_usuario
            WHERE i.estado = 1 AND (i.lote_stock LIKE ?)
            ORDER BY i.id_ingreso_stock DESC
            """;
           psm = cn.prepareStatement(sql);
           psm.setString(1, "%" + terminoBusqueda + "%");
           rs = psm.executeQuery();
           while (rs.next()){
                IngresoStock ingreso = new IngresoStock();
                ingreso.setIdIngresoStock(rs.getInt("id_ingreso_stock"));
                ingreso.setFechaIngreso(rs.getString("lote_stock"));

                Usuario usu = new Usuario();
                usu.setNombres(rs.getString("id_usuario"));
                ingreso.setUsuario(usu);

                ingreso.setFechaIngreso(rs.getString("fecha_ingreso"));
                ingreso.setEstado(rs.getInt("estado"));
                lista.add(ingreso);
           }
       } catch (Exception e) {
           System.out.println("Error al bucar por termino de busqueda: "+ e.getMessage());
       } finally {
           try {
               if (rs != null) rs.close();
               if (psm != null) psm.close();
               if (cn != null) MySQLConexion.closeConexion(cn);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       return  lista;
    }

    @Override
    public IngresoStock buscarIngresoStockPorId(int idIngresoStock) {
        try {
            cn = MySQLConexion.getConnection();
            String sql = "SELECT * FROM ingreso_stock WHERE id_ingreso_stock = ? AND estado = 1";
            psm = cn.prepareStatement(sql);
            psm.setInt(1, idIngresoStock);
            rs = psm.executeQuery();

            while (rs.next()){
                IngresoStock ingreso = new IngresoStock();
                ingreso.setIdIngresoStock(rs.getInt("id_ingreso_stock"));
                ingreso.setFechaIngreso(rs.getString("lote_stock"));

                Usuario usu = new Usuario();
                usu.setNombres(rs.getString("id_usuario"));
                ingreso.setUsuario(usu);

                ingreso.setFechaIngreso(rs.getString("fecha_ingreso"));
                ingreso.setEstado(rs.getInt("estado"));

                return ingreso;
            }
        } catch (Exception e) {
            System.out.println("Error al buscar por ID: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (psm != null) psm.close();
                if (cn != null) MySQLConexion.closeConexion(cn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
