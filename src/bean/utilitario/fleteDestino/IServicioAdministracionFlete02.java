/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.fleteDestino;

/**
 *
 * @author henrypb
 */
public interface IServicioAdministracionFlete02 {
    
    public abstract Boolean estadoRegistrado ( String codEstado ); 
    
    public abstract Flete02 getEstado( String codEstado ); 
    
}
