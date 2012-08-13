package cl.tival.check.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Cheque implements Serializable {

	private static final long serialVersionUID = 1L;
	private String numero;
	private Date fecha;
	private String estado;
	private String quienCobra;
	private Empresa empresa;
	private List<Factura> facturas;
	private Boolean activo;

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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getQuienCobra() {
		return quienCobra;
	}

	public void setQuienCobra(String quienCobra) {
		this.quienCobra = quienCobra;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}
