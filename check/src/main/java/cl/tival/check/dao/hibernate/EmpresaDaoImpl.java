package cl.tival.check.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cl.tival.check.dao.EmpresaDao;
import cl.tival.check.model.Empresa;

public class EmpresaDaoImpl extends HibernateDaoSupport implements EmpresaDao {

	@Override
	public Empresa getEmpresa(String rutEmpresa) {
		return (Empresa) getHibernateTemplate().get(Empresa.class, rutEmpresa);
	}

	@Override
	public List<Empresa> getEmpresas(Boolean activoEmpresa) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Empresa.class);
		criteria.add(Restrictions.eq("activo", activoEmpresa));
		criteria.addOrder(Order.asc("rut"));
		@SuppressWarnings("unchecked")
		List<Empresa> empresas = getHibernateTemplate().findByCriteria(criteria);
		return empresas;
	}

	@Override
	public void saveEmpresa(Empresa empresa) {
		getHibernateTemplate().saveOrUpdate(empresa);
	}

	@Override
	public void activateEmpresa(String rutEmpresa) {
		Empresa empresa = getEmpresa(rutEmpresa);
		if(empresa!=null) {
			empresa.setActivo(Boolean.TRUE);
			saveEmpresa(empresa);
		}
	}

	@Override
	public void deactivateEmpresa(String rutEmpresa) {
		Empresa empresa = getEmpresa(rutEmpresa);
		if(empresa!=null) {
			empresa.setActivo(Boolean.FALSE);
			saveEmpresa(empresa);
		}
	}
}
