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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author henrypb
 */
public class ServicioAdministracionVend03 implements IServicioAdministracionVend03 {

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Boolean existePedido(String nroPedido) {
        ControladorBD conectarOracle = new ControladorBD();
        // ** Declaracion de Atributos / Variables **:  
        Boolean encontrado = Boolean.FALSE;
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C3_NO_PEDIDO as nroPedido " +
                         "from   VEND03_DAT " +
                         "where  C3_NO_PEDIDO = '"+nroPedido+"' ";  
            ResultSet rs = stmt.executeQuery(sql);
            if ( rs.next() ) {
                 encontrado = Boolean.TRUE;  
            }  // if. 
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionVend03.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionVend03.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally. 
        if (con!=null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionVend03.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (encontrado); 
    } // existePedido.  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Vend03 getPedido(String nroPedido) {
        ControladorBD conectarOracle = new ControladorBD();
        // ** Declaracion de Atributos / Variables **:  
        Vend03 datosPedido = null;  
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C3_NO_PEDIDO as nroPedido, " + 
                         "       C3_FECHA_PEDIDO as fechaPedido," +  
                         "       C3_COD_CLIENTE as codCliente," + 
                         "       C3_ENTREGA1 as entrega1," +  
                         "       C3_ENTREGA2 as entrega2," + 
                         "       C3_ENTREGA3 as entrega3," +  
                         "       C3_ORDEN_FAB as ordenFab," +  
                         "       C3_ORDEN_COMPRA ordenCompra," +  
                         "       C3_FORMA_PAGO as formaPago," +  
                         "       C3_PLAZO as plazo," + 
                         "       C3_TRANSPORTE as transporte," +   
                         "       C3_COD_VENDEDOR as codVendedor," +  
                         "       C3_ZONA as zonaVenta," +  
                         "       C3_CLAVE as clave," +  
                         "       C3_OBSERVACION1 as observacion1," +  
                         "       C3_OBSERVACION2 as observacion2," + 
                         "       C3_DESCUENTO as descuento," +  
                         "       C3_RECONOCI_FLETE as reconiFlete,"  +  
                         "       C3_COBRO_FLETE as cobroFlete,"  +  
                         "       C3_ALI_CUOTA as alicuota "  +  
                         "from   VEND03_DAT " +
                         "where  C3_NO_PEDIDO ='"+nroPedido+"' ";  
            ResultSet rs = stmt.executeQuery(sql);
            if ( rs.next() ) {
                 String  pedido =   rs.getString("nroPedido");  
                 java.sql.Date fechaPedido = rs.getDate("fechaPedido");  
                 String  codCliente = rs.getString("codCliente"); 
                 String  entrega1 = rs.getString("entrega1");  
                 String  entrega2 = rs.getString("entrega2"); 
                 String  entrega3 = rs.getString("entrega3");  
                 String  ordenFab = rs.getString("ordenFab");  
                 String  ordenCompra = rs.getString("ordenCompra");  
                 String  formaPago = rs.getString("formaPago");  
                 Integer plazo = rs.getInt("plazo");  
                 String  transporte = rs.getString("transporte");  
                 String  codVendedor = rs.getString("codVendedor");  
                 String  zona = rs.getString("zonaVenta");  
                 String  clave = rs.getString("clave");  
                 String  observacion1 = rs.getString("observacion1");  
                 String  observacion2 = rs.getString("observacion2");  
                 Double  descuento = rs.getDouble("descuento");  
                 Double  reconociFlete = rs.getDouble("reconiFlete");  
                 Double  cobroFlete = rs.getDouble("cobroFlete");  
                 Double  alicuota = rs.getDouble("alicuota"); 
                 datosPedido = new Vend03(pedido,fechaPedido,codCliente,entrega1,entrega2,entrega3,
                                          ordenFab,ordenCompra,formaPago,plazo,transporte,codVendedor,
                                          zona,clave,observacion1,observacion2,descuento,reconociFlete,
                                          cobroFlete,alicuota); 
            }   // if.  
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionVend03.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionVend03.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally. 
        if (con!=null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionVend03.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (datosPedido); 
    }  // getPedido.  

    private void imprimirExcepcion(SQLException ex) {
        System.err.println( ex );
    }
    
}  // ServicioAdministracionVend03.  

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
