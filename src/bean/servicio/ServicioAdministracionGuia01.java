/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.servicio;

import bean.controlador.ControladorBD;
import bean.entidad.Guia01;
import bean.interfase.IServicioAdministracionGuia01;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author henrypb
 */
public class ServicioAdministracionGuia01 implements IServicioAdministracionGuia01 {

    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Boolean guiaExiste(String nroGuia) {
        ControladorBD conectarBD = new ControladorBD();
        Boolean encontrado = false;
        Connection con = null;
        Statement stmt = null;
        String sql = "select C1_GUIA as nroGuia " +
                     "from   GUIAS01_DAT " +  
                     "where  C1_GUIA = '"+nroGuia+"' ";  
        try {
            con = conectarBD.getConeccion();
            stmt = (Statement) con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                encontrado = true; 
            }  // if interno.
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
                Logger.getLogger(ServicioAdministracionGuia01.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (encontrado);
    }   // guiaExiste.  
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Boolean guiaRegistrada(String ncf, String nroGuia, Date fechaGuia) {
        ControladorBD conectarBD = new ControladorBD();
        Boolean encontrado = false;
        Connection con = null;
        Statement stmt = null;
        String sql = "select C1_GUIA as nroGuia,"
                + "C1_FECHA_GUIA as fechaGuia,"
                + "C1_NCF as ncf "
                + "from GUIAS01_DAT "
                + "where ( '"+ncf+"' is null or ( '" + ncf + "' is not null and C1_NCF='" + ncf + "') ) "
                + "and   ( '"+nroGuia+"' is null or ( '" + nroGuia + "' is not null and C1_GUIA = '" + nroGuia + "') ) "
                + "and   exists ( select CODIGO "
                +               " from   CXCD_DAT "
                +               " where  CODIGO = C1_CODIGO_CLIENTE )"; 
        
        if (fechaGuia!=null) {
                sql = sql + "and ( '" + fechaGuia + "' is not null and C1_FECHA_GUIA='" + formatoFecha.format(fechaGuia) + "') ";
        }
        try {
            con = conectarBD.getConeccion();
            stmt = (Statement) con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                encontrado = true; 
            }  // if interno.
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
                Logger.getLogger(ServicioAdministracionGuia01.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (encontrado);
    } // guiaRegistrada. 
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Boolean nroCtrlFiscalExiste(String nroCtrlFiscal) {
        ControladorBD conectarBD = new ControladorBD();
        Boolean encontrado = Boolean.FALSE;
        Connection con = null;
        Statement stmt = null;
        try {
            con = conectarBD.getConeccion();
            stmt = con.createStatement();
            String sql = "select C1_NCF as ncf " +
                         "from   GUIAS01_DAT " +
                         "where  C1_NCF='"+nroCtrlFiscal+"' "; 
            ResultSet rs = stmt.executeQuery(sql);
            if ( rs.next() ) {
                encontrado = Boolean.TRUE; 
            }
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
                Logger.getLogger(ServicioAdministracionGuia01.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (encontrado); 
    }  // nroCtrlFiscalExiste().  
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Guia01 getMaestroGuia(String ncf, String nroGuia, Date fechaGuia) {
        ControladorBD conectarBD = new ControladorBD();
        Guia01 maestroGuia = null;
        Connection con = null;
        Statement stmt = null;
        String sql = "select C1_GUIA as nroGuia, C1_FECHA_GUIA as fechaGuia,"
                + "C1_CLIENTE_ESPECIAL as clienteEspecial,"
                + "C1_CODIGO_CLIENTE as codCliente,NOMBRE_CLI_PROV as nombreCliente, C1_RAZON_SOCIAL as razonSocial,"
                + "C1_RIF as rif, C1_NIT as nit,C1_NCF as ncf,"
                + "C1_DIR_FISCAL1 as dirFiscal1,C1_DIR_FISCAL2 as dirFiscal2,C1_DIR_FISCAL3 as dirFiscal3,"
                + "C1_RETIRADO_PLANTA as retiradoPlanta,"
                + "C1_ENTREGA1 as dirEntrega1,C1_ENTREGA2 as dirEntrega2,"
                + "C1_ENTREGA3 as dirEntrega3,C1_ENTREGA4 as dirEntrega4,"
                + "C1_ORDEN_COMPRA as ordenCompra,C1_PEDIDO1 as pedido1,C1_PEDIDO2 as pedido2,"
                + "C1_PEDIDO3 as pedido3,C1_PEDIDO4 as pedido4,"
                + "C1_PEDIDO5 as pedido5,C1_PEDIDO6 as pedido6,"
                + "C1_ORDEN1 as orden1,C1_ORDEN2 as orden2,"
                + "C1_FORMA_PAGO as formaPago,C1_PLAZO as plazo,"
                + "C1_VENDEDOR as vendedor,C1_NOMBRE_VENDEDOR as nombreVendedor,C1_ZONA as zona,"
                + "C1_ALICUOTA as alicuota,C1_FACTURA as factura,C1_FECHA_FACTURA as fechaFactura,"
                + "C1_NOMBRE_CHOFER as nombreChofer,C1_CI_CHOFER as ciChofer,"
                + "C1_COD_TRANSP as codTransp,C1_NOMBRE_TRANSP as nombreTransp,C1_TIPO_TRANSPORTE as tipoTransp,"
                + "C1_COD_CAMION as codCamion,C1_TIPO_CAMION as tipoCamion,C1_NO_EJES as nroEjes,"
                + "C1_CAPACIDAD as capacidad,C1_PLACA_CHUTO as placaChuto,C1_PLACA_BATEA as placaBatea,"
                + "C1_FECHA_RELACION as fechaRelacion,C1_COD_DESTINO as codDestino,C1_COD_SECTOR as codSector,"
                + "C1_NOMBRE_DESTINO as nombreDestino,C1_ESTADO as estado,"
                + "C1_GUIA_REPARTO as guiaReparto,C1_GUIA_ORIGEN_REPARTO as guiaOrigenReparto,"
                + "C1_SERIAL_TICKET1 as serialTicket1,"
                + "C1_PESO_TARA as pesoTara,C1_SERIAL_TICKET2 as serialTicket2,C1_PESO_BRUTO as pesoBruto,"
                + "C1_STATUS as status,C1_OBSERVACION as observacion,"
                + "C1_OBSERVACION_ROMANA1 as observacionRomana,C1_FLETE_PROCESADO as fleteProcesado,"
                + "GUIAS01_DAT.rowId as idRow "
                + "from GUIAS01_DAT,CXCD_DAT "
                + "where C1_CODIGO_CLIENTE = CODIGO "
                + "and ( '"+ncf+"' is null or ( '"+ncf+ "' is not null and C1_NCF='" + ncf + "') ) "; 
                if ( fechaGuia == null )  {
                   sql = sql + "and ( '"+nroGuia+"' is null or ( '" + nroGuia + "' is not null and C1_GUIA = '" + nroGuia + "') ) ";  
                }
                else {
                   sql = sql + "and ( '"+nroGuia+"' is null or ( '" + nroGuia + "' is not null and C1_GUIA = '" + nroGuia + "') ) " 
                             + "and ( '"+fechaGuia+"' is null or ( '" + fechaGuia + "' is not null and C1_FECHA_GUIA='" + formatoFecha.format(fechaGuia) + "') ) ";
                }
        try {
            con = conectarBD.getConeccion();
            stmt = (Statement) con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String  guia = rs.getString("nroGuia");
                Date    fecha = (java.sql.Date) rs.getDate("fechaGuia");
                String  clienteEspecial = rs.getString("clienteEspecial"); 
                String  codCliente    = rs.getString("codCliente"); 
                String  nombreCliente = rs.getString("nombreCliente");  
                String  razonSocial   = rs.getString("razonSocial");   
                String  rif = rs.getString("rif");  
                String  nit =rs.getString("nit");  
                String  nroCtrlFiscal = rs.getString("ncf");
                String  dirFiscal1    = rs.getString("dirFiscal1"); 
                String  dirFiscal2    = rs.getString("dirFiscal2");  
                String  dirFiscal3    = rs.getString("dirFiscal3"); 
                String  retiradoPlanta= rs.getString("retiradoPlanta"); 
                String  dirEntegra1   = rs.getString("dirEntrega1"); 
                String  dirEntegra2   = rs.getString("dirEntrega2"); 
                String  dirEntegra3   = rs.getString("dirEntrega3"); 
                String  dirEntegra4   = rs.getString("dirEntrega4");
                String  ordenCompra   = rs.getString("ordenCompra"); 
                String  pedido1       = rs.getString("pedido1"); 
                String  pedido2       = rs.getString("pedido2"); 
                String  pedido3       = rs.getString("pedido3"); 
                String  pedido4       = rs.getString("pedido4"); 
                String  pedido5       = rs.getString("pedido5");
                String  pedido6       = rs.getString("pedido6");
                String  orden1        = rs.getString("orden1"); 
                String  orden2        = rs.getString("orden2");  
                String  formaPago     = rs.getString("formaPago");  
                Integer pĺazo         = rs.getInt("plazo");    
                String  vendedor      = rs.getString("vendedor"); 
                String  nombreVendedor = rs.getString("nombreVendedor");  
                String  zona          = rs.getString("zona"); 
                Double  alicuota      = rs.getDouble("alicuota"); 
                String  factura       = rs.getString("factura");  
                Date    fechaFactura  = (java.sql.Date) rs.getDate("fechaFactura");
                String  nombreChofer  = rs.getString("nombreChofer"); 
                String  ciChofer      = rs.getString("ciChofer");  
                String  codTransp     = rs.getString("codTransp"); 
                String  nombreTransp  = rs.getString("nombreTransp");  
                String  tipoTransp    = rs.getString("tipoTransp"); 
                String  codCamion     = rs.getString("codCamion");  
                String  tipoCamion    = rs.getString("tipoCamion"); 
                String  nroEjes       = rs.getString("nroEjes");  
                Double  capacidad     = rs.getDouble("capacidad"); 
                String  placaChuto    = rs.getString("placaChuto"); 
                String  placaBatea    = rs.getString("placaBatea"); 
                Date    fechaRelacion = (java.sql.Date) rs.getDate("fechaRelacion");
                String  codDestino    = rs.getString("codDestino"); 
                String  codSector     = rs.getString("codSector");  
                String  nombreDestino = rs.getString("nombreDestino");  
                String  estado        = rs.getString("estado"); 
                String  guiaReparto   = rs.getString("guiaReparto");  
                String  guiaOrigenReparto = rs.getString("guiaOrigenReparto");  
                String  serialTicket1 = rs.getString("serialTicket1"); 
                Double  pesoTara      = rs.getDouble("pesoTara"); 
                String  serialTicket2 = rs.getString("serialTicket2"); 
                Double  pesoBruto     = rs.getDouble("pesoBruto");  
                String  status        = rs.getString("status");  
                String  observacion   = rs.getString("observacion"); 
                String  observacionRomana = rs.getString("observacionRomana");  
                String  fleteProcesado = rs.getString("fleteProcesado");
                String  rowId         = rs.getString("idRow"); 
                maestroGuia = new Guia01(guia,fecha, 
                                         clienteEspecial,
                                         codCliente,  
                                         nombreCliente, 
                                         razonSocial, 
                                         rif, 
                                         nit, 
                                         nroCtrlFiscal, 
                                         dirFiscal1, 
                                         dirFiscal2, 
                                         dirFiscal3, 
                                         retiradoPlanta, 
                                         dirEntegra1, 
                                         dirEntegra2, 
                                         dirEntegra3, 
                                         dirEntegra4, 
                                         ordenCompra, 
                                         pedido1, 
                                         pedido2, 
                                         pedido3, 
                                         pedido4, 
                                         pedido5, 
                                         pedido6, 
                                         orden1, 
                                         orden2, 
                                         formaPago, 
                                         pĺazo, 
                                         vendedor, 
                                         nombreVendedor, 
                                         zona, 
                                         alicuota, 
                                         factura, 
                                         fechaFactura, 
                                         nombreChofer, 
                                         ciChofer, 
                                         codTransp, 
                                         nombreTransp, 
                                         tipoTransp, 
                                         codCamion, 
                                         tipoCamion, 
                                         nroEjes, 
                                         capacidad, 
                                         placaChuto, 
                                         placaBatea, 
                                         fechaRelacion, 
                                         codDestino, 
                                         codSector, 
                                         nombreDestino, 
                                         estado,  
                                         guiaReparto, 
                                         guiaOrigenReparto, 
                                         serialTicket1, 
                                         pesoTara, 
                                         serialTicket2, 
                                         pesoBruto, 
                                         status, 
                                         observacion, 
                                         observacionRomana, 
                                         fleteProcesado,
                                         rowId); 
            }  // if interno.
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
                Logger.getLogger(ServicioAdministracionGuia01.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (maestroGuia);
    } // buscarGuia.  
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static String getNextGuia() {
        ControladorBD conectarBD = new ControladorBD();
        String nextGuia = null;  
        Connection con = null;
        Statement stmt = null;
        String sql = "select TO_CHAR( TO_NUMBER(C1_GUIA) + 1) as nextGuia " +  
                     "from   GUIAS01_DAT "  +  
                     "where  C1_GUIA = ( select MAX( C1_GUIA ) " +  
                                        "from   GUIAS01_DAT ) "; 
        try {
            con = conectarBD.getConeccion();
            stmt = (Statement) con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                nextGuia = rs.getString("nextGuia");  
            }  // if interno.
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
                Logger.getLogger(ServicioAdministracionGuia01.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (nextGuia);
    }  // getNextGuia().  
    
  //--------------------------------------------------------------------------
  //--------------------------------------------------------------------------
   public static long getNextNroCtrlFiscal() {
        ControladorBD conectarBD= new ControladorBD();
        long     nextNCF = 0;  
        Connection con = null;
        Statement stmt = null;
        String sql= "select TO_NUMBER( MAX( C1_NCF ) + 1 ) as nextNCF " +  
                    "from   GUIAS01_DAT " +  
                    "where  C1_NCF is not null ";        
                
        try {
            con = conectarBD.getConeccion();
            stmt = (Statement) con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                nextNCF = rs.getLong("nextNCF");  
            }  // if interno.
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
                Logger.getLogger(ServicioAdministracionGuia01.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (nextNCF);
    }   // getNextNroCtrlFiscal(). 
   
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void setParmPostearGuiaDespacho(PreparedStatement pstmt, Guia01 gui01 ) {
        
    }  // setParmPostearGuiaDespacho(). 
   
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void setParmPerNatural(Guia01 gui01 ) {
        
    }  // setParmPerNatural(). 
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void setParmTranspDestino(Guia01 gui01 ) {
        
    }  // setParmPostearGuiaDespacho(). 
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void setParmPesoRomana(Guia01 gui01 ) {
        
    }  // setParmPostearGuiaDespacho(). 
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public void incluir(Guia01 guia01) {
        ControladorBD conectarBD = new ControladorBD();
        Connection con = null;
        int ok = 0;
        PreparedStatement pstmt = null;
        String sql = "insert into GUIAS01_DAT " +
                     "values(?, ?, ? , ? , ? , ? , ? , ? , ?, ?," +  // 1
                     "       ?, ?, ?,  ?,  ?,  ?,  ?,  ?,  ?, ?," +  // 2
                     "       ?, ?, ?,  ?,  ?,  ?,  ?,  ?,  ?, ?," +  // 3
                     "       ?, ?, ?,  ?,  ?,  ?,  ?,  ?,  ?, ?," +  // 4
                     "       ?, ?, ?,  ?,  ?,  ?,  ?,  ?,  ?, ?," +  // 5
                     "       ?, ?, ?,  ?,  ?,  ?,  ?,  ?, ?)";       // 6
                     //      1  2  3   4   5   6   7   8   9  0 
        try {
            con = conectarBD.getConeccion();
            pstmt = con.prepareStatement(sql);  
            pstmt.setString(1, guia01.getC1_GUIA());                //  String  guia = rs.getString("nroGuia");
            pstmt.setDate(2, guia01.getC1_FECHA_GUIA());            //  Date    fecha = (java.sql.Date) rs.getDate("fechaGuia");
            pstmt.setString(3, guia01.getC1_CLIENTE_ESPECIAL());    //  String  clienteEspecial = rs.getString("clienteEspecial"); 
            pstmt.setString(4, guia01.getC1_CODIGO_CLIENTE());      //  String  codCliente    = rs.getString("codCliente"); 
            pstmt.setString(5, guia01.getC1_RAZON_SOCIAL());        //  String  razonSocial   = rs.getString("razonSocial");   
            pstmt.setString(6, guia01.getC1_RIF());                 //  String  rif = rs.getString("rif");  
            pstmt.setString(7, guia01.getC1_NIT());                 //  String  nit =rs.getString("nit");  
            pstmt.setString(8, guia01.getC1_NCF());                 //  String  nroCtrlFiscal = rs.getString("ncf");
            pstmt.setString(9, guia01.getC1_DIR_FISCAL1());         //  String  dirFiscal1    = rs.getString("dirFiscal1"); 
            pstmt.setString(10, guia01.getC1_DIR_FISCAL2());        //  String  dirFiscal2    = rs.getString("dirFiscal2");  
            pstmt.setString(11, guia01.getC1_DIR_FISCAL3());        //  String  dirFiscal3    = rs.getString("dirFiscal3"); 
            pstmt.setString(12, guia01.getC1_RETIRADO_PLANTA());    //  String  retiradoPlanta= rs.getString("retiradoPlanta"); 
            pstmt.setString(13, guia01.getC1_ENTREGA1());           //  String  dirEntegra1   = rs.getString("dirEntrega1"); 
            pstmt.setString(14, guia01.getC1_ENTREGA2());           //  String  dirEntegra2   = rs.getString("dirEntrega2"); 
            pstmt.setString(15, guia01.getC1_ENTREGA3());           //  String  dirEntegra3   = rs.getString("dirEntrega3"); 
            pstmt.setString(16, guia01.getC1_ENTREGA4());           //  String  dirEntegra4   = rs.getString("dirEntrega4");
            pstmt.setString(17, guia01.getC1_ORDEN_COMPRA());       //  String  ordenCompra   = rs.getString("ordenCompra"); 
            pstmt.setString(18, guia01.getC1_PEDIDO1());            //  String  pedido1       = rs.getString("pedido1"); 
            pstmt.setString(19, guia01.getC1_PEDIDO2());            //  String  pedido2       = rs.getString("pedido2"); 
            pstmt.setString(20, guia01.getC1_PEDIDO3());            //  String  pedido3       = rs.getString("pedido3"); 
            pstmt.setString(21, guia01.getC1_PEDIDO4());            //  String  pedido4       = rs.getString("pedido4"); 
            pstmt.setString(22, guia01.getC1_PEDIDO5());            //  String  pedido5       = rs.getString("pedido5");
            pstmt.setString(23, guia01.getC1_PEDIDO6());            //  String  pedido6       = rs.getString("pedido6");
            pstmt.setString(24, guia01.getC1_ORDEN1());             //  String  orden1        = rs.getString("orden1"); 
            pstmt.setString(25, guia01.getC1_ORDEN2());             //  String  orden2        = rs.getString("orden2");  
            pstmt.setString(26, guia01.getC1_FORMA_PAGO());         //  String  formaPago     = rs.getString("formaPago");  
            pstmt.setInt(27, guia01.getC1_PLAZO());                 //  Integer pĺazo         = rs.getInt("plazo");    
            pstmt.setString(28, guia01.getC1_VENDEDOR());           //  String  vendedor      = rs.getString("vendedor"); 
            pstmt.setString(29, guia01.getC1_NOMBRE_VENDEDOR());    //  String  nombreVendedor = rs.getString("nombreVendedor");  
            pstmt.setString(30, guia01.getC1_ZONA());               //  String  zona          = rs.getString("zona"); 
            pstmt.setDouble(31, guia01.getC1_ALICUOTA());           //  Double  alicuota      = rs.getDouble("alicuota"); 
            pstmt.setString(32, guia01.getC1_FACTURA());            //  String  factura       = rs.getString("factura");  
            pstmt.setDate(33, guia01.getC1_FECHA_FACTURA());        //  Date    fechaFactura  = (java.sql.Date) rs.getDate("fechaFactura");
            pstmt.setString(34, guia01.getC1_NOMBRE_CHOFER());      //  String  nombreChofer  = rs.getString("nombreChofer"); 
            pstmt.setString(35, guia01.getC1_CI_CHOFER());          //  String  ciChofer      = rs.getString("ciChofer");  
            pstmt.setString(36, guia01.getC1_COD_TRANSP());         //  String  codTransp     = rs.getString("codTransp"); 
            pstmt.setString(37, guia01.getC1_NOMBRE_TRANSP());      //  String  nombreTransp  = rs.getString("nombreTransp");  
            pstmt.setString(38, guia01.getC1_TIPO_TRANSPORTE());    //  String  tipoTransp    = rs.getString("tipoTransp"); 
            pstmt.setString(39, guia01.getC1_COD_CAMION());         //  String  codCamion     = rs.getString("codCamion");  
            pstmt.setString(40, guia01.getC1_TIPO_CAMION());        //  String  tipoCamion    = rs.getString("tipoCamion"); 
            pstmt.setString(41, guia01.getC1_NO_EJES());            //  String  nroEjes       = rs.getString("nroEjes");  
            pstmt.setDouble(42, ( guia01.getC1_CAPACIDAD()==null? 0.00 : guia01.getC1_CAPACIDAD()) );  //  Double  capacidad     = rs.getDouble("capacidad"); 
            pstmt.setString(43, guia01.getC1_PLACA_CHUTO());        //  String  placaChuto    = rs.getString("placaChuto"); 
            pstmt.setString(44, guia01.getC1_PLACA_BATEA());        //  String  placaBatea    = rs.getString("placaBatea"); 
            pstmt.setDate(45, guia01.getC1_FECHA_RELACION());       //  Date    fechaRelacion = (java.sql.Date) rs.getDate("fechaRelacion");
            pstmt.setString(46, guia01.getC1_COD_DESTINO());        //  String  codDestino    = rs.getString("codDestino"); 
            pstmt.setString(47, guia01.getC1_COD_SECTOR());         //  String  codSector. 
            pstmt.setString(48, guia01.getC1_NOMBRE_DESTINO());     //  String  nombreDestino = rs.getString("nombreDestino");  
            pstmt.setString(49, guia01.getC1_ESTADO());             //  String  estado        = rs.getString("estado"); 
            pstmt.setString(50, guia01.getC1_GUIA_REPARTO());       //  String  guiaReparto   = rs.getString("guiaReparto");  
            pstmt.setString(51, guia01.getC1_GUIA_ORIGEN_REPARTO()); // String  guiaOrigenReparto = rs.getString("guiaOrigenReparto");  
            pstmt.setString(52, guia01.getC1_SERIAL_TICKET1());     //  String  serialTicket1 = rs.getString("serialTicket1"); 
            pstmt.setDouble(53, ( guia01.getC1_PESO_TARA()==null ? 0.00 : guia01.getC1_PESO_TARA()) );   //  Double  pesoTara      = rs.getDouble("pesoTara"); 
            pstmt.setString(54, guia01.getC1_SERIAL_TICKET1());     //  String  serialTicket2 = rs.getString("serialTicket2"); 
            pstmt.setDouble(55, ( guia01.getC1_PESO_BRUTO()==null ? 0.00 : guia01.getC1_PESO_BRUTO()) ); //  Double  pesoBruto     = rs.getDouble("pesoBruto");  
            pstmt.setString(56, guia01.getC1_STATUS());             //  String  status        = rs.getString("status");  
            pstmt.setString(57, guia01.getC1_OBSERVACION());        //  String  observacion   = rs.getString("observacion"); 
            pstmt.setString(58, guia01.getC1_OBSERVACION_ROMANA1()); // String  observacionRomana = rs.getString("observacionRomana");  
            pstmt.setString(59, guia01.getC1_FLETE_PROCESADO());    //  String  fleteProcesado = rs.getString("fleteProcesado");
            pstmt.executeUpdate();
        } catch (SQLException excepcion) {
            imprimirExcepcion(excepcion);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionGuia01.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionGuia01.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }  // incluir().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public void actualizar(Guia01 guia01) {
        ControladorBD conectarBD = new ControladorBD();
        Connection con = null;
        int ok = 0;
        PreparedStatement pstmt = null;
        String sql = "update GUIAS01_DAT set " +  
              "C1_FECHA_GUIA = ?," +                                            // P-1.   
              "C1_CLIENTE_ESPECIAL = ?," +                                      // P-2. 
              "C1_CODIGO_CLIENTE = ?,"+                                         // P-3.   
              "C1_RAZON_SOCIAL =?," +                                           // P-4. 
              "C1_RIF = ?," +                                                   // P-5. 
              "C1_NIT = ?," +                                                   // P-6.
              "C1_NCF = ?," +                                                   // P-7.
              "C1_DIR_FISCAL1 = ?," +                                           // P-8.
              "C1_DIR_FISCAL2 = ?," +                                           // P-9.
              "C1_DIR_FISCAL3 = ?," +                                           // P-10.
              "C1_RETIRADO_PLANTA = ?," +                                       // P-11.
              "C1_ENTREGA1 = ?," +                                              // P-12.
              "C1_ENTREGA2 = ?," +                                              // P-13.
              "C1_ENTREGA3 = ?," +                                              // P-14.
              "C1_ENTREGA4 = ?," +                                              // P-15.
              "C1_ORDEN_COMPRA = ?," +                                          // P-16.
              "C1_PEDIDO1 = ?," +                                               // P-17.
              "C1_PEDIDO2 = ?," +                                               // P-18.
              "C1_PEDIDO3 = ?," +                                               // P-19.
              "C1_PEDIDO4 = ?," +                                               // P-20.
              "C1_PEDIDO5 = ?," +                                               // P-21.
              "C1_PEDIDO6 = ?," +                                               // P-22.
              "C1_ORDEN1  = ?," +                                               // P-23.
              "C1_ORDEN2  = ?," +                                               // P-24.
              "C1_FORMA_PAGO = ?," +                                            // P-25.
              "C1_PLAZO = ?," +                                                 // P-26.
              "C1_VENDEDOR = ?," +                                              // P-27.
              "C1_NOMBRE_VENDEDOR = ?," +                                       // P-28.
              "C1_ZONA = ?," +                                                  // P-29.
              "C1_ALICUOTA = ?," +                                              // P-30.
              "C1_FACTURA = ?," +                                               // P-31.
              "C1_FECHA_FACTURA = ?,"  +                                        // P-32.
              "C1_NOMBRE_CHOFER = ?," +                                         // P-33.
              "C1_CI_CHOFER = ?," +                                             // P-34.
              "C1_COD_TRANSP = ?," +                                            // P-35.
              "C1_NOMBRE_TRANSP = ?," +                                         // P-36.
              "C1_TIPO_TRANSPORTE = ?," +                                       // P-37.
              "C1_COD_CAMION = ?," +                                            // P-38.
              "C1_TIPO_CAMION = ?," +                                           // P-39.
              "C1_NO_EJES = ?," +                                               // P-40.
              "C1_CAPACIDAD = ?," +                                             // P-41.
              "C1_PLACA_CHUTO = ?," +                                           // P-42.
              "C1_PLACA_BATEA = ?," +                                           // P-43.
              "C1_FECHA_RELACION = ?," +                                        // P-44.
              "C1_COD_DESTINO = ?," +                                           // P-45.
              "C1_COD_SECTOR = ?," +                                            // P-46.  
              "C1_NOMBRE_DESTINO = ?," +                                        // P-47.
              "C1_ESTADO = ?," +                                                // P-48.
              "C1_GUIA_REPARTO = ?," +                                          // P-49.
              "C1_GUIA_ORIGEN_REPARTO = ?," +                                   // P-50.
              "C1_SERIAL_TICKET1 = ?," +                                        // P-51.
              "C1_PESO_TARA = ?," +                                             // P-52.
              "C1_SERIAL_TICKET2 =?," +                                         // P-53.
              "C1_PESO_BRUTO = ?," +                                            // P-54.
              "C1_STATUS = ?," +                                                // P-55.
              "C1_OBSERVACION = ?," +                                           // P-56.
              "C1_OBSERVACION_ROMANA1 = ?," +                                   // P-57.
              "C1_FLETE_PROCESADO = ? " +                                       // P-58.
              "where C1_GUIA='" + guia01.getC1_GUIA()+ "'";
        try {
            con = conectarBD.getConeccion();
            pstmt = con.prepareStatement(sql);  
            pstmt.setDate(1, guia01.getC1_FECHA_GUIA());            //  Date    fecha = (java.sql.Date) rs.getDate("fechaGuia");
            pstmt.setString(2, guia01.getC1_CLIENTE_ESPECIAL());    //  String  clienteEspecial = rs.getString("clienteEspecial"); 
            pstmt.setString(3, guia01.getC1_CODIGO_CLIENTE());      //  String  codCliente    = rs.getString("codCliente"); 
            pstmt.setString(4, guia01.getC1_RAZON_SOCIAL());        //  String  razonSocial   = rs.getString("razonSocial");   
            pstmt.setString(5, guia01.getC1_RIF());                 //  String  rif = rs.getString("rif");  
            pstmt.setString(6, guia01.getC1_NIT());                 //  String  nit =rs.getString("nit");  
            pstmt.setString(7, guia01.getC1_NCF());                 //  String  nroCtrlFiscal = rs.getString("ncf");
            pstmt.setString(8, guia01.getC1_DIR_FISCAL1());         //  String  dirFiscal1    = rs.getString("dirFiscal1"); 
            pstmt.setString(9, guia01.getC1_DIR_FISCAL2());        //  String  dirFiscal2    = rs.getString("dirFiscal2");  
            pstmt.setString(10, guia01.getC1_DIR_FISCAL3());        //  String  dirFiscal3    = rs.getString("dirFiscal3"); 
            pstmt.setString(11, guia01.getC1_RETIRADO_PLANTA());    //  String  retiradoPlanta= rs.getString("retiradoPlanta"); 
            pstmt.setString(12, guia01.getC1_ENTREGA1());           //  String  dirEntegra1   = rs.getString("dirEntrega1"); 
            pstmt.setString(13, guia01.getC1_ENTREGA2());           //  String  dirEntegra2   = rs.getString("dirEntrega2"); 
            pstmt.setString(14, guia01.getC1_ENTREGA3());           //  String  dirEntegra3   = rs.getString("dirEntrega3"); 
            pstmt.setString(15, guia01.getC1_ENTREGA4());           //  String  dirEntegra4   = rs.getString("dirEntrega4");
            pstmt.setString(16, guia01.getC1_ORDEN_COMPRA());       //  String  ordenCompra   = rs.getString("ordenCompra"); 
            pstmt.setString(17, guia01.getC1_PEDIDO1());            //  String  pedido1       = rs.getString("pedido1"); 
            pstmt.setString(18, guia01.getC1_PEDIDO2());            //  String  pedido2       = rs.getString("pedido2"); 
            pstmt.setString(19, guia01.getC1_PEDIDO3());            //  String  pedido3       = rs.getString("pedido3"); 
            pstmt.setString(20, guia01.getC1_PEDIDO4());            //  String  pedido4       = rs.getString("pedido4"); 
            pstmt.setString(21, guia01.getC1_PEDIDO5());            //  String  pedido5       = rs.getString("pedido5");
            pstmt.setString(22, guia01.getC1_PEDIDO6());            //  String  pedido6       = rs.getString("pedido6");
            pstmt.setString(23, guia01.getC1_ORDEN1());             //  String  orden1        = rs.getString("orden1"); 
            pstmt.setString(24, guia01.getC1_ORDEN2());             //  String  orden2        = rs.getString("orden2");  
            pstmt.setString(25, guia01.getC1_FORMA_PAGO());         //  String  formaPago     = rs.getString("formaPago");  
            pstmt.setInt(26, guia01.getC1_PLAZO());                 //  Integer pĺazo         = rs.getInt("plazo");    
            pstmt.setString(27, guia01.getC1_VENDEDOR());           //  String  vendedor      = rs.getString("vendedor"); 
            pstmt.setString(28, guia01.getC1_NOMBRE_VENDEDOR());    //  String  nombreVendedor = rs.getString("nombreVendedor");  
            pstmt.setString(29, guia01.getC1_ZONA());               //  String  zona          = rs.getString("zona"); 
            pstmt.setDouble(30, guia01.getC1_ALICUOTA());           //  Double  alicuota      = rs.getDouble("alicuota"); 
            pstmt.setString(31, guia01.getC1_FACTURA());            //  String  factura       = rs.getString("factura");  
            pstmt.setDate(32, guia01.getC1_FECHA_FACTURA());        //  Date    fechaFactura  = (java.sql.Date) rs.getDate("fechaFactura");
            pstmt.setString(33, guia01.getC1_NOMBRE_CHOFER());      //  String  nombreChofer  = rs.getString("nombreChofer"); 
            pstmt.setString(34, guia01.getC1_CI_CHOFER());          //  String  ciChofer      = rs.getString("ciChofer");  
            pstmt.setString(35, guia01.getC1_COD_TRANSP());         //  String  codTransp     = rs.getString("codTransp"); 
            pstmt.setString(36, guia01.getC1_NOMBRE_TRANSP());      //  String  nombreTransp  = rs.getString("nombreTransp");  
            pstmt.setString(37, guia01.getC1_TIPO_TRANSPORTE());    //  String  tipoTransp    = rs.getString("tipoTransp"); 
            pstmt.setString(38, guia01.getC1_COD_CAMION());         //  String  codCamion     = rs.getString("codCamion");  
            pstmt.setString(39, guia01.getC1_TIPO_CAMION());        //  String  tipoCamion    = rs.getString("tipoCamion"); 
            pstmt.setString(40, guia01.getC1_NO_EJES());            //  String  nroEjes       = rs.getString("nroEjes");  
            pstmt.setDouble(41, ( guia01.getC1_CAPACIDAD()==null? 0.00 : guia01.getC1_CAPACIDAD()) );  //  Double  capacidad     = rs.getDouble("capacidad"); 
            pstmt.setString(42, guia01.getC1_PLACA_CHUTO());        //  String  placaChuto    = rs.getString("placaChuto"); 
            pstmt.setString(43, guia01.getC1_PLACA_BATEA());        //  String  placaBatea    = rs.getString("placaBatea"); 
            pstmt.setDate(44, guia01.getC1_FECHA_RELACION());       //  Date    fechaRelacion = (java.sql.Date) rs.getDate("fechaRelacion");
            pstmt.setString(45, guia01.getC1_COD_DESTINO());        //  String  codDestino    = rs.getString("codDestino"); 
            pstmt.setString(46, guia01.getC1_COD_SECTOR());         //  String  codSector
            pstmt.setString(47, guia01.getC1_NOMBRE_DESTINO());     //  String  nombreDestino = rs.getString("nombreDestino");  
            pstmt.setString(48, guia01.getC1_ESTADO());             //  String  estado        = rs.getString("estado"); 
            pstmt.setString(49, guia01.getC1_GUIA_REPARTO());       //  String  guiaReparto   = rs.getString("guiaReparto");  
            pstmt.setString(50, guia01.getC1_GUIA_ORIGEN_REPARTO()); // String  guiaOrigenReparto = rs.getString("guiaOrigenReparto");  
            pstmt.setString(51, guia01.getC1_SERIAL_TICKET1());     //  String  serialTicket1 = rs.getString("serialTicket1"); 
            pstmt.setDouble(52, ( guia01.getC1_PESO_TARA()==null ? 0.00 : guia01.getC1_PESO_TARA()) );   //  Double  pesoTara      = rs.getDouble("pesoTara"); 
            pstmt.setString(53, guia01.getC1_SERIAL_TICKET1());     //  String  serialTicket2 = rs.getString("serialTicket2"); 
            pstmt.setDouble(54, ( guia01.getC1_PESO_BRUTO()==null ? 0.00 : guia01.getC1_PESO_BRUTO()) ); //  Double  pesoBruto     = rs.getDouble("pesoBruto");  
            pstmt.setString(55, guia01.getC1_STATUS());             //  String  status        = rs.getString("status");  
            pstmt.setString(56, guia01.getC1_OBSERVACION());        //  String  observacion   = rs.getString("observacion"); 
            pstmt.setString(57, guia01.getC1_OBSERVACION_ROMANA1()); // String  observacionRomana = rs.getString("observacionRomana");  
            pstmt.setString(58, guia01.getC1_FLETE_PROCESADO());    //  String  fleteProcesado = rs.getString("fleteProcesado");
            ok = pstmt.executeUpdate();
        } // try
        catch (SQLException excepcion) {
            imprimirExcepcion(excepcion);
        } catch (Exception excepcion) {
            imprimirExcepcion( (SQLException) excepcion );
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionGuia01.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionGuia01.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } // finally.
    }  // actualizar().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public void eliminar( String nroGuia ) {
        ControladorBD conectarBD = new ControladorBD();
        Connection con = null;
        PreparedStatement pstmt = null;
        int ok;  
        String sql = "delete from GUIAS01_DAT "
                   + "where  C1_GUIA = '"+nroGuia+"'"; 
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
                    Logger.getLogger(ServicioAdministracionGuia01.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioAdministracionGuia01.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } // finally.
    }  // eliminar(). 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private static void imprimirExcepcion(SQLException excepcion) {
        System.err.println(excepcion);
    }
    
}  // ServicioAdministracionGuia01  ( maestroGuia ). 

    //--------------------------------------------------------------------------
    /*  MODELO de Servicio ( No escribas tanto ni tampoco le temais a Judas
     *  que solo Judas temio ):   
     *--------------------------------------------------------------------------
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
               << codigo aqui >>.  
            }
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
                Logger.getLogger(ServicioAdministracionGuia01.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (encontrado); 
    }  // * unidadTransporteRegistrada *
    * 
    */