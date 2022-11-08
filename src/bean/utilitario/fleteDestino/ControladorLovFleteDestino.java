/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.fleteDestino;

import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import javax.swing.JDialog;
import vistas.PanelGuiaDespacho;

/**
 *
 * @author henrypb
 */
public class ControladorLovFleteDestino implements IControladorLovFleteDestino {

    final Integer sizeX = 550, // valores constantes.
            sizeY = 320;
    final Integer posX = 380,
            posY = 420;  

    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    
    @Override
    public void ejecutarDialogLovFleteDestino() {
        final JDialog dialogFrame = new JDialog();
        final PanelLovFleteDestino panelLovFleteDestino = new PanelLovFleteDestino();
        // -------------------------------------------
        // * configurar boton conforme del panel:
        //   -----------------------------------
        panelLovFleteDestino.btnConforme.addActionListener( new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Integer filaSeleccionada = panelLovFleteDestino.tablaFleteDestino.getSelectedRow();  
                if ( filaSeleccionada >= 0 ) {
                     int colunnaCodDestino = PanelLovFleteDestino.columnas.COD_DESTINO.ordinal();  
                     int colunnaCodSector  = PanelLovFleteDestino.columnas.COD_SECTOR.ordinal();  
                     int colunnaEstado     = PanelLovFleteDestino.columnas.ESTADO.ordinal();  
                     int colunnaNombreDestino = PanelLovFleteDestino.columnas.NOMBRE_DESTINO.ordinal();  
                     //System.out.println(panelLovFleteDestino.tablaFleteDestino.getValueAt(filaSeleccionada,colunnaCodDestino));
                     String codDestino    = (String) panelLovFleteDestino.tablaFleteDestino.getValueAt(filaSeleccionada,colunnaCodDestino);
                     String codSector     = (String) panelLovFleteDestino.tablaFleteDestino.getValueAt(filaSeleccionada, colunnaCodSector); 
                     String estado        = (String) panelLovFleteDestino.tablaFleteDestino.getValueAt(filaSeleccionada, colunnaEstado);  
                     String nombreDestino = (String) panelLovFleteDestino.tablaFleteDestino.getValueAt(filaSeleccionada,colunnaNombreDestino);
                     PanelGuiaDespacho.txtCodDestino.setText(codDestino);
                     PanelGuiaDespacho.txtCodSector.setText(codSector);
                     PanelGuiaDespacho.txtDestino.setText(nombreDestino);
                     PanelGuiaDespacho.txtEstado.setText(estado);
                     java.sql.Date alFecha;  
                     if (  PanelGuiaDespacho.txtFechaGuia.getDate() == null ) {
                           alFecha  = new java.sql.Date( new java.util.Date().getTime() );  // iniciar una Var java.sql.Date con la fecha del Sistema.  
                     }  
                     else  {
                           alFecha  = new java.sql.Date( PanelGuiaDespacho.txtFechaGuia.getDate().getTime() );          
                     }  // if-else.  
                     ServicioAdministracionFlete04 servicioAdministracionFlete04 = new ServicioAdministracionFlete04();  
                     Flete04 fleteDestino = servicioAdministracionFlete04.getFleteDestino(codDestino,alFecha);  
                     PanelGuiaDespacho.txtFechaVigente.setText( formatoFecha.format(fleteDestino.getC4_FECHA_RELACION())); 
                }    
                dialogFrame.dispose();
            }
        });
        // * configurar boton cancelar del panel:  
        //   -----------------------------------
        panelLovFleteDestino.btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dialogFrame.dispose();
            }
        });
        //--------------------------------------------
        dialogFrame.setContentPane(panelLovFleteDestino);
        dialogFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogFrame.setModal(true);
        dialogFrame.setLocation(posX, posY);
        dialogFrame.setSize(sizeX, sizeY);
        dialogFrame.setVisible(true);
    }
}
