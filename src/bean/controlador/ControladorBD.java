/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author henrypb
 * Sistema de Gestion de Cuentas Bancarias
 * creadao el 31/01/2012.
 * 
 */
public class ControladorBD {
       public String driver = "oracle.jdbc.driver.OracleDriver";
       //public String oracleSID = "DES102";
       public String oracleSID = "DES112";
       //public String oracleSID = "DES119C";   //  
       
       //public String host = "193.168.0.3";
       public String host = "193.168.0.59";      // Oracle RDBMS 11G R2 en ORACLE ENTERPRISE LINUX   
       //public String host = "193.168.0.57";    // Oracle RDBMS 19C en Debian Bulleyes. 
       
       public String usuario = "ops$despacho";
       public String contrasena = "ops$despacho";
       public String puerto = "1521";
       public Connection con;  

private void connect() {
      try {
          Class.forName(driver);
          String url = "jdbc:oracle:thin:@" + host +":"+puerto+":" + oracleSID;
          //System.out.println(url+","+usuario+","+contrasena);
          con = DriverManager.getConnection(url, usuario, contrasena);
      } catch ( Exception excepcion ) { System.out.println( excepcion ); }
       //catch (Exception e) { System.out.println("ERROR: fallo la coneccion a MySQL");};
    } // connect.

//--------------------------------------------------------------------------
public Connection getConeccion() throws SQLException {
      if ( con == null ) {
           connect();
      }
      else
          if ( con.isClosed() ) {
            connect();
          }
      return con;
   } // getConeccionOracle

}  