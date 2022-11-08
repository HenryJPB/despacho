/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.controlador;

import bean.entidad.Guia02;
import bean.interfase.IControladorDetalleGuia;
import bean.modelo.ModeloTablaGuia02;
import bean.servicio.ServicioAdministracionGuia02;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.*;
import bean.utilitario.inventFacturacion.Inv01;
import bean.utilitario.inventFacturacion.Inv04;
import bean.utilitario.inventFacturacion.ServicioAdministracionInv01;
import bean.utilitario.inventFacturacion.ServicioAdministracionInv02;
import bean.utilitario.inventFacturacion.ServicioAdministracionInv04;
import bean.utilitario.libreria.LibreriaHP;
import java.math.BigDecimal;
import javax.swing.JOptionPane;
import vistas.PanelAgregarDetalleGuia;
import vistas.PanelGuiaDespacho;
import vistas.PanelZoomAgregarDetalleGuia;

/**
 *
 * @author henrypb
 */
public class ControladorDetalleGuia implements IControladorDetalleGuia {

    final static String NUEVO_COD_ITEM = "<codigo item>";
    final static String NUEVA_DESCRIPCION = "<descripcion>";
    final static Integer sizeX = 680, // valores constantes.
            sizeY = 370;
    final static Integer posX = 250,
            posY = 200;
    ModeloTablaGuia02 modeloTablaGuia02;
    LibreriaHP libHP = new LibreriaHP(); 

    public enum COLUMNA {

        ITEM_NO, CODIGO, TIPO, DESCRIPCION, PESO_UNI, NO_PEDIDO, CANTIDAD, ATADOS, ITEMS, PRECIO, ALICUOTA, TOTAL_PESO, FAC_PESO, FAC_UNIDAD, TOTAL_MONTO, BOTON_ACTUALIZAR, BOTON_ELIMINAR, ID_ROW
    }   //  0      1       2      3            4          5          6        7      8       9        10        11          12         13           14              15             16          17        

    private enum RENGLON {

