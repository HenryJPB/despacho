/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.modelo;

import bean.entidad.Guia04;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author henrypb
 * Modelos Tabla Tipo Transporte / Unidad Tipo Transporte <--> Tipo Camion.  
 * 
 */
public abstract class ModeloTablaUnidadTransporte extends AbstractTableModel {
    
    private String[]       columnas = {"Cod Unidad", "Tipo","Color","Nro Ejes","Cap. (tn)","Placa Chuto","Placa Batea"}; 
    public  List<Guia04>   unidadesTransporte = new ArrayList<Guia04>();
    public final Object[]  longValues = {"12345678", "1234567890","1234567890","12345678","12345678","12345678","12345678"};  // definir ancho de columnas. 
    
    @Override
    public int getRowCount() {
        return unidadesTransporte.size();
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
        Guia04 tipoTransporte = unidadesTransporte.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return  ( tipoTransporte.getC4_COD_CAMION()!=null?tipoTransporte.getC4_COD_CAMION():"" );   
            case 1:
                return  ( tipoTransporte.getC4_TIPO_CAMION()!=null?tipoTransporte.getC4_TIPO_CAMION():"" );  
            case 2:
                return  ( tipoTransporte.getC4_COLOR()!=null?tipoTransporte.getC4_COLOR():"" ); 
            case 3:
                return  ( tipoTransporte.getC4_NO_EJES()!=null?tipoTransporte.getC4_NO_EJES():"" ); 
            case 4:
                return  ( tipoTransporte.getC4_CAPACIDAD()!=null?tipoTransporte.getC4_CAPACIDAD():"" );  
            case 5: 
                return  ( tipoTransporte.getC4_PLACA_CHUTO()!=null?tipoTransporte.getC4_PLACA_CHUTO():"" );  
            case 6:
                return  ( tipoTransporte.getC4_PLACA_BATEA()!=null?tipoTransporte.getC4_PLACA_BATEA():"" );   
        }  // switch. 
        return (tipoTransporte);
    }  // getValueAt. 
    
    //--------------------------------------------------------------------------
    //  ??: El siguiente metodo NO es neceario ver metodo anterior e 
    //      implementalo como getValue( rowIndex, -1 ). Donde -1 = un valor
    //      fuera de rango que hace que se active la ultima isntruccion de este
    //      metodo.  
    //--------------------------------------------------------------------------
    public Object getRecordAt( int rowIndex ) {
           return ( unidadesTransporte.get(rowIndex) );  
    }
    
    /**
     * Don't need to implement this method unless your table's* data can change.
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
        // no Implementado.  
    }
}   