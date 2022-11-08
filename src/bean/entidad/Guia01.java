/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.entidad;

import java.sql.Date;

/**
 *
 * @author henrypb
 */
public class Guia01 {

    private String C1_GUIA;                                   // C.1.
    private Date C1_FECHA_GUIA;                               // C.2.  
    private String C1_CLIENTE_ESPECIAL;                       // C.3.  
    private String C1_CODIGO_CLIENTE;                         // C.4.
    private String NOMBRE_CLIENTE;                            // C.5.  
    private String C1_RAZON_SOCIAL;                           // C.6.  
    private String C1_RIF;                                    // C.7.  
    private String C1_NIT;                                    // C.8.  
    private String C1_NCF;                                    // C.9.  
    private String C1_DIR_FISCAL1;                            // C.10.   
    private String C1_DIR_FISCAL2;                            // C.11.  
    private String C1_DIR_FISCAL3;                            // C.12.  
    private String C1_RETIRADO_PLANTA;                        // C.13.  
    private String C1_ENTREGA1;                               // C.14.  
    private String C1_ENTREGA2;                               // C.15.  
    private String C1_ENTREGA3;                               // C.16.  
    private String C1_ENTREGA4;                               // C.17.  
    private String C1_ORDEN_COMPRA;                           // C.18.  
    private String C1_PEDIDO1;                                // C.19.   
    private String C1_PEDIDO2;                                // C.20.  
    private String C1_PEDIDO3;                                // C.21.  
    private String C1_PEDIDO4;                                // C.22.  
    private String C1_PEDIDO5;                                // C.25.  
    private String C1_PEDIDO6;                                // C.26.  
    private String C1_ORDEN1;                                 // C.27.
    private String C1_ORDEN2;                                 // C.28.  
    private String C1_FORMA_PAGO;                             // C.29.  
    private Integer C1_PLAZO;                                 // C.30.  
    private String C1_VENDEDOR;                               // C.31.  
    private String C1_NOMBRE_VENDEDOR;                        // C.32.  
    private String C1_ZONA;                                   // C.33.  
    private Double C1_ALICUOTA;                               // C.34.  
    private String C1_FACTURA;                                // C.35. 
    private Date   C1_FECHA_FACTURA;                          // C.36.
    private String C1_NOMBRE_CHOFER;                          // C.37.  
    private String C1_CI_CHOFER;                              // C.38. 
    private String C1_COD_TRANSP;                             // C.39.  
    private String C1_NOMBRE_TRANSP;                          // C.40. 
    private String C1_TIPO_TRANSPORTE;                        // C.41.  
    private String C1_COD_CAMION;                             // C.42. 
    private String C1_TIPO_CAMION;                            // C.43.  
    private String C1_NO_EJES;                                // C.44.  
    private Double C1_CAPACIDAD;                              // C.45.  
    private String C1_PLACA_CHUTO;                            // C.46.  
    private String C1_PLACA_BATEA;                            // C.47. 
    private Date   C1_FECHA_RELACION;                         // C.48.  
    private String C1_COD_DESTINO;                            // C.49.  
    private String C1_COD_SECTOR;                             // C.50.  
    private String C1_NOMBRE_DESTINO;                         // C.51.  
    private String C1_ESTADO;                                 // C.52.  
    private String C1_GUIA_REPARTO;                           // C.53.  
    private String C1_GUIA_ORIGEN_REPARTO;                    // C.54. 
    private String C1_SERIAL_TICKET1;                         // C.55.  
    private Double C1_PESO_TARA;                              // C.56.  
    private String C1_SERIAL_TICKET2;                         // C.57.  
    private Double C1_PESO_BRUTO;                             // C.58. 
    private String C1_STATUS;                                 // C.59.  
    private String C1_OBSERVACION;                            // C.60.  
    private String C1_OBSERVACION_ROMANA1;                    // C.61. 
    private String C1_FLETE_PROCESADO;                        // C.62. 
    private String rowId;                                     // C.63. 

    public Guia01() {
        // **
    }

    public Guia01(String C1_GUIA, Date C1_FECHA_GUIA, String C1_NCF) {
        this.C1_GUIA = C1_GUIA;
        this.C1_FECHA_GUIA = C1_FECHA_GUIA;
        this.C1_NCF = C1_NCF;
    }

