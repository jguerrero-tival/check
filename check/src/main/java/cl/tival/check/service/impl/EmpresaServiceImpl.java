package cl.tival.check.service.impl;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import cl.tival.check.app.CheckConstants;
import cl.tival.check.dao.EmpresaDao;
import cl.tival.check.model.Empresa;
import cl.tival.check.service.EmpresaService;

public class EmpresaServiceImpl implements EmpresaService {

	private EmpresaDao empresaDao;

	@Override
	public List<Empresa> getEmpresasActivas() {
		return empresaDao.getEmpresas(Boolean.TRUE);
	}

	@Override
	public ComboBoxModel getComboBoxEmpresasActivas() {
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		//model.addElement(Palabra.institucionSin);
		List<Empresa> empresas = getEmpresasActivas();
		for (int i = 0; i < empresas.size(); i++) {
			model.addElement(empresas.get(i));
		}
		return model;
	}

	public int addEmpresa(Empresa empresa) {
		try {
			Empresa empresaTmp = empresaDao.getEmpresa(empresa.getRut());
			if(empresaTmp!=null) {
				if(empresaTmp.getActivo().equals(Boolean.FALSE)) {
					empresaTmp.setActivo(Boolean.TRUE);
					empresaTmp.setRazonSocial(empresa.getRazonSocial());
					empresaTmp.setRutRepresentante(empresa.getRutRepresentante());
					empresaTmp.setRazonSocialRepresentante(empresa.getRazonSocialRepresentante());
					empresaDao.saveEmpresa(empresaTmp);
					return CheckConstants.AGREGAR_EMPRESA_ACTIVADO;
				} else {
					return CheckConstants.AGREGAR_EMPRESA_EXISTE;
				}
			} else {
				empresaDao.saveEmpresa(empresa);
				return CheckConstants.AGREGAR_EMPRESA_OK;
			}
		} catch (Exception e) {
			System.err.println(e.getStackTrace());
			return CheckConstants.AGREGAR_EMPRESA_FAIL;
		}
	}

	public int delEmpresa(String rutEmpresa) {
		try {
			Empresa empresaTmp = empresaDao.getEmpresa(rutEmpresa);
			if(empresaTmp==null) {
				return CheckConstants.ELIMINAR_EMPRESA_NO_EXISTE;
			} else {
				if(!empresaTmp.getActivo()) {
					return CheckConstants.ELIMINAR_EMPRESA_DESACTIVADO;
				} else {
					empresaTmp.setActivo(Boolean.FALSE);
					empresaDao.saveEmpresa(empresaTmp);
					return CheckConstants.ELIMINAR_EMPRESA_OK;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getStackTrace());
			return CheckConstants.ELIMINAR_EMPRESA_FAIL;
		}
	}

	public int updateEmpresa(Empresa empresa) {
		try {
			Empresa empresaTmp = empresaDao.getEmpresa(empresa.getRut());
			if(empresaTmp!=null) {
				if(empresaTmp.getActivo().equals(Boolean.TRUE)) {
					empresaTmp.setRazonSocial(empresa.getRazonSocial());
					empresaTmp.setRutRepresentante(empresa.getRutRepresentante());
					empresaTmp.setRazonSocialRepresentante(empresa.getRazonSocialRepresentante());
					empresaDao.saveEmpresa(empresaTmp);
					return CheckConstants.MODIFICAR_EMPRESA_OK;
				} else {
					return CheckConstants.MODIFICAR_EMPRESA_DESACTIVADO;
				}
			} else {
				return CheckConstants.MODIFICAR_EMPRESA_NO_EXISTE;
			}
		} catch (Exception e) {
			System.err.println(e.getStackTrace());
			return CheckConstants.MODIFICAR_EMPRESA_FAIL;
		}
	}
	
	public void setEmpresaDao(EmpresaDao empresaDao) {
		this.empresaDao = empresaDao;
	}
	
}
