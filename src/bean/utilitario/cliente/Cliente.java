/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.cliente;

/**
 *
 * @author henrypb
 * 
 */
public class Cliente {
       private String seleccionar;   // Aplicarle a este campo clase JButton setReender/setEditor.  
       private String codCliente;
       private String nombreCliente;

    public Cliente(String seleccionar, String codCliente, String nombreCliente) {
        this.seleccionar = seleccionar;
        this.codCliente = codCliente;
        this.nombreCliente = nombreCliente;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getSeleccionar() {
        return seleccionar;
    }

    public void setSeleccionar(String seleccionar) {
        this.seleccionar = seleccionar;
    }
}  // public class Cliente.  
