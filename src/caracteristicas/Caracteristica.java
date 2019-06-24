package caracteristicas;

import utils.UtilsClases;
/**
 * Esta clase abstracta es utilizada para poder almacenar de manera general
 * las caracteristicas particulares de cada categoria.
 * @version 1.0
 *
 */
public abstract class Caracteristica {
	private String nombre;
	
	public Caracteristica(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
	
	//public static crearCaracteristica
	@Override
	public String toString() {
		return UtilsClases.crearToString(this.getClass(),this);
	}
}
