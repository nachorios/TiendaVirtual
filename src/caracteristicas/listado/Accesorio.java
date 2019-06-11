package caracteristicas.listado;

import caracteristicas.Caracteristica;
import utils.UtilsClases;

public class Accesorio extends Caracteristica{
	private String marca;
	private String color;
	private String material;
	private String origen;
	
	public Accesorio(String marca, String color, String material, String origen) {
		super("Accesorio");
		this.marca = marca;
		this.color = color;
		this.material = material;
		this.origen = origen;
	}
	public String getMarca() {
		return marca;
	}
	public String getColor() {
		return color;
	}
	public String getMaterial() {
		return material;
	}
	public String getOrigen() {
		return origen;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return UtilsClases.crearToString(this.getClass(),this);
	}
}
