package co.com.accenture.service.oferta.utils;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class fechaactual {
	
	
	public Date Obtenerfecha() {
		Date fecha = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
		String fechaTexto = formatter.format(fecha);
		
		try {
			Date date = formatter.parse(fechaTexto);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fecha;
	}
	
	public Date ObtenerFechaBD(String fecha) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	try {
    		
			Date date = formatter.parse(fecha);
		//	System.out.println(date);
		//	System.out.println (date.getDay());
		//	System.out.println (date.getMonth());
		//	System.out.println(date.getYear());
			
			
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	
	}
}
