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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author henrypb
 */
public class ServicioAdministracionInv04 implements IServicioAdministracionInv04 {

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Boolean productoEspecialRegistrado(String nroOrden, String codProducto) {
        ControladorBD conectarOracle = new ControladorBD();
        //--------------------------------------------
        // ** Declaracion de Atributos / Variables **:
        //--------------------------------------------
        Boolean encontrado = Boolean.FALSE;
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C4_CODIGO, C4_ORDEN " +
                         "from   INV04_DAT " +
                         "where  C4_ORDEN = '"+nroOrden+"' " +  
                         "and    C4_CODIGO='"+codProducto+"' "; 
            ResultSet rs = stmt.executeQuery(sql);
            if ( rs.next() ) encontrado = Boolean.TRUE;  
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionInv04.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionInv04.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally. 
        if (con!=null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionInv04.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (encontrado); 
    }  // productoEspecialRegistrado().

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Inv04 getProductoEspecial(String nroOrdenFab, String codProducto) {
        ControladorBD conectarOracle = new ControladorBD();
        //--------------------------------------------
        // ** Declaracion de Atributos / Variables **:  
        //--------------------------------------------
        Boolean encontrado = false;
        Connection con = null;
        Statement stmt = null;
        Inv04 datosProducto = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C4_ORDEN as nroOrden," + 
                         "C4_CODIGO as codigo," +  
                         "C4_TIPO_PROD as tipo," +  
                         "lTrim(to_char(C4_TAML,'99.99')||'/'||to_char(C4_TAMT,'99.99')||' '||" +
                         "to_char(C4_SEPL,'999.99')||' x'||to_char(C4_SEPT,'999.99')||' '||" +
                         "to_char(C4_DIAML,'99.99')||'/'||to_char(C4_DIAMT,'99.99')||' ') as descripcion," +  
                         "C4_TAML as tamL," + 
                         "C4_TAMT as tamT," +  
                         "C4_SEPL as sepL," +  
                         "C4_SEPT as sepT," +  
                         "C4_DIAML as diamL," +  
                         "C4_DIAMT as diamT," + 
                         "C4_NUML as numL," +  
                         "C4_NUMT as numT," + 
                         "C4_SOBRL1 as sobrL1," +  
                         "C4_SOBRL2 as sobrL2," +  
                         "C4_SOBRT1 as sobrT1," +  
                         "C4_SOBRT2 as sobrT2," +  
                         "C4_PESO_ITEM as pesoItem," +  
                         "C4_LAM as lam," +  
                         "C4_BSXTON as bsTon " +  
                         "from   INV04_DAT " + 
                         "where  C4_ORDEN = '"+nroOrdenFab+"' " + 
                         "and    C4_CODIGO = '"+codProducto+"' "; 
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String tipo = rs.getString("tipo"); 
                String descripcion  = rs.getString("descripcion");  
                Double tamL = rs.getDouble("tamL"); 
                Double tamT = rs.getDouble("tamT");  
                Double sepL = rs.getDouble("sepL");  
                Double sepT = rs.getDouble("sepT");
                Double diamL = rs.getDouble("diamL");  
                Double diamT = rs.getDouble("diamT");  
                Integer numL = rs.getInt("numL"); 
                Integer numT = rs.getInt("numT"); 
                Integer sobrL1 = rs.getInt("sobrL1"); 
                Integer sobrL2 = rs.getInt("sobrL2"); 
                Integer sobrT1 = rs.getInt("sobrT1");  
                Integer sobrT2 = rs.getInt("sobrT2"); 
                Double pesoItem = rs.getDouble("pesoItem"); 
                Integer lam = rs.getInt("lam"); 
                Double bsTon = rs.getDouble("bsTon"); 
                datosProducto = new Inv04(nroOrdenFab,codProducto,tipo,descripcion,tamL,tamT,sepL,sepT,diamL,diamT,numL,numT,sobrL1,sobrL2,sobrT1,sobrT2,pesoItem,lam,bsTon); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionInv04.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionInv04.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally. 
        if (con!=null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionInv04.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (datosProducto); 
    }  //  getProductoEspecial().
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Boolean ordenFabRegistrada(String nroOrdenFab) {
        ControladorBD conectarOracle = new ControladorBD();
        // Declaracion de Atributos / Variables:  
        Boolean encontrado = false;
        Connection con = null;
        Statement stmt = null;
        //--------------------------------------
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C4_ORDEN " +
                         "from   INV04_DAT " +
                         "where  C4_ORDEN = '"+nroOrdenFab+"' " +
                         "and    ROWNUM = 1"; 
            ResultSet rs = stmt.executeQuery(sql);
            if ( rs.next()) {
                 encontrado = true;  
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionInv04.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionInv04.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally. 
        if (con!=null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionInv04.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (encontrado); 
    }  // ordenFabRegistrada.  
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public List<Inv04> getListaProductosEspeciales(String nroOrdenFab) {
        ControladorBD conectarOracle = new ControladorBD();
        // Declaracion de Atributos/Variables:
        Inv04 producto;
        List<Inv04> listaProductos = new ArrayList<Inv04>();
        Connection con = null;
        Statement stmt = null;
        // ----------------------------------
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C4_CODIGO as codProducto,"
                    + "C4_TIPO_PROD as tipoProducto,"
                    + "to_char(C4_TAML,'99.99')||'/'||to_char(C4_TAMT,'99.99')||' '||"
                    + "to_char(C4_SEPL,'999.99')||' x'||to_char(C4_SEPT,'999.99')||' '||"
                    + "to_char(C4_DIAML,'99.99')||'/'||to_char(C4_DIAMT,'99.99')||' ' as descripcion "
                    + "from INV04_DAT "
                    + "where  C4_ORDEN = '" + nroOrdenFab + "' ";
            ResultSet rs = stmt.executeQuery(sql);
            while ( rs.next()) {
                    String codigo = rs.getString("codProducto"); 
                    String tipo   = rs.getString("tipoProducto");  
                    String descripcion = rs.getString("descripcion");  
                    producto = new Inv04(codigo,tipo,descripcion);  
                    listaProductos.add(producto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionInv04.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionInv04.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally. 
        if (con != null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionInv04.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (listaProductos);
    }  // getListaProductosEspeciales.  
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Double precio(String ordenFab, String codProducto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void imprimirExcepcion(SQLException ex) {
        System.out.println("ATENCION: "+ex );
    }

  
}  // ServicioAdministracionInv04.  


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
