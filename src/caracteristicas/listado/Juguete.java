package caracteristicas.listado;

import caracteristicas.Caracteristica;
import utils.UtilsClases;

public class Juguete extends Caracteristica{
	private String marca;
	private int edadMinima;
	private int edadMaxima;
	private String modelo;
	
	
	public Juguete(String marca, int edadMinima, int edadMaxima, String modelo) {
		super("Juguete");
		this.marca = marca;
		this.edadMinima = edadMinima;
		this.edadMaxima = edadMaxima;
		this.modelo = modelo;
	}
	public String getMarca() {
		return marca;
	}
	public int getEdadMinima() {
		return edadMinima;
	}
	public int getEdadMaxima() {
		return edadMaxima;
	}
	public String getModelo() {
		return modelo;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return UtilsClases.crearToString(this.getClass(),this);
	}
}
