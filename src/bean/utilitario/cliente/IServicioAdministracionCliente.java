/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.cliente;

import java.util.List;

/**
 *
 * @author henrypb
 */
public interface IServicioAdministracionCliente {
    
       public abstract List<Cliente> getClientesActivos();  
       
       public abstract Boolean clienteRegistrado( String codCliente );
       
       public abstract Cliente getCliente( String codCliente ); 
       
}  // IServicioAdministracionCliente.  
