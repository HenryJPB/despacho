/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.cliente;

import com.jktoolkit.table.JKColumn;
import com.jktoolkit.table.JKPopupMenuItem;
import com.jktoolkit.table.JKTable;
import com.jktoolkit.table.datasource.impl.JKDataSourceList;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JDialog;
import vistas.PanelGuiaDespacho;

/**
 *
 * @author henrypb
 */
public class ControladorLovCliente implements IControladorLovCliente {

    final Integer sizeX = 570, // valores constantes.
            sizeY = 460;
    final Integer posX = 350,
            posY = 200;

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public void ejecutarFrameLovCliente() {
        FrameCliente frameCliente = new FrameCliente();
        /**
         * *******
         * NOTA al 24/01/2013 : 11:57: Basado en que los JFrame no pueden ser
         * Modales porque son contenedores padres se utilizo este artificio de
         * crear un JDialog que si lo puede ser para activar un LOV de clientes
         * Activos *******
         */
        frameCliente.setLocation(posX, posY);
        //frameCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameCliente.setVisible(true);
        // (1).prueba:
        // Genera ERRORES de Ejecucion al cerrar el Dialog. No dispose el objeto. ?????
           /*
         * FrameMenuPrincipal framePrincipal = new FrameMenuPrincipal(); JDialog
         * d = new JDialog(framePrincipal,"Dialogo",true); d.setSize(300,300);
         * d.setLocationRelativeTo(framePrincipal);
         * d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
         * d.setVisible(true);
         *
         */
        // (2).
        // Gernera ERRORES de Ejecucion al cerrar el Dialog. No dispose el object. ????. 
           /*
         * JDialog dialogLovCliente = new JDialog(framePrincipal,"Dialogo
         * Frame",Dialog.ModalityType.DOCUMENT_MODAL);
         * dialogLovCliente.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
         * dialogLovCliente.setContentPane(frameCliente.getContentPane());
         * dialogLovCliente.setSize(x, y);
         * dialogLovCliente.setLocationRelativeTo(null);
         * dialogLovCliente.setVisible(true); *
         */
    }

    @Override
    public void ejecutarDialogLovCliente() {
        final JDialog dialogFrame = new JDialog();
        final PanelLovCliente panelLovCliente = new PanelLovCliente();
        panelLovCliente.btnConforme.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                int fila = panelLovCliente.tablaClientes.getSelectedRow();
                if (fila >= 0) {
                    Integer ordinalCodCliente = PanelLovCliente.columnas.CODIGO_CLIENTE.ordinal();
                    Integer ordinalNombreCliente = PanelLovCliente.columnas.NOMBRE_CLIENTE.ordinal();
                    String codCliente = (String) panelLovCliente.tablaClientes.getValueAt(fila, ordinalCodCliente);
                    String nombreCliente = (String) panelLovCliente.tablaClientes.getValueAt(fila, ordinalNombreCliente);
                    //System.out.println("nombreCliente="+nombreCliente); 
                    PanelGuiaDespacho.txtCodCliente.setText(codCliente);
                    PanelGuiaDespacho.txtCodCliente.setBackground(Color.white);
                    PanelGuiaDespacho.txtRazonSocial.setText(nombreCliente);
                    PanelGuiaDespacho.chbClienteValido.setSelected(true);
                }
                dialogFrame.dispose();
            }
        });
        //        
        panelLovCliente.btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dialogFrame.dispose();
            }
        });
        dialogFrame.setContentPane(panelLovCliente);
        dialogFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogFrame.setModal(true);
        dialogFrame.setLocation(posX, posY);
        dialogFrame.setSize(sizeX, sizeY);
        dialogFrame.setVisible(true);
    }

    //--------------------------------------------------------------------------
    // este metodo manipula una JKTable.  
    //--------------------------------------------------------------------------
    @Override
    public void ejecutarDialogLovJKTCliente() {
        final JDialog dialogFrame = new JDialog();
        PanelLovCliente panelLovCliente = new PanelLovCliente();
        // cargar el Array List de Clientes:           
        //List<Cliente> clientes = new ArrayList<Cliente>(); 
        List<Cliente> clientes = panelLovCliente.cargarDatosClientes();
        // crear el DataSource:   
        JKDataSourceList<Cliente> dataSourceClientes = new JKDataSourceList<Cliente>(clientes);
        // crear la table de Clientes:  
        final JKTable tablaClientes = new JKTable<Cliente>(dataSourceClientes);
        // Agregar las colunnas q va a manipular la tabla.   
        tablaClientes.addColumn(new JKColumn("Boton-Seleccionar", "seleccionar", false));   // false <-> ocultar campo.  
        tablaClientes.addColumn(new JKColumn("Codigo", "codCliente", true));
        tablaClientes.addColumn(new JKColumn("Nombre Cliente", "nombreCliente", true));
        tablaClientes.setMaxRows(22);   // nro filas de la tabla. 
        // Agregamos el menu  contextual a la tabla de Clientes:  
        tablaClientes.addPopupMenuItem(new JKPopupMenuItem("SELECCIONAR:[<-!]", KeyEvent.VK_ENTER, new Runnable() {
            @Override
            public void run() {
                List<Cliente> listaClientesSeleccionados = tablaClientes.getSelectedObjects();    
                //Cliente   clienteSeleccionado      = (Cliente) tablaClientes.getSelectedObject();  
                Cliente clienteSeleccionado = null; 
                for (int i=0; i< listaClientesSeleccionados.size(); i++) {
                    clienteSeleccionado = listaClientesSeleccionados.get(i);  
                }  // for.  
                PanelGuiaDespacho.txtCodCliente.setText(clienteSeleccionado.getCodCliente());
                PanelGuiaDespacho.txtCodCliente.setBackground(Color.white);
                PanelGuiaDespacho.txtRazonSocial.setText(clienteSeleccionado.getNombreCliente());
                PanelGuiaDespacho.chbClienteValido.setSelected(true);
                dialogFrame.dispose();
            }
        }));
        // pintamos los cambios en la tabla:
        tablaClientes.draw();
        // y se la agragamos al Pane de contenedor JDialog:
        dialogFrame.setContentPane(tablaClientes);
        //
        dialogFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogFrame.setModal(true);
        dialogFrame.setLocation(posX, posY);
        //dialogFrame.setLocationRelativeTo(null);    // Centrar el objeto ???.  
        dialogFrame.setTitle("[ L.V. CLIENTES ACTIVOS ]");
        int ajusteMarcoY = 4;
        dialogFrame.setSize(sizeX, sizeY - ajusteMarcoY);
        dialogFrame.setVisible(true);
    }
}  // ControladoLovCliente.  