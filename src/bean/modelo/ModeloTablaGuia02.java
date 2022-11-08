/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.modelo;

import bean.controlador.ControladorDetalleGuia;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author henrypb Modelos Tabla Tipo Transporte / Unidad Tipo Transporte <-->
 * Tipo Camion.
 *
 */
public abstract class ModeloTablaGuia02 extends AbstractTableModel {

    ControladorDetalleGuia.COLUMNA RENGLON;  
    //private enum RENGLON {
    // ITEM_NO, CODIGO, TIPO, DESCRIPCION, PESO_UNI, NO_PEDIDO, CANTIDAD, ATADOS, ITEMS, PRECIO,ALICUOTA,TOTAL_PESO, BOTON_ACTUALIZAR, BOTON_ELIMINAR, ID_ROW }
    //   1        2        3          4            5       6         7      8         9     10      11        12              13           14            15
    //                              1        2        3        4            5           6         7       8        9       10           11         12        13     14        15            16           17     18  
    private String[] columnas = {"Item", "Codigo", "Tipo","Descripcion", "Peso/Uni", "Pedido", "Cantidad","Atados", "Items", "Precio"," Alicuota","Total Peso"," FxP" ,"FxU", "Total Monto","Actualizar", "Accion", ""};   // ocultar la columna idRow.  
    public List<Object[]> listaDetalleProductosGuia = new ArrayList<Object[]>();   // Fue sustituida por arregloDetalleProductosGuia para incorporar los botones [Actualizar] y [Eliminar].   
    public Object[][] arregloDetalleProductosGuia = {};
    public Object[] longValues = {1234, "123456789012345", "1234","123456789-123456789-123456789-123456789",9999999.999, "1234567890", 9999999.999, "1234", 99999.999, 99999998.99,9999.99,9999999999.99,Boolean.FALSE,Boolean.FALSE,9999999999.99,"12345678901234","123456789012",""};  // :definir ancho y clase de la columna. (String/Value). 
    //                           (ItemNo),  (Codigo)       (Tipo)          (Descripcion)                    (Peso)        (Pedido)      (Cant)    (Atados)  (Items)    (Precio)   (Alicu)   (Total P)     (Fac x Peso)  (Fac x Unid)  (Total M)    (btnActualizar)   (btnEliminar) (idRow)                               
    //                               1         2             3                  4                             5             6            7          8        9           10        11         12            13           14             15              16          17            18  
    @Override
    public int getRowCount() {
        return listaDetalleProductosGuia.size();
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
        //return ( getValueAt(0, c).getClass() );  // fila 0 ????.  << ERROR >>.  Ajustado en la sig. Linea el 03/10/13.  
        return ( longValues[c].getClass() ); 
    } // getColumnClass. 

    @Override
    public boolean isCellEditable(int row, int col) {
        if ( col == RENGLON.CODIGO.ordinal() || col==RENGLON.TIPO.ordinal() || col== RENGLON.ALICUOTA.ordinal() ||col == RENGLON.TOTAL_PESO.ordinal() || col==RENGLON.FAC_PESO.ordinal() || col==RENGLON.FAC_UNIDAD.ordinal() ||col== RENGLON.TOTAL_MONTO.ordinal() ) {
                   return (false);
        } else {
                   return (true);
        }  // if-else. 
    }  // isCellEditable.  

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (listaDetalleProductosGuia.size() > 0) {
            Object[] detalleGuia = listaDetalleProductosGuia.get(rowIndex);
            return (detalleGuia[columnIndex]);
        } else {
            return (null);
        }
    }  // getValueAt. 

    //--------------------------------------------------------------------------
    //  ??: El siguiente metodo NO es neceario. Ver metodo anterior e 
    //      implementalo como getValue( rowIndex, -1 ). Donde -1 = un valor
    //      fuera de rango que hace que se active la ultima isntruccion de este
    //      metodo.  
    //--------------------------------------------------------------------------
    public Object getRecordAt(int rowIndex) {
        return (listaDetalleProductosGuia.get(rowIndex));
    }

    /**
     * Don't need to implement this method unless your table's* data can change.
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
        // Someter 'value' a algun proceso de validacion antes de alterar el valor en la tabla:  
        if (col == 3) {
            String descripcion = (String) value;  // col de Descripcion.    
            if (descripcion.length() > 0) {
                listaDetalleProductosGuia.get(row)[col] = value;
                fireTableCellUpdated(row, col);   //  forzar Actualizar el nuevo valor sobra la tabla.  
            }
        }
        else {
            listaDetalleProductosGuia.get(row)[col] = value;
            fireTableCellUpdated(row, col);   //  forzar Actualizar el nuevo valor sobra la tabla.  
        }
    }  // setValueAt().

    //---------------------------------------------------------------------------
    // NOTA al 31/07/2013: funcionó con Errores ???.  
    //---------------------------------------------------------------------------
    public void limpiarLista() {
        listaDetalleProductosGuia.clear();
        fireTableDataChanged();       // ???.  
    }

    //--------------------------------------------------------------------------
    // Esto NO esta funcionando,.........(01/08/2013)..............
    //--------------------------------------------------------------------------
    /*
    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        //String columnName = model.getColumnName(column);
        //model = 
        //Object data = model.getValueAt(row, column);
        Object[] detalleGuia = listaDetalleProductosGuia.get(row);
        //System.out.println("dentro del TableChanged valor=" +detalleGuia[column]);
        // *****Do something with the data <<Aqui>>..****
    }
    */
    
}   // ModeloTablaDetalleGuia.  