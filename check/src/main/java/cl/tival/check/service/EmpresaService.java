package cl.tival.check.service;

import java.util.List;

import javax.swing.ComboBoxModel;

import cl.tival.check.model.Empresa;

public interface EmpresaService {

	public List<Empresa> getEmpresasActivas();
	
	public ComboBoxModel getComboBoxEmpresasActivas();
	
	public int addEmpresa(Empresa empresa);

	public int delEmpresa(String rutEmpresa);

	public int updateEmpresa(Empresa empresa);

}
