package caracteristicas.listado;

import utils.UtilsClases;

public class Otro {
	private String caracteristicas;
	
	public Otro(String caracteristicas) {
		super();
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
