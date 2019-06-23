package caracteristicas.listado;

import caracteristicas.Caracteristica;
import utils.UtilsClases;

public class Otro extends Caracteristica{
	private String caracteristicas;
	
	public Otro(String caracteristicas) {
		super("Otro");
		this.caracteristicas = caracteristicas;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return UtilsClases.crearToString(this.getClass(),this);
	}
	public String getCaracteristicas() {
		return caracteristicas;
	}
}
