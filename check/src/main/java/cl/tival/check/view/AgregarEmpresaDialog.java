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
import cl.tival.check.service.EmpresaService;
import cl.tival.check.util.Format;
import cl.tival.check.util.Message;
import cl.tival.check.util.Pantalla;
import cl.tival.check.util.Rut;

public class AgregarEmpresaDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private EmpresaService empresaService;
	
	// Variables declaration - do not modify
    private JButton agregarButton;
    private JPanel botonesPanel;
    private JButton cancelarButton;
    private JPanel empresaPanel;
    private JPanel facturacionPanel;
    private JLabel razonSocialEmpresaLabel;
    private JTextField razonSocialEmpresaTextField;
    private JLabel razonSocialFacturaLabel;
    private JTextField razonSocialFacturaTextField;
    private JLabel rutEmpresaLabel;
    private JFormattedTextField rutEmpresaTextField;
    private JLabel rutFacturaLabel;
    private JFormattedTextField rutFacturaTextField;
    private JPanel answerPanel;
    private JLabel answerLabel;
    // End of variables declaration

    public AgregarEmpresaDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private void cleanDialog() {
    	rutEmpresaTextField.setText("");
    	razonSocialEmpresaTextField.setText("");
    	rutFacturaTextField.setText("");
    	razonSocialFacturaTextField.setText("");
    }

    private void rutEmpresaTextFieldKeyTyped(KeyEvent evt) {
		Format.cadenaUpperCase(evt);
    }

    private void razonSocialEmpresaTextFieldKeyTyped(KeyEvent evt) {
		Format.cadenaUpperCase(evt);
    }

    private void rutFacturaTextFieldKeyTyped(KeyEvent evt) {
		Format.cadenaUpperCase(evt);
    }

    private void razonSocialFacturaTextFieldKeyTyped(KeyEvent evt) {
		Format.cadenaUpperCase(evt);
    }

    private void cancelarButtonActionPerformed(ActionEvent evt) {
    	this.cleanDialog();
    	this.dispose();
    }

    private void agregarButtonActionPerformed(ActionEvent evt) {
		if (this.rutEmpresaTextField.getText().equals("__.___.___-_")) {
			Message.timerLabel(this.answerLabel, Color.RED, "Debe rellenar el campo \"Rut (Empresa)\".");
		} else if (!Rut.verificaRut(this.rutEmpresaTextField.getText())) {
			Message.timerLabel(this.answerLabel, Color.RED, "El campo \"Rut (Empresa)\" no es correcto.");
		} else if(this.razonSocialEmpresaTextField.getText().trim().length()==0) {
			Message.timerLabel(this.answerLabel, Color.RED, "Debe rellenar el campo \"Razón Social (Empresa)\".");			
		} else if (this.rutFacturaTextField.getText().equals("__.___.___-_")) {
			Message.timerLabel(this.answerLabel, Color.RED, "Debe rellenar el campo \"Rut (Cheque)\".");
		} else if (!Rut.verificaRut(this.rutFacturaTextField.getText())) {
			Message.timerLabel(this.answerLabel, Color.RED, "El campo \"Rut (Cheque)\" no es correcto.");
		} else if(this.razonSocialFacturaTextField.getText().trim().length()==0) {
			Message.timerLabel(this.answerLabel, Color.RED, "Debe rellenar el campo \"Razón Social (Cheque)\".");			
		} else{
			Empresa empresa = new Empresa();
			empresa.setActivo(Boolean.TRUE);
			empresa.setRut(this.rutEmpresaTextField.getText());
			empresa.setRazonSocial(this.razonSocialEmpresaTextField.getText());
			empresa.setRutRepresentante(this.rutFacturaTextField.getText());
			empresa.setRazonSocialRepresentante(this.razonSocialFacturaTextField.getText());
			
			int answer = empresaService.addEmpresa(empresa);
			this.setAnswer(answer);
		}
	}
    
	private void setAnswer(int answer) {
		if (answer == CheckConstants.AGREGAR_EMPRESA_OK) {
			Message.timerLabel(this.answerLabel, Color.GREEN, "El registro se agregó exitosamente.");
			this.cleanDialog();
		} else if (answer == CheckConstants.AGREGAR_EMPRESA_EXISTE) {
			Message.timerLabel(this.answerLabel, Color.RED, "Ya existe el registro en el sistema.");
		} else if (answer == CheckConstants.AGREGAR_EMPRESA_ACTIVADO) {
			Message.timerLabel(this.answerLabel, Color.RED, "El registro estaba eliminado, se recupero exitosamente.");
			this.cleanDialog();
		} else if (answer == CheckConstants.AGREGAR_EMPRESA_FAIL) {
			Message.timerLabel(this.answerLabel, Color.RED, "Existe un error en el sistema al guardar el registro.");
		}
	}
    
    private void initComponents() {

        empresaPanel = new JPanel();
        rutEmpresaLabel = new JLabel();
        razonSocialEmpresaLabel = new JLabel();
        rutEmpresaTextField = new JFormattedTextField(Format.getMaskRut());
        razonSocialEmpresaTextField = new JTextField();
        facturacionPanel = new JPanel();
        rutFacturaLabel = new JLabel();
        razonSocialFacturaLabel = new JLabel();
        rutFacturaTextField = new JFormattedTextField(Format.getMaskRut());
        razonSocialFacturaTextField = new JTextField();
        botonesPanel = new JPanel();
        agregarButton = new JButton();
        cancelarButton = new JButton();
        answerPanel = new JPanel();
        answerLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        empresaPanel.setBorder(BorderFactory.createTitledBorder("Información de la Empresa"));

        rutEmpresaLabel.setText("RUT:");

        razonSocialEmpresaLabel.setText("Razón Social:");

        rutEmpresaTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                rutEmpresaTextFieldKeyTyped(evt);
            }
        });

        razonSocialEmpresaTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                razonSocialEmpresaTextFieldKeyTyped(evt);
            }
        });

        GroupLayout empresaPanelLayout = new GroupLayout(empresaPanel);
        empresaPanel.setLayout(empresaPanelLayout);
        empresaPanelLayout.setHorizontalGroup(
            empresaPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(empresaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(empresaPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(razonSocialEmpresaLabel, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(rutEmpresaLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(empresaPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(razonSocialEmpresaTextField)
                    .addGroup(empresaPanelLayout.createSequentialGroup()
                        .addComponent(rutEmpresaTextField, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 243, Short.MAX_VALUE)))
                .addContainerGap())
        );
        empresaPanelLayout.setVerticalGroup(
            empresaPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(empresaPanelLayout.createSequentialGroup()
                .addGroup(empresaPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(rutEmpresaLabel)
                    .addComponent(rutEmpresaTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(empresaPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(razonSocialEmpresaLabel)
                    .addComponent(razonSocialEmpresaTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        );

        facturacionPanel.setBorder(BorderFactory.createTitledBorder("Datos para el cheque"));

        rutFacturaLabel.setText("RUT:");

        razonSocialFacturaLabel.setText("Razón Social:");

        rutFacturaTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                rutFacturaTextFieldKeyTyped(evt);
            }
        });

        razonSocialFacturaTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                razonSocialFacturaTextFieldKeyTyped(evt);
            }
        });

        GroupLayout facturacionPanelLayout = new GroupLayout(facturacionPanel);
        facturacionPanel.setLayout(facturacionPanelLayout);
        facturacionPanelLayout.setHorizontalGroup(
            facturacionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(facturacionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(facturacionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(rutFacturaLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(razonSocialFacturaLabel, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(facturacionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(razonSocialFacturaTextField)
                    .addGroup(facturacionPanelLayout.createSequentialGroup()
                        .addComponent(rutFacturaTextField, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        facturacionPanelLayout.setVerticalGroup(
            facturacionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(facturacionPanelLayout.createSequentialGroup()
                .addGroup(facturacionPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(rutFacturaLabel)
                    .addComponent(rutFacturaTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(facturacionPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(razonSocialFacturaLabel)
                    .addComponent(razonSocialFacturaTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
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

        GroupLayout jPanelAnswerLayout = new GroupLayout(answerPanel);
        answerPanel.setLayout(jPanelAnswerLayout);
        jPanelAnswerLayout.setHorizontalGroup(
            jPanelAnswerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(answerLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelAnswerLayout.setVerticalGroup(
            jPanelAnswerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(answerLabel, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(botonesPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(facturacionPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(empresaPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(answerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(empresaPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(facturacionPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonesPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(answerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    	
        pack();
		this.setLocation(Pantalla.centrar(this.getSize()));
		this.setResizable(false);
		this.setTitle("Agregar Empresa");

    }

	public void setEmpresaService(EmpresaService empresaService) {
		this.empresaService = empresaService;
	}

}
