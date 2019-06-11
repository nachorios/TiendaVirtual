package caracteristicas.listado;

import caracteristicas.Caracteristica;
import utils.UtilsClases;

public class Libro extends Caracteristica{
	private String saga;
	private String idioma;
	private String autor;
	private String editorial;
	private String genero;
	private String formato;
	
	public Libro(String saga, String idioma, String autor, String editorial, String genero, String formato) {
		super("Libro");
		this.saga = saga;
		this.idioma = idioma;
		this.autor = autor;
		this.editorial = editorial;
		this.genero = genero;
		this.formato = formato;
	}
	public String getSaga() {
		return saga;
	}
	public String getIdioma() {
		return idioma;
	}
	public String getAutor() {
		return autor;
	}
	public String getEditorial() {
		return editorial;
	}
	public String getGenero() {
		return genero;
	}
	public String getFormato() {
		return formato;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return UtilsClases.crearToString(this.getClass(),this);
	}
}
