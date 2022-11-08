/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.cliente;

/**
 *
 * @author henrypb
 */
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 * @version 1.0 11/09/98
 */

public class BotonEditorCliente extends DefaultCellEditor {
    
    protected JButton button;
    private String label;
    private boolean isPushed;
    private enum ordinalColumnaTabla {SELECT,CODIGO,NOMBRE_CLIENTE};  
    public String codCliente;
    public String nombreCliente;  
    
    public BotonEditorCliente(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                //JOptionPane.showMessageDialog(button,"BYE!!!!");
            }
        });
    }

    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        isPushed = true;
        // La siguientes instrucciones funcionaron correctaente al 04/02/2013 a la 11:56.  
        //Integer ordinalCodCliente = ordinalColumnaTabla.CODIGO.ordinal(); 
        //Integer ordinalNombreCliente = ordinalColumnaTabla.NOMBRE_CLIENTE.ordinal(); 
        //codCliente = (String) table.getValueAt(row,ordinalCodCliente); 
        //nombreCliente = (String) table.getValueAt(row, ordinalNombreCliente);  
        return button;
    }

    //----------------------------------------------------------------------------
    // Este metodo aplica a los eventos de control del USUARIO:
    //----------------------------------------------------------------------------
    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            // ** Ejecutar accion del USUARIO:  
            //JOptionPane.showMessageDialog(button,"Codigo del Cliente="+codCliente);
            //PanelGuiaDespacho.txtCodCliente.setValue(codCliente);
            //PanelGuiaDespacho.txtRazonSocial.setText(nombreCliente);
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    @Override
    protected  void fireEditingStopped() {
        super.fireEditingStopped();
        //JOptionPane.showMessageDialog(button,"BYE!!!!");
    }
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void disposeFrameCliente() {
       // FrameCliente.this.   .this.setVisible(false); // o .dispose(). <<< ERROR ¿¿¿???
    }
}
