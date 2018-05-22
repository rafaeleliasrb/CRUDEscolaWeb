package br.com.escola.model.utils;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class Utils {
	
	@SuppressWarnings("rawtypes")
	public static void checkValues(Enumeration keys, HttpServletRequest request) throws Exception {
		while (keys.hasMoreElements()){
			String key = (String) keys.nextElement();
			String value = request.getParameter(key);
//			System.out.println(key + ": " + value);
			
			if(key.equals("id")) {
				try {
					Integer.parseInt(value);
				} catch (NumberFormatException e) {
					throw new Exception("ID Inválido!");
				}
			} else if(key.equals("idade")) {
				try {
					Integer.parseInt(value);
				} catch (NumberFormatException e) {
					throw new Exception("Idade inválida!");
				}
			} else if(key.equals("nota")) {
				try {
					float nota = Float.parseFloat(value);
					
					if(nota < 0 && nota > 10) {
						throw new Exception("Nota inválida!");						
					}
					
				} catch (NumberFormatException e) {
					throw new Exception("Nota inválida!");
				}
			}
		}
	}

}
