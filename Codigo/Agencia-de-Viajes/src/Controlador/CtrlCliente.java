package Controlador;


import java.sql.*;
import Modelo.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @Grupo E
 */
public class CtrlCliente implements ActionListener {

    //Variables
    private Cliente mod;
    private ConsultasCliente modC;
    private frmRegistrarClientes frm;
    private frmEliminarCliente frmE;
    private frmModificarCliente frmM;
    private frmBuscarCliente frmB;


    
    //metodo constructor
    public CtrlCliente(Cliente mod, ConsultasCliente modC, frmRegistrarClientes frm, frmEliminarCliente frmE, frmModificarCliente frmM, frmBuscarCliente frmB) {
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;
        this.frmE = frmE;
        this.frmM = frmM;
        this.frmB = frmB;

        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frmE.btnEliminarRegistro.addActionListener(this);
        this.frmM.btnActualizar.addActionListener(this);
    }

    
    public void iniciar() {
        frm.setTitle("Clientes");
        frm.setLocationRelativeTo(null);
    }
    
    
    
    //LISTAR en la interfaz de LISTAR CLIENTES
    public void Listar() {
        DefaultTableModel md = new DefaultTableModel();
        ResultSet rs = ConsultasCliente.ListarTabla("select * from CONSUL_CLIENTES");
        md.setColumnIdentifiers(new Object[]{"Id", "Cedula", "Nombres", "Apellidos", "Telefono", "Direccion", "Email"});

        try {
            while (rs.next()) {
                md.addRow(new Object[]{rs.getInt("id"), rs.getString("Cedula"), rs.getString("Nombres"), rs.getString("Apellidos"), rs.getString("Telefono"), rs.getString("Direccion"), rs.getString("Email")});
                frmE.tblCliente.setModel(md);
                frmM.tblCliente.setModel(md);
                frmB.tblCliente.setModel(md);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    
    
   //ELIMINAR CLIENTE CONTROLADOR
    public void Eliminar(){
        int fila =frmE.tblCliente.getSelectedRowCount();
        //si no selecciona ningun registro
        if(fila<1){
            JOptionPane.showMessageDialog(null, "Debe de seleccionar un registro de la tabla ","AVISO",JOptionPane.INFORMATION_MESSAGE);
        }else{//caso contrario eliminar registro
            if(modC.Eliminar(frmE.tblCliente.getValueAt(frmE.tblCliente.getSelectedRow(), 0).toString())){
            JOptionPane.showMessageDialog(null, "Registro Eliminado!"); 
            }
        }
    
    }

    
    
//BOTONES frmCliente --- llamando a los metodos en ConsultasClientes
    @Override
    public void actionPerformed(ActionEvent e) {

        
        //Guardar Cliente 
        if (e.getSource() == frm.btnGuardar) {
            
            mod.setCedula(frm.txtCedula.getText());
            mod.setNombres(frm.txtNombres.getText());
            mod.setApellidos(frm.txtApellidos.getText());
            mod.setTelefono(frm.txtTelefono.getText());
            mod.setDireccion(frm.txtDireccion.getText());
            mod.setEmail(frm.txtEmail.getText());

            if (modC.registrar(mod)) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar");
            }
        }
        
        
        if (e.getSource() == frmM.btnActualizar) {
            mod.setIdCliente(Integer.parseInt(frmM.txtIdCliente.getText()));
            mod.setCedula(frmM.txtCedula.getText());
            mod.setNombres(frmM.txtNombres.getText());
            mod.setApellidos(frmM.txtApellidos.getText());
            mod.setTelefono(frmM.txtTelefono.getText());
            mod.setDireccion(frmM.txtDireccion.getText());
            mod.setEmail(frmM.txtEmail.getText());

            if (modC.modificar(mod)) {
                JOptionPane.showMessageDialog(null, "Registro Actualizado");
                frmM.buscarCliente(frmM.txtBusqueda.getText());
                frmM.txtBusqueda.setText("");
                frmM.txtIdCliente.setText("");
                frmM.txtCedula.setText("");
                frmM.txtNombres.setText("");
                frmM.txtApellidos.setText("");
                frmM.txtTelefono.setText("");
                frmM.txtDireccion.setText("");
                frmM.txtEmail.setText("");
                
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
        frm.txtCedula.setText("");
        frm.txtNombres.setText("");
        frm.txtApellidos.setText("");
        frm.txtTelefono.setText("");
        frm.txtDireccion.setText("");
        frm.txtEmail.setText("");
    }
    
    
    
    
    
    
}
