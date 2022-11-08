/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.servicio;

import bean.controlador.ControladorBD;
import bean.entidad.Guia05;
import bean.interfase.IServicioAdministracionGuia05;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author henrypb
 */
public class ServicioAdministracionGuia05 implements IServicioAdministracionGuia05 {

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Boolean transporteRegistrado(String codTransporte) {
        ControladorBD conectarOracle = new ControladorBD();
        Boolean encontrado = false;
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C5_COD_TRANSPORTE " +
                         "from   GUIAS05_DAT " +
                         "where  C5_COD_TRANSPORTE ='"+codTransporte+"' " +
                         "and    C5_STATUS='A'";  
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                encontrado = true;  
            }
        } catch (SQLException ex) {
            //Logger.getLogger(ServicioAdministracionGuia01.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    //Logger.getLogger(ServicioAdministracionGuia01.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally. 
        if (con!=null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionGuia01.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (encontrado); 
    }  // * transporteRegistrado *

    @Override
    public Guia05 getDatosTransporte(String codTransporte) {
        Guia05 transporte = null; 
        ControladorBD conectarOracle = new ControladorBD();
        Boolean encontrado = false;
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C5_COD_TRANSPORTE as codTransporte," +
                         "C5_NOMBRE_TRANSP as nombreTransporte," + 
                         "C5_CODIGO_CONTABLE as codContable," +                       
                         "C5_STATUS as status," +
                         "rowID as IdRow " +
                         "from  GUIAS05_DAT " +
                         "where C5_COD_TRANSPORTE='"+codTransporte+"' ";  
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                //String codTransporte    = rs.getString("codTransporte");   
                String nombreTransporte = rs.getString("nombreTransporte"); 
                String codContable      = rs.getString("codContable");                           
                String status           = rs.getString("status");  
                String rowID            = rs.getString("idRow");  
                transporte = new Guia05(codTransporte,nombreTransporte,codContable,status,rowID);  
            }  // if-interno. 
        } catch (SQLException ex) {
            //Logger.getLogger(ServicioAdministracionGuia01.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    //Logger.getLogger(ServicioAdministracionGuia01.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally. 
        if (con!=null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionGuia01.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (transporte); 
    }  // getDatosTransṕorte.  
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void imprimirExcepcion(SQLException excepcion) {
        System.out.println(excepcion);
    }
    
    /*  MODELO de Servicio ( No escribas tanto ni tampoco le temais a Judas
     *  que solo Judas temio ):   
     * 
    public Boolean transporteRegistrado(String codTransporte) {
        ControladorBD conectarOracle = new ControladorBD();
        Boolean encontrado = false;
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = ""; 
            ResultSet rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            //Logger.getLogger(ServicioAdministracionGuia01.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    //Logger.getLogger(ServicioAdministracionGuia01.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally. 
        if (con!=null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionGuia01.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (encontrado); 
    }  // * unidadTransporteRegistrada *
    * 
    */

    @Override
    public List<Guia05> getTransportesActivos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}  // public class ServicioAdministracionGuia05.   
