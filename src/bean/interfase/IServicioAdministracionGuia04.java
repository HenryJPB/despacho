/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.interfase;

import bean.entidad.Guia04;
import java.util.List;

/**
 *
 * @author henrypb
 * Datos de la Unidad de Transporte: 
 * 
 */
public interface IServicioAdministracionGuia04 {
    
       public Boolean unidadTransporteRegistrado(String ciTransportista, String codTransporte );  
      
       public List<Guia04> getLovUnidadesTransporte(String ciTransportista, String codTransporte );  
       
       public Guia04 getDatosUnidadTransporte( String ciTransportista, String codTransporte );  
}
