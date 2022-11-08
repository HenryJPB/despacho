/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.interfase;

import bean.entidad.Guia01;

/**
 *
 * @author henrypb
 * 
 */
public interface IServicioAdministracionGuia01 {
     
       public abstract Boolean guiaRegistrada(String ncf, String nroGuia,java.sql.Date fechaGuia );  

       public abstract Boolean guiaExiste( String nroGuia );  

       public abstract Boolean nroCtrlFiscalExiste( String nroCtrlFiscal );  
       
       public abstract Guia01 getMaestroGuia(String ncf, String nroGuia,java.sql.Date fechaGuia );  

       //public abstract String getNextGuia();          //  interface Class no permite definir metodos staticos ??.  
       
       //public abstract long getNextNroCtrlFiscal();   //  interface Class no permite definir metodos staticos ??. 
       
       public abstract void incluir( Guia01 guia01 ); 
       
       public abstract void actualizar( Guia01 guia01 ); 
       
       public abstract void eliminar( String nroGuia ); 
       
}  // IServicioAdministracionGuia01. 