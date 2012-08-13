package cl.tival.check.ui;

import java.util.Date;

import cl.tival.check.model.Factura;

public class FacturaFilter {
	
	private Long numeroContains;
	private Date fechaContains;

	public Long getNumeroContains() {
		return numeroContains;
	}

	public void setNumeroContains(Long numeroContains) {
		this.numeroContains = numeroContains;
	}

	public Date getFechaContains() {
		return fechaContains;
	}

	public void setFechaContains(Date fechaContains) {
		this.fechaContains = fechaContains;
	}

	public static FacturaFilter fromFactura(Factura f) {
		FacturaFilter filter = new FacturaFilter();
		filter.setNumeroContains(f.getNumero());
		filter.setFechaContains(f.getFecha());
		return filter;
	}
}
