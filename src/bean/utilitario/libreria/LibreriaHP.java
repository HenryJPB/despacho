/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.libreria;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author henrypb
 */
public class LibreriaHP {

    public SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

    public SimpleDateFormat formatoFechaInvertido = new SimpleDateFormat("yyyy/MM/dd");

    public SimpleDateFormat formatoFechaTipo2 = new SimpleDateFormat("dd-MM-yyyy");

    public SimpleDateFormat formatoFechaInvTipo2 = new SimpleDateFormat("yyyy-MM-dd");

    public DecimalFormat formatoDecimal = new DecimalFormat("###,###,###,##0.00");

    public DecimalFormat formatoFactura = new DecimalFormat("000000");

    public DecimalFormat formatoNcf = new DecimalFormat("0000000000");

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public boolean esNumerico(String valorStr) {
        try {
            double d = Double.parseDouble(valorStr);
        } // try.
        catch (NumberFormatException nfe) {
            return false;
        }  // catch.  
        return true;
    }  //

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void error(JPanel panel, JTextField campo, String mensaje, Color colorOriginal) {
        campo.setBackground(Color.YELLOW);
        //campo.setBackground(Color.RED);
        JOptionPane.showMessageDialog(panel, mensaje, "ATENCION", JOptionPane.ERROR_MESSAGE);
        //campo.setBackground(Color.WHITE);
        campo.setBackground(colorOriginal);
        campo.requestFocus();
    }  // errror(). 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void showMsgError(JPanel panel, Object campoReferente, String tipoObjecto, String mensaje, Color colorOriginal) {
        switch (tipoObjecto.toUpperCase()) {
            case "TEXTO": {
                JTextField campo = (JTextField) campoReferente;
                campo.setBackground(Color.YELLOW);
                JOptionPane.showMessageDialog(panel, mensaje, "ATENCION", JOptionPane.ERROR_MESSAGE);
                campo.setBackground(colorOriginal);
                campo.requestFocus();
                break;
            }
            case "FECHA": {
                JDateChooser campo = (JDateChooser) campoReferente;
                campo.setBackground(Color.YELLOW);
                JOptionPane.showMessageDialog(panel, mensaje, "ATENCION", JOptionPane.ERROR_MESSAGE);
                campo.setBackground(colorOriginal);
                campo.requestFocus();
                break;
            }
            case "COMBO": {
                JComboBox campo = (JComboBox) campoReferente;
                campo.setBackground(Color.YELLOW);
                JOptionPane.showMessageDialog(panel, mensaje, "ATENCION", JOptionPane.ERROR_MESSAGE);
                campo.setBackground(colorOriginal);
                campo.requestFocus();
                break;
            }
            default: {
                JTextField campo = (JTextField) campoReferente;
                campo.setBackground(Color.YELLOW);
                JOptionPane.showMessageDialog(panel, mensaje, "ATENCION", JOptionPane.ERROR_MESSAGE);
                campo.setBackground(colorOriginal);
                campo.requestFocus();
                break;
            }
        }  // switch. 
    }  // showError(). 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void errorFecha(JPanel panel, JDateChooser objeto, String mensaje, Color colorOriginal) {
        objeto.setBackground(Color.YELLOW);
        //campo.setBackground(Color.RED);
        JOptionPane.showMessageDialog(panel, mensaje, "ATENCION", JOptionPane.ERROR_MESSAGE);
        //objeto.setBackground(Color.WHITE);
        objeto.setBackground(colorOriginal);
        objeto.requestFocus();
    }  // errror(). 

    //--------------------------------------------------------------------------
    // convertir un String a un String en formato numerico convencional. 
    // Ejm. 10.213,45 -> 10213.45 para convertir en <>.Parse... / new BigDecimal().  
    // Otro Ejemplo en RunTime:
    //    String paridadS = PanelDocxCob.txtParCambiaria.getText();  
    //    BigDecimal paridadCambiaria = new BigDecimal( libHP.convFormatoNumerico(paridadS) );
    //--------------------------------------------------------------------------
    public String convFormatoNumericoBak(String valorS1) {
        String valorS2 = null;
        valorS2 = valorS1.replace(".", "");
        valorS2 = valorS2.replace(",", ".");
        valorS2 = valorS2.replace(" ", "");
        return (valorS2);
    }  //  convFormatoNumerico(). 

    //--------------------------------------------------------------------------
    // * des Formatear un valor numerico *
    //--------------------------------------------------------------------------
    public String desFormatoNumerico(String valorS1) {
        String valorS2 = null;
        valorS2 = valorS1.replace(".", "");
        valorS2 = valorS2.replace(",", ".");
        valorS2 = valorS2.replace(" ", "");
        return (valorS2);
    }  //  desFormatoNumerico(). 

    //--------------------------------------------------------------------------
    // Return TRUE for a valid valor numerico:
    // Ejm:  1.234.567,12   :=> (valido)
    //         1235567.12   :=> (valido)
    //        %6aa19383     :=> (no valido).
    //         1223.111.112 :=> (no valido). 
    //--------------------------------------------------------------------------
    public Boolean cadenaNumericaValida(String valorS) {
        Boolean ok = Boolean.TRUE;
        valorS = valorS.replace(" ", "");   // no permitir caracteres "blank"
        valorS = valorS.replace(".", "");
        valorS = valorS.replace(",", ".");   // cambiar la "," por el "."  
        try {
            double d = Double.parseDouble(valorS);
        } // try.
        catch (NumberFormatException nfe) {
            ok = Boolean.FALSE;
            System.err.println(" ATENCION ( LibreriaHP() )!: Error cadena numerica: " + nfe + ".");
        }  // catch.  
        return (ok);
    }  // cadenaNumericaValida(). 

    //--------------------------------------------------------------------------
    // (IN) dd-MM-yyyy -> (OUT) yyyy-MM-dd.
    //--------------------------------------------------------------------------
    public String invertirFecha(String fecha) {
        final String separador = "-";
        String fechaResult = null;
        String dia = fecha.substring(0, 2);
        String mes = fecha.substring(3, 5);
        String ano = fecha.substring(6, 10);
        fechaResult = ano + separador + mes + separador + dia;
        return (fechaResult);
    }

    //--------------------------------------------------------------------------
    // (IN)yyyy-MM-dd -> (OUT) dd-MM-yyyy
    //--------------------------------------------------------------------------
    public String straightFecha(String fecha) {
        final String separador = "-";
        String fechaResult = null;
        String dia = fecha.substring(8, 10);
        String mes = fecha.substring(5, 7);
        String ano = fecha.substring(0, 4);
        fechaResult = dia + separador + mes + separador + ano;
        return (fechaResult);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public java.sql.Date getSqlDate(java.util.Date utilFecha) {
        java.sql.Date sqlFecha = new java.sql.Date(utilFecha.getTime());
        return (sqlFecha);
    }  // getSqlDate(). 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void notificarFocus(JTextField campo) {
        campo.setBackground(Color.YELLOW);
        campo.requestFocus();
    }  // notificarFocus().  

    //--------------------------------------------------------------------------
    // check this link: http://stackoverflow.com/questions/8997228/how-to-get-name-of-the-first-day-of-a-month
    //--------------------------------------------------------------------------
    public Date getFirstDateOfCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    } // getFirstDateOfCurrentMonth()

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void showMsgProcesando(JDialog jdlProcesando) {
        final int sizeX = 400, sizeY = 60;
        //
        String strComentario = "SELECCION DE DATOS EN PROGRESO ... ";
        JLabel comentario = new JLabel(strComentario, SwingConstants.CENTER);
        comentario.setIcon(new ImageIcon("utilitario/imagenes/Imagen-animada-Cerebro-21.gif"));
        comentario.setLocation(130, 70);
        comentario.setSize(260, 40);
        comentario.setFont(new Font("Dialog", 1, 20));
        //
        JProgressBar miProgressBar = new JProgressBar(0, 100);
        miProgressBar.setSize(220, 20);
        //miProgressBar.setLocation(130, 100);
        miProgressBar.setLocation(10, 100);
        miProgressBar.setBorderPainted(true);
        miProgressBar.setBorder(BorderFactory.createRaisedBevelBorder());
        JPanel contenido = new JPanel(null);
        //frameProcesando = new JFrame("Seleccion de Datos en Progreso,...");
        //JDialog jdlProcesando = new JDialog();
        jdlProcesando.setContentPane(contenido);
        //jdlProcesando.setUndecorated(Boolean.TRUE);
        jdlProcesando.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //Container contentPane = miReportFrame.getContentPane();
        jdlProcesando.setSize(sizeX, sizeY);   // y=100.  
        //frameProcesando.setLocation(300, 200);
        jdlProcesando.setLocationRelativeTo(null);  // Locate always at center ??
        jdlProcesando.add(comentario);
        jdlProcesando.add(miProgressBar);
        //Image imagen = Toolkit.getDefaultToolkit().getImage("../utilitario/imagenes/reloj-arena-12.gif");   // <=! (Brqto: 17/04/2015) esto NO esta trabajando,...????
        //Image imagen = Toolkit.getDefaultToolkit().getImage("utilitario/imagenes/reloj-arena-12.gif");   // <=! (Brqto: 17/04/2015) esto NO esta trabajando,...????
        //jdlProcesando.setIconImage(imagen);
        jdlProcesando.setTitle(strComentario);
        //jdlProcesando.setModal(Boolean.TRUE);  // <-- Incorpora los componentes de manera visible pero genera una Salida Indeseable.   
        jdlProcesando.setResizable(Boolean.FALSE);
        jdlProcesando.setFont(new Font("Monospaced", Font.BOLD, 12));
        jdlProcesando.setVisible(true);
        /*  
         //----------------------------------------------------------------------
         //  La siguiene instruccion no se esta ejecutando porque
         //  ??? La barra de Progreso no se esta mostrando ???. 
         //----------------------------------------------------------------------
         try {
         //for (Integer i = 0; i <= 200; i += 20) {
         for (Integer i = 0; i <= 200; i += 2) {  // 10?
         Thread.sleep(i);
         miProgressBar.setValue(i / 2);
         }

         } catch (InterruptedException ex) {
         Logger.getLogger(PanelConsultaDocxCobrar.class.getName()).log(Level.SEVERE, null, ex);
         }
         */
    }  // showMsgProcesando()

}  // MyLibreryHP.  
