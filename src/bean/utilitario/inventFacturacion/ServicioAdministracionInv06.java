/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.inventFacturacion;

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
public class ServicioAdministracionInv06 implements IServicioAdministracionInv06 {

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Boolean facPeso(String tipoProd) {
        ControladorBD conectarOracle = new ControladorBD();
        //----------------------------------------------------------------------
        // ** Declaracion de Atributos / Variables **:  
        //----------------------------------------------------------------------
        Boolean valido = Boolean.FALSE;
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C6_FXPESO as facXpeso "
                    + "from   INV06_DAT "
                    + "where  C6_TIPO ='" + tipoProd + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String facXpeso = rs.getString("facXpeso");
                if (facXpeso != null) {
                    if (!facXpeso.isEmpty() || facXpeso.equals("X")) {
                        valido = Boolean.TRUE;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionInv06.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionInv06.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally. 
        if (con != null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionInv06.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (valido);
    }  // facXpeso(). 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
     public Boolean facUnidad(String tipoProd) {
        ControladorBD conectarOracle = new ControladorBD();
        //----------------------------------------------------------------------
        // ** Declaracion de Atributos / Variables **:  
        //----------------------------------------------------------------------
        Boolean valido = Boolean.FALSE;
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C6_FXUNIDAD as facXunidad "
                    + "from   INV06_DAT "
                    + "where  C6_TIPO ='" + tipoProd + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String facXunidad = rs.getString("facXunidad");
                if (facXunidad != null) {
                    if (!facXunidad.isEmpty() || facXunidad.equals("X")) {
                        valido = Boolean.TRUE;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionInv06.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionInv06.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally. 
        if (con != null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionInv06.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (valido);
    }  // facXunidad().
    
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void imprimirExcepcion(SQLException ex) {
        System.err.println(ex);
    }
}
// ********** NOTAS UTILES: ****************************************************

/*  --------------Formatear un Numero ------------------------------
 DecimalFormat df = new DecimalFormat("#.##");
 System.out.print(df.format(d));
 * ------------------------------------------------------------------
 */
//--------------------------------------------------------------------------
    /*  MODELO de Servicio ( No escribas tanto ni tampoco NI le temais a Judas
 *  que solo Judas temio ):   
 * -------------------------------------------------------------------------
 public Boolean transporteRegistrado(String codTransporte) {
 ControladorBD conectarOracle = new ControladorBD();
 // ** Declaracion de Atributos / Variables **:  
 Boolean encontrado = false;
 Connection con = null;
 Statement stmt = null;
 try {
 con = conectarOracle.getConeccion();
 stmt = con.createStatement();
 String sql = ""; 
 ResultSet rs = stmt.executeQuery(sql);
 if / while () {
 }
 } catch (SQLException ex) {
 Logger.getLogger(ServicioAdministracion____.class.getName()).log(Level.SEVERE, null, ex);
 imprimirExcepcion(ex);
 } finally {
 if (stmt != null) {
 try {
 stmt.close();
 } catch (SQLException ex) {
 Logger.getLogger(ServicioAdministracion____.class.getName()).log(Level.SEVERE, null, ex);
 imprimirExcepcion(ex);
 }
 }  // try interno. 
 }  // finally. 
 if (con!=null) {
 try {
 stmt.close();
 con.close();
 } catch (SQLException ex) {
 Logger.getLogger(ServicioAdministracion____.class.getName()).log(Level.SEVERE, null, ex);
 }
 }
 return (encontrado); 
 }  // * unidadTransporteRegistrada *
 * ---------------------------------------------------------------------------
 */
