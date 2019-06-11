package caracteristicas.listado;

import caracteristicas.Caracteristica;
import utils.UtilsClases;

public class Musica extends Caracteristica{
	private String nombreArtistas;
	private String formato;
	private int a�oLanzamiento;
	private String nombreAlbum;
	private int cantCanciones;
	private String origen;
	private String genero;
	
	public Musica(String nombreArtistas, String formato, int a�oLanzamiento, String nombreAlbum, int cantCanciones,
			String origen, String genero) {
		super("Musica");
		this.nombreArtistas = nombreArtistas;
		this.formato = formato;
		this.a�oLanzamiento = a�oLanzamiento;
		this.nombreAlbum = nombreAlbum;
		this.cantCanciones = cantCanciones;
		this.origen = origen;
		this.genero = genero;
	}
	public String getNombreArtistas() {
		return nombreArtistas;
	}
	public String getFormato() {
		return formato;
	}
	public int getA�oLanzamiento() {
		return a�oLanzamiento;
	}
	public String getNombreAlbum() {
		return nombreAlbum;
	}
	public int getCantCanciones() {
		return cantCanciones;
	}
	public String getOrigen() {
		return origen;
	}
	public String getGenero() {
		return genero;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return UtilsClases.crearToString(this.getClass(),this);
	}
}
