/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.controlador;

import bean.entidad.Guia03;
import bean.entidad.Guia05;
import bean.interfase.IControladorLovTransportista;
import bean.servicio.ServicioAdministracionGuia05;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import vistas.PanelGuiaDespacho;
import vistas.PanelLovTransportista;

/**
 *
 * @author henrypb
 */
public class ControladorLovTransportista implements IControladorLovTransportista {
    
    JDialog dialogFrame = new JDialog();
    
    @Override
    public void ejecutarDialogLovTransportista() {
       final PanelLovTransportista panelLovTransportista = new PanelLovTransportista(); 
       panelLovTransportista.btnConforme.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Integer filaSeleccionada = panelLovTransportista.tablaTransportista.getSelectedRow();   
                if (filaSeleccionada >= 0 ) {
                    Guia03 transportista = (Guia03) panelLovTransportista.tablaTransportista.getValueAt(filaSeleccionada, -1);  
                    //JOptionPane.showMessageDialog(panelLovTransportista,"Usuario esta conforme con la seleccion.");  
                    PanelGuiaDespacho.txtCedulaTransportista.setText(transportista.getC3_CI_CHOFER());
                    PanelGuiaDespacho.txtNombreTransportista.setText(transportista.getC3_NOMBRE_CHOFER()); 
                    ServicioAdministracionGuia05 servicioAdministracionGuia05 = new ServicioAdministracionGuia05();  
                    if ( servicioAdministracionGuia05.transporteRegistrado(transportista.getC3_COD_TRANSP()) ) {
                       //JOptionPane.showMessageDialog(dialogFrame, "Transporte encontrado.");
                       Guia05 transporte = servicioAdministracionGuia05.getDatosTransporte(transportista.getC3_COD_TRANSP()); 
                       PanelGuiaDespacho.txtCodTransporte.setText(transporte.getC5_COD_TRANSPORTE());
                       PanelGuiaDespacho.txtNombreTransporte.setText(transporte.getC5_NOMBRE_TRANSP());
                    }
                    else {
                       JOptionPane.showMessageDialog(dialogFrame,"Inconsistencia de Datos en el registro del Transporte. Pongace en contacto con el DBA.");
                    }  // if-interno. 
                }      // if filaSeleccionada,..
                disposeDialogLovTransportista();  
            }
        });      
       panelLovTransportista.btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                disposeDialogLovTransportista();
            }
        });       
       dialogFrame.setContentPane(panelLovTransportista);
       dialogFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
       dialogFrame.setModal(true);
       dialogFrame.setLocation(520,210);
       dialogFrame.setSize(440,360);
       //dialogFrame.pack(); 
       dialogFrame.setVisible(true);
    }
    
    public void disposeDialogLovTransportista() {
           dialogFrame.setVisible(false);
           dialogFrame.dispose();     
    }
}