    //--------------------------------------------------------------------------
    // Constructor: Realizar Consulta de la Guia.  
    //--------------------------------------------------------------------------
    /* 
    public Guia01-1(String C1_GUIA, Date C1_FECHA_GUIA, String C1_CLIENTE_ESPECIAL, String C1_CODIGO_CLIENTE, String NOMBRE_CLIENTE, String C1_RAZON_SOCIAL, String C1_RIF, String C1_NIT, String C1_NCF, String C1_DIR_FISCAL1, String C1_DIR_FISCAL2, String C1_DIR_FISCAL3, String C1_RETIRADO_PLANTA, String C1_ENTREGA1, String C1_ENTREGA2, String C1_ENTREGA3, String C1_ENTREGA4, String C1_ORDEN_COMPRA, String C1_PEDIDO1, String C1_PEDIDO2, String C1_PEDIDO3, String C1_PEDIDO4, String C1_PEDIDO5, String C1_PEDIDO6, String C1_ORDEN1, String C1_ORDEN2, String C1_FORMA_PAGO, Integer C1_PLAZO, String C1_VENDEDOR, String C1_NOMBRE_VENDEDOR, String C1_ZONA, Double C1_ALICUOTA, String C1_FACTURA, Date C1_FECHA_FACTURA, String C1_NOMBRE_CHOFER, String C1_CI_CHOFER, String C1_COD_TRANSP, String C1_NOMBRE_TRANSP, String C1_TIPO_TRANSPORTE, String C1_COD_CAMION, String C1_TIPO_CAMION, String C1_NO_EJES, Double C1_CAPACIDAD, String C1_PLACA_CHUTO, String C1_PLACA_BATEA, Date C1_FECHA_RELACION, String C1_COD_DESTINO, String C1_NOMBRE_DESTINO, String C1_ESTADO, String C1_GUIA_REPARTO, String C1_GUIA_ORIGEN_REPARTO, String C1_SERIAL_TICKET1, Double C1_PESO_TARA, String C1_SERIAL_TICKET2, Double C1_PESO_BRUTO, String C1_STATUS, String C1_OBSERVACION, String C1_OBSERVACION_ROMANA1, String C1_FLETE_PROCESADO, String rowId) {
        this.C1_GUIA = C1_GUIA;
        this.C1_FECHA_GUIA = C1_FECHA_GUIA;
        this.C1_CLIENTE_ESPECIAL = C1_CLIENTE_ESPECIAL;
        this.C1_CODIGO_CLIENTE = C1_CODIGO_CLIENTE;
        this.NOMBRE_CLIENTE = NOMBRE_CLIENTE;
        this.C1_RAZON_SOCIAL = C1_RAZON_SOCIAL;
        this.C1_RIF = C1_RIF;
        this.C1_NIT = C1_NIT;
        this.C1_NCF = C1_NCF;
        this.C1_DIR_FISCAL1 = C1_DIR_FISCAL1;
        this.C1_DIR_FISCAL2 = C1_DIR_FISCAL2;
        this.C1_DIR_FISCAL3 = C1_DIR_FISCAL3;
        this.C1_RETIRADO_PLANTA = C1_RETIRADO_PLANTA;
        this.C1_ENTREGA1 = C1_ENTREGA1;
        this.C1_ENTREGA2 = C1_ENTREGA2;
        this.C1_ENTREGA3 = C1_ENTREGA3;
        this.C1_ENTREGA4 = C1_ENTREGA4;
        this.C1_ORDEN_COMPRA = C1_ORDEN_COMPRA;
        this.C1_PEDIDO1 = C1_PEDIDO1;
        this.C1_PEDIDO2 = C1_PEDIDO2;
        this.C1_PEDIDO3 = C1_PEDIDO3;
        this.C1_PEDIDO4 = C1_PEDIDO4;
        this.C1_PEDIDO5 = C1_PEDIDO5;
        this.C1_PEDIDO6 = C1_PEDIDO6;
        this.C1_ORDEN1 = C1_ORDEN1;
        this.C1_ORDEN2 = C1_ORDEN2;
        this.C1_FORMA_PAGO = C1_FORMA_PAGO;
        this.C1_PLAZO = C1_PLAZO;
        this.C1_VENDEDOR = C1_VENDEDOR;
        this.C1_NOMBRE_VENDEDOR = C1_NOMBRE_VENDEDOR;
        this.C1_ZONA = C1_ZONA;
        this.C1_ALICUOTA = C1_ALICUOTA;
        this.C1_FACTURA = C1_FACTURA;
        this.C1_FECHA_FACTURA = C1_FECHA_FACTURA;
        this.C1_NOMBRE_CHOFER = C1_NOMBRE_CHOFER;
        this.C1_CI_CHOFER = C1_CI_CHOFER;
        this.C1_COD_TRANSP = C1_COD_TRANSP;
        this.C1_NOMBRE_TRANSP = C1_NOMBRE_TRANSP;
        this.C1_TIPO_TRANSPORTE = C1_TIPO_TRANSPORTE;
        this.C1_COD_CAMION = C1_COD_CAMION;
        this.C1_TIPO_CAMION = C1_TIPO_CAMION;
        this.C1_NO_EJES = C1_NO_EJES;
        this.C1_CAPACIDAD = C1_CAPACIDAD;
        this.C1_PLACA_CHUTO = C1_PLACA_CHUTO;
        this.C1_PLACA_BATEA = C1_PLACA_BATEA;
        this.C1_FECHA_RELACION = C1_FECHA_RELACION;
        this.C1_COD_DESTINO = C1_COD_DESTINO;
        this.C1_NOMBRE_DESTINO = C1_NOMBRE_DESTINO;
        this.C1_ESTADO = C1_ESTADO;
        this.C1_GUIA_REPARTO = C1_GUIA_REPARTO;
        this.C1_GUIA_ORIGEN_REPARTO = C1_GUIA_ORIGEN_REPARTO;
        this.C1_SERIAL_TICKET1 = C1_SERIAL_TICKET1;
        this.C1_PESO_TARA = C1_PESO_TARA;
        this.C1_SERIAL_TICKET2 = C1_SERIAL_TICKET2;
        this.C1_PESO_BRUTO = C1_PESO_BRUTO;
        this.C1_STATUS = C1_STATUS;
        this.C1_OBSERVACION = C1_OBSERVACION;
        this.C1_OBSERVACION_ROMANA1 = C1_OBSERVACION_ROMANA1;
        this.C1_FLETE_PROCESADO = C1_FLETE_PROCESADO;
        this.rowId = rowId;
    }
    */
    //--------------------------------------------------------------------------
    // Constructor: Realizar Consulta de la Guia.  
    //--------------------------------------------------------------------------
    public Guia01(String C1_GUIA, Date C1_FECHA_GUIA, String C1_CLIENTE_ESPECIAL, String C1_CODIGO_CLIENTE, String NOMBRE_CLIENTE, String C1_RAZON_SOCIAL, String C1_RIF, String C1_NIT, String C1_NCF, String C1_DIR_FISCAL1, String C1_DIR_FISCAL2, String C1_DIR_FISCAL3, String C1_RETIRADO_PLANTA, String C1_ENTREGA1, String C1_ENTREGA2, String C1_ENTREGA3, String C1_ENTREGA4, String C1_ORDEN_COMPRA, String C1_PEDIDO1, String C1_PEDIDO2, String C1_PEDIDO3, String C1_PEDIDO4, String C1_PEDIDO5, String C1_PEDIDO6, String C1_ORDEN1, String C1_ORDEN2, String C1_FORMA_PAGO, Integer C1_PLAZO, String C1_VENDEDOR, String C1_NOMBRE_VENDEDOR, String C1_ZONA, Double C1_ALICUOTA, String C1_FACTURA, Date C1_FECHA_FACTURA, String C1_NOMBRE_CHOFER, String C1_CI_CHOFER, String C1_COD_TRANSP, String C1_NOMBRE_TRANSP, String C1_TIPO_TRANSPORTE, String C1_COD_CAMION, String C1_TIPO_CAMION, String C1_NO_EJES, Double C1_CAPACIDAD, String C1_PLACA_CHUTO, String C1_PLACA_BATEA, Date C1_FECHA_RELACION, String C1_COD_DESTINO, String C1_COD_SECTOR, String C1_NOMBRE_DESTINO, String C1_ESTADO, String C1_GUIA_REPARTO, String C1_GUIA_ORIGEN_REPARTO, String C1_SERIAL_TICKET1, Double C1_PESO_TARA, String C1_SERIAL_TICKET2, Double C1_PESO_BRUTO, String C1_STATUS, String C1_OBSERVACION, String C1_OBSERVACION_ROMANA1, String C1_FLETE_PROCESADO, String rowId) {
        this.C1_GUIA = C1_GUIA;
        this.C1_FECHA_GUIA = C1_FECHA_GUIA;
        this.C1_CLIENTE_ESPECIAL = C1_CLIENTE_ESPECIAL;
        this.C1_CODIGO_CLIENTE = C1_CODIGO_CLIENTE;
        this.NOMBRE_CLIENTE = NOMBRE_CLIENTE;
        this.C1_RAZON_SOCIAL = C1_RAZON_SOCIAL;
        this.C1_RIF = C1_RIF;
        this.C1_NIT = C1_NIT;
        this.C1_NCF = C1_NCF;
        this.C1_DIR_FISCAL1 = C1_DIR_FISCAL1;
        this.C1_DIR_FISCAL2 = C1_DIR_FISCAL2;
        this.C1_DIR_FISCAL3 = C1_DIR_FISCAL3;
        this.C1_RETIRADO_PLANTA = C1_RETIRADO_PLANTA;
        this.C1_ENTREGA1 = C1_ENTREGA1;
        this.C1_ENTREGA2 = C1_ENTREGA2;
        this.C1_ENTREGA3 = C1_ENTREGA3;
        this.C1_ENTREGA4 = C1_ENTREGA4;
        this.C1_ORDEN_COMPRA = C1_ORDEN_COMPRA;
        this.C1_PEDIDO1 = C1_PEDIDO1;
        this.C1_PEDIDO2 = C1_PEDIDO2;
        this.C1_PEDIDO3 = C1_PEDIDO3;
        this.C1_PEDIDO4 = C1_PEDIDO4;
        this.C1_PEDIDO5 = C1_PEDIDO5;
        this.C1_PEDIDO6 = C1_PEDIDO6;
        this.C1_ORDEN1 = C1_ORDEN1;
        this.C1_ORDEN2 = C1_ORDEN2;
        this.C1_FORMA_PAGO = C1_FORMA_PAGO;
        this.C1_PLAZO = C1_PLAZO;
        this.C1_VENDEDOR = C1_VENDEDOR;
        this.C1_NOMBRE_VENDEDOR = C1_NOMBRE_VENDEDOR;
        this.C1_ZONA = C1_ZONA;
        this.C1_ALICUOTA = C1_ALICUOTA;
        this.C1_FACTURA = C1_FACTURA;
        this.C1_FECHA_FACTURA = C1_FECHA_FACTURA;
        this.C1_NOMBRE_CHOFER = C1_NOMBRE_CHOFER;
        this.C1_CI_CHOFER = C1_CI_CHOFER;
        this.C1_COD_TRANSP = C1_COD_TRANSP;
        this.C1_NOMBRE_TRANSP = C1_NOMBRE_TRANSP;
        this.C1_TIPO_TRANSPORTE = C1_TIPO_TRANSPORTE;
        this.C1_COD_CAMION = C1_COD_CAMION;
        this.C1_TIPO_CAMION = C1_TIPO_CAMION;
        this.C1_NO_EJES = C1_NO_EJES;
        this.C1_CAPACIDAD = C1_CAPACIDAD;
        this.C1_PLACA_CHUTO = C1_PLACA_CHUTO;
        this.C1_PLACA_BATEA = C1_PLACA_BATEA;
        this.C1_FECHA_RELACION = C1_FECHA_RELACION;
        this.C1_COD_DESTINO = C1_COD_DESTINO;
        this.C1_COD_SECTOR = C1_COD_SECTOR;
        this.C1_NOMBRE_DESTINO = C1_NOMBRE_DESTINO;
        this.C1_ESTADO = C1_ESTADO;
        this.C1_GUIA_REPARTO = C1_GUIA_REPARTO;
        this.C1_GUIA_ORIGEN_REPARTO = C1_GUIA_ORIGEN_REPARTO;
        this.C1_SERIAL_TICKET1 = C1_SERIAL_TICKET1;
        this.C1_PESO_TARA = C1_PESO_TARA;
        this.C1_SERIAL_TICKET2 = C1_SERIAL_TICKET2;
        this.C1_PESO_BRUTO = C1_PESO_BRUTO;
        this.C1_STATUS = C1_STATUS;
        this.C1_OBSERVACION = C1_OBSERVACION;
        this.C1_OBSERVACION_ROMANA1 = C1_OBSERVACION_ROMANA1;
        this.C1_FLETE_PROCESADO = C1_FLETE_PROCESADO;
        this.rowId = rowId;
    }

