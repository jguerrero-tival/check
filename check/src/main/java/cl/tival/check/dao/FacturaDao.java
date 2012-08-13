package cl.tival.check.dao;

import java.util.List;

import cl.tival.check.model.Factura;

public interface FacturaDao {

	public Factura getFactura(Long numeroFactura);

	public List<Factura> getFacturas(Boolean activoFactura);

	public  List<Factura> getFacturasByEmpresa(String rutEmpresa, Boolean activoFactura);
	
	public void saveFactura(Factura factura);

	public void activateFactura(Long idFactura);

	public void deactivateFactura(Long idFactura);

}