        CODIGO, TIPO_PROD, DESCRIPCION
    };  // Colunnas de la LOV.  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private Double totalPeso(int fila) {
        Double pesoUni = (Double) PanelGuiaDespacho.tablaDetalleGuia.getValueAt(fila, COLUMNA.PESO_UNI.ordinal());
        Double cantidad = (Double) PanelGuiaDespacho.tablaDetalleGuia.getValueAt(fila, COLUMNA.CANTIDAD.ordinal());
        return (pesoUni * cantidad);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public void configurarTablaDetalleGuia() {
        // table 'Default cell Renderer':
        RenderFormatoTabla formatoTabla = new RenderFormatoTabla();
        PanelGuiaDespacho.tablaDetalleGuia.setDefaultRenderer(String.class, (TableCellRenderer) formatoTabla);
        PanelGuiaDespacho.tablaDetalleGuia.setDefaultRenderer(Integer.class, (TableCellRenderer) formatoTabla);
        PanelGuiaDespacho.tablaDetalleGuia.setDefaultRenderer(Double.class, (TableCellRenderer) formatoTabla);
        PanelGuiaDespacho.tablaDetalleGuia.setDefaultRenderer(Float.class, (TableCellRenderer) formatoTabla);
        // Probando:--------------------------------------------------------------------------
        // Agregar un MouseListener a la tabla para validar la entrada de datos en las celdas:
        PanelGuiaDespacho.tablaDetalleGuia.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // ****EJEMPLO ILUSTRATIVO: ***********  
                if (e.getClickCount() == 1) {
                    int fila = PanelGuiaDespacho.tablaDetalleGuia.getSelectedRow();
                    int columna = PanelGuiaDespacho.tablaDetalleGuia.getSelectedColumn();
                    // *** do some action if appropriate column ***
                    //System.out.println("etas cliqueando el Grid. fila, colunna=(" + fila + "," + columna + ")");
                    //if (columna  == COLUMNA.PESO_UNI.ordinal() || columna == COLUMNA.CANTIDAD.ordinal() || columna == COLUMNA.PRECIO.ordinal()) {
                    //     System.out.println("*****CALCULAR BS. ITEM->Grid(fila,colunna)=" + PanelGuiaDespacho.tablaDetalleGuia.getValueAt(fila, columna) + "*****");
                }
                /* -------------------Probando al 01/08/2013:  ----------------------------------------
                 if (e.getClickCount() == 2) {
                 //JTable target = (JTable) e.getSource();
                 int fila2 =  PanelGuiaDespacho.tablaDetalleGuia.getSelectedRow();
                 int columna2 = PanelGuiaDespacho.tablaDetalleGuia.getSelectedColumn();
                 // *** do some action if appropriate column ***
                 // ejemplo:  
                 System.out.println("Valor de la celda (f,c)="+PanelGuiaDespacho.tablaDetalleGuia.getValueAt(fila, columna));  
                 //validarCeldaTabla( fila, columna );  
                 }  */
            }  // MouseEvent,...
        });  // addMouseListener().  
        //PanelGuiaDespacho.tablaDetalleGuia.tableChanged( TableModelEvent e);   ????
        //----------------------------------------------------------------------
        // POST-CHANGE: metodo para Validar el Cambio en la tabla (02/08/2013)--
        //----------------------------------------------------------------------
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {   // Se comporta como un evento POST-CHANGE. 
                TableCellListener tcl = (TableCellListener) e.getSource();
                /* funciono correctamente al 02/08/2013: Pero la instrucciones deben estar aqui para garantizar que se 
                 * ejecutan cuando se detecta un cambio de valor en la tabla del tipo: "postChange".   
                 */
                //System.out.println("Row   : " + tcl.getRow());
                //System.out.println("Column: " + tcl.getColumn());
                //System.out.println("Old   : " + tcl.getOldValue());
                //System.out.println("New   : " + tcl.getNewValue());
                // Crear <<Aqui>> metodo de validacion:
                int renglon = tcl.getColumn();
                //
                /*
                 if (renglon == COLUMNA.PESO_UNI.ordinal() || renglon == COLUMNA.CANTIDAD.ordinal() || renglon == COLUMNA.ITEMS.ordinal() || renglon == COLUMNA.PRECIO.ordinal()) {
                 int fila = tcl.getRow();  // Post-change en el valor de alguna de estas columnas y se requiere evitar  una valor negativo utilizando la funcion ABS (Aboslute Value). 
                 Double old_totalPesoColumna = (Double) PanelGuiaDespacho.tablaDetalleGuia.getValueAt(fila, COLUMNA.TOTAL_PESO.ordinal());
                 Double old_totalMontoColumna = (Double) PanelGuiaDespacho.tablaDetalleGuia.getValueAt(fila, COLUMNA.TOTAL_MONTO.ordinal());
                 // *peso*
                 Number pesoNumber = (Number) PanelGuiaDespacho.tablaDetalleGuia.getValueAt(fila, COLUMNA.PESO_UNI.ordinal());
                 Double peso = ( pesoNumber == null ? 0.00 : pesoNumber.doubleValue() ); 
                 // *cantidad* 
                 Number cantidadNumber = (Number) PanelGuiaDespacho.tablaDetalleGuia.getValueAt(fila, COLUMNA.CANTIDAD.ordinal());
                 Double cantidad = ( cantidadNumber==null ? 0.00 : cantidadNumber.doubleValue() );
                 // *items*
                 Double items = (Double) PanelGuiaDespacho.tablaDetalleGuia.getValueAt(fila, COLUMNA.ITEMS.ordinal());
                 PanelGuiaDespacho.tablaDetalleGuia.setValueAt(Math.abs(items), fila, COLUMNA.ITEMS.ordinal());
                 // *precio*
                 Number precioNumber = (Number) PanelGuiaDespacho.tablaDetalleGuia.getValueAt(fila, COLUMNA.PRECIO.ordinal());
                 Double precio = ( precioNumber==null ? 0.00 : precioNumber.doubleValue() ); 
                 //----------------------------------------------------------
                 // Actualizar TOTAL_PESO: ********************************** 
                 //----------------------------------------------------------
                 PanelGuiaDespacho.tablaDetalleGuia.setValueAt(totalPeso(fila), fila, COLUMNA.TOTAL_PESO.ordinal());
                 Double totalPesoGuia = (Double) PanelGuiaDespacho.txtDisplayTotalPesoGuia.getValue();
                 totalPesoGuia = totalPesoGuia - old_totalPesoColumna;
                 totalPesoGuia = totalPesoGuia + totalPeso(fila);
                 PanelGuiaDespacho.txtDisplayTotalPesoGuia.setValue(totalPesoGuia);
                 PanelGuiaDespacho.txtDisplayPesoNominal.setValue(totalPesoGuia);
                 //----------------------------------------------------------
                 // Actualizar TOTAL_MONTO: *********************************
                 //----------------------------------------------------------
                 Double totalMontoGuia = (Double) PanelGuiaDespacho.txtDisplayTotalMonto.getValue();
                 totalMontoGuia = totalMontoGuia - old_totalMontoColumna;
                 Double totalMontoColumna = precio;
                 Boolean facXpeso = (Boolean) PanelGuiaDespacho.tablaDetalleGuia.getValueAt(fila, COLUMNA.FAC_PESO.ordinal());
                 if (facXpeso) {
                 totalMontoColumna = totalMontoColumna * peso;
                 }
                 Boolean facXunidad = (Boolean) PanelGuiaDespacho.tablaDetalleGuia.getValueAt(fila, COLUMNA.FAC_UNIDAD.ordinal());
                 if (facXunidad) {
                 totalMontoColumna = totalMontoColumna * cantidad;
                 }
                 PanelGuiaDespacho.tablaDetalleGuia.setValueAt(totalMontoColumna, fila, COLUMNA.TOTAL_MONTO.ordinal());
                 totalMontoGuia = totalMontoGuia + totalMontoColumna;
                 PanelGuiaDespacho.txtDisplayTotalMonto.setValue(totalMontoGuia);  // (*)
                 // A este nivel se requiere un desplazamiento. 
                 } // if renglon,...
                 */
                PanelGuiaDespacho.chbActualizarGridProductos.setSelected(Boolean.TRUE);
            }  // actionPerfomed.  
        };  // actionPerformed. 
        // 
        TableCellListener tcl = new TableCellListener(PanelGuiaDespacho.tablaDetalleGuia, action);   // <! EeeeeeXIIIIITOOOOOOOOOOOOOOOOOOOO.....!!!. 
        // ** Pero esa es una validacion del tipo postChange; cuando ya la tabla ha cambiado su valor.
    }   // configurarTablaGuiaDespacho(); 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public void iniciarTablaDetalleGuia() {
        configurarTablaDetalleGuia();
        iniciarTablaGuia02();
    }   // iniciarTablaDetalleGuia02.  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public void ejecutarDialogAddDetalleGuia() {
        final int X = 420,
                  Y = 80,
                sizeX = 600,
                sizeY = 500;
        final JDialog dlgAddRegDetalle = new JDialog();
        final PanelAgregarDetalleGuia panelAddItem = new PanelAgregarDetalleGuia();
        PanelAgregarDetalleGuia.btnOkIngresarNuevoReg.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /* Eliminame:  
                  ControladorDetalleGuia controladorDetalleGuia = new ControladorDetalleGuia();
                  controladorDetalleGuia.incluirItemsTablaGuia02();
                  PanelAgregarDetalleGuia.limpiarCampos();
                  PanelAgregarDetalleGuia.setCamposAgregarItemsGuia(Boolean.FALSE);
                  PanelGuiaDespacho.chbActualizarGridProductos.setSelected(Boolean.TRUE);
                */
                if ( okCamposClaves() ) {
                    incluirItemsTablaGuia02();
                    dlgAddRegDetalle.dispose();
                    PanelAgregarDetalleGuia.setCamposAgregarItemsGuia(Boolean.FALSE);
                    PanelGuiaDespacho.chbActualizarGridProductos.setSelected(Boolean.TRUE);
                } else {
                    JOptionPane.showMessageDialog(panelAddItem,"Campos claves nulos","ATENCION", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        PanelAgregarDetalleGuia.btnLimpiarIngresarNuevoReg.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PanelAgregarDetalleGuia.limpiarCampos();
            }
        });
        PanelAgregarDetalleGuia.btnCancelarNuevoReg.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelAgregarDetalleGuia.setCamposAgregarItemsGuia(false);
                //this.setVisible(Boolean.FALSE);
                dlgAddRegDetalle.dispose();
            }
        });
        //--------------------------------------------------------------------
        PanelAgregarDetalleGuia.limpiarCampos();
        PanelAgregarDetalleGuia.setCamposAgregarItemsGuia(true);
        //--------------------------------------------------------------------
        dlgAddRegDetalle.setContentPane(panelAddItem);
        dlgAddRegDetalle.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dlgAddRegDetalle.setModal(Boolean.TRUE);
        dlgAddRegDetalle.setSize(sizeX, sizeY);
        dlgAddRegDetalle.setLocation(X, Y);
        dlgAddRegDetalle.setVisible(Boolean.TRUE);
    }  // ejecutarDialogAddDetalleGuia()
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private boolean okCamposClaves() {
       Boolean ok = Boolean.TRUE; 
       String codItem = PanelAgregarDetalleGuia.txtCodigo.getText(); 
       if ( codItem==null || codItem.isEmpty()  ) {
            ok = Boolean.FALSE;            
       } else {
           String tipoProd = (String) PanelAgregarDetalleGuia.cbbTipoProducto.getSelectedItem(); 
           if ( tipoProd==null || tipoProd.isEmpty()   ) {
               ok = Boolean.FALSE; 
           } else {
               String cantidadS = PanelAgregarDetalleGuia.txtCantidad.getText();
               BigDecimal cantidad = BigDecimal.ZERO; 
               if ( cantidadS!=null && !cantidadS.isEmpty() ) {
                   cantidad = new BigDecimal(libHP.desFormatoNumerico(cantidadS));
               } 
               String precioS = PanelAgregarDetalleGuia.txtPrecioUni.getText(); 
               BigDecimal precio = BigDecimal.ZERO; 
               if ( precioS!=null && !precioS.isEmpty()) {
                   precio = new BigDecimal(libHP.desFormatoNumerico(precioS)); 
               }
               String totalS = PanelAgregarDetalleGuia.txtDisplayTotalMonto.getText(); 
               BigDecimal total = BigDecimal.ZERO; 
               if (totalS!=null && !totalS.isEmpty() ) {
                   total = new BigDecimal(libHP.desFormatoNumerico(totalS));
               } 
               if ( cantidad.equals(BigDecimal.ZERO) || precio.equals(BigDecimal.ZERO) || total.equals(BigDecimal.ZERO) ) {
                   ok = Boolean.FALSE;                    
               }
           }
       }
       return (ok); 
    }  // okCamposClaves() 
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public void ejecutarDialogZoomAgregarDetalleGuia() {
        final JDialog jdialogFrameZoomDetalleGuia = new JDialog();
        final PanelZoomAgregarDetalleGuia panelZoomAgregarDetalleGuia = new PanelZoomAgregarDetalleGuia();
        String codigo = PanelAgregarDetalleGuia.txtCodigo.getText();
        PanelZoomAgregarDetalleGuia.txtCodigo.setText(codigo);
        DefaultComboBoxModel dfCbbModel = (DefaultComboBoxModel) PanelAgregarDetalleGuia.cbbTipoProducto.getModel();
        PanelZoomAgregarDetalleGuia.cbbTipoProducto.setModel(dfCbbModel);
        String descripcion = PanelAgregarDetalleGuia.txtDescripcion.getText();
        PanelZoomAgregarDetalleGuia.txtDescripcion.setText(descripcion);
        // *** Agregar funcionabilidad a los botones:
        panelZoomAgregarDetalleGuia.btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jdialogFrameZoomDetalleGuia.dispose();
            }
        });
        panelZoomAgregarDetalleGuia.btnConforme.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                /*  
                 String codigo = PanelZoomAgregarDetalleGuia.txtCodigo.getText();
                 PanelAgregarDetalleGuia.txtCodigo.setText(codigo);
                 DefaultComboBoxModel dfCbbModel = (DefaultComboBoxModel) PanelZoomAgregarDetalleGuia.cbbTipoProducto.getModel();  
                 PanelAgregarDetalleGuia.cbbTipoProducto.setModel(dfCbbModel);
                 String descripcion = PanelZoomAgregarDetalleGuia.txtDescripcion.getText();  
                 PanelAgregarDetalleGuia.txtDescripcion.setText(descripcion);  
                 */
                // <Escribe AQUI las instrucciones para refill de Grid Detalle de la GUIA. >
                jdialogFrameZoomDetalleGuia.dispose();
            }
        });
        panelZoomAgregarDetalleGuia.btnZoomMinus.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String codigo = PanelZoomAgregarDetalleGuia.txtCodigo.getText();
                PanelAgregarDetalleGuia.txtCodigo.setText(codigo);
                DefaultComboBoxModel dfCbbModel = (DefaultComboBoxModel) PanelZoomAgregarDetalleGuia.cbbTipoProducto.getModel();
                PanelAgregarDetalleGuia.cbbTipoProducto.setModel(dfCbbModel);
                String descripcion = PanelZoomAgregarDetalleGuia.txtDescripcion.getText();
                PanelAgregarDetalleGuia.txtDescripcion.setText(descripcion);
                jdialogFrameZoomDetalleGuia.dispose();
            }
        });
        panelZoomAgregarDetalleGuia.btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jdialogFrameZoomDetalleGuia.dispose();
            }
        });
        // *** -------------------------------------
        jdialogFrameZoomDetalleGuia.setContentPane(panelZoomAgregarDetalleGuia);
        jdialogFrameZoomDetalleGuia.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jdialogFrameZoomDetalleGuia.setModal(true);
        jdialogFrameZoomDetalleGuia.setLocation(posX, posY);
        jdialogFrameZoomDetalleGuia.setSize(sizeX, sizeY);
        jdialogFrameZoomDetalleGuia.setVisible(true);
    }  // ejecutarDialogFrameZoomAgregarDetalleGuia.  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void iniciarTablaGuia02() {
        JTableHeader header = PanelGuiaDespacho.tablaDetalleGuia.getTableHeader();
        header.setBackground(Color.black);
        header.setForeground(Color.yellow);
        header.setFont(new Font("Monospaced", Font.BOLD, 13));
        modeloTablaGuia02 = new ModeloTablaGuia02() {
        };
        modeloTablaGuia02.listaDetalleProductosGuia.clear();  // Asegurar q la tabla este vacia. Revisar metodo refresh el Grid. ???.   
        cargarDatosTablaGuia02(modeloTablaGuia02.listaDetalleProductosGuia);       // <: utilizando un Lista. 
        //cargarDatosTablaGuia02(modeloTablaGuia02.arregloDetalleProductosGuia);   // <: utilizando un Arreglo bidimensional.  
        PanelGuiaDespacho.tablaDetalleGuia.setModel(modeloTablaGuia02);
        ordenarTabla();
        configurarCeldasTabla();
        PanelGuiaDespacho.tablaDetalleGuia.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        setColumnSizes(PanelGuiaDespacho.tablaDetalleGuia);
        ocultarColumna(COLUMNA.ID_ROW.ordinal());
    }  // iniciarTablaGuia02.  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void ocultarColumna(int indiceColumna) {   // segun Internet link: http://elfragmx.wordpress.com/2011/03/12/ocultar-columna-de-jtable-en-java/
        PanelGuiaDespacho.tablaDetalleGuia.getColumnModel().getColumn(indiceColumna).setMaxWidth(0);
        PanelGuiaDespacho.tablaDetalleGuia.getColumnModel().getColumn(indiceColumna).setMinWidth(0);
        PanelGuiaDespacho.tablaDetalleGuia.getColumnModel().getColumn(indiceColumna).setPreferredWidth(0);
    }  // ocultarColumna().   // funciono correctamente el 23-07-2013.  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void configurarCeldasTabla() {
        PanelGuiaDespacho.tablaDetalleGuia.getColumnModel().getColumn(COLUMNA.DESCRIPCION.ordinal()).setCellEditor(new CellEditorAlfaNumerico(COLUMNA.DESCRIPCION));
        PanelGuiaDespacho.tablaDetalleGuia.getColumnModel().getColumn(COLUMNA.NO_PEDIDO.ordinal()).setCellEditor(new CellEditorAlfaNumerico(COLUMNA.NO_PEDIDO));
        // *
        PanelGuiaDespacho.tablaDetalleGuia.getColumn("Peso/Uni").setCellRenderer(new RenderCeldaNumerica("###,###,##0.000"));
        PanelGuiaDespacho.tablaDetalleGuia.getColumn("Peso/Uni").setCellEditor(new CellEditorNumerico(COLUMNA.PESO_UNI, "###,###,##0.000"));
        // configurar .addListener: actionPerformed para desplazar la columna Peso/Uni. 
        //
        PanelGuiaDespacho.tablaDetalleGuia.getColumn("Total Peso").setCellRenderer(new RenderCeldaNumerica("###,###,###,##0.000"));
        //PanelGuiaDespacho.tablaDetalleGuia.getColumn("Cantidad").setCellRenderer(new RenderCeldaNumerica("###,###,##0.00"));
        PanelGuiaDespacho.tablaDetalleGuia.getColumn("Cantidad").setCellEditor(new CellEditorNumerico(COLUMNA.CANTIDAD, "###,###,##0.00"));
        // configurar .addListener. actionPerformed para desplazar la colunna cantidad.
        PanelGuiaDespacho.tablaDetalleGuia.getColumn("Precio").setCellEditor(new CellEditorNumerico(COLUMNA.PRECIO, "###,###,##0.00"));
        //
        PanelGuiaDespacho.tablaDetalleGuia.getColumn("Items").setCellEditor(new CellEditorNumerico(COLUMNA.ITEMS, "###,###,##0"));
        // Configurar Boton Actualizar.  
        PanelGuiaDespacho.tablaDetalleGuia.getColumn("Actualizar").setCellRenderer(new BotonRenderer());
        PanelGuiaDespacho.tablaDetalleGuia.getColumn("Actualizar").setCellEditor(new BotonActualizarTablaGuia02(new JCheckBox()));
        // Remind: el identificador "Actualizar" es nombre del header q identifica la columna. 
        PanelGuiaDespacho.tablaDetalleGuia.getColumn("Accion").setCellRenderer(new BotonRenderer());
        PanelGuiaDespacho.tablaDetalleGuia.getColumn("Accion").setCellEditor(new BotonEliminarTablaGuia02(new JCheckBox()));
        //
        // Remind: el identificador "Accion" es nombre del header q identifica la columna.  
        // RenderCelda formatoCelda = new RenderCelda();   // ?????.  
        // OJO: las siguientes instrucciones estan SUJETAS A REVISION ( Brqto. 10/10/2013 ). NO ESTAN FUNCIONANDO CORRECTAMENTE.   
        //PanelGuiaDespacho.tablaDetalleGuia.getColumn("Cantidad").setCellRenderer((TableCellRenderer) new RenderCelda());
        //PanelGuiaDespacho.tablaDetalleGuia.getColumn("Cantidad").setCellRenderer( (TableCellRenderer) new RenderCelda());
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private Boolean facXpeso(String facPeso) {
        if (facPeso == null || facPeso.isEmpty()) {
            return (Boolean.FALSE);
        } else {
            return (Boolean.TRUE);
        }
    }  // facXpeso(). 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private Boolean facXunidad(String facUnidad) {
        if (facUnidad == null || facUnidad.isEmpty()) {
            return (Boolean.FALSE);
        } else {
            return (Boolean.TRUE);
        }
    }  // facXunidad(). 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private double calcularMonto(Guia02 productosGuia) {
        Double monto = productosGuia.getC2_PRECIO();
        if (facXpeso(productosGuia.getC2_FXPESO())) {
            monto = monto * productosGuia.getC2_PESO();
        }
        if (facXunidad(productosGuia.getC2_FXUNIDAD())) {
            monto = monto * productosGuia.getC2_UNIDADES();
        }
        return (monto);
    }   // calcularMonto().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void cargarDatosTablaGuia02(List<Object[]> listaProductos) {
        Object[] fila;
        ServicioAdministracionGuia02 servicioAdministracionGuia02 = new ServicioAdministracionGuia02();
        String nroGuia = PanelGuiaDespacho.txtDisplayNroGuia.getText();
        if (nroGuia != null && !nroGuia.isEmpty() && servicioAdministracionGuia02.existeRegDetalleGuia(nroGuia)) {
            //Guia02 productosGuiaGrid;  // Este constructor solo hace referencia a un subconjunto de valores de la tabla; ver Clase Guia02.java.  
            double acumPesoGuia = 0.00;
            double totalMonto = 0.00;
            //listaProductos.clear();
            for (Guia02 productosGuia : servicioAdministracionGuia02.getDetalleGuia(nroGuia)) {
                fila = new Object[]{(productosGuia.getC2_ITEM_NO() != null ? productosGuia.getC2_ITEM_NO() : 0),
                    productosGuia.getC2_CODIGO(),
                    productosGuia.getC2_TIPO(),
                    productosGuia.getC2_DESCRIPCION(),
                    productosGuia.getC2_PESO(),
                    (productosGuia.getC2_NO_PEDIDO() != null ? productosGuia.getC2_NO_PEDIDO() : ""),
                    productosGuia.getC2_UNIDADES(),
                    (productosGuia.getC2_ATADOS() != null ? productosGuia.getC2_ATADOS() : ""),
                    (productosGuia.getC2_ITEMS() != null ? productosGuia.getC2_ITEMS() : 0),
                    productosGuia.getC2_PRECIO(),
                    productosGuia.getC2_ALICUOTA(),
                    productosGuia.getC2_PESO_GUIA(),
                    facXpeso(productosGuia.getC2_FXPESO()), // Display Item: Fac x Peso en boleano.  
                    facXunidad(productosGuia.getC2_FXUNIDAD()), // Display Item: Fac x Unidad en boleano. 
                    (double) calcularMonto(productosGuia), // Display Item: Total Monto.  
                    "Conforme",
                    "Eliminar",
                    productosGuia.getIdRow()};   // IdRow.
                acumPesoGuia = acumPesoGuia + productosGuia.getC2_PESO_GUIA();
                totalMonto = totalMonto + calcularMonto(productosGuia);
                listaProductos.add(fila);
            }  // for.
            PanelGuiaDespacho.txtDisplayTotalPesoGuia.setValue(acumPesoGuia);
            PanelGuiaDespacho.txtDisplayPesoNominal.setValue(acumPesoGuia);
            PanelGuiaDespacho.txtDisplayTotalMonto.setValue(totalMonto);
        }  // if. 
    }  // cargarDatosProductosGuia().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void setColumnSizes(JTable tabla) {
        ModeloTablaGuia02 model = (ModeloTablaGuia02) tabla.getModel();
        TableColumn column;
        Component comp;
        int headerWidth;
        int cellWidth;
        Object[] longValues = model.longValues;    // <! de esta menera accedes a los miembros de la clase ModeloTablaGuia02. 
        TableCellRenderer headerRenderer = tabla.getTableHeader().getDefaultRenderer();
        for (int i = 0; i < modeloTablaGuia02.getColumnCount(); i++) {   // (**)  
            column = tabla.getColumnModel().getColumn(i);
            comp = headerRenderer.getTableCellRendererComponent(null, column.getHeaderValue(), false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;
            comp = tabla.getDefaultRenderer(model.getColumnClass(i)).getTableCellRendererComponent(tabla, longValues[i], false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;
            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
        // ** Valores constantes para definir el ancho de algunas colunnas:  *** 
        tabla.getColumn("Atados").setPreferredWidth(60);   //  <<  where n=valor en px.  
        tabla.getColumnModel().getColumn(COLUMNA.ALICUOTA.ordinal()).setPreferredWidth(100);   // valor expresado en pixels. 
        tabla.getColumnModel().getColumn(COLUMNA.FAC_PESO.ordinal()).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(COLUMNA.FAC_UNIDAD.ordinal()).setPreferredWidth(50);
    } //  setColumnSizes().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void ordenarTabla_old() {
        TableModel modeloTablaOrdenada = PanelGuiaDespacho.tablaDetalleGuia.getModel();
        RowSorter<TableModel> tablaOrdenada = new TableRowSorter<TableModel>(modeloTablaOrdenada);
        PanelGuiaDespacho.tablaDetalleGuia.setRowSorter(tablaOrdenada);
    }

    //--------------------------------------------------------------------------
    // SUJETO A REVISION (Brqto: 10/10/2013).>> Produce un efecto indeseable 
    // al actualizar los datos del grid luego de que esta ha sido reordenada.  ?????.  
    //--------------------------------------------------------------------------
    private void ordenarTabla() {
        ModeloTablaGuia02 modeloTablaOrdenada = (ModeloTablaGuia02) PanelGuiaDespacho.tablaDetalleGuia.getModel();
        RowSorter<TableModel> tablaOrdenada = new TableRowSorter<TableModel>(modeloTablaOrdenada);
        PanelGuiaDespacho.tablaDetalleGuia.setRowSorter(tablaOrdenada);
    }

    //--------------------------------------------------------------------------
    // NOTA : Este metodo genera problemas <-"si y solo si" -> cuando el Usuario 
    //        intenta eliminar ""TODOS*" los registros del Grid.  (antes).
    //        Superado el 04 de Oct 2013.  
    //--------------------------------------------------------------------------
    public void eliminarRegListaProductos(String idRow) {
        ModeloTablaGuia02 modeloTabla = (ModeloTablaGuia02) PanelGuiaDespacho.tablaDetalleGuia.getModel();
        ArrayList<Object[]> listaProductos;
        listaProductos = (ArrayList<Object[]>) modeloTabla.listaDetalleProductosGuia;
        if (listaProductos.size() > 0) {
            // Probar...
            // NOTA (30/07/2013): Existen 2 metodos para eliminar un elemento de un ArrayList:
            // (1). Por su indice o posicion dentro del Arreglo. 
            // (2). Segun el objeto como argumento; pero de igual forma se requieres saber cual Objeto es; por lo tanto hay que realizar un busqueda en el ArrayList.  ?.  
            // Procedere por el 2do Metodo:
            Object[] fila = null;
            boolean encontrado = false;
            int i = -1;
            while (!encontrado && i < listaProductos.size()) {    // <! realizar busqueda. 
                i++;
                fila = listaProductos.get(i);
                if (idRow.equals(fila[COLUMNA.ID_ROW.ordinal()])) {
                    encontrado = true;
                }
            }  // while-  
            if (encontrado) {
                Double totalMontoGuia = (Double) PanelGuiaDespacho.txtDisplayTotalMonto.getValue();
                Number totalPesoGuia = (Number) PanelGuiaDespacho.txtDisplayTotalPesoGuia.getValue();  // otra forma de expresar valor Numerico.  
                // Actualizar Peso de la Guia: 
                Double pesoItemEliminado = (Double) fila[COLUMNA.TOTAL_PESO.ordinal()];
                Double totalPeso = totalPesoGuia.doubleValue() - pesoItemEliminado;
                PanelGuiaDespacho.txtDisplayTotalPesoGuia.setValue(totalPeso);
                PanelGuiaDespacho.txtDisplayPesoNominal.setValue(totalPeso);
                // Actualizar Total Monto de la Guia:  
                Double montoItemEliminado = (Double) fila[COLUMNA.TOTAL_MONTO.ordinal()];
                PanelGuiaDespacho.txtDisplayTotalMonto.setValue(totalMontoGuia - montoItemEliminado);
                // * Remover: * 
                modeloTabla.listaDetalleProductosGuia.remove(fila);
                modeloTabla.fireTableRowsDeleted(i, i);   //  funcionó correctamente., o:    
                //modeloTabla.fireTableDataChanged();     //  funcionó tambien correctamente; revisar ventajas y diferencias.        
                PanelGuiaDespacho.chbActualizarGridProductos.setSelected(encontrado);
            }
        }  // if listaProductos posee + de 1 registro.  
    }  // eliminarRegListProductos().

    //------------------------------------------------------------------------------
    //------------------------------------------------------------------------------
    private Boolean despachoProdEspecial() {
        Boolean correcto = Boolean.TRUE;
        if (PanelGuiaDespacho.txtOrden1.getText() == null || PanelGuiaDespacho.txtOrden1.getText().isEmpty()) {
            correcto = Boolean.FALSE;
        }
        return (correcto);
    }  // despachoProdEspecial.  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void actualizarDatosNoClavesAgregarProductos(String codigoProd, String tipoProd) {
        Boolean facPeso = PanelAgregarDetalleGuia.facPeso(tipoProd);
        Boolean facUnidad = PanelAgregarDetalleGuia.facUnidad(tipoProd);
        String pedido = null;
        Double precio = 0.00;
        if (despachoProdEspecial()) {
            pedido = PanelGuiaDespacho.txtOrden1.getText();
            ServicioAdministracionInv04 servicioAdministracionInv04 = new ServicioAdministracionInv04();
            Inv04 datosProd = servicioAdministracionInv04.getProductoEspecial(pedido, codigoProd);  // Donde pedido = OrdenFab.  
            PanelAgregarDetalleGuia.txtPesoUni.setValue(datosProd.getC4_PESO_ITEM());
            precio = datosProd.getC4_BSXTON();
            PanelAgregarDetalleGuia.txtItems.setValue(datosProd.getC4_LAM());
            PanelAgregarDetalleGuia.txtCantidad.setValue(datosProd.getC4_LAM());
        } else {  // despachoProdStandard.  
            pedido = PanelGuiaDespacho.txtPedido1.getText();
            ServicioAdministracionInv01 servicioAdministracionInv01 = new ServicioAdministracionInv01();
            Inv01 datosProd = servicioAdministracionInv01.getProductoStandard(codigoProd);
            PanelAgregarDetalleGuia.txtPesoUni.setValue(datosProd.getC1_PESO());
            PanelAgregarDetalleGuia.txtItems.setValue(datosProd.getC1_ITEMS_ATADO());
            ServicioAdministracionInv02 servicioAdministracionInv02 = new ServicioAdministracionInv02();
            Date hoyEs = new Date();
            java.sql.Date alFecha = new java.sql.Date(hoyEs.getTime());
            if (servicioAdministracionInv02.precioReferencialExiste(codigoProd)) {
                if (facPeso) {
                    precio = servicioAdministracionInv02.precioKsg(codigoProd, alFecha);
                } else {
                    precio = servicioAdministracionInv02.precioUnidad(codigoProd, alFecha);
                }
            }
        }  // if despachoProdEspecial ?.  
        PanelAgregarDetalleGuia.txtPedido.setText(pedido);
        PanelAgregarDetalleGuia.txtPrecioUni.setValue(precio);
        //PanelAgregarDetalleGuia.cbbTipoProducto.setSelectedItem(tipoProd);   // ver NOTA(1): 
        if (facPeso) {
            PanelAgregarDetalleGuia.chbFacPeso.setSelected(Boolean.TRUE);
        } else {
            PanelAgregarDetalleGuia.chbFacPeso.setSelected(Boolean.FALSE);
        }
        if (facUnidad) {
            PanelAgregarDetalleGuia.chbFacUnidad.setSelected(Boolean.TRUE);
        } else {
            PanelAgregarDetalleGuia.chbFacUnidad.setSelected(Boolean.FALSE);
        }
        Number alicuota = (Number) PanelGuiaDespacho.txtAlicuota.getValue();
        PanelAgregarDetalleGuia.txtAlicuota.setValue(alicuota.doubleValue());
        // NOTA(1): Recuerda que para ejecutar esta instruccion se requiere definir el modelo en: Properties>Model> y agregar cada valor; ejemplo MP,MR,CL,MP,PL,CH,...
        //          porque sino el Sistema lo valida y no aparecere en la caja de texto.  
    }  // actualizarDatosNoClavesAgregarProductos()

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void actualizarDatosClavesAgregarProdutos(Integer filaSeleccionada, JTable tablaProd) {
        //PanelLovProductos panelLovProductos = new PanelLovProductos();
        String codigoProd = (String) tablaProd.getValueAt(filaSeleccionada, RENGLON.CODIGO.ordinal());
        String tipoProd = (String) tablaProd.getValueAt(filaSeleccionada, RENGLON.TIPO_PROD.ordinal());
        String descripcion = (String) tablaProd.getValueAt(filaSeleccionada, RENGLON.DESCRIPCION.ordinal());
        PanelAgregarDetalleGuia.txtCodigo.setText(codigoProd);
        DefaultComboBoxModel dfCbbModel = new DefaultComboBoxModel();
        dfCbbModel.addElement(tipoProd);
        PanelAgregarDetalleGuia.cbbTipoProducto.setModel(dfCbbModel);
        PanelAgregarDetalleGuia.txtDescripcion.setText(descripcion);
        PanelAgregarDetalleGuia.txtDescripcion.requestFocus();
        actualizarDatosNoClavesAgregarProductos(codigoProd, tipoProd);
    }  // actualizarDatosAgregarProdutosGuia().

    //---------------------------------------------------------------------------
    //---------------------------------------------------------------------------
    private Integer getItemNoNuevoReg() {
        ModeloTablaGuia02 modeloTabla = (ModeloTablaGuia02) PanelGuiaDespacho.tablaDetalleGuia.getModel();
        ArrayList<Object[]> listaProductos;
        listaProductos = (ArrayList<Object[]>) modeloTabla.listaDetalleProductosGuia;
        Object[] fila = null;
        Integer i = 0;
        Integer candidato = 0;
        Integer mayor = 0;
        Integer valor = 0;
        while (i < listaProductos.size()) {
            fila = listaProductos.get(i);
            valor = (Integer) fila[COLUMNA.ITEM_NO.ordinal()];
            if (candidato == valor) {
                candidato++;
                i = 0;
            } else {
                //if ((Integer) fila[COLUMNA.ITEM_NO.ordinal()] > mayor) {
                if (valor > mayor) {
                    //mayor = (Integer) fila[COLUMNA.ITEM_NO.ordinal()];
                    mayor = valor;
                }
                i++;
            } // if-else.  
        } // while.  
        candidato = mayor + 1;
        return (candidato);
    }  // getItemNoNuevoReg().  

    //---------------------------------------------------------------------------
    //---------------------------------------------------------------------------
    private String getIdRowNuevoReg() {
        LibreriaHP libreriaHP = new LibreriaHP();
        ModeloTablaGuia02 modeloTabla = (ModeloTablaGuia02) PanelGuiaDespacho.tablaDetalleGuia.getModel();
        ArrayList<Object[]> listaProductos;
        listaProductos = (ArrayList<Object[]>) modeloTabla.listaDetalleProductosGuia;
        Object[] fila = null;
        Integer i = 0;
        Double candidato = 0.00;
        Double mayor = 0.00;
        String valorStr = "";
        Double valor = 0.00;
        while (i < listaProductos.size()) {
            fila = listaProductos.get(i);
            valorStr = fila[COLUMNA.ID_ROW.ordinal()].toString();
            if (libreriaHP.esNumerico(valorStr)) {
                valor = Double.parseDouble(valorStr);
                if (candidato == valor) {
                    candidato++;
                    i = 0;
                } else {
                    if (valor > mayor) {
                        mayor = valor;
                    }
                    i++;
                } // if-else interno. 
            } else {
                i++;
            }// if-else externo.  
        } // while.  
        candidato = mayor + 1;
        return (candidato.toString());
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void agregarNuevoRegDetalle() {
        ModeloTablaGuia02 modeloTabla = (ModeloTablaGuia02) PanelGuiaDespacho.tablaDetalleGuia.getModel();
        ArrayList<Object[]> listaProductos = (ArrayList<Object[]>) modeloTabla.listaDetalleProductosGuia;
        //------------------------------------------------
        Integer itemNo = getItemNoNuevoReg();      // (*).  
        //------------------------------------------------
        String codigo = NUEVO_COD_ITEM;        //PanelAgregarDetalleGuia.txtCodigo.getText();
        String tipo = (String) PanelAgregarDetalleGuia.cbbTipoProducto.getSelectedItem();
        String descripcion = NUEVA_DESCRIPCION; // PanelAgregarDetalleGuia.txtDescripcion.getText();
        //Number pesoNumber = (Number) PanelAgregarDetalleGuia.txtPesoUni.getValue();
        Double peso = 0.00;  //pesoNumber.doubleValue();
        String nroPedido = null;
        Double cantidad = 0.00;   // cantidadNumber.doubleValue();
        String atados = null;     //PanelAgregarDetalleGuia.txtAtados.getText();
        Double items = 0.00;      // itemsNumber.doubleValue();
        Double precio = 0.00;     // precioNumber.doubleValue();
        Double alicuota = 0.00;   // alicuotaNumber.doubleValue();
        //-------------------------------------------
        String idRow = getIdRowNuevoReg();  // (*).  
        //-------------------------------------------
        Boolean facPeso = PanelAgregarDetalleGuia.facPeso(tipo);
        Boolean facUnidad = PanelAgregarDetalleGuia.facUnidad(tipo);
        Double monto = precio;
        if (facPeso) {
            monto = monto * peso;
        }
        if (facUnidad) {
            monto = monto * cantidad;
        }
        // --*set the fila *  
        Object[] fila;
        fila = new Object[]{itemNo,
            codigo,
            tipo,
            descripcion,
            peso,
            nroPedido,
            cantidad,
            atados,
            items,
            precio,
            alicuota,
            (cantidad * peso), // << Peso Guia.  
            facPeso, // Display Item: Fac x Peso en boleano.  
            facUnidad, // Display Item: Fac x Unidad en boleano.  
            monto, // Display Item: Total Monto.  
            "Conforme",
            "Eliminar",
            idRow};      // <<: A este nivel se desconoce su idRow x q No se ha hecho la inserción en la B.D. 
        listaProductos.add(fila);
        modeloTabla.fireTableDataChanged();   // Asegurarle a la Tabla q su List Model cambió.  
        /*
         Number totalPesoNumber = (Number) PanelGuiaDespacho.txtDisplayTotalPesoGuia.getValue();
         Double totalPeso = (totalPesoNumber == null ? 0.00 + (cantidad * peso) : totalPesoNumber.doubleValue() + (cantidad * peso));
         PanelGuiaDespacho.txtDisplayTotalPesoGuia.setValue(totalPeso);
         PanelGuiaDespacho.txtDisplayPesoNominal.setValue(totalPeso);
         Number totalMontoNumber = (Number) PanelGuiaDespacho.txtDisplayTotalMonto.getValue();
         Double totalMonto = (totalMontoNumber == null ? 0.00 + monto : totalMontoNumber.doubleValue() + monto);
         PanelGuiaDespacho.txtDisplayTotalMonto.setValue(totalMonto);
         */
    }  // agregarNuevoRegDetalle()

    //------------------------------------------------------------------------------
    //------------------------------------------------------------------------------
    public void incluirItemsTablaGuia02() {
        ModeloTablaGuia02 modeloTabla = (ModeloTablaGuia02) PanelGuiaDespacho.tablaDetalleGuia.getModel();
        ArrayList<Object[]> listaProductos = (ArrayList<Object[]>) modeloTabla.listaDetalleProductosGuia;
        Object[] fila;
        Integer itemNo = getItemNoNuevoReg();  // (*).  
        String codigo = PanelAgregarDetalleGuia.txtCodigo.getText();
        String tipo = (String) PanelAgregarDetalleGuia.cbbTipoProducto.getSelectedItem();
        String descripcion = PanelAgregarDetalleGuia.txtDescripcion.getText();
        Number pesoNumber = (Number) PanelAgregarDetalleGuia.txtPesoUni.getValue();
        Double peso = pesoNumber.doubleValue();
        String nroPedido = null;
        nroPedido = PanelAgregarDetalleGuia.txtPedido.getText();
        Number cantidadNumber = (Number) PanelAgregarDetalleGuia.txtCantidad.getValue();
        Double cantidad = cantidadNumber.doubleValue();
        String atados = PanelAgregarDetalleGuia.txtAtados.getText();
        Number itemsNumber = (Number) PanelAgregarDetalleGuia.txtItems.getValue();
        Double items = itemsNumber.doubleValue();
        Number precioNumber = (Number) PanelAgregarDetalleGuia.txtPrecioUni.getValue();
        Double precio = precioNumber.doubleValue();
        Number alicuotaNumber = (Number) PanelAgregarDetalleGuia.txtAlicuota.getValue();
        Double alicuota = alicuotaNumber.doubleValue();
        String idRow = getIdRowNuevoReg();  // (*).   
        Boolean facPeso = PanelAgregarDetalleGuia.facPeso(tipo);
        Boolean facUnidad = PanelAgregarDetalleGuia.facUnidad(tipo);
        Double monto = precio;
        if (facPeso) {
            monto = monto * peso;
        }
        if (facUnidad) {
            monto = monto * cantidad;
        }
        fila = new Object[]{itemNo,
            codigo,
            tipo,
            descripcion,
            peso,
            nroPedido,
            cantidad,
            atados,
            items,
            precio,
            alicuota,
            (cantidad * peso), // << Peso Guia.  
            facPeso, // Display Item: Fac x Peso en boleano.  
            facUnidad, // Display Item: Fac x Unidad en boleano.  
            monto, // Display Item: Total Monto.  
            "Conforme",
            "Eliminar",
            idRow};      // <<: A este nivel se desconoce su idRow x q No se ha hecho la inserción en la B.D. 
        listaProductos.add(fila);
        modeloTabla.fireTableDataChanged();   // Asegurarle a la Tabla q su List Model cambió.  
        Number totalPesoNumber = (Number) PanelGuiaDespacho.txtDisplayTotalPesoGuia.getValue();
        Double totalPeso = (totalPesoNumber == null ? 0.00 + (cantidad * peso) : totalPesoNumber.doubleValue() + (cantidad * peso));
        PanelGuiaDespacho.txtDisplayTotalPesoGuia.setValue(totalPeso);
        PanelGuiaDespacho.txtDisplayPesoNominal.setValue(totalPeso);
        Number totalMontoNumber = (Number) PanelGuiaDespacho.txtDisplayTotalMonto.getValue();
        Double totalMonto = (totalMontoNumber == null ? 0.00 + monto : totalMontoNumber.doubleValue() + monto);
        PanelGuiaDespacho.txtDisplayTotalMonto.setValue(totalMonto);
    }  // incluirItemsTablaGuia02().  
}  // ControladorAgregarDetalleGuia. ******************************************


/* *****************************************************************************
 * -----------------------------------------------------------------------------
 * NOTAS varias al 12 de Marzo 2013 a las 10:05 am:
 * /**************************** OTRO EJEMPLO de manipulacion de fechas:
 * java.sql.Date alFecha; //java.sql.Date alFecha = new java.sql.Date(
 * PanelGuiaDespacho.txtFechaGuia.getDate().getTime() ); if (
 * PanelGuiaDespacho.txtFechaGuia.getDate() == null ) { alFecha = new
 * java.sql.Date( new java.util.Date().getTime() ); // iniciar una Var
 * java.sql.Date con la fecha del Sistema. } // if else { alFecha = new
 * java.sql.Date( PanelGuiaDespacho.txtFechaGuia.getDate().getTime() ); }
 * **********************
 */
