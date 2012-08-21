package cl.tival.check.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Cheque implements Serializable {

	private static final long serialVersionUID = 1L;
	private String numero;
	private Date fecha;
	private String fraccion;
	private Set<Factura> facturas;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getFraccion() {
		return fraccion;
	}

	public void setFraccion(String fraccion) {
		this.fraccion = fraccion;
	}

	public Set<Factura> getFacturas() {
		if (facturas == null) {
			facturas = new HashSet<Factura>();
		}
		return facturas;
	}

	public void setFacturas(Set<Factura> facturas) {
		this.facturas = facturas;
	}

	public void addFactura(Factura factura) {
		if(factura!=null) {
			factura.getCheques().add(this);
			getFacturas().add(factura);
		}
	}

}
