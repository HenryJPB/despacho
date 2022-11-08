/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.*;

/**
 *
 * @author henrypb
 */
public class DialogTransportista {
    
    /*--------------------------------------------------------------------------
    List of Value Transportistas: 
    --------------------------------------------------------------------------*/
    public void lovTransportistas() {
       //JLabel titulo = new JLabel("L.O.V. TRANSPORTISTAS ", SwingConstants.CENTER);
       JLabel titulo = new JLabel("L.O.V. TRANSPORTISTAS ");
       //titulo.setLocation(1,1);
       //titulo.setSize(220, 40);
       titulo.setFont(new Font("Dialog", 1, 14));
       //
       JTable tablaTransportista = new JTable( new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Cedula-Rif", "Transportista"
            }
        ));
       tablaTransportista.setSize(250,200);
       //tablaTransportista.setLocation(1,1);
       JScrollPane  panelScroll = new JScrollPane( tablaTransportista );
       //
       JButton btnConforme = new JButton("Conforme"); 
       JButton btnCancelar = new JButton("Cancelar"); 
       //
       JPanel miPanelContenido = new JPanel();
       miPanelContenido.add(titulo);
       miPanelContenido.add(panelScroll, BorderLayout.CENTER );
       miPanelContenido.add(btnConforme);
       miPanelContenido.add(btnCancelar); 
       JDialog dialogFrame = new JDialog();
       dialogFrame.setContentPane(miPanelContenido);
       dialogFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
       dialogFrame.setModal(true);
       dialogFrame.setLocation(500,210);
       dialogFrame.setSize(400,300);
       //dialogFrame.pack(); 
       dialogFrame.setVisible(true);
   }
    
}  // DialogTransportistas. 
