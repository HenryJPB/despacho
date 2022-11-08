/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.controlador;

import bean.entidad.Guia01;
import bean.entidad.Guia02;
import bean.interfase.IControladorGuiaDespacho;
import bean.modelo.ModeloTablaGuia02;
import bean.servicio.ServicioAdministracionGuia01;
import bean.servicio.ServicioAdministracionGuia02;
import bean.utilitario.libreria.LibreriaHP;
import despacho.Estilos;
import java.awt.Color;
import java.sql.Date;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vistas.PanelGuiaDespacho;

/**
 *
 * @author henrypb
 */
public class ControladorGuiaDespacho implements IControladorGuiaDespacho {

    PanelGuiaDespacho panelGuiaDespacho = new PanelGuiaDespacho();
    ServicioAdministracionGuia01 servicioAdministracionGuia01;
    ServicioAdministracionGuia02 servicioAdministracionGuia02;
    LibreriaHP libreriaHP = new LibreriaHP();
    ControladorDetalleGuia.COLUMNA RENGLON;
    final int SI = 0;
    final int NO = 1;

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public void agregarRegistro() {
        PanelGuiaDespacho.activarCampos();
        PanelGuiaDespacho.limpiarCampos();
        PanelGuiaDespacho.iniciarCampos();
        PanelGuiaDespacho.setVisibleBotoneraBusqueda(false);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public void editarRegistro() {
        PanelGuiaDespacho.activarCampos();
        PanelGuiaDespacho.setVisibleBotoneraBusqueda(false);
    } // editarRegistro. 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private Boolean validarDatosPostearGuia() {
        servicioAdministracionGuia01 = new ServicioAdministracionGuia01();
        String dirEntrega = PanelGuiaDespacho.txtDireccionEntrega.getText().toUpperCase();
        PanelGuiaDespacho.txtDireccionEntrega.setText(dirEntrega);
        String observacion = PanelGuiaDespacho.txtObservacion.getText().toUpperCase();
        PanelGuiaDespacho.txtObservacion.setText(observacion);
        Boolean valido = Boolean.TRUE;
        // check for Nro. de guia:   
        Color colorOriginal = PanelGuiaDespacho.txtNroGuia.getBackground();  
        if (valido && (PanelGuiaDespacho.txtNroGuia.getText() == null || PanelGuiaDespacho.txtNroGuia.getText().isEmpty())) {
            valido = Boolean.FALSE;
            libreriaHP.showMsgError(panelGuiaDespacho, PanelGuiaDespacho.txtNroGuia,"TEXTO", "NRO de GUIA NO puede ser NULO.",colorOriginal);
        } else {
            String nroGuia = PanelGuiaDespacho.txtNroGuia.getText();
            if (PanelGuiaDespacho.chbModoInsertar.isSelected() && servicioAdministracionGuia01.guiaExiste(nroGuia)) {
                valido = Boolean.FALSE;
                libreriaHP.showMsgError(panelGuiaDespacho, PanelGuiaDespacho.txtNroGuia,"TEXTO","Intenta repetir el No. de GUIA.",colorOriginal);
            }
        }
        // check for: nro Ctrl Fiscal ------------------------------------------ 
        if (valido && (PanelGuiaDespacho.txtNroControlFiscal.getText() == null || PanelGuiaDespacho.txtNroControlFiscal.getText().isEmpty())) {
            valido = Boolean.FALSE;
            libreriaHP.showMsgError(panelGuiaDespacho, PanelGuiaDespacho.txtNroControlFiscal,"TEXTO","Nro Control Fiscal NO puede ser nulo.",colorOriginal);
        } else {
            String nroCtrlFiscal = PanelGuiaDespacho.txtNroControlFiscal.getText();
            if (PanelGuiaDespacho.chbModoInsertar.isSelected() && servicioAdministracionGuia01.nroCtrlFiscalExiste(nroCtrlFiscal)) {
                valido = Boolean.FALSE;
                libreriaHP.showMsgError(panelGuiaDespacho, PanelGuiaDespacho.txtNroControlFiscal,"TEXTO","Intenta repetir el No. Ctrl Fiscal.",colorOriginal);
            }
        }
        // check for: cod. Cliente ---------------------------------------------
        if (valido && (PanelGuiaDespacho.txtCodCliente.getText() == null || PanelGuiaDespacho.txtCodCliente.getText().isEmpty())) {
            valido = Boolean.FALSE;
            libreriaHP.showMsgError(panelGuiaDespacho, PanelGuiaDespacho.txtCodCliente,"TEXTO","Cod Cliente NO puede ser nulo",colorOriginal);
        }
        // check for: cod. Pedido ---------------------------------------------        
        String ordenFab = PanelGuiaDespacho.txtOrden1.getText();
        String pedido = PanelGuiaDespacho.txtPedido1.getText();
        if (valido && (ordenFab == null || ordenFab.isEmpty()) && (pedido == null || pedido.isEmpty())) {
            valido = Boolean.FALSE;
            libreriaHP.showMsgError(panelGuiaDespacho, PanelGuiaDespacho.txtPedido1,"TEXTO","El campo Pedido NO puede ser nulo.",colorOriginal);
        }
        return (valido);
    }  // validarDatosPostearGuia().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private Boolean validarDatosPerNatural() {
        String cedula = PanelGuiaDespacho.txtCedula.getText();
        PanelGuiaDespacho.txtCedula.setText(cedula.toUpperCase());
        String nombre = PanelGuiaDespacho.txtRazonSocialPerNatural.getText();
        PanelGuiaDespacho.txtRazonSocialPerNatural.setText(nombre.toUpperCase());
        String direccion = PanelGuiaDespacho.txtDireccionFiscal.getText();
        PanelGuiaDespacho.txtDireccionFiscal.setText(direccion.toUpperCase());
        Boolean valido = Boolean.TRUE;
        if (PanelGuiaDespacho.chbVentaContado.isSelected() && ((cedula == null || cedula.isEmpty()) || (nombre == null || nombre.isEmpty()) || (direccion == null || direccion.isEmpty()))) {
            valido = Boolean.FALSE;
            Color colorOriginal = PanelGuiaDespacho.txtDireccionFiscal.getBackground(); 
            libreriaHP.showMsgError(panelGuiaDespacho, PanelGuiaDespacho.txtCedula,"TEXTO","Datos Fiscales de la Persona Natural INCOMPLETOS o con valores NULOS.",colorOriginal);
        }
        return (valido);
    }  // validarDatosPerNatural()

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private Boolean validarDatosTranspDestino() {
        String cedulaTransp = PanelGuiaDespacho.txtCedulaTransportista.getText();
        PanelGuiaDespacho.txtCedulaTransportista.setText(cedulaTransp.toUpperCase());
        String codTransp = PanelGuiaDespacho.txtCodTransporte.getText();
        String codCamion = PanelGuiaDespacho.txtCodCamion.getText();
        String codDestino = PanelGuiaDespacho.txtCodDestino.getText();
        Boolean valido = Boolean.TRUE;
        if (valido && ((cedulaTransp == null || cedulaTransp.isEmpty()) || (codTransp == null || codTransp.isEmpty()) || (codCamion == null || codCamion.isEmpty()) || (codDestino == null || codDestino.isEmpty()))) {
            valido = Boolean.FALSE;
            Color colorOriginal = PanelGuiaDespacho.txtCodDestino.getBackground(); 
            libreriaHP.showMsgError(panelGuiaDespacho, PanelGuiaDespacho.txtCedulaTransportista,"TEXTO","Datos del Transporte y/o Destino INCOMPLETOS o con valores NULOS.",colorOriginal);
        }
        return (valido);
    }  // validarDatosTranspDestino().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private Boolean validarDatosClaves() {
        Boolean valido = validarDatosPostearGuia() && validarDatosPerNatural() && validarDatosTranspDestino();
        return (valido);
    }  // validarDatosClaves.  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void getDirEntrega(Guia01 guia01) {
        String dirEntrega = PanelGuiaDespacho.txtDireccionEntrega.getText();
        int tamCadena = 30;
        String cadena;
        cadena = (dirEntrega.length() > 1 * tamCadena ? dirEntrega.substring(0, 1 * tamCadena) : dirEntrega.substring(0, dirEntrega.length()));
        guia01.setC1_ENTREGA1(cadena);
        if (dirEntrega.length() > 1 * tamCadena) {
            cadena = (dirEntrega.length() > 2 * tamCadena ? dirEntrega.substring(1 * tamCadena + 1, 2 * tamCadena) : dirEntrega.substring(1 * tamCadena + 1, dirEntrega.length()));
        } else {
            cadena = null;
        }
        guia01.setC1_ENTREGA2(cadena);
        if (dirEntrega.length() > 2 * tamCadena) {
            cadena = (dirEntrega.length() > 3 * tamCadena ? dirEntrega.substring(2 * tamCadena + 1, 3 * tamCadena) : dirEntrega.substring(2 * tamCadena + 1, dirEntrega.length()));
        } else {
            cadena = null;
        }
        guia01.setC1_ENTREGA3(cadena);
        if (dirEntrega.length() > 3 * tamCadena) {
            cadena = (dirEntrega.length() > 4 * tamCadena ? dirEntrega.substring(3 * tamCadena + 1, 4 * tamCadena) : dirEntrega.substring(3 * tamCadena + 1, dirEntrega.length()));
        } else {
            cadena = null;
        }
        guia01.setC1_ENTREGA4(cadena);
    }  // getDirEntrega().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private String formaPago(String strFormaPago) {
        String chrFormaPago = null;
        switch (strFormaPago) {
            case "Contado": {
                chrFormaPago = "C";
                break;
            }
            case "Credito": {
                chrFormaPago = "R";
                break;
            }
            default:
                chrFormaPago = null;
        }  // switch
        return (chrFormaPago);
    } // formaPago.  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void getDatosPostearGuia(Guia01 guia01) {
        guia01.setC1_NCF(PanelGuiaDespacho.txtNroControlFiscal.getText());
        guia01.setC1_GUIA(PanelGuiaDespacho.txtNroGuia.getText());
        guia01.setC1_FECHA_GUIA(new java.sql.Date(PanelGuiaDespacho.txtFechaGuia.getDate().getTime()));
        String retiradoPlanta = null;
        if (PanelGuiaDespacho.chbRetiradoPlanta.isSelected()) {
            retiradoPlanta = "X";
        }
        guia01.setC1_RETIRADO_PLANTA(retiradoPlanta);
        String ventaClienteEspecial = null;
        if (PanelGuiaDespacho.chbVentaContado.isSelected()) {
            ventaClienteEspecial = "X";
        }
        guia01.setC1_CLIENTE_ESPECIAL(ventaClienteEspecial);
        //String formaPago = (String) PanelGuiaDespacho.cbbFormaPago.getSelectedItem();  
        guia01.setC1_FORMA_PAGO(formaPago(PanelGuiaDespacho.cbbFormaPago.getSelectedItem().toString()));
        guia01.setC1_PLAZO((Integer) PanelGuiaDespacho.spnPlazoDias.getValue());
        guia01.setC1_CODIGO_CLIENTE(PanelGuiaDespacho.txtCodCliente.getText());
        guia01.setC1_RAZON_SOCIAL(PanelGuiaDespacho.txtRazonSocial.getText());
        getDirEntrega(guia01);
        guia01.setC1_ORDEN_COMPRA(PanelGuiaDespacho.txtOrdenCompra.getText());
        guia01.setC1_ORDEN1(PanelGuiaDespacho.txtOrden1.getText());
        guia01.setC1_ORDEN2(PanelGuiaDespacho.txtOrden2.getText());
        guia01.setC1_PEDIDO1(PanelGuiaDespacho.txtPedido1.getText());
        guia01.setC1_PEDIDO2(PanelGuiaDespacho.txtPedido2.getText());
        guia01.setC1_PEDIDO3(PanelGuiaDespacho.txtPedido3.getText());
        guia01.setC1_PEDIDO4(PanelGuiaDespacho.txtPedido4.getText());
        Number alicuota = (Number) PanelGuiaDespacho.txtAlicuota.getValue();
        guia01.setC1_ALICUOTA(alicuota.doubleValue());
        guia01.setC1_NOMBRE_VENDEDOR((String) PanelGuiaDespacho.cbbVendedor.getSelectedItem());
        guia01.setC1_VENDEDOR(PanelGuiaDespacho.txtCodVendedor.getText());
        guia01.setC1_ZONA((String) PanelGuiaDespacho.cbbZonaVenta.getSelectedItem());
        guia01.setC1_OBSERVACION(PanelGuiaDespacho.txtObservacion.getText());
    }  // getDatosPostearGuia(). 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void getDirFiscalPerNat(Guia01 guia01) {
        String dirFiscal = PanelGuiaDespacho.txtDireccionFiscal.getText();
        int tamCadena = 40;
        String cadena;
        cadena = (dirFiscal.length() > 1 * tamCadena ? dirFiscal.substring(0, 1 * tamCadena) : dirFiscal.substring(0, dirFiscal.length()));
        guia01.setC1_DIR_FISCAL1(cadena);
        if (dirFiscal.length() > 2 * tamCadena) {
            cadena = (dirFiscal.length() > 2 * tamCadena ? dirFiscal.substring(1 * tamCadena + 1, 2 * tamCadena) : dirFiscal.substring(1 * tamCadena + 1, dirFiscal.length()));
        } else {
            cadena = null;
        }
        guia01.setC1_DIR_FISCAL2(cadena);
        if (dirFiscal.length() > 3 * tamCadena) {
            cadena = (dirFiscal.length() > 3 * tamCadena ? dirFiscal.substring(2 * tamCadena + 1, 3 * tamCadena) : dirFiscal.substring(2 * tamCadena + 1, dirFiscal.length()));
        } else {
            cadena = null;
        }
        guia01.setC1_DIR_FISCAL3(cadena);
    }  // getDirEntrega().

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void getDatosPerNatural(Guia01 guia01) {
        guia01.setC1_RIF(PanelGuiaDespacho.txtCedula.getText());
        guia01.setC1_RAZON_SOCIAL(PanelGuiaDespacho.txtRazonSocialPerNatural.getText());
        guia01.setC1_NIT(PanelGuiaDespacho.txtNit.getText());
        getDirFiscalPerNat(guia01);
    }  // getDatosPerNatural().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private String tipoTransporte(String strTipoTransporte) {
        String chrTipoTransporte = null;
        switch (strTipoTransporte) {
            case "Terrestre": {
                chrTipoTransporte = "T";
                break;
            }
            case "Maritimo": {
                chrTipoTransporte = "M";
                break;
            }
            case "Areo": {
                chrTipoTransporte = "A";
                break;
            }
            default:
                chrTipoTransporte = "O";   // Otro.  
        }  // switch. 
        return (chrTipoTransporte);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void getDatosTranspDestino(Guia01 guia01) {
        guia01.setC1_CI_CHOFER(PanelGuiaDespacho.txtCedulaTransportista.getText());
        guia01.setC1_NOMBRE_CHOFER(PanelGuiaDespacho.txtNombreTransportista.getText());
        guia01.setC1_COD_TRANSP(PanelGuiaDespacho.txtCodTransporte.getText());
        String nombreTransp = PanelGuiaDespacho.txtNombreTransporte.getText();
        nombreTransp = (nombreTransp.length() > 30 ? nombreTransp.substring(0, 30 - 1) : nombreTransp.substring(0, nombreTransp.length()));
        guia01.setC1_NOMBRE_TRANSP(nombreTransp);
        guia01.setC1_TIPO_TRANSPORTE((tipoTransporte(PanelGuiaDespacho.cbbTipoTransporte.getSelectedItem().toString())));
        guia01.setC1_COD_CAMION(PanelGuiaDespacho.txtCodCamion.getText());
        guia01.setC1_TIPO_CAMION(PanelGuiaDespacho.txtTipoCamion.getText());
        guia01.setC1_NO_EJES(PanelGuiaDespacho.txtNroEjes.getText());
        guia01.setC1_PLACA_BATEA(PanelGuiaDespacho.txtPlacaBatea.getText());
        guia01.setC1_PLACA_CHUTO(PanelGuiaDespacho.txtPlacaChuto.getText());
        //----------------------------------------------------------------------
        // * Destino: *  
        //----------------------------------------------------------------------
        final int tamCodDestino = 3;  // enumerado desde 0..2  ( 3 digitos ).  
        // 
        String codDestino = PanelGuiaDespacho.txtCodDestino.getText();  
        if ( codDestino.length()>(tamCodDestino) ) {
             codDestino = codDestino.substring(0,tamCodDestino); 
        }
        guia01.setC1_COD_DESTINO(codDestino);
        //
        String codSector = PanelGuiaDespacho.txtCodSector.getText(); 
        if ( codSector.length()>(tamCodDestino ) ) {
             codSector = codSector.substring(0,tamCodDestino);  
        }
        guia01.setC1_COD_SECTOR(codSector);
        guia01.setC1_NOMBRE_DESTINO(PanelGuiaDespacho.txtDestino.getText());
        guia01.setC1_ESTADO(PanelGuiaDespacho.txtEstado.getText());
        Date fechaRelacion = null;
        if (PanelGuiaDespacho.txtFechaVigente.getText() != null && !PanelGuiaDespacho.txtFechaVigente.getText().isEmpty()) {
            try {
                fechaRelacion = (new java.sql.Date(Estilos.formatoFecha.parse(PanelGuiaDespacho.txtFechaVigente.getText()).getTime()));
            } catch (ParseException ex) {
                Logger.getLogger(ControladorGuiaDespacho.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        guia01.setC1_FECHA_RELACION(fechaRelacion);
        String guiaReparto = null;
        if (PanelGuiaDespacho.chbGuiaReparto.isSelected()) {
            guiaReparto = "X";
        }
        guia01.setC1_GUIA_REPARTO(guiaReparto);
        guia01.setC1_GUIA_ORIGEN_REPARTO(PanelGuiaDespacho.txtGuiaOrigenReparto.getText());
    }  // getDatosTranspDestino(). 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void getDatosPesoRomana(Guia01 guia01) {
        Number pesoTara = (Number) PanelGuiaDespacho.txtPesoTara.getValue();
        guia01.setC1_PESO_TARA(pesoTara.doubleValue());
        Number pesoBruto = (Number) PanelGuiaDespacho.txtPesoBruto.getValue();
        guia01.setC1_PESO_BRUTO(pesoBruto.doubleValue());
        guia01.setC1_SERIAL_TICKET1(PanelGuiaDespacho.txtSerialTicket1.getText());
        guia01.setC1_SERIAL_TICKET2(PanelGuiaDespacho.txtSerialTicket2.getText());
    }  // getDatosRomana().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void agregarDatosMaestro() {
        Guia01 guia01 = new Guia01();
        getDatosPostearGuia(guia01);
        getDatosPerNatural(guia01);
        getDatosTranspDestino(guia01);
        getDatosPesoRomana(guia01);
        guia01.setC1_STATUS("D");  // D)iferido.  
        servicioAdministracionGuia01 = new ServicioAdministracionGuia01();
        servicioAdministracionGuia01.incluir(guia01);
    }   // agregarDatosMaestro.  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private String facXpeso(Boolean fp) {
        if (fp) {
            return ("X");
        } else {
            return (null);
        }
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private String facXunidad(Boolean fu) {
        if (fu) {
            return ("X");
        } else {
            return (null);
        }
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void getDatosGrid(Object[] fila, Guia02 itemGrid) {
        itemGrid.setC2_ITEM_NO((Integer) fila[RENGLON.ITEM_NO.ordinal()]);
        itemGrid.setC2_CODIGO((String) fila[RENGLON.CODIGO.ordinal()]);
        itemGrid.setC2_TIPO((String) fila[RENGLON.TIPO.ordinal()]);
        itemGrid.setC2_DESCRIPCION((String) fila[RENGLON.DESCRIPCION.ordinal()]);
        itemGrid.setC2_PESO((Double) fila[RENGLON.PESO_UNI.ordinal()]);
        itemGrid.setC2_NO_PEDIDO((String) fila[RENGLON.NO_PEDIDO.ordinal()]);
        itemGrid.setC2_UNIDADES((Double) fila[RENGLON.CANTIDAD.ordinal()]);
        itemGrid.setC2_ITEMS((Double) fila[RENGLON.ITEMS.ordinal()]);
        itemGrid.setC2_ATADOS((String) fila[RENGLON.ATADOS.ordinal()]);
        itemGrid.setC2_PRECIO((Double) fila[RENGLON.PRECIO.ordinal()]);
        itemGrid.setC2_ALICUOTA((Double) fila[RENGLON.ALICUOTA.ordinal()]);
        itemGrid.setC2_PESO_GUIA((Double) fila[RENGLON.TOTAL_PESO.ordinal()]);
        itemGrid.setC2_FXPESO((String) facXpeso((Boolean) fila[RENGLON.FAC_PESO.ordinal()]));
        itemGrid.setC2_FXUNIDAD((String) facXunidad((Boolean) fila[RENGLON.FAC_UNIDAD.ordinal()]));
    }  // getDatosGrid().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void agregarGuia() {
        if (validarDatosClaves()) {
            String nroGuia = PanelGuiaDespacho.txtNroGuia.getText();
            servicioAdministracionGuia01 = new ServicioAdministracionGuia01();
            if (nroGuia != null && !servicioAdministracionGuia01.guiaExiste(nroGuia)) { // correcto la Guia no existe: procede la insercion. 
                //System.out.println("La insercion procede y los campos claves estan validados. nro Guia=" + nroGuia + " no existe ****");
                agregarDatosMaestro();
                if (PanelGuiaDespacho.chbActualizarGridProductos.isSelected()) {
                    actualizarDatosGrid();        // Actualizar datos Detalle de la guia: (1)Eliminacion e (2)Insercion. 
                }
                PanelGuiaDespacho.limpiarCampos();
                cancelar();
                PanelGuiaDespacho.txtDisplayGuiaProcesada.setText(nroGuia);
                if (JOptionPane.showConfirmDialog(panelGuiaDespacho, "¿Deseas imprimir la guia procesada?:", "ATENCION", JOptionPane.YES_NO_OPTION) == SI) {
                    String guiaProcesada = PanelGuiaDespacho.txtDisplayGuiaProcesada.getText();
                    System.out.println("EJECUTAR PROCESO DE IMPRESION DE LA GUIA PROCESADA=" + guiaProcesada + ",.........................................");
                    ControladorReporte controladorReporte = new ControladorReporte();
                    controladorReporte.imprimirGuia(guiaProcesada, guiaProcesada);
                }
            }  // if interno.  
        }
    }  // agregarGuia.  

    //--------------------------------------------------------------------------
    // opcion: ACTUALIZAR (update):  
    //--------------------------------------------------------------------------
    private void actualizarDatosMaestro() {
        Guia01 guia01 = new Guia01();
        getDatosPostearGuia(guia01);
        getDatosPerNatural(guia01);
        getDatosTranspDestino(guia01);
        getDatosPesoRomana(guia01);
        servicioAdministracionGuia01 = new ServicioAdministracionGuia01();
        servicioAdministracionGuia01.actualizar(guia01);
    }  // actualizarDatosMaestro.  

    //--------------------------------------------------------------------------
    // opcion actualizar x INCLUSION y/o ACTUALIZAR x modificacion. 
    // nota (08-10-2013): El proceso de actualizacion del Detalle de la Guia ( Grid )
    // consiste en la (1) eliminacion de los registros fisicamente e (2) inclusion de 
    // de los de los registros de la lista de Productos.  
    //--------------------------------------------------------------------------
    private void actualizarDatosGrid() {
        servicioAdministracionGuia02 = new ServicioAdministracionGuia02();
        String nroGuia = PanelGuiaDespacho.txtNroGuia.getText();
        if (servicioAdministracionGuia02.existeRegDetalleGuia(nroGuia)) {
            servicioAdministracionGuia02.eliminarProductosGuia(nroGuia);
        }
        ModeloTablaGuia02 modeloTablaGuia02 = (ModeloTablaGuia02) PanelGuiaDespacho.tablaDetalleGuia.getModel();
        for (Integer i = 0; i < modeloTablaGuia02.listaDetalleProductosGuia.size(); i++) {
            Object[] fila = modeloTablaGuia02.listaDetalleProductosGuia.get(i);
            Guia02 guia02 = new Guia02();
            getDatosGrid(fila, guia02);
            servicioAdministracionGuia02.incluirProductoGuia(nroGuia, guia02);
        }  // for.  
    }  // agregarDatosGrid.  

    //--------------------------------------------------------------------------
    // opcion: ACTUALIZAR x cambio o modificacion.  
    //--------------------------------------------------------------------------
    private void actualizarGuia() {
        if (validarDatosClaves()) {
            actualizarDatosMaestro();
            if (PanelGuiaDespacho.chbActualizarGridProductos.isSelected()) {
                actualizarDatosGrid();    // Actualizar datos detalle de la guia.  
            }
            JOptionPane.showMessageDialog(panelGuiaDespacho, "Guia actualizada satisfactoriamente.");
            cancelar();
        }
    }  // actualizarGuia.  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public void actualizarRegistro() {
        if (PanelGuiaDespacho.chbModoInsertar.isSelected()) {  // insert.  
            agregarGuia();
        } else // update Guia.  
        if (PanelGuiaDespacho.chbModoEditar.isSelected()) {    // update. 
            actualizarGuia();
        }
    } // actualizarRegistro.  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private Boolean modoConsulta() {
        Boolean valido = PanelGuiaDespacho.chbModoConsultar.isSelected() && !PanelGuiaDespacho.chbModoInsertar.isSelected() && !PanelGuiaDespacho.chbModoEditar.isSelected();
        return (valido);
    }  // modoConsulta(). 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public void eliminarRegistro() {
        servicioAdministracionGuia01 = new ServicioAdministracionGuia01();
        //JOptionPane.showMessageDialog(panelGuiaDespacho, "Ejecutar instrucciones para ELIMINAR registros");
        String nroGuia = PanelGuiaDespacho.txtNroGuia.getText();
        if ((nroGuia != null && !nroGuia.isEmpty()) && modoConsulta() && (JOptionPane.showConfirmDialog(panelGuiaDespacho, "Eliminar Registro", "Conforme", JOptionPane.YES_NO_OPTION) == SI)) {
            // si exiteGuia01( nroGuia )
            //    -> eliminarGuia01 
            if (servicioAdministracionGuia01.guiaExiste(nroGuia)) {
                servicioAdministracionGuia01.eliminar(nroGuia);
            }
            servicioAdministracionGuia02 = new ServicioAdministracionGuia02();
            // si existeGuia02( nroGuia )
            //    -> eliminarGuia02.  
            if (servicioAdministracionGuia02.existeRegDetalleGuia(nroGuia)) {
                servicioAdministracionGuia02.eliminarProductosGuia(nroGuia);
            }
            PanelGuiaDespacho.limpiarCampos();
            PanelGuiaDespacho.desactivarCampos();
            // deshabiliarCampos.
        }  // if JOption.. 
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public void limpiarCampos() {
        PanelGuiaDespacho.limpiarCampos();
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public void cancelar() {
        PanelGuiaDespacho.desactivarCampos();
        PanelGuiaDespacho.cancelarBusqueda();
        PanelGuiaDespacho.setVisibleBotoneraBusqueda(true);
    }
}   // ControladorGuiaDespacho().    