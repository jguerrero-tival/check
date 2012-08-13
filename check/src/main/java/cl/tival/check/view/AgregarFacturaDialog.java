package cl.tival.check.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import cl.tival.check.app.CheckConstants;
import cl.tival.check.model.Empresa;
import cl.tival.check.model.Factura;
import cl.tival.check.service.FacturaService;
import cl.tival.check.util.Format;
import cl.tival.check.util.Message;
import cl.tival.check.util.Pantalla;

public class AgregarFacturaDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private Empresa empresa;
	private FacturaService facturaService;
	
	// Variables declaration - do not modify
    private JButton agregarButton;
    private JLabel answerLabel;
    private JPanel answerPanel;
    private JPanel botonesPanel;
    private JButton cancelarButton;
    private JPanel empresaPanel;
    private JLabel fechaLabel;
    private JFormattedTextField fechaTextField;
    private JLabel formatoFechaLabel;
    private JPanel jPanel1;
    private JLabel montoLabel;
    private JTextField montoTextField;
    private JLabel numeroLabel;
    private JTextField numeroTextField;
    private JLabel razonSocialLabel;
    private JTextField razonSocialTextField;
    private JLabel rutLabel;
    private JTextField rutTextField;
    // End of variables declaration

    public AgregarFacturaDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    private void cleanDialog() {
    	//rutTextField.setText("");
    	//razonSocialTextField.setText("");
    	numeroTextField.setText("");
    	fechaTextField.setText("");
    	montoTextField.setText("");
    }

    private void agregarButtonActionPerformed(ActionEvent evt) {
		if (this.numeroTextField.getText().trim().length()==0) {
			Message.timerLabel(this.answerLabel, Color.RED, "Debe rellenar el campo \"Número (Factura)\".");
		} else if (this.fechaTextField.getText().equals("__/__/____")) {
			Message.timerLabel(this.answerLabel, Color.RED, "Debe rellenar el campo \"Fecha (Factura)\".");
		} else if (Format.stringToDate(this.fechaTextField.getText())==null) {
			Message.timerLabel(this.answerLabel, Color.RED, "El campo \"Fecha (Factura)\" no es válido.");
		} else if(this.montoTextField.getText().trim().length()==0) {
			Message.timerLabel(this.answerLabel, Color.RED, "Debe rellenar el campo \"Monto + IVA (Factura)\".");
		} else{
			Factura factura = new Factura();
			factura.setEmpresa(this.empresa);
			factura.setNumero(Long.valueOf(numeroTextField.getText()));
			factura.setFecha(Format.stringToDate(this.fechaTextField.getText()));
			factura.setMonto(Long.valueOf(montoTextField.getText()));
			factura.setActivo(Boolean.TRUE);
			factura.setEstado(CheckConstants.ESTADO_FACTURA_INGRESADA);
			
			int answer = facturaService.addFactura(factura);
			this.setAnswer(answer);
		}    
	}

	private void setAnswer(int answer) {
		if (answer == CheckConstants.AGREGAR_FACTURA_OK) {
			Message.timerLabel(this.answerLabel, Color.GREEN, "El registro se agregó exitosamente.");
			this.cleanDialog();
		} else if (answer == CheckConstants.AGREGAR_FACTURA_EXISTE) {
			Message.timerLabel(this.answerLabel, Color.RED, "Ya existe el registro en el sistema.");
		} else if (answer == CheckConstants.AGREGAR_FACTURA_ACTIVADO) {
			Message.timerLabel(this.answerLabel, Color.RED, "El registro estaba eliminado, se recupero exitosamente.");
			this.cleanDialog();
		} else if (answer == CheckConstants.AGREGAR_FACTURA_FAIL) {
			Message.timerLabel(this.answerLabel, Color.RED, "Existe un error en el sistema al guardar el registro.");
		}
	}
	
    private void cancelarButtonActionPerformed(ActionEvent evt) {
    	this.cleanDialog();
    	this.dispose();
    }

    private void numeroTextFieldKeyTyped(KeyEvent evt) {
		Format.formatLong(evt);
    }

    private void fechaTextFieldKeyTyped(KeyEvent evt) {
        // TODO add your handling code here:
    }

    private void montoTextFieldKeyTyped(KeyEvent evt) {
		Format.formatLong(evt);
    }
    
    private void initComponents() {
        empresaPanel = new JPanel();
        rutLabel = new JLabel();
        razonSocialLabel = new JLabel();
        razonSocialTextField = new JTextField();
        rutTextField = new JTextField();
        jPanel1 = new JPanel();
        numeroLabel = new JLabel();
        fechaLabel = new JLabel();
        montoLabel = new JLabel();
        numeroTextField = new JTextField();
		fechaTextField = new JFormattedTextField(Format.getMaskDate());
        montoTextField = new JTextField();
        formatoFechaLabel = new JLabel();
        botonesPanel = new JPanel();
        agregarButton = new JButton();
        cancelarButton = new JButton();
        answerPanel = new JPanel();
        answerLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        empresaPanel.setBorder(BorderFactory.createTitledBorder("Empresa"));

        rutLabel.setText("RUT:");

        razonSocialLabel.setText("Razón Social:");

        razonSocialTextField.setEditable(false);

        rutTextField.setEditable(false);

        GroupLayout empresaPanelLayout = new GroupLayout(empresaPanel);
        empresaPanel.setLayout(empresaPanelLayout);
        empresaPanelLayout.setHorizontalGroup(
            empresaPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(empresaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(empresaPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(rutLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(razonSocialLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(empresaPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(empresaPanelLayout.createSequentialGroup()
                        .addComponent(rutTextField, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(razonSocialTextField))
                .addContainerGap())
        );
        empresaPanelLayout.setVerticalGroup(
            empresaPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(empresaPanelLayout.createSequentialGroup()
                .addGroup(empresaPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(rutLabel)
                    .addComponent(rutTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(empresaPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(razonSocialLabel)
                    .addComponent(razonSocialTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.setBorder(BorderFactory.createTitledBorder("Factura"));

        numeroLabel.setText("Número:");

        fechaLabel.setText("Fecha:");

        montoLabel.setText("Monto + IVA:");

        numeroTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                numeroTextFieldKeyTyped(evt);
            }
        });

        fechaTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                fechaTextFieldKeyTyped(evt);
            }
        });

        montoTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                montoTextFieldKeyTyped(evt);
            }
        });

        formatoFechaLabel.setText("(dd/mm/aaaa)");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(fechaLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(montoLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(numeroLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(numeroTextField, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addComponent(fechaTextField)
                    .addComponent(montoTextField))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formatoFechaLabel)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(numeroLabel)
                    .addComponent(numeroTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(fechaLabel)
                    .addComponent(fechaTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatoFechaLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(montoLabel)
                    .addComponent(montoTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        );

        botonesPanel.setBorder(BorderFactory.createTitledBorder(""));

        agregarButton.setText("Agregar");
        agregarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                agregarButtonActionPerformed(evt);
            }
        });
        botonesPanel.add(agregarButton);

        cancelarButton.setText("Salir");
        cancelarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });
        botonesPanel.add(cancelarButton);

        answerPanel.setBorder(BorderFactory.createTitledBorder(""));

        answerLabel.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        answerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        GroupLayout answerPanelLayout = new GroupLayout(answerPanel);
        answerPanel.setLayout(answerPanelLayout);
        answerPanelLayout.setHorizontalGroup(
            answerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(answerLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        answerPanelLayout.setVerticalGroup(
            answerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(answerLabel, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(empresaPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonesPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                    .addComponent(answerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(empresaPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonesPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(answerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
		this.setLocation(Pantalla.centrar(this.getSize()));
		this.setResizable(false);
		this.setTitle("Agregar Factura");
    }
    
	public void setFacturaService(FacturaService facturaService) {
		this.facturaService = facturaService;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
		rutTextField.setText(this.empresa.getRut());
		razonSocialTextField.setText(this.empresa.getRazonSocial());
	}

}
