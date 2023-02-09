package Modelo;

import java.sql.*;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author Grupo E
 */
public class ConsultasHotel extends Conexion {
    
    //METODO REGISTRAR CLIENTE
    public boolean registrar(Hotel h) {

        CallableStatement ps = null;
        Connection con = getConnection();

        String sql = "{CALL REGISTRAR_HOTEL(INCREMENTADOIDHOTEL.NEXTVAL,?,?,?,?,?)}";//Insertando datos en la tabla HOTEL

        try {
            ps = (CallableStatement) con.prepareCall(sql);
            ps.setString(1, h.getNombre());
            ps.setString(2, h.getUbicacion());
            ps.setString(3, h.getTelefono());
            ps.setString(4, h.getDisponibilidad());
            ps.setString(5, h.getServicios());
            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
                return false;
            }
        }
    }
    
    //METODO MODIFICAR CLIENTE
     public boolean modificar(Hotel h) {

        CallableStatement ps = null;
        Connection con = getConnection();

        String sql = "{CALL ACTUALIZAR_HOTEL(?,?,?,?,?,?)}";
   
        try {
            
            ps = con.prepareCall(sql);
            ps.setInt(1, h.getIdHotel());
            ps.setString(2, h.getNombre());
            ps.setString(3, h.getUbicacion());
            ps.setString(4, h.getTelefono());
            ps.setString(5, h.getDisponibilidad());
            ps.setString(6, h.getServicios());
            //Envia la sentencia de Actualizar
            ps.executeUpdate();
            con.close();
            return true;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
                return false;
            }
        }
    }
       
    
    //METODO LISTAR CLIENTES
    public static ResultSet ListarTabla(String consulta){
        Statement sql;
        ResultSet rs=null;
        Connection con = getConnection();
        try {
            sql=con.createStatement();
            rs=sql.executeQuery(consulta);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }
    
     //METODO ELIMINAR CLIENTE
    public static boolean Eliminar(String id) {
        int idH=Integer.parseInt(id);
        CallableStatement ps = null;
        Connection con = getConnection();

        String sql = "{CALL ELIMINAR_HOTEL(?)}";

        try {
            ps = con.prepareCall(sql);
             ps.setInt(1, idH);
            
            
            ps.execute();
            con.close();
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            
            return false;
       
        }
    }
}
