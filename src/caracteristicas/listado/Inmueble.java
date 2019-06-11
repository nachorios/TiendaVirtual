package caracteristicas.listado;

import caracteristicas.Caracteristica;
import utils.UtilsClases;

public class Inmueble extends Caracteristica{
	
	private int superficie;
	private int ambientes;
	private int baños;
	private int dormitorios;
	private int cocheras;
	
	
	
	public Inmueble(int superficie, int ambientes, int baños, int dormitorios, int cocheras) {
		super("Inmueble");
		this.superficie = superficie;
		this.ambientes = ambientes;
		this.baños = baños;
		this.dormitorios = dormitorios;
		this.cocheras = cocheras;
	}



	public int getSuperficie() {
		return superficie;
	}



	public int getAmbientes() {
		return ambientes;
	}



	public int getBaños() {
		return baños;
	}



	public int getDormitorios() {
		return dormitorios;
	}



	public int getCocheras() {
		return cocheras;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return UtilsClases.crearToString(this.getClass(),this);
	}
}
