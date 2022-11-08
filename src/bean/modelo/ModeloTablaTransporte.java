/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.modelo;

import bean.entidad.Guia05;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author henrypb
 * NOTA: 
 * 
 */
public abstract class ModeloTablaTransporte extends AbstractTableModel {
    
    private String[]       columnas = {"Cod Transp", "Nombre Transporte"}; 
    public  List<Guia05>   transportes = new ArrayList<Guia05>();
    public final Object[]  longValues = {"*12345*", "123456789-123456789-123456789-"};  // definir ancho de columnas. 

    @Override
    public int getRowCount() {
        return transportes.size();
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
        Guia05 transporte = transportes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return  transporte.getC5_COD_TRANSPORTE();  
            case 1:
                return  transporte.getC5_NOMBRE_TRANSP();  
             }  // switch. 
        return (transporte);
    }  // getValueAt. 
    
    //--------------------------------------------------------------------------
    //  ??: El siguiente metodo NO es neceario ver metodo anterior e 
    //      implementalo como getValue( rowIndex, -1 ). Donde -1 = un valor
    //      fuera de rango que hace que se active la ultima isntruccion de este
    //      metodo.  
    //--------------------------------------------------------------------------
    public Object getRecordAt( int rowIndex ) {
           return ( transportes.get(rowIndex) );  
    }
    
    /**
     * Don't need to implement this method unless your table's* data can change.
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
        // no Implementado.  
    }
}  // ModeloTablaTipoTransporte.  
