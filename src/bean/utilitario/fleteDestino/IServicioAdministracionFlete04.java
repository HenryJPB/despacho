/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.fleteDestino;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author henrypb
 */
public interface IServicioAdministracionFlete04 {
    
       public List<Flete04> getLovFleteDestinos( Date alFecha );  
       
       public Flete04       getFleteDestino( String codDestino, Date alFecha );  
       
}
