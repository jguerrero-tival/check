package cl.tival.check.util;

import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.MaskFormatter;

public class Format {

	public static void cadenaUpperCase(KeyEvent e) {
		String s = String.valueOf(e.getKeyChar());
		s = s.toUpperCase();
		e.setKeyChar(s.charAt(0));
	}

	public static String cadenaLong(String cadena) {
		return stringNumberToLong(cadena);
	}

	public static String cadenaLong(KeyEvent e, String cadena) {
		String respuesta;
		if (onlyNumbers(e)) {
			respuesta = stringNumberToLong(cadena.concat(String.valueOf(e
					.getKeyChar())));
			e.setKeyChar((char) KeyEvent.VK_CLEAR);
		} else {
			respuesta = stringNumberToLong(cadena);
		}
		return respuesta;
	}

	public static String cadenaDouble(KeyEvent e, String cadena) {
		String respuesta;
		if (onlyNumbers(e)) {
			respuesta = stringNumberToDoubleDotted(cadena.concat(String
					.valueOf(e.getKeyChar())));
			e.setKeyChar((char) KeyEvent.VK_CLEAR);
		} else {
			respuesta = stringNumberToDoubleDotted(cadena);
		}
		return respuesta;
	}

	public static boolean onlyNumbers(KeyEvent e) {
		boolean respuesta;
		int k = e.getKeyChar();
		if ((k > 47 && k < 58)/* ||(k==8||k==KeyEvent.VK_PERIOD) */) {
			// respuesta = String.valueOf(e.getKeyChar());
			respuesta = true;
		} else {
			// respuesta = "";
			respuesta = false;
			e.setKeyChar((char) KeyEvent.VK_CLEAR);
		}
		// e.setKeyChar((char)KeyEvent.VK_CLEAR);
		return respuesta;
	}

	private static void onlyNumbersAndDot(KeyEvent e, String cadena) {
		int k = e.getKeyChar();
		if ((k > 47 && k < 58)
				|| ((k == 8 || k == KeyEvent.VK_PERIOD) && !cadena
						.contains("."))) {
		} else {
			e.setKeyChar((char) KeyEvent.VK_CLEAR);
		}
	}

	public static String stringNumberToLong(String number) {
		String newNumber = new String();
		int contador = 0;
		for (int i = number.length() - 1; i >= 0; i--) {
			if (!number.substring(i, i + 1).equals(".")) {
				if (contador < 3) {
					newNumber = number.substring(i, i + 1).concat(newNumber);
				} else {
					newNumber = number.substring(i, i + 1).concat(".")
							.concat(newNumber);
					contador = 0;
				}
				contador++;
			}
		}
		return newNumber;
	}

	/*
	 * se debe ingresar un string de la forma 123123123.34, donde el punto es el
	 * separador decimal
	 */
	public static String stringNumberToDouble(String number) {
		String newNumber = new String();
		int contador = 0;
		for (int i = number.length() - 1; i >= 0; i--) {
			if (!number.substring(i, i + 1).equals(".")) {
				if (contador < 3) {
					newNumber = number.substring(i, i + 1).concat(newNumber);
				} else {
					newNumber = number.substring(i, i + 1).concat(".")
							.concat(newNumber);
					contador = 0;
				}
				contador++;
			} else {
				newNumber = ",".concat(newNumber);
				contador = 0;
			}
		}
		return newNumber;
	}

	/*
	 * se debe ingresar un string de la forma 123.123.123,34, donde el punto es
	 * el separador de miles, y la coma es el separador decimal
	 */
	public static String stringNumberToDoubleDotted(String number) {
		String newNumber = new String();
		// number = removeDot(number);
		int contador = 0;
		for (int i = number.length() - 1; i >= 0; i--) {
			if (!number.substring(i, i + 1).equals(".")
					&& !number.substring(i, i + 1).equals(",")) {
				if (contador < 3) {
					newNumber = number.substring(i, i + 1).concat(newNumber);
				} else {
					newNumber = number.substring(i, i + 1).concat(".")
							.concat(newNumber);
					contador = 0;
				}
				contador++;
			} else if (number.substring(i, i + 1).equals(",")) {
				newNumber = ",".concat(newNumber);
				contador = 0;
			}
		}
		return newNumber;
	}

	public static String removeDot(String cadena) {
		System.out.println("CADENA ORIGINAL: " + cadena);
		String newCadena = new String();
		for (int i = 0; i < cadena.length(); i++) {
			if (!cadena.substring(i, i + 1).equals(".")) {
				newCadena = newCadena.concat(cadena.substring(i, i + 1));
			}
		}
		System.out.println("CADENA NUEVA: " + newCadena);
		return newCadena;
	}

	public static void maxLength(int max, String cadena, KeyEvent e) {
		if (!(cadena.length() < max)) {
			e.setKeyChar((char) KeyEvent.VK_CLEAR);
		}
	}

	public static void formatLong(KeyEvent e) {
		onlyNumbers(e);
	}

	public static void formatDouble(KeyEvent e, String cadena) {
		onlyNumbersAndDot(e, cadena);
	}

	public static void ignoreKey(KeyEvent e) {
		e.setKeyChar((char) KeyEvent.VK_CLEAR);
	}

	public static MaskFormatter getMaskRut() {
		MaskFormatter maskRut = null;
		try {
			maskRut = new MaskFormatter("##.###.###-A");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		maskRut.setPlaceholderCharacter('_');
		maskRut.setValidCharacters("0123456789K");
		return maskRut;
	}
	
	public static MaskFormatter getMaskDate() {
		MaskFormatter maskDate = null;
		try {
			maskDate = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		maskDate.setPlaceholderCharacter('_');
		maskDate.setValidCharacters("0123456789");
		return maskDate;
	}
	
	public static Date stringToDate(String fechaString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return (Date) sdf.parseObject(fechaString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String dateToString(Date fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(fecha);
	}

}
