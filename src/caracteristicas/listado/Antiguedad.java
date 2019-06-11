package caracteristicas.listado;

import caracteristicas.Caracteristica;
import utils.UtilsClases;

public class Antiguedad extends Caracteristica{
	private String origen;
	private String marca;
	private String material;
	private int a�osAntiguedad;
	
	
	
	public Antiguedad(String origen, String marca, String material, int a�osAntiguedad) {
		super("Antiguedad");
		this.origen = origen;
		this.marca = marca;
		this.material = material;
		this.a�osAntiguedad = a�osAntiguedad;
	}
	public String getOrigen() {
		return origen;
	}
	public String getMarca() {
		return marca;
	}
	public String getMaterial() {
		return material;
	}
	public int getA�osAntiguedad() {
		return a�osAntiguedad;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return UtilsClases.crearToString(this.getClass(),this);
	}
}
