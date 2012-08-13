package cl.tival.check.service;

import java.util.List;

import cl.tival.check.model.Factura;
import cl.tival.check.table.FacturaTableModel;
import cl.tival.check.ui.FacturaFilter;

public interface FacturaService {
	
	public List<Factura> getFacturasActivas(String empresa);

	public List<Factura> getFacturasByNumeros(List<Long> numerosFactura);

	public FacturaTableModel getFacturasActivasTableModel(String rutEmpresa);

	public int addFactura(Factura factura);
	
	public int deleteFactura(Long numeroFactura);

	public int updateFactura(Long numeroFacturaActual, Factura factura);
	
	public Factura getFactura(Long numeroFactura);
	
	
	
	
	
	
	
	
	
	public List<Factura> findFacturas(final FacturaFilter filter);

	public boolean checkFilter(Factura factura, FacturaFilter filter);

	public void saveFactura(Factura factura);
	
	public void deactivateFactura(Factura factura);
	
}
