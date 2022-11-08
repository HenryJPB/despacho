/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.inventFacturacion;

import bean.controlador.ControladorBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;   // (**)

/**
 *
 * @author henrypb
 */
public class ServicioAdministracionInv02 implements IServicioAdministracionInv02 {

    //SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); 
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Boolean precioReferencialExiste(String codProducto) {
        ControladorBD conectarOracle = new ControladorBD();
        // ** Declaracion de Atributos / Variables **:  
        Boolean encontrado = Boolean.FALSE;
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C2_CODIGO as Ok " +
                         "from   INV02_DAT " +
                         "where  C2_CODIGO='"+codProducto+"'" +
                         "and    ROWNUM = 1";  
            ResultSet rs = stmt.executeQuery(sql);
            if ( rs.next() ) {
                encontrado = Boolean.TRUE;  
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionInv02.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionInv02.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally. 
        if (con != null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionInv02.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (encontrado);
    }   // precioReferencialExiste. 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Double precioUnidad(String codProducto, Date alFecha) {
        ControladorBD conectarOracle = new ControladorBD();
        // ** Declaracion de Atributos / Variables **:  
        Double precioUnidad = 0.00;  
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C2_PRECIO_UNIDAD as precioUnidad " +
                         "from   INV02_DAT " +
                         "where  C2_CODIGO='"+codProducto+"'" +
                         "and    C2_FECHA = (select MAX(T2.C2_FECHA)  " +
                         "                   from   INV02_DAT T2 " +
                         "                   where  T2.C2_CODIGO = INV02_DAT.C2_CODIGO " + 
                         "                   and    C2_FECHA <= '"+formatoFecha.format(alFecha)+"' )"; 
            ResultSet rs = stmt.executeQuery(sql);
            if ( rs.next() ) {
                 precioUnidad = rs.getDouble("precioUnidad");  
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionInv02.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionInv02.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally. 
        if (con != null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionInv02.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (precioUnidad);
    }  // precioUnidad. 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Double precioKsg(String codProducto, Date alFecha) {
        ControladorBD conectarOracle = new ControladorBD();
        // ** Declaracion de Atributos / Variables **:  
        Double precioKgs = 0.00;  
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C2_PRECIO_KGS as precioKgs " +
                         "from   INV02_DAT " +
                         "where  C2_CODIGO='"+codProducto+"'" +
                         "and    C2_FECHA = (select MAX(T2.C2_FECHA)  " +
                         "                   from   INV02_DAT T2 " +
                         "                   where  T2.C2_CODIGO = INV02_DAT.C2_CODIGO " + 
                         "                   and    C2_FECHA <= '"+formatoFecha.format(alFecha)+"' )"; 
            ResultSet rs = stmt.executeQuery(sql);
            if ( rs.next() ) {
                 precioKgs = rs.getDouble("precioKgs");  
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionInv02.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionInv02.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally. 
        if (con != null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionInv02.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (precioKgs);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void imprimirExcepcion(SQLException ex) {
        System.err.println(ex);
    }
}   // ServicioAdministracionInv02.  


// ********** NOTAS IMPORTANTES: ****************************************************
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
