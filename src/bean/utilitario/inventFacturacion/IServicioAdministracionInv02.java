/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.inventFacturacion;

/**
 *
 * @author henrypb
 */
public interface IServicioAdministracionInv02 {
    
    public abstract Boolean precioReferencialExiste( String codProducto );  
    
    public abstract Double precioUnidad( String codProducto,java.sql.Date alFecha ); 
            
    public abstract Double precioKsg( String codProducto,java.sql.Date alFecha ); 
    
}  // IServicioAdministracionInv02.  
