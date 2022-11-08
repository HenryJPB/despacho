/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import bean.controlador.ControladorDetalleGuia;
import java.awt.Color;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import bean.utilitario.inventFacturacion.ControladorLovProductos;
import bean.utilitario.inventFacturacion.Inv01;
import bean.utilitario.inventFacturacion.Inv04;
import bean.utilitario.inventFacturacion.ServicioAdministracionInv01;
import bean.utilitario.inventFacturacion.ServicioAdministracionInv04;
import bean.utilitario.inventFacturacion.ServicioAdministracionInv06;

/**
 *
 * @author henrypb
 */
public class PanelAgregarDetalleGuia extends javax.swing.JPanel {

    final int returnCHAR = 10;
    final int tabCHAR = 13;

    /**
     * Creates new form PanelDetalleGuia
     */
    public PanelAgregarDetalleGuia() {
        initComponents();
        iniciarAtributos();         
        /*
         * header.setBackground(Color.black);
         header.setForeground(Color.yellow);
         header.setFont(new Font("Monospaced",Font.BOLD,13));
         */
    }
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void iniciarAtributos() {
        //this.btnCancelarNuevoReg.setVisible(Boolean.FALSE);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static void setCamposAgregarItemsGuia(Boolean valor) {
        PanelAgregarDetalleGuia.txtCodigo.setEditable(valor);
        PanelAgregarDetalleGuia.btnLovProductos.setEnabled(valor);
        PanelAgregarDetalleGuia.txtDescripcion.setEditable(valor);
        PanelAgregarDetalleGuia.txtPedido.setEditable(valor);
        PanelAgregarDetalleGuia.txtPesoUni.setEditable(valor);
        PanelAgregarDetalleGuia.txtAtados.setEditable(valor);
        PanelAgregarDetalleGuia.txtItems.setEditable(valor);
        PanelAgregarDetalleGuia.txtCantidad.setEditable(valor);
        PanelAgregarDetalleGuia.txtPrecioUni.setEditable(valor);
        PanelAgregarDetalleGuia.txtAlicuota.setEditable(valor);
    }  //  setCamposAgregarItemsGuia().

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static void limpiarCampos() {
        PanelAgregarDetalleGuia.txtCodigo.setText("");
        PanelAgregarDetalleGuia.cbbTipoProducto.setSelectedItem(null);
        PanelAgregarDetalleGuia.txtDescripcion.setText("");
        String pedido = PanelGuiaDespacho.txtPedido1.getText();
        PanelAgregarDetalleGuia.txtPedido.setText(pedido);
        PanelAgregarDetalleGuia.txtPesoUni.setValue(0.00);
        PanelAgregarDetalleGuia.txtAtados.setText("");
        PanelAgregarDetalleGuia.txtItems.setValue(0.00);
        PanelAgregarDetalleGuia.txtCantidad.setValue(0.00);
        PanelAgregarDetalleGuia.txtPrecioUni.setValue(0.00);
        PanelAgregarDetalleGuia.chbFacPeso.setSelected(false);
        PanelAgregarDetalleGuia.chbFacUnidad.setSelected(false);
        Number alicuota = (Number) PanelGuiaDespacho.txtAlicuota.getValue();
        PanelAgregarDetalleGuia.txtAlicuota.setValue(alicuota.doubleValue());
        PanelAgregarDetalleGuia.txtDisplayPesoGuia.setValue(0.00);
        PanelAgregarDetalleGuia.txtDisplayPesoGuia.setValue(0.00);
        PanelAgregarDetalleGuia.txtDisplayTotalMonto.setValue(0.00);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static Boolean facPeso(String tipoProd) {
        Boolean correcto = Boolean.FALSE;
        ServicioAdministracionInv06 servicioAdministracionInv06 = new ServicioAdministracionInv06();
        if (servicioAdministracionInv06.facPeso(tipoProd)) {
            correcto = Boolean.TRUE;
        }
        return (correcto);
    }  // facPeso().  

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static Boolean facUnidad(String tipoProd) {
        Boolean correcto = Boolean.FALSE;
        ServicioAdministracionInv06 servicioAdministracionInv06 = new ServicioAdministracionInv06();
        if ( servicioAdministracionInv06.facUnidad(tipoProd) ) {
            correcto = Boolean.TRUE;
        }
        return (correcto);
    }  // facPeso().
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private Boolean validarCodigo() {
        ServicioAdministracionInv01 servicioAdministracionInv01 = new ServicioAdministracionInv01();
        ServicioAdministracionInv04 servicioAdministracionInv04 = new ServicioAdministracionInv04();
        String codProducto = PanelAgregarDetalleGuia.txtCodigo.getText();
        Boolean valido = Boolean.FALSE;
        if (PanelAgregarDetalleGuia.txtCodigo.getText() != null || !PanelAgregarDetalleGuia.txtCodigo.toString().isEmpty()) {
            if (PanelGuiaDespacho.txtOrden1.getText() == null || PanelGuiaDespacho.txtOrden1.getText().isEmpty()) {
                // true? : se trata de un producto Standard =>
                if (servicioAdministracionInv01.productoStandardRegistrado(codProducto)) {
                    Inv01 datosProducto = servicioAdministracionInv01.getProductoStandard(codProducto);
                    DefaultComboBoxModel dfCbbModel = new DefaultComboBoxModel();   // Ver Nota (*) 
                    dfCbbModel.addElement(datosProducto.getC1_TIPO());
                    PanelAgregarDetalleGuia.cbbTipoProducto.setModel(dfCbbModel);
                    PanelAgregarDetalleGuia.txtDescripcion.setText(datosProducto.getC1_DESCRIPCION());
                    /*
                    if (facPeso(datosProducto.getC1_TIPO())) {
                        PanelAgregarDetalleGuia.chbFacPeso.setSelected(Boolean.TRUE);
                    } else {
                        PanelAgregarDetalleGuia.chbFacPeso.setSelected(Boolean.FALSE);
                    }
                    PanelAgregarDetalleGuia.txtDescripcion.setText(datosProducto.getC1_DESCRIPCION());
                    
                    */
                    valido = Boolean.TRUE;
                }
            } else {
                // false? : se trata de un Pedido Especial =>
                String nroOrden = PanelGuiaDespacho.txtOrden1.getText();
                if (servicioAdministracionInv04.productoEspecialRegistrado(nroOrden, codProducto)) {
                    Inv04 datosProducto = servicioAdministracionInv04.getProductoEspecial(nroOrden, codProducto);
                    DefaultComboBoxModel dfCbbModel = new DefaultComboBoxModel();  // Ver Nota (*)  
                    dfCbbModel.addElement(datosProducto.getC4_TIPO_PROD());
                    PanelAgregarDetalleGuia.cbbTipoProducto.setModel(dfCbbModel);
                    PanelAgregarDetalleGuia.txtDescripcion.setText(datosProducto.getDescripcion());
                    /*
                    if (facPeso(datosProducto.getC4_TIPO_PROD())) {
                        PanelAgregarDetalleGuia.chbFacPeso.setSelected(Boolean.TRUE);
                    } else {
                        PanelAgregarDetalleGuia.chbFacPeso.setSelected(Boolean.FALSE);
                    }
                    PanelAgregarDetalleGuia.txtDescripcion.setText(datosProducto.getDescripcion());
                    */
                    valido = Boolean.TRUE;
                }
            }
        }   // es codigo != null ?.  
        if (!valido) {
            PanelAgregarDetalleGuia.txtCodigo.setBackground(Color.YELLOW);
            //JOptionPane.showConfirmDialog(this,"Codigo del Producto NO registrado.","ATENCION:",JOptionPane.NO_OPTION)
            JOptionPane.showMessageDialog(this, "Codigo del Producto NO registrado.", "ATENCION:", JOptionPane.ERROR_MESSAGE);
            PanelAgregarDetalleGuia.txtCodigo.setBackground(Color.WHITE);
        }
        return (valido);
    }  // validarCodigo().  
    // NOTA (*): De esta menera estamos definiendo el Combo Box model en Tiempo Real. 
    //           Otra manera es iniciarlo en Propiedades del Objeto y agregar cada TIPO de Prod. 
    //           

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private Boolean validarDescripcion() {
        Boolean valido = Boolean.FALSE;
        if (PanelAgregarDetalleGuia.txtDescripcion.getText() == null || PanelAgregarDetalleGuia.txtDescripcion.getText().isEmpty()) {
            PanelAgregarDetalleGuia.txtDescripcion.setBackground(Color.YELLOW);
            JOptionPane.showMessageDialog(this, "Campo NO puede ser nulo.", "ATENCION", JOptionPane.ERROR_MESSAGE);
            PanelAgregarDetalleGuia.txtDescripcion.setBackground(Color.WHITE);
            PanelAgregarDetalleGuia.txtDescripcion.requestFocus();  //  .setFocus ...??? : REVISAR?. 
        } else {
            valido = Boolean.TRUE;  //PanelAgregarDetalleGuia.txtPedido.requestFocus();
        }
        return (valido);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private Boolean existePedido(String nroPedido) {
        String ped1 = PanelGuiaDespacho.txtPedido1.getText();
        String ped2 = PanelGuiaDespacho.txtPedido2.getText();
        String ped3 = PanelGuiaDespacho.txtPedido3.getText();
        String ped4 = PanelGuiaDespacho.txtPedido4.getText();
        Boolean existe = Boolean.FALSE;
        if (nroPedido.equals(ped1) || nroPedido.equals(ped2) || nroPedido.equals(ped3) || nroPedido.equals(ped4)) {
            existe = Boolean.TRUE;
        }
        return (existe);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private Boolean validarPedido() {
        Boolean valido = Boolean.FALSE;
        String nroPedido = PanelAgregarDetalleGuia.txtPedido.getText();
        if (nroPedido != null || !nroPedido.isEmpty()) {
            valido = Boolean.TRUE;
            if (existePedido(nroPedido)) {
                valido = Boolean.TRUE;
            } else {
                PanelAgregarDetalleGuia.txtPedido.setBackground(Color.YELLOW);
                //JOptionPane.showConfirmDialog(this,"Codigo del Producto NO registrado.","ATENCION:",JOptionPane.NO_OPTION)
                JOptionPane.showMessageDialog(this, "Nro. Pedido NO registrado.", "ATENCION:", JOptionPane.ERROR_MESSAGE);
                PanelAgregarDetalleGuia.txtPedido.setBackground(Color.WHITE);
            }
        }
        return (valido);
    }  // validarPedido(). 

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void calcularPeso() {
        Number peso = (Number) PanelAgregarDetalleGuia.txtPesoUni.getValue();
        Number cantidad = (Number) PanelAgregarDetalleGuia.txtCantidad.getValue();
        PanelAgregarDetalleGuia.txtDisplayPesoGuia.setValue(peso.doubleValue() * cantidad.doubleValue());
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void calcularMonto() {
        String tipoProd = PanelAgregarDetalleGuia.cbbTipoProducto.getSelectedItem().toString();
        Number precio = (Number) PanelAgregarDetalleGuia.txtPrecioUni.getValue();
        Number cantidad = (Number) PanelAgregarDetalleGuia.txtCantidad.getValue();
        Number peso = (Number) PanelAgregarDetalleGuia.txtPesoUni.getValue();
        Double totalMonto = precio.doubleValue() * cantidad.doubleValue();
        if (facPeso(tipoProd)) {
            totalMonto = totalMonto * peso.doubleValue();
        }
        PanelAgregarDetalleGuia.txtDisplayTotalMonto.setValue(totalMonto);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void validarPeso() {
        Double peso = 0.00;
        if (PanelAgregarDetalleGuia.txtPesoUni.getValue() != null) {
            Number pesoNumber = (Number) PanelAgregarDetalleGuia.txtPesoUni.getValue();
            peso = Math.abs(pesoNumber.doubleValue());
        }
        //PanelAgregarDetalleGuia.txtPesoUni.setValue((peso==null ? 0.00 : peso) );
        PanelAgregarDetalleGuia.txtPesoUni.setValue(peso);
        calcularPeso();
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private Boolean validarAtados() {
        return (Boolean.TRUE);  
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void validarItems() {
         Double items = 0.00; 
         if ( PanelAgregarDetalleGuia.txtItems.getValue() != null ) {
              Number itemsNumber = (Number) PanelAgregarDetalleGuia.txtItems.getValue();  
              items = Math.abs(itemsNumber.doubleValue());
         }
         PanelAgregarDetalleGuia.txtItems.setValue(items);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void validarCantidad() {
        Double cantidad = 0.00;
        if (PanelAgregarDetalleGuia.txtCantidad.getValue() != null) {
            Number cantidadNumber = (Number) PanelAgregarDetalleGuia.txtCantidad.getValue();
            cantidad = Math.abs(cantidadNumber.doubleValue());
        }
        // PanelAgregarDetalleGuia.txtCantidad.setValue( (cantidad==null ? 0.00 : cantidad) );
        PanelAgregarDetalleGuia.txtCantidad.setValue(cantidad);
        calcularPeso();
        calcularMonto();
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void validarPrecio() {
        Double precio = 0.00;
        if (PanelAgregarDetalleGuia.txtPrecioUni.getValue() != null) {
            Number precioNumber = (Number) PanelAgregarDetalleGuia.txtPrecioUni.getValue();
            precio = Math.abs(precioNumber.doubleValue());
        }
        PanelAgregarDetalleGuia.txtPrecioUni.setValue(precio);
        calcularMonto();
    }

    /**
     * *************************************************************************
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     * *************************************************************************
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnLovProductos = new javax.swing.JButton();
        cbbTipoProducto = new javax.swing.JComboBox();
        txtDescripcion = new javax.swing.JTextField();
        txtPedido = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPesoUni = new javax.swing.JFormattedTextField();
        txtAtados = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JFormattedTextField();
        txtPrecioUni = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        txtDisplayPesoGuia = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        txtAlicuota = new javax.swing.JFormattedTextField();
        txtDisplayTotalMonto = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        chbFacPeso = new javax.swing.JCheckBox();
        txtItems = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        chbFacUnidad = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        btnCancelarNuevoReg = new javax.swing.JButton();
        btnLimpiarIngresarNuevoReg = new javax.swing.JButton();
        btnOkIngresarNuevoReg = new javax.swing.JButton();

        setBackground(new java.awt.Color(165, 195, 203));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(18, 8, 8));
        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(253, 249, 6));
        jLabel1.setText("Codigo");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel1.setOpaque(true);
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 70, 80, -1));

        jLabel2.setBackground(new java.awt.Color(18, 8, 8));
        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(235, 242, 10));
        jLabel2.setText("Tipo");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel2.setOpaque(true);
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, 52, 20));

        jLabel3.setBackground(new java.awt.Color(18, 8, 8));
        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(235, 242, 10));
        jLabel3.setText("Descripcion");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel3.setOpaque(true);
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 110, 20));

        jLabel4.setBackground(new java.awt.Color(18, 8, 8));
        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(235, 242, 10));
        jLabel4.setText("Pedido No.");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel4.setOpaque(true);
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, 20));

        txtCodigo.setEditable(false);
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 114, 30));

        btnLovProductos.setFont(new java.awt.Font("DejaVu Sans", 1, 8)); // NOI18N
        btnLovProductos.setText("...");
        btnLovProductos.setEnabled(false);
        btnLovProductos.setOpaque(true);
        btnLovProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLovProductosActionPerformed(evt);
            }
        });
        add(btnLovProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 30, 30));

        cbbTipoProducto.setVerifyInputWhenFocusTarget(false);
        add(cbbTipoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 60, 30));

        txtDescripcion.setEditable(false);
        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });
        add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 350, 30));

        txtPedido.setEditable(false);
        txtPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPedidoActionPerformed(evt);
            }
        });
        txtPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPedidoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPedidoKeyTyped(evt);
            }
        });
        add(txtPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 88, 30));

        jLabel6.setBackground(new java.awt.Color(18, 8, 8));
        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(235, 242, 10));
        jLabel6.setText("Peso ksg/unid");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel6.setOpaque(true);
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, -1, 20));

        jLabel8.setBackground(new java.awt.Color(18, 8, 8));
        jLabel8.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(235, 242, 10));
        jLabel8.setText("Atados");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel8.setOpaque(true);
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 80, 20));

        txtPesoUni.setEditable(false);
        txtPesoUni.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        txtPesoUni.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPesoUni.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPesoUniFocusLost(evt);
            }
        });
        txtPesoUni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesoUniActionPerformed(evt);
            }
        });
        txtPesoUni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPesoUniKeyPressed(evt);
            }
        });
        add(txtPesoUni, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, 107, 30));

        txtAtados.setEditable(false);
        txtAtados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAtadosActionPerformed(evt);
            }
        });
        txtAtados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAtadosKeyTyped(evt);
            }
        });
        add(txtAtados, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 54, 30));

        jLabel9.setBackground(new java.awt.Color(18, 8, 8));
        jLabel9.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(235, 242, 10));
        jLabel9.setText("Items x Atados");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel9.setOpaque(true);
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, -1, 20));

        jLabel11.setBackground(new java.awt.Color(18, 8, 8));
        jLabel11.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(235, 242, 10));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Cantidad");
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel11.setOpaque(true);
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 86, 20));

        jLabel12.setBackground(new java.awt.Color(18, 8, 8));
        jLabel12.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(235, 242, 10));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Precio/Uni");
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel12.setOpaque(true);
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, 84, 20));

        txtCantidad.setEditable(false);
        txtCantidad.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 100, 30));

        txtPrecioUni.setEditable(false);
        txtPrecioUni.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtPrecioUni.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPrecioUni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioUniActionPerformed(evt);
            }
        });
        add(txtPrecioUni, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 230, 100, 30));

        jLabel13.setBackground(new java.awt.Color(18, 8, 8));
        jLabel13.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(235, 242, 10));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Peso Guia");
        jLabel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel13.setOpaque(true);
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 110, 20));

        txtDisplayPesoGuia.setEditable(false);
        txtDisplayPesoGuia.setBackground(new java.awt.Color(130, 243, 170));
        txtDisplayPesoGuia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDisplayPesoGuia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.000"))));
        txtDisplayPesoGuia.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDisplayPesoGuia.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        add(txtDisplayPesoGuia, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 110, 30));

        jLabel14.setBackground(new java.awt.Color(18, 8, 8));
        jLabel14.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(235, 242, 10));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Alicuota %");
        jLabel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel14.setOpaque(true);
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 270, -1, 20));

        txtAlicuota.setEditable(false);
        txtAlicuota.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtAlicuota.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        add(txtAlicuota, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 270, 90, 30));

        txtDisplayTotalMonto.setEditable(false);
        txtDisplayTotalMonto.setBackground(new java.awt.Color(171, 241, 238));
        txtDisplayTotalMonto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDisplayTotalMonto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtDisplayTotalMonto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDisplayTotalMonto.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        add(txtDisplayTotalMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 360, 131, 30));

        jLabel16.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel16.setText("(1)");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, 20));

        jLabel17.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel17.setText("(2)");
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, -1, -1));

        jLabel18.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel18.setText("(3)");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, -1, 20));

        jLabel19.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel19.setText("(4)");
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, 20));

        jLabel20.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel20.setText("(5)");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, 20));

        jLabel21.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel21.setText("(6)");
        add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, -1, 20));

        jLabel22.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel22.setText("(7)");
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, 20));

        jLabel23.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel23.setText("(8)");
        add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, -1, 20));

        jLabel24.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel24.setText("(9)");
        add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, 20));

        jLabel25.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel25.setText("(10)");
        add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, -1, 20));

        jLabel26.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel26.setText("(11)");
        add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, 20));

        jLabel27.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel27.setText("(12)");
        add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 280, -1, 20));

        jLabel29.setBackground(new java.awt.Color(18, 8, 8));
        jLabel29.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(235, 242, 10));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("TOTAL");
        jLabel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel29.setOpaque(true);
        add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 360, 130, 30));

        jLabel30.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel30.setText("(13)");
        add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, -1, 20));

        chbFacPeso.setBackground(new java.awt.Color(12, 3, 10));
        chbFacPeso.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        chbFacPeso.setForeground(new java.awt.Color(246, 251, 4));
        chbFacPeso.setText("F x Peso");
        chbFacPeso.setEnabled(false);
        chbFacPeso.setOpaque(true);
        add(chbFacPeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 320, 81, 20));

        txtItems.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        txtItems.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtItemsActionPerformed(evt);
            }
        });
        add(txtItems, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, 113, 30));

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel5.setText("X");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, 15, 20));

        chbFacUnidad.setBackground(new java.awt.Color(22, 18, 14));
        chbFacUnidad.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        chbFacUnidad.setForeground(new java.awt.Color(246, 222, 13));
        chbFacUnidad.setText("FxUnidad");
        chbFacUnidad.setEnabled(false);
        chbFacUnidad.setOpaque(true);
        add(chbFacUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, -1, 20));

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Items de la Guia");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 550, -1));

        btnCancelarNuevoReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/undo16px.png"))); // NOI18N
        btnCancelarNuevoReg.setToolTipText("Cancelar.");
        btnCancelarNuevoReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarNuevoRegActionPerformed(evt);
            }
        });
        add(btnCancelarNuevoReg, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 400, 60, 40));

        btnLimpiarIngresarNuevoReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/cepillo-de-escoba16px.png"))); // NOI18N
        btnLimpiarIngresarNuevoReg.setToolTipText("Limpiar campos.");
        btnLimpiarIngresarNuevoReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarIngresarNuevoRegActionPerformed(evt);
            }
        });
        add(btnLimpiarIngresarNuevoReg, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 400, 60, 40));

        btnOkIngresarNuevoReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/checkMark16px.png"))); // NOI18N
        btnOkIngresarNuevoReg.setToolTipText("Ok!, Conforme - guardar registro en la grilla.");
        btnOkIngresarNuevoReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkIngresarNuevoRegActionPerformed(evt);
            }
        });
        add(btnOkIngresarNuevoReg, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 400, 60, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        if ( validarCodigo() ) {
            String codProd = PanelAgregarDetalleGuia.txtCodigo.getText();  
            String tipoProd = PanelAgregarDetalleGuia.cbbTipoProducto.getSelectedItem().toString();  
            ControladorDetalleGuia controladorDetalleGuia = new ControladorDetalleGuia();  
            controladorDetalleGuia.actualizarDatosNoClavesAgregarProductos(codProd, tipoProd);  
            PanelAgregarDetalleGuia.txtDescripcion.requestFocus();
        }
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void btnLovProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLovProductosActionPerformed
        ControladorLovProductos controladorLovProductos = new ControladorLovProductos();
        controladorLovProductos.ejecutarDialogPanelProductos();
    }//GEN-LAST:event_btnLovProductosActionPerformed

    private void txtPesoUniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesoUniActionPerformed
        validarPeso();
        PanelAgregarDetalleGuia.txtAtados.requestFocus();
    }//GEN-LAST:event_txtPesoUniActionPerformed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        validarCantidad();
        PanelAgregarDetalleGuia.txtPrecioUni.requestFocus();
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtPrecioUniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioUniActionPerformed
        validarPrecio();
    }//GEN-LAST:event_txtPrecioUniActionPerformed

    private void txtPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPedidoActionPerformed
        if (validarPedido()) {
            PanelAgregarDetalleGuia.txtPesoUni.requestFocus();
        }
    }//GEN-LAST:event_txtPedidoActionPerformed

    private void txtPedidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPedidoKeyTyped
        /* --------------------------------------------
         int k = (int) evt.getKeyChar();
         int k2 = (int) evt.getKeyCode();
         //System.out.println("Key Code=" + k2);
         if (k == returnCHAR || k == tabCHAR || evt.getKeyCode() == KeyEvent.VK_TAB) {
         //System.out.println("Usuario ha presionado:\n[ENTER] o [TAB] key.");
         } else {
         // System.out.println("El usuario tipeo: " + k);
         }
         -------------------------------------------------*/
        //------------------------------------------------
        // Introducir solo caracteres numericos.  
        char car = evt.getKeyChar();
        if (car < '0' || car > '9') {
            evt.consume();  // si el car no esta en el rango ['0'..'9'] lo desechamos.  
        }
    }//GEN-LAST:event_txtPedidoKeyTyped

    private void txtPedidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPedidoKeyPressed
        // Ejemplo 1: 
        //PanelAgregarDetalleGuia.txtPedido.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,java.util.Collections.EMPTY_SET);
        //if (evt.getKeyCode() == KeyEvent.VK_TAB) {
        //     System.out.println("requestFocus,....");
        //    //PanelAgregarDetalleGuia.txtPesoUni.requestFocus();
        //}
        // Ejemplo 2: 
        /*
         KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
         @Override
         public boolean dispatchKeyEvent(KeyEvent evento) {
         //int tecla = evento.getKeyCode();
         if (evento.getKeyCode() == KeyEvent.VK_TAB) {
         System.out.println("VK_TAB.");
         //validar();
         }
         if (evento.getKeyCode() == KeyEvent.VK_ENTER) {
         System.out.println("VK_ENTER.");
         //validar();
         }
         return false;
         }
         });
         */
    }//GEN-LAST:event_txtPedidoKeyPressed

    private void txtPesoUniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoUniKeyPressed
        //Evento KeyEventDispatcher el cual hace que funcione la tecla de TABULADOR
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent evento) {
                //int tecla = evento.getKeyCode();
                if (evento.getKeyCode() == KeyEvent.VK_TAB) {
                    //validarPeso();
                }
                if (evento.getKeyCode() == KeyEvent.VK_ENTER) {
                    //validarPeso();
                }
                return false;
            }
        });
    }//GEN-LAST:event_txtPesoUniKeyPressed

    private void txtPesoUniFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesoUniFocusLost
        validarPeso();
    }//GEN-LAST:event_txtPesoUniFocusLost

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        if (validarDescripcion()) {
            PanelAgregarDetalleGuia.txtPedido.requestFocus();
        }
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void txtAtadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAtadosActionPerformed
       if ( validarAtados() ) {
           PanelAgregarDetalleGuia.txtItems.requestFocus();
       }
    }//GEN-LAST:event_txtAtadosActionPerformed

    private void txtItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtItemsActionPerformed
        validarItems();  
        PanelAgregarDetalleGuia.txtCantidad.requestFocus();
    }//GEN-LAST:event_txtItemsActionPerformed

