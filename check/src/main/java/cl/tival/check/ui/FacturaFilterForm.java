package cl.tival.check.ui;

import javax.swing.JComponent;

import cl.tival.check.model.Factura;

import com.jgoodies.forms.layout.FormLayout;
import org.springframework.richclient.form.FilterForm;
import org.springframework.richclient.form.builder.FormLayoutFormBuilder;

public class FacturaFilterForm extends FilterForm {

	public FacturaFilterForm() {
		super("facturaFilterForm");
	}

	@Override
	protected Object newFormObject() {
		return new FacturaFilter();
	}

	@Override
	public void setFormObject(Object formObject) {
		if (formObject instanceof Factura) {
			super.setFormObject(FacturaFilter.fromFactura((Factura) formObject));
		} else {
			super.setFormObject(formObject);
		}
	}

	protected JComponent createFormControl() {
		FormLayout layout = new FormLayout("default, 3dlu, fill:pref:nogrow", "default");
		FormLayoutFormBuilder builder = new FormLayoutFormBuilder(getBindingFactory(), layout);
		builder.addPropertyAndLabel("numeroContains");
		builder.nextRow();
		builder.addPropertyAndLabel("fechaContains");
		return builder.getPanel();
	}
}
