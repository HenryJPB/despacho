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
public class ServicioAdministracionInv01 implements IServicioAdministracionInv01 {

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Boolean productoStandardRegistrado(String codProducto) {
        ControladorBD conectarOracle = new ControladorBD();
        // *Declaracion de Atributos / Variables*:  
        Boolean encontrado = false;
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C1_CODIGO from INV01_DAT where C1_CODIGO='"+codProducto+"'"; 
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) encontrado = true;  
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionInv01.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionInv01.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally. 
        if (con!=null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionInv01.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (encontrado); 
    }  // productoStandardRegistrado().

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Inv01 getProductoStandard(String codProducto) {
        ControladorBD conectarOracle = new ControladorBD();
        // **Declaracion de Atributos / Variables**:  
        Connection con = null;
        Statement stmt = null;
        Inv01 datosProducto = null; 
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C1_CODIGO as codProducto, C1_TIPO as tipo, C1_DESCRIPCION as descripcion," +
                         "C1_ESPESOR as diametro, C1_LONGITUD as longitud, C1_ANCHO as altura, " +  
                         "C1_AREA as area, C1_SEPARACION as separacion, C1_ITEMS_ATADO as itemsAtados, " + 
                         "C1_PESO as peso, C1_PRECIO_ACTUAL as precio, C1_UNIDAD_MEDIDA as unidadMedida, " + 
                         "C1_COD_MAQUINA as codMaquina, C1_OBSERVACION as observacion, C1_UNIDADES as unidades, C1_COD_CONTABLE as codContable " +  
                         "from INV01_DAT " + 
                         "where C1_CODIGO ='"+codProducto+"' ";  
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String tipo = rs.getString("tipo");  
                String descripcion = rs.getString("descripcion");  
                Double diametro = rs.getDouble("diametro");  
                Double longitud = rs.getDouble("longitud");   
                Double altura =   rs.getDouble("altura");   
                Double area =  rs.getDouble("area");   
                String separacion = rs.getString("separacion");    
                Integer itemsAtados = rs.getInt("itemsAtados");   
                Double peso = rs.getDouble("peso");   
                Double precio = rs.getDouble("precio");    
                String unidadMedida =  rs.getString("unidadMedida");   
                String codMaquina = rs.getString("codMaquina");
                String observacion = rs.getString("observacion"); 
                Integer unidades = rs.getInt("unidades"); 
                String codContable =  rs.getString("codContable");
                datosProducto = new Inv01(codProducto,tipo,descripcion,diametro,longitud,altura,area,separacion,itemsAtados,peso,precio,unidadMedida,codMaquina,observacion,unidades,codContable); 
            }  // if.  
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionInv01.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionInv01.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally. 
        if (con!=null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionInv01.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (datosProducto); 
    }  // getProductoStandard().  
    
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public List<Inv01> getListaProductosStandard() {
        ControladorBD conectarOracle = new ControladorBD();
        // Declaracion de Atributos / Variables:  
        Inv01 producto;
        List<Inv01> listaProductos = new ArrayList<Inv01>();  
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarOracle.getConeccion();
            stmt = con.createStatement();
            String sql = "select C1_CODIGO as codigo," +
                         "C1_TIPO as tipoProducto," +
                         "C1_DESCRIPCION as descripcion " +
                         "from  INV01_DAT " +
                         "order by C1_CODIGO ";  
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                   String codigo   = rs.getString("codigo");  
                   String tipoProd = rs.getString("tipoProducto"); 
                   String descripcion = rs.getString("descripcion");  
                   producto = new Inv01(codigo,tipoProd,descripcion); 
                   listaProductos.add(producto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicioAdministracionInv01.class.getName()).log(Level.SEVERE, null, ex);
            imprimirExcepcion(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionInv01.class.getName()).log(Level.SEVERE, null, ex);
                    imprimirExcepcion(ex);
                }
            }  // try interno. 
        }  // finally. 
        if (con!=null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServicioAdministracionInv01.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (listaProductos); 
    }  // getListaProductosStandard.  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void imprimirExcepcion(SQLException ex) {
        System.out.println("ATENCION: "+ex);
    }

}  // ServicioAdministracionInv01.  


//************NOTAS UTILES: ****************************************************
    //--------------------------------------------------------------------------
    /*  MODELO de Servicio ( No escribas tanto ni tampoco le temais a Judas
     *  que solo Judas temio ):   
     * -------------------------------------------------------------------------
    public Boolean transporteRegistrado(String codTransporte) {
        ControladorBD conectarOracle = new ControladorBD();
        // Declaracion de Atributos / Variables:  
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


