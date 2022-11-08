/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import bean.controlador.ControladorDetalleGuia;
import bean.controlador.ControladorGuiaDespacho;
import bean.controlador.ControladorLovTransportista;
import bean.controlador.ControladorLovUnidadTransporte;
import bean.entidad.Guia01;
import bean.entidad.Guia03;
import bean.entidad.Guia05;
import bean.modelo.ModeloTablaGuia02;
import bean.servicio.ServicioAdministracionGuia01;
import bean.servicio.ServicioAdministracionGuia02;
import bean.servicio.ServicioAdministracionGuia03;
import bean.servicio.ServicioAdministracionGuia05;
import despacho.Estilos;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import bean.utilitario.cliente.ControladorLovCliente;
import bean.utilitario.cliente.ServicioAdministracionCliente;
import bean.utilitario.contab.Contabaf;
import bean.utilitario.contab.ServicioAdministracionContabaf;
import bean.utilitario.fleteDestino.ControladorLovFleteDestino;
import bean.utilitario.inventFacturacion.ServicioAdministracionInv04;
import bean.utilitario.libreria.LibreriaHP;
import bean.utilitario.venta.ServicioAdministracionVend01;
import bean.utilitario.venta.ServicioAdministracionVend03;
import bean.utilitario.venta.Vend01;
import bean.utilitario.venta.Vend03;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author henrypb
 */
public class PanelGuiaDespacho extends javax.swing.JPanel {
    /*
     * FrameMenuPrincipal framePrincipal = new FrameMenuPrincipal(); Integer
     * xWindow = framePrincipal.xWindow; Integer yWindow =
     * framePrincipal.yWindow;
     */
    // NOTA: Dimensiones de este panel:
    // Hor. Size=954.
    // Ver. Size=Default. 

