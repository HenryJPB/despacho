/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.entidad;

/**
 *
 * @author henrypb
 */
public class Guia02 {

    private String  C2_GUIA;
    private Integer C2_ITEM_NO;
    private String  C2_CODIGO;
    private String  C2_TIPO;
    private String  C2_DESCRIPCION;
    private Double  C2_PESO;
    private String  C2_NO_PEDIDO;
    private Double  C2_UNIDADES;
    private String  C2_ATADOS;
    private Double  C2_ITEMS;
    private Double  C2_PRECIO;
    private Double  C2_ALICUOTA;
    private String  C2_FXPESO;
    private String  C2_FXUNIDAD;  
    private Double  C2_PESO_GUIA;
    private String  idRow;

    //--------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    public Guia02() {
    }

    // -------------------------------------------------------------------------------------------------------------------------
    // Este CONSTRUCTOR solo aplica a la Tabla Modelo de Detalles de la Guia de Despacho para rellenar el grid de la aplicacion.  
    // -------------------------------------------------------------------------------------------------------------------------
    public Guia02(Integer C2_ITEM_NO, String C2_CODIGO, String C2_TIPO, String C2_DESCRIPCION, Double C2_PESO, String C2_NO_PEDIDO, Double C2_UNIDADES, Double C2_ITEMS, String C2_ATADOS, Double C2_PRECIO,Double C2_ALICUOTA,Double C2_PESO_GUIA, String C2_FXPESO,String C2_FXUNIDAD ) {
        this.C2_ITEM_NO = C2_ITEM_NO;
        this.C2_CODIGO = C2_CODIGO;
        this.C2_TIPO = C2_TIPO;
        this.C2_DESCRIPCION = C2_DESCRIPCION;
        this.C2_PESO = C2_PESO;
        this.C2_NO_PEDIDO = C2_NO_PEDIDO;
        this.C2_UNIDADES = C2_UNIDADES;
        this.C2_ITEMS = C2_ITEMS;
        this.C2_ATADOS = C2_ATADOS;
        this.C2_PRECIO = C2_PRECIO;
        this.C2_ALICUOTA = C2_ALICUOTA;
        this.C2_PESO_GUIA = C2_PESO_GUIA;
        this.C2_FXPESO = C2_FXPESO;
        this.C2_FXUNIDAD = C2_FXUNIDAD; 
    }

    public Guia02(String C2_GUIA, Integer C2_ITEM_NO, String C2_CODIGO, String C2_TIPO, String C2_DESCRIPCION, Double C2_PESO, String C2_NO_PEDIDO, Double C2_UNIDADES, Double C2_ITEMS, String C2_ATADOS, Double C2_PRECIO, Double C2_ALICUOTA, String C2_FXPESO,String C2_FXUNIDAD,Double C2_PESO_GUIA, String idRow) {
        this.C2_GUIA = C2_GUIA;
        this.C2_ITEM_NO = C2_ITEM_NO;
        this.C2_CODIGO = C2_CODIGO;
        this.C2_TIPO = C2_TIPO;
        this.C2_DESCRIPCION = C2_DESCRIPCION;
        this.C2_PESO = C2_PESO;
        this.C2_NO_PEDIDO = C2_NO_PEDIDO;
        this.C2_UNIDADES = C2_UNIDADES;
        this.C2_ITEMS = C2_ITEMS;
        this.C2_ATADOS = C2_ATADOS;
        this.C2_PRECIO = C2_PRECIO;
        this.C2_ALICUOTA = C2_ALICUOTA;
        this.C2_FXPESO = C2_FXPESO;
        this.C2_FXUNIDAD = C2_FXUNIDAD;  
        this.C2_PESO_GUIA = C2_PESO_GUIA;
        this.idRow = idRow;
    }

    public Double getC2_ALICUOTA() {
        return C2_ALICUOTA;
    }

    public void setC2_ALICUOTA(Double C2_ALICUOTA) {
        this.C2_ALICUOTA = C2_ALICUOTA;
    }

    public String getC2_ATADOS() {
        return C2_ATADOS;
    }

    public void setC2_ATADOS(String C2_ATADOS) {
        this.C2_ATADOS = C2_ATADOS;
    }

    public String getC2_CODIGO() {
        return C2_CODIGO;
    }

    public void setC2_CODIGO(String C2_CODIGO) {
        this.C2_CODIGO = C2_CODIGO;
    }

    public String getC2_DESCRIPCION() {
        return C2_DESCRIPCION;
    }

    public void setC2_DESCRIPCION(String C2_DESCRIPCION) {
        this.C2_DESCRIPCION = C2_DESCRIPCION;
    }

    public String getC2_FXPESO() {
        return C2_FXPESO;
    }

    public void setC2_FXPESO(String C2_FXPESO) {
        this.C2_FXPESO = C2_FXPESO;
    }

    public String getC2_FXUNIDAD() {
        return C2_FXUNIDAD;
    }

    public void setC2_FXUNIDAD(String C2_FXUNIDAD) {
        this.C2_FXUNIDAD = C2_FXUNIDAD;
    }

    public String getC2_GUIA() {
        return C2_GUIA;
    }

    public void setC2_GUIA(String C2_GUIA) {
        this.C2_GUIA = C2_GUIA;
    }

    public Integer getC2_ITEM_NO() {
        return C2_ITEM_NO;
    }

    public void setC2_ITEM_NO(Integer C2_ITEM_NO) {
        this.C2_ITEM_NO = C2_ITEM_NO;
    }

    public Double getC2_ITEMS() {
        return C2_ITEMS;
    }

    public void setC2_ITEMS(Double C2_ITEMS) {
        this.C2_ITEMS = C2_ITEMS;
    }

    public String getC2_NO_PEDIDO() {
        return C2_NO_PEDIDO;
    }

    public void setC2_NO_PEDIDO(String C2_NO_PEDIDO) {
        this.C2_NO_PEDIDO = C2_NO_PEDIDO;
    }

    public Double getC2_PESO() {
        return C2_PESO;
    }

    public void setC2_PESO(Double C2_PESO) {
        this.C2_PESO = C2_PESO;
    }

    public Double getC2_PESO_GUIA() {
        return C2_PESO_GUIA;
    }

    public void setC2_PESO_GUIA(Double C2_PESO_GUIA) {
        this.C2_PESO_GUIA = C2_PESO_GUIA;
    }

    public Double getC2_PRECIO() {
        return C2_PRECIO;
    }

    public void setC2_PRECIO(Double C2_PRECIO) {
        this.C2_PRECIO = C2_PRECIO;
    }

    public String getC2_TIPO() {
        return C2_TIPO;
    }

    public void setC2_TIPO(String C2_TIPO) {
        this.C2_TIPO = C2_TIPO;
    }

    public Double getC2_UNIDADES() {
        return C2_UNIDADES;
    }

    public void setC2_UNIDADES(Double C2_UNIDADES) {
        this.C2_UNIDADES = C2_UNIDADES;
    }

    public String getIdRow() {
        return idRow;
    }

    public void setIdRow(String idRow) {
        this.idRow = idRow;
    }
}  // Guia02.  
