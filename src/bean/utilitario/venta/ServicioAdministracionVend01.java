/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.venta;

import bean.controlador.ControladorBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author henrypb
 */
public class ServicioAdministracionVend01 implements IServicioAdministracionVend01 {

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public ArrayList<String> getVendedores() {
        ControladorBD conectarOracle = new ControladorBD();
        ArrayList<String> vendedores=new ArrayList<String>();
        String nombreVend = null;  
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select rTrim(C1_NOMBRE_VEND)||' '||rTrim(C1_COD_VENDEDOR) as nombreVend " +  
                         "from   VEND01_DAT " +
                         "order  by C1_NOMBRE_VEND,C1_COD_VENDEDOR";  
            ResultSet rs = stmt.executeQuery(sql);
            while ( rs.next() ) {
                  nombreVend = rs.getString("nombreVend"); 
                  vendedores.add(nombreVend);
            }  // while.  
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
                Logger.getLogger(ServicioAdministracionVend01.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (vendedores);
    }  // getVendedores. 
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Vend01 getVendedor( String codVendedor ) {
        ControladorBD conectarOracle = new ControladorBD();
        Vend01 datosVend = null;  
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C1_COD_VENDEDOR as codVendedor, " +  
                         "       C1_CI as cedula, " + 
                         "       C1_NOMBRE_VEND as nombreVend, " + 
                         "       C1_DIRECCION1 as direccion1, " +  
                         "       C1_DIRECCION2 as direccion2, " + 
                         "       C1_TELEFONO as telefono, " +  
                         "       C1_CELULAR as telf_cel, " +  
                         "       C1_LIMIT_CREDITO as limiteCredito, " + 
                         "       C1_COD_RUTA as ruta, " +  
                         "       C1_ZONA as zona, " + 
                         "       C1_CLAVE as clave " +                         
                         "from   VEND01_DAT " +
                         "where  C1_COD_VENDEDOR='"+codVendedor+"' ";  
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String codVend = rs.getString("codVendedor");  
                String cedula  = rs.getString("cedula");  
                String nombreVendedor = rs.getString("nombreVend"); 
                String direccion1 = rs.getString("direccion1"); 
                String direccion2 = rs.getString("direccion2");  
                String telefono = rs.getString("telefono"); 
                String telf_cel = rs.getString("telf_cel");  
                Double limiteCredito = rs.getDouble("limiteCredito");
                String ruta = rs.getString("ruta");  
                String zona = rs.getString("zona");  
                String clave = rs.getString("clave");  
                datosVend = new Vend01(codVend,cedula,nombreVendedor,direccion1,direccion2,telefono,telf_cel,limiteCredito,ruta,zona,clave); 
            }   // if.  
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
                Logger.getLogger(ServicioAdministracionVend01.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (datosVend); 
    }  // getVendedor. 
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public ArrayList<String> getZonaVentas() {
        ControladorBD conectarOracle = new ControladorBD();
        ArrayList<String> zonas=new ArrayList<String>();  
        String zona = null;  
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select  distinct C1_ZONA as zona " +
                         "from    VEND01_DAT " +
                         "order  by C1_ZONA ";  
            ResultSet rs = stmt.executeQuery(sql);
            while ( rs.next() ) {
                   zona = rs.getString("zona");  
                   zonas.add(zona);
            }  // while.  
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
                Logger.getLogger(ServicioAdministracionVend01.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (zonas);
    }   // getZonasVenta. 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void imprimirExcepcion(SQLException ex) {
        System.err.println( ex );
    }

}  // ServicioAdministracionVend01.  

    //--------------------------------------------------------------------------
    /*  MODELO de Servicio ( No escribas tanto ni tampoco le temais a Judas
     *  que solo Judas temio ):   
     * 
     *--------------------------------------------------------------------------
     */  
