package caracteristicas.listado;

import caracteristicas.Caracteristica;
import utils.UtilsClases;
/**
 * 
 * @author usuario
 *
 */
public class Vehiculo extends Caracteristica{
	
	private int modelo;
	private int kilometros;
	private int cantPuertas;
	private String color;
	
	/**
	 * @param modelo
	 * @param kilometros
	 * @param cantPuertas
	 * @param color
	 */
	public Vehiculo(int modelo, int kilometros, int cantPuertas, String color) {
		super("Vehiculo");
		this.modelo = modelo;
		this.kilometros = kilometros;
		this.cantPuertas = cantPuertas;
		this.color = color;
	}
	
	public int getModelo() {
		return modelo;
	}
	public void setModelo(int modelo) {
		this.modelo = modelo;
	}
	public int getKilometros() {
		return kilometros;
	}
	public void setKilometros(int kilometros) {
		this.kilometros = kilometros;
	}
	public int getCantPuertas() {
		return cantPuertas;
	}
	public void setCantPuertas(int cantPuertas) {
		this.cantPuertas = cantPuertas;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return UtilsClases.crearToString(this.getClass(),this);
	}
	
}
