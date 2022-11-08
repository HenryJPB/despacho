/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.contab;

/**
 *
 * @author henrypb
 */
public class Contabaf {
       private String CODIGO_EMPRESA;  
       private String NOMBRE_EMPRESA;  
       private String DIRECCION;  
       private String FECHA_INICIAL;  
       private String FECHA_EJERCICIO;  
       private String RIF;

    /*  tabla CONTABAF_DAT:
    CODIGO_EMPRESA                  NOT NULL VARCHAR2(3)
    NOMBRE_EMPRESA                           VARCHAR2(40)
    DIRECCION                                VARCHAR2(50)
    LONGITUD_CUENTA                          NUMBER(14,2)
    NRO_RUPTURAS                             NUMBER(14,2)
    FORMATO_CUENTA                           VARCHAR2(25)
    CUENTA_GAN_PERD                          VARCHAR2(25)
    FECHA_PERIODO                            VARCHAR2(10)
    FECHA_INICIAL                            VARCHAR2(10)
    FECHA_EJERCICIO                          VARCHAR2(10)
    CIERRES                                  VARCHAR2(18)
    RIF                                      VARCHAR2(20)
     */
    public Contabaf(String CODIGO_EMPRESA, String NOMBRE_EMPRESA, String DIRECCION, String FECHA_INICIAL, String FECHA_EJERCICIO, String RIF) {
        this.CODIGO_EMPRESA = CODIGO_EMPRESA;
        this.NOMBRE_EMPRESA = NOMBRE_EMPRESA;
        this.DIRECCION = DIRECCION;
        this.FECHA_INICIAL = FECHA_INICIAL;
        this.FECHA_EJERCICIO = FECHA_EJERCICIO;
        this.RIF = RIF;
    }
       
    public String getCODIGO_EMPRESA() {
        return CODIGO_EMPRESA;
    }

    public void setCODIGO_EMPRESA(String CODIGO_EMPRESA) {
        this.CODIGO_EMPRESA = CODIGO_EMPRESA;
    }

    public String getNOMBRE_EMPRESA() {
        return NOMBRE_EMPRESA;
    }

    public void setNOMBRE_EMPRESA(String NOMBRE_EMPRESA) {
        this.NOMBRE_EMPRESA = NOMBRE_EMPRESA;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public String getFECHA_INICIAL() {
        return FECHA_INICIAL;
    }

    public void setFECHA_INICIAL(String FECHA_INICIAL) {
        this.FECHA_INICIAL = FECHA_INICIAL;
    }

    public String getFECHA_EJERCICIO() {
        return FECHA_EJERCICIO;
    }

    public void setFECHA_EJERCICIO(String FECHA_EJERCICIO) {
        this.FECHA_EJERCICIO = FECHA_EJERCICIO;
    }

    public String getRIF() {
        return RIF;
    }

    public void setRIF(String RIF) {
        this.RIF = RIF;
    }
       
}
