/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.inventFacturacion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.*;
import vistas.PanelGuiaDespacho;

/**
 *
 * @author henrypb NOTA al 13-03-2013: Este objeto (PanelLovProductos) es
 * implantado utilizando la Clase DefaultTableModel.
 *
 */
public class PanelLovProductos extends javax.swing.JPanel {

    public JButton btnConforme;
    public JButton btnCancelar;
    DefaultTableModel modeloTablaProductos;

    /**
     * Creates new form PanelLovTransportista
     */
    public PanelLovProductos() {
        iniciarComponentes();
        iniciarTablaProductos();
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void iniciarComponentes() {
        initComponents();
        btnConforme = new JButton("Seleccionar");
        btnCancelar = new JButton("Cancelar");
        sppBotonera.setLeftComponent(btnConforme);
        sppBotonera.setRightComponent(btnCancelar);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public enum columnas {

        COD_PRODUCTO, TIPO_PRODUCTO, DESCRIPCION
    }

    private void iniciarTablaProductos() {
        String[] columnNames = {"Codigo", "Tipo", "Descripcion"};
        Object[][] datos = {};
        modeloTablaProductos = new DefaultTableModel(datos, columnNames) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        JTableHeader header = this.tablaProductos.getTableHeader();
        header.setBackground(Color.black);
        header.setForeground(Color.yellow);
        header.setFont(new Font("Monospaced", Font.BOLD, 13));
        cargarDatosTablaProductos();
        this.tablaProductos.setModel(modeloTablaProductos);
        this.tablaProductos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        setColumnSizes(this.tablaProductos);
        ordenarTabla();
    }  // iniciarTablaFleteDestino(); 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void cargarDatosTablaProductos() {
        final int noColunnas = 3;
        //Object dato[]; // = new Object[noColunnas];   // Donde n=Nro de Objetos columnas
        Object dato[]; // 
        String nroOrden = PanelGuiaDespacho.txtOrden1.getText();
        if (!nroOrden.isEmpty()) {   // Se trata de un Despacho de Prod Especiales.  
            ServicioAdministracionInv04 servicioAdministracionInv04 = new ServicioAdministracionInv04();
            for (Inv04 producto : servicioAdministracionInv04.getListaProductosEspeciales(nroOrden)) {
                dato = new Object[]{producto.getC4_CODIGO(), producto.getC4_TIPO_PROD(), producto.getDescripcion()};
                modeloTablaProductos.addRow(dato);
            }  // for.  
        } else {    // Despacho de Prod Estandard. 
            ServicioAdministracionInv01 servicioAdministracionInv01 = new ServicioAdministracionInv01();
            for (Inv01 producto : servicioAdministracionInv01.getListaProductosStandard()) {
                dato = new Object[]{producto.getC1_CODIGO(), producto.getC1_TIPO(), producto.getC1_DESCRIPCION()};
                modeloTablaProductos.addRow(dato);
            }  // for.  
        }      // if-else- 
    }  // cargarDatosTablaProductos().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void setColumnSizes(JTable tabla) {
        //ModeloTablaUnidadTransporte model = (ModeloTablaUnidadTransporte) tabla.getModel();
        TableModel model = tabla.getModel();
        TableColumn column;
        Component comp;
        int headerWidth;
        int cellWidth;
        //Object[] longValues = model.longValues;
        Object[] longValues = {"1234567890123", "1234", "1234567890-1234567890-1234567890-1234567890-123"};
        TableCellRenderer headerRenderer = tabla.getTableHeader().getDefaultRenderer();
        for (int i = 0; i < modeloTablaProductos.getColumnCount(); i++) {   // (**)  
            column = tabla.getColumnModel().getColumn(i);
            comp = headerRenderer.getTableCellRendererComponent(null, column.getHeaderValue(), false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;
            comp = tabla.getDefaultRenderer(model.getColumnClass(i)).getTableCellRendererComponent(tabla, longValues[i], false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;
            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
    } //  setColumnSizes().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void ordenarTabla() {
        TableModel modeloTablaOrdenada = this.tablaProductos.getModel();
        RowSorter<TableModel> tablaOrdenada = new TableRowSorter<TableModel>(modeloTablaOrdenada);
        this.tablaProductos.setRowSorter(tablaOrdenada);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        sppBotonera = new javax.swing.JSplitPane();

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/logoDesica16px.png"))); // NOI18N
        jLabel1.setBounds(20, 10, 60, 46);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel2.setText("L.O.V. MATERIALES");
        jLabel2.setBounds(140, 20, 160, 17);
        jLayeredPane1.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        tablaProductos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaProductos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(sppBotonera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sppBotonera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane sppBotonera;
    public javax.swing.JTable tablaProductos;
    // End of variables declaration//GEN-END:variables
}
