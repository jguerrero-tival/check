package cl.tival.check.view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.tival.check.model.Empresa;
import cl.tival.check.model.Factura;
import cl.tival.check.service.FacturaService;
import cl.tival.check.service.PrinterService;
import cl.tival.check.util.Fecha;
import cl.tival.check.util.Format;
import cl.tival.check.util.Pantalla;

public class ImprimirDialog extends javax.swing.JDialog {

	final static Logger LOG = LoggerFactory.getLogger(ImprimirDialog.class);
	private static final long serialVersionUID = 1L;
	private PrinterService printerService;
	private FacturaService facturaService;
	private Empresa empresa;
	private List<Long> numerosFactura;
	private List<Factura> facturas;
	private int cantidadChequesTmp;
	
    // Variables declaration - do not modify
    private javax.swing.JLabel answerLabel;
    private javax.swing.JPanel answerPanel;
    private javax.swing.JPanel botonesPanel;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JComboBox cantidadChequesComboBox;
    private javax.swing.JLabel cantidadChequesLabel;
    private javax.swing.JLabel chequeLabel;
    private javax.swing.JPanel chequesPanel;
    private javax.swing.JLabel fechaLabel;
    private JFormattedTextField fechaTextField1;
    private JFormattedTextField fechaTextField2;
    private JFormattedTextField fechaTextField3;
    private JFormattedTextField fechaTextField4;
    private JFormattedTextField fechaTextField5;
    private JFormattedTextField fechaTextField6;
    private javax.swing.JPanel formaPagoPanel;
    private javax.swing.JButton imprimirButton1;
    private javax.swing.JButton imprimirButton2;
    private javax.swing.JButton imprimirButton3;
    private javax.swing.JButton imprimirButton4;
    private javax.swing.JButton imprimirButton5;
    private javax.swing.JButton imprimirButton6;
    private javax.swing.JLabel numeroChequeLabel1;
    private javax.swing.JLabel numeroChequeLabel2;
    private javax.swing.JLabel numeroChequeLabel3;
    private javax.swing.JLabel numeroChequeLabel4;
    private javax.swing.JLabel numeroChequeLabel5;
    private javax.swing.JLabel numeroChequeLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel montoLabel;
    private javax.swing.JTextField montoTextField1;
    private javax.swing.JTextField montoTextField2;
    private javax.swing.JTextField montoTextField3;
    private javax.swing.JTextField montoTextField4;
    private javax.swing.JTextField montoTextField5;
    private javax.swing.JTextField montoTextField6;
    private javax.swing.JLabel totalComprobacionLabel;
    private javax.swing.JTextField totalComprobacionTextField;
    private javax.swing.JLabel totalPagarLabel;
    private javax.swing.JTextField totalPagarTextField;
    // End of variables declaration

