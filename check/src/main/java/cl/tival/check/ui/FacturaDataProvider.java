package cl.tival.check.ui;

import org.springframework.richclient.widget.editor.provider.AbstractDataProvider;

import cl.tival.check.model.Factura;
import cl.tival.check.service.FacturaService;

import java.util.List;

public class FacturaDataProvider extends AbstractDataProvider {

	private FacturaService service;

	public FacturaDataProvider(FacturaService service) {
		this.service = service;
	}

	public boolean supportsFiltering() {
		return true;
	}

	public List getList(Object criteria) {
		if (criteria instanceof FacturaFilter) {
			return service.findFacturas((FacturaFilter) criteria);
		} else if (criteria instanceof Factura) {
			return service.findFacturas(FacturaFilter.fromFactura((Factura) criteria));
		} else {
			throw new IllegalArgumentException("This provider can only filter through SupplierFilter, not " + criteria.getClass());
		}
	}

	public boolean supportsUpdate() {
		return true;
	}

	@Override
	public Object doCreate(Object newData) {
		service.saveFactura((Factura) newData);
		return newData;
	}

	@Override
	public void doDelete(Object dataToRemove) {
		service.deactivateFactura((Factura) dataToRemove);
	}

	@Override
	public Object doUpdate(Object updatedData) {
		service.saveFactura((Factura) updatedData);
		return updatedData;
	}

	public boolean supportsCreate() {
		return true;
	}

	public boolean supportsClone() {
		return true;
	}

	public boolean supportsDelete() {
		return true;
	}
}
