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
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.*;
import vistas.PanelGuiaDespacho;

/**
 *
 * @author henrypb
 */
public class FrameCliente extends javax.swing.JFrame {
    final Integer cantColumnas = 3;   // declarar valor constante. 
    
    
    public FrameCliente() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);  // Aplica solo a este contexto,de lo contrario se cerraria toda la aplicacion "by default". 
        iniciarTablaClientes();
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private enum columnas {
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
        cargarDatosClientes( modeloTablaClientes.clientes ); 
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
        TableColumn column=null;
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
    private void cargarDatosClientes(List<Cliente> clientes) {
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
    private void setUpBotonSeleccionar() {
        TableColumn columnaSeleccionar = this.tablaClientes.getColumnModel().getColumn(columnas.SELECCIONAR.ordinal());
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
    public void disposeFrameCliente() {
        FrameCliente.this.setVisible(false); //
        FrameCliente.this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnConforme = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jDesktopPane1.setBackground(javax.swing.UIManager.getDefaults().getColor("white"));
        jDesktopPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/cliente/logoDesica16px.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setBounds(60, 10, 39, 46);
        jDesktopPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setBackground(javax.swing.UIManager.getDefaults().getColor("CheckBox.foreground"));
        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(9, 4, 4));
        jLabel2.setText("      (LOV)   CLIENTES ACTIVOS");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(252, 203, 5)));
        jLabel2.setBounds(110, 10, 278, 46);
        jDesktopPane1.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaClientes);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/cliente/undo16px.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel3.setText("Filtrar por, ");

        jLabel4.setText("Codigo?:");

        jFormattedTextField1.setText("jFormattedTextField1");

        jLabel5.setText("Nombre ?:");

        jTextField1.setText("jTextField1");

        jButton1.setText("Refrescar");

        btnConforme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/cliente/checkMark16px.png"))); // NOI18N
        btnConforme.setText("Conforme");
        btnConforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConformeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(0, 12, 12))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConforme)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addGap(184, 184, 184))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConforme)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        FrameCliente.this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tablaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClientesMouseClicked
        //FrameCliente.this.dispose();
    }//GEN-LAST:event_tablaClientesMouseClicked

    private void btnConformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConformeActionPerformed
        int fila = this.tablaClientes.getSelectedRow();  
        Integer ordinalCodCliente = columnas.CODIGO_CLIENTE.ordinal(); 
        Integer ordinalNombreCliente = columnas.NOMBRE_CLIENTE.ordinal(); 
        String codCliente = (String) this.tablaClientes.getValueAt(fila,ordinalCodCliente); 
        String nombreCliente = (String)  this.tablaClientes.getValueAt(fila, ordinalNombreCliente);  
        //System.out.println("nombreCliente="+nombreCliente); 
        PanelGuiaDespacho.txtCodCliente.setText(codCliente); 
        PanelGuiaDespacho.txtCodCliente.setBackground(Color.white);
        PanelGuiaDespacho.txtRazonSocial.setText(nombreCliente);
        PanelGuiaDespacho.chbClienteValido.setSelected(true);
        disposeFrameCliente();
    }//GEN-LAST:event_btnConformeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new FrameCliente().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConforme;
    private javax.swing.JButton jButton1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tablaClientes;
    // End of variables declaration//GEN-END:variables
}