    //--------------------------------------------------------------------------
    // Constructor: Actualizar x Inclusion o Modificacion de la B.D.  
    //--------------------------------------------------------------------------
    /*
    public Guia01(String C1_GUIA, Date C1_FECHA_GUIA, String C1_CLIENTE_ESPECIAL, String C1_CODIGO_CLIENTE, String C1_RAZON_SOCIAL, String C1_RIF, String C1_NIT, String C1_NCF, String C1_DIR_FISCAL1, String C1_DIR_FISCAL2, String C1_DIR_FISCAL3, String C1_RETIRADO_PLANTA, String C1_ENTREGA1, String C1_ENTREGA2, String C1_ENTREGA3, String C1_ENTREGA4, String C1_ORDEN_COMPRA, String C1_PEDIDO1, String C1_PEDIDO2, String C1_PEDIDO3, String C1_PEDIDO4, String C1_PEDIDO5, String C1_PEDIDO6, String C1_ORDEN1, String C1_ORDEN2, String C1_FORMA_PAGO, Integer C1_PLAZO, String C1_VENDEDOR, String C1_NOMBRE_VENDEDOR, String C1_ZONA, Double C1_ALICUOTA, String C1_FACTURA, Date C1_FECHA_FACTURA, String C1_NOMBRE_CHOFER, String C1_CI_CHOFER, String C1_COD_TRANSP, String C1_NOMBRE_TRANSP, String C1_TIPO_TRANSPORTE, String C1_COD_CAMION, String C1_TIPO_CAMION, String C1_NO_EJES, Double C1_CAPACIDAD, String C1_PLACA_CHUTO, String C1_PLACA_BATEA, Date C1_FECHA_RELACION, String C1_COD_DESTINO, String C1_NOMBRE_DESTINO, String C1_ESTADO, String C1_GUIA_REPARTO, String C1_GUIA_ORIGEN_REPARTO, String C1_SERIAL_TICKET1, Double C1_PESO_TARA, String C1_SERIAL_TICKET2, Double C1_PESO_BRUTO, String C1_STATUS, String C1_OBSERVACION, String C1_OBSERVACION_ROMANA1, String C1_FLETE_PROCESADO) {
        this.C1_GUIA = C1_GUIA;
        this.C1_FECHA_GUIA = C1_FECHA_GUIA;
        this.C1_CLIENTE_ESPECIAL = C1_CLIENTE_ESPECIAL;
        this.C1_CODIGO_CLIENTE = C1_CODIGO_CLIENTE;
        this.C1_RAZON_SOCIAL = C1_RAZON_SOCIAL;
        this.C1_RIF = C1_RIF;
        this.C1_NIT = C1_NIT;
        this.C1_NCF = C1_NCF;
        this.C1_DIR_FISCAL1 = C1_DIR_FISCAL1;
        this.C1_DIR_FISCAL2 = C1_DIR_FISCAL2;
        this.C1_DIR_FISCAL3 = C1_DIR_FISCAL3;
        this.C1_RETIRADO_PLANTA = C1_RETIRADO_PLANTA;
        this.C1_ENTREGA1 = C1_ENTREGA1;
        this.C1_ENTREGA2 = C1_ENTREGA2;
        this.C1_ENTREGA3 = C1_ENTREGA3;
        this.C1_ENTREGA4 = C1_ENTREGA4;
        this.C1_ORDEN_COMPRA = C1_ORDEN_COMPRA;
        this.C1_PEDIDO1 = C1_PEDIDO1;
        this.C1_PEDIDO2 = C1_PEDIDO2;
        this.C1_PEDIDO3 = C1_PEDIDO3;
        this.C1_PEDIDO4 = C1_PEDIDO4;
        this.C1_PEDIDO5 = C1_PEDIDO5;
        this.C1_PEDIDO6 = C1_PEDIDO6;
        this.C1_ORDEN1 = C1_ORDEN1;
        this.C1_ORDEN2 = C1_ORDEN2;
        this.C1_FORMA_PAGO = C1_FORMA_PAGO;
        this.C1_PLAZO = C1_PLAZO;
        this.C1_VENDEDOR = C1_VENDEDOR;
        this.C1_NOMBRE_VENDEDOR = C1_NOMBRE_VENDEDOR;
        this.C1_ZONA = C1_ZONA;
        this.C1_ALICUOTA = C1_ALICUOTA;
        this.C1_FACTURA = C1_FACTURA;
        this.C1_FECHA_FACTURA = C1_FECHA_FACTURA;
        this.C1_NOMBRE_CHOFER = C1_NOMBRE_CHOFER;
        this.C1_CI_CHOFER = C1_CI_CHOFER;
        this.C1_COD_TRANSP = C1_COD_TRANSP;
        this.C1_NOMBRE_TRANSP = C1_NOMBRE_TRANSP;
        this.C1_TIPO_TRANSPORTE = C1_TIPO_TRANSPORTE;
        this.C1_COD_CAMION = C1_COD_CAMION;
        this.C1_TIPO_CAMION = C1_TIPO_CAMION;
        this.C1_NO_EJES = C1_NO_EJES;
        this.C1_CAPACIDAD = C1_CAPACIDAD;
        this.C1_PLACA_CHUTO = C1_PLACA_CHUTO;
        this.C1_PLACA_BATEA = C1_PLACA_BATEA;
        this.C1_FECHA_RELACION = C1_FECHA_RELACION;
        this.C1_COD_DESTINO = C1_COD_DESTINO;
        this.C1_NOMBRE_DESTINO = C1_NOMBRE_DESTINO;
        this.C1_ESTADO = C1_ESTADO;
        this.C1_GUIA_REPARTO = C1_GUIA_REPARTO;
        this.C1_GUIA_ORIGEN_REPARTO = C1_GUIA_ORIGEN_REPARTO;
        this.C1_SERIAL_TICKET1 = C1_SERIAL_TICKET1;
        this.C1_PESO_TARA = C1_PESO_TARA;
        this.C1_SERIAL_TICKET2 = C1_SERIAL_TICKET2;
        this.C1_PESO_BRUTO = C1_PESO_BRUTO;
        this.C1_STATUS = C1_STATUS;
        this.C1_OBSERVACION = C1_OBSERVACION;
        this.C1_OBSERVACION_ROMANA1 = C1_OBSERVACION_ROMANA1;
        this.C1_FLETE_PROCESADO = C1_FLETE_PROCESADO;
    }
    */
    
