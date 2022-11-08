/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.venta;

/**
 *
 * @author henrypb
 */
public interface IServicioAdministracionVend03 {
       
    public abstract Boolean existePedido( String nroPedido );
    
    public abstract Vend03  getPedido( String nroPedido );  
    
}
