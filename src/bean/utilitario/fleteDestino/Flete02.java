/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.fleteDestino;

/**
 *
 * @author henrypb
 */
public class Flete02 {
    private String C2_COD_ESTADO;  
    private String C2_NOMBRE_ESTADO;

    public Flete02(String C2_COD_ESTADO, String C2_NOMBRE_ESTADO) {
        this.C2_COD_ESTADO = C2_COD_ESTADO;
        this.C2_NOMBRE_ESTADO = C2_NOMBRE_ESTADO;
    }

    public String getC2_COD_ESTADO() {
        return C2_COD_ESTADO;
    }

    public void setC2_COD_ESTADO(String C2_COD_ESTADO) {
        this.C2_COD_ESTADO = C2_COD_ESTADO;
    }

    public String getC2_NOMBRE_ESTADO() {
        return C2_NOMBRE_ESTADO;
    }

    public void setC2_NOMBRE_ESTADO(String C2_NOMBRE_ESTADO) {
        this.C2_NOMBRE_ESTADO = C2_NOMBRE_ESTADO;
    }
    
}