    //--------------------------------------------------------------------------
    // Constructor: Actualizar x Inclusion o Modificacion de la B.D.  
    //--------------------------------------------------------------------------
    public Guia01(String C1_GUIA, Date C1_FECHA_GUIA, String C1_CLIENTE_ESPECIAL, String C1_CODIGO_CLIENTE, String C1_RAZON_SOCIAL, String C1_RIF, String C1_NIT, String C1_NCF, String C1_DIR_FISCAL1, String C1_DIR_FISCAL2, String C1_DIR_FISCAL3, String C1_RETIRADO_PLANTA, String C1_ENTREGA1, String C1_ENTREGA2, String C1_ENTREGA3, String C1_ENTREGA4, String C1_ORDEN_COMPRA, String C1_PEDIDO1, String C1_PEDIDO2, String C1_PEDIDO3, String C1_PEDIDO4, String C1_PEDIDO5, String C1_PEDIDO6, String C1_ORDEN1, String C1_ORDEN2, String C1_FORMA_PAGO, Integer C1_PLAZO, String C1_VENDEDOR, String C1_NOMBRE_VENDEDOR, String C1_ZONA, Double C1_ALICUOTA, String C1_FACTURA, Date C1_FECHA_FACTURA, String C1_NOMBRE_CHOFER, String C1_CI_CHOFER, String C1_COD_TRANSP, String C1_NOMBRE_TRANSP, String C1_TIPO_TRANSPORTE, String C1_COD_CAMION, String C1_TIPO_CAMION, String C1_NO_EJES, Double C1_CAPACIDAD, String C1_PLACA_CHUTO, String C1_PLACA_BATEA, Date C1_FECHA_RELACION, String C1_COD_DESTINO, String C1_COD_SECTOR, String C1_NOMBRE_DESTINO, String C1_ESTADO, String C1_GUIA_REPARTO, String C1_GUIA_ORIGEN_REPARTO, String C1_SERIAL_TICKET1, Double C1_PESO_TARA, String C1_SERIAL_TICKET2, Double C1_PESO_BRUTO, String C1_STATUS, String C1_OBSERVACION, String C1_OBSERVACION_ROMANA1, String C1_FLETE_PROCESADO) {
        this.C1_GUIA = C1_GUIA;
        this.C1_FECHA_GUIA = C1_FECHA_GUIA;
        this.C1_CLIENTE_ESPECIAL = C1_CLIENTE_ESPECIAL;
        this.C1_CODIGO_CLIENTE = C1_CODIGO_CLIENTE;
        this.C1_RAZON_SOCIAL = C1_RAZON_SOCIAL;
        this.C1_RIF = C1_RIF;
        this.C1_NIT = C1_NIT;
        this.C1_NCF = C1_NCF;
        this.C1_DIR_FISCAL1 = C1_DIR_FISCAL1;
        this.C1_DIR_FISCAL2 = C1_DIR_FISCAL2;
        this.C1_DIR_FISCAL3 = C1_DIR_FISCAL3;
        this.C1_RETIRADO_PLANTA = C1_RETIRADO_PLANTA;
        this.C1_ENTREGA1 = C1_ENTREGA1;
        this.C1_ENTREGA2 = C1_ENTREGA2;
        this.C1_ENTREGA3 = C1_ENTREGA3;
        this.C1_ENTREGA4 = C1_ENTREGA4;
        this.C1_ORDEN_COMPRA = C1_ORDEN_COMPRA;
        this.C1_PEDIDO1 = C1_PEDIDO1;
        this.C1_PEDIDO2 = C1_PEDIDO2;
        this.C1_PEDIDO3 = C1_PEDIDO3;
        this.C1_PEDIDO4 = C1_PEDIDO4;
        this.C1_PEDIDO5 = C1_PEDIDO5;
        this.C1_PEDIDO6 = C1_PEDIDO6;
        this.C1_ORDEN1 = C1_ORDEN1;
        this.C1_ORDEN2 = C1_ORDEN2;
        this.C1_FORMA_PAGO = C1_FORMA_PAGO;
        this.C1_PLAZO = C1_PLAZO;
        this.C1_VENDEDOR = C1_VENDEDOR;
        this.C1_NOMBRE_VENDEDOR = C1_NOMBRE_VENDEDOR;
        this.C1_ZONA = C1_ZONA;
        this.C1_ALICUOTA = C1_ALICUOTA;
        this.C1_FACTURA = C1_FACTURA;
        this.C1_FECHA_FACTURA = C1_FECHA_FACTURA;
        this.C1_NOMBRE_CHOFER = C1_NOMBRE_CHOFER;
        this.C1_CI_CHOFER = C1_CI_CHOFER;
        this.C1_COD_TRANSP = C1_COD_TRANSP;
        this.C1_NOMBRE_TRANSP = C1_NOMBRE_TRANSP;
        this.C1_TIPO_TRANSPORTE = C1_TIPO_TRANSPORTE;
        this.C1_COD_CAMION = C1_COD_CAMION;
        this.C1_TIPO_CAMION = C1_TIPO_CAMION;
        this.C1_NO_EJES = C1_NO_EJES;
        this.C1_CAPACIDAD = C1_CAPACIDAD;
        this.C1_PLACA_CHUTO = C1_PLACA_CHUTO;
        this.C1_PLACA_BATEA = C1_PLACA_BATEA;
        this.C1_FECHA_RELACION = C1_FECHA_RELACION;
        this.C1_COD_DESTINO = C1_COD_DESTINO;
        this.C1_COD_SECTOR = C1_COD_SECTOR;
        this.C1_NOMBRE_DESTINO = C1_NOMBRE_DESTINO;
        this.C1_ESTADO = C1_ESTADO;
        this.C1_GUIA_REPARTO = C1_GUIA_REPARTO;
        this.C1_GUIA_ORIGEN_REPARTO = C1_GUIA_ORIGEN_REPARTO;
        this.C1_SERIAL_TICKET1 = C1_SERIAL_TICKET1;
        this.C1_PESO_TARA = C1_PESO_TARA;
        this.C1_SERIAL_TICKET2 = C1_SERIAL_TICKET2;
        this.C1_PESO_BRUTO = C1_PESO_BRUTO;
        this.C1_STATUS = C1_STATUS;
        this.C1_OBSERVACION = C1_OBSERVACION;
        this.C1_OBSERVACION_ROMANA1 = C1_OBSERVACION_ROMANA1;
        this.C1_FLETE_PROCESADO = C1_FLETE_PROCESADO;
    }
    
