
package Main;


import Modelo.Cliente;
import Modelo.ConsultasCliente;
import Vista.MenuPrincipal;

/**
 *
 * @author Pc
 */
public class Main {

    public static void main(String[] args) {
        Cliente mod = new Cliente();
        ConsultasCliente modC =new ConsultasCliente();
        MenuPrincipal men= new MenuPrincipal();
        men.setVisible(true);
        

    }
}
