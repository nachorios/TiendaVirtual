package caracteristicas.listado;

import utils.UtilsClases;

public class Joyeria {
	private String marca;
	private float peso;
	private String estilo;
	private String material;
	
	public Joyeria(String marca, float peso, String estilo, String material) {
		super();
		this.marca = marca;
		this.peso = peso;
		this.estilo = estilo;
		this.material = material;
	}
	public String getMarca() {
		return marca;
	}
	public float getPeso() {
		return peso;
	}
	public String getEstilo() {
		return estilo;
	}
	public String getMaterial() {
		return material;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return UtilsClases.crearToString(this.getClass(),this);
	}
}
