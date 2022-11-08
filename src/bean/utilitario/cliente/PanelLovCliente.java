/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.cliente;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.*;


/**
 *
 * @author henrypb
 */
public class PanelLovCliente extends javax.swing.JPanel {
    
     final Integer cantColumnas = 3;   // declarar valor constante. 
    /**
     * Creates new form PanelLovCliente
     */
    public PanelLovCliente() {
        initComponents();
        //setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);  // Aplica solo a este contexto,de lo contrario se cerraria toda la aplicacion "by default". 
        iniciarTablaClientes();
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public enum columnas {
        SELECCIONAR, CODIGO_CLIENTE, NOMBRE_CLIENTE
    }
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void iniciarTablaClientes() {
        JTableHeader header = this.tablaClientes.getTableHeader();  
        header.setBackground(Color.black);
        header.setForeground(Color.yellow);
        header.setFont(new Font("Monospaced",Font.BOLD,13));
        ModeloTablaCliente modeloTablaClientes = new ModeloTablaCliente() {};
        //cargarDatosClientes( modeloTablaClientes.clientes ); 
        modeloTablaClientes.clientes = (List<Cliente>) cargarDatosClientes();  
        this.tablaClientes.setModel(modeloTablaClientes);
        this.tablaClientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //iniciarColumnSizes( this.tablaClientes ); 
        ajustarColumnSizes(this.tablaClientes ); 
        setUpBotonSeleccionar();  
        ordenarTablaClientes(); 
    }  // loadTablaClientes. 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void ajustarColumnSizes(JTable tabla ) {
        TableColumn column;
        for (int i = 0; i < cantColumnas; i++) {
            column = tabla.getColumnModel().getColumn(i);
            switch (i) {
                case 0: {
                    column.setPreferredWidth(120);
                    break; 
                }
                case 1: {
                    column.setPreferredWidth(70);
                    break; 
                }
                case 2: {
                    column.setPreferredWidth(334);
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
        ModeloTablaCliente model = (ModeloTablaCliente) tabla.getModel();
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        Object[] longValues = model.longValues;
        TableCellRenderer headerRenderer = tabla.getTableHeader().getDefaultRenderer();
        for (int i = 0; i < cantColumnas; i++) {
            column = tabla.getColumnModel().getColumn(i);
            comp = headerRenderer.getTableCellRendererComponent(null, column.getHeaderValue(),false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;
            comp = tabla.getDefaultRenderer(model.getColumnClass(i)).getTableCellRendererComponent(tabla, longValues[i],false, false, 0, i);
            cellWidth = comp.getPreferredSize().width; 
            /*
            if (DEBUG) {
                System.out.println("Initializing width of column "+ i + ". "+ "headerWidth = " + headerWidth+ "; cellWidth = " + cellWidth);
            }
            */
            column.setPreferredWidth(Math.max(headerWidth, cellWidth)); 
        }
    } //initColumnSizes. 
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void cargarDatosClientesOLD(List<Cliente> clientes) {
        ServicioAdministracionCliente servicioAdministracionCliente = new ServicioAdministracionCliente(); 
        //clientes = servicioAdministracionCliente.getClientesActivos();  // ERROR si se hace de forma directa. Se comporta de manera diferente a los Arrays. 
        for ( Cliente cliente : servicioAdministracionCliente.getClientesActivos() ) {
              clientes.add(cliente);
        }  // for 
        /*   NOTA: 
         *   Collections.copy(clientes,servicioAdministracionCliente.getClientesActivos() );
         *   The destination list must be long enough to hold all copied elements. If it is
         *   longer than that, the rest of the destination list's elments would remain
         *   unaffected.
         */
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public List<Cliente> cargarDatosClientes() {
        List<Cliente> clientes = new ArrayList<Cliente>();  
        ServicioAdministracionCliente servicioAdministracionCliente = new ServicioAdministracionCliente(); 
        //clientes = servicioAdministracionCliente.getClientesActivos();  // ERROR si se hace de forma directa. Se comporta de manera diferente a los Arrays. 
        for ( Cliente cliente : servicioAdministracionCliente.getClientesActivos() ) {
              clientes.add(cliente);
        }  // for 
        return (clientes);
    }
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void setUpBotonSeleccionar() {
        TableColumn columnaSeleccionar = this.tablaClientes.getColumnModel().getColumn(PanelLovCliente.columnas.SELECCIONAR.ordinal());
        //this.tablaClientes.getColumn("seleccionar").setCellRenderer(new BotonRendererCliente());  // o, 
        // ---------------------------
        // Cell Renderer: 
        BotonRendererCliente botonRenderer = new BotonRendererCliente(); 
        botonRenderer.addActionListener(new ActionListener() { 
            @Override
             public void actionPerformed(ActionEvent evt) {  // Este trigger no se esta activando aqui. ¿¿¿????
                    // ?? System.out.println("Ud presiono Boton Seleccionar de la Grilla Frame Cliente.");
             }
        });
        columnaSeleccionar.setCellRenderer( botonRenderer );             // << Este metodo tiene efecto sobre el despligue de la grilla. 
        //columnaSeleccionar.setCellRenderer(new BotonRendererCliente());             // << Este metodo tiene efecto sobre el despligue de la grilla. 
        BotonEditorCliente botonEditor = new BotonEditorCliente(new JCheckBox()); 
        // ---------------------------
        // **  Cell Editor **
        //columnaSeleccionar.setCellEditor(new BotonEditorCliente(new JCheckBox()));  // << Este metodo tiene efecto sobre los eventos. Ejemplo cuando el usuario presiona sobre el boton.  
        columnaSeleccionar.setCellEditor( botonEditor );  // << Este metodo tiene efecto sobre los eventos. Ejemplo cuando el usuario presiona sobre el boton.  
}
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void ordenarTablaClientes() {
         ModeloTablaCliente tablaOrdenadaClientes = (ModeloTablaCliente) this.tablaClientes.getModel(); 
         RowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(tablaOrdenadaClientes); 
         this.tablaClientes.setRowSorter(ordenador);  
    }
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void disposePanelLovCliente() {
        //PanelLovCliente.this.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        btnConforme = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/cliente/logoDesica16px.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        jLabel2.setBackground(javax.swing.UIManager.getDefaults().getColor("CheckBox.foreground"));
        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(9, 4, 4));
        jLabel2.setText("      (LOV)   CLIENTES ACTIVOS");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel3.setText("Filtrar por:");
        jLabel3.setBounds(10, 10, 79, 27);
        jLayeredPane1.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel4.setText("Codigo?:");
        jLabel4.setBounds(10, 40, 65, 17);
        jLayeredPane1.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel5.setText("Nombre ?:");
        jLabel5.setBounds(10, 60, 77, 17);
        jLayeredPane1.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jFormattedTextField1.setText("C-9999");
        jFormattedTextField1.setBounds(90, 30, 59, 27);
        jLayeredPane1.add(jFormattedTextField1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField1.setText("%nombreCliente%");
        jTextField1.setBounds(90, 60, 189, 27);
        jLayeredPane1.add(jTextField1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jButton1.setText("Refrescar");
        jButton1.setBounds(282, 60, 120, 29);
        jLayeredPane1.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        tablaClientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaClientes);

        btnConforme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/cliente/checkMark16px.png"))); // NOI18N
        btnConforme.setText("Conforme");
        btnConforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConformeActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/cliente/undo16px.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLayeredPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(btnConforme)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConforme))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tablaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClientesMouseClicked
        //FrameCliente.this.dispose();
    }//GEN-LAST:event_tablaClientesMouseClicked

    private void btnConformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConformeActionPerformed
        /*
        int fila = this.tablaClientes.getSelectedRow();
        Integer ordinalCodCliente = columnas.CODIGO_CLIENTE.ordinal();
        Integer ordinalNombreCliente = columnas.NOMBRE_CLIENTE.ordinal();
        String codCliente = (String) this.tablaClientes.getValueAt(fila, ordinalCodCliente);
        String nombreCliente = (String) this.tablaClientes.getValueAt(fila, ordinalNombreCliente);
        //System.out.println("nombreCliente="+nombreCliente); 
        PanelGuiaDespacho.txtCodCliente.setText(codCliente);
        PanelGuiaDespacho.txtCodCliente.setBackground(Color.white);
        PanelGuiaDespacho.txtRazonSocial.setText(nombreCliente);
        PanelGuiaDespacho.chbClienteValido.setSelected(true);
        disposePanelLovCliente();
        * 
        */
    }//GEN-LAST:event_btnConformeActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        //disposePanelLovCliente();  
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCancelar;
    public javax.swing.JButton btnConforme;
    private javax.swing.JButton jButton1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    public javax.swing.JTable tablaClientes;
    // End of variables declaration//GEN-END:variables
}
