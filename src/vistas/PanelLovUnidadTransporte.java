/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import bean.entidad.Guia04;
import bean.modelo.ModeloTablaUnidadTransporte;
import bean.servicio.ServicioAdministracionGuia04;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.*;

/**
 *
 * @author henrypb
 */
public class PanelLovUnidadTransporte extends javax.swing.JPanel {

    public JButton btnConforme; 
    public JButton btnCancelar; 
    ModeloTablaUnidadTransporte modeloTablaUnidadTransporte; 
    
    List<Guia04> unidades = new ArrayList<Guia04>(); 
    /**
     * Creates new form PanelLovTransportista
     */
    public PanelLovUnidadTransporte() {
        iniciarComponentes(); 
        iniciarTablaUnidadTransporte(); 
        //cargarDatosUnidadesTransporte(unidades); 
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void iniciarComponentes() {
        initComponents();
        btnConforme = new JButton("Conforme");  
        btnCancelar = new JButton("Cancelar"); 
        sppBotonera.setLeftComponent(btnConforme);
        sppBotonera.setRightComponent(btnCancelar);
    }
    
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public enum columnas {
        COD_UNIDAD, TIPO_UNIDAD, COLOR, NRO_EJES, CAPACIDAD, PLACA_CHUTO, PLACA_BATEA 
    }
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void iniciarTablaUnidadTransporte() {
        JTableHeader header = this.tablaUnidadTransporte.getTableHeader();  
        header.setBackground(Color.black);
        header.setForeground(Color.yellow);
        header.setFont(new Font("Monospaced",Font.BOLD,13));
        modeloTablaUnidadTransporte = new ModeloTablaUnidadTransporte() {}; 
        cargarDatosUnidadesTransporte( modeloTablaUnidadTransporte.unidadesTransporte ); 
        this.tablaUnidadTransporte.setModel(modeloTablaUnidadTransporte);
        this.tablaUnidadTransporte.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        ajustarColumnSizes(this.tablaUnidadTransporte ); 
        //setUpBotonSeleccionar();  
        ordenarTabla(); 
    }  // iniciarTablaUnidadTransporte. 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void ajustarColumnSizes(JTable tabla ) {
        TableColumn column;
        for (int i = 0; i < modeloTablaUnidadTransporte.getColumnCount(); i++) {
            column = tabla.getColumnModel().getColumn(i);
            switch (i) {
                case 0: {  // Cod Camion
                    column.setPreferredWidth(80);
                    break; 
                }
                case 1: {  // Tipo Camion
                    column.setPreferredWidth(100);
                    break; 
                }
                case 2: {  // color.  
                    column.setPreferredWidth(100);
                    break;  
                }
                case 3: {  // Nro Ejes
                    column.setPreferredWidth(100);
                    break;  
                }
                case 4: { // capacidad
                    column.setPreferredWidth(100);
                    break; 
                }
                case 5: { // placa Chuto.  
                    column.setPreferredWidth(100);
                    break;  
                }
                case 6: { // pĺace Batea. 
                    column.setPreferredWidth(100);
                    break;  
                }
            }  // switch. 
        }  // for.
    }  // ajustarColumnSizes. 
    
    //--------------------------------------------------------------------------
    // NOTA: este metodo funciono correctamente el dia: 31/01/2013 a las 15:46.  
    //--------------------------------------------------------------------------
    /** This method picks good column sizes.* If all column heads are wider than the column's cells'* contents, then you can just use column.sizeWidthToFit().*/
    private void iniciarColumnSizes(JTable tabla) {
        ModeloTablaUnidadTransporte model = (ModeloTablaUnidadTransporte) tabla.getModel();
        TableColumn column;
        Component comp;
        int headerWidth;
        int cellWidth;
        Object[] longValues = model.longValues;
        TableCellRenderer headerRenderer = tabla.getTableHeader().getDefaultRenderer();
        for (int i = 0; i < modeloTablaUnidadTransporte.getColumnCount(); i++) {
            column = tabla.getColumnModel().getColumn(i);
            comp = headerRenderer.getTableCellRendererComponent(null, column.getHeaderValue(),false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;
            comp = tabla.getDefaultRenderer(model.getColumnClass(i)).getTableCellRendererComponent(tabla, longValues[i],false, false, 0, i);
            cellWidth = comp.getPreferredSize().width; 
            column.setPreferredWidth(Math.max(headerWidth, cellWidth)); 
        }
    } //initColumnSizes. 
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void cargarDatosUnidadesTransporte(List<Guia04> unidadesTransporte ) {
        String ciTransportista = PanelGuiaDespacho.txtCedulaTransportista.getText();  
        String codTransporte = PanelGuiaDespacho.txtCodTransporte.getText();  
        ServicioAdministracionGuia04 servicioAdministracionGuia04 = new ServicioAdministracionGuia04(); 
        for ( Guia04 unidadTransporte : servicioAdministracionGuia04.getLovUnidadesTransporte(ciTransportista,codTransporte) ) {
              unidadesTransporte.add(unidadTransporte); 
        }  // for 
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void ordenarTabla() {
         ModeloTablaUnidadTransporte tablaOrdenadaUnidadTransporte = (ModeloTablaUnidadTransporte) this.tablaUnidadTransporte.getModel(); 
         RowSorter<TableModel> tablaOrdenada = new TableRowSorter<TableModel>(tablaOrdenadaUnidadTransporte); 
         this.tablaUnidadTransporte.setRowSorter(tablaOrdenada);  
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
        tablaUnidadTransporte = new javax.swing.JTable();
        sppBotonera = new javax.swing.JSplitPane();

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/logoDesica16px.png"))); // NOI18N
        jLabel1.setBounds(10, 30, 50, 46);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel2.setText("L.O.V. UNIDAD(ES) TRANSPORTE");
        jLabel2.setBounds(60, 20, 320, 17);
        jLayeredPane1.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        tablaUnidadTransporte.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tablaUnidadTransporte.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaUnidadTransporte);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(142, Short.MAX_VALUE)
                .addComponent(sppBotonera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sppBotonera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane sppBotonera;
    public javax.swing.JTable tablaUnidadTransporte;
    // End of variables declaration//GEN-END:variables
}
