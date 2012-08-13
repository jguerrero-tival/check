package cl.tival.check.ui;

import cl.tival.check.model.Factura;

import com.jgoodies.forms.layout.FormLayout;
import org.springframework.richclient.form.FormModelHelper;
import org.springframework.richclient.form.TabbedForm;
import org.springframework.richclient.form.builder.FormLayoutFormBuilder;

public class FacturaForm extends TabbedForm {

	public FacturaForm() {
		super(FormModelHelper.createFormModel(new Factura(), "facturaForm"));
	}

	protected Tab[] getTabs() {
		FormLayout layout = new FormLayout("default, 3dlu, fill:pref:nogrow", "default");
		FormLayoutFormBuilder builder = new FormLayoutFormBuilder(getBindingFactory(), layout);
		setFocusControl(builder.addPropertyAndLabel("numero")[0]);
		builder.nextRow();
		builder.addPropertyAndLabel("fecha");
		builder.nextRow();
		builder.addPropertyAndLabel("monto");
		builder.nextRow();
		builder.addPropertyAndLabel("estado");
		builder.nextRow();
		builder.addPropertyAndLabel("empresa");
//		builder.nextRow();
//		builder.addPropertyAndLabel("anombrede");

		return new Tab[] { new Tab("Detalle de la Factura", builder.getPanel()) };
	}
}