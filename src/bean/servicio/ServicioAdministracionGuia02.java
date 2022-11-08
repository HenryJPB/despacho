/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.servicio;

import bean.controlador.ControladorBD;
import bean.entidad.Guia02;
import bean.interfase.IServicioAdministracionGuia02;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class ServicioAdministracionGuia02 implements IServicioAdministracionGuia02 {

    //--------------------------------------------------------------------------
    // Chequear por inconsistencia de datos entre la Relacion Maestro-Detalle.  
    //--------------------------------------------------------------------------
    @Override
    public Boolean existeRegDetalleGuia(String nroGuia) {
        ControladorBD conectarOracle = new ControladorBD();
        Boolean encontrado = false;
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C2_CODIGO " +
                         "from   GUIAS02_DAT " +
                         "where  C2_GUIA='"+nroGuia+"' " +
                         "and    ROWNUM = 1";  // Suficiente con chequear por al menos un item.  
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                encontrado = true; 
            }   // if.  
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionGuia02.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionGuia02.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally. 
        if (con!=null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionGuia02.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (encontrado);
    }  // existeRegDetalleGuia.  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public List<Guia02> getDetalleGuia(String nroGuia) {
        ControladorBD conectarOracle = new ControladorBD();
        List<Guia02> listaProductosGuia = new ArrayList<Guia02>();  
        Guia02  regDetalle;  
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C2_GUIA,"      +
                         "C2_ITEM_NO as itemNo," +  
                         "C2_CODIGO as codigo," +
                         "C2_TIPO as tipoProd," +
                         "C2_DESCRIPCION as descripcion," +  
                         "C2_PESO as peso," +  
                         "C2_NO_PEDIDO as nroPedido," +  
                         "C2_UNIDADES as cantidad," + 
                         "C2_ITEMS as items," +  
                         "C2_ATADOS as atados," + 
                         "C2_PRECIO as precio," + 
                         "C2_ALICUOTA as alicuota,"  + 
                         "C2_FXPESO as fxPeso," + 
                         "C2_FXUNIDAD as fxUnidad,"  +  
                         "C2_PESO_GUIA as pesoGuia," +
                         "rowId as idRow " +  
                         "from  GUIAS02_DAT " +
                         "where C2_GUIA ='"+nroGuia+"' " +
                         "order by C2_ITEM_NO, C2_TIPO, C2_CODIGO";  
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                   Integer itemNo      = rs.getInt("itemNo");  
                   String  codigo      = rs.getString("codigo"); 
                   String  tipoProd    = rs.getString("tipoProd");  
                   String  descripcion = rs.getString("descripcion");  
                   Double  peso        = rs.getDouble("peso");
                   String  nroPedido   = rs.getString("nroPedido");  
                   Double  cantidad    = rs.getDouble("cantidad");  
                   Double  items       = rs.getDouble("items");  
                   String  atados      = rs.getString("atados");  
                   Double  precio      = rs.getDouble("precio"); 
                   Double  alicuota    = rs.getDouble("alicuota");  
                   String  fxPeso      = rs.getString("fxPeso");  
                   String  fxUnidad    = rs.getString("fxUnidad"); 
                   Double  pesoGuia    = rs.getDouble("pesoGuia"); 
                   String  idRow       = rs.getString("idRow");  
                   regDetalle = new  Guia02(nroGuia,itemNo,codigo,tipoProd,
                                            descripcion,peso,nroPedido,cantidad,
                                            items,atados,precio,alicuota,fxPeso,
                                            fxUnidad,pesoGuia,idRow);         
                   listaProductosGuia.add(regDetalle);
            }  // while.  
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionGuia02.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionGuia02.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally. 
        if (con!=null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionGuia02.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (listaProductosGuia); 
    }  // getDetalleGuia.  
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public void incluirProductoGuia(String nroGuia, Guia02 itemGuia02) {
        ControladorBD conectarBD = new ControladorBD();
        Connection con = null;
        int ok = 0;
        PreparedStatement pstmt = null;
        String sql = "insert into GUIAS02_DAT " +
                     "values( ?, ?, ? , ? , ? , ? , ? , ? , ?, ?, ?, ?, ?, ?, ? )";   // 1
                     //       1  2  3   4   5   6   7   8   9 10 11 12 13 14 15 
        try {
            con = conectarBD.getConeccion();
            pstmt = con.prepareStatement(sql);  
            pstmt.setString(1, nroGuia );
            pstmt.setInt(2, itemGuia02.getC2_ITEM_NO());
            pstmt.setString(3, itemGuia02.getC2_CODIGO());
            pstmt.setString(4, itemGuia02.getC2_TIPO());
            pstmt.setString(5, itemGuia02.getC2_DESCRIPCION());
            pstmt.setDouble(6, itemGuia02.getC2_PESO());
            pstmt.setString(7, itemGuia02.getC2_NO_PEDIDO());
            pstmt.setDouble(8, itemGuia02.getC2_UNIDADES());  
            pstmt.setDouble(9, itemGuia02.getC2_ITEMS());
            pstmt.setString(10, itemGuia02.getC2_ATADOS());
            pstmt.setDouble(11, itemGuia02.getC2_PRECIO());
            pstmt.setDouble(12, itemGuia02.getC2_ALICUOTA());
            pstmt.setString(13, itemGuia02.getC2_FXPESO());
            pstmt.setString(14, itemGuia02.getC2_FXUNIDAD());
            pstmt.setDouble(15, itemGuia02.getC2_PESO_GUIA());
            pstmt.executeUpdate();
        } catch (SQLException excepcion) {
            imprimirExcepcion(excepcion);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionGuia02.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionGuia02.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }  // incluirProductoGuia().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public void actualizarProductoGuia(String nroGuia, Guia02 itemGuia02) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public void eliminarProductosGuia( String nroGuia ) {
        ControladorBD conectarBD = new ControladorBD();
        Connection con = null;
        PreparedStatement pstmt = null;
        int ok;  
        String sql = "delete from GUIAS02_DAT "
                   + "where  C2_GUIA = '"+nroGuia+"'"; 
        try {
            con = conectarBD.getConeccion();
            pstmt = con.prepareStatement(sql);
            //pstmt.setString(1, guia01.getC1_GUIA() );
            pstmt.executeUpdate();
        } // try
        catch (SQLException excepcion) {
            imprimirExcepcion(excepcion);
        } catch (Exception excepcion) {
            imprimirExcepcion((SQLException) excepcion);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionGuia02.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionGuia02.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } // finally.
    }  // eliminarProductosGuia02.  
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void imprimirExcepcion(SQLException ex) {
        System.err.println("ATENCION!!; chequear por excepcion: "+ex );  
    }  // imprimirExcepcion.  


    
}  // ServicioAdministracionGuia02.*********************************************

    //--------------------------------------------------------------------------
    /*  MODELO de Servicio ( No escribas tanto ni tampoco le temais a Judas
     *  que solo Judas temio ):   
     * -------------------------------------------------------------------------
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
            if / while () {
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionGuia01.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionGuia01.class.getName()).log(Level.SEVERE, null, ex);
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
    * ---------------------------------------------------------------------------
    */