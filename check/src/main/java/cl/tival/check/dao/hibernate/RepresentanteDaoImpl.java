package cl.tival.check.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cl.tival.check.dao.RepresentanteDao;
import cl.tival.check.model.Empresa;
import cl.tival.check.model.Representante;

public class RepresentanteDaoImpl extends HibernateDaoSupport implements RepresentanteDao {

	@Override
	public Representante getRepresentante(String rutRepresentante) {
		return (Representante) getHibernateTemplate().get(Representante.class, rutRepresentante);
	}

	@Override
	public List<Representante> getRepresentanteByEmpresa(Empresa empresa, Boolean activoRepresentante) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Representante.class);
		criteria.add(Restrictions.eq("activo", activoRepresentante));
		criteria.add(Restrictions.eq("empresa", empresa));
		@SuppressWarnings("unchecked")
		List<Representante> representantes = getHibernateTemplate().findByCriteria(criteria);
		return representantes;
	}

	@Override
	public void saveRepresentante(Representante representante) {
		getHibernateTemplate().save(representante);
	}

	@Override
	public void activateRepresentante(String rutRepresentante) {
		Representante representante = getRepresentante(rutRepresentante);
		if(representante!=null) {
			representante.setActivo(Boolean.TRUE);
			saveRepresentante(representante);
		}
	}

	@Override
	public void deactivateRepresentante(String rutRepresentante) {
		Representante representante = getRepresentante(rutRepresentante);
		if(representante!=null) {
			representante.setActivo(Boolean.FALSE);
			saveRepresentante(representante);
		}
	}
	
}
