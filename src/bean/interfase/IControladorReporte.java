/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.interfase;

/**
 *
 * @author henrypb
 */
public interface IControladorReporte {
       
       public abstract void ejecutarReporteGuiaDespacho();  
    
       public abstract void imprimirGuia( String guia1, String guia2 );  
}
