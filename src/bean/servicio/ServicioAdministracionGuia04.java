/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.servicio;

import bean.controlador.ControladorBD;
import bean.entidad.Guia04;
import bean.interfase.IServicioAdministracionGuia04;
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
public class ServicioAdministracionGuia04 implements IServicioAdministracionGuia04  {

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Boolean unidadTransporteRegistrado(String ciTransportista, String codTransporte ) {
        ControladorBD conectarOracle = new ControladorBD();
        Boolean encontrado = false;
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C4_COD_CAMION " +
                         "from   GUIAS04_DAT " +
                         "where  C4_CI_CHOFER='"+ciTransportista+"' " +
                         "and    C4_COD_TRANSP='"+codTransporte+"' ";  
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                encontrado = true;  
            }  // if. 
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionGuia05.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionGuia04.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally. 
        if (con!=null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionGuia04.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (encontrado); 
    }  // * unidadTransporteRegistrada *
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Guia04 getDatosUnidadTransporte(String ciTransportista, String codTransporte ) {
        Guia04 unidadTransporte = null;  
        ControladorBD conectarOracle = new ControladorBD();
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C4_COD_CAMION as codCamion," +
                         "C4_TIPO_CAMION as tipoCamion," +
                         "C4_COLOR as color," +
                         "C4_NO_EJES as nroEjes," +
                         "C4_CAPACIDAD as capacidad," +
                         "C4_PLACA_CHUTO as placaChuto," +             
                         "C4_PLACA_BATEA as placaBatea," +
                         "rowId as idRow " +
                         "from   GUIAS04_DAT " +
                         "where  C4_CI_CHOFER='"+ciTransportista+"' " +
                         "and    C4_COD_TRANSP='"+codTransporte+"' ";  
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String codCamion  = rs.getString("codCamion"); 
                String tipoCamion = rs.getString("tipoCamion");  
                String color      = rs.getString("color");  
                String nroEjes    = rs.getString("nroEjes");  
                Double capacidad  = rs.getDouble("capacidad");  
                String placaChuto = rs.getString("placaChuto");               
                String placaBatea = rs.getString("placaBatea");  
                String rowId      = rs.getString("idRow");  
                unidadTransporte = new Guia04(ciTransportista,codTransporte,
                                              codCamion,tipoCamion,
                                              color,nroEjes,
                                              capacidad,placaChuto,
                                              placaBatea,rowId);  
            }  // if-interno.  
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionGuia04.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionGuia04.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally. 
        if (con!=null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionGuia04.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (unidadTransporte); 
    }  // * getDatosUnidadTransporte
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public List<Guia04> getLovUnidadesTransporte(String ciTransportista, String codTransporte ) {
        List<Guia04> listaTransportes = new ArrayList<Guia04>();  
        Guia04 unidadTransporte;  
        ControladorBD conectarOracle = new ControladorBD();
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C4_COD_CAMION as codCamion," +
                         "C4_TIPO_CAMION as tipoCamion," +
                         "C4_COLOR as color," +
                         "C4_NO_EJES as nroEjes," +
                         "C4_CAPACIDAD as capacidad," +
                         "C4_PLACA_CHUTO as placaChuto," +             
                         "C4_PLACA_BATEA as placaBatea," +
                         "rowId as IdRow " +
                         "from   GUIAS04_DAT " +
                         "where  C4_CI_CHOFER='"+ciTransportista+"' " +
                         "and    C4_COD_TRANSP='"+codTransporte+"' ";  
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String codCamion  = rs.getString("codCamion"); 
                String tipoCamion = rs.getString("tipoCamion");  
                String color      = rs.getString("color");  
                String nroEjes    = rs.getString("nroEjes");  
                Double capacidad  = rs.getDouble("capacidad");  
                String placaChuto = rs.getString("placaChuto");               
                String placaBatea = rs.getString("placaBatea");  
                unidadTransporte = new Guia04(codCamion,tipoCamion,
                                              color,nroEjes,
                                              capacidad,placaChuto,
                                              placaBatea);  
                listaTransportes.add(unidadTransporte);
            }  // while.    
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionGuia04.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionGuia04.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally. 
        if (con!=null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionGuia04.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (listaTransportes);
    }
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void imprimirExcepcion(SQLException excepcion) {
        System.out.println(excepcion);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private String tipoUnidadTransporte( char tipoUnidad ) {
            switch ( tipoUnidad ) {
                case 'C' : return ("CAMION");
                case 'G' : return ("GANDOLA");
                case 'T' : return ("TORONTO");
                default  : return ("ERROR");
            }  // switch.   
    }  // tipoUnidadTransporte. 
  
}  // public class ServicioAdministracionGuia04. 
