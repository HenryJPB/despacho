/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.cliente;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author henrypb
 */
public abstract class ModeloTablaCliente extends AbstractTableModel {

    private String[] columnas = {"Seleccionar", "Codigo", "Nombre/Razon Social"}; 
    //protected Class[] columnClasses = new Class[]{String.class, String.class, String.class};
    //private List<Cliente> clientes;
    public List<Cliente> clientes = new ArrayList<Cliente>();
    //public final Object[] longValues = {"seleccionar", "12345", "123456789012345678901234567890", new Integer(20), Boolean.TRUE};  // <: Ejemplo. 
    public final Object[] longValues = {"123seleccionar123", "12345", "123456789-123456789-123456789-"};  // definir ancho de columnas. 
    /*
    public ModeloTablaCliente() {
        clientes = new ArrayList<Cliente>();
        getClientes(); // refrescar tabla de Datos. 
    } // public ModeloTablaCliente.  
    */
    
    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = clientes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cliente.getSeleccionar();
            case 1:
                return cliente.getCodCliente();
            case 2:
                return cliente.getNombreCliente();
        }  // switch. 
        return cliente;
    }  // getValueAt. 

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    } // getColumnClass. 

    @Override
    public boolean isCellEditable(int row, int col) {
          if (col == 0 ) { // <-Solo se respetara NO-update la columna=0. 
             return true;
          }
          else {
             return false; 
          }
    }

    /**
     * Don't need to implement this method unless your table's* data can change.
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
        // no Implementado.  
    }
} // ModeloTablaCliente.  
