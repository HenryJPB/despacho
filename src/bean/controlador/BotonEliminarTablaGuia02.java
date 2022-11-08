/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.controlador;

/**
 *
 * @author henrypb
 */
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
 
/**
 * @version 1.0 11/09/98
 */
public class BotonEliminarTablaGuia02 extends DefaultCellEditor {
  protected JButton button;
  private   String  botonLabel,
                    valorObjetoLista;
  //private enum      COLUMNA {ITEM_NO,CODIGO,TIPO,DESCRIPCION,PESO,NO_PEDIDO,UNIDADES,ITEMS,ATADOS,PRECIO,PESO_GUIA,CONFORME,ELIMINAR,ID_ROW};  
  ControladorDetalleGuia.COLUMNA RENGLON; 
  private boolean   isPushed;
 
  public BotonEliminarTablaGuia02(JCheckBox checkBox) {
    super(checkBox);
    button = new JButton();
    button.setOpaque(true);
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
      }
    });
  }
  
  //----------------------------------------------------------------------------
  //----------------------------------------------------------------------------
  @Override
  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    if (isSelected) {
      button.setForeground(table.getSelectionForeground());
      button.setBackground(table.getSelectionBackground());
    } else{
      button.setForeground(table.getForeground());
      button.setBackground(table.getBackground());
    }
    botonLabel = (value ==null) ? "" : value.toString();
    button.setText( botonLabel );
    isPushed = true;
    getComponenteTabla( table, row );  
    return button;
  }
 
  //----------------------------------------------------------------------------
  //----------------------------------------------------------------------------
  private void getComponenteTabla(JTable tabla, int fila ) {
    // El siguiente ejemplo consiste en obtener el valor de una colunna de la tabla: 
      int indiceColumna = RENGLON.ID_ROW.ordinal();  
      valorObjetoLista = (String) tabla.getValueAt(fila, indiceColumna);    // valorObjetoLista es una Var. de ambito Global.  
  }
  
 //-----------------------------------------------------------------------------
 // Accionar cambios al presionar el presionar boton Eliminar sobre la tabla. 
 //-----------------------------------------------------------------------------
  @Override
  public Object getCellEditorValue() {
    if (isPushed)  {   // si el Usuario presina el boton.  
     // Ejemplo:    
     // JOptionPane.showMessageDialog(button ,"contenidoTabla(fila,column-IdRow)="+valorObjetoLista);   // Donde valor es una variable de ambito general.  
     /*  NOTA al 30/07/2013:  No se puede ejecutar "eliminarRegLista" a este nivel porque elimina el componente/boton eliminar de la lista antes
         de ejecutar el metodo requerido para detener el procese de edicion "fireEditingStopped".       
     // 
     ControladorDetalleGuia controladorDetalleGuia = new ControladorDetalleGuia();  
     String idRow = valorObjetoLista;  
     controladorDetalleGuia.eliminarRegListaProductos(idRow);
     //
     */  
    }
    isPushed = false;
    return botonLabel ;
  }
   
  //----------------------------------------------------------------------------
  //----------------------------------------------------------------------------
  @Override
  public boolean stopCellEditing() {
    isPushed = false;
    return super.stopCellEditing();
  }
  
  //----------------------------------------------------------------------------
  //----------------------------------------------------------------------------
  @Override
  protected void fireEditingStopped() {
    super.fireEditingStopped();      // El usuario Edito el Registro y requiere finalizar esta tarea. 
                                     // Finalizado el momento que usuario libero el boton ->se-> ejecuta la accion:  
    ControladorDetalleGuia controladorDetalleGuia = new ControladorDetalleGuia();  
    String idRow = valorObjetoLista;  
    controladorDetalleGuia.eliminarRegListaProductos(idRow);
  }
}  // BotonEliminarTablaGuia02().  
