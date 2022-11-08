/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.entidad;

/**
 *
 * @author henrypb
 * Datos del Transporte: 
 * 
 */
public class Guia05 {
       private String C5_COD_TRANSPORTE; 
       private String C5_NOMBRE_TRANSP; 
       private String C5_CODIGO_CONTABLE; 
       private String C5_STATUS;
       private String rowId;

    public Guia05(String C5_COD_TRANSPORTE, String C5_NOMBRE_TRANSP) {
        this.C5_COD_TRANSPORTE = C5_COD_TRANSPORTE;
        this.C5_NOMBRE_TRANSP = C5_NOMBRE_TRANSP;
    }

    public Guia05(String C5_COD_TRANSPORTE, String C5_NOMBRE_TRANSP, String C5_CODIGO_CONTABLE, String C5_STATUS, String rowId) {
        this.C5_COD_TRANSPORTE = C5_COD_TRANSPORTE;
        this.C5_NOMBRE_TRANSP = C5_NOMBRE_TRANSP;
        this.C5_CODIGO_CONTABLE = C5_CODIGO_CONTABLE;
        this.C5_STATUS = C5_STATUS;
        this.rowId = rowId;
    }

    public String getC5_CODIGO_CONTABLE() {
        return C5_CODIGO_CONTABLE;
    }

    public void setC5_CODIGO_CONTABLE(String C5_CODIGO_CONTABLE) {
        this.C5_CODIGO_CONTABLE = C5_CODIGO_CONTABLE;
    }

    public String getC5_COD_TRANSPORTE() {
        return C5_COD_TRANSPORTE;
    }

    public void setC5_COD_TRANSPORTE(String C5_COD_TRANSPORTE) {
        this.C5_COD_TRANSPORTE = C5_COD_TRANSPORTE;
    }

    public String getC5_NOMBRE_TRANSP() {
        return C5_NOMBRE_TRANSP;
    }

    public void setC5_NOMBRE_TRANSP(String C5_NOMBRE_TRANSP) {
        this.C5_NOMBRE_TRANSP = C5_NOMBRE_TRANSP;
    }

    public String getC5_STATUS() {
        return C5_STATUS;
    }

    public void setC5_STATUS(String C5_STATUS) {
        this.C5_STATUS = C5_STATUS;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }
    
}  // Guia05.  
