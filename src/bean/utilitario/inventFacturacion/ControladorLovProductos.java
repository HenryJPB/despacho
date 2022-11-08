/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.inventFacturacion;

import bean.controlador.ControladorDetalleGuia;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;

/**
 *
 * @author henrypb
 */
public class ControladorLovProductos implements IControladorLovProductos {

    @Override
    public void ejecutarDialogPanelProductos() {
        final Integer sizeX = 522, // valores constantes.
                sizeY = 320;
        final Integer posX = 340,
                posY = 220;
        final JDialog dialogFrame = new JDialog();
        final PanelLovProductos panelLovProductos = new PanelLovProductos();
        panelLovProductos.btnConforme.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Integer filaSeleccionada = panelLovProductos.tablaProductos.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    ControladorDetalleGuia controladorDetalleGuia = new ControladorDetalleGuia();  
                    //System.err.println("****Codigo=" + panelLovProductos.tablaProductos.getValueAt(filaSeleccionada, 0)+"*****");
                    controladorDetalleGuia.actualizarDatosClavesAgregarProdutos( filaSeleccionada,panelLovProductos.tablaProductos );
                }   // if filaSeleccionada > 0.
                dialogFrame.dispose();
            }
        });
        panelLovProductos.btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dialogFrame.dispose();
            }
        });
        dialogFrame.setContentPane(panelLovProductos);
        dialogFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogFrame.setModal(true);
        dialogFrame.setLocation(posX, posY);
        dialogFrame.setSize(sizeX, sizeY);
        dialogFrame.setVisible(true);
    }   // ejecutarDialogPanelProductos.  

}