    public Double getC1_ALICUOTA() {
        return C1_ALICUOTA;
    }

    public void setC1_ALICUOTA(Double C1_ALICUOTA) {
        this.C1_ALICUOTA = C1_ALICUOTA;
    }

    public Double getC1_CAPACIDAD() {
        return C1_CAPACIDAD;
    }

    public void setC1_CAPACIDAD(Double C1_CAPACIDAD) {
        this.C1_CAPACIDAD = C1_CAPACIDAD;
    }

    public String getC1_CI_CHOFER() {
        return C1_CI_CHOFER;
    }

    public void setC1_CI_CHOFER(String C1_CI_CHOFER) {
        this.C1_CI_CHOFER = C1_CI_CHOFER;
    }

    public String getC1_CLIENTE_ESPECIAL() {
        return C1_CLIENTE_ESPECIAL;
    }

    public void setC1_CLIENTE_ESPECIAL(String C1_CLIENTE_ESPECIAL) {
        this.C1_CLIENTE_ESPECIAL = C1_CLIENTE_ESPECIAL;
    }

    public String getC1_CODIGO_CLIENTE() {
        return C1_CODIGO_CLIENTE;
    }

    public void setC1_CODIGO_CLIENTE(String C1_CODIGO_CLIENTE) {
        this.C1_CODIGO_CLIENTE = C1_CODIGO_CLIENTE;
    }

