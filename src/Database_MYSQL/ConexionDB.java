/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database_MYSQL;

import java.sql.DriverManager;
import com.mysql.jdbc.Connection;
import java.sql.SQLException;

/**
 *
 * @author JosephPC
 */
public class ConexionDB {

    public static String host = "jdbc:mysql://localhost/hotelera";
    public static String username = "root";
    public static String pass = "";
    public static final String CLASE = "com.mysql.jdbc.Driver";

    public static Connection conectarDB() throws SQLException {
        Connection conexion = null;

        try {
            Class.forName(CLASE);
            conexion = (Connection) DriverManager.getConnection(host, username, pass);
            System.out.print("Conexion Establecida");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.print(e);
        }

        return conexion;
    }

}
