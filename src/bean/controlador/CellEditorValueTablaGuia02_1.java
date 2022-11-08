/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * Clase SUJETA A REVISON ¿¿¿???.  ( Brqto: 10/10/2013 ). NO ESTA FUNCIONANDO CORRECTAMENTE. 
 * 
 */
package bean.controlador;
import static bean.controlador.ControladorDetalleGuia.COLUMNA.DESCRIPCION;
import static bean.controlador.ControladorDetalleGuia.COLUMNA.NO_PEDIDO;
import static bean.controlador.ControladorDetalleGuia.COLUMNA.PESO_UNI;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import vistas.PanelGuiaDespacho;

public class CellEditorValueTablaGuia02_1 extends AbstractCellEditor implements TableCellEditor {

    private   Object OldValor=null,
                     NuevoValor=null;
    private   JComponent component = new JFormattedTextField();
    private   ControladorDetalleGuia.COLUMNA columna;  
    // protected TableCellEditor editor;
       
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public CellEditorValueTablaGuia02_1(ControladorDetalleGuia.COLUMNA columna) {
      this.columna = columna; 
    }
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        OldValor=value;  
        ( (JFormattedTextField)component ).setText(value.toString());  //coloca valor de la celda al JFormattedTextField. 
        //System.out.println("Valor antes de cambiar="+getCellEditorValue()); 
        return component;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public Object getCellEditorValue() {
        return ((JFormattedTextField)component).getText();
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private boolean esNumerico( String valorStr ) {
        try {
           double d = Double.parseDouble(valorStr);
        }  // try.
        catch(NumberFormatException nfe)  {
              return false;
        }  // catch.  
        return true;  
    }  //
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void validarRenglones() {
       NuevoValor = getCellEditorValue();  
       String valorStr; 
       Double valor;   
       switch ( columna ) {
           case DESCRIPCION :
               valorStr = NuevoValor.toString();
               if ( valorStr==null || valorStr.isEmpty() ) ((JFormattedTextField)component).setText((String) OldValor);
               break;  
           case PESO_UNI :   // Sujeto a revision al 21/08/2013. 
               // ?? org.apache.commons.lang.StringUtils.isNumeric(). ??? //
               if (!esNumerico(NuevoValor.toString())) {  // garantizar que el String representa na cadena numerica. 
                   ((JFormattedTextField)component).setValue(OldValor);
               }
               else {   
                   valor = Double.parseDouble(NuevoValor.toString());
                   if (valor < 0 ) ((JFormattedTextField)component).setValue(OldValor);
               }  // if. 
               break;  
           case NO_PEDIDO : 
               valorStr = NuevoValor.toString();
               String p1 = PanelGuiaDespacho.txtPedido1.getText(); 
               String p2 = PanelGuiaDespacho.txtPedido2.getText();
               String p3 = PanelGuiaDespacho.txtPedido3.getText();
               String p4 = PanelGuiaDespacho.txtPedido4.getText();
               if ( !valorStr.equals(p1) && !valorStr.equals(p2) && !valorStr.equals(p3) && !valorStr.equals(p4) ) ( (JFormattedTextField) component).setText((String) OldValor);
               break; 
       }  // siwtch(). 
    }  // validarRenglones(). 
     
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public boolean stopCellEditing() {
       //System.out.println("Valor despues de cambiar="+getCellEditorValue());  
       validarRenglones(); 
       return super.stopCellEditing(); 
    }
    
}  // cellEditorValueTablaGuia02().  