    public String getNOMBRE_CLIENTE() {
        return NOMBRE_CLIENTE;
    }

    public void setNOMBRE_CLIENTE(String NOMBRE_CLIENTE) {
        this.NOMBRE_CLIENTE = NOMBRE_CLIENTE;
    }

    public String getC1_COD_CAMION() {
        return C1_COD_CAMION;
    }

    public void setC1_COD_CAMION(String C1_COD_CAMION) {
        this.C1_COD_CAMION = C1_COD_CAMION;
    }

    public String getC1_COD_DESTINO() {
        return C1_COD_DESTINO;
    }

    public void setC1_COD_DESTINO(String C1_COD_DESTINO) {
        this.C1_COD_DESTINO = C1_COD_DESTINO;
    }

    public String getC1_COD_SECTOR() {
        return C1_COD_SECTOR;
    }

    public void setC1_COD_SECTOR(String C1_COD_SECTOR) {
        this.C1_COD_SECTOR = C1_COD_SECTOR;
    }

    public String getC1_COD_TRANSP() {
        return C1_COD_TRANSP;
    }

    public void setC1_COD_TRANSP(String C1_COD_TRANSP) {
        this.C1_COD_TRANSP = C1_COD_TRANSP;
    }

    public String getC1_DIR_FISCAL1() {
        return C1_DIR_FISCAL1;
    }

    public void setC1_DIR_FISCAL1(String C1_DIR_FISCAL1) {
        this.C1_DIR_FISCAL1 = C1_DIR_FISCAL1;
    }

    public String getC1_DIR_FISCAL2() {
        return C1_DIR_FISCAL2;
    }

    public void setC1_DIR_FISCAL2(String C1_DIR_FISCAL2) {
        this.C1_DIR_FISCAL2 = C1_DIR_FISCAL2;
    }

    public String getC1_DIR_FISCAL3() {
        return C1_DIR_FISCAL3;
    }

    public void setC1_DIR_FISCAL3(String C1_DIR_FISCAL3) {
        this.C1_DIR_FISCAL3 = C1_DIR_FISCAL3;
    }

    public String getC1_ENTREGA1() {
        return C1_ENTREGA1;
    }

    public void setC1_ENTREGA1(String C1_ENTREGA1) {
        this.C1_ENTREGA1 = C1_ENTREGA1;
    }

    public String getC1_ENTREGA2() {
        return C1_ENTREGA2;
    }

    public void setC1_ENTREGA2(String C1_ENTREGA2) {
        this.C1_ENTREGA2 = C1_ENTREGA2;
    }

