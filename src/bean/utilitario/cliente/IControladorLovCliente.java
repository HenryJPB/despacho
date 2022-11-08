/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.cliente;

/**
 *
 * @author henrypb
 */
public interface IControladorLovCliente {
       
       public abstract void ejecutarFrameLovCliente(); 

       public abstract void ejecutarDialogLovCliente(); 
       
       public abstract void ejecutarDialogLovJKTCliente();  // esta metodo manipula una JKTable añadida.  (JKT).  
}
