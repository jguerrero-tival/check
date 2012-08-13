package cl.tival.check.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.tival.check.app.CheckConstants;
import cl.tival.check.model.Empresa;
import cl.tival.check.model.Factura;
import cl.tival.check.service.FacturaService;
import cl.tival.check.util.Format;
import cl.tival.check.util.Message;
import cl.tival.check.util.Pantalla;

public class ModificarFacturaDialog extends JDialog {

	final static Logger LOG = LoggerFactory.getLogger(ModificarFacturaDialog.class);
	private static final long serialVersionUID = 1L;
	private Empresa empresa;
	private Factura factura;
	private FacturaService facturaService;
	
	// Variables declaration - do not modify
    private JButton modificarButton;
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

    public ModificarFacturaDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    private void cleanDialog() {
    	LOG.trace("Starting \"cleanDialog\" method.");
    	//rutTextField.setText("");
    	//razonSocialTextField.setText("");
    	//numeroTextField.setText("");
    	//fechaTextField.setText("");
    	//montoTextField.setText("");
    	LOG.trace("Finishing \"cleanDialog\" method.");
    }

    private void modificarButtonActionPerformed(ActionEvent evt) {
    	LOG.trace("Starting \"modificarButtonActionPerformed\" method.");
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
			factura.setActivo(this.factura.getActivo());
			factura.setEstado(this.factura.getEstado());
			int answer = facturaService.updateFactura(this.factura.getNumero(), factura);
			this.setAnswer(answer);
		}
		LOG.trace("Finishing \"modificarButtonActionPerformed\" method.");
	}

	private void setAnswer(int answer) {
    	LOG.trace("Starting \"setAnswer\" method.");

		if (answer == CheckConstants.MODIFICAR_FACTURA_OK) {
			Message.timerLabel(this.answerLabel, Color.GREEN, "El registro se modificó exitosamente.");
			this.cleanDialog();
		} else if (answer == CheckConstants.MODIFICAR_FACTURA_NO_EXISTE) {
			Message.timerLabel(this.answerLabel, Color.RED, "EL registro a modificar no existe.");
		} else if (answer == CheckConstants.MODIFICAR_FACTURA_DESACTIVADO) {
			Message.timerLabel(this.answerLabel, Color.RED, "EL registro a modificar se encuentra desactivado.");
		} else if (answer == CheckConstants.MODIFICAR_FACTURA_FAIL) {
			Message.timerLabel(this.answerLabel, Color.RED, "Existe un error en el sistema al modificar el registro.");
		}

		LOG.trace("Finishing \"setAnswer\" method.");
	}
	
    private void cancelarButtonActionPerformed(ActionEvent evt) {
    	LOG.trace("Starting \"cancelarButtonActionPerformed\" method.");
    	this.cleanDialog();
    	this.dispose();
    	LOG.trace("Finishing \"cancelarButtonActionPerformed\" method.");
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
        modificarButton = new JButton();
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

        modificarButton.setText("Modificar");
        modificarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                modificarButtonActionPerformed(evt);
            }
        });
        botonesPanel.add(modificarButton);

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
		this.setTitle("Modificar Factura");
    }
    
	public void setFacturaService(FacturaService facturaService) {
		this.facturaService = facturaService;
	}
	
	public void setEmpresa(Empresa empresa) {
    	LOG.trace("Starting \"setEmpresa\" method.");
		this.empresa = empresa;
		rutTextField.setText(this.empresa.getRut());
		razonSocialTextField.setText(this.empresa.getRazonSocial());
    	LOG.trace("Finishing \"setEmpresa\" method.");
	}
	
	public void setFactura(Factura factura) {
    	LOG.trace("Starting \"setFactura\" method.");
		this.factura = factura;
		numeroTextField.setText(this.factura.getNumero().toString());
		fechaTextField.setText(Format.dateToString(this.factura.getFecha()));
		montoTextField.setText(this.factura.getMonto().toString());
    	LOG.trace("Finishing \"setFactura\" method.");
	}

}
