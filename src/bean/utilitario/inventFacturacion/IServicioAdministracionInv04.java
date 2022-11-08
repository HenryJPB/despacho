/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.inventFacturacion;

import java.util.List;

/**
 *
 * @author henrypb
 */
public interface IServicioAdministracionInv04 {
        
    public abstract Boolean productoEspecialRegistrado(String nroOrdenFab, String codProducto );
    
    public abstract Inv04 getProductoEspecial( String nroOrdenFab, String codProducto );  
    
    public abstract Boolean ordenFabRegistrada( String nroOrdenFab ); 
    
    public abstract List<Inv04> getListaProductosEspeciales(String nroOrdenFab);  
            
    public abstract Double precio(String ordenFab, String codProducto );
    
}  //  
