package cl.tival.check.util;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

public class Palabra {
	
	/* institucion */
	
	public final static String institucionSin = "SIN INSTITUCION";
	
	public final static String institucionFonasa = "FONASA";

	/* titulo */

	public final static String titulo = "REMUNERACIONES";

	/* descuentos */

	public final static String descuentoPrevision = "PREVISION";

	public final static String descuentoSalud = "SALUD";

	public final static String descuentoAdicionalIsapre = "ADICIONAL ISAPRE";

	public final static String descuentoImpuestoUnico = "IMPUESTO UNICO";

	public final static String descuentoSeguroCesantia = "SEGURO DE CESANTIA";

	public final static String descuentoApv = "APV";
	
	public final static String descuentoAhorroVoluntario = "AHORRO VOLUNTARIO";
	
	public final static String descuentoAnticipo = "ANTICIPO";

	/* imponibles */

	public final static String imponibleSueldoBase = "SUELDO BASE";

	public final static String imponibleHorasExtraordinarias = "HORAS EXTRAORDINARIAS";
	
	public final static String imponibleGratificacion = "GRATIFICACION";

	/* no imponibles */

	public final static String noImponibleColacion = "COLACION";

	public final static String noImponibleMovilizacion = "MOVILIZACION";
	
	public final static String noImponibleAsignacionFamiliar = "ASIGNACION FAMILIAR";
	
	public final static String noImponibleCargaRetroactiva = "CARGA RETROACTIVA";
	
	/* regimen */

	public final static String regimenAntiguo = "ANTIGUO";

	public final static String regimenNuevo = "NUEVO";

	public final static ComboBoxModel getComboBoxModelAfpRegimen() {
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		model.addElement(regimenNuevo);
		model.addElement(regimenAntiguo);
		return model;
	}

	/* moneda */

	public final static String monedaMoneda = "MONEDA";

	public final static String monedaBillete = "BILLETE";

	public final static ComboBoxModel getComboBoxModelMonedaTipo() {
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		model.addElement(monedaBillete);
		model.addElement(monedaMoneda);
		return model;
	}

	/* mes */

	public final static String mes01 = "ENERO";

	public final static String mes02 = "FEBRERO";

	public final static String mes03 = "MARZO";

	public final static String mes04 = "ABRIL";

	public final static String mes05 = "MAYO";

	public final static String mes06 = "JUNIO";

	public final static String mes07 = "JULIO";

	public final static String mes08 = "AGOSTO";

	public final static String mes09 = "SEPTIEMBRE";

	public final static String mes10 = "OCTUBRE";

	public final static String mes11 = "NOVIEMBRE";

	public final static String mes12 = "DICIEMBRE";

	public final static ComboBoxModel getComboBoxModelMeses() {
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		model.addElement(mes01);
		model.addElement(mes02);
		model.addElement(mes03);
		model.addElement(mes04);
		model.addElement(mes05);
		model.addElement(mes06);
		model.addElement(mes07);
		model.addElement(mes08);
		model.addElement(mes09);
		model.addElement(mes10);
		model.addElement(mes11);
		model.addElement(mes12);
		return model;
	}
	
	public final static ComboBoxModel getComboBoxModelDias(Long anno, String mes) {
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		int n = 0;
		if (mes.equals(mes01) || mes.equals(mes03) || mes.equals(mes05) || 
				mes.equals(mes07) || mes.equals(mes08) || mes.equals(mes10) || mes.equals(mes12)) {
			n = 31;
		} else if (mes.equals(mes04) || mes.equals(mes06) || mes.equals(mes09) || mes.equals(mes11)) {
			n = 30;
		} else if (mes.equals(mes02)) {
			if (anno.intValue()%4==0) {
				n = 29;
			} else {
				n = 28;
			}
		}
		for (int i=0; i<n; i++) {
			model.addElement(String.valueOf(i+1));
		}
		return model;
	}

	/* boolean */

	public final static String booleanTrue = "SI";

	public final static String booleanFalse = "NO";

	public static ComboBoxModel getComboBoxModelYesNo() {
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		model.addElement(booleanFalse);
		model.addElement(booleanTrue);
		return model;
	}

	public final static String getBooleanString(Boolean bool) {
		if (bool.booleanValue()) {
			return booleanTrue;
		} else {
			return booleanFalse;
		}
	}

	public final static Boolean setBooleanString(String bool) {
		if (bool.equals(booleanTrue)) {
			return new Boolean(true);
		} else {
			return new Boolean(false);
		}
	}

	/* gratificacion */

	public static String gratificacionSin = "SIN GRATIFICACION";

	public static String gratificacionPorcentaje = "PORCENTAJE";

	public static String gratificacionPorcentajeTope = "PORCENTAJE + TOPE";

	public static ComboBoxModel getComboBoxModelGratificaciones() {
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		model.addElement(gratificacionSin);
		model.addElement(gratificacionPorcentaje);
		model.addElement(gratificacionPorcentajeTope);
		return model;
	}

	/* region */

	public static ComboBoxModel getComboBoxModelRegiones() {
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		model.addElement("01");
		model.addElement("02");
		model.addElement("03");
		model.addElement("04");
		model.addElement("05");
		model.addElement("06");
		model.addElement("07");
		model.addElement("08");
		model.addElement("09");
		model.addElement("10");
		model.addElement("11");
		model.addElement("12");
		model.addElement("RM");
		model.setSelectedItem("05");
		return model;
	}

	/* sexo */
	
	public static String sexoMasculino = "M";
	
	public static String sexoFemenino = "F";
	
	public static ComboBoxModel getComboBoxModelSexo() {
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		model.addElement(sexoMasculino);
		model.addElement(sexoFemenino);
		return model;
	}
	
	/* trato */

	public static String tratoDia = "DIA";
	
	public static String tratoMes = "MES";

	public static ComboBoxModel getComboBoxModelTrato() {
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		model.addElement(tratoDia);
		model.addElement(tratoMes);
		return model;
	}

	/* forma de pago */
	
	public static String formaPagoEfectivo = "EFECTIVO";
	
	public static String formaPagoCheque = "CHEQUE";
	
	public static String formaPagoDeposito = "DEPOSITO";
	
	public static ComboBoxModel getComboBoxModelFormaPago() {
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		model.addElement(formaPagoEfectivo);
		model.addElement(formaPagoCheque);
		model.addElement(formaPagoDeposito);
		return model;
	}
	
	/* tipo de contrato */
	
	public static String tipoContratoIndefinido = "INDEFINIDO";
	
	public static String tipoContratoPlazoFijo = "PLAZO FIJO";

	public static String tipoContratoObra = "POR OBRA";
	
	public static ComboBoxModel getComboBoxModelTipoContrato() {
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		model.addElement(tipoContratoIndefinido);
		model.addElement(tipoContratoPlazoFijo);
		model.addElement(tipoContratoObra);
		return model;
	}
	
	/* tipo de pacto */
	
	public static String tipoPactoPesos = "PESOS";
	
	public static String tipoPactoUf = "U.F.";
	
	public static ComboBoxModel getComboBoxModelTipoPacto() {
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		model.addElement(tipoPactoPesos);
		model.addElement(tipoPactoUf);
		return model;
	}
	
	/* tipo salud */
	
	public static String tipoSaludFonasa = institucionFonasa;
	
	public static String tipoSaludIsapre = "ISAPRE";
	
}