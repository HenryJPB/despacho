/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.contab;

import bean.controlador.ControladorBD;
import bean.utilitario.fleteDestino.ServicioAdministracionFlete02;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author henrypb
 */
public class ServicioAdministracionContabaf implements IServicioAdministracionContabaf {

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Contabaf getDatosContable(String codEmpresa) {
        ControladorBD conectarOracle = new ControladorBD();
        Connection con = null;
        Statement stmt = null;
        Contabaf datosContable = null;  
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select CODIGO_EMPRESA as codEmpresa,"  +   
                         "NOMBRE_EMPRESA as razonSocial," +   
                         "DIRECCION as direccion," +   
                         "FECHA_INICIAL as fechaInicial," +   
                         "FECHA_EJERCICIO as fechaEjercicio," +    
                         "RIF as rif "  +  
                         "from   CONTABAF_DAT " +
                         "where  CODIGO_EMPRESA ='" +codEmpresa+"' ";  
            ResultSet rs = stmt.executeQuery(sql);
        if ( rs.next() ) {
             String razonSocial = rs.getString("razonSocial");
             String direccion = rs.getString("direccion");  
             String fechaInicial = rs.getString("fechaInicial");  
             String fechaEjercicio = rs.getString("fechaEjercicio");  
             String rif = rs.getString("rif");  
             datosContable = new Contabaf(codEmpresa,razonSocial,direccion,fechaInicial,fechaEjercicio,rif);  
         }    // if.      
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionFlete02.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionFlete02.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally. 
        if (con!=null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionFlete02.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (datosContable);
    }   // getDatosContable.  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void imprimirExcepcion(SQLException ex) {
        System.err.println( ex );
    }
    
}  // ServcioAdministracionContabaf.  
