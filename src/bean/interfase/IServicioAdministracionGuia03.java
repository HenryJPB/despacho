/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.interfase;

import bean.entidad.Guia03;
import java.util.List;

/**
 *
 * @author henrypb
 */
public interface IServicioAdministracionGuia03 {
    
       public void transportistaRegistrado();  
       
       public List<Guia03> getTransportistasActivos(); 
       
       public Boolean existeTransportista( String cedula );  
       
       public Guia03 getDatosTransportista( String ciTransportista );  
}
