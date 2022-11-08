/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.entidad;

/**
 *
 * @author henrypb
 * Datos del Transportista:  
 * 
 */
public class Guia03 {
       private String C3_CI_CHOFER; 
       private String C3_NOMBRE_CHOFER; 
       private String C3_TELF_CHOFER; 
       private String C3_CEL_CHOFER; 
       private String C3_DIRECCION_CHOFER; 
       private String C3_COD_TRANSP; 
       private String C3_TIPO_TRANSP; 
       private Double C3_CAPACIDAD; 
       private String C3_PLACA_CHUTO;
       private String C3_PLACA_BATEA;
       private String C3_STATUS;
       private String rowId;

    public Guia03(String C3_CI_CHOFER, String C3_NOMBRE_CHOFER, String C3_TELF_CHOFER, String C3_CEL_CHOFER, String C3_DIRECCION_CHOFER, String C3_COD_TRANSP, String C3_TIPO_TRANSP, Double C3_CAPACIDAD, String C3_PLACA_CHUTO, String C3_PLACA_BATEA, String C3_STATUS, String rowId) {
        this.C3_CI_CHOFER = C3_CI_CHOFER;
        this.C3_NOMBRE_CHOFER = C3_NOMBRE_CHOFER;
        this.C3_TELF_CHOFER = C3_TELF_CHOFER;
        this.C3_CEL_CHOFER = C3_CEL_CHOFER;
        this.C3_DIRECCION_CHOFER = C3_DIRECCION_CHOFER;
        this.C3_COD_TRANSP = C3_COD_TRANSP;
        this.C3_TIPO_TRANSP = C3_TIPO_TRANSP;
        this.C3_CAPACIDAD = C3_CAPACIDAD;
        this.C3_PLACA_CHUTO = C3_PLACA_CHUTO;
        this.C3_PLACA_BATEA = C3_PLACA_BATEA;
        this.C3_STATUS = C3_STATUS;
        this.rowId = rowId;
    }

    public Double getC3_CAPACIDAD() {
        return C3_CAPACIDAD;
    }

    public void setC3_CAPACIDAD(Double C3_CAPACIDAD) {
        this.C3_CAPACIDAD = C3_CAPACIDAD;
    }

    public String getC3_CEL_CHOFER() {
        return C3_CEL_CHOFER;
    }

    public void setC3_CEL_CHOFER(String C3_CEL_CHOFER) {
        this.C3_CEL_CHOFER = C3_CEL_CHOFER;
    }

    public String getC3_CI_CHOFER() {
        return C3_CI_CHOFER;
    }

    public void setC3_CI_CHOFER(String C3_CI_CHOFER) {
        this.C3_CI_CHOFER = C3_CI_CHOFER;
    }

    public String getC3_COD_TRANSP() {
        return C3_COD_TRANSP;
    }

    public void setC3_COD_TRANSP(String C3_COD_TRANSP) {
        this.C3_COD_TRANSP = C3_COD_TRANSP;
    }

    public String getC3_DIRECCION_CHOFER() {
        return C3_DIRECCION_CHOFER;
    }

    public void setC3_DIRECCION_CHOFER(String C3_DIRECCION_CHOFER) {
        this.C3_DIRECCION_CHOFER = C3_DIRECCION_CHOFER;
    }

    public String getC3_NOMBRE_CHOFER() {
        return C3_NOMBRE_CHOFER;
    }

    public void setC3_NOMBRE_CHOFER(String C3_NOMBRE_CHOFER) {
        this.C3_NOMBRE_CHOFER = C3_NOMBRE_CHOFER;
    }

    public String getC3_PLACA_BATEA() {
        return C3_PLACA_BATEA;
    }

    public void setC3_PLACA_BATEA(String C3_PLACA_BATEA) {
        this.C3_PLACA_BATEA = C3_PLACA_BATEA;
    }

    public String getC3_PLACA_CHUTO() {
        return C3_PLACA_CHUTO;
    }

    public void setC3_PLACA_CHUTO(String C3_PLACA_CHUTO) {
        this.C3_PLACA_CHUTO = C3_PLACA_CHUTO;
    }

    public String getC3_STATUS() {
        return C3_STATUS;
    }

    public void setC3_STATUS(String C3_STATUS) {
        this.C3_STATUS = C3_STATUS;
    }

    public String getC3_TELF_CHOFER() {
        return C3_TELF_CHOFER;
    }

    public void setC3_TELF_CHOFER(String C3_TELF_CHOFER) {
        this.C3_TELF_CHOFER = C3_TELF_CHOFER;
    }

    public String getC3_TIPO_TRANSP() {
        return C3_TIPO_TRANSP;
    }

    public void setC3_TIPO_TRANSP(String C3_TIPO_TRANSP) {
        this.C3_TIPO_TRANSP = C3_TIPO_TRANSP;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

}  // clase Guia03.
