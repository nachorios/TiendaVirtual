package utils;

import java.lang.reflect.InvocationTargetException;
/**
 * Esta clase almacena todos los metodos que nos facilitan la creacion de caracteristicas de forma dinamica
 * sin la necesidad de declarar cada getter y setter.
 * @version 1.0
 * 
 */
public class UtilsClases {
	public static String crearToString(Class<?> clase, Object obj) {
		StringBuilder sb = new StringBuilder();
		try {
			String nombre;
			sb.append("{");
			for (int i = 0; i < clase.getDeclaredMethods().length; i++) {
				nombre = clase.getDeclaredMethods()[i].getName();
				if (nombre.contains("get")) {
					try {
						sb.append("\""+nombre.replaceAll("get", "")+"\": \""+clase.getDeclaredMethods()[i].invoke(obj, new Object[]{})+"\" - ");
					} catch (IllegalAccessException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			sb.append("}");
		} catch (IllegalArgumentException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sb.toString();
	}

    public static boolean objectIsInteger(Class<?> obj) {
    	boolean flag = false;
    	if (obj.getTypeName().equals(int.class.getTypeName()))
    		flag = true;
    	return flag;
    }
    
    public static boolean objectIsFloat(Class<?> obj) {
    	boolean flag = false;
    	if (obj.getTypeName().equals(float.class.getTypeName()))
    		flag = true;
    	return flag;
    }
    
    public static boolean objectIsString(Class<?> obj) {
    	boolean flag = false;
    	if (obj.getTypeName().equals(String.class.getTypeName()))
    		flag = true;
    	return flag;
    }
}
