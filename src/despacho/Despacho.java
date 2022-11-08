/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package despacho;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import vistas.FrameAccesoPrincipal;

/**
 *
 * @author henrypb Fecha creacion: Barquisimeto: 29/11/2012.
 *
 */
public class Despacho {
    private static JFrame miFrame;
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private static void mostrarFrameBienvenida() {
        //JFrame miFrame;
        JProgressBar miProgressBar;
        JLabel comentarioBienvenida = new JLabel("BIENVENIDO!!", SwingConstants.CENTER);
        comentarioBienvenida.setLocation(130, 70);
        comentarioBienvenida.setSize(220, 40);
        comentarioBienvenida.setFont(new Font("Dialog", 1, 20));
        miProgressBar = new JProgressBar(0, 100);
        miProgressBar.setSize(220, 20);
        miProgressBar.setLocation(130, 100);
        miProgressBar.setBorderPainted(true);
        miProgressBar.setBorder(BorderFactory.createRaisedBevelBorder());
        JPanel contenido = new JPanel(null);
        miFrame = new JFrame("SOLUCION ADMINISTRATIVA DE DESPACHO");
        miFrame.setContentPane(contenido);
        miFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Container contentPane = miReportFrame.getContentPane();
        miFrame.setSize(500, 250);
        miFrame.setLocation(250, 200);
        miFrame.add(comentarioBienvenida);
        miFrame.add(miProgressBar);
        miFrame.setVisible(true);
        try {
            for (Integer i = 0; i <= 200; i += 20) {
                Thread.sleep(i);
                miProgressBar.setValue(i / 2);
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Despacho.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  // metodo mostrarFrameBienvenida(). 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static void main(String[] args) {
        /*
        try {  // El siguiente comando define la apariencia de la aplicacion/se puede suprimir.  
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
        } */  
        mostrarFrameBienvenida();
        FrameAccesoPrincipal frameAccesoPrincipal = new FrameAccesoPrincipal();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        frameAccesoPrincipal.setState(Frame.NORMAL);
        //frameAccesoPrincipal.setSize(dimension);  // Funcionó correctamente el 29/11/2012. 15:55- 
        frameAccesoPrincipal.setSize(Estilos.xWindow, Estilos.yWindow);
        miFrame.dispose();
        frameAccesoPrincipal.setVisible(true);
    }
}
