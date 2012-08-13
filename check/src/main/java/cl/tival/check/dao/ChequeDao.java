package cl.tival.check.dao;

import java.util.List;

import cl.tival.check.model.Cheque;
import cl.tival.check.model.Empresa;

public interface ChequeDao {

	public Cheque getCheque(String numeroCheque);

	public List<Cheque> getCheques(Boolean activoCheque);

	public List<Cheque> getChequesByEmpresa(Empresa empresa, Boolean activoCheque);

	public void saveCheque(Cheque cheque);

	public void activateCheque(String numeroCheque);

	public void deactivateCheque(String numeroCheque);

}