    Estilos estilo = new Estilos();
    final Integer xWindow = 950 - 20; 
    final Integer yWindow = 730 - 20;
    ServicioAdministracionGuia01 servicioAdministracionGuia01 = new ServicioAdministracionGuia01();
    //ControladorDetalleGuia controladorDetalleGuia;
    LibreriaHP libreriaHP = new LibreriaHP(); 
    /**
     * Creates new form PanelGuiasDespacho
     */
    public PanelGuiaDespacho() {
        //initComponents();
        iniciarComponentes();
        desactivarCampos();
        //setBackground(estilo.backgroundColor); 
        //this.txtFechaGuia.setEnabled(true);
        //this.txtFechaGuia.setDate(new Date());
        //this.jTabbedPaneGuiaDespacho.setSize(100, 100);  // ?? No esta funcionando.
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void iniciarComponentes() {
        initComponents();
        PanelGuiaDespacho.txtDisplayGuiaProcesada.setVisible(false);
        PanelGuiaDespacho.chbClienteValido.setVisible(false);
        PanelGuiaDespacho.chbClienteValido.setSelected(true);  // El cliente es valido si esta certificado o nulo (empty). 
        PanelAgregarDetalleGuia panelAgregarDetalleGuia = new PanelAgregarDetalleGuia();
        //PanelGuiaDespacho.scrollPaneDetalleGuia.setViewportView(panelAgregarDetalleGuia);
        ControladorDetalleGuia controladorDetalleGuia = new ControladorDetalleGuia();
        //controladorDetalleGuia.configurarTablaDetalleGuia(); antes. Ahora: 
        controladorDetalleGuia.iniciarTablaDetalleGuia();
    }   // iniciarComponentes(). 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void setIndicadorModoOperacion(String indicador) {
        switch (indicador.charAt(0)) {
            case 'c':
                lblModoOperacion.setText("Modo: " + indicador);
                chbModoConsultar.setSelected(true);
                chbModoInsertar.setSelected(false);
                chbModoEditar.setSelected(false);
                break;
            case 'i':
                lblModoOperacion.setText("Modo: " + indicador);
                chbModoInsertar.setSelected(true);
                chbModoConsultar.setSelected(false);
                chbModoEditar.setSelected(false);
                break;
            case 'e':
                lblModoOperacion.setText("Modo: " + indicador);
                chbModoEditar.setSelected(true);
                break;
            default:    // home. 
                lblModoOperacion.setText("Modo: " + indicador);
                //chbModoConsultar.setSelected(false);
                //chbModoInsertar.setSelected(false);
                chbModoEditar.setSelected(false);
        }   // switch().  
    }   //  setIndicadorModoOperacion().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static void activarCampos() {
        if (chbModoConsultar.isSelected() && chbModoEditar.isSelected()) {   // Editar un reg consultado.
            // no Activar los campos claves <-> Busquedad: son No updatable porque son claves.  
            activarCamposPostearGuiaDespacho(true);
        } else if (chbModoInsertar.isSelected() && chbModoEditar.isSelected()) {  // Insertar un reg.  
            activarCamposBusqueda(true);
            activarCamposPostearGuiaDespacho(true);
        }
        activarCamposDespachoPerNatural(true);
        activarCamposTransporteDestino(true);
        activarCamposDetalleProductos(true);
        activarCamposPesoRomana(true);
    }   // activarCampos().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static void desactivarCampos() {
        activarCamposPostearGuiaDespacho(false);
        activarCamposDespachoPerNatural(false);
        activarCamposTransporteDestino(false);
        activarCamposDetalleProductos(false);
        activarCamposPesoRomana(false);
        activarCamposBusqueda(false);
        setBotoneraBusqueda(false);       // btnBuscar = true, btnEjecutarBusqueda=false,...
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private static void activarCamposPostearGuiaDespacho(Boolean activar) {
        //PanelGuiaDespacho.txtNroControlFiscal.setEditable(activar);   // (*) Campo clave. 
        //PanelGuiaDespacho.txtNroGuia.setEditable(activar);            // (*) Campo clave.
        //PanelGuiaDespacho.txtFechaGuia.setEnabled(activar);           // (*) Campo clave.
        PanelGuiaDespacho.chbRetiradoPlanta.setEnabled(activar);
        PanelGuiaDespacho.chbVentaContado.setEnabled(activar);
        PanelGuiaDespacho.cbbFormaPago.setEnabled(activar);
        PanelGuiaDespacho.spnPlazoDias.setEnabled(activar);
        PanelGuiaDespacho.btnLovCliente.setEnabled(activar);
        PanelGuiaDespacho.txtCodCliente.setEditable(activar);
        //PanelGuiaDespacho.txtRazonSocial.setEnabled(true);   // <! objeto tipo Display.
        PanelGuiaDespacho.txtDireccionEntrega.setEditable(activar);
        PanelGuiaDespacho.txtNroControlFiscal.setEditable(activar);
        PanelGuiaDespacho.txtOrdenCompra.setEditable(activar);
        PanelGuiaDespacho.txtOrden1.setEditable(activar);
        PanelGuiaDespacho.txtOrden2.setEditable(activar);
        PanelGuiaDespacho.txtPedido1.setEditable(activar);
        PanelGuiaDespacho.txtPedido2.setEditable(activar);
        PanelGuiaDespacho.txtPedido3.setEditable(activar);
        PanelGuiaDespacho.txtAlicuota.setEditable(activar);
        PanelGuiaDespacho.txtPedido4.setEditable(activar);
        PanelGuiaDespacho.cbbVendedor.setEnabled(activar);
        PanelGuiaDespacho.txtCodVendedor.setEditable(activar);   // <! objeto tipo Display. ??? 
        PanelGuiaDespacho.cbbZonaVenta.setEnabled(activar);
        PanelGuiaDespacho.txtObservacion.setEditable(activar);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private static void activarCamposDespachoPerNatural(Boolean activar) {
        PanelGuiaDespacho.txtCedula.setEditable(activar);
        PanelGuiaDespacho.txtRazonSocialPerNatural.setEditable(activar);
        PanelGuiaDespacho.txtNit.setEditable(activar);
        PanelGuiaDespacho.txtDireccionFiscal.setEditable(activar);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private static void activarCamposTransporteDestino(Boolean activar) {
        PanelGuiaDespacho.btnLovTransportista.setEnabled(activar);
        PanelGuiaDespacho.txtCedulaTransportista.setEditable(activar);  // <= posee L.O.V.  
        //PanelGuiaDespacho.txtNombreTransportista.setEditable(activar);
        PanelGuiaDespacho.txtCodTransporte.setEditable(activar);
        // NOTA al 20-02-2013: 
        // No activar la L.O.V. del codigo del Transporte
        // por que a la fecha el concepto de Transporte 
        // esta directamente relacionado con el Transportista
        // baja la Premisa de: 
        // 1 Transportista <=> 1 Unidad de Transporte.
        PanelGuiaDespacho.btnLovTransporte.setEnabled(activar);
        //PanelGuiaDespacho.txtNombreTransporte.setEditable(activar);
        PanelGuiaDespacho.cbbTipoTransporte.setEnabled(activar);
        PanelGuiaDespacho.btnUnidadTransporte.setEnabled(activar);
        PanelGuiaDespacho.txtNroEjes.setEditable(activar);
        PanelGuiaDespacho.txtCodCamion.setEditable(activar);
        PanelGuiaDespacho.txtTipoCamion.setEditable(activar);
        PanelGuiaDespacho.txtPlacaBatea.setEditable(activar);
        PanelGuiaDespacho.txtPlacaChuto.setEditable(activar);
        PanelGuiaDespacho.btnLovFleteDestino.setEnabled(activar);
        PanelGuiaDespacho.txtCodDestino.setEditable(activar);  // <= posee L.O.V. 
        PanelGuiaDespacho.txtCodSector.setEditable(activar);  
        //PanelGuiaDespacho.txtDestino.setEditable(activar);
        //PanelGuiaDespacho.txtEstado.setEditable(activar);
        PanelGuiaDespacho.chbGuiaReparto.setEnabled(activar);
        PanelGuiaDespacho.txtGuiaOrigenReparto.setEditable(activar);
    }
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private static void activarCamposDetalleProductos(Boolean activar) {
        PanelGuiaDespacho.txtDisplayNroGuia.setEditable(false);
        PanelGuiaDespacho.txtDisplayFechaGuia.setEditable(false);
        PanelGuiaDespacho.txtNroFactura.setEditable(false);
        PanelGuiaDespacho.txtFechaFactura.setEditable(false);
        PanelGuiaDespacho.tablaDetalleGuia.setEnabled(activar);
        //PanelGuiaDespacho.txtTotalPesoGuia.setEnabled(false);
        PanelGuiaDespacho.btnAgregarRegDetalle.setEnabled(activar);
        PanelGuiaDespacho.btnRefrescarTabla.setEnabled(activar);
        PanelGuiaDespacho.btnZoomAgregarDetalleGuia.setEnabled(activar);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private static void activarCamposPesoRomana(Boolean activar) {
        PanelGuiaDespacho.txtPesoTara.setEditable(activar);
        PanelGuiaDespacho.txtPesoBruto.setEditable(activar);
        PanelGuiaDespacho.txtSerialTicket1.setEditable(activar);
        PanelGuiaDespacho.txtSerialTicket2.setEditable(activar);
        PanelGuiaDespacho.txtDisplayPesoNominal.setEditable(false);
        PanelGuiaDespacho.txtPesoNeto.setEditable(false);
    } // activarCamposPesoRomana().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static void activarCamposBusqueda(Boolean activar) {
        PanelGuiaDespacho.txtNroControlFiscal.setEditable(activar);
        PanelGuiaDespacho.txtNroGuia.setEditable(activar);
        PanelGuiaDespacho.txtFechaGuia.setEnabled(activar);
    }
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private static void iniciarFechaGuia() {
        String patronFechaContable = "dd-MM-yyyy";
        SimpleDateFormat formatoFechaContable = new SimpleDateFormat(patronFechaContable);  
        ServicioAdministracionContabaf servicioAdministracionContabaf = new ServicioAdministracionContabaf(); 
        Contabaf datosContable = servicioAdministracionContabaf.getDatosContable("001");  
        String strFechaInicialPerContable = datosContable.getFECHA_INICIAL();  
        try { 
              Date fechaInicialPerContable = formatoFechaContable.parse(strFechaInicialPerContable);  
              PanelGuiaDespacho.txtFechaGuia.setMinSelectableDate(fechaInicialPerContable);
        } catch (ParseException ex) {
            Logger.getLogger(ControladorGuiaDespacho.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  // iniciarFechaGuia.  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private static void iniciarCbbVendedores() {
        PanelGuiaDespacho.cbbVendedor.removeAllItems();
        ServicioAdministracionVend01 servicioAdministracionVend01 = new ServicioAdministracionVend01();
        for (String nombreVendedor : servicioAdministracionVend01.getVendedores()) {
            PanelGuiaDespacho.cbbVendedor.addItem(nombreVendedor);
        }  // for. 
    }  // iniciarCbbVendedores(): 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private static void iniciarCbbZonaVentas() {
        PanelGuiaDespacho.cbbZonaVenta.removeAllItems();
        ServicioAdministracionVend01 servicioAdministracionVend01 = new ServicioAdministracionVend01();
        for (String zona : servicioAdministracionVend01.getZonaVentas()) {
            PanelGuiaDespacho.cbbZonaVenta.addItem(zona);
        }  // for.- 
    }  // iniciarCbbZonaVentas.   

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static void iniciarCampos() {
        Double factorAlicuota = 16.00;  
        String patronDecimal = "000000000";
        DecimalFormat formatoNCF = new DecimalFormat(patronDecimal);
        PanelGuiaDespacho.txtNroGuia.setText(ServicioAdministracionGuia01.getNextGuia());
        long nextNCF = ServicioAdministracionGuia01.getNextNroCtrlFiscal();
        PanelGuiaDespacho.txtNroControlFiscal.setText(formatoNCF.format(nextNCF));
        iniciarFechaGuia();  
        PanelGuiaDespacho.txtFechaGuia.setDate(new Date());
        PanelGuiaDespacho.txtCodCliente.setBackground(Color.white);
        PanelGuiaDespacho.chbClienteValido.setSelected(true);
        PanelGuiaDespacho.txtAlicuota.setValue(factorAlicuota);
        iniciarCbbVendedores();
        iniciarCbbZonaVentas();
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private static void limpiarCamposPostearGuia() {
        txtNroControlFiscal.setText("");
        txtNroGuia.setText("");
        txtFechaGuia.setDate(new Date());
        chbVentaContado.setSelected(false);
        txtCodCliente.setText("");
        txtRazonSocial.setText("");
        txtCedula.setText("");
        txtRazonSocialPerNatural.setText("");
        txtNit.setText("");
        txtDireccionFiscal.setText("");
        cbbFormaPago.setSelectedItem("CONTADO");   // C)ontador / cR)edito.  
        chbRetiradoPlanta.setSelected(false);
        txtDireccionEntrega.setText("");
        txtOrdenCompra.setText("");
        spnPlazoDias.setValue(0);
        txtOrden1.setText("");
        txtOrden2.setText("");
        txtPedido1.setText("");
        txtPedido2.setText("");
        txtPedido3.setText("");
        txtPedido4.setText("");
        txtAlicuota.setValue(0);
        cbbVendedor.setSelectedItem("");
        txtCodVendedor.setText("");
        cbbZonaVenta.setSelectedItem("");
        txtObservacion.setText("");
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private static void limpiarCamposDespachoPerNatural() {
        txtCedula.setText("");
        txtRazonSocialPerNatural.setText("");
        txtNit.setText("");
        txtDireccionFiscal.setText("");
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private static void limpiarCamposTransporteDestino() {
        txtCedulaTransportista.setText("");
        txtNombreTransportista.setText("");
        txtCodTransporte.setText("");
        txtNombreTransporte.setText("");
        //DefaultComboBoxModel cbbModelTipoTransp = new DefaultComboBoxModel();
        // cbbModelTipoTransp.removeAllElements();
        // String tipoTransporte= clasificarTipoTransporte(guia01.getC1_TIPO_TRANSPORTE().charAt(0)); 
        // cbbModelTipoTransp.addElement(tipoTransporte);
        //cbbTipoTransporte.setModel(cbbModelTipoTransp);
        cbbTipoTransporte.setSelectedItem("");
        txtCodCamion.setText("");
        txtTipoCamion.setText("");
        txtNroEjes.setText("");
        txtPlacaBatea.setText("");
        txtPlacaChuto.setText("");
        //----------------------------------------------------------------------
        // * Destino: *  
        //----------------------------------------------------------------------
        txtCodDestino.setText("");
        txtCodSector.setText("");
        txtDestino.setText("");
        txtEstado.setText("");
        txtFechaVigente.setText("");
        chbGuiaReparto.setSelected(false);
        txtGuiaOrigenReparto.setText("");
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private static void limpiarCamposPesoRomana() {
        txtPesoTara.setValue(0);
        txtPesoBruto.setValue(0);
        txtPesoNeto.setValue(0);
        PanelGuiaDespacho.txtDisplayPesoNominal.setValue(0.00);
        txtSerialTicket1.setText("");
        txtSerialTicket2.setText("");
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private static void limpiarCamposDetalleGuia() {
        txtDisplayNroGuia.setText("");
        txtDisplayFechaGuia.setText("");
        txtNroFactura.setText("");
        txtFechaFactura.setText("");
        // --------------------------------------------------------------------
        // *1er intento al 30/07/2013*:  
        //ModeloTablaGuia02 modeloTablaGuia02 = (ModeloTablaGuia02) PanelGuiaDespacho.tablaDetalleGuia.getModel();   
        //modeloTablaGuia02.limpiarLista();    // Funciona con errores.  
        //PanelGuiaDespacho.tablaDetalleGuia.setModel(new ModeloTablaGuia02() {} );  // NOTA al 31/07/2013: esta instruccion esta generando un Error ????.  
        // ---------------------------------------------------------------------
        // *2do intento al 31/07/2013*:  
        //ModeloTablaGuia02 clearModeloTablaGuia02 = new ModeloTablaGuia02() {};  
        //clearModeloTablaGuia02.fireTableDataChanged();
        //PanelGuiaDespacho.tablaDetalleGuia.setModel( clearModeloTablaGuia02 ); 
        // ---------------------------------------------------------------------
        // *3er intento al 31/07/2013*: 
        //PanelGuiaDespacho.tablaDetalleGuia.setModel(new DefaultTableModel() );     // Solo funciona con esta trampola, porque ???.       
        // ---------------------------------------------------------------------
        // *4to Intento al 31/07/2013*:
        //String[] columnNames = {"1", "2", "3","4","5","6","7","8","9","10","11","12","13","14"};
        //Object[][] datos = {};
        //PanelGuiaDespacho.tablaDetalleGuia.setModel(new DefaultTableModel(datos,columnNames) ); 
        //DefaultTableModel clearTablaModeloGuia02 = new DefaultTableModel(datos,columnNames); 
        //PanelGuiaDespacho.tablaDetalleGuia.setModel( clearTablaModeloGuia02 );  
        //
        //
        //PanelGuiaDespacho.tablaDetalleGuia.setModel(new DefaultTableModel());       // << Solo funciona con esta trampola, porque ???. 
        //PanelGuiaDespacho.tablaDetalleGuia.setModel(new ModeloTablaGuia02() {} );     // << Error ¿????.  
        //-------------------------------
        /*
         * ModeloTablaGuia02 m = (ModeloTablaGuia02) PanelGuiaDespacho.tablaDetalleGuia.getModel();
        m.listaDetalleProductosGuia.clear();
        m.fireTableDataChanged();
        PanelGuiaDespacho.tablaDetalleGuia.setModel(m);
        */  
        PanelGuiaDespacho.txtDisplayTotalPesoGuia.setValue(0.00);
        PanelGuiaDespacho.txtDisplayTotalMonto.setValue(0.00);
        ControladorDetalleGuia controladorDetalleGuia = new ControladorDetalleGuia(); 
        controladorDetalleGuia.iniciarTablaDetalleGuia();   // Error ?????.  Superado el día 04 Oct. 2013. ver NOTA (*1). 
    }   // limpiarCamposDetalleGuia().  
    // NOTA (*1):  Se ajusto el metodo getColumnClass del bean /modelo/ModeloTablaGuia02.  
    
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static void limpiarCampos() {
        limpiarCamposPostearGuia();
        limpiarCamposDespachoPerNatural();
        limpiarCamposTransporteDestino();
        limpiarCamposPesoRomana();
        limpiarCamposBusqueda();
        limpiarCamposDetalleGuia();
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static void limpiarCamposBusqueda() {
        PanelGuiaDespacho.txtNroControlFiscal.setValue(null);
        PanelGuiaDespacho.txtNroGuia.setValue(null);
        //PanelGuiaDespacho.txtFechaGuia.setDate(new Date());  // new Date means Fecha del Sistema.  
        PanelGuiaDespacho.txtFechaGuia.setDate(null);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static void cancelarBusqueda() {
        activarCamposBusqueda(false);
        setBotoneraBusqueda(false);
    }

    //---------------------------------------------------------------------------
    //---------------------------------------------------------------------------
    public static void setBotoneraBusqueda(Boolean activar) {
        PanelGuiaDespacho.btnBuscar.setEnabled(!activar);
        PanelGuiaDespacho.btnEjecutarBuscar.setEnabled(activar);
        PanelGuiaDespacho.btnCancelarBusqueda.setEnabled(activar);
        PanelGuiaDespacho.cbbOperadorBusquedaFecha.setEnabled(activar);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static void setVisibleBotoneraBusqueda(Boolean activar) {
        //PanelGuiaDespacho.btnBuscar.setVisible(!PanelGuiaDespacho.btnBuscar.isVisible());
        PanelGuiaDespacho.btnBuscar.setVisible(activar);
        //PanelGuiaDespacho.btnEjecutarBuscar.setVisible(!PanelGuiaDespacho.btnEjecutarBuscar.isVisible());
        PanelGuiaDespacho.btnEjecutarBuscar.setVisible(activar);
        //PanelGuiaDespacho.btnCancelarBusqueda.setVisible(!PanelGuiaDespacho.btnCancelarBusqueda.isVisible());
        PanelGuiaDespacho.btnCancelarBusqueda.setVisible(activar);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private String clasificarTipoTransporte(char tipoTransporte) {
        String transporte;
        switch (tipoTransporte) {
            case 'C':
                transporte = "CAMION";
                break;
            case 'T':
                transporte = "TORONTO";
                break;
            case 'G':
                transporte = "GANDOLA";
                break;
            default:
                transporte = "ERROR";
        }  // switch
        return (transporte);
    }  // clasificarTipoTransporte(). 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void showDatosPostearGuiaDespacho(Guia01 guia01) {
        Integer valor = Integer.parseInt((guia01.getC1_NCF() != null ? guia01.getC1_NCF() : "0"));  // equivalente a la funcion NVL de Oracle PL/SQL.     
        //txtNroControlFiscal.setValue(Integer.parseInt((guia01.getC1_NCF()!=null)?guia01.getC1_NCF():"0"));
        txtNroControlFiscal.setValue(valor);
        txtNroGuia.setValue(Integer.parseInt(guia01.getC1_GUIA()));
        txtFechaGuia.setDate(guia01.getC1_FECHA_GUIA());
        txtCodCliente.setValue(guia01.getC1_CODIGO_CLIENTE());
        if (guia01.getC1_CLIENTE_ESPECIAL() != null) {  // Venta al Contado.  
            chbVentaContado.setSelected(true);
        } else {
            chbVentaContado.setSelected(false);
        }
        txtRazonSocial.setText(guia01.getNOMBRE_CLIENTE());
        // **   EJEMPLO modelo:  
        //DefaultComboBoxModel cbbModelFormaPago = new DefaultComboBoxModel(); 
        //cbbModelFormaPago.removeAllElements(); 
        //cbbModelFormaPago.addElement(guia01.getC1_FORMA_PAGO().equals("C") ?"CONTADO":"CREDITO");
        //cbbFormaPago.setModel(cbbModelFormaPago);
        cbbFormaPago.setSelectedItem(guia01.getC1_FORMA_PAGO().equals("C") ? "CONTADO" : "CREDITO");   // C)ontador / cR)edito.  
        // **
        if (guia01.getC1_RETIRADO_PLANTA() == null) {
            chbRetiradoPlanta.setSelected(false);
        } else {
            chbRetiradoPlanta.setSelected(true);
        }
        String cadena = guia01.getC1_ENTREGA1() != null ? guia01.getC1_ENTREGA1() : " ";
        cadena = cadena + (guia01.getC1_ENTREGA2() != null ? guia01.getC1_ENTREGA2() : " ");
        cadena = cadena + (guia01.getC1_ENTREGA3() != null ? guia01.getC1_ENTREGA3() : " ");
        cadena = cadena + (guia01.getC1_ENTREGA4() != null ? guia01.getC1_ENTREGA4() : "");
        txtDireccionEntrega.setText(cadena);
        txtOrdenCompra.setText(guia01.getC1_ORDEN_COMPRA() != null ? guia01.getC1_ORDEN_COMPRA() : "");
        valor = guia01.getC1_PLAZO();
        spnPlazoDias.setValue(valor);
        txtOrden1.setText(guia01.getC1_ORDEN1());
        txtOrden2.setText(guia01.getC1_ORDEN2());
        txtPedido1.setText(guia01.getC1_PEDIDO1());
        txtPedido2.setText(guia01.getC1_PEDIDO2());
        txtPedido3.setText(guia01.getC1_PEDIDO3());
        txtPedido4.setText(guia01.getC1_PEDIDO4());
        //txtAlicuota.setValue(guia01.getC1_ALICUOTA()!=null?guia01.getC1_ALICUOTA():"");
        txtAlicuota.setValue(guia01.getC1_ALICUOTA());
        // *Vendedor* 
        //DefaultComboBoxModel cbbModelVendedor = new DefaultComboBoxModel(); 
        //  cbbModelVendedor.removeAllElements();
        //  cbbModelVendedor.addElement(guia01.getC1_VENDEDOR());
        //cbbVendedor.setModel(cbbModelVendedor);
        iniciarCbbVendedores();
        String vendedor = guia01.getC1_NOMBRE_VENDEDOR() + " " + guia01.getC1_VENDEDOR();
        cbbVendedor.setSelectedItem(vendedor);
        //txtCodVendedor.setText(guia01.getC1_VENDEDOR());
        // *Zona de Venta*  
        //DefaultComboBoxModel cbbModelZonaVenta = new DefaultComboBoxModel(); 
        //  cbbModelZonaVenta.removeAllElements();
        //  cbbModelZonaVenta.addElement(guia01.getC1_ZONA()); 
        //cbbZonaVenta.setModel(cbbModelZonaVenta);
        iniciarCbbZonaVentas();
        cbbZonaVenta.setSelectedItem(guia01.getC1_ZONA());
        txtObservacion.setText(guia01.getC1_OBSERVACION());
    }   // showDatosPosterGuiaDespacho().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void showDatosDespachoPerNatural(Guia01 guia01) {
        txtCedula.setText(guia01.getC1_RIF());
        txtRazonSocialPerNatural.setText(guia01.getC1_RAZON_SOCIAL());
        txtNit.setText(guia01.getC1_NIT());
        String dirFiscalPerNatural = ( guia01.getC1_DIR_FISCAL1() == null ? "" : guia01.getC1_DIR_FISCAL1() );
        dirFiscalPerNatural = dirFiscalPerNatural + ( guia01.getC1_DIR_FISCAL2() == null ? "" : guia01.getC1_DIR_FISCAL2() );  
        dirFiscalPerNatural = dirFiscalPerNatural + ( guia01.getC1_DIR_FISCAL3() == null ? "" : guia01.getC1_DIR_FISCAL3() ); 
        //String dirFiscalPerNatural = guia01.getC1_DIR_FISCAL1()+guia01.getC1_DIR_FISCAL2()+guia01.getC1_DIR_FISCAL3();  
        txtDireccionFiscal.setText(dirFiscalPerNatural);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void showDatosTranspoteDestino(Guia01 guia01) {
        txtCedulaTransportista.setText(guia01.getC1_CI_CHOFER());
        txtNombreTransportista.setText(guia01.getC1_NOMBRE_CHOFER());
        txtCodTransporte.setText(guia01.getC1_COD_TRANSP());
        txtNombreTransporte.setText(guia01.getC1_NOMBRE_TRANSP());
        //DefaultComboBoxModel cbbModelTipoTransp = new DefaultComboBoxModel();
        // cbbModelTipoTransp.removeAllElements();
        // String tipoTransporte= clasificarTipoTransporte(guia01.getC1_TIPO_TRANSPORTE().charAt(0)); 
        // cbbModelTipoTransp.addElement(tipoTransporte);
        //cbbTipoTransporte.setModel(cbbModelTipoTransp);
        cbbTipoTransporte.setSelectedItem(guia01.getC1_TIPO_TRANSPORTE().charAt(0));
        txtCodCamion.setText(guia01.getC1_COD_CAMION());
        txtTipoCamion.setText(guia01.getC1_TIPO_CAMION());
        txtNroEjes.setText(guia01.getC1_NO_EJES());
        txtPlacaBatea.setText(guia01.getC1_PLACA_BATEA());
        txtPlacaChuto.setText(guia01.getC1_PLACA_CHUTO());
        //----------------------------------------------------------------------
        // * Destino: *  
        //----------------------------------------------------------------------
        txtCodDestino.setText(guia01.getC1_COD_DESTINO());
        txtCodSector.setText(guia01.getC1_COD_SECTOR());
        txtDestino.setText(guia01.getC1_NOMBRE_DESTINO());
        txtEstado.setText(guia01.getC1_ESTADO());
        txtFechaVigente.setText(guia01.getC1_FECHA_RELACION() != null ? Estilos.formatoFecha.format(guia01.getC1_FECHA_RELACION()) : "");
        Boolean marcar;
        if (guia01.getC1_GUIA_REPARTO() != null && guia01.getC1_GUIA_REPARTO().charAt(0) != ' ') {
            marcar = true;
        } else {
            marcar = false;
        }
        chbGuiaReparto.setSelected(marcar);
        txtGuiaOrigenReparto.setText(guia01.getC1_GUIA_ORIGEN_REPARTO());
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void showDatosPesoRomana(Guia01 guia01) {
        Double pesoTara = guia01.getC1_PESO_TARA(),
                pesoBruto = guia01.getC1_PESO_BRUTO(),
                pesoNeto = pesoBruto - pesoTara;
        txtPesoTara.setValue(pesoTara);
        txtPesoBruto.setValue(pesoBruto);
        txtPesoNeto.setValue(pesoNeto);
        txtSerialTicket1.setText(guia01.getC1_SERIAL_TICKET1());
        txtSerialTicket2.setText(guia01.getC1_SERIAL_TICKET2());
    }

    //--------------------------------------------------------------------------
    //   DETTALLE GUIA:  
    //--------------------------------------------------------------------------
    private void showDetalleGuia(Guia01 guia01) {
        txtDisplayNroGuia.setText(guia01.getC1_GUIA());
        if (guia01.getC1_FECHA_GUIA() != null) {
            txtDisplayFechaGuia.setText(Estilos.formatoFecha.format(guia01.getC1_FECHA_GUIA()));
        }
        txtNroFactura.setText(guia01.getC1_FACTURA());
        txtFechaFactura.setText(guia01.getC1_FECHA_FACTURA() == null ? "" : Estilos.formatoFecha.format(guia01.getC1_FECHA_FACTURA()));
        ControladorDetalleGuia controladorDetalleGuia = new ControladorDetalleGuia();
        controladorDetalleGuia.iniciarTablaDetalleGuia();
        /*   ... trabajando con Jlist:  (example): 
         DefaultListModel listModel = new DefaultListModel();  
         if (guia01.getC1_ORDEN1()!=null ) listModel.addElement(guia01.getC1_ORDEN1()); 
         if (guia01.getC1_ORDEN2()!=null)  listModel.addElement(guia01.getC1_ORDEN2()); 
         lsbOrdenFab.setModel(listModel);
         */
    }  // showDetalleGuia(). 

    //--------------------------------------------------------------------------
    // Presentar los datos de la tabla Maestra de la Guia de Despacho. 
    //--------------------------------------------------------------------------
    private void showDatosMaestroGuia(Guia01 guia01) {
        showDatosPostearGuiaDespacho(guia01);
        showDatosDespachoPerNatural(guia01);
        showDatosTranspoteDestino(guia01);
        showDatosPesoRomana(guia01);
    }   // showDatosMaestroGuia(). 

    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void ejecutarBusquedaGuia() {
        String ncf = PanelGuiaDespacho.txtNroControlFiscal.getText();
        String nroGuia = PanelGuiaDespacho.txtNroGuia.getText();
        java.sql.Date fechaGuia;
        final String clienteAnulado = "C-9999";
        if (PanelGuiaDespacho.txtFechaGuia.getDate() != null) {
            //fechaGuia =  (java.sql.Date) PanelGuiaDespacho.txtFechaGuia.getDate();  // Error al realizar la conversion si este valor fuese nulo. 
            //fechaGuia = new java.sql.Date(fechaGuia.getTime());
            fechaGuia = new java.sql.Date(PanelGuiaDespacho.txtFechaGuia.getDate().getTime());
        } else {
            fechaGuia = null;
        }
        if (ncf.isEmpty() && nroGuia.isEmpty() && fechaGuia == null) {
            JOptionPane.showMessageDialog(this, "Debes rellenar los campos claves para realizar tu busqueda.", "ATENCION:", JOptionPane.ERROR_MESSAGE);
        } else if (servicioAdministracionGuia01.guiaRegistrada(ncf, nroGuia, fechaGuia)) {
            setIndicadorModoOperacion("consultar");
            Guia01 datosMaestroGuia = servicioAdministracionGuia01.getMaestroGuia(ncf, nroGuia, fechaGuia);
            showDatosMaestroGuia(datosMaestroGuia);
            PanelGuiaDespacho.txtDisplayGuiaProcesada.setText(nroGuia);
            if (!datosMaestroGuia.getC1_CODIGO_CLIENTE().equals(clienteAnulado)) {
                ServicioAdministracionGuia02 servicioAdministracionGuia02 = new ServicioAdministracionGuia02();
                if (servicioAdministracionGuia02.existeRegDetalleGuia(nroGuia)) {
                    showDetalleGuia(datosMaestroGuia);
                    //ControladorAgregarDetalleGuia controladorAgregarDetalleGuia = new ControladorAgregarDetalleGuia();  
                    //controladorAgregarDetalleGuia.iniciarTablaDetalleGuia();
                } else {
                    System.out.println("ERROR(101): Guia No Anulado con Inconsistencia de Informacion (Detalle).");
                    //System.exit(101);  // V 1 = Indice Pro; 01 = secuencia. 
                }  // if-else interno.  
            }
        } // if servicio,...
        else {
            PanelGuiaDespacho.txtNroGuia.setBackground(Color.YELLOW);
            //System.out.println("*Cod Guia NO encontrada / revisar por inconsistencia de Informacion...*");
            JOptionPane.showMessageDialog(this, "Guia NO registrada. Intente nuevamente.", "ATENCION:", JOptionPane.WARNING_MESSAGE);
            PanelGuiaDespacho.txtNroGuia.setBackground(Color.WHITE);            
        }  // if-else.
        cancelarBusqueda();
    }  // ejecutarBusqueda de la Guia.  
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private Boolean validarDatosCliente() {
        Boolean valido = Boolean.FALSE;
        ServicioAdministracionCliente servicioAdministracionCliente = new ServicioAdministracionCliente();
        String codCliente = PanelGuiaDespacho.txtCodCliente.getText().toUpperCase();
        if ( codCliente!=null || !codCliente.isEmpty() ) {
            if (servicioAdministracionCliente.clienteRegistrado(codCliente)) {
                valido = Boolean.TRUE;
                String nombreCliente = servicioAdministracionCliente.getCliente(codCliente).getNombreCliente();
                PanelGuiaDespacho.txtRazonSocial.setText(nombreCliente);
                PanelGuiaDespacho.txtCodCliente.setText(codCliente);
                PanelGuiaDespacho.chbClienteValido.setSelected(true);
            }  // if interno.   
        } else {
            valido = Boolean.TRUE;
        }
        if (!valido) {
            PanelGuiaDespacho.chbClienteValido.setSelected(false);  // <! Este es un control centinela; de lo contrario el Programador debera Validar la Entrada al momento de actualizar los datos en B.D. 
            PanelGuiaDespacho.txtCodCliente.setBackground(Color.YELLOW);
            JOptionPane.showMessageDialog(this, "Codigo del Cliente NO registrado.", "ATENCION:", JOptionPane.ERROR_MESSAGE);
            PanelGuiaDespacho.txtCodCliente.setBackground(Color.WHITE);
        }
        return (valido);
    }  // validarDatosCliente(). 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void validarDirEntrega() {
        if ( ( PanelGuiaDespacho.txtDireccionEntrega.getText()==null || PanelGuiaDespacho.txtDireccionEntrega.getText().isEmpty() ) && PanelGuiaDespacho.chbRetiradoPlanta.isSelected() ) {
            String texto = " [* RETIRADO EN PLANTA *]"; 
            PanelGuiaDespacho.txtDireccionEntrega.setText(texto); 
        }
    }  // validarDirEntrega().  
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private Boolean validarTransportista() {
        Boolean valido = Boolean.FALSE;
        String cedula = PanelGuiaDespacho.txtCedulaTransportista.getText();
        if (cedula == null || cedula.isEmpty()) {
            valido = Boolean.TRUE;
        } else {
            ServicioAdministracionGuia03 servicioAdministracionGuia03 = new ServicioAdministracionGuia03();
            if (servicioAdministracionGuia03.existeTransportista(cedula)) {
                valido = Boolean.TRUE;
                Guia03 datosTransportista = servicioAdministracionGuia03.getDatosTransportista(cedula);
                PanelGuiaDespacho.txtNombreTransportista.setText(datosTransportista.getC3_NOMBRE_CHOFER());
                ServicioAdministracionGuia05 servicioAdministracionGuia05 = new ServicioAdministracionGuia05();
                Guia05 transporte = servicioAdministracionGuia05.getDatosTransporte(datosTransportista.getC3_COD_TRANSP());
                PanelGuiaDespacho.txtCodTransporte.setText(transporte.getC5_COD_TRANSPORTE());
                PanelGuiaDespacho.txtNombreTransporte.setText(transporte.getC5_NOMBRE_TRANSP());
            } else {  // cedula Transportista NO registrada.  
                PanelGuiaDespacho.txtCedulaTransportista.setBackground(Color.YELLOW);
                JOptionPane.showMessageDialog(this, "TRANSPORTISTA NO registrado", "ATENCION", JOptionPane.ERROR_MESSAGE);
                PanelGuiaDespacho.txtCedulaTransportista.setBackground(Color.WHITE);
            }  // if interno. 
        }
        return (valido);
    }  // validarTransportista().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private Boolean checkValoresPesoRomana() {
        Boolean valido = Boolean.FALSE; 
        Number p1 = (Number) PanelGuiaDespacho.txtPesoTara.getValue();
        Number p2 = (Number) PanelGuiaDespacho.txtPesoBruto.getValue();
        if ( p2.doubleValue() >= p1.doubleValue() ) valido = Boolean.TRUE;  
        return (valido); 
    }
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void calcularPesoRomana() {
        Number p1 = (Number) PanelGuiaDespacho.txtPesoTara.getValue();
        Number p2 = (Number) PanelGuiaDespacho.txtPesoBruto.getValue();
        PanelGuiaDespacho.txtPesoNeto.setValue( p2.floatValue() - p1.floatValue() );
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private Boolean validarOrdenFab() {
        Boolean valido = Boolean.FALSE;
        ServicioAdministracionInv04 servicioAdministracionInv04 = new ServicioAdministracionInv04();
        String ordenFab = PanelGuiaDespacho.txtOrden1.getText();
        if ((ordenFab != null || !ordenFab.isEmpty()) && !servicioAdministracionInv04.ordenFabRegistrada(ordenFab)) {
            PanelGuiaDespacho.txtOrden1.setBackground(Color.YELLOW);
            JOptionPane.showMessageDialog(this, "Orden de Fab No registrada.", "ATENCION:", JOptionPane.ERROR_MESSAGE);
            PanelGuiaDespacho.txtOrden1.setBackground(Color.WHITE);
        } else {
            valido = Boolean.TRUE;
        }
        return (valido);
    }  // validarOrdenFab(). 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private Boolean validarPedido() {
        Boolean valido = Boolean.FALSE;
        ServicioAdministracionVend03 servicioAdministracionVend03 = new ServicioAdministracionVend03();
        String pedido = PanelGuiaDespacho.txtPedido1.getText();
        if (pedido != null || !pedido.isEmpty()) {
            if (!servicioAdministracionVend03.existePedido(pedido)) {
                PanelGuiaDespacho.txtPedido1.setBackground(Color.YELLOW);
                JOptionPane.showMessageDialog(this, "Pedido NO registrado.", "ATENCION", JOptionPane.ERROR_MESSAGE);
                PanelGuiaDespacho.txtPedido1.setBackground(Color.WHITE);
            } else {
                valido = Boolean.TRUE;
                Vend03 datosPedido = servicioAdministracionVend03.getPedido(pedido);
                ServicioAdministracionVend01 servicioAdministracionVend01 = new ServicioAdministracionVend01();
                Vend01 datosVendedor = servicioAdministracionVend01.getVendedor(datosPedido.getC3_COD_VENDEDOR());
                String vendedor = datosVendedor.getC1_NOMBRE_VEND() + " " + datosVendedor.getC1_COD_VENDEDOR();   // prepara datos para el cbb Vendedor.  
                PanelGuiaDespacho.cbbVendedor.setSelectedItem(vendedor);  // Esto hace que se dispare el envent "acionPerformed" del cbb y c actualize el campo cod Vendedor.  
                PanelGuiaDespacho.cbbZonaVenta.setSelectedItem(datosVendedor.getC1_ZONA());
            }  // else interno.  
        } // if. 
        else {
            valido = Boolean.TRUE;
        } // else externo.  
        return (valido);
    }  // validarPedido().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private Boolean validarGuiaReparto() {
        Boolean valido = Boolean.FALSE;
        String guiaReparto = PanelGuiaDespacho.txtGuiaOrigenReparto.getText();
        if (PanelGuiaDespacho.chbGuiaReparto.isSelected()) {
            if (guiaReparto == null || guiaReparto.isEmpty()) {
                PanelGuiaDespacho.txtGuiaOrigenReparto.setBackground(Color.YELLOW);
                JOptionPane.showMessageDialog(this, "Este campo NO puede ser nulo.", "ATENCION", JOptionPane.ERROR_MESSAGE);
                PanelGuiaDespacho.txtGuiaOrigenReparto.setBackground(Color.WHITE);
            } else {
                if (!servicioAdministracionGuia01.guiaExiste(guiaReparto)) {
                    PanelGuiaDespacho.txtGuiaOrigenReparto.setBackground(Color.YELLOW);
                    JOptionPane.showMessageDialog(this, "Guia Original de REPARTO NO REGISTRADA.", "ATENCION", JOptionPane.ERROR_MESSAGE);
                    PanelGuiaDespacho.txtGuiaOrigenReparto.setBackground(Color.WHITE);
                } else {
                    valido = Boolean.TRUE;
                }
            } // if interno.  
        } else {
            PanelGuiaDespacho.txtGuiaOrigenReparto.setText(null);
            valido = Boolean.TRUE;
        }  // if externo.  
        return (valido);
    }  // validarGuiaReparto().  
    
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void agregarRegDetalle() {
       /*   ELIMINAME: 
       final int X = 420,
                 Y = 80, 
             sizeX = 600,
             sizeY = 500; 
       JDialog dlgAddRegDetalle = new JDialog();
       PanelAgregarDetalleGuia panelAddItem = new PanelAgregarDetalleGuia(); 
       //--------------------------------------------------------------------
       panelAddItem.limpiarCampos();
       panelAddItem.setCamposAgregarItemsGuia(true);
       //--------------------------------------------------------------------
       dlgAddRegDetalle.setContentPane(panelAddItem);
       dlgAddRegDetalle.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
       dlgAddRegDetalle.setModal(Boolean.TRUE);
       dlgAddRegDetalle.setSize(sizeX,sizeY);
       dlgAddRegDetalle.setLocation(X, Y);
       dlgAddRegDetalle.setVisible(Boolean.TRUE);
       ELIMINAME     */
       ControladorDetalleGuia ctldrDetalleGuia = new ControladorDetalleGuia();
       ctldrDetalleGuia.ejecutarDialogAddDetalleGuia();
    }  // agregarRegDetalle()

    
    /**
     * *************************************************************************
     * -------------------------------------------------------------------------
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     * -------------------------------------------------------------------------
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPaneGuiaDespacho = new javax.swing.JTabbedPane();
        javax.swing.JPanel jPanelPostearGuiaDesp = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        txtNroGuia = new javax.swing.JFormattedTextField();
        txtFechaGuia = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnCancelarBusqueda = new javax.swing.JButton();
        btnEjecutarBuscar = new javax.swing.JButton();
        cbbOperadorBusquedaFecha = new javax.swing.JComboBox();
        txtDisplayGuiaProcesada = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNroControlFiscal = new javax.swing.JFormattedTextField();
        jSeparator2 = new javax.swing.JSeparator();
        chbVentaContado = new javax.swing.JCheckBox();
        cbbFormaPago = new javax.swing.JComboBox();
        spnPlazoDias = new javax.swing.JSpinner();
        chbRetiradoPlanta = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        txtCodCliente = new javax.swing.JFormattedTextField();
        chbClienteValido = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtRazonSocial = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDireccionEntrega = new javax.swing.JEditorPane();
        jLabel7 = new javax.swing.JLabel();
        txtOrdenCompra = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txtAlicuota = new javax.swing.JFormattedTextField();
        cbbVendedor = new javax.swing.JComboBox();
        cbbZonaVenta = new javax.swing.JComboBox();
        btnLovCliente = new javax.swing.JButton();
        txtOrden1 = new javax.swing.JTextField();
        txtOrden2 = new javax.swing.JTextField();
        txtPedido1 = new javax.swing.JTextField();
        txtPedido2 = new javax.swing.JTextField();
        txtPedido3 = new javax.swing.JTextField();
        txtPedido4 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        txtCodVendedor = new javax.swing.JTextField();
        chbModoConsultar = new javax.swing.JRadioButton();
        chbModoInsertar = new javax.swing.JRadioButton();
        chbModoEditar = new javax.swing.JRadioButton();
        jLayeredPane10 = new javax.swing.JLayeredPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtObservacion = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        jPanelVentaPersonaNatural = new javax.swing.JPanel();
        jLayeredPane6 = new javax.swing.JLayeredPane();
        jLabel23 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JFormattedTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtRazonSocialPerNatural = new javax.swing.JTextArea();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtNit = new javax.swing.JFormattedTextField();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtDireccionFiscal = new javax.swing.JEditorPane();
        jPanelDatosTransporte = new javax.swing.JPanel();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtCedulaTransportista = new javax.swing.JFormattedTextField();
        btnLovTransportista = new javax.swing.JButton();
        txtNombreTransportista = new javax.swing.JTextField();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jLabel31 = new javax.swing.JLabel();
        txtCodTransporte = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        btnLovTransporte = new javax.swing.JButton();
        txtNombreTransporte = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        cbbTipoTransporte = new javax.swing.JComboBox();
        btnUnidadTransporte = new javax.swing.JButton();
        txtCodCamion = new javax.swing.JTextField();
        txtTipoCamion = new javax.swing.JTextField();
        txtNroEjes = new javax.swing.JTextField();
        txtPlacaChuto = new javax.swing.JTextField();
        txtPlacaBatea = new javax.swing.JTextField();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        jLabel39 = new javax.swing.JLabel();
        txtCodSector = new javax.swing.JTextField();
        btnLovFleteDestino = new javax.swing.JButton();
        txtFechaVigente = new javax.swing.JFormattedTextField();
        jLabel40 = new javax.swing.JLabel();
        txtDestino = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        txtGuiaOrigenReparto = new javax.swing.JTextField();
        chbGuiaReparto = new javax.swing.JCheckBox();
        txtCodDestino = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanelDetalleGuia = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtDisplayNroGuia = new javax.swing.JTextField();
        txtNroFactura = new javax.swing.JTextField();
        txtFechaFactura = new javax.swing.JFormattedTextField();
        jLabel22 = new javax.swing.JLabel();
        txtDisplayFechaGuia = new javax.swing.JFormattedTextField();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLabel21 = new javax.swing.JLabel();
        txtDisplayTotalMonto = new javax.swing.JFormattedTextField();
        jLabel43 = new javax.swing.JLabel();
        txtDisplayTotalPesoGuia = new javax.swing.JFormattedTextField();
        jLayeredPane11 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDetalleGuia = new javax.swing.JTable();
        btnRefrescarTabla = new javax.swing.JButton();
        btnZoomAgregarDetalleGuia = new javax.swing.JButton();
        btnAgregarRegDetalle = new javax.swing.JButton();
        chbActualizarGridProductos = new javax.swing.JCheckBox();
        jPanelPesoRomana = new javax.swing.JPanel();
        jLayeredPane7 = new javax.swing.JLayeredPane();
        jLabel4 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtSerialTicket1 = new javax.swing.JTextField();
        txtSerialTicket2 = new javax.swing.JTextField();
        txtPesoBruto = new javax.swing.JFormattedTextField();
        txtPesoTara = new javax.swing.JFormattedTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txtDisplayPesoNominal = new javax.swing.JFormattedTextField();
        jLayeredPane9 = new javax.swing.JLayeredPane();
        jLabel47 = new javax.swing.JLabel();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        txtDisplayDiferenciaPesoRomana = new javax.swing.JFormattedTextField();
        jLabel52 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        txtPesoNeto = new javax.swing.JFormattedTextField();
        jLabel53 = new javax.swing.JLabel();
        lblModoOperacion = new javax.swing.JLabel();

        jTabbedPaneGuiaDespacho.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N

        jPanelPostearGuiaDesp.setBackground(new java.awt.Color(184, 191, 195));
        jPanelPostearGuiaDesp.setEnabled(false);

        jDesktopPane1.setBackground(java.awt.SystemColor.control);
        jDesktopPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(251, 211, 13), new java.awt.Color(196, 189, 189)));

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel1.setText("No. Guia: ");
        jDesktopPane1.add(jLabel1);
        jLabel1.setBounds(10, 10, 90, 20);

        txtNroGuia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("00000"))));
        txtNroGuia.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        txtNroGuia.setNextFocusableComponent(txtFechaGuia);
        txtNroGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNroGuiaActionPerformed(evt);
            }
        });
        jDesktopPane1.add(txtNroGuia);
        txtNroGuia.setBounds(110, 10, 102, 27);

        txtFechaGuia.setDateFormatString("dd/MM/yyyy");
        txtFechaGuia.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jDesktopPane1.add(txtFechaGuia);
        txtFechaGuia.setBounds(160, 40, 122, 27);

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel2.setText("Fecha guia: ");
        jDesktopPane1.add(jLabel2);
        jLabel2.setBounds(10, 50, 120, 17);

        btnBuscar.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/buscar16px.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setToolTipText("Procesar Buscar.");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jDesktopPane1.add(btnBuscar);
        btnBuscar.setBounds(160, 70, 110, 30);

        btnCancelarBusqueda.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnCancelarBusqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/undo16px.png"))); // NOI18N
        btnCancelarBusqueda.setText("Cancelar");
        btnCancelarBusqueda.setToolTipText("Cancelar Buscar.");
        btnCancelarBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarBusquedaActionPerformed(evt);
            }
        });
        jDesktopPane1.add(btnCancelarBusqueda);
        btnCancelarBusqueda.setBounds(460, 70, 120, 30);

        btnEjecutarBuscar.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnEjecutarBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/carritoEntrega16px.png"))); // NOI18N
        btnEjecutarBuscar.setText("Ejecutar Buscar");
        btnEjecutarBuscar.setToolTipText("Ejecutar Busqueda segun valores del campo clave.");
        btnEjecutarBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarBuscarActionPerformed(evt);
            }
        });
        jDesktopPane1.add(btnEjecutarBuscar);
        btnEjecutarBuscar.setBounds(270, 70, 190, 30);

        cbbOperadorBusquedaFecha.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "=", ">", "<" }));
        jDesktopPane1.add(cbbOperadorBusquedaFecha);
        cbbOperadorBusquedaFecha.setBounds(110, 40, 50, 27);

        txtDisplayGuiaProcesada.setEditable(false);
        jDesktopPane1.add(txtDisplayGuiaProcesada);
        txtDisplayGuiaProcesada.setBounds(500, 40, 100, 27);

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel5.setText("Nro. Control Fiscal: ");
        jDesktopPane1.add(jLabel5);
        jLabel5.setBounds(240, 10, 150, 17);

        txtNroControlFiscal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("000000000"))));
        txtNroControlFiscal.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        txtNroControlFiscal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNroControlFiscalActionPerformed(evt);
            }
        });
        jDesktopPane1.add(txtNroControlFiscal);
        txtNroControlFiscal.setBounds(389, 10, 210, 27);

        jSeparator2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        chbVentaContado.setText("Venta Per. Natural.");

        cbbFormaPago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Contado", "CRedito" }));
        cbbFormaPago.setToolTipText("");
        cbbFormaPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbFormaPagoActionPerformed(evt);
            }
        });

        spnPlazoDias.setModel(new javax.swing.SpinnerNumberModel(0, 0, 30, 1));
        spnPlazoDias.setNextFocusableComponent(chbRetiradoPlanta);
        spnPlazoDias.setOpaque(false);

        chbRetiradoPlanta.setText("Retirado en Planta.");
        chbRetiradoPlanta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbRetiradoPlantaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel6.setText("Direccion Entrega: ");

        txtCodCliente.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        txtCodCliente.setPreferredSize(new java.awt.Dimension(10, 18));
        txtCodCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodClienteActionPerformed(evt);
            }
        });
        txtCodCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodClienteFocusLost(evt);
            }
        });

        chbClienteValido.setText("chbClienteValido");
        chbClienteValido.setEnabled(false);

        txtRazonSocial.setColumns(20);
        txtRazonSocial.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        txtRazonSocial.setLineWrap(true);
        txtRazonSocial.setRows(5);
        txtRazonSocial.setBorder(new javax.swing.border.MatteBorder(null));
        txtRazonSocial.setEnabled(false);
        jScrollPane3.setViewportView(txtRazonSocial);

        txtDireccionEntrega.setBorder(new javax.swing.border.MatteBorder(null));
        txtDireccionEntrega.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        txtDireccionEntrega.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtDireccionEntregaPropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(txtDireccionEntrega);

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel7.setText("Codigo Cliente: ");

        txtOrdenCompra.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N

        jLabel8.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel8.setText("Nombre/Razon Social:");

        jSeparator1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setText("Tipo Pago: ");

        jLabel10.setText("Plazo (dias): ");

        jLabel11.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel11.setText("Orden de Compra: ");

        jLabel12.setText("Orden (es) de Fab: ");

        jLabel13.setText("Pedido (s): ");

        jLabel14.setText("Vendedor: ");

        jLabel15.setText("Zona Venta: ");

        jLabel16.setText("Alicuota (%): ");

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtAlicuota.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtAlicuota.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtAlicuota.setValue(12.00);

        cbbVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbVendedorActionPerformed(evt);
            }
        });

        btnLovCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/buscar16px.png"))); // NOI18N
        btnLovCliente.setText("...");
        btnLovCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLovClienteActionPerformed(evt);
            }
        });

        txtOrden1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOrden1ActionPerformed(evt);
            }
        });

        txtPedido1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPedido1ActionPerformed(evt);
            }
        });

        jLabel45.setText("(*)");

        txtCodVendedor.setEditable(false);
        txtCodVendedor.setOpaque(false);

        chbModoConsultar.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        chbModoConsultar.setText("Modo Consultar");
        chbModoConsultar.setEnabled(false);

        chbModoInsertar.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        chbModoInsertar.setText("Modo Insertar");
        chbModoInsertar.setEnabled(false);

        chbModoEditar.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        chbModoEditar.setText("Modo Editar");
        chbModoEditar.setEnabled(false);

        jLayeredPane10.setBackground(new java.awt.Color(203, 206, 207));
        jLayeredPane10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLayeredPane10.setOpaque(true);

        txtObservacion.setColumns(20);
        txtObservacion.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        txtObservacion.setLineWrap(true);
        txtObservacion.setRows(5);
        jScrollPane4.setViewportView(txtObservacion);

        jLayeredPane10.add(jScrollPane4);
        jScrollPane4.setBounds(10, 30, 429, 40);

        jLabel17.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel17.setText("Observacion: ");
        jLayeredPane10.add(jLabel17);
        jLabel17.setBounds(10, 10, 110, 15);

        javax.swing.GroupLayout jPanelPostearGuiaDespLayout = new javax.swing.GroupLayout(jPanelPostearGuiaDesp);
        jPanelPostearGuiaDesp.setLayout(jPanelPostearGuiaDespLayout);
        jPanelPostearGuiaDespLayout.setHorizontalGroup(
            jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanelPostearGuiaDespLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPostearGuiaDespLayout.createSequentialGroup()
                        .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelPostearGuiaDespLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chbRetiradoPlanta)
                                    .addGroup(jPanelPostearGuiaDespLayout.createSequentialGroup()
                                        .addComponent(chbVentaContado)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel45))))
                            .addGroup(jPanelPostearGuiaDespLayout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addGroup(jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelPostearGuiaDespLayout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbbFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelPostearGuiaDespLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spnPlazoDias, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanelPostearGuiaDespLayout.createSequentialGroup()
                        .addGroup(jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPostearGuiaDespLayout.createSequentialGroup()
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtOrdenCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(160, 160, 160))
                                .addGroup(jPanelPostearGuiaDespLayout.createSequentialGroup()
                                    .addGroup(jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel8)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(25, 25, 25)))
                            .addGroup(jPanelPostearGuiaDespLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLovCliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbClienteValido)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLayeredPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelPostearGuiaDespLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(chbModoConsultar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chbModoInsertar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chbModoEditar))
                            .addGroup(jPanelPostearGuiaDespLayout.createSequentialGroup()
                                .addGroup(jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addGroup(jPanelPostearGuiaDespLayout.createSequentialGroup()
                                        .addGroup(jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtOrden2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanelPostearGuiaDespLayout.createSequentialGroup()
                                                .addComponent(jLabel12)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtOrden1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel13)))
                                .addGap(1, 1, 1)
                                .addGroup(jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanelPostearGuiaDespLayout.createSequentialGroup()
                                        .addComponent(txtPedido2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPedido4))
                                    .addGroup(jPanelPostearGuiaDespLayout.createSequentialGroup()
                                        .addComponent(txtPedido1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPedido3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanelPostearGuiaDespLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addGroup(jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelPostearGuiaDespLayout.createSequentialGroup()
                                        .addComponent(cbbZonaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel16)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtAlicuota, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelPostearGuiaDespLayout.createSequentialGroup()
                                        .addComponent(cbbVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtCodVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelPostearGuiaDespLayout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelPostearGuiaDespLayout.setVerticalGroup(
            jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPostearGuiaDespLayout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPostearGuiaDespLayout.createSequentialGroup()
                        .addGroup(jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chbVentaContado)
                            .addComponent(jLabel45))
                        .addGap(7, 7, 7)
                        .addGroup(jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cbbFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(spnPlazoDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelPostearGuiaDespLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chbRetiradoPlanta))
                    .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPostearGuiaDespLayout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPostearGuiaDespLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPostearGuiaDespLayout.createSequentialGroup()
                                .addGroup(jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnLovCliente)
                                    .addComponent(chbClienteValido))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtOrdenCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addGap(34, 34, 34))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPostearGuiaDespLayout.createSequentialGroup()
                                .addGroup(jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtOrden1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13)
                                    .addComponent(txtPedido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPedido3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtOrden2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPedido2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPedido4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(cbbVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCodVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbZonaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16)
                                    .addComponent(txtAlicuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLayeredPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanelPostearGuiaDespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(chbModoEditar)
                                    .addComponent(chbModoInsertar)
                                    .addComponent(chbModoConsultar))
                                .addGap(35, 35, 35))))))
        );

        jTabbedPaneGuiaDespacho.addTab("Postear Guia Despacho", jPanelPostearGuiaDesp);

        jPanelVentaPersonaNatural.setBackground(new java.awt.Color(184, 191, 195));

        jLayeredPane6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel23.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel23.setText("Ced id/RIF: ");
        jLayeredPane6.add(jLabel23);
        jLabel23.setBounds(210, 30, 86, 20);

        txtCedula.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        txtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaActionPerformed(evt);
            }
        });
        jLayeredPane6.add(txtCedula);
        txtCedula.setBounds(310, 20, 206, 27);

        txtRazonSocialPerNatural.setColumns(20);
        txtRazonSocialPerNatural.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        txtRazonSocialPerNatural.setRows(5);
        jScrollPane8.setViewportView(txtRazonSocialPerNatural);

        jLayeredPane6.add(jScrollPane8);
        jScrollPane8.setBounds(310, 60, 312, 50);

        jLabel24.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel24.setText("Nombre del Cliente/Razon Social:");
        jLayeredPane6.add(jLabel24);
        jLabel24.setBounds(50, 80, 246, 17);

        jLabel25.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel25.setText("N.I.T:");
        jLayeredPane6.add(jLabel25);
        jLabel25.setBounds(260, 140, 38, 17);

        txtNit.setEditable(false);
        txtNit.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLayeredPane6.add(txtNit);
        txtNit.setBounds(310, 130, 140, 27);

        jLabel26.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel26.setText("Direccion Fiscal:");
        jLayeredPane6.add(jLabel26);
        jLabel26.setBounds(180, 190, 120, 17);

        txtDireccionFiscal.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jScrollPane7.setViewportView(txtDireccionFiscal);

        jLayeredPane6.add(jScrollPane7);
        jScrollPane7.setBounds(310, 190, 310, 80);

        javax.swing.GroupLayout jPanelVentaPersonaNaturalLayout = new javax.swing.GroupLayout(jPanelVentaPersonaNatural);
        jPanelVentaPersonaNatural.setLayout(jPanelVentaPersonaNaturalLayout);
        jPanelVentaPersonaNaturalLayout.setHorizontalGroup(
            jPanelVentaPersonaNaturalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVentaPersonaNaturalLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLayeredPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(218, Short.MAX_VALUE))
        );
        jPanelVentaPersonaNaturalLayout.setVerticalGroup(
            jPanelVentaPersonaNaturalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVentaPersonaNaturalLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLayeredPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(116, Short.MAX_VALUE))
        );

        jTabbedPaneGuiaDespacho.addTab("Despacho Persona Natural", jPanelVentaPersonaNatural);

        jPanelDatosTransporte.setBackground(new java.awt.Color(184, 191, 195));
        jPanelDatosTransporte.setVerifyInputWhenFocusTarget(false);

        jLayeredPane3.setBackground(java.awt.SystemColor.controlHighlight);
        jLayeredPane3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel29.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel29.setText("Ced. Id. Transportista:  ");
        jLayeredPane3.add(jLabel29);
        jLabel29.setBounds(60, 10, 210, 17);

        jLabel30.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel30.setText("Nombre del transportista: ");
        jLayeredPane3.add(jLabel30);
        jLabel30.setBounds(60, 40, 220, 20);

        txtCedulaTransportista.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        txtCedulaTransportista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaTransportistaActionPerformed(evt);
            }
        });
        jLayeredPane3.add(txtCedulaTransportista);
        txtCedulaTransportista.setBounds(280, 10, 150, 27);

        btnLovTransportista.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        btnLovTransportista.setText("...");
        btnLovTransportista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLovTransportistaActionPerformed(evt);
            }
        });
        jLayeredPane3.add(btnLovTransportista);
        btnLovTransportista.setBounds(430, 10, 30, 29);

        txtNombreTransportista.setEditable(false);
        txtNombreTransportista.setBackground(new java.awt.Color(166, 231, 234));
        txtNombreTransportista.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLayeredPane3.add(txtNombreTransportista);
        txtNombreTransportista.setBounds(280, 40, 370, 27);

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel31.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel31.setText("Codigo: ");
        jLayeredPane4.add(jLabel31);
        jLabel31.setBounds(60, 10, 100, 17);

        txtCodTransporte.setEditable(false);
        txtCodTransporte.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLayeredPane4.add(txtCodTransporte);
        txtCodTransporte.setBounds(250, 10, 80, 27);

        jLabel32.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel32.setText("Nombre: ");
        jLayeredPane4.add(jLabel32);
        jLabel32.setBounds(60, 40, 80, 17);

        btnLovTransporte.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        btnLovTransporte.setText("...");
        jLayeredPane4.add(btnLovTransporte);
        btnLovTransporte.setBounds(330, 10, 30, 29);

        txtNombreTransporte.setEditable(false);
        txtNombreTransporte.setBackground(new java.awt.Color(166, 231, 234));
        txtNombreTransporte.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLayeredPane4.add(txtNombreTransporte);
        txtNombreTransporte.setBounds(250, 40, 370, 27);

        jLabel33.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel33.setText("Transporte:  ");
        jLayeredPane4.add(jLabel33);
        jLabel33.setBounds(60, 70, 160, 17);

        jLabel34.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel34.setText("Codigo camion:  ");
        jLayeredPane4.add(jLabel34);
        jLabel34.setBounds(60, 100, 180, 17);

        jLabel35.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel35.setText("Tipo camion: ");
        jLayeredPane4.add(jLabel35);
        jLabel35.setBounds(60, 130, 180, 17);

        jLabel36.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel36.setText("Nro de ejes:  ");
        jLayeredPane4.add(jLabel36);
        jLabel36.setBounds(440, 70, 110, 20);

        jLabel37.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel37.setText("Placa Batea:  ");
        jLayeredPane4.add(jLabel37);
        jLabel37.setBounds(440, 130, 110, 17);

        jLabel38.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel38.setText("Placa Chuto: ");
        jLayeredPane4.add(jLabel38);
        jLabel38.setBounds(440, 100, 110, 17);

        cbbTipoTransporte.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        cbbTipoTransporte.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Terrestre", "Maritimo", "Aereo" }));
        jLayeredPane4.add(cbbTipoTransporte);
        cbbTipoTransporte.setBounds(250, 70, 130, 27);

        btnUnidadTransporte.setText("...");
        btnUnidadTransporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnidadTransporteActionPerformed(evt);
            }
        });
        jLayeredPane4.add(btnUnidadTransporte);
        btnUnidadTransporte.setBounds(350, 100, 30, 29);

        txtCodCamion.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLayeredPane4.add(txtCodCamion);
        txtCodCamion.setBounds(250, 100, 100, 27);

        txtTipoCamion.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLayeredPane4.add(txtTipoCamion);
        txtTipoCamion.setBounds(250, 130, 130, 27);

        txtNroEjes.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLayeredPane4.add(txtNroEjes);
        txtNroEjes.setBounds(540, 70, 130, 27);

        txtPlacaChuto.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLayeredPane4.add(txtPlacaChuto);
        txtPlacaChuto.setBounds(540, 100, 90, 27);

        txtPlacaBatea.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLayeredPane4.add(txtPlacaBatea);
        txtPlacaBatea.setBounds(540, 130, 90, 27);

        jLayeredPane5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel39.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel39.setText("Destino-Sector :");
        jLayeredPane5.add(jLabel39);
        jLabel39.setBounds(30, 20, 130, 17);

        txtCodSector.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLayeredPane5.add(txtCodSector);
        txtCodSector.setBounds(260, 10, 60, 27);

        btnLovFleteDestino.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        btnLovFleteDestino.setText("...");
        btnLovFleteDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLovFleteDestinoActionPerformed(evt);
            }
        });
        jLayeredPane5.add(btnLovFleteDestino);
        btnLovFleteDestino.setBounds(320, 10, 40, 29);

        txtFechaVigente.setEditable(false);
        txtFechaVigente.setBackground(new java.awt.Color(166, 231, 234));
        txtFechaVigente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd-MM-yyyy"))));
        txtFechaVigente.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        txtFechaVigente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaVigenteActionPerformed(evt);
            }
        });
        jLayeredPane5.add(txtFechaVigente);
        txtFechaVigente.setBounds(620, 10, 90, 27);

        jLabel40.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel40.setText("Destino:   ");
        jLayeredPane5.add(jLabel40);
        jLabel40.setBounds(60, 40, 90, 17);

        txtDestino.setEditable(false);
        txtDestino.setBackground(new java.awt.Color(166, 231, 234));
        txtDestino.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLayeredPane5.add(txtDestino);
        txtDestino.setBounds(160, 40, 200, 27);

        jLabel41.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel41.setText("Estado:   ");
        jLayeredPane5.add(jLabel41);
        jLabel41.setBounds(60, 70, 100, 17);

        jLabel42.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel42.setText("Tarifa vigente al:  ");
        jLayeredPane5.add(jLabel42);
        jLabel42.setBounds(480, 10, 140, 20);

        txtEstado.setEditable(false);
        txtEstado.setBackground(new java.awt.Color(166, 231, 234));
        txtEstado.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLayeredPane5.add(txtEstado);
        txtEstado.setBounds(160, 70, 200, 27);

        jLabel44.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel44.setText("Nro Guia Reparto Origen: ");
        jLayeredPane5.add(jLabel44);
        jLabel44.setBounds(410, 100, 200, 17);

        txtGuiaOrigenReparto.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        txtGuiaOrigenReparto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGuiaOrigenRepartoActionPerformed(evt);
            }
        });
        jLayeredPane5.add(txtGuiaOrigenReparto);
        txtGuiaOrigenReparto.setBounds(610, 100, 100, 27);

        chbGuiaReparto.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        chbGuiaReparto.setText("Guia Reparto ");
        chbGuiaReparto.setNextFocusableComponent(txtGuiaOrigenReparto);
        chbGuiaReparto.setOpaque(true);
        chbGuiaReparto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbGuiaRepartoActionPerformed(evt);
            }
        });
        jLayeredPane5.add(chbGuiaReparto);
        chbGuiaReparto.setBounds(160, 100, 190, 24);

        txtCodDestino.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLayeredPane5.add(txtCodDestino);
        txtCodDestino.setBounds(160, 10, 80, 27);

        jLabel54.setText("-");
        jLayeredPane5.add(jLabel54);
        jLabel54.setBounds(250, 10, 20, 30);

        jLabel55.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel55.setText("Codigo");
        jLayeredPane5.add(jLabel55);
        jLabel55.setBounds(60, 0, 60, 17);

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(128, 0, 48));
        jLabel3.setText("DATOS DEL TRANSPORTISTA: ");

        jLabel27.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(128, 0, 48));
        jLabel27.setText("DATOS DEL TRANSPORTE: ");

        jLabel28.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(128, 0, 48));
        jLabel28.setText("DATOS DESTINO: ");

        javax.swing.GroupLayout jPanelDatosTransporteLayout = new javax.swing.GroupLayout(jPanelDatosTransporte);
        jPanelDatosTransporte.setLayout(jPanelDatosTransporteLayout);
        jPanelDatosTransporteLayout.setHorizontalGroup(
            jPanelDatosTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDatosTransporteLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanelDatosTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addGroup(jPanelDatosTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLayeredPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
                        .addComponent(jLayeredPane3, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel28)
                    .addComponent(jLabel27))
                .addContainerGap(238, Short.MAX_VALUE))
        );
        jPanelDatosTransporteLayout.setVerticalGroup(
            jPanelDatosTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDatosTransporteLayout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(1, 1, 1)
                .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPaneGuiaDespacho.addTab("Transporte y Destino", jPanelDatosTransporte);

        jPanelDetalleGuia.setBackground(new java.awt.Color(184, 191, 195));
        jPanelDetalleGuia.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLayeredPane1.setBackground(java.awt.SystemColor.controlHighlight);
        jLayeredPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(10, 14, 16), null));

        jLabel18.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel18.setText("Nro. Guia Despacho: ");
        jLayeredPane1.add(jLabel18);
        jLabel18.setBounds(50, 20, 150, 15);

        jLabel19.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel19.setText("Nro. Factura: ");
        jLayeredPane1.add(jLabel19);
        jLabel19.setBounds(440, 20, 140, 17);

        jLabel20.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel20.setText("Fecha factura: ");
        jLayeredPane1.add(jLabel20);
        jLabel20.setBounds(440, 60, 130, 17);

        txtDisplayNroGuia.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLayeredPane1.add(txtDisplayNroGuia);
        txtDisplayNroGuia.setBounds(210, 10, 100, 27);

        txtNroFactura.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        txtNroFactura.setFocusCycleRoot(true);
        jLayeredPane1.add(txtNroFactura);
        txtNroFactura.setBounds(570, 10, 100, 27);

        txtFechaFactura.setFocusCycleRoot(true);
        txtFechaFactura.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLayeredPane1.add(txtFechaFactura);
        txtFechaFactura.setBounds(570, 50, 100, 27);

        jLabel22.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel22.setText("Fecha Guia:    ");
        jLayeredPane1.add(jLabel22);
        jLabel22.setBounds(50, 50, 140, 17);

        txtDisplayFechaGuia.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLayeredPane1.add(txtDisplayFechaGuia);
        txtDisplayFechaGuia.setBounds(210, 50, 100, 27);

        jPanelDetalleGuia.add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 900, 86));

        jLayeredPane2.setBackground(java.awt.SystemColor.controlHighlight);
        jLayeredPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(21, 14, 14), null));

        jLabel21.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Total MONTO (Bs) : ");
        jLayeredPane2.add(jLabel21);
        jLabel21.setBounds(470, 20, 180, 17);

        txtDisplayTotalMonto.setEditable(false);
        txtDisplayTotalMonto.setBackground(new java.awt.Color(197, 250, 191));
        txtDisplayTotalMonto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDisplayTotalMonto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###,##0.00"))));
        txtDisplayTotalMonto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDisplayTotalMonto.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLayeredPane2.add(txtDisplayTotalMonto);
        txtDisplayTotalMonto.setBounds(660, 10, 180, 30);

        jLabel43.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel43.setText("Total kgs (Peso Guia): ");
        jLayeredPane2.add(jLabel43);
        jLabel43.setBounds(20, 20, 180, 17);

        txtDisplayTotalPesoGuia.setEditable(false);
        txtDisplayTotalPesoGuia.setBackground(new java.awt.Color(187, 235, 237));
        txtDisplayTotalPesoGuia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDisplayTotalPesoGuia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###,##0.000"))));
        txtDisplayTotalPesoGuia.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDisplayTotalPesoGuia.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLayeredPane2.add(txtDisplayTotalPesoGuia);
        txtDisplayTotalPesoGuia.setBounds(210, 10, 180, 30);

        jPanelDetalleGuia.add(jLayeredPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 900, 55));

        jLayeredPane11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tablaDetalleGuia.setBorder(new javax.swing.border.MatteBorder(null));
        tablaDetalleGuia.setModel(new ModeloTablaGuia02() {});
        tablaDetalleGuia.setName("DETALLE PRODUCTOS DETALLE"); // NOI18N
        tablaDetalleGuia.setRowHeight(20);
        jScrollPane1.setViewportView(tablaDetalleGuia);

        jLayeredPane11.add(jScrollPane1);
        jScrollPane1.setBounds(10, 50, 880, 160);

        btnRefrescarTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/redo16px.png"))); // NOI18N
        btnRefrescarTabla.setToolTipText("Refrescar Grilla de Productos.");
        btnRefrescarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefrescarTablaActionPerformed(evt);
            }
        });
        jLayeredPane11.add(btnRefrescarTabla);
        btnRefrescarTabla.setBounds(420, 10, 40, 30);

        btnZoomAgregarDetalleGuia.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        btnZoomAgregarDetalleGuia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/zoom16px.png"))); // NOI18N
        btnZoomAgregarDetalleGuia.setText("Zoom");
        btnZoomAgregarDetalleGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZoomAgregarDetalleGuiaActionPerformed(evt);
            }
        });
        jLayeredPane11.add(btnZoomAgregarDetalleGuia);
        btnZoomAgregarDetalleGuia.setBounds(460, 10, 430, 29);

        btnAgregarRegDetalle.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        btnAgregarRegDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/anadir16px.png"))); // NOI18N
        btnAgregarRegDetalle.setText("AGREGAR REGISTROS");
        btnAgregarRegDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarRegDetalleActionPerformed(evt);
            }
        });
        jLayeredPane11.add(btnAgregarRegDetalle);
        btnAgregarRegDetalle.setBounds(10, 10, 410, 29);

        jPanelDetalleGuia.add(jLayeredPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 106, 900, 220));

        chbActualizarGridProductos.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        chbActualizarGridProductos.setText("Actualizar Grid");
        chbActualizarGridProductos.setEnabled(false);
        jPanelDetalleGuia.add(chbActualizarGridProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 340, 100, -1));

        jTabbedPaneGuiaDespacho.addTab("Detalle Productos ", jPanelDetalleGuia);

        jPanelPesoRomana.setBackground(new java.awt.Color(184, 191, 195));
        jPanelPesoRomana.setRequestFocusEnabled(false);

        jLayeredPane7.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(124, 58, 58), null));

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel4.setText("Serial Ticket (P.Tara): ");
        jLayeredPane7.add(jLabel4);
        jLabel4.setBounds(370, 20, 190, 20);

        jLabel46.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel46.setText("Peso Tara:");
        jLayeredPane7.add(jLabel46);
        jLabel46.setBounds(80, 20, 100, 20);

        jLabel48.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel48.setText("Peso NOMINAL:");
        jLayeredPane7.add(jLabel48);
        jLabel48.setBounds(50, 200, 130, 20);

        txtSerialTicket1.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLayeredPane7.add(txtSerialTicket1);
        txtSerialTicket1.setBounds(560, 20, 100, 27);

        txtSerialTicket2.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLayeredPane7.add(txtSerialTicket2);
        txtSerialTicket2.setBounds(560, 80, 100, 27);

        txtPesoBruto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtPesoBruto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPesoBruto.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        txtPesoBruto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesoBrutoActionPerformed(evt);
            }
        });
        txtPesoBruto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPesoBrutoKeyTyped(evt);
            }
        });
        jLayeredPane7.add(txtPesoBruto);
        txtPesoBruto.setBounds(194, 80, 130, 27);

        txtPesoTara.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtPesoTara.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPesoTara.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        txtPesoTara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesoTaraActionPerformed(evt);
            }
        });
        jLayeredPane7.add(txtPesoTara);
        txtPesoTara.setBounds(194, 20, 130, 27);

        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLayeredPane7.add(jSeparator4);
        jSeparator4.setBounds(80, 120, 240, 10);

        jLabel49.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel49.setText("Peso Bruto:");
        jLayeredPane7.add(jLabel49);
        jLabel49.setBounds(80, 80, 100, 20);

        jLabel50.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel50.setText("Peso NETO:");
        jLayeredPane7.add(jLabel50);
        jLabel50.setBounds(80, 150, 100, 20);

        txtDisplayPesoNominal.setEditable(false);
        txtDisplayPesoNominal.setBackground(new java.awt.Color(226, 213, 150));
        txtDisplayPesoNominal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDisplayPesoNominal.setForeground(new java.awt.Color(20, 10, 10));
        txtDisplayPesoNominal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtDisplayPesoNominal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDisplayPesoNominal.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLayeredPane7.add(txtDisplayPesoNominal);
        txtDisplayPesoNominal.setBounds(180, 200, 140, 27);

        jLayeredPane9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel47.setBackground(new java.awt.Color(242, 222, 14));
        jLabel47.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel47.setText("  DIFERENCIA:");
        jLabel47.setOpaque(true);
        jLayeredPane9.add(jLabel47);
        jLabel47.setBounds(60, 40, 120, 30);

        jFormattedTextField4.setBackground(new java.awt.Color(217, 227, 233));
        jFormattedTextField4.setText("jFormattedTextField3");
        jFormattedTextField4.setOpaque(false);
        jLayeredPane9.add(jFormattedTextField4);
        jFormattedTextField4.setBounds(-190, 10, 160, 27);

        txtDisplayDiferenciaPesoRomana.setEditable(false);
        txtDisplayDiferenciaPesoRomana.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDisplayDiferenciaPesoRomana.setForeground(new java.awt.Color(15, 11, 11));
        txtDisplayDiferenciaPesoRomana.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtDisplayDiferenciaPesoRomana.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDisplayDiferenciaPesoRomana.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLayeredPane9.add(txtDisplayDiferenciaPesoRomana);
        txtDisplayDiferenciaPesoRomana.setBounds(190, 40, 90, 30);

        jLabel52.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel52.setText("%");
        jLayeredPane9.add(jLabel52);
        jLabel52.setBounds(290, 50, 30, 17);

        jLayeredPane7.add(jLayeredPane9);
        jLayeredPane9.setBounds(380, 130, 340, 100);

        jLabel51.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel51.setText("Serial Ticket (P.Bruto):");
        jLayeredPane7.add(jLabel51);
        jLabel51.setBounds(370, 80, 190, 20);

        txtPesoNeto.setBackground(new java.awt.Color(219, 235, 236));
        txtPesoNeto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPesoNeto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtPesoNeto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPesoNeto.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLayeredPane7.add(txtPesoNeto);
        txtPesoNeto.setBounds(180, 150, 140, 27);

        javax.swing.GroupLayout jPanelPesoRomanaLayout = new javax.swing.GroupLayout(jPanelPesoRomana);
        jPanelPesoRomana.setLayout(jPanelPesoRomanaLayout);
        jPanelPesoRomanaLayout.setHorizontalGroup(
            jPanelPesoRomanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPesoRomanaLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLayeredPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(218, Short.MAX_VALUE))
        );
        jPanelPesoRomanaLayout.setVerticalGroup(
            jPanelPesoRomanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPesoRomanaLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLayeredPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        jTabbedPaneGuiaDespacho.addTab("Datos Peso Romana", jPanelPesoRomana);

        jLabel53.setBackground(new java.awt.Color(126, 52, 57));
        jLabel53.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(251, 251, 251));
        jLabel53.setText("POSTEAR GUIA DE DESPACHO");
        jLabel53.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel53.setOpaque(true);

        lblModoOperacion.setBackground(new java.awt.Color(126, 56, 52));
        lblModoOperacion.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        lblModoOperacion.setForeground(new java.awt.Color(242, 247, 250));
        lblModoOperacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblModoOperacion.setText("Modo Operacion");
        lblModoOperacion.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 868, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblModoOperacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneGuiaDespacho, javax.swing.GroupLayout.PREFERRED_SIZE, 1049, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModoOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPaneGuiaDespacho, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        activarCamposBusqueda(true);
        setBotoneraBusqueda(true);
    }//GEN-LAST:event_btnBuscarActionPerformed
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void btnCancelarBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarBusquedaActionPerformed
        cancelarBusqueda();
    }//GEN-LAST:event_btnCancelarBusquedaActionPerformed
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void btnEjecutarBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarBuscarActionPerformed
        ejecutarBusquedaGuia();  
    }//GEN-LAST:event_btnEjecutarBuscarActionPerformed
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void btnLovClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLovClienteActionPerformed
        /*
         if (PanelGuiaDespacho.chbClienteValido.isSelected() || PanelGuiaDespacho.txtCodCliente.getText().isEmpty()) {  // (Ver comment *.1). 
         ControladorLovCliente lovCliente = new ControladorLovCliente();
         //lovCliente.ejecutarFrameLovCliente();        // Aplica 1er metodo. 
         //lovCliente.ejecutarDialogLovCliente();       //    "   2do metodo.  
         lovCliente.ejecutarDialogLovJKTCliente();      //    "   3er intento.  
         }  // if.
         */
        ControladorLovCliente lovCliente = new ControladorLovCliente();
        lovCliente.ejecutarDialogLovJKTCliente();
    }//GEN-LAST:event_btnLovClienteActionPerformed
    
    private void txtCodClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodClienteFocusLost
        // validarDatosCliente();   // En stand by porque produce un efecto Indeseable.  
    }//GEN-LAST:event_txtCodClienteFocusLost
    
    private void btnLovTransportistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLovTransportistaActionPerformed
        ControladorLovTransportista controladorLovTransportista = new ControladorLovTransportista();
        controladorLovTransportista.ejecutarDialogLovTransportista();
    }//GEN-LAST:event_btnLovTransportistaActionPerformed
    
    private void btnUnidadTransporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnidadTransporteActionPerformed
        if (!PanelGuiaDespacho.txtCedulaTransportista.getText().isEmpty() && !PanelGuiaDespacho.txtCodTransporte.getText().isEmpty()) {
            ControladorLovUnidadTransporte controladorUnidadTransporte = new ControladorLovUnidadTransporte();
            controladorUnidadTransporte.ejecutarDialogLovUnidadTransporte();
        }  // if.  
    }//GEN-LAST:event_btnUnidadTransporteActionPerformed
    
    private void btnLovFleteDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLovFleteDestinoActionPerformed
        ControladorLovFleteDestino controladorLovFleteDestino = new ControladorLovFleteDestino();
        controladorLovFleteDestino.ejecutarDialogLovFleteDestino();
    }//GEN-LAST:event_btnLovFleteDestinoActionPerformed
    
    private void btnZoomAgregarDetalleGuiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoomAgregarDetalleGuiaActionPerformed
        ControladorDetalleGuia controladorDetalleGuia = new ControladorDetalleGuia();
        controladorDetalleGuia.ejecutarDialogZoomAgregarDetalleGuia();
    }//GEN-LAST:event_btnZoomAgregarDetalleGuiaActionPerformed
    
    private void cbbFormaPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbFormaPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbFormaPagoActionPerformed
    
    private void cbbVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbVendedorActionPerformed
        String vendedor = (String) PanelGuiaDespacho.cbbVendedor.getSelectedItem();
        if ( vendedor != null ) {
        Integer l = vendedor.length();
        String codVendedor = vendedor.substring(l - 2);
        PanelGuiaDespacho.txtCodVendedor.setText(codVendedor);
        ServicioAdministracionVend01 servicioAdministracionVend01 = new ServicioAdministracionVend01();
        Vend01 datosVendedor = servicioAdministracionVend01.getVendedor(codVendedor);
        PanelGuiaDespacho.cbbZonaVenta.setSelectedItem(datosVendedor.getC1_ZONA());
        }
    }//GEN-LAST:event_cbbVendedorActionPerformed
    
    private void txtPesoTaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesoTaraActionPerformed
        calcularPesoRomana();
        PanelGuiaDespacho.txtPesoBruto.requestFocus();
        //System.out.println("Aquica \ny nuevamente Aquica");
    }//GEN-LAST:event_txtPesoTaraActionPerformed
    
    private void txtPesoBrutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesoBrutoActionPerformed
        if ( checkValoresPesoRomana() ) {
            calcularPesoRomana();
            Number pesoNeto = (Number) PanelGuiaDespacho.txtPesoNeto.getValue();  
            Number pesoNominal = (Number) PanelGuiaDespacho.txtDisplayPesoNominal.getValue();  
            Double difPeso = 0.00;  
            if ( pesoNeto != null && pesoNominal != null  ) {
                 difPeso = ( pesoNominal.doubleValue() == 0 ? 0 : ( pesoNeto.doubleValue() / pesoNominal.doubleValue() ) * 100.00 );
            }
            PanelGuiaDespacho.txtDisplayDiferenciaPesoRomana.setValue(difPeso);
            PanelGuiaDespacho.txtSerialTicket1.requestFocus();  
        } else {
          Color colorOriginal = PanelGuiaDespacho.txtPesoBruto.getBackground(); 
          libreriaHP.showMsgError(this, PanelGuiaDespacho.txtPesoBruto,"TEXTO","Revisar valores peso Romana. ¿P.Tara > P.Bruto?.",colorOriginal);
        }
    }//GEN-LAST:event_txtPesoBrutoActionPerformed
    
    private void txtPesoBrutoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoBrutoKeyTyped
        final int returnCHAR = 10;
        final int tabCHAR = 13;
        int k = (int) evt.getKeyChar();
        if (k == returnCHAR || k == tabCHAR) {
            //System.out.println("Usuario ha presionado:\n[ENTER] o [TAB] key.");
        }
    }//GEN-LAST:event_txtPesoBrutoKeyTyped
    
    private void txtCodClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodClienteActionPerformed
        String codigo = PanelGuiaDespacho.txtCodCliente.getText().toUpperCase();
        PanelGuiaDespacho.txtCodCliente.setText(codigo);
        if (validarDatosCliente()) {
            PanelGuiaDespacho.txtDireccionEntrega.requestFocus();
        } else {
            PanelGuiaDespacho.txtCodCliente.requestFocus();
        }
    }//GEN-LAST:event_txtCodClienteActionPerformed
    
    private void btnRefrescarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarTablaActionPerformed
        ControladorDetalleGuia controladorDetalleGuia = new ControladorDetalleGuia();
        controladorDetalleGuia.iniciarTablaDetalleGuia();
        PanelGuiaDespacho.chbActualizarGridProductos.setSelected(false);
    }//GEN-LAST:event_btnRefrescarTablaActionPerformed
    
    private void btnAgregarRegDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarRegDetalleActionPerformed
       if ( PanelGuiaDespacho.chbModoEditar.isSelected() ) {
            agregarRegDetalle(); 
            //ControladorDetalleGuia controladorDetalleGuia = new ControladorDetalleGuia();
            //controladorDetalleGuia.agregarNuevoRegDetalle();
            //PanelAgregarDetalleGuia.limpiarCampos();
            //PanelAgregarDetalleGuia.setCamposAgregarItemsGuia(true);
       }
    }//GEN-LAST:event_btnAgregarRegDetalleActionPerformed
            
    private void txtOrden1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOrden1ActionPerformed
        if (validarOrdenFab()) {
            PanelGuiaDespacho.txtOrden2.requestFocus();
        } else {
            PanelGuiaDespacho.txtOrden1.requestFocus();
        }
    }//GEN-LAST:event_txtOrden1ActionPerformed
        
    private void txtPedido1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPedido1ActionPerformed
        if (validarPedido()) {
            PanelGuiaDespacho.txtPedido2.requestFocus();
        } else {
            PanelGuiaDespacho.txtPedido1.requestFocus();
        }
    }//GEN-LAST:event_txtPedido1ActionPerformed
    
    private void txtCedulaTransportistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaTransportistaActionPerformed
        String cedula = PanelGuiaDespacho.txtCedulaTransportista.getText().toUpperCase();
        PanelGuiaDespacho.txtCedulaTransportista.setText(cedula.toUpperCase());
        if (validarTransportista()) {
            PanelGuiaDespacho.txtTipoCamion.requestFocus();
        } else {
            PanelGuiaDespacho.txtCedulaTransportista.requestFocus();
        }
    }//GEN-LAST:event_txtCedulaTransportistaActionPerformed
    
    private void txtFechaVigenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaVigenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaVigenteActionPerformed
    
    private void chbGuiaRepartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbGuiaRepartoActionPerformed
        if (PanelGuiaDespacho.chbGuiaReparto.isSelected()) {
            PanelGuiaDespacho.txtGuiaOrigenReparto.requestFocus();
        } else {
            PanelGuiaDespacho.txtGuiaOrigenReparto.setText(null);
        }
    }//GEN-LAST:event_chbGuiaRepartoActionPerformed
    
    private void txtGuiaOrigenRepartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGuiaOrigenRepartoActionPerformed
        if (!validarGuiaReparto()) {
            PanelGuiaDespacho.txtGuiaOrigenReparto.requestFocus();
        } else {
            PanelGuiaDespacho.txtDisplayNroGuia.requestFocus();
        }
    }//GEN-LAST:event_txtGuiaOrigenRepartoActionPerformed

    private void txtNroGuiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNroGuiaActionPerformed
        PanelGuiaDespacho.txtFechaGuia.requestFocus();
    }//GEN-LAST:event_txtNroGuiaActionPerformed

    private void txtNroControlFiscalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNroControlFiscalActionPerformed
        PanelGuiaDespacho.txtNroGuia.requestFocus();
    }//GEN-LAST:event_txtNroControlFiscalActionPerformed

    private void chbRetiradoPlantaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbRetiradoPlantaActionPerformed
        validarDirEntrega(); 
    }//GEN-LAST:event_chbRetiradoPlantaActionPerformed

    private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed
        String cedula = PanelGuiaDespacho.txtCedula.getText(); 
        PanelGuiaDespacho.txtCedula.setText(cedula.toUpperCase());
    }//GEN-LAST:event_txtCedulaActionPerformed

    private void txtDireccionEntregaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtDireccionEntregaPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionEntregaPropertyChange

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnAgregarRegDetalle;
    public static javax.swing.JButton btnBuscar;
    public static javax.swing.JButton btnCancelarBusqueda;
    public static javax.swing.JButton btnEjecutarBuscar;
    public static javax.swing.JButton btnLovCliente;
    public static javax.swing.JButton btnLovFleteDestino;
    private static javax.swing.JButton btnLovTransporte;
    public static javax.swing.JButton btnLovTransportista;
    public static javax.swing.JButton btnRefrescarTabla;
    public static javax.swing.JButton btnUnidadTransporte;
    public static javax.swing.JButton btnZoomAgregarDetalleGuia;
    public static javax.swing.JComboBox cbbFormaPago;
    public static javax.swing.JComboBox cbbOperadorBusquedaFecha;
    public static javax.swing.JComboBox cbbTipoTransporte;
    public static javax.swing.JComboBox cbbVendedor;
    public static javax.swing.JComboBox cbbZonaVenta;
    public static javax.swing.JCheckBox chbActualizarGridProductos;
    public static javax.swing.JCheckBox chbClienteValido;
    public static javax.swing.JCheckBox chbGuiaReparto;
    public static javax.swing.JRadioButton chbModoConsultar;
    public static javax.swing.JRadioButton chbModoEditar;
    public static javax.swing.JRadioButton chbModoInsertar;
    public static javax.swing.JCheckBox chbRetiradoPlanta;
    public static javax.swing.JCheckBox chbVentaContado;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JFormattedTextField jFormattedTextField4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane10;
    private javax.swing.JLayeredPane jLayeredPane11;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    public static javax.swing.JLayeredPane jLayeredPane4;
    private static javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JLayeredPane jLayeredPane6;
    private javax.swing.JLayeredPane jLayeredPane7;
    private javax.swing.JLayeredPane jLayeredPane9;
    public static javax.swing.JPanel jPanelDatosTransporte;
    private javax.swing.JPanel jPanelDetalleGuia;
    private javax.swing.JPanel jPanelPesoRomana;
    private javax.swing.JPanel jPanelVentaPersonaNatural;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    public static javax.swing.JTabbedPane jTabbedPaneGuiaDespacho;
    public static javax.swing.JLabel lblModoOperacion;
    public static javax.swing.JSpinner spnPlazoDias;
    public static javax.swing.JTable tablaDetalleGuia;
    public static javax.swing.JFormattedTextField txtAlicuota;
    public static javax.swing.JFormattedTextField txtCedula;
    public static javax.swing.JFormattedTextField txtCedulaTransportista;
    public static javax.swing.JTextField txtCodCamion;
    public static javax.swing.JFormattedTextField txtCodCliente;
    public static javax.swing.JTextField txtCodDestino;
    public static javax.swing.JTextField txtCodSector;
    public static javax.swing.JTextField txtCodTransporte;
    public static javax.swing.JTextField txtCodVendedor;
    public static javax.swing.JTextField txtDestino;
    public static javax.swing.JEditorPane txtDireccionEntrega;
    public static javax.swing.JEditorPane txtDireccionFiscal;
    public static javax.swing.JFormattedTextField txtDisplayDiferenciaPesoRomana;
    public static javax.swing.JFormattedTextField txtDisplayFechaGuia;
    public static javax.swing.JTextField txtDisplayGuiaProcesada;
    public static javax.swing.JTextField txtDisplayNroGuia;
    public static javax.swing.JFormattedTextField txtDisplayPesoNominal;
    public static javax.swing.JFormattedTextField txtDisplayTotalMonto;
    public static javax.swing.JFormattedTextField txtDisplayTotalPesoGuia;
    public static javax.swing.JTextField txtEstado;
    public static javax.swing.JFormattedTextField txtFechaFactura;
    public static com.toedter.calendar.JDateChooser txtFechaGuia;
    public static javax.swing.JFormattedTextField txtFechaVigente;
    public static javax.swing.JTextField txtGuiaOrigenReparto;
    public static javax.swing.JFormattedTextField txtNit;
    public static javax.swing.JTextField txtNombreTransporte;
    public static javax.swing.JTextField txtNombreTransportista;
    public static javax.swing.JFormattedTextField txtNroControlFiscal;
    public static javax.swing.JTextField txtNroEjes;
    public static javax.swing.JTextField txtNroFactura;
    public static javax.swing.JFormattedTextField txtNroGuia;
    public static javax.swing.JTextArea txtObservacion;
    public static javax.swing.JTextField txtOrden1;
    public static javax.swing.JTextField txtOrden2;
    public static javax.swing.JFormattedTextField txtOrdenCompra;
    public static javax.swing.JTextField txtPedido1;
    public static javax.swing.JTextField txtPedido2;
    public static javax.swing.JTextField txtPedido3;
    public static javax.swing.JTextField txtPedido4;
    public static javax.swing.JFormattedTextField txtPesoBruto;
    private static javax.swing.JFormattedTextField txtPesoNeto;
    public static javax.swing.JFormattedTextField txtPesoTara;
    public static javax.swing.JTextField txtPlacaBatea;
    public static javax.swing.JTextField txtPlacaChuto;
    public static javax.swing.JTextArea txtRazonSocial;
    public static javax.swing.JTextArea txtRazonSocialPerNatural;
    public static javax.swing.JTextField txtSerialTicket1;
    public static javax.swing.JTextField txtSerialTicket2;
    public static javax.swing.JTextField txtTipoCamion;
    // End of variables declaration//GEN-END:variables
}
