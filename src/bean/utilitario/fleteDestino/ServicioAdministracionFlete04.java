/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.fleteDestino;

import bean.controlador.ControladorBD;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author henrypb
 */
public class ServicioAdministracionFlete04 implements IServicioAdministracionFlete04 {

    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    
    @Override
    public List<Flete04> getLovFleteDestinos(Date alFecha ) {
        Flete04 regFleteDestino;  
        List<Flete04> listaFleteDestino = new ArrayList<Flete04>();  
        ControladorBD conectarOracle = new ControladorBD();
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C4_FECHA_RELACION as fechaRelacion," +
                         "C4_COD_PAIS as codPais," +   
                         "C4_COD_ESTADO as codEstado," + 
                         "C2_NOMBRE_ESTADO as nombreEstado," +
                         "C4_COD_DESTINO as codDestino," +   
                         "C4_COD_SECTOR as codSector,"  +  
                         "C4_NOMBRE_DESTINO as nombreDestino," +    
                         "C4_COD_POSTAL as codPostal," +    
                         "C4_PRECIO_TON_CAMION as precioTnCamion," +    
                         "C4_PRECIO_TON_GANDOLA as precioTnGandola," +   
                         "C4_PRECIO_TON_TORONTO as precioTnToronto," +    
                         "FLETE04_DAT.rowId as idRow " +
                         "from  FLETE04_DAT, FLETE02_DAT " +
                         "where C4_COD_ESTADO = C2_COD_ESTADO " +  
                         "and   C4_FECHA_RELACION =  ( select MAX( T1.C4_FECHA_RELACION ) " +
                                                      "from   FLETE04_DAT T1 " +
                                                      "where  T1.C4_FECHA_RELACION <= '"+formatoFecha.format(alFecha)+"') " +
                         "order by C4_COD_DESTINO"; 
            ResultSet rs = stmt.executeQuery(sql);
            while ( rs.next()) {
                    Date    fechaRelacion   = rs.getDate("fechaRelacion");  
                    String  codPais         = rs.getString("codPais"); 
                    String  codEstado       = rs.getString("codEstado"); 
                    String  nombreEstado    = rs.getString("nombreEstado");  
                    String  codDestino      = rs.getString("codDestino"); 
                    String  codSector       = rs.getString("codSector"); 
                    String  nombreDestino   = rs.getString("nombreDestino"); 
                    String  codPostal       = rs.getString("codPostal");  
                    Double  precioTnCamion  = rs.getDouble("precioTnCamion");  
                    Double  precioTnGandola = rs.getDouble("precioTnGandola");  
                    Double  precioTnToronto = rs.getDouble("precioTnToronto");  
                    String  idRow           = rs.getString("idRow");  
                    regFleteDestino = new Flete04(fechaRelacion,codPais,
                                                  codEstado,nombreEstado,codDestino,
                                                  codSector,nombreDestino,codPostal,
                                                  precioTnCamion,precioTnGandola,
                                                  precioTnToronto,idRow);  
                    listaFleteDestino.add(regFleteDestino);
            }  // while 
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionFlete04.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(ServicioAdministracionFlete04.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (listaFleteDestino);
    }
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Flete04 getFleteDestino(String codDestino, Date alFecha) {
        /*
        this.C4_FECHA_RELACION = C4_FECHA_RELACION;
        this.C4_COD_PAIS = C4_COD_PAIS;
        this.C4_COD_ESTADO = C4_COD_ESTADO;
        this.nombreEstado = nombreEstado;
        this.C4_COD_DESTINO = C4_COD_DESTINO;
        this.C4_NOMBRE_DESTINO = C4_NOMBRE_DESTINO;
        this.C4_COD_POSTAL = C4_COD_POSTAL;
        this.C4_PRECIO_TON_CAMION = C4_PRECIO_TON_CAMION;
        this.C4_PRECIO_TON_GANDOLA = C4_PRECIO_TON_GANDOLA;
        this.C4_PRECIO_TON_TORONTO = C4_PRECIO_TON_TORONTO;
        this.rowId = rowId;
         */
        Flete04    fleteDestino = null;  
        ControladorBD conectarOracle = new ControladorBD();
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C4_FECHA_RELACION as fechaRelacion," +
                         "C4_COD_PAIS  as codPais," +
                         "C4_COD_ESTADO as codEstado," + 
                         "C2_NOMBRE_ESTADO as nombreEstado," +
                         "C4_COD_DESTINO as codigoDestino," +  
                         "C4_COD_SECTOR as codigoSector," + 
                         "C4_NOMBRE_DESTINO as nombreDestino," + 
                         "C4_COD_POSTAL as codPostal," + 
                         "C4_PRECIO_TON_CAMION  as precioTnCamion," + 
                         "C4_PRECIO_TON_GANDOLA as precioTnGandola," +  
                         "C4_PRECIO_TON_TORONTO as precioTnToronto," + 
                         "FLETE04_DAT.rowId as idRow " +
                         "from  FLETE04_DAT,FLETE02_DAT " +
                         "where C4_COD_ESTADO = C2_COD_ESTADO " +  
                         "and   C4_COD_DESTINO = '" +codDestino+"' " +  
                         "and   C4_FECHA_RELACION =  ( select MAX( T1.C4_FECHA_RELACION ) " +
                                                      "from   FLETE04_DAT T1 " +
                                                      "where  T1.C4_FECHA_RELACION <= '"+formatoFecha.format(alFecha)+"' )";  
            ResultSet rs = stmt.executeQuery(sql);
        if  ( rs.next() ) {
              Date fechaRelacion     = rs.getDate("fechaRelacion");  
              String codPais         = rs.getString("codPais");         
              String codEstado       = rs.getString("codEstado");   
              String nombreEstado    = rs.getString("nombreEstado");  
              String codigoDestino   = rs.getString("codigoDestino");  
              String codigoSector    = rs.getString("codigoSector");  
              String nombreDestino   = rs.getString("nombreDestino");   
              String codPostal       = rs.getString("codPostal");  
              Double precioTnCamion  = rs.getDouble("precioTnCamion");  
              Double precioTnGandola = rs.getDouble("precioTnGandola");    
              Double precioTnToronto = rs.getDouble("precioTnToronto");  
              String idRow           = rs.getString("idRow");  
              fleteDestino = new Flete04(fechaRelacion, codPais,         
                                         codEstado,nombreEstado,   
                                         codigoDestino,codigoSector,nombreDestino,    
                                         codPostal, precioTnCamion,  
                                         precioTnGandola, precioTnToronto, 
                                         idRow);  
          }    // if.  
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionFlete04.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionFlete04.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally. 
        if (con!=null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionFlete04.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (fleteDestino); 
    }  // getFleteDestin
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void imprimirExcepcion(SQLException excepcion) {
        System.out.println(excepcion);
    }
    
    // ********************* PLANTILLA  ****************************************
    /* =========================================================================
     *  MODELO de Servicio ( No escribas tanto ni tampoco le temais a Judas
     *  que solo Judas temio ):   
     * =========================================================================
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
        while ( rs.next() ) {
         }    // while.    
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracion_____.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracion_____.class.getName()).log(Level.SEVERE, null, ex);
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
    // ========================================================================= 
  **************/
  
}  // ServicioAdministracionFlete04. 
