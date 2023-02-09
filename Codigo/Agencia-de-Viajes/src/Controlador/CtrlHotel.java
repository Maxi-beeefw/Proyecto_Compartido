package Controlador;


import java.sql.*;
import Modelo.ConsultasHotel;
import Modelo.Hotel;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @Grupo E
 */
public class CtrlHotel implements ActionListener {

    //Variables
    private Hotel mod;
    private ConsultasHotel modC;
    private frmRegistrarHotel frmR;
    private frmEliminarHotel frmE;
    private frmModificarHotel frmM;
    private frmBuscarHotel frmB;


    
    //metodo constructor
    public CtrlHotel(Hotel mod, ConsultasHotel modC, frmRegistrarHotel frmR, frmEliminarHotel frmE, frmModificarHotel frmM, frmBuscarHotel frmB) {
        this.mod = mod;
        this.modC = modC;
        this.frmR = frmR;
        this.frmE = frmE;
        this.frmM = frmM;
        this.frmB = frmB;

        this.frmR.btnGuardar.addActionListener(this);
        this.frmR.btnModificar.addActionListener(this);
        this.frmE.btnEliminarRegistro.addActionListener(this);
        this.frmM.btnActualizar.addActionListener(this);
    }

    
    public void iniciar() {
        frmR.setTitle("Hoteles");
        frmR.setLocationRelativeTo(null);
    }
    
    
    
    //LISTAR en la interfaz de LISTAR CLIENTES
    public void Listar() {
        DefaultTableModel md = new DefaultTableModel();
        ResultSet rs = ConsultasHotel.ListarTabla("SELECT * FROM HOTEL ORDER BY IDHOTEL");
        md.setColumnIdentifiers(new Object[]{"IDHOTEL", "NOMBRE", "UBICACION", "TELEFONO", "DISPONIBILIDAD", "SERVICIOS"});

        try {
            while (rs.next()) {
                md.addRow(new Object[]{rs.getInt("IDHOTEL"), rs.getString("NOMBRE"), rs.getString("UBICACION"), rs.getString("TELEFONO"), rs.getString("DISPONIBILIDAD"), rs.getString("SERVICIOS")});
                frmE.tblHotel.setModel(md);
                frmM.tblHotel.setModel(md);
                frmB.tblHotel.setModel(md);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    
    
   //ELIMINAR CLIENTE CONTROLADOR
    public void Eliminar(){
        int fila =frmE.tblHotel.getSelectedRowCount();
        System.out.println(fila);
        //si no selecciona ningun registro
        if(fila<1){
            JOptionPane.showMessageDialog(null, "Debe de seleccionar un registro de la tabla ","AVISO",JOptionPane.INFORMATION_MESSAGE);
        }else{//caso contrario eliminar registro
            if(modC.Eliminar(frmE.tblHotel.getValueAt(frmE.tblHotel.getSelectedRow(), 0).toString())){
            JOptionPane.showMessageDialog(null, "Registro Eliminado!"); 
            }
        
        }
    
    }

    
    
//BOTONES frmCliente --- llamando a los metodos en ConsultasClientes
    @Override
    public void actionPerformed(ActionEvent e) {

        
        //Guardar Cliente 
        if (e.getSource() == frmR.btnGuardar) {
            
            mod.setNombre(frmR.txtNombre.getText());
            mod.setUbicacion(frmR.txtUbicacion.getText());
            mod.setTelefono(frmR.txtTelefono.getText());
            mod.setDisponibilidad(frmR.txtDisponibilidad.getText());
            mod.setServicios(frmR.txtServicios.getText());

            if (modC.registrar(mod)) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar");
            }
        }
        
        
        if (e.getSource() == frmM.btnActualizar) {
            mod.setIdHotel(Integer.parseInt(frmM.txtIdHotel.getText()));
            mod.setNombre(frmM.txtNombre.getText());
            mod.setUbicacion(frmM.txtUbicacion.getText());
            mod.setTelefono(frmM.txtTelefono.getText());
            mod.setDisponibilidad(frmM.txtDisponibilidad.getText());
            mod.setServicios(frmM.txtServicios.getText());

            if (modC.modificar(mod)) {
                JOptionPane.showMessageDialog(null, "Registro Actualizado");
                frmM.buscarHotel(frmM.txtBusqueda.getText());
//                frmM.txtBusqueda.setText("");
                frmM.txtIdHotel.setText("");
                frmM.txtNombre.setText("");
                frmM.txtUbicacion.setText("");
                frmM.txtTelefono.setText("");
                frmM.txtDisponibilidad.setText("");
                frmM.txtServicios.setText("");                
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar");
            }
        }

        
        if (e.getSource() == frmE.btnEliminarRegistro) {
            Eliminar();
            Listar();
            frmE.txtBusqueda.setText("");
        }

        
    }
    public void limpiar(){
        frmR.txtNombre.setText("");
        frmR.txtUbicacion.setText("");
        frmR.txtTelefono.setText("");
        frmR.txtDisponibilidad.setText("");
        frmR.txtServicios.setText("");
    }
    
    
    
    
    
    
}
