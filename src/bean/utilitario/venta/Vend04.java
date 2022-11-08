/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.venta;

import java.sql.Date;

/**
 *
 * @author henrypb
 */
public class Vend04 {
     private String C3_NO_PEDIDO;  
     private java.sql.Date C3_FECHA_PEDIDO;  
     private String C3_COD_CLIENTE; 

    public Vend04(String C3_NO_PEDIDO, Date C3_FECHA_PEDIDO, String C3_COD_CLIENTE) {
        this.C3_NO_PEDIDO = C3_NO_PEDIDO;
        this.C3_FECHA_PEDIDO = C3_FECHA_PEDIDO;
        this.C3_COD_CLIENTE = C3_COD_CLIENTE;
    }

    public String getC3_NO_PEDIDO() {
        return C3_NO_PEDIDO;
    }

    public void setC3_NO_PEDIDO(String C3_NO_PEDIDO) {
        this.C3_NO_PEDIDO = C3_NO_PEDIDO;
    }

    public Date getC3_FECHA_PEDIDO() {
        return C3_FECHA_PEDIDO;
    }

    public void setC3_FECHA_PEDIDO(Date C3_FECHA_PEDIDO) {
        this.C3_FECHA_PEDIDO = C3_FECHA_PEDIDO;
    }

    public String getC3_COD_CLIENTE() {
        return C3_COD_CLIENTE;
    }

    public void setC3_COD_CLIENTE(String C3_COD_CLIENTE) {
        this.C3_COD_CLIENTE = C3_COD_CLIENTE;
    }
     
}  // Vend04.  
