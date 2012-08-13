package cl.tival.check.util;

import java.util.Date;

public class Fecha {

	@SuppressWarnings("deprecation")
	public static Date createDate() {
		Date fecha = new Date();
		fecha.setDate(1);
		return fecha;
	}
	
	@SuppressWarnings("deprecation")
	public static Date createDate(int year, int month){
		Date fecha = new Date(year-1900, month-1, 1);
		return fecha;
	}
	
	@SuppressWarnings("deprecation")
	public static Date createDate(Long year, String month){
		Date fecha = new Date(year.intValue()-1900, mesNumero(month)-1, 1);
		return fecha;
	}
	
	@SuppressWarnings("deprecation")
	public static Date createDate(int year, int month, int date){
		Date fecha = new Date(year-1900, month-1, date);
		return fecha;
	}
	
	@SuppressWarnings("deprecation")
	public static Date createDate(Long year, String month, Long date){
		Date fecha = new Date(year.intValue()-1900, mesNumero(month)-1, date.intValue());
		return fecha;
	}
	
	/* convierte el mes obtenido de Date a un mes real */
	@SuppressWarnings("deprecation")
	public static Long getMonth(Date fecha) {
		return new Long(fecha.getMonth()+1);
	}
	
	/* convierte el mes obtenido de Date a un mes real en palabras*/
	@SuppressWarnings("deprecation")
	public static String getMonthString(Date fecha) {
		return mesPalabra(fecha.getMonth()+1);
	}

	/* convierte el a�o obtenido de Date a un a�o real */
	@SuppressWarnings("deprecation")
	public static Long getYear(Date fecha) {
		return new Long(fecha.getYear()+1900);
	}
	
	@SuppressWarnings("deprecation")
	public static Long getDay(Date fecha) {
		return new Long(fecha.getDate());
	}
	
	public static String mesPalabra(int mes) {
		switch (mes) {
		case  1: return Palabra.mes01; 
		case  2: return Palabra.mes02;
		case  3: return Palabra.mes03;
		case  4: return Palabra.mes04;
		case  5: return Palabra.mes05;
		case  6: return Palabra.mes06;
		case  7: return Palabra.mes07;
		case  8: return Palabra.mes08;
		case  9: return Palabra.mes09;
		case 10: return Palabra.mes10;
		case 11: return Palabra.mes11;
		case 12: return Palabra.mes12;
		default: return "";
		}
	}
	
	public static int mesNumero(String mes) {
		if(mes.equals(Palabra.mes01)) return  1;
		if(mes.equals(Palabra.mes02)) return  2;
		if(mes.equals(Palabra.mes03)) return  3;
		if(mes.equals(Palabra.mes04)) return  4;
		if(mes.equals(Palabra.mes05)) return  5;
		if(mes.equals(Palabra.mes06)) return  6;
		if(mes.equals(Palabra.mes07)) return  7;
		if(mes.equals(Palabra.mes08)) return  8;
		if(mes.equals(Palabra.mes09)) return  9;
		if(mes.equals(Palabra.mes10)) return 10;
		if(mes.equals(Palabra.mes11)) return 11;
		if(mes.equals(Palabra.mes12)) return 12;
		return 0;
	}
	
	@SuppressWarnings("deprecation")
	public static Date getNextDate(Date fecha) {
		Date nextFecha = createDate();
		int year = fecha.getYear();
		int month = fecha.getMonth();
		if(month==11) {
			nextFecha.setYear(year+1);
			nextFecha.setMonth(0);
		} else {
			nextFecha.setYear(year);
			nextFecha.setMonth(month+1);
		}
		return nextFecha;
	}
	
	public static String fechaTexto(Date fecha) {
		return getYear(fecha).toString() + ", " + getMonthString(fecha);
	}

}