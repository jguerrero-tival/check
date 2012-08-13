package cl.tival.check.dao;

import java.util.List;

import cl.tival.check.model.Empresa;
import cl.tival.check.model.Representante;

public interface RepresentanteDao {

	public Representante getRepresentante(String rutRepresentante);

	public List<Representante> getRepresentanteByEmpresa(Empresa empresa, Boolean activoRepresentante);

	public void saveRepresentante(Representante representante);

	public void activateRepresentante(String rutRepresentante);

	public void deactivateRepresentante(String rutRepresentante);

}
