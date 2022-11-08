/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.fleteDestino;

import java.sql.Date;

/**
 *
 * @author henrypb
 * 
 */
public class Flete04 {
    private Date     C4_FECHA_RELACION; 
    private String   C4_COD_PAIS;  
    private String   C4_COD_ESTADO;  
    private String   nombreEstado;  
    private String   C4_COD_DESTINO;  
    private String   C4_COD_SECTOR;  
    private String   C4_NOMBRE_DESTINO;  
    private String   C4_COD_POSTAL;  
    private Double   C4_PRECIO_TON_CAMION;  
    private Double   C4_PRECIO_TON_GANDOLA;  
    private Double   C4_PRECIO_TON_TORONTO;  
    private String   rowId;

    /* Entidad utilizada para gestionar la LOV del componente Flete-destino.  
    public Flete04(String C4_COD_ESTADO, String nombreEstado, String C4_COD_DESTINO, String C4_NOMBRE_DESTINO) {
        this.C4_COD_ESTADO = C4_COD_ESTADO;
        this.nombreEstado = nombreEstado;
        this.C4_COD_DESTINO = C4_COD_DESTINO;
        this.C4_NOMBRE_DESTINO = C4_NOMBRE_DESTINO;
    }
    */

    //--------------------------------------------------------------------------
    // Constructor utilizado para gestionar la LOV del componente Flete-destino.  
    //--------------------------------------------------------------------------
    public Flete04(String C4_COD_ESTADO, String nombreEstado, String C4_COD_DESTINO, String C4_COD_SECTOR, String C4_NOMBRE_DESTINO) {
        this.C4_COD_ESTADO = C4_COD_ESTADO;
        this.nombreEstado = nombreEstado;
        this.C4_COD_DESTINO = C4_COD_DESTINO;
        this.C4_COD_SECTOR = C4_COD_SECTOR;
        this.C4_NOMBRE_DESTINO = C4_NOMBRE_DESTINO;
    }

    //--------------------------------------------------------------------------
    // Constructor utilizado para gestionar la los registros del Banco de Datos. 
    //--------------------------------------------------------------------------
    /*
    public Flete04(Date C4_FECHA_RELACION, String C4_COD_PAIS, String C4_COD_ESTADO, String nombreEstado, String C4_COD_DESTINO, String C4_NOMBRE_DESTINO, String C4_COD_POSTAL, Double C4_PRECIO_TON_CAMION, Double C4_PRECIO_TON_GANDOLA, Double C4_PRECIO_TON_TORONTO, String rowId) {
        this.C4_FECHA_RELACION = C4_FECHA_RELACION;
        this.C4_COD_PAIS = C4_COD_PAIS;
        this.C4_COD_ESTADO = C4_COD_ESTADO;
        this.nombreEstado = nombreEstado;
        this.C4_COD_DESTINO = C4_COD_DESTINO;
        this.C4_NOMBRE_DESTINO = C4_NOMBRE_DESTINO;
        this.C4_COD_POSTAL = C4_COD_POSTAL;
        this.C4_PRECIO_TON_CAMION = C4_PRECIO_TON_CAMION;
        this.C4_PRECIO_TON_GANDOLA = C4_PRECIO_TON_GANDOLA;
        this.C4_PRECIO_TON_TORONTO = C4_PRECIO_TON_TORONTO;
        this.rowId = rowId;
    }
    */
    
    //--------------------------------------------------------------------------
    // Constructor utilizado para gestionar la los registros del Banco de Datos. 
    //--------------------------------------------------------------------------
    public Flete04(Date C4_FECHA_RELACION, String C4_COD_PAIS, String C4_COD_ESTADO, String nombreEstado, String C4_COD_DESTINO, String C4_COD_SECTOR, String C4_NOMBRE_DESTINO, String C4_COD_POSTAL, Double C4_PRECIO_TON_CAMION, Double C4_PRECIO_TON_GANDOLA, Double C4_PRECIO_TON_TORONTO, String rowId) {
        this.C4_FECHA_RELACION = C4_FECHA_RELACION;
        this.C4_COD_PAIS = C4_COD_PAIS;
        this.C4_COD_ESTADO = C4_COD_ESTADO;
        this.nombreEstado = nombreEstado;
        this.C4_COD_DESTINO = C4_COD_DESTINO;
        this.C4_COD_SECTOR = C4_COD_SECTOR;
        this.C4_NOMBRE_DESTINO = C4_NOMBRE_DESTINO;
        this.C4_COD_POSTAL = C4_COD_POSTAL;
        this.C4_PRECIO_TON_CAMION = C4_PRECIO_TON_CAMION;
        this.C4_PRECIO_TON_GANDOLA = C4_PRECIO_TON_GANDOLA;
        this.C4_PRECIO_TON_TORONTO = C4_PRECIO_TON_TORONTO;
        this.rowId = rowId;
    }
    
    public String getC4_COD_DESTINO() {
        return C4_COD_DESTINO;
    }

    public void setC4_COD_DESTINO(String C4_COD_DESTINO) {
        this.C4_COD_DESTINO = C4_COD_DESTINO;
    }

    public String getC4_COD_SECTOR() {
        return C4_COD_SECTOR;
    }

    public void setC4_COD_SECTOR(String C4_COD_SECTOR) {
        this.C4_COD_SECTOR = C4_COD_SECTOR;
    }
    
    public String getC4_COD_ESTADO() {
        return C4_COD_ESTADO;
    }

    public void setC4_COD_ESTADO(String C4_COD_ESTADO) {
        this.C4_COD_ESTADO = C4_COD_ESTADO;
    }

    public String getC4_COD_PAIS() {
        return C4_COD_PAIS;
    }

    public void setC4_COD_PAIS(String C4_COD_PAIS) {
        this.C4_COD_PAIS = C4_COD_PAIS;
    }

    public String getC4_COD_POSTAL() {
        return C4_COD_POSTAL;
    }

    public void setC4_COD_POSTAL(String C4_COD_POSTAL) {
        this.C4_COD_POSTAL = C4_COD_POSTAL;
    }

    public Date getC4_FECHA_RELACION() {
        return C4_FECHA_RELACION;
    }

    public void setC4_FECHA_RELACION(Date C4_FECHA_RELACION) {
        this.C4_FECHA_RELACION = C4_FECHA_RELACION;
    }

    public String getC4_NOMBRE_DESTINO() {
        return C4_NOMBRE_DESTINO;
    }

    public void setC4_NOMBRE_DESTINO(String C4_NOMBRE_DESTINO) {
        this.C4_NOMBRE_DESTINO = C4_NOMBRE_DESTINO;
    }

    public Double getC4_PRECIO_TON_CAMION() {
        return C4_PRECIO_TON_CAMION;
    }

    public void setC4_PRECIO_TON_CAMION(Double C4_PRECIO_TON_CAMION) {
        this.C4_PRECIO_TON_CAMION = C4_PRECIO_TON_CAMION;
    }

    public Double getC4_PRECIO_TON_GANDOLA() {
        return C4_PRECIO_TON_GANDOLA;
    }

    public void setC4_PRECIO_TON_GANDOLA(Double C4_PRECIO_TON_GANDOLA) {
        this.C4_PRECIO_TON_GANDOLA = C4_PRECIO_TON_GANDOLA;
    }

    public Double getC4_PRECIO_TON_TORONTO() {
        return C4_PRECIO_TON_TORONTO;
    }

    public void setC4_PRECIO_TON_TORONTO(Double C4_PRECIO_TON_TORONTO) {
        this.C4_PRECIO_TON_TORONTO = C4_PRECIO_TON_TORONTO;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

} //   Flete04.  
