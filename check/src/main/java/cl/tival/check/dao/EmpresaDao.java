package cl.tival.check.dao;

import java.util.List;

import cl.tival.check.model.Empresa;

public interface EmpresaDao {

	public Empresa getEmpresa(String rutEmpresa);

	public List<Empresa> getEmpresas(Boolean activoEmpresa);

	public void saveEmpresa(Empresa empresa);

	public void activateEmpresa(String rutEmpresa);

	public void deactivateEmpresa(String rutEmpresa);

}
