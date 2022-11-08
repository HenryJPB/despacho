/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.fleteDestino;

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
 * @author henrypb
 */
public class PanelLovFleteDestino extends javax.swing.JPanel {

    public JButton btnConforme;
    public JButton btnCancelar;
    DefaultTableModel modeloTablaFleteDestino;

    /**
     * Creates new form PanelLovTransportista
     */
    public PanelLovFleteDestino() {
        iniciarComponentes();
        iniciarTablaFleteDestino();
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

        COD_DESTINO,COD_SECTOR, ESTADO, NOMBRE_DESTINO
    }

    private void iniciarTablaFleteDestino() {
        String[] columnNames = {"Codigo","-Sector","Estado", "Nombre Destino"};
        Object[][] datos = {};
        //modeloTablaFleteDestino = new DefaultTableModel();  
        modeloTablaFleteDestino = new DefaultTableModel(datos, columnNames) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        JTableHeader header = this.tablaFleteDestino.getTableHeader();
        header.setBackground(Color.black);
        header.setForeground(Color.yellow);
        header.setFont(new Font("Monospaced", Font.BOLD, 13));
        cargarDatosTablaFleteDestino();
        this.tablaFleteDestino.setModel(modeloTablaFleteDestino);
        this.tablaFleteDestino.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        setColumnSizes(this.tablaFleteDestino);
        ordenarTabla();
    }  // iniciarTablaFleteDestino(); 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void cargarDatosTablaFleteDestino() {
        ServicioAdministracionFlete04 servicioAdministracionFlete04 = new ServicioAdministracionFlete04();
        Object dato[];   // Donde n=Nro de Objetos columnas.    
        /*
         * utilizando metodo 1* Object dato[] = new Object[2]; // Donde n=Nro de
         * Objetos columnas. dato[0] = "0001"; dato[1] = "PRUEBA 01"; utilizando
         * metodo 2 * dato = new Object[] {"0002","PRUEBA DESTINO 02"};
         * modeloTablaFleteDestino.addRow(dato);
         */
        java.sql.Date alFecha;
        //java.sql.Date alFecha = new java.sql.Date( PanelGuiaDespacho.txtFechaGuia.getDate().getTime() );  
        if (PanelGuiaDespacho.txtFechaGuia.getDate() == null) {
            alFecha = new java.sql.Date(new java.util.Date().getTime());  // iniciar una Var java.sql.Date con la fecha del Sistema.  
        } // if
        else {
            alFecha = new java.sql.Date(PanelGuiaDespacho.txtFechaGuia.getDate().getTime());
        }
        for (Flete04 fleteDestino : servicioAdministracionFlete04.getLovFleteDestinos(alFecha)) {
            dato = new Object[]{fleteDestino.getC4_COD_DESTINO(),fleteDestino.getC4_COD_SECTOR(),fleteDestino.getNombreEstado(), fleteDestino.getC4_NOMBRE_DESTINO()};
            modeloTablaFleteDestino.addRow(dato);
        }  // for.  
    }  // cargarDatosTablaFleteDestino().  

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
        Object[] longValues = {"12345678", "12345678","1234567890123456", "1234567890-1234567890-1234567890-1234567890"};
        TableCellRenderer headerRenderer = tabla.getTableHeader().getDefaultRenderer();
        for (int i = 0; i < modeloTablaFleteDestino.getColumnCount(); i++) {   // (**)  
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
        TableModel modeloTablaOrdenada = this.tablaFleteDestino.getModel();
        RowSorter<TableModel> tablaOrdenada = new TableRowSorter<TableModel>(modeloTablaOrdenada);
        this.tablaFleteDestino.setRowSorter(tablaOrdenada);
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
        tablaFleteDestino = new javax.swing.JTable();
        sppBotonera = new javax.swing.JSplitPane();

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/logoDesica16px.png"))); // NOI18N
        jLabel1.setBounds(10, 10, 50, 40);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel2.setText("L.O.V. FLETE DESTINOS");
        jLabel2.setBounds(100, 20, 200, 17);
        jLayeredPane1.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        tablaFleteDestino.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tablaFleteDestino.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaFleteDestino);

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
    public javax.swing.JTable tablaFleteDestino;
    // End of variables declaration//GEN-END:variables
}
