/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.interfase;

import bean.entidad.Guia05;
import java.util.List;

/**
 *
 * @author henrypb
 * Datos del Transporte:
 * 
 */
public interface IServicioAdministracionGuia05 {
    
       public Boolean transporteRegistrado( String codTransporte );  
       
       public List<Guia05> getTransportesActivos();  
       
       public Guia05 getDatosTransporte( String codTransporte );  
       
}
