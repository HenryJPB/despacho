/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.controlador;

import bean.interfase.IControladorReporte;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.view.JRViewer;
import vistas.PanelReporteGuiaDespacho;
import vistas.PanelReporteGuiaDespacho_old;

/**
 *
 * @author henrypb
 */
public class ControladorReporte implements IControladorReporte {

    final Integer sizeX = 400;
    final Integer sizeY = 330;
    final Integer posX = 400;
    final Integer posY = 200;

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public void ejecutarReporteGuiaDespacho() {
        final JDialog dialogFrame = new JDialog();
        PanelReporteGuiaDespacho panelReporteGuiaDespacho = new PanelReporteGuiaDespacho();
        panelReporteGuiaDespacho.btnOk.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String guia1 = PanelReporteGuiaDespacho.txtGuia1.getText();
                String guia2 = PanelReporteGuiaDespacho.txtGuia2.getText();
                imprimirGuia(guia1, guia2);
                dialogFrame.dispose();
            }
        });
        panelReporteGuiaDespacho.btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogFrame.dispose();
            }
        });
        dialogFrame.setContentPane(panelReporteGuiaDespacho);
        dialogFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogFrame.setModal(true);
        dialogFrame.setSize(sizeX, sizeY);
        dialogFrame.setLocation(posX, posY);
        dialogFrame.setVisible(true);
    }  // ejecutarReporteGuiaDesp().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void exportarExcel(JasperPrint jp, String nombreReporte) throws JRException {
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "./salida/" + nombreReporte);
        exporter.exportReport();
    }  // imprimirPDF().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    //@Override
    public void imprimirGuiaOld(String guia1, String guia2) {
        ControladorBD conectarBD = new ControladorBD();
        JasperReport jasperReporte;
        JasperPrint jasperPrint;
        // Conectar al Data Source: 
        Connection coneccion = null;
        try {
            coneccion = conectarBD.getConeccion();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        // jasperParameter is a Hashmap contains the parameters passed from application to the jrxml layout
        HashMap parametros = new HashMap();
        parametros.put("guia1", guia1);
        parametros.put("guia2", guia2);
        String nombreReporte = guia1;
        try {
            jasperReporte = JasperCompileManager.compileReport("./reportes/reporteGuiaDespacho.jrxml");
            jasperPrint = JasperFillManager.fillReport(jasperReporte, parametros, coneccion);
            String destino = PanelReporteGuiaDespacho_old.cbbDestinoReporte.getSelectedItem().toString();
            switch (destino) {
                case "html": {
                    nombreReporte = nombreReporte + ".html";
                    JasperExportManager.exportReportToHtmlFile(jasperPrint, "./salida/" + nombreReporte);
                    break;
                }  // case html.  
                case "pdf": {
                    nombreReporte = nombreReporte + ".pdf";
                    JasperExportManager.exportReportToPdfFile(jasperPrint, "./salida/" + nombreReporte);
                    break;
                }  // case pdf.  
                case "excel": {
                    nombreReporte = nombreReporte + ".xls";
                    exportarExcel(jasperPrint, nombreReporte);
                    break;
                }  // case excel.  
            }  // switch.  
        } catch (JRException ex) {
            Logger.getLogger(ControladorReporte.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println( ex );  
        }  // try-catch.
        //-------------------------------------------------
        // **** Ejecutar Reporte on the DeskTop:      ****
        //-------------------------------------------------
        try {
            File path = new File("salida/" + nombreReporte);
            Desktop.getDesktop().open(path);
        } catch (IOException ex) {
            System.err.println( ex );
        }  // try-catch. 
    }  // imprimirGuia().  
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public void imprimirGuia(String guia1, String guia2) {
        //------------------------*Old*-----------------------------------
        //final String jrxml_REPORTE = "reporteGuiaDespacho_v5.jrxml";  
        //final String jasper_REPORTE = "reporteGuiaDespacho_v5.jasper";  
        
        final String jrxml_REPORTE = "reporteGuiaDespacho_v6.jrxml";  
        final String jasper_REPORTE = "reporteGuiaDespacho_v6.jasper";  
        
        ControladorBD conectarBD = new ControladorBD();
        JasperReport jasperReport;
        JasperPrint jasperPrint;
        // Conectar al Data Source: 
        Connection coneccion = null;
        try {
            coneccion = conectarBD.getConeccion();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        // jasperParameter is a Hashmap contains the parameters passed from application to the jrxml layout
        HashMap parametros = new HashMap();
        parametros.put("guia1", guia1);
        parametros.put("guia2", guia2);
        //String nombreReporte = guia1;
        try {
            //jasperReporte = JasperCompileManager.compileReport("./reportes/reporteGuiaDespacho.jrxml");
            //jasperReporte = JasperCompileManager.compileReport("./reportes/reporteGuiaDespacho_v3.jrxml");
            //
            // Asi funciono, pero se compila every instace de ejecucion: 
            jasperReport = JasperCompileManager.compileReport( "./reportes/"+jrxml_REPORTE );
            //
            // Bqto, 28 enero 2022: *NO FUNCIONO* :-( 
            //InputStream jasperStream = (InputStream) this.getClass().getResourceAsStream("/jasperReports/rptTransacciones.jasper");
            //InputStream jasperStream = (InputStream) this.getClass().getResourceAsStream( "./reportes/"+jasper_REPORTE );
            //JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
            //
            jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, coneccion);
            //
            //String destino = PanelReporteGuiaDespacho.cbbDestinoReporte.getSelectedItem().toString();
            //JasperViewer jViewer=new JasperViewer(jasperPrint,true);  // jasperViewer en un contenedor de tipo jFrame por eso el error al tratar de add este al jFrame.  <> ver diferencia JRViewer.
            //jViewer.setSize( 1200,780 );
            //jViewer.setVisible(true);
            /*         
            JFrame framejViewer = new JFrame("INFORME GUIA DE DESPACHO");  
            framejViewer.getContentPane().add(new JRViewer(jasperPrint) );  //  setContentPane(framejViewer); ???
            framejViewer.pack();
            framejViewer.setVisible(true);
            */  
            JDialog dialogJViewer = new JDialog();
            dialogJViewer.setSize( 1200,780 );
            //dialogJViewer.pack();   //  << este comando genera una salida indeseable. 
            dialogJViewer.getContentPane().add(new JRViewer(jasperPrint));   
            dialogJViewer.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialogJViewer.setModal(true);
            dialogJViewer.setTitle("INFORME GUIA DESPACHO");
            dialogJViewer.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ControladorReporte.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println( ex );  
        }  // try-catch.
    }  // imprimirGuia().
    
}  // ControladorReporte.  