    public String getC1_ENTREGA3() {
        return C1_ENTREGA3;
    }

    public void setC1_ENTREGA3(String C1_ENTREGA3) {
        this.C1_ENTREGA3 = C1_ENTREGA3;
    }

    public String getC1_ENTREGA4() {
        return C1_ENTREGA4;
    }

    public void setC1_ENTREGA4(String C1_ENTREGA4) {
        this.C1_ENTREGA4 = C1_ENTREGA4;
    }

    public String getC1_FACTURA() {
        return C1_FACTURA;
    }

    public void setC1_FACTURA(String C1_FACTURA) {
        this.C1_FACTURA = C1_FACTURA;
    }

    public Date getC1_FECHA_FACTURA() {
        return C1_FECHA_FACTURA;
    }

    public void setC1_FECHA_FACTURA(Date C1_FECHA_FACTURA) {
        this.C1_FECHA_FACTURA = C1_FECHA_FACTURA;
    }

    public Date getC1_FECHA_GUIA() {
        return C1_FECHA_GUIA;
    }

    public void setC1_FECHA_GUIA(Date C1_FECHA_GUIA) {
        this.C1_FECHA_GUIA = C1_FECHA_GUIA;
    }

    public Date getC1_FECHA_RELACION() {
        return C1_FECHA_RELACION;
    }

    public void setC1_FECHA_RELACION(Date C1_FECHA_RELACION) {
        this.C1_FECHA_RELACION = C1_FECHA_RELACION;
    }

    public String getC1_FLETE_PROCESADO() {
        return C1_FLETE_PROCESADO;
    }

    public void setC1_FLETE_PROCESADO(String C1_FLETE_PROCESADO) {
        this.C1_FLETE_PROCESADO = C1_FLETE_PROCESADO;
    }

    public String getC1_FORMA_PAGO() {
        return C1_FORMA_PAGO;
    }

    public void setC1_FORMA_PAGO(String C1_FORMA_PAGO) {
        this.C1_FORMA_PAGO = C1_FORMA_PAGO;
    }

    public String getC1_GUIA() {
        return C1_GUIA;
    }

    public void setC1_GUIA(String C1_GUIA) {
        this.C1_GUIA = C1_GUIA;
    }

    public String getC1_GUIA_ORIGEN_REPARTO() {
        return C1_GUIA_ORIGEN_REPARTO;
    }

    public void setC1_GUIA_ORIGEN_REPARTO(String C1_GUIA_ORIGEN_REPARTO) {
        this.C1_GUIA_ORIGEN_REPARTO = C1_GUIA_ORIGEN_REPARTO;
    }

    public String getC1_GUIA_REPARTO() {
        return C1_GUIA_REPARTO;
    }

    public void setC1_GUIA_REPARTO(String C1_GUIA_REPARTO) {
        this.C1_GUIA_REPARTO = C1_GUIA_REPARTO;
    }

    public String getC1_NCF() {
        return C1_NCF;
    }

    public void setC1_NCF(String C1_NCF) {
        this.C1_NCF = C1_NCF;
    }

    public String getC1_NIT() {
        return C1_NIT;
    }

    public void setC1_NIT(String C1_NIT) {
        this.C1_NIT = C1_NIT;
    }

    public String getC1_NOMBRE_CHOFER() {
        return C1_NOMBRE_CHOFER;
    }

    public void setC1_NOMBRE_CHOFER(String C1_NOMBRE_CHOFER) {
        this.C1_NOMBRE_CHOFER = C1_NOMBRE_CHOFER;
    }

    public String getC1_NOMBRE_DESTINO() {
        return C1_NOMBRE_DESTINO;
    }

    public void setC1_NOMBRE_DESTINO(String C1_NOMBRE_DESTINO) {
        this.C1_NOMBRE_DESTINO = C1_NOMBRE_DESTINO;
    }

    public String getC1_ESTADO() {
        return C1_ESTADO;
    }

    public void setC1_ESTADO(String C1_ESTADO) {
        this.C1_ESTADO = C1_ESTADO;
    }

    public String getC1_NOMBRE_TRANSP() {
        return C1_NOMBRE_TRANSP;
    }

    public void setC1_NOMBRE_TRANSP(String C1_NOMBRE_TRANSP) {
        this.C1_NOMBRE_TRANSP = C1_NOMBRE_TRANSP;
    }

    public String getC1_NOMBRE_VENDEDOR() {
        return C1_NOMBRE_VENDEDOR;
    }

    public void setC1_NOMBRE_VENDEDOR(String C1_NOMBRE_VENDEDOR) {
        this.C1_NOMBRE_VENDEDOR = C1_NOMBRE_VENDEDOR;
    }

    public String getC1_NO_EJES() {
        return C1_NO_EJES;
    }

    public void setC1_NO_EJES(String C1_NO_EJES) {
        this.C1_NO_EJES = C1_NO_EJES;
    }

    public String getC1_OBSERVACION() {
        return C1_OBSERVACION;
    }

    public void setC1_OBSERVACION(String C1_OBSERVACION) {
        this.C1_OBSERVACION = C1_OBSERVACION;
    }

    public String getC1_OBSERVACION_ROMANA1() {
        return C1_OBSERVACION_ROMANA1;
    }

    public void setC1_OBSERVACION_ROMANA1(String C1_OBSERVACION_ROMANA1) {
        this.C1_OBSERVACION_ROMANA1 = C1_OBSERVACION_ROMANA1;
    }

    public String getC1_ORDEN1() {
        return C1_ORDEN1;
    }

    public void setC1_ORDEN1(String C1_ORDEN1) {
        this.C1_ORDEN1 = C1_ORDEN1;
    }