    public ImprimirDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private void cantidadChequesComboBoxActionPerformed(java.awt.event.ActionEvent evt) {                                                        
    	int cantidadCheques = (Integer) cantidadChequesComboBox.getSelectedItem();
    	changeCantidadChequesInfo(cantidadCheques);
    }                                                       

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
    	LOG.trace("Starting \"cancelarButtonActionPerformed\" method.");
    	//this.cleanDialog();
    	this.dispose();
    	LOG.trace("Finishing \"cancelarButtonActionPerformed\" method.");
    }                                              
    
    private void montoTextField1KeyTyped(KeyEvent evt) {
		Format.formatLong(evt);
		actualizarTotalComprobacion((Integer)cantidadChequesComboBox.getSelectedItem());
    }

    private void montoTextField2KeyTyped(KeyEvent evt) {
		Format.formatLong(evt);
		actualizarTotalComprobacion((Integer)cantidadChequesComboBox.getSelectedItem());
    }

    private void montoTextField3KeyTyped(KeyEvent evt) {
		Format.formatLong(evt);
		actualizarTotalComprobacion((Integer)cantidadChequesComboBox.getSelectedItem());
    }

    private void montoTextField4KeyTyped(KeyEvent evt) {
		Format.formatLong(evt);
		actualizarTotalComprobacion((Integer)cantidadChequesComboBox.getSelectedItem());
    }

    private void montoTextField5KeyTyped(KeyEvent evt) {
		Format.formatLong(evt);
		actualizarTotalComprobacion((Integer)cantidadChequesComboBox.getSelectedItem());
    }

    private void montoTextField6KeyTyped(KeyEvent evt) {
		Format.formatLong(evt);
		actualizarTotalComprobacion((Integer)cantidadChequesComboBox.getSelectedItem());
    }

    private void imprimirButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    	if(validateMontoComprobacion()) {
	    	Date fecha = Format.stringToDate(this.fechaTextField1.getText());
	    	Long montoTotalFraccion = Long.valueOf(montoTextField1.getText());
	    	String fraccion = "(1/"+cantidadChequesComboBox.getSelectedItem()+")";
	    	printerService.printFactura(numerosFactura, fecha, montoTotalFraccion, fraccion);
        } else {
        	alertaImpresion();
        }
    	
    }

    private void imprimirButton2ActionPerformed(java.awt.event.ActionEvent evt) {
    	if(validateMontoComprobacion()) {
	    	Date fecha = Format.stringToDate(this.fechaTextField2.getText());
	    	Long montoTotalFraccion = Long.valueOf(montoTextField2.getText());
	    	String fraccion = "(2/"+cantidadChequesComboBox.getSelectedItem()+")";
	    	printerService.printFactura(numerosFactura, fecha, montoTotalFraccion, fraccion);
        } else {
        	alertaImpresion();
        }
    }

    private void imprimirButton3ActionPerformed(java.awt.event.ActionEvent evt) {
    	if(validateMontoComprobacion()) {
	    	Date fecha = Format.stringToDate(this.fechaTextField3.getText());
	    	Long montoTotalFraccion = Long.valueOf(montoTextField3.getText());
	    	String fraccion = "(3/"+cantidadChequesComboBox.getSelectedItem()+")";
	    	printerService.printFactura(numerosFactura, fecha, montoTotalFraccion, fraccion);
        } else {
        	alertaImpresion();
        }
    }

    private void imprimirButton4ActionPerformed(java.awt.event.ActionEvent evt) {
    	if(validateMontoComprobacion()) {
	    	Date fecha = Format.stringToDate(this.fechaTextField4.getText());
	    	Long montoTotalFraccion = Long.valueOf(montoTextField4.getText());
	    	String fraccion = "(4/"+cantidadChequesComboBox.getSelectedItem()+")";
	    	printerService.printFactura(numerosFactura, fecha, montoTotalFraccion, fraccion);
        } else {
        	alertaImpresion();
        }
    }

    private void imprimirButton5ActionPerformed(java.awt.event.ActionEvent evt) {
    	if(validateMontoComprobacion()) {
	    	Date fecha = Format.stringToDate(this.fechaTextField5.getText());
	    	Long montoTotalFraccion = Long.valueOf(montoTextField5.getText());
	    	String fraccion = "(5/"+cantidadChequesComboBox.getSelectedItem()+")";
	    	printerService.printFactura(numerosFactura, fecha, montoTotalFraccion, fraccion);
        } else {
        	alertaImpresion();
        }
    }

    private void imprimirButton6ActionPerformed(java.awt.event.ActionEvent evt) {
    	if(validateMontoComprobacion()) {
	    	Date fecha = Format.stringToDate(this.fechaTextField6.getText());
	    	Long montoTotalFraccion = Long.valueOf(montoTextField6.getText());
	    	String fraccion = "(6/"+cantidadChequesComboBox.getSelectedItem()+")";
	    	printerService.printFactura(numerosFactura, fecha, montoTotalFraccion, fraccion);
        } else {
        	alertaImpresion();
        }
    }
    
    private boolean validateMontoComprobacion() {
    	Long totalComprobacion = Long.valueOf(totalComprobacionTextField.getText());
    	Long totalPagar = Long.valueOf(totalPagarTextField.getText());
    	if(totalComprobacion.longValue()==totalPagar.longValue()) {
    		return true;
    	}
    	return false;
    }
    
    private void alertaImpresion() {
    	JOptionPane.showMessageDialog(this, "Para imprimir el Total a Pagar debe ser igual al Total de Comprobación.", "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    private void initComponents() {

        formaPagoPanel = new javax.swing.JPanel();
        cantidadChequesLabel = new javax.swing.JLabel();
        cantidadChequesComboBox = new javax.swing.JComboBox();
        totalPagarLabel = new javax.swing.JLabel();
        totalPagarTextField = new javax.swing.JTextField();
        totalComprobacionLabel = new javax.swing.JLabel();
        totalComprobacionTextField = new javax.swing.JTextField();
        chequesPanel = new javax.swing.JPanel();
        chequeLabel = new javax.swing.JLabel();
        montoLabel = new javax.swing.JLabel();
        fechaLabel = new javax.swing.JLabel();
        numeroChequeLabel1 = new javax.swing.JLabel();
        montoTextField1 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        fechaTextField1 = new JFormattedTextField(Format.getMaskDate());
        numeroChequeLabel2 = new javax.swing.JLabel();
        montoTextField2 = new javax.swing.JTextField();
        fechaTextField2 = new JFormattedTextField(Format.getMaskDate());
        fechaTextField3 = new JFormattedTextField(Format.getMaskDate());
        numeroChequeLabel3 = new javax.swing.JLabel();
        montoTextField3 = new javax.swing.JTextField();
        numeroChequeLabel4 = new javax.swing.JLabel();
        fechaTextField4 = new JFormattedTextField(Format.getMaskDate());
        montoTextField4 = new javax.swing.JTextField();
        numeroChequeLabel5 = new javax.swing.JLabel();
        fechaTextField5 = new JFormattedTextField(Format.getMaskDate());
        montoTextField5 = new javax.swing.JTextField();
        numeroChequeLabel6 = new javax.swing.JLabel();
        fechaTextField6 = new JFormattedTextField(Format.getMaskDate());
        montoTextField6 = new javax.swing.JTextField();
        imprimirButton1 = new javax.swing.JButton();
        imprimirButton2 = new javax.swing.JButton();
        imprimirButton3 = new javax.swing.JButton();
        imprimirButton4 = new javax.swing.JButton();
        imprimirButton5 = new javax.swing.JButton();
        imprimirButton6 = new javax.swing.JButton();
        botonesPanel = new javax.swing.JPanel();
        cancelarButton = new javax.swing.JButton();
        answerPanel = new javax.swing.JPanel();
        answerLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        formaPagoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Forma de Pago"));

        cantidadChequesLabel.setText("Cantidad de cheques:");

        cantidadChequesComboBox.setModel(new javax.swing.DefaultComboBoxModel(new Integer[] { 1, 2, 3, 4, 5, 6 }));
        cantidadChequesComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadChequesComboBoxActionPerformed(evt);
            }
        });

        totalPagarLabel.setText("Total a Pagar:");
        totalPagarTextField.setEditable(false);

        totalComprobacionLabel.setText("Total Comprobación:");
        totalComprobacionTextField.setEditable(false);

        javax.swing.GroupLayout formaPagoPanelLayout = new javax.swing.GroupLayout(formaPagoPanel);
        formaPagoPanel.setLayout(formaPagoPanelLayout);
        formaPagoPanelLayout.setHorizontalGroup(
            formaPagoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formaPagoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formaPagoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(totalComprobacionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalPagarLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cantidadChequesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formaPagoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(totalPagarTextField)
                    .addComponent(cantidadChequesComboBox, 0, 115, Short.MAX_VALUE)
                    .addComponent(totalComprobacionTextField))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        formaPagoPanelLayout.setVerticalGroup(
            formaPagoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formaPagoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formaPagoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cantidadChequesLabel)
                    .addComponent(cantidadChequesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formaPagoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalPagarLabel)
                    .addComponent(totalPagarTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formaPagoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalComprobacionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalComprobacionLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        chequesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Cheques"));

        chequeLabel.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N
        chequeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chequeLabel.setText("Cheque Nº");
        chequeLabel.setBorder(null);

        montoLabel.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N
        montoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        montoLabel.setText("Monto");
        montoLabel.setBorder(null);

        fechaLabel.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N
        fechaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fechaLabel.setText("Fecha");
        fechaLabel.setBorder(null);

        numeroChequeLabel1.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N
        numeroChequeLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numeroChequeLabel1.setText("1");
        numeroChequeLabel1.setBorder(null);

        numeroChequeLabel2.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N
        numeroChequeLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numeroChequeLabel2.setText("2");
        numeroChequeLabel2.setBorder(null);

        numeroChequeLabel3.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N
        numeroChequeLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numeroChequeLabel3.setText("3");
        numeroChequeLabel3.setBorder(null);

        numeroChequeLabel4.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N
        numeroChequeLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numeroChequeLabel4.setText("4");
        numeroChequeLabel4.setBorder(null);

        numeroChequeLabel5.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N
        numeroChequeLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numeroChequeLabel5.setText("5");
        numeroChequeLabel5.setBorder(null);

        numeroChequeLabel6.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N
        numeroChequeLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numeroChequeLabel6.setText("6");
        numeroChequeLabel6.setBorder(null);

        montoTextField1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
            	montoTextField1KeyTyped(evt);
            }
        });

        montoTextField2.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
            	montoTextField2KeyTyped(evt);
            }
        });

        montoTextField3.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
            	montoTextField3KeyTyped(evt);
            }
        });

        montoTextField4.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
            	montoTextField4KeyTyped(evt);
            }
        });

        montoTextField5.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
            	montoTextField5KeyTyped(evt);
            }
        });

        montoTextField6.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
            	montoTextField6KeyTyped(evt);
            }
        });

        
        
        imprimirButton1.setText("Imprimir 1");
        imprimirButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirButton1ActionPerformed(evt);
            }
        });

        imprimirButton2.setText("Imprimir 2");
        imprimirButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirButton2ActionPerformed(evt);
            }
        });

        imprimirButton3.setText("Imprimir 3");
        imprimirButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirButton3ActionPerformed(evt);
            }
        });

        imprimirButton4.setText("Imprimir 4");
        imprimirButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirButton4ActionPerformed(evt);
            }
        });

        imprimirButton5.setText("Imprimir 5");
        imprimirButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirButton5ActionPerformed(evt);
            }
        });

        imprimirButton6.setText("Imprimir 6");
        imprimirButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout chequesPanelLayout = new javax.swing.GroupLayout(chequesPanel);
        chequesPanel.setLayout(chequesPanelLayout);
        chequesPanelLayout.setHorizontalGroup(
            chequesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chequesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chequesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(chequesPanelLayout.createSequentialGroup()
                        .addGroup(chequesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(chequesPanelLayout.createSequentialGroup()
                                .addComponent(chequeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(montoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(chequesPanelLayout.createSequentialGroup()
                                .addComponent(numeroChequeLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(montoTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechaTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(imprimirButton2))
                            .addGroup(chequesPanelLayout.createSequentialGroup()
                                .addComponent(numeroChequeLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(montoTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechaTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(imprimirButton3))
                            .addGroup(chequesPanelLayout.createSequentialGroup()
                                .addComponent(numeroChequeLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(montoTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechaTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(imprimirButton4))
                            .addGroup(chequesPanelLayout.createSequentialGroup()
                                .addComponent(numeroChequeLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(montoTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechaTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(imprimirButton5))
                            .addGroup(chequesPanelLayout.createSequentialGroup()
                                .addComponent(numeroChequeLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(montoTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechaTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(imprimirButton6))
                            .addGroup(chequesPanelLayout.createSequentialGroup()
                                .addComponent(numeroChequeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(montoTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechaTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(imprimirButton1)))
                        .addGap(0, 69, Short.MAX_VALUE)))
                .addContainerGap())
        );
        chequesPanelLayout.setVerticalGroup(
            chequesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chequesPanelLayout.createSequentialGroup()
                .addGroup(chequesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chequeLabel)
                    .addComponent(montoLabel)
                    .addComponent(fechaLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(chequesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chequesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fechaTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(imprimirButton1))
                    .addGroup(chequesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(numeroChequeLabel1)
                        .addComponent(montoTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(chequesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chequesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fechaTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(imprimirButton2))
                    .addGroup(chequesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(numeroChequeLabel2)
                        .addComponent(montoTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(chequesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chequesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fechaTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(imprimirButton3))
                    .addGroup(chequesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(numeroChequeLabel3)
                        .addComponent(montoTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(chequesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chequesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fechaTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(imprimirButton4))
                    .addGroup(chequesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(numeroChequeLabel4)
                        .addComponent(montoTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(chequesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chequesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fechaTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(imprimirButton5))
                    .addGroup(chequesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(numeroChequeLabel5)
                        .addComponent(montoTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(chequesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chequesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fechaTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(imprimirButton6))
                    .addGroup(chequesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(numeroChequeLabel6)
                        .addComponent(montoTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botonesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        cancelarButton.setText("   Salir   ");
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });
        botonesPanel.add(cancelarButton);

        answerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        answerLabel.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        answerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout answerPanelLayout = new javax.swing.GroupLayout(answerPanel);
        answerPanel.setLayout(answerPanelLayout);
        answerPanelLayout.setHorizontalGroup(
            answerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(answerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        answerPanelLayout.setVerticalGroup(
            answerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(answerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(formaPagoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chequesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonesPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(answerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(formaPagoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chequesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(answerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
		this.setLocation(Pantalla.centrar(this.getSize()));
		this.setResizable(false);
		this.setTitle("Imprimir Cheques");
    }

	public void setFacturaService(FacturaService facturaService) {
		this.facturaService = facturaService;
	}

	public void setPrinterService(PrinterService printerService) {
		this.printerService = printerService;
	}

	public void setEmpresa(Empresa empresa) {
    	LOG.trace("Starting \"setEmpresa\" method.");
		this.empresa = empresa;
    	LOG.trace("Finishing \"setEmpresa\" method.");
	}

	public void setNumerosFactura(List<Long> numerosFactura) {
    	LOG.trace("Starting \"setNumerosFactura\" method.");
		this.numerosFactura = numerosFactura;
    	LOG.trace("Finishing \"setNumerosFactura\" method.");
	}
	
	public void cleanScreen() {
    	LOG.trace("Starting \"cleanScreen\" method.");
    	Integer cantidadCheques = 1;
    	cantidadChequesComboBox.getModel().setSelectedItem(cantidadCheques);
    	
    	facturas = facturaService.getFacturasByNumeros(numerosFactura);
    	
    	LOG.debug("Obtener el monto total de las facturas.");
    	Long montoTotal = 0L;
    	for(int i=0; i<facturas.size(); i++) {
    		Factura factura = facturas.get(i);
    		montoTotal += factura.getMonto();
    	}

    	totalPagarTextField.setText(Long.toString(montoTotal));
    	
    	changeCantidadChequesInfo(cantidadCheques);

    	LOG.trace("Finishing \"cleanScreen\" method.");		
	}
	
	private void changeCantidadChequesInfo(Integer cantidadCheques) {
    	LOG.trace("Starting \"changeCantidadChequesInfo\" method.");

    	montoTextField1.setText("");
    	montoTextField2.setText("");
    	montoTextField3.setText("");
    	montoTextField4.setText("");
    	montoTextField5.setText("");
    	montoTextField6.setText("");

    	montoTextField1.setEditable(false);
    	montoTextField2.setEditable(false);
    	montoTextField3.setEditable(false);
    	montoTextField4.setEditable(false);
    	montoTextField5.setEditable(false);
    	montoTextField6.setEditable(false);

    	fechaTextField1.setText("");
    	fechaTextField2.setText("");
    	fechaTextField3.setText("");
    	fechaTextField4.setText("");
    	fechaTextField5.setText("");
    	fechaTextField6.setText("");

    	fechaTextField1.setEditable(false);
    	fechaTextField2.setEditable(false);
    	fechaTextField3.setEditable(false);
    	fechaTextField4.setEditable(false);
    	fechaTextField5.setEditable(false);
    	fechaTextField6.setEditable(false);

    	imprimirButton1.setEnabled(false);
    	imprimirButton2.setEnabled(false);
    	imprimirButton3.setEnabled(false);
    	imprimirButton4.setEnabled(false);
    	imprimirButton5.setEnabled(false);
    	imprimirButton6.setEnabled(false);
    	
    	LOG.debug("Obtener el monto total de las facturas.");
    	Long montoTotal = 0L;
    	for(int i=0; i<facturas.size(); i++) {
    		Factura factura = facturas.get(i);
    		montoTotal += factura.getMonto();
    	}
    	
    	LOG.debug("Calcular el monto de cada cheque.");
    	int diferencia = 0;
    	long montos[] = new long[cantidadCheques];
    	while ( (montoTotal % cantidadCheques) != 0) {
    		montoTotal++;
    		diferencia++;
    	}
    	
    	for(int i=0; i<cantidadCheques; i++) {
    		montos[i] = montoTotal / cantidadCheques;
    	}
    	montos[cantidadCheques-1] -= diferencia;

    	LOG.debug("Calcular la fecha de cada cheque.");
    	Calendar cal = new GregorianCalendar();
    	//Calendar cal = new GregorianCalendar(2013, 0, 31);
    	int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
    	int month = cal.get(Calendar.MONTH);
    	Date[] fechas = new Date[cantidadCheques];
    	fechas[0] = cal.getTime();
    	for(int i=1; i<cantidadCheques; i++) {
    		cal.set(Calendar.DAY_OF_MONTH, 1);
    		cal.add(Calendar.MONTH, 1);
    		int maxDayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    		if(dayOfMonth>maxDayOfMonth) {
    			cal.set(Calendar.DAY_OF_MONTH, maxDayOfMonth);
    		} else {
    			cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    		}
    		fechas[i] = cal.getTime();
    	}
    	
    	
    	switch (cantidadCheques) {
		case 6:
			montoTextField6.setEditable(true);
	    	fechaTextField6.setEditable(true);
	    	imprimirButton6.setEnabled(true);
	    	montoTextField6.setText(Long.toString(montos[5]));
			fechaTextField6.setText(Format.dateToString(fechas[5]));
		case 5:
			montoTextField5.setEditable(true);
	    	fechaTextField5.setEditable(true);
	    	imprimirButton5.setEnabled(true);
	    	montoTextField5.setText(Long.toString(montos[4]));
			fechaTextField5.setText(Format.dateToString(fechas[4]));
		case 4:
			montoTextField4.setEditable(true);
	    	fechaTextField4.setEditable(true);
	    	imprimirButton4.setEnabled(true);
	    	montoTextField4.setText(Long.toString(montos[3]));
			fechaTextField4.setText(Format.dateToString(fechas[3]));
		case 3:
			montoTextField3.setEditable(true);
	    	fechaTextField3.setEditable(true);
	    	imprimirButton3.setEnabled(true);
	    	montoTextField3.setText(Long.toString(montos[2]));
			fechaTextField3.setText(Format.dateToString(fechas[2]));
		case 2:
			montoTextField2.setEditable(true);
	    	fechaTextField2.setEditable(true);
	    	imprimirButton2.setEnabled(true);
	    	montoTextField2.setText(Long.toString(montos[1]));
			fechaTextField2.setText(Format.dateToString(fechas[1]));
		case 1:
			montoTextField1.setEditable(true);
	    	fechaTextField1.setEditable(true);
	    	imprimirButton1.setEnabled(true);
	    	montoTextField1.setText(Long.toString(montos[0]));
			fechaTextField1.setText(Format.dateToString(fechas[0]));
		default:
			break;
		}				
    	    	
    	actualizarTotalComprobacion(cantidadCheques);
    	LOG.trace("Finishing \"changeCantidadChequesInfo\" method.");				
	}

	private void actualizarTotalComprobacion(int cantidadCheques) {
		cantidadChequesTmp = cantidadCheques;
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(200);
					long totalComprobacion = 0L;
					switch (cantidadChequesTmp) {
					case 6:
						totalComprobacion += montoTextField6.getText().equals("") ? 0L : Long.valueOf(montoTextField6.getText());
					case 5:
						totalComprobacion += montoTextField5.getText().equals("") ? 0L : Long.valueOf(montoTextField5.getText());
					case 4:
						totalComprobacion += montoTextField4.getText().equals("") ? 0L : Long.valueOf(montoTextField4.getText());
					case 3:
						totalComprobacion += montoTextField3.getText().equals("") ? 0L : Long.valueOf(montoTextField3.getText());
					case 2:
						totalComprobacion += montoTextField2.getText().equals("") ? 0L : Long.valueOf(montoTextField2.getText());
					case 1:
						totalComprobacion += montoTextField1.getText().equals("") ? 0L : Long.valueOf(montoTextField1.getText());
					default:
						break;
					}
					totalComprobacionTextField.setText(Long.toString(totalComprobacion));
				} catch (InterruptedException e) {
				}
			}
		});
		t.start();

		
	}

}
