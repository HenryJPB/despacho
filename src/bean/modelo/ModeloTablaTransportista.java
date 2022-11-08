/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.modelo;

import bean.entidad.Guia03;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author henrypb
 */
public abstract class ModeloTablaTransportista extends AbstractTableModel  {

    private String[]       columnas = {"Cedula Id.", "Nombre/Razon Social"}; 
    public  List<Guia03>   transportistas = new ArrayList<Guia03>();
    public final Object[]  longValues = {"V123456789012", "123456789-123456789-123456789-"};  // definir ancho de columnas. 

    
    @Override
    public int getRowCount() {
        return transportistas.size();
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
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    } // getColumnClass. 

    @Override
    public boolean isCellEditable(int row, int col) {
          return (false);  
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Guia03 transportista = transportistas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return  transportista.getC3_CI_CHOFER(); 
            case 1:
                return  transportista.getC3_NOMBRE_CHOFER(); 
        }  // switch. 
        return (transportista);
    }  // getValueAt. 
    
    //--------------------------------------------------------------------------
    //  ??: El siguiente metodo NO es neceario ver metodo anterior e 
    //      implementalo como getValue( rowIndex, -1 ). Donde -1 = un valor
    //      fuera de rango que hace que se active la ultima isntruccion de este
    //      metodo.  
    //--------------------------------------------------------------------------
    public Object getRecordAt( int rowIndex ) {
           return ( transportistas.get(rowIndex) );  
    }
    
    /**
     * Don't need to implement this method unless your table's* data can change.
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
        // no Implementado.  
    }
    
}   // ModeloTablaTranspotista.  