    private void txtAtadosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAtadosKeyTyped
        // **Introducir solo caracteres numericos **.  
        char car = evt.getKeyChar(); 
        if (car<'0' || car>'9') evt.consume();  // si el car no esta en el rango ['0'..'9'] lo desechamos.  
    }//GEN-LAST:event_txtAtadosKeyTyped

    private void btnCancelarNuevoRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarNuevoRegActionPerformed
        //PanelAgregarDetalleGuia.setCamposAgregarItemsGuia(false);
        //this.setVisible(Boolean.FALSE);
    }//GEN-LAST:event_btnCancelarNuevoRegActionPerformed

    private void btnLimpiarIngresarNuevoRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarIngresarNuevoRegActionPerformed
        //PanelAgregarDetalleGuia.limpiarCampos();
    }//GEN-LAST:event_btnLimpiarIngresarNuevoRegActionPerformed

    private void btnOkIngresarNuevoRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkIngresarNuevoRegActionPerformed
        /*
        ControladorDetalleGuia controladorDetalleGuia = new ControladorDetalleGuia();
        controladorDetalleGuia.incluirItemsTablaGuia02();
        PanelAgregarDetalleGuia.limpiarCampos();
        PanelAgregarDetalleGuia.setCamposAgregarItemsGuia(Boolean.FALSE);
        PanelGuiaDespacho.chbActualizarGridProductos.setSelected(Boolean.TRUE);
        */  
    }//GEN-LAST:event_btnOkIngresarNuevoRegActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnCancelarNuevoReg;
    public static javax.swing.JButton btnLimpiarIngresarNuevoReg;
    public static javax.swing.JButton btnLovProductos;
    public static javax.swing.JButton btnOkIngresarNuevoReg;
    public static javax.swing.JComboBox cbbTipoProducto;
    public static javax.swing.JCheckBox chbFacPeso;
    public static javax.swing.JCheckBox chbFacUnidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JFormattedTextField txtAlicuota;
    public static javax.swing.JTextField txtAtados;
    public static javax.swing.JFormattedTextField txtCantidad;
    public static javax.swing.JTextField txtCodigo;
    public static javax.swing.JTextField txtDescripcion;
    public static javax.swing.JFormattedTextField txtDisplayPesoGuia;
    public static javax.swing.JFormattedTextField txtDisplayTotalMonto;
    public static javax.swing.JFormattedTextField txtItems;
    public static javax.swing.JTextField txtPedido;
    public static javax.swing.JFormattedTextField txtPesoUni;
    public static javax.swing.JFormattedTextField txtPrecioUni;
    // End of variables declaration//GEN-END:variables
}
