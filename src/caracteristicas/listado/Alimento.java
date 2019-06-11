package caracteristicas.listado;

import caracteristicas.Caracteristica;
import utils.UtilsClases;

public class Alimento extends Caracteristica{
	private String marca;
	private int unidades;
	private String embase;
	private String sabor;
	
	
	
	public Alimento(String marca, int unidades, String embase, String sabor) {
		super("Alimento");
		this.marca = marca;
		this.unidades = unidades;
		this.embase = embase;
		this.sabor = sabor;
	}
	public String getMarca() {
		return marca;
	}
	public int getUnidades() {
		return unidades;
	}
	public String getEmbase() {
		return embase;
	}
	public String getSabor() {
		return sabor;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return UtilsClases.crearToString(this.getClass(),this);
	}
}
