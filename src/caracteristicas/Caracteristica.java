package caracteristicas;

import utils.UtilsClases;

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
