package cl.tival.check.ui;

import org.springframework.richclient.widget.editor.DefaultDataEditorWidget;
import org.springframework.richclient.widget.table.PropertyColumnTableDescription;

import cl.tival.check.model.Factura;

public class FacturaDataEditor extends DefaultDataEditorWidget {
	private static final PropertyColumnTableDescription TABLE_DESCRIPTION;

	static {
		TABLE_DESCRIPTION = new PropertyColumnTableDescription("facturaDataEditor", Factura.class);
		TABLE_DESCRIPTION.addPropertyColumn("numero");
		TABLE_DESCRIPTION.addPropertyColumn("fecha");
		TABLE_DESCRIPTION.addPropertyColumn("monto");
		TABLE_DESCRIPTION.addPropertyColumn("estado");
		TABLE_DESCRIPTION.addPropertyColumn("empresa");
		//TABLE_DESCRIPTION.addPropertyColumn("anombrede");		
	}

	public FacturaDataEditor(FacturaDataProvider facturaDataProvider) {
		super("facturaDataEditor", facturaDataProvider, new FacturaForm(), TABLE_DESCRIPTION, new FacturaFilterForm());
	}
}
