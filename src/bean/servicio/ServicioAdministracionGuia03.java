/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.servicio;

import bean.controlador.ControladorBD;
import bean.entidad.Guia03;
import bean.interfase.IServicioAdministracionGuia03;
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
public class ServicioAdministracionGuia03 implements IServicioAdministracionGuia03 {

    @Override
    public void transportistaRegistrado() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Guia03> getTransportistasActivos() {
        Guia03 transportista;
        List<Guia03> listaTransportista = new ArrayList<Guia03>();
        ControladorBD conectarOracle = new ControladorBD();
        Connection con = null;
        Statement stmt = null;
        String sql = "select C3_CI_CHOFER as ciTransportista,"
                + "C3_NOMBRE_CHOFER as nombreTransportista,"
                + "C3_TELF_CHOFER as telfTransportista,"
                + "C3_CEL_CHOFER as celTransportista,"
                + "C3_DIRECCION_CHOFER as direccTransportista,"
                + "C3_COD_TRANSP as codTransp,"
                + "C3_TIPO_TRANSP as tipoTransp,"
                + "C3_CAPACIDAD as capacidad,"
                + "C3_PLACA_CHUTO as placaChuto,"
                + "C3_PLACA_BATEA as placaBatea,"
                + "C3_STATUS as status,"
                + "rowId as idRow "
                + "from GUIAS03_DAT "
                + "where C3_STATUS='A' "
                + "order by C3_NOMBRE_CHOFER, C3_CI_CHOFER ";
        try {
            con = conectarOracle.getConeccion();
            stmt = (Statement) con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String ciTransportista = rs.getString("ciTransportista");
                String nombreTransportista = rs.getString("nombreTransportista");
                String telfTransportista = rs.getString("telfTransportista");
                String celTransportista = rs.getString("celTransportista");
                String direccTransportista = rs.getString("direccTransportista");
                String codTransp = rs.getString("codTransp");
                String tipoTransp = rs.getString("tipoTransp");
                Double capacidad = rs.getDouble("capacidad");
                String placaChuto = rs.getString("placaChuto");
                String placaBatea = rs.getString("placaBatea");
                String status = rs.getString("status");
                String idRow = rs.getString("idRow");
                transportista = new Guia03(ciTransportista,
                        nombreTransportista,
                        telfTransportista,
                        celTransportista,
                        direccTransportista,
                        codTransp,
                        tipoTransp,
                        capacidad,
                        placaChuto,
                        placaBatea,
                        status,
                        idRow);
                listaTransportista.add(transportista);
            }  // while
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
            }  // if. 
        }
        if (con!=null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionGuia03.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  // con!=null.  
        return (listaTransportista);
    }  // getTransportistaActivos.    
     
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Boolean existeTransportista( String cedulaTransportista ) {
        ControladorBD conectarOracle = new ControladorBD();
        Boolean encontrado = false;
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C3_CI_CHOFER " +
                         "from   GUIAS03_DAT " +
                         "where  C3_CI_CHOFER='"+cedulaTransportista+"' " +
                         "and    C3_STATUS = 'A'";     // solo Transportistas A)ctivos.  
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
    }  // existeTrasnsportista.  
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Guia03 getDatosTransportista(String cedulaId) {
        Guia03 transportista = null;
        ControladorBD conectarOracle = new ControladorBD();
        Connection con = null;
        Statement stmt = null;
        String sql = "select C3_CI_CHOFER as ciTransportista,"
                + "C3_NOMBRE_CHOFER as nombreTransportista,"
                + "C3_TELF_CHOFER as telfTransportista,"
                + "C3_CEL_CHOFER as celTransportista,"
                + "C3_DIRECCION_CHOFER as direccTransportista,"
                + "C3_COD_TRANSP as codTransp,"
                + "C3_TIPO_TRANSP as tipoTransp,"
                + "C3_CAPACIDAD as capacidad,"
                + "C3_PLACA_CHUTO as placaChuto,"
                + "C3_PLACA_BATEA as placaBatea,"
                + "C3_STATUS as status,"
                + "rowId as idRow "
                + "from GUIAS03_DAT "
                + "where C3_STATUS='A' "
                + "and   C3_CI_CHOFER ='"+cedulaId+"' ";
        try {
            con = conectarOracle.getConeccion();
            stmt = (Statement) con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String ciTransportista = rs.getString("ciTransportista");
                String nombreTransportista = rs.getString("nombreTransportista");
                String telfTransportista = rs.getString("telfTransportista");
                String celTransportista = rs.getString("celTransportista");
                String direccTransportista = rs.getString("direccTransportista");
                String codTransp = rs.getString("codTransp");
                String tipoTransp = rs.getString("tipoTransp");
                Double capacidad = rs.getDouble("capacidad");
                String placaChuto = rs.getString("placaChuto");
                String placaBatea = rs.getString("placaBatea");
                String status = rs.getString("status");
                String idRow = rs.getString("idRow");
                transportista = new Guia03(ciTransportista,
                        nombreTransportista,
                        telfTransportista,
                        celTransportista,
                        direccTransportista,
                        codTransp,
                        tipoTransp,
                        capacidad,
                        placaChuto,
                        placaBatea,
                        status,
                        idRow);
            }  // if. 
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
            }  // if. 
        }
        if (con!=null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionGuia03.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  // con!=null.  
        return (transportista);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void imprimirExcepcion(SQLException excepcion) {
        System.err.println(excepcion);
    }
    
}  // ServicioAdministracionGuia03.  
