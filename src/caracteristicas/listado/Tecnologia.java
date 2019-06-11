package caracteristicas.listado;

import caracteristicas.Caracteristica;
import utils.UtilsClases;

public class Tecnologia extends Caracteristica{
	private String marca;
	private String modelo;
	private String linea;
	
	public Tecnologia(String marca, String modelo, String linea) {
		super("Tecnologia");
		this.marca = marca;
		this.modelo = modelo;
		this.linea = linea;
	}
	public String getMarca() {
		return marca;
	}
	public String getModelo() {
		return modelo;
	}
	public String getLinea() {
		return linea;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return UtilsClases.crearToString(this.getClass(),this);
	}
}
