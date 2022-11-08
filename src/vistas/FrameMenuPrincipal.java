/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import bean.controlador.ControladorGuiaDespacho;
import bean.controlador.ControladorReporte;
import despacho.Despacho;
import despacho.Estilos;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 *
 * @author henrypb
 */
public class FrameMenuPrincipal extends javax.swing.JFrame {
    ControladorGuiaDespacho controladorGuiaDespacho = new ControladorGuiaDespacho(); 
    PanelPortada            panelPortada = new PanelPortada(); 
    PanelGuiaDespacho       panelGuiaDespacho = new PanelGuiaDespacho();
    PanelConsultarOrdFab    panelConsultarOrdFab = new PanelConsultarOrdFab(); 
    PanelNotaEntrega        panelNotaEntrega  = new PanelNotaEntrega(); 
    PanelTransportista      panelTransportista = new PanelTransportista(); 
    Despacho                despacho = new Despacho(); 
    Integer                 xWindow = Estilos.xWindow,
                            yWindow = Estilos.yWindow; 
    
    /**
     * Creates new form FrameMenuPrincipal
     */
    public FrameMenuPrincipal() {
        initComponents();
        actualizarMenuDespacho();
        agregarPaneles(); 
        setPaneles(Boolean.FALSE);   
        panelPortada.setVisible(Boolean.TRUE);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void actualizarMenuDespacho() {
        // Postear Guia Despacho:----------------------------------------------- 
        this.jMenuItemGuiaDespacho.setMnemonic('G');
        this.jMenuItemGuiaDespacho.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.ALT_MASK));
        this.jMenuItemGuiaDespacho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //JOptionPane.showMessageDialog(rootPane,"Menu item 'Postear Guia' clicked."); 
                //System.out.println("Menu item 'Postear Guia' clicked.");
                setPaneles(Boolean.FALSE); 
                panelGuiaDespacho.setVisible(true);
            }
        });
        // Consultar Relacion de  Ordenes de Fab:-------------------------------
        this.jMenuItemConsultarOrdFab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                setPaneles(Boolean.FALSE); 
                panelConsultarOrdFab.setVisible(true);
            }
        }); 
        // Nota de Entrega:-----------------------------------------------------
        this.jMenuItemNotaEntrega.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //JOptionPane.showMessageDialog(rootPane,"Menu item 'Consultar Relacion Ord Fab.' clicked."); 
                //System.out.println("Menu item 'Postear Guia' clicked.");
                setPaneles(Boolean.FALSE); 
                panelNotaEntrega.setVisible(true);
            }
        }); 
        // Transportista:-------------------------------------------------------
        this.jMenuItemTransportista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //JOptionPane.showMessageDialog(rootPane,"Menu item 'Consultar Relacion Ord Fab.' clicked."); 
                //System.out.println("Menu item 'Postear Guia' clicked.");
                setPaneles(Boolean.FALSE); 
                panelTransportista.setVisible(true);
            }
        }); 
        // Salir:---------------------------------------------------------------
        this.jMenuItemSalir.setMnemonic('S');
        this.jMenuItemSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
        this.jMenuItemSalir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Menu item 'SALIR' clicked.");
                FrameMenuPrincipal.this.setVisible(false);
                FrameMenuPrincipal.this.dispose();
            }
        });
        // Imprimir Guia Despacho:
        this.jMenuItemEmitirGuia.setMnemonic('E');
        this.jMenuItemEmitirGuia.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
        this.jMenuItemEmitirGuia.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                JOptionPane.showMessageDialog(rootPane,"Menu item 'Imprimir Guia' clicked."); 
                System.out.println("Menu item: 'Imprimir Guia' clicked.");
            }
        });
        // Opcion del Menu: AYUDA: 
        this.jMenuAyuda.setMnemonic('A');
        // this.jMenuSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));  // Nota al 05/12/12: setAccelerator aplica aJMenuItem solamente. ( use setMnemonic instead ). 
        this.jMenuAyuda.addMenuListener(new MenuListener() {

            @Override
            public void menuSelected(MenuEvent me) {
                JOptionPane.showMessageDialog(rootPane,"Activar Sistema de Ayuda onLine."); 
                //FrameMenuPrincipal.this.dispose();
            }

            @Override
            public void menuDeselected(MenuEvent me) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void menuCanceled(MenuEvent me) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }  // actualizarMenuDespacho().  
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void agregarPaneles() {
        final Integer posX = 1,
                      posY = 200; 
        final int     N = 3;    // ajustar windows-  
        //Container contenedorPrincipal = FrameMenuPrincipal.this.getContentPane(); 
        Container contenedorPrincipal = this.getContentPane(); 
        // ** Panel Portada **
        panelPortada.setSize(xWindow-N, yWindow-N);
        panelPortada.setLocation(posX, posY);
        contenedorPrincipal.add(panelPortada); 
        // ** Panel Guia Despacho:  
        panelGuiaDespacho.setSize(xWindow-N, yWindow-N );
        panelGuiaDespacho.setLocation(posX, posY);
        //FrameMenuPrincipal.this.add(panelGuiaDespacho); 
        contenedorPrincipal.add(panelGuiaDespacho);   
        // ** Panel Consultar Ordenes de Fab.
        panelConsultarOrdFab.setSize(xWindow-N,yWindow - N );
        panelConsultarOrdFab.setLocation(posX,posY);
        contenedorPrincipal.add(panelConsultarOrdFab); 
        //  ** Panel Nota de Entrega
        panelNotaEntrega.setSize(xWindow- N, yWindow- N );
        panelNotaEntrega.setLocation(posX, posY);
        contenedorPrincipal.add(panelNotaEntrega); 
        //  ** Panel Transportistas: 
        panelTransportista.setSize(xWindow- N, yWindow- N );
        panelTransportista.setLocation(posX, posY);
        contenedorPrincipal.add(panelTransportista); 
    }  // agregarPaneles(). 
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void setPaneles(Boolean activar) {
        panelPortada.setVisible(activar);
        panelGuiaDespacho.setVisible(activar);
        panelConsultarOrdFab.setVisible(activar);
        panelNotaEntrega.setVisible(activar);
        panelTransportista.setVisible(activar);
    }  // desactivarPaneles().
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        javax.swing.JLabel lblMenuPrincipal = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblHome = new javax.swing.JLabel();
        lblDesconectar = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JToolBar.Separator();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        jSeparator10 = new javax.swing.JToolBar.Separator();
        jSeparator11 = new javax.swing.JToolBar.Separator();
        jButton11 = new javax.swing.JButton();
        jSeparator12 = new javax.swing.JToolBar.Separator();
        jSeparator13 = new javax.swing.JToolBar.Separator();
        jSeparator14 = new javax.swing.JToolBar.Separator();
        jSeparator15 = new javax.swing.JToolBar.Separator();
        jSeparator16 = new javax.swing.JToolBar.Separator();
        jSeparator17 = new javax.swing.JToolBar.Separator();
        jMenuBarDespacho = new javax.swing.JMenuBar();
        jMenuActualizar = new javax.swing.JMenu();
        jMenuItemGuiaDespacho = new javax.swing.JMenuItem();
        jMenuItemTransportista = new javax.swing.JMenuItem();
        jMenuItemNotaEntrega = new javax.swing.JMenuItem();
        jMenuItemConsultarOrdFab = new javax.swing.JMenuItem();
        jMenuItemSalir = new javax.swing.JMenuItem();
        jMenuReporte = new javax.swing.JMenu();
        jMenuItemEmitirGuia = new javax.swing.JMenuItem();
        jMenuItemExistenciaProdStandard = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItemMovInvProdStandard = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItemExistMallasEspecial = new javax.swing.JMenuItem();
        jMenuItemMovMallasEspecial = new javax.swing.JMenuItem();
        jMenuMantenimiento = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuAyuda = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jDesktopPane1.setBackground(new java.awt.Color(254, 254, 254));
        jDesktopPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        lblMenuPrincipal.setBackground(new java.awt.Color(254, 254, 254));
        lblMenuPrincipal.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblMenuPrincipal.setForeground(new java.awt.Color(132, 6, 43));
        lblMenuPrincipal.setText("GESTION DE DESPACHO");
        lblMenuPrincipal.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblMenuPrincipal.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        lblMenuPrincipal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblMenuPrincipal.setOpaque(true);
        lblMenuPrincipal.setBounds(640, 20, 291, 31);
        jDesktopPane1.add(lblMenuPrincipal, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/logoFondonorma2x.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jLabel2.setBounds(100, 10, 77, 100);
        jDesktopPane1.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/logoDesica2x.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jLabel1.setBounds(10, 10, 90, 100);
        jDesktopPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lblHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/home32px.png"))); // NOI18N
        lblHome.setToolTipText("Ir a la Pag. Principal. ");
        lblHome.setBounds(850, 60, 40, 30);
        jDesktopPane1.add(lblHome, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lblDesconectar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/candado-abierto32px.png"))); // NOI18N
        lblDesconectar.setToolTipText("Desconectar Sistema.");
        lblDesconectar.setAutoscrolls(true);
        lblDesconectar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDesconectarMouseClicked(evt);
            }
        });
        lblDesconectar.setBounds(900, 60, 30, 30);
        jDesktopPane1.add(lblDesconectar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jToolBar1.setBackground(new java.awt.Color(121, 121, 121));
        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setRollover(true);

        btnAgregar.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/anadir16px.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAgregar.setFocusable(false);
        btnAgregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAgregar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAgregar);

        btnEditar.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/lapiz16px.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEditar.setFocusable(false);
        btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEditar);

        btnActualizar.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/checkMark16px.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnActualizar.setFocusable(false);
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnActualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnActualizar);

        jButton6.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/primerReg16px.png"))); // NOI18N
        jButton6.setText("1er Registro");
        jButton6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton6);

        jButton7.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/anterior16px.png"))); // NOI18N
        jButton7.setText("Anterior");
        jButton7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton7);

        jButton8.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/siguiente16px.png"))); // NOI18N
        jButton8.setText("Siguiente");
        jButton8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton8);

        jButton9.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/ultimoReg16px.png"))); // NOI18N
        jButton9.setText("Ult Registro");
        jButton9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton9);

        btnImprimir.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/impresoraModDskJet16px.png"))); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnImprimir.setFocusable(false);
        btnImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        jToolBar1.add(btnImprimir);

        btnEliminar.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/menos16px.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEliminar.setFocusable(false);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEliminar);

        btnLimpiar.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/cepillo-de-escoba16px.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnLimpiar.setFocusable(false);
        btnLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLimpiar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnLimpiar);

        btnCancelar.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/deshacer16px.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCancelar.setFocusable(false);
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCancelar);
        jToolBar1.add(jSeparator9);
        jToolBar1.add(jSeparator7);
        jToolBar1.add(jSeparator5);
        jToolBar1.add(jSeparator1);
        jToolBar1.add(jSeparator3);
        jToolBar1.add(jSeparator2);
        jToolBar1.add(jSeparator4);
        jToolBar1.add(jSeparator6);
        jToolBar1.add(jSeparator8);
        jToolBar1.add(jSeparator10);
        jToolBar1.add(jSeparator11);

        jButton11.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bean/utilitario/imagenes/retornar16px.png"))); // NOI18N
        jButton11.setText("Retornar");
        jButton11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton11.setFocusable(false);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton11);
        jToolBar1.add(jSeparator12);
        jToolBar1.add(jSeparator13);
        jToolBar1.add(jSeparator14);
        jToolBar1.add(jSeparator15);
        jToolBar1.add(jSeparator16);
        jToolBar1.add(jSeparator17);

        jMenuBarDespacho.setForeground(new java.awt.Color(254, 254, 254));
        jMenuBarDespacho.setAutoscrolls(true);
        jMenuBarDespacho.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N

        jMenuActualizar.setForeground(new java.awt.Color(124, 5, 49));
        jMenuActualizar.setText("Actualizar");
        jMenuActualizar.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N

        jMenuItemGuiaDespacho.setText("Guia Despacho");
        jMenuActualizar.add(jMenuItemGuiaDespacho);

        jMenuItemTransportista.setText("Transportistas");
        jMenuActualizar.add(jMenuItemTransportista);

        jMenuItemNotaEntrega.setText("Nota Entrega");
        jMenuActualizar.add(jMenuItemNotaEntrega);

        jMenuItemConsultarOrdFab.setText("Consulta Ord Fab");
        jMenuActualizar.add(jMenuItemConsultarOrdFab);

        jMenuItemSalir.setText("Salir");
        jMenuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSalirActionPerformed(evt);
            }
        });
        jMenuActualizar.add(jMenuItemSalir);

        jMenuBarDespacho.add(jMenuActualizar);

        jMenuReporte.setForeground(new java.awt.Color(124, 5, 49));
        jMenuReporte.setText("Reportes");
        jMenuReporte.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N

        jMenuItemEmitirGuia.setText("Emitir Guia Despacho");
        jMenuReporte.add(jMenuItemEmitirGuia);

        jMenuItemExistenciaProdStandard.setText("Existencia Materiales");
        jMenuReporte.add(jMenuItemExistenciaProdStandard);

        jMenuItemMovInvProdStandard.setText("Movimientos Inventario");
        jMenuReporte.add(jMenuItemMovInvProdStandard);

        jMenuItemExistMallasEspecial.setText("Existencia Mallas Especiales");
        jMenuReporte.add(jMenuItemExistMallasEspecial);

        jMenuItemMovMallasEspecial.setText("Mov Inv Mallas Especiales");
        jMenuReporte.add(jMenuItemMovMallasEspecial);

        jMenuBarDespacho.add(jMenuReporte);

        jMenuMantenimiento.setForeground(new java.awt.Color(124, 5, 49));
        jMenuMantenimiento.setText("Mantenimiento");
        jMenuMantenimiento.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N

        jMenuItem2.setText("Configurar Impresora");
        jMenuMantenimiento.add(jMenuItem2);

        jMenuItem3.setText("Configurar Pagina");
        jMenuMantenimiento.add(jMenuItem3);

        jMenuItem1.setText("Respaldar Datos");
        jMenuMantenimiento.add(jMenuItem1);

        jMenuBarDespacho.add(jMenuMantenimiento);

        jMenuAyuda.setForeground(new java.awt.Color(11, 11, 2));
        jMenuAyuda.setText("Ayuda");
        jMenuAyuda.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jMenuBarDespacho.add(jMenuAyuda);

        setJMenuBar(jMenuBarDespacho);
        jMenuBarDespacho.getAccessibleContext().setAccessibleParent(this);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1089, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 286, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void lblDesconectarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDesconectarMouseClicked
        this.dispose();
         FrameAccesoPrincipal portalAcceso = new FrameAccesoPrincipal(); 
         portalAcceso.setSize(Estilos.xWindow, Estilos.yWindow);
         portalAcceso.setVisible(true);
    }//GEN-LAST:event_lblDesconectarMouseClicked

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if ( panelGuiaDespacho.isVisible()) {
             controladorGuiaDespacho.agregarRegistro(); 
             panelGuiaDespacho.setIndicadorModoOperacion("insertar");
        }
    }//GEN-LAST:event_btnAgregarActionPerformed
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
         if ( panelGuiaDespacho.isVisible() && ( PanelGuiaDespacho.chbModoConsultar.isSelected() || PanelGuiaDespacho.chbModoInsertar.isSelected() )  ) {
             panelGuiaDespacho.setIndicadorModoOperacion("editar");
             controladorGuiaDespacho.editarRegistro();
         }
    }//GEN-LAST:event_btnEditarActionPerformed

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
         if ( panelGuiaDespacho.isVisible()) {
             controladorGuiaDespacho.eliminarRegistro(); 
         }
    }//GEN-LAST:event_btnEliminarActionPerformed

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
         if ( panelGuiaDespacho.isVisible()) {
              controladorGuiaDespacho.limpiarCampos();
         }
    }//GEN-LAST:event_btnLimpiarActionPerformed
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if ( panelGuiaDespacho.isVisible()) {
             controladorGuiaDespacho.cancelar();
             panelGuiaDespacho.setIndicadorModoOperacion("operar");
         }
    }//GEN-LAST:event_btnCancelarActionPerformed
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
         if ( panelGuiaDespacho.isVisible()) {
             controladorGuiaDespacho.actualizarRegistro(); 
         }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void jMenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSalirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemSalirActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        if ( panelGuiaDespacho.isVisible() ) {
            ControladorReporte controladorReporte = new ControladorReporte();  
            controladorReporte.ejecutarReporteGuiaDespacho();
        }   // if.       
    }//GEN-LAST:event_btnImprimirActionPerformed
 
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new FrameMenuPrincipal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenuActualizar;
    private javax.swing.JMenu jMenuAyuda;
    private javax.swing.JMenuBar jMenuBarDespacho;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItemConsultarOrdFab;
    private javax.swing.JMenuItem jMenuItemEmitirGuia;
    private javax.swing.JMenuItem jMenuItemExistenciaProdStandard;
    private javax.swing.JMenuItem jMenuItemGuiaDespacho;
    private javax.swing.JMenuItem jMenuItemMovMallasEspecial;
    private javax.swing.JMenuItem jMenuItemNotaEntrega;
    private javax.swing.JMenuItem jMenuItemSalir;
    private javax.swing.JMenuItem jMenuItemTransportista;
    private javax.swing.JMenu jMenuMantenimiento;
    private javax.swing.JMenu jMenuReporte;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator10;
    private javax.swing.JToolBar.Separator jSeparator11;
    private javax.swing.JToolBar.Separator jSeparator12;
    private javax.swing.JToolBar.Separator jSeparator13;
    private javax.swing.JToolBar.Separator jSeparator14;
    private javax.swing.JToolBar.Separator jSeparator15;
    private javax.swing.JToolBar.Separator jSeparator16;
    private javax.swing.JToolBar.Separator jSeparator17;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JToolBar.Separator jSeparator9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblDesconectar;
    private javax.swing.JLabel lblHome;
    // End of variables declaration//GEN-END:variables
}
