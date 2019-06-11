package caracteristicas.listado;

import caracteristicas.Caracteristica;
import utils.UtilsClases;

public class Ropa extends Caracteristica{
	private String marca;
	private String modelo;
	private String genero;
	private String material;
	private String temporada;
	
	
	public Ropa(String marca, String modelo, String genero, String material, String temporada) {
		super("Ropa");
		this.marca = marca;
		this.modelo = modelo;
		this.genero = genero;
		this.material = material;
		this.temporada = temporada;
	}
	public String getMarca() {
		return marca;
	}
	public String getModelo() {
		return modelo;
	}
	public String getGenero() {
		return genero;
	}
	public String getMaterial() {
		return material;
	}
	public String getTemporada() {
		return temporada;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return UtilsClases.crearToString(this.getClass(),this);
	}
}
