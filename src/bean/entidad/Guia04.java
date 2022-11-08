/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.entidad;

/**
 *
 * @author henrypb
 * Datos de la Unidad de Transporte:
 * 
 */
public class Guia04 {
       private String C4_CI_CHOFER; 
       private String C4_COD_TRANSP; 
       private String C4_COD_CAMION; 
       private String C4_TIPO_CAMION; 
       private String C4_COLOR; 
       private String C4_NO_EJES; 
       private Double C4_CAPACIDAD; 
       private String C4_PLACA_CHUTO; 
       private String C4_PLACA_BATEA;
       private String rowId;

    // -------------------------------------------------------------------------
    // Este constructor aplica a LOV de la Unidad de Transporte: 
    // -------------------------------------------------------------------------
    public Guia04(String C4_COD_CAMION, String C4_TIPO_CAMION, String C4_COLOR, String C4_NO_EJES, Double C4_CAPACIDAD, String C4_PLACA_CHUTO, String C4_PLACA_BATEA) {
        this.C4_COD_CAMION = C4_COD_CAMION;
        this.C4_TIPO_CAMION = C4_TIPO_CAMION;
        this.C4_COLOR = C4_COLOR;
        this.C4_NO_EJES = C4_NO_EJES;
        this.C4_CAPACIDAD = C4_CAPACIDAD;
        this.C4_PLACA_CHUTO = C4_PLACA_CHUTO;
        this.C4_PLACA_BATEA = C4_PLACA_BATEA;
    }
       
    public Guia04(String C4_CI_CHOFER, String C4_COD_TRANSP, String C4_COD_CAMION, String C4_TIPO_CAMION, String C4_COLOR, String C4_NO_EJES, Double C4_CAPACIDAD, String C4_PLACA_CHUTO, String C4_PLACA_BATEA, String rowId) {
        this.C4_CI_CHOFER = C4_CI_CHOFER;
        this.C4_COD_TRANSP = C4_COD_TRANSP;
        this.C4_COD_CAMION = C4_COD_CAMION;
        this.C4_TIPO_CAMION = C4_TIPO_CAMION;
        this.C4_COLOR = C4_COLOR;
        this.C4_NO_EJES = C4_NO_EJES;
        this.C4_CAPACIDAD = C4_CAPACIDAD;
        this.C4_PLACA_CHUTO = C4_PLACA_CHUTO;
        this.C4_PLACA_BATEA = C4_PLACA_BATEA;
        this.rowId = rowId;
    }

    public String getC4_CI_CHOFER() {
        return C4_CI_CHOFER;
    }

    public void setC4_CI_CHOFER(String C4_CI_CHOFER) {
        this.C4_CI_CHOFER = C4_CI_CHOFER;
    }

    public String getC4_COD_CAMION() {
        return C4_COD_CAMION;
    }

    public void setC4_COD_CAMION(String C4_COD_CAMION) {
        this.C4_COD_CAMION = C4_COD_CAMION;
    }

    public String getC4_COD_TRANSP() {
        return C4_COD_TRANSP;
    }

    public void setC4_COD_TRANSP(String C4_COD_TRANSP) {
        this.C4_COD_TRANSP = C4_COD_TRANSP;
    }

    public String getC4_COLOR() {
        return C4_COLOR;
    }

    public void setC4_COLOR(String C4_COLOR) {
        this.C4_COLOR = C4_COLOR;
    }

    public String getC4_NO_EJES() {
        return C4_NO_EJES;
    }

    public void setC4_NO_EJES(String C4_NO_EJES) {
        this.C4_NO_EJES = C4_NO_EJES;
    }

    public Double getC4_CAPACIDAD() {
        return C4_CAPACIDAD;
    }

    public void setC4_CAPACIDAD(Double C4_CAPACIDAD) {
        this.C4_CAPACIDAD = C4_CAPACIDAD;
    }
    
    public String getC4_PLACA_BATEA() {
        return C4_PLACA_BATEA;
    }

    public void setC4_PLACA_BATEA(String C4_PLACA_BATEA) {
        this.C4_PLACA_BATEA = C4_PLACA_BATEA;
    }

    public String getC4_PLACA_CHUTO() {
        return C4_PLACA_CHUTO;
    }

    public void setC4_PLACA_CHUTO(String C4_PLACA_CHUTO) {
        this.C4_PLACA_CHUTO = C4_PLACA_CHUTO;
    }

    public String getC4_TIPO_CAMION() {
        return C4_TIPO_CAMION;
    }

    public void setC4_TIPO_CAMION(String C4_TIPO_CAMION) {
        this.C4_TIPO_CAMION = C4_TIPO_CAMION;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

}   