package org.example.sistemawilson.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConexion {

    public static Connection getConnection(){
        Connection cn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/bdWilson?useTimezOne=true&serverTimezone=UTC";
            String usr = "root";
            String psw = "mysql";
            cn = DriverManager.getConnection(url,usr,psw);
            System.out.println("conexion ok");
        } catch (ClassNotFoundException ex){
            System.out.println("No hay Driver!!");
        } catch (SQLException ex) {
            System.out.println("Error con esta BD");
        }
        return cn;
    }

    public static void closeConexion(Connection cn){
        try {
            cn.close();
        } catch (SQLException e){
            System.out.println("Problemas con cerrar la conexion");
        }
    }
}
