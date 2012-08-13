package cl.tival.check.dao;

import java.util.List;

import cl.tival.check.model.Parametro;

public interface ParametroDao {

	public Parametro getParametro(String claveParametro);

	public List<Parametro> getParametros(Boolean activoParametro);

	public void saveParametro(Parametro parametro);

	public void activateParametro(String claveParametro);

	public void deactivateParametro(String claveParametro);
	
}
