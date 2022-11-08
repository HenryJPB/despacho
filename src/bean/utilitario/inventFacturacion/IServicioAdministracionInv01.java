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
public interface IServicioAdministracionInv01 {
    
    public abstract Boolean productoStandardRegistrado( String codProducto );
    
    public abstract Inv01   getProductoStandard(String codProducto ); 
    
    public abstract List<Inv01> getListaProductosStandard();   
    
}  //
