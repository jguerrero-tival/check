package cl.tival.check.model;

import java.io.Serializable;
import java.util.Date;

public class Factura implements Serializable, Comparable<Factura> {

	private static final long serialVersionUID = 1L;
	private Empresa empresa;
	private Long numero;
	private Date fecha;
	private Long monto;
	private String estado;
	private Cheque cheque;
	private Boolean activo;
	private Boolean seleccion;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getMonto() {
		return monto;
	}

	public void setMonto(Long monto) {
		this.monto = monto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Cheque getCheque() {
		return cheque;
	}

	@Override
	public String toString() {
		return "Factura [empresa=" + empresa + ", numero="
				+ numero + ", fecha=" + fecha + ", monto=" + monto
				+ ", estado=" + estado + ", cheque=" + cheque + ", activo="
				+ activo + "]";
	}

	public void setCheque(Cheque cheque) {
		this.cheque = cheque;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Boolean getSeleccion() {
		if(seleccion==null)
			return Boolean.FALSE;
		return seleccion;
	}

	public void setSeleccion(Boolean seleccion) {
		this.seleccion = seleccion;
	}

	@Override
	public int compareTo(Factura o) {
		return o.getNumero().compareTo(this.numero);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Factura))
			return false;
		Factura other = (Factura) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Factura factura = new Factura();
		factura.setActivo(activo);
		factura.setCheque(cheque);
		factura.setEmpresa(empresa);
		factura.setEstado(estado);
		factura.setFecha(fecha);
		return factura;
	}
	
}
