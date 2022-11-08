/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import bean.entidad.Guia03;
import bean.modelo.ModeloTablaTransportista;
import bean.servicio.ServicioAdministracionGuia03;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.*;

/**
 *
 * @author henrypb
 */
public class PanelLovTransportista extends javax.swing.JPanel {

    public JButton btnConforme; 
    public JButton btnCancelar; 
    ModeloTablaTransportista modeloTablaTransportista; 
    /**
     * Creates new form PanelLovTransportista
     */
    public PanelLovTransportista() {
        iniciarComponentes(); 
        iniciarTablaTransportistas(); 
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
        CEDULA_TRANSPORTISTA, NOMBRE_TRANSPORTISTA
    }
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void iniciarTablaTransportistas() {
        JTableHeader header = this.tablaTransportista.getTableHeader();  
        header.setBackground(Color.black);
        header.setForeground(Color.yellow);
        header.setFont(new Font("Monospaced",Font.BOLD,13));
        modeloTablaTransportista = new ModeloTablaTransportista() {}; 
        cargarDatosTransportistas( modeloTablaTransportista.transportistas ); 
        this.tablaTransportista.setModel(modeloTablaTransportista);
        this.tablaTransportista.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //iniciarColumnSizes( this.tablaClientes ); 
        ajustarColumnSizes(this.tablaTransportista ); 
        //setUpBotonSeleccionar();  
        ordenarTablaTransportista(); 
    }  // iniiciarTablaTransportista. 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void ajustarColumnSizes(JTable tabla ) {
        TableColumn column;
        for (int i = 0; i < modeloTablaTransportista.getColumnCount(); i++) {
            column = tabla.getColumnModel().getColumn(i);
            switch (i) {
                case 0: {
                    column.setPreferredWidth(100);
                    break; 
                }
                case 1: {
                    column.setPreferredWidth(320);
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
        ModeloTablaTransportista model = (ModeloTablaTransportista) tabla.getModel();
        TableColumn column;
        Component comp;
        int headerWidth;
        int cellWidth;
        Object[] longValues = model.longValues;
        TableCellRenderer headerRenderer = tabla.getTableHeader().getDefaultRenderer();
        for (int i = 0; i < modeloTablaTransportista.getColumnCount(); i++) {
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
    private void cargarDatosTransportistas(List<Guia03> transportistas ) {
        ServicioAdministracionGuia03 servicioAdministracionGuia03 = new ServicioAdministracionGuia03(); 
        for ( Guia03 transportista : servicioAdministracionGuia03.getTransportistasActivos()) {
              transportistas.add(transportista); 
        }  // for 
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void ordenarTablaTransportista() {
         ModeloTablaTransportista tablaOrdenadaTransportista = (ModeloTablaTransportista) this.tablaTransportista.getModel(); 
         RowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(tablaOrdenadaTransportista); 
         this.tablaTransportista.setRowSorter(ordenador);  
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
        tablaTransportista = new javax.swing.JTable();
        sppBotonera = new javax.swing.JSplitPane();

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/logoDesica16px.png"))); // NOI18N
        jLabel1.setBounds(10, 10, 38, 46);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel2.setText("L.O.V. TRANSPORTISTA ACTIVOS");
        jLabel2.setBounds(100, 20, 255, 17);
        jLayeredPane1.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        tablaTransportista.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tablaTransportista.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaTransportista);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(120, Short.MAX_VALUE)
                .addComponent(sppBotonera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sppBotonera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane sppBotonera;
    public javax.swing.JTable tablaTransportista;
    // End of variables declaration//GEN-END:variables
}
