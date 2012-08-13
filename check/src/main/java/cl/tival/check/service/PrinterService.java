package cl.tival.check.service;

import java.util.Date;
import java.util.List;

public interface PrinterService {
	
	public void printFactura(List<Long> numerosFactura);

	public void printFactura(List<Long> numerosFactura, Date fecha, Long montoTotalFraccion, String fraccion);

	public void marcarFacturaimpresa(List<Long> numerosFactura);
	
}
