package cl.tival.check.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.tival.check.app.CheckConstants;
import cl.tival.check.dao.FacturaDao;
import cl.tival.check.model.Cheque;
import cl.tival.check.model.Factura;
import cl.tival.check.service.FacturaService;
import cl.tival.check.table.FacturaTableModel;
import cl.tival.check.ui.FacturaFilter;

public class FacturaServiceImpl implements FacturaService {
	
	final static Logger LOG = LoggerFactory.getLogger(FacturaServiceImpl.class);
	private FacturaDao facturaDao;

	public List<Factura> getFacturasActivas(String rutEmpresa) {
		return facturaDao.getFacturasByEmpresa(rutEmpresa, Boolean.TRUE);
	}
	
	public List<Factura> getFacturasByNumeros(List<Long> numerosFactura) {
		List<Factura> facturas = new ArrayList<Factura>();
		for (int i=0; i<numerosFactura.size(); i++) {
			Factura factura = facturaDao.getFactura(numerosFactura.get(i));
			facturas.add(factura);
		}
		return facturas;
	}
	
	public FacturaTableModel getFacturasActivasTableModel(String rutEmpresa) {
		FacturaTableModel model = new FacturaTableModel();
		List<Factura> facturas = this.getFacturasActivas(rutEmpresa);
		for (int i = 0; i < facturas.size(); i++) {
			model.addObject((Factura) facturas.get(i));
		}
		return model;
	}

	public int addFactura(Factura factura) {
		try {
			Factura facturaTmp = facturaDao.getFactura(factura.getNumero());
			if(facturaTmp!=null) {
				if(facturaTmp.getActivo().equals(Boolean.FALSE)) {
					facturaTmp.setActivo(Boolean.TRUE);
					facturaTmp.setEmpresa(factura.getEmpresa());
					facturaTmp.setNumero(factura.getNumero());
					facturaTmp.setFecha(factura.getFecha());
					facturaTmp.setMonto(factura.getMonto());
					facturaDao.saveFactura(facturaTmp);
					return CheckConstants.AGREGAR_FACTURA_ACTIVADO;
				} else {
					return CheckConstants.AGREGAR_FACTURA_EXISTE;
				}
			} else {
				facturaDao.saveFactura(factura);
				return CheckConstants.AGREGAR_FACTURA_OK;
			}
		} catch (Exception e) {
			System.err.println(e.getStackTrace());
			return CheckConstants.AGREGAR_FACTURA_FAIL;
		}
	}

	public int deleteFactura(Long numeroFactura) {
		LOG.trace("Starting \"deleteFactura\" method.");
		try {
			Factura facturaTmp = facturaDao.getFactura(numeroFactura);
			if(facturaTmp==null) {
				LOG.error("La factura {} no existe. No se puede eliminar.", numeroFactura);
				return CheckConstants.ELIMINAR_FACTURA_NO_EXISTE;
			} else {
				if(!facturaTmp.getActivo()) {
					LOG.error("La factura {} no esta activa. No se puede eliminar.", numeroFactura);
					return CheckConstants.ELIMINAR_FACTURA_DESACTIVADO;
				} else {
					facturaTmp.setActivo(Boolean.FALSE);
					facturaDao.saveFactura(facturaTmp);
					LOG.debug("Se elimino la factura {}.", numeroFactura);
					return CheckConstants.ELIMINAR_FACTURA_OK;
				}
			}
		} catch (Exception e) {
			LOG.error("Error al eliminar factura {}.", numeroFactura);
			return CheckConstants.ELIMINAR_FACTURA_FAIL;
		}
		//LOG.trace("Finishing \"deleteFactura\" method.");
	}

	public int updateFactura(Long numeroFacturaActual, Factura factura) {
		LOG.trace("Starting \"updateFatura\" method.");
		try {
			Factura facturaTmp = facturaDao.getFactura(numeroFacturaActual);
			if(facturaTmp!=null) {
				if(facturaTmp.getActivo().equals(Boolean.TRUE)) {
					facturaTmp.setNumero(factura.getNumero());
					facturaTmp.setFecha(factura.getFecha());
					facturaTmp.setMonto(factura.getMonto());
					facturaDao.saveFactura(facturaTmp);
					if(!factura.getNumero().equals(numeroFacturaActual)) {
						deleteFactura(numeroFacturaActual);
					}
					LOG.debug("Se modifico la factura {}.", factura.getNumero());
					return CheckConstants.MODIFICAR_FACTURA_OK;
				} else {
					LOG.error("La factura {} no esta activa. No se puede modificar.", factura.getNumero());
					return CheckConstants.MODIFICAR_FACTURA_DESACTIVADO;
				}
			} else {
				LOG.error("La factura {} no existe. No se puede modificar.", factura.getNumero());
				return CheckConstants.MODIFICAR_FACTURA_NO_EXISTE;
			}
		} catch (Exception e) {
			LOG.error("Error al modificar factura {}.", factura.getNumero());
			return CheckConstants.MODIFICAR_FACTURA_FAIL;
		}
		//LOG.trace("Finishing \"updateFatura\" method.");
	}
	
	public Factura getFactura(Long numeroFactura) {
		LOG.trace("Starting \"getFactura\" method.");
		Factura factura = facturaDao.getFactura(numeroFactura);
		LOG.trace("Finishing \"getFactura\" method.");
		return factura;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public List<Factura> findFacturas(final FacturaFilter filter) {
		
		List<Factura> filtered = new ArrayList<Factura>();
		List<Factura> facturas = facturaDao.getFacturas(Boolean.TRUE);
		for (Factura factura : facturas) {
			if (checkFilter(factura, filter)) {
				filtered.add(factura);
			}
		}
		return filtered;
	}

	public boolean checkFilter(Factura factura, FacturaFilter filter) {
		boolean numeroOk = true;
		boolean fechaOk = true;
		if (filter.getNumeroContains() != null) {
			String numero = factura.getNumero()!=null ? factura.getNumero().toString() : "";
			numeroOk = numero.contains(filter.getNumeroContains().toString());
		}		
		if (filter.getFechaContains() != null) {
			String fecha = factura.getFecha()!=null ? factura.getFecha().toString() : "";
			fechaOk = fecha.contains(filter.getFechaContains().toString());
		}
		return numeroOk && fechaOk;
	}

	public FacturaDao getFacturaDao() {
		return facturaDao;
	}
	
	public void setFacturaDao(FacturaDao facturaDao) {
		this.facturaDao = facturaDao;
	}

	public void saveFactura(Factura factura) {
		factura.setEstado("Ingresado");
		factura.setActivo(Boolean.TRUE);
		facturaDao.saveFactura(factura);
	}
	
	public void deactivateFactura(Factura factura) {
		facturaDao.deactivateFactura(factura.getNumero());
	}

	public void guardarCheque(List<Long> numerosFactura, Cheque cheque) {
		List<Factura> facturas = getFacturasByNumeros(numerosFactura);
		String fraccion = cheque.getFraccion();
		for (Factura factura : facturas) {
			Set<Cheque> cheques = factura.getCheques();
			for (Cheque chequeFactura : cheques) {
				if(fraccion.equals(chequeFactura.getFraccion())) {
					factura.removeCheque(chequeFactura);
				}
			}
			factura.addCheque(cheque);
			facturaDao.saveFactura(factura);
		}
	}
}

