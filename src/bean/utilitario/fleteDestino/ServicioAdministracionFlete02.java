/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.fleteDestino;

import bean.controlador.ControladorBD;
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
public class ServicioAdministracionFlete02 implements IServicioAdministracionFlete02 {

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Boolean estadoRegistrado(String codEstado) {
        ControladorBD conectarOracle = new ControladorBD();
        Boolean encontrado = false;
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C2_NOMBRE_ESTADO " +
                         "from   FLETE02_DAT " +
                         "where  C2_COD_ESTADO = '"+codEstado+"'";  
            ResultSet rs = stmt.executeQuery(sql);
        if ( rs.next() ) {
             encontrado = true;  
          }  // if.    
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
        return (encontrado);
    }  // * estadoRegistrado * 
                
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Flete02 getEstado(String codEstado) {
        Flete02    estado = null;  
        ControladorBD conectarOracle = new ControladorBD();
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C2_COD_ESTADO codEstado," +
                         "C2_NOMBRE_ESTADO as nombreEstado " +
                         "from   FLETE02_DAT " +
                         "where  C2_COD_ESTADO = '"+codEstado+"'";  
            ResultSet rs = stmt.executeQuery(sql);
        if ( rs.next() ) {
             String   nombreEstado = rs.getString("nombreEstado"); 
             estado = new Flete02(codEstado,nombreEstado); 
         }    // while.    
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
        return (estado); 
    }  // * getEstado.* 

    private void imprimirExcepcion(SQLException excepcion) {
       System.out.println(excepcion);
    }

}  // ServicioAdministracionFlete02.  
