package cl.tival.check.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cl.tival.check.app.CheckConstants;
import cl.tival.check.dao.FacturaDao;
import cl.tival.check.model.Factura;

public class FacturaDaoImpl extends HibernateDaoSupport implements FacturaDao {

	@Override
	public Factura getFactura(Long numeroFactura) {
		return (Factura) getHibernateTemplate().get(Factura.class, numeroFactura);
	}

	@Override
	public List<Factura> getFacturas(Boolean activoFactura) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Factura.class);
		criteria.add(Restrictions.eq("activo", activoFactura));
		criteria.add(Restrictions.eq("estado", CheckConstants.ESTADO_FACTURA_INGRESADA));
		criteria.addOrder(Order.desc("fecha"));
		@SuppressWarnings("unchecked")
		List<Factura> facturas = getHibernateTemplate().findByCriteria(criteria);
		return facturas;
	}

	@Override
	public List<Factura> getFacturasByEmpresa(String rutEmpresa, Boolean activoFactura) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Factura.class);
		criteria.createAlias("empresa", "empresa");
		criteria.add(Restrictions.eq("activo", activoFactura));
		criteria.add(Restrictions.eq("estado", CheckConstants.ESTADO_FACTURA_INGRESADA));
		criteria.add(Restrictions.eq("empresa.rut", rutEmpresa));
		criteria.addOrder(Order.desc("fecha"));
		@SuppressWarnings("unchecked")
		List<Factura> facturas = getHibernateTemplate().findByCriteria(criteria);
		return facturas;
	}

	@Override
	public void saveFactura(Factura factura) {
		getHibernateTemplate().saveOrUpdate(factura);
	}

	@Override
	public void activateFactura(Long idFactura) {
		Factura factura = getFactura(idFactura);
		if(factura!=null) {
			factura.setActivo(Boolean.TRUE);
			saveFactura(factura);
		}
	}
	
	@Override
	public void deactivateFactura(Long idFactura) {
		Factura factura = getFactura(idFactura);
		if(factura!=null) {
			factura.setActivo(Boolean.FALSE);
			saveFactura(factura);
		}
	}

}
