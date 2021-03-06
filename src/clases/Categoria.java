package clases;

import org.json.JSONObject;
import interfaces.IJsonObj;

import java.io.Serializable;

/**
	 * Esta clase almacena el tipo de categoria y el nombre de la misma.
	 * @version 1.0
	 *
	 */
	public class Categoria implements IJsonObj, Serializable {
	/**
	 * Este enum representa las distintas categorias que puede poseer un producto.
	 * @version 1.0
	 *
	 */
	public enum CategoriaType{ACCESORIO,ALIMENTO,ANTIGUEDAD,ARTE,INMUEBLE,JOYERIA,JUGUETE,LIBRO,MUSICA,ROPA,TECNOLOGIA,VEHICULO,OTRO}
	
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
	
	public CategoriaType getTipo() {
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
