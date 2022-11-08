/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.controlador;

import java.awt.Component;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

//public class CellEditorValueTablaGuia02 extends ModeloTablaGuia02 {
public class CellEditorValueTablaGuia02_old extends AbstractCellEditor implements TableCellEditor {

    private   JComponent component = new JTextField();
    private   ControladorDetalleGuia.COLUMNA nroColumna;  
    protected TableCellEditor editor;
       
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public CellEditorValueTablaGuia02_old(ControladorDetalleGuia.COLUMNA columna) {
    System.out.println("....................:CELL EDITOR VALUE..............(constructor),....");
        this.nroColumna = columna; 
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        //editor = (TableCellEditor)editors.get(new Integer(row));
        //if (editor == null) {
        //  editor = defaultEditor;
        //}
        //return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
        ((JTextField)component).setText(value.toString());  //coloca valor de la celda al JTextField
        return component;
    }

    // *************************************  .  
    @Override
    public Object getCellEditorValue() {
        System.err.println("dentro de getCelleditorValue.....");
        //return editor.getCellEditorValue();
        return ((JTextField)component).getText();
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        // selectEditor((MouseEvent) anEvent);
        return editor.isCellEditable(anEvent);
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        // selectEditor((MouseEvent) anEvent);
        return editor.shouldSelectCell(anEvent);
    }

    @Override
    public boolean stopCellEditing() {
    System.out.println("Dentro de STOP CELL EDITING VALUE,....");  
       return super.stopCellEditing(); 
    }
    
    @Override
    public void cancelCellEditing() {
        editor.cancelCellEditing();
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
        editor.addCellEditorListener(l);
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        editor.removeCellEditorListener(l);
    }
}
