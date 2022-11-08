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
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;
 
/**
 * @version 1.0 11/09/98
 */
public class BotonRendererCliente extends JButton implements TableCellRenderer {
 
  public BotonRendererCliente() {
    setOpaque(true);
  }
  
    @Override
  public Component getTableCellRendererComponent(JTable table, Object value,
                   boolean isSelected, boolean hasFocus, int row, int column) {
    //setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilitario/cliente/checkMark16px.png")));
    if (isSelected) {
      setForeground(table.getSelectionForeground());
      setBackground(table.getSelectionBackground());
    } else{
      setForeground(table.getForeground());
      setBackground(UIManager.getColor("Button.background"));
    }
    setText( (value ==null) ? "" : value.toString() );
    return this;
  }
}
