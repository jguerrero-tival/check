package cl.tival.check.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cl.tival.check.dao.ChequeDao;
import cl.tival.check.model.Cheque;
import cl.tival.check.model.Empresa;

public class ChequeDaoImpl extends HibernateDaoSupport implements ChequeDao {

	@Override
	public Cheque getCheque(String numeroCheque) {
		return (Cheque) getHibernateTemplate().get(Cheque.class, numeroCheque);
	}

	@Override
	public List<Cheque> getCheques(Boolean activoCheque) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Cheque.class);
		criteria.add(Restrictions.eq("activo", activoCheque));
		@SuppressWarnings("unchecked")
		List<Cheque> cheques = getHibernateTemplate().findByCriteria(criteria);
		return cheques;
	}

	@Override
	public List<Cheque> getChequesByEmpresa(Empresa empresa, Boolean activoCheque) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Cheque.class);
		criteria.add(Restrictions.eq("activo", activoCheque));
		criteria.add(Restrictions.eq("empresa", empresa));
		@SuppressWarnings("unchecked")
		List<Cheque> cheques = getHibernateTemplate().findByCriteria(criteria);
		return cheques;
	}

	@Override
	public void saveCheque(Cheque cheque) {
		getHibernateTemplate().save(cheque);
	}

	@Override
	public void activateCheque(String numeroCheque) {
		Cheque cheque = getCheque(numeroCheque);
		if(cheque!=null) {
			cheque.setActivo(Boolean.TRUE);
			saveCheque(cheque);
		}
	}

	@Override
	public void deactivateCheque(String numeroCheque) {
		Cheque cheque = getCheque(numeroCheque);
		if(cheque!=null) {
			cheque.setActivo(Boolean.FALSE);
			saveCheque(cheque);
		}
	}
	
}
