/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.venta;

import java.util.ArrayList;

/**
 *
 * @author henrypb
 */
public interface IServicioAdministracionVend01 {
    
    public abstract ArrayList<String> getVendedores(); 
    
    public abstract Vend01 getVendedor( String codVendedor ); 
    
    public abstract ArrayList<String> getZonaVentas();  
    
}
