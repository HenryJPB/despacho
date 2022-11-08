/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.interfase;

import bean.entidad.Guia02;
import java.util.List;

/**
 *
 * @author henrypb
 */
public interface IServicioAdministracionGuia02 {

    public abstract Boolean      existeRegDetalleGuia(  String nroGuia );  
    
    public abstract List<Guia02> getDetalleGuia(String nroGuia );  
    
    public abstract void incluirProductoGuia(String nroGuia, Guia02 itemGuia02 ); 
    
    public abstract void actualizarProductoGuia( String nroGuia, Guia02 itemGuia02 ); 

    public abstract void eliminarProductosGuia( String nroGuia ); 
    
}
