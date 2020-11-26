
package Database_MYSQL;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProcesosSQL {
    
    public static ConexionDB conexion = new ConexionDB();
    public static PreparedStatement procedimiento_listo;
    public static ResultSet resultado;
    public static String sql;
    public static int resultado_num = 0;
    
    
    public int registrar(String nombre, String username, String password){
        int result = 0;
        Connection conexion = null;
        
        String insertUser = ("INSERT INTO usuarios(id, nombre, usuario, password) VALUES(?, ? , ? ,?) ");
        
        try{
            conexion = ConexionDB.conectarDB();
            procedimiento_listo = (PreparedStatement) conexion.prepareStatement(insertUser);
            procedimiento_listo.setString(1, null);
            procedimiento_listo.setString(2, nombre);
            procedimiento_listo.setString(3, username);
            procedimiento_listo.setString(4, password);
            result = procedimiento_listo.executeUpdate();
            procedimiento_listo.close();
            
            conexion.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return result;
    }
    
    
    public String buscarUsuario(String username){
        String search_username = null;
        Connection conexion = null;
        
        try{
            conexion = ConexionDB.conectarDB();
            String sqlSearch = ("SELECT usuario FROM usuarios WHERE usuario = '" + username + "'");
            procedimiento_listo = (PreparedStatement) conexion.prepareStatement(sqlSearch);
            resultado = procedimiento_listo.executeQuery();
            
            if(resultado.next()){
                String usuario = resultado.getString("usuario");
                search_username = usuario;
                
            }
            
            conexion.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return search_username;
    }
    
    
    public String buscarUsuarioRegistrado(String username, String password){
        String search_user = null;
        
        Connection conexion = null;
        
        try{
           conexion = ConexionDB.conectarDB();
           String sql_search_user = ("SELECT usuario, password FROM usuarios WHERE usuario = '" + username+"' && password = '" + password+"'");
           procedimiento_listo = (PreparedStatement) conexion.prepareStatement(sql_search_user);
           resultado = procedimiento_listo.executeQuery();
           
           if(resultado.next()){
               search_user = "aprobado";
           }else{
               search_user = "denegado";
           }
           
           conexion.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        
        
        return search_user;
    }

    
    
    
    
    
}
