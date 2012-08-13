package cl.tival.check.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cl.tival.check.dao.ParametroDao;
import cl.tival.check.model.Parametro;

public class ParametroDaoImpl extends HibernateDaoSupport implements ParametroDao {

	@Override
	public Parametro getParametro(String claveParametro) {
		return (Parametro) getHibernateTemplate().get(Parametro.class, claveParametro);
	}

	@Override
	public List<Parametro> getParametros(Boolean activoParametro) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Parametro.class);
		criteria.add(Restrictions.eq("activo", activoParametro));
		@SuppressWarnings("unchecked")
		List<Parametro> parametros = getHibernateTemplate().findByCriteria(criteria);
		return parametros;	
	}

	@Override
	public void saveParametro(Parametro parametro) {
		getHibernateTemplate().saveOrUpdate(parametro);
	}

	@Override
	public void activateParametro(String claveParametro) {
		Parametro parametro = getParametro(claveParametro);
		if(parametro!=null) {
			parametro.setActivo(Boolean.TRUE);
			saveParametro(parametro);
		}
	}

	@Override
	public void deactivateParametro(String claveParametro) {
		Parametro parametro = getParametro(claveParametro);
		if(parametro!=null) {
			parametro.setActivo(Boolean.FALSE);
			saveParametro(parametro);
		}
	}
	
}
