package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Grupo E
 */
public class Conexion 
{
    private static Connection conn =null;
    private static String login = "Proyecto";//Usuario de la Base de Datos
    private static String password = "proyecto";//Contraseña de la Base de Datos
    private static String url = "jdbc:oracle:thin:@//localhost:1522/XE";//url conexion a la base de Datos llamada "Proyecto"
    //jdbc:oracle:thin:@//localhost:1522/XE
    public static Connection getConnection(){
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");//Driver jdbc para establecer la conexion
            conn = DriverManager.getConnection(url,login,password);
            conn.setAutoCommit(false);
            
            if(conn!=null){
                System.out.println("Conexion Exitosa");
            }else{
                System.out.println("Conexion Erronea");
            }
        } catch (ClassNotFoundException|SQLException e) {
            JOptionPane.showMessageDialog(null, "Conexion Erronea" + e.getMessage());
        
        }
        return conn;
    }
    
    public void desconexion(){
        try {
            conn.close();
        } catch (Exception e) {
            System.out.println("Error al desconectar "+ e.getMessage());
        }
    }
    
    
    public static void main(String[] args) {
        Conexion c=new Conexion();
        c.getConnection();
    }
}
