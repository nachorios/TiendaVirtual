package caracteristicas.listado;

import caracteristicas.Caracteristica;
import utils.UtilsClases;

public class Arte extends Caracteristica{
	private String marca;
	private String material;
	private String modelo;
	private int ancho;
	private int alto;
	
	public Arte(String marca, String material, String modelo, int ancho, int alto) {
		super("Arte");
		this.marca = marca;
		this.material = material;
		this.modelo = modelo;
		this.ancho = ancho;
		this.alto = alto;
	}
	public String getMarca() {
		return marca;
	}
	public String getMaterial() {
		return material;
	}
	public String getModelo() {
		return modelo;
	}
	public int getAncho() {
		return ancho;
	}
	public int getAlto() {
		return alto;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return UtilsClases.crearToString(this.getClass(),this);
	}
}
