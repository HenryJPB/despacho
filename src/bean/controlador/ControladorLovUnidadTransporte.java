/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.controlador;

import bean.entidad.Guia04;
import bean.interfase.IControladorLovUnidadTransporte;
import javax.swing.JDialog;
import vistas.PanelGuiaDespacho;
import vistas.PanelLovUnidadTransporte;

/**
 *
 * @author henrypb
 */
public class ControladorLovUnidadTransporte implements IControladorLovUnidadTransporte {

    final Integer sizeX = 600, // valores constantes.
                  sizeY = 300;
    final Integer posX = 350,
                  posY = 200;
    
    
    @Override
    public void ejecutarDialogLovUnidadTransporte() {
        final JDialog dialogFrame = new JDialog();
        final PanelLovUnidadTransporte panelLovUnidadTransporte = new PanelLovUnidadTransporte();
        // Configurar boton Conforme.  
        panelLovUnidadTransporte.btnConforme.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                int filaSeleccionada = panelLovUnidadTransporte.tablaUnidadTransporte.getSelectedRow();  
                if (filaSeleccionada >= 0 ) {
                    Guia04 unidadTransporte = (Guia04) panelLovUnidadTransporte.tablaUnidadTransporte.getValueAt(filaSeleccionada, -1);  
                    //JOptionPane.showMessageDialog(dialogFrame,"exito!!: datos Unidad transporte seleccionada de LOV.");  
                    PanelGuiaDespacho.txtCodCamion.setText(unidadTransporte.getC4_COD_CAMION());  
                    PanelGuiaDespacho.txtTipoCamion.setText(unidadTransporte.getC4_TIPO_CAMION());  
                    PanelGuiaDespacho.txtNroEjes.setText(unidadTransporte.getC4_NO_EJES());  
                    PanelGuiaDespacho.txtPlacaChuto.setText(unidadTransporte.getC4_PLACA_CHUTO());  
                    PanelGuiaDespacho.txtPlacaBatea.setText(unidadTransporte.getC4_PLACA_BATEA());  
                } // if. 
                dialogFrame.dispose();
            }
        });
        // Configurar boton cancelar.  
        panelLovUnidadTransporte.btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dialogFrame.dispose();
            }
        });
        dialogFrame.setContentPane(panelLovUnidadTransporte);
        dialogFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogFrame.setModal(true);
        dialogFrame.setLocation(posX, posY);
        dialogFrame.setSize(sizeX, sizeY);
        dialogFrame.setVisible(true);
    }
    
}  // ControladorLovUnidadTranspote.  
