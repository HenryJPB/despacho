/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.controlador;

import static bean.controlador.ControladorDetalleGuia.COLUMNA.CANTIDAD;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import javax.swing.AbstractCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellEditor;
import vistas.PanelGuiaDespacho;

/**
 *
 * @author henrypb Creado : 10-10-2013.
 *
 */
public class CellEditorNumerico extends AbstractCellEditor implements TableCellEditor {

    private Number oldValor = null;
    private Number nuevoValor = null;
    private int fila;
    private String formatoNumerico;
    private JFormattedTextField celda = new JFormattedTextField();
    private ControladorDetalleGuia.COLUMNA columna;  // atributo NO considerada 

    //--------------------------------------------------------------------------
    // Constructor de la clase
    // Fecha: 10/10/2013.   
    // NOTA: ejemplo de pase de valor a una clase. 
    //--------------------------------------------------------------------------
    public CellEditorNumerico(ControladorDetalleGuia.COLUMNA columna, String fn) {
        this.columna = columna;
        this.formatoNumerico = fn;
        celda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Object getCellEditorValue() {
        //return ( (JFormattedTextField) editor ).getValue() ;  
        //return new Integer(getValue());
        //return Double.parseDouble(editor.getText()); // .getValue(); 
        return ((JFormattedTextField) celda).getValue();
    }  // getCellEditorValue.  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        oldValor = (Number) value;
        fila = row;
        //JFormattedTextField editor = (JFormattedTextField) super.getTableCellEditorComponent(table, value, isSelected, row, column);
        /**
         * * Originalmente asi (Brqto: 10/10/2013): if (value!=null){
         * DecimalFormat numberFormat = new
         * DecimalFormat("#,##0.00;(#,##0.00)"); editor.setFormatterFactory(new
         * javax.swing.text.DefaultFormatterFactory(new
         * javax.swing.text.NumberFormatter(numberFormat))); Number num =
         * (Number) value; String text = numberFormat.format(num);
         * editor.setHorizontalAlignment(SwingConstants.RIGHT);
         * editor.setText(text); } return editor;
         */
        //Si values es nulo dara problemas de renderizado, por lo tanto se pone vacio
        //JLabel celda = new JLabel(value == null ? "" : value.toString());
        //JFormattedTextField celda = new JFormattedTextField(value == null ? "" : value.toString());
        //JFormattedTextField celda = new JFormattedTextField();
        if (value instanceof String) {
            celda.setText(value.toString());
        } else if (value instanceof Integer) {
            celda.setValue(value);
            celda.setHorizontalAlignment(SwingConstants.CENTER);
        } else if (value instanceof BigDecimal || value instanceof Double || value instanceof Float) {
            celda.setValue(value);
            celda.setHorizontalAlignment(SwingConstants.RIGHT);
            //celda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###,###,##0.00"))));
            celda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(formatoNumerico))));
        }
        /*
         if (row == 1) {
         lbl.setHorizontalAlignment(SwingConstants.RIGHT); //alina a la izquierda
         }
         if (row == 2) {
         lbl.setForeground(Color.BLUE); //fuente azul
         }
         if (column == 1 & row != 2) { //color de fondo
         lbl.setOpaque(true);
         lbl.setBackground(Color.YELLOW);
         }
         */
        if (isSelected) {
            //celda.setOpaque(true);
            //celda.setBackground(Color.YELLOW);
            //celda.setBackground(Estilos.colorCelda);
        }
        return celda;
    }  // getTableCellEditorComponent.

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void actualizarPeso(Double peso, Double cantidad) {
        Double old_totalPesoColumna = (Double) PanelGuiaDespacho.tablaDetalleGuia.getValueAt(fila, ControladorDetalleGuia.COLUMNA.TOTAL_PESO.ordinal());
        Double totalPeso = peso * cantidad;
        PanelGuiaDespacho.tablaDetalleGuia.setValueAt(totalPeso, fila, ControladorDetalleGuia.COLUMNA.TOTAL_PESO.ordinal());
        Double totalPesoGuia = (Double) PanelGuiaDespacho.txtDisplayTotalPesoGuia.getValue();
        totalPesoGuia = totalPesoGuia - old_totalPesoColumna;
        totalPesoGuia = totalPesoGuia + totalPeso;
        PanelGuiaDespacho.txtDisplayTotalPesoGuia.setValue(totalPesoGuia);
        PanelGuiaDespacho.txtDisplayPesoNominal.setValue(totalPesoGuia);
    }  // actualizarPeso().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void actualizarMonto(Double peso, Double cantidad, Double precio) {
        Double old_totalMontoColumna = (Double) PanelGuiaDespacho.tablaDetalleGuia.getValueAt(fila, ControladorDetalleGuia.COLUMNA.TOTAL_MONTO.ordinal());
        //
        Double totalMontoGuia = (Double) PanelGuiaDespacho.txtDisplayTotalMonto.getValue();
        totalMontoGuia = totalMontoGuia - old_totalMontoColumna;
        Double totalMontoColumna = precio;
        Boolean facXpeso = (Boolean) PanelGuiaDespacho.tablaDetalleGuia.getValueAt(fila, ControladorDetalleGuia.COLUMNA.FAC_PESO.ordinal());
        if (facXpeso) {
            totalMontoColumna = totalMontoColumna * peso;
        }
        Boolean facXunidad = (Boolean) PanelGuiaDespacho.tablaDetalleGuia.getValueAt(fila, ControladorDetalleGuia.COLUMNA.FAC_UNIDAD.ordinal());
        if (facXunidad) {
            totalMontoColumna = totalMontoColumna * cantidad;
        }
        PanelGuiaDespacho.tablaDetalleGuia.setValueAt(totalMontoColumna, fila, ControladorDetalleGuia.COLUMNA.TOTAL_MONTO.ordinal());
        totalMontoGuia = totalMontoGuia + totalMontoColumna;
        PanelGuiaDespacho.txtDisplayTotalMonto.setValue(totalMontoGuia);  // (*)
    }  // actualizarMonto(). 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void validarDatos() {
        nuevoValor = (Number) getCellEditorValue();
        if (nuevoValor == null) {
            nuevoValor = 0.00;
        } else {
            nuevoValor = Math.abs(nuevoValor.doubleValue());
        }  // if-else. 
        ((JFormattedTextField) celda).setValue(nuevoValor);
        ControladorDetalleGuia.COLUMNA colPeso = ControladorDetalleGuia.COLUMNA.PESO_UNI;
        ControladorDetalleGuia.COLUMNA colCantidad = ControladorDetalleGuia.COLUMNA.CANTIDAD;
        ControladorDetalleGuia.COLUMNA colPrecio = ControladorDetalleGuia.COLUMNA.PRECIO;
        if (columna == colPeso || columna == colCantidad || columna == colPrecio) {
            // iniciar atributos de las variables:
            Double peso = (Double) PanelGuiaDespacho.tablaDetalleGuia.getValueAt(fila, colPeso.ordinal());
            Double cantidad = (Double) PanelGuiaDespacho.tablaDetalleGuia.getValueAt(fila, colCantidad.ordinal());
            Double precio = (Double) PanelGuiaDespacho.tablaDetalleGuia.getValueAt(fila, colPrecio.ordinal());
            switch (columna) {
                case PESO_UNI: {
                    peso = (Double) nuevoValor;
                    break;
                }  // PESO_UNI.
                case CANTIDAD: {
                    cantidad = (Double) nuevoValor;
                    break;
                }  // case CANTIDAD. 
                case PRECIO: {
                    precio = (Double) nuevoValor;
                    break;
                }
            }  // swicth.
            actualizarPeso(peso, cantidad);
            actualizarMonto(peso, cantidad, precio);
        }  // if-else. 
    }  // validar().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    protected void fireEditingStopped() {
        validarDatos();
        super.fireEditingStopped();
    }
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public boolean stopCellEditing() {
        //System.out.println("Valor despues de cambiar="+getCellEditorValue());  
        //validarDatos();
        return super.stopCellEditing();
    }
} // CellEditorNumerico.  
