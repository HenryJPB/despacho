/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.inventFacturacion;

import java.sql.Date;

/**
 *
 * @author henrypb
 */
public class Inv02 {
       private String        C2_CODIGO;    
       private Double        C2_PRECIO_UNIDAD;  
       private Double        C2_PRECIO_KGS; 
       private java.sql.Date C2_FECHA;  

    public Inv02(String C2_CODIGO, Double C2_PRECIO_UNIDAD, Double C2_PRECIO_KGS, Date C2_FECHA) {
        this.C2_CODIGO = C2_CODIGO;
        this.C2_PRECIO_UNIDAD = C2_PRECIO_UNIDAD;
        this.C2_PRECIO_KGS = C2_PRECIO_KGS;
        this.C2_FECHA = C2_FECHA;
    }

    public String getC2_CODIGO() {
        return C2_CODIGO;
    }

    public void setC2_CODIGO(String C2_CODIGO) {
        this.C2_CODIGO = C2_CODIGO;
    }

    public Double getC2_PRECIO_UNIDAD() {
        return C2_PRECIO_UNIDAD;
    }

    public void setC2_PRECIO_UNIDAD(Double C2_PRECIO_UNIDAD) {
        this.C2_PRECIO_UNIDAD = C2_PRECIO_UNIDAD;
    }

    public Double getC2_PRECIO_KGS() {
        return C2_PRECIO_KGS;
    }

    public void setC2_PRECIO_KGS(Double C2_PRECIO_KGS) {
        this.C2_PRECIO_KGS = C2_PRECIO_KGS;
    }

    public Date getC2_FECHA() {
        return C2_FECHA;
    }

    public void setC2_FECHA(Date C2_FECHA) {
        this.C2_FECHA = C2_FECHA;
    }
       
}   // Inv02.  