    public String getC1_ORDEN2() {
        return C1_ORDEN2;
    }

    public void setC1_ORDEN2(String C1_ORDEN2) {
        this.C1_ORDEN2 = C1_ORDEN2;
    }

    public String getC1_ORDEN_COMPRA() {
        return C1_ORDEN_COMPRA;
    }

    public void setC1_ORDEN_COMPRA(String C1_ORDEN_COMPRA) {
        this.C1_ORDEN_COMPRA = C1_ORDEN_COMPRA;
    }

    public String getC1_PEDIDO1() {
        return C1_PEDIDO1;
    }

    public void setC1_PEDIDO1(String C1_PEDIDO1) {
        this.C1_PEDIDO1 = C1_PEDIDO1;
    }

    public String getC1_PEDIDO2() {
        return C1_PEDIDO2;
    }

    public void setC1_PEDIDO2(String C1_PEDIDO2) {
        this.C1_PEDIDO2 = C1_PEDIDO2;
    }

    public String getC1_PEDIDO3() {
        return C1_PEDIDO3;
    }

    public void setC1_PEDIDO3(String C1_PEDIDO3) {
        this.C1_PEDIDO3 = C1_PEDIDO3;
    }

    public String getC1_PEDIDO4() {
        return C1_PEDIDO4;
    }

    public void setC1_PEDIDO4(String C1_PEDIDO4) {
        this.C1_PEDIDO4 = C1_PEDIDO4;
    }

    public String getC1_PEDIDO5() {
        return C1_PEDIDO5;
    }

    public void setC1_PEDIDO5(String C1_PEDIDO5) {
        this.C1_PEDIDO5 = C1_PEDIDO5;
    }

    public String getC1_PEDIDO6() {
        return C1_PEDIDO6;
    }

    public void setC1_PEDIDO6(String C1_PEDIDO6) {
        this.C1_PEDIDO6 = C1_PEDIDO6;
    }

    public Double getC1_PESO_BRUTO() {
        return C1_PESO_BRUTO;
    }

    public void setC1_PESO_BRUTO(Double C1_PESO_BRUTO) {
        this.C1_PESO_BRUTO = C1_PESO_BRUTO;
    }

    public Double getC1_PESO_TARA() {
        return C1_PESO_TARA;
    }

    public void setC1_PESO_TARA(Double C1_PESO_TARA) {
        this.C1_PESO_TARA = C1_PESO_TARA;
    }

    public String getC1_PLACA_BATEA() {
        return C1_PLACA_BATEA;
    }

    public void setC1_PLACA_BATEA(String C1_PLACA_BATEA) {
        this.C1_PLACA_BATEA = C1_PLACA_BATEA;
    }

    public String getC1_PLACA_CHUTO() {
        return C1_PLACA_CHUTO;
    }

    public void setC1_PLACA_CHUTO(String C1_PLACA_CHUTO) {
        this.C1_PLACA_CHUTO = C1_PLACA_CHUTO;
    }

    public Integer getC1_PLAZO() {
        return C1_PLAZO;
    }

    public void setC1_PLAZO(Integer C1_PLAZO) {
        this.C1_PLAZO = C1_PLAZO;
    }

    public String getC1_RAZON_SOCIAL() {
        return C1_RAZON_SOCIAL;
    }

    public void setC1_RAZON_SOCIAL(String C1_RAZON_SOCIAL) {
        this.C1_RAZON_SOCIAL = C1_RAZON_SOCIAL;
    }

    public String getC1_RETIRADO_PLANTA() {
        return C1_RETIRADO_PLANTA;
    }

    public void setC1_RETIRADO_PLANTA(String C1_RETIRADO_PLANTA) {
        this.C1_RETIRADO_PLANTA = C1_RETIRADO_PLANTA;
    }

    public String getC1_RIF() {
        return C1_RIF;
    }

    public void setC1_RIF(String C1_RIF) {
        this.C1_RIF = C1_RIF;
    }

    public String getC1_SERIAL_TICKET1() {
        return C1_SERIAL_TICKET1;
    }

    public void setC1_SERIAL_TICKET1(String C1_SERIAL_TICKET1) {
        this.C1_SERIAL_TICKET1 = C1_SERIAL_TICKET1;
    }

    public String getC1_SERIAL_TICKET2() {
        return C1_SERIAL_TICKET2;
    }

    public void setC1_SERIAL_TICKET2(String C1_SERIAL_TICKET2) {
        this.C1_SERIAL_TICKET2 = C1_SERIAL_TICKET2;
    }

    public String getC1_STATUS() {
        return C1_STATUS;
    }

    public void setC1_STATUS(String C1_STATUS) {
        this.C1_STATUS = C1_STATUS;
    }

    public String getC1_TIPO_CAMION() {
        return C1_TIPO_CAMION;
    }

    public void setC1_TIPO_CAMION(String C1_TIPO_CAMION) {
        this.C1_TIPO_CAMION = C1_TIPO_CAMION;
    }

    public String getC1_TIPO_TRANSPORTE() {
        return C1_TIPO_TRANSPORTE;
    }

    public void setC1_TIPO_TRANSPORTE(String C1_TIPO_TRANSPORTE) {
        this.C1_TIPO_TRANSPORTE = C1_TIPO_TRANSPORTE;
    }

    public String getC1_VENDEDOR() {
        return C1_VENDEDOR;
    }

    public void setC1_VENDEDOR(String C1_VENDEDOR) {
        this.C1_VENDEDOR = C1_VENDEDOR;
    }

    public String getC1_ZONA() {
        return C1_ZONA;
    }

    public void setC1_ZONA(String C1_ZONA) {
        this.C1_ZONA = C1_ZONA;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }
}  // public class Guia01.
