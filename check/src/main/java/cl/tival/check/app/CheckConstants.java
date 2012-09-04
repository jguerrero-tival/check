package cl.tival.check.app;

public class CheckConstants {
	
	/* Respuestas Empresa */
	public static final int AGREGAR_EMPRESA_FAIL = -1;
	public static final int AGREGAR_EMPRESA_OK = 0;
	public static final int AGREGAR_EMPRESA_EXISTE = 1;
	public static final int AGREGAR_EMPRESA_ACTIVADO = 2;

	public static final int ELIMINAR_EMPRESA_FAIL = -1;
	public static final int ELIMINAR_EMPRESA_OK = 0;
	public static final int ELIMINAR_EMPRESA_DESACTIVADO = 1;
	public static final int ELIMINAR_EMPRESA_NO_EXISTE = 2;

	public static final int MODIFICAR_EMPRESA_FAIL = -1;
	public static final int MODIFICAR_EMPRESA_OK = 0;
	public static final int MODIFICAR_EMPRESA_DESACTIVADO = 1;
	public static final int MODIFICAR_EMPRESA_NO_EXISTE = 2;

	/* Respuestas Factura*/
	public static final int AGREGAR_FACTURA_FAIL = -1;
	public static final int AGREGAR_FACTURA_OK = 0;
	public static final int AGREGAR_FACTURA_EXISTE = 1;
	public static final int AGREGAR_FACTURA_ACTIVADO = 2;

	public static final int ELIMINAR_FACTURA_FAIL = -1;
	public static final int ELIMINAR_FACTURA_OK = 0;
	public static final int ELIMINAR_FACTURA_DESACTIVADO = 1;
	public static final int ELIMINAR_FACTURA_NO_EXISTE = 2;

	public static final int MODIFICAR_FACTURA_FAIL = -1;
	public static final int MODIFICAR_FACTURA_OK = 0;
	public static final int MODIFICAR_FACTURA_DESACTIVADO = 1;
	public static final int MODIFICAR_FACTURA_NO_EXISTE = 2;

	public static final int BUSCAR_CHEQUES_FAIL = -1;
	public static final int BUSCAR_CHEQUES_OK = 0;
	public static final int BUSCAR_CHEQUES_NO_EXISTE = 1;

	/* Estados Factura */
	public static final String ESTADO_FACTURA_INGRESADA = "INGRESADA";
	public static final String ESTADO_FACTURA_IMPRESA = "IMPRESA";
	public static final String ESTADO_FACTURA_ANULADA = "ANULADA";

}
