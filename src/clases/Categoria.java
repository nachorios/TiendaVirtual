package clases;

import org.json.JSONObject;
import interfaces.IJsonObj;

enum CategoriaType{ACCESORIO,ALIMENTO,ANTIGUEDAD,ARTE,INMUEBLE,JOYERIA,JUGUETE,LIBRO,MUSICA,OTRO,ROPA,TECNOLOGIA,VEHICULO}

	public class Categoria implements IJsonObj{
	private CategoriaType tipo;
	private String nombre;
	
	public Categoria() {
		this.tipo = null;
		nombre = "";
	}
	
	public Categoria(Categoria cat) {
		this.tipo = cat.getTipo();
		this.nombre = cat.getNombre();
	}
	
	public Categoria(CategoriaType tipo) {
		this.tipo = tipo;
		this.nombre = tipo.name();
	}
	
	private CategoriaType getTipo() {
		return tipo;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
		public String toString() {
			return getNombre().toLowerCase();
		}
	
	@Override
		public boolean equals(Object obj) {
			boolean flag = false;
			if (obj != null && obj instanceof CategoriaType) {
				if (obj.equals(getTipo())) {
					flag = true;
				}
			}
			return flag;
		}

	@Override
	public JSONObject objetoAJSON() {
		JSONObject jsonCategoria = new JSONObject();
		
		return jsonCategoria;
	}

}
