/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.cliente;

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
//------------------------------------------------------------------------------
//------------------------------------------------------------------------------
public class ServicioAdministracionCliente implements IServicioAdministracionCliente {

    // Eliminar este metodo:
    public List<Cliente> prueba() {
           List<Cliente> listaClientes = new ArrayList<Cliente>(); 
           listaClientes.add( new Cliente("select","01","Prueba01"));  
           listaClientes.add( new Cliente("select","02","Prueba02"));
           listaClientes.add( new Cliente("select","03","Prueba03"));
           listaClientes.add( new Cliente("select","04","Prueba04"));
           listaClientes.add( new Cliente("select","05","Prueba05"));
           listaClientes.add( new Cliente("select","06","Prueba06"));
           listaClientes.add( new Cliente("select","07","Prueba07"));
           listaClientes.add( new Cliente("select","08","Prueba08"));
           return listaClientes; 
    }
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Boolean clienteRegistrado( String codClienteBuscar ) {
       ControladorBD conectarOracle = new ControladorBD();
       Boolean        encontrado = false;  
       Connection     con;
       Statement      stmt = null;
       String         sql = "select CODIGO "
                          + "from   CXCD_DAT "
                          + "where  CODIGO = '"+codClienteBuscar+"' ";  
       try {
           con  = conectarOracle.getConeccion(); 
           stmt = con.createStatement(); 
           ResultSet rs = stmt.executeQuery(sql);  
           if (rs.next() ) {
               encontrado = true; 
           }
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionCliente.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        }  finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionCliente.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally.   // try. 
       return (encontrado); 
    } // clienteRegistrado. 
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Cliente getCliente( String codClienteBuscar ) {
       ControladorBD conectarOracle = new ControladorBD();
       Cliente        regCliente=null; 
       Connection     con;
       Statement      stmt = null;
       String         sql = "select CODIGO as codCliente, NOMBRE_CLI_PROV as nombreCliente "
                          + "from   CXCD_DAT "
                          + "where  CODIGO = '"+codClienteBuscar+"' ";  
       try {
           con  = conectarOracle.getConeccion(); 
           stmt = con.createStatement(); 
           ResultSet rs = stmt.executeQuery(sql);  
           if (rs.next() ) {
               String codCliente    = rs.getString("codCliente"); 
               String nombreCliente = rs.getString("nombreCliente"); 
               regCliente = new Cliente(null,codCliente,nombreCliente); 
           }
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionCliente.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        }  finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionCliente.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally.   // try. 
       return (regCliente); 
    } // getCliente. 
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public List<Cliente> getClientesActivos() {
        ControladorBD conectarOracle = new ControladorBD();
        List<Cliente> clientes = new ArrayList<Cliente>(); 
        Connection    con;
        Statement     stmt = null; 
        String        sql = "select CODIGO as codCliente,"
                          + "NOMBRE_CLI_PROV as nombreCliente "
                          + "from   CXCD_DAT "
                          + "where  substr(CODIGO,1,1) = 'C' "  // Solo clientes
                          + "and    ( TIPO_DE_CLIENTE is not null and TIPO_DE_CLIENTE = 'A' ) "     // activos. 
                          + "order  by NOMBRE_CLI_PROV"; 
        try {
            con = conectarOracle.getConeccion(); 
            stmt = (Statement) con.createStatement();
            ResultSet rs = stmt.executeQuery(sql); 
            while ( rs.next() ) {
                String codCliente = rs.getString("codCliente");
                String nombreCliente = rs.getString("nombreCliente"); 
                Cliente cliente = new Cliente("Seleccionar",codCliente,nombreCliente);
                clientes.add(cliente);
            }  // while.
         } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionCliente.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
         }  finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionCliente.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally.   
        return ( clientes ); 
    }
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void imprimirExcepcion(SQLException excepcion) {
        System.out.println(excepcion);
    }
}  // ServicioAdministracionCliente. 
