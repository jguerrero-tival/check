package cl.tival.check.model;

import java.io.Serializable;
import java.util.List;

public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;
	private String rut;
	private String razonSocial;
	private String rutRepresentante;
	private String razonSocialRepresentante;
	private List<Factura> facturas;
	private List<Cheque> cheques;
	private Boolean activo;

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getRutRepresentante() {
		return rutRepresentante;
	}

	public void setRutRepresentante(String rutRepresentante) {
		this.rutRepresentante = rutRepresentante;
	}

	public String getRazonSocialRepresentante() {
		return razonSocialRepresentante;
	}

	public void setRazonSocialRepresentante(String razonSocialRepresentante) {
		this.razonSocialRepresentante = razonSocialRepresentante;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public List<Cheque> getCheques() {
		return cheques;
	}

	public void setCheques(List<Cheque> cheques) {
		this.cheques = cheques;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return rut.concat(" --- ").concat(razonSocial);
	}

	@Override
	public boolean equals(Object obj) {
		if(this.rut!=null) {
			return this.rut.equals(((Empresa) obj).getRut());
		}
		return false;
	}

	
}
