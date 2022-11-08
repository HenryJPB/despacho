/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.interfase;

/**
 *
 * @author henrypb
 */
public interface IControladorGuiaDespacho {
    
    public abstract void agregarRegistro();
    
    public abstract void editarRegistro(); 
    
    public abstract void actualizarRegistro();
    
    public abstract void eliminarRegistro(); 
    
    public abstract void limpiarCampos(); 
    
    public abstract void cancelar(); 
    
}