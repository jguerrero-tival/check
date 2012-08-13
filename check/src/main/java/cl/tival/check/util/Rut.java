package cl.tival.check.util;

public class Rut {
	
	public static boolean verificaRut(String rut){
		int p = 2;//hasta 7
		String numero = rut.substring(0, rut.length()-2);
		String dv = rut.substring(rut.length()-1);
		System.out.println(numero+"-"+dv);
		String dvv;
		int total = 0;
		for(int i=numero.length()-1;i>=0;i--) {
			String valor = numero.substring(i, i+1);
			System.out.println(valor);
			if(!valor.equals(".")){
				total = total + p*Integer.valueOf(valor).intValue();
				if(p==7) {
					p=2;
				} else {
					p++;
				}
			}
		}
		int resto = total%11;
		int dv_valido = 11-resto;
		
		if(dv_valido==11) {
			dvv = "0";
		} else if (dv_valido == 10) {
			dvv = "K";
		} else {
			dvv = String.valueOf(dv_valido);
		}
		return dv.equals(dvv);
	}
}

