package clases;
import java.util.ArrayList;

import org.json.JSONObject;

import caracteristicas.Caracteristica;
import interfaces.IJsonObj;


/**
 * Esta clase almacena toda la informacion de un producto, Su ID
 * es unico e irrepetible.
 * @author usuario
 * @version 1.0
 */
public class Producto implements IJsonObj{
	private int ID;
	private String nombre;
	private String vendedor;
	private float precio;
	private int cantidad;
	private boolean enVenta;
	private String descripcion;
	private Caracteristica caracteristicas;
	private Categoria categorias;
	
	@Override
	public JSONObject objetoAJSON() {
		JSONObject jsonProducto = new JSONObject();
		jsonProducto.put("id", getID());
		jsonProducto.put("nombre", getNombre());
		jsonProducto.put("vendedor", getVendedor());
		jsonProducto.put("precio", getPrecio());
		jsonProducto.put("enVenta", isEnVenta());
		jsonProducto.put("descripcion", getDescripcion());
		jsonProducto.put("caracteristicas", getCaracteristicas());
		jsonProducto.put("categoria", getCategoria().objetoAJSON());
		
		return jsonProducto;
	}
	
	
	/****************************/
	
	/**
	 * Compara si el ID del objeto enviado por parametros
	 * es igual que el objeto actual.
	 * @param obj : Cualquier objeto (Debe ser tipo producto para lograr una correcta comparacion).
	 * @return Verdadero o falseo
	 */
	@Override
	public boolean equals(Object obj) {
		boolean flag = false;
		if (obj != null && obj instanceof Producto) {
			if(((Producto)obj).getID() == getID()) {
				flag = true;
			}
		}
		return flag;
	}
	
	@Override
	public String toString() {
		return 	"\nNombre: "+getNombre()
				+"\nID: "+getID()
				+"\nVendedor: "+getVendedor()
				+"\nPrecio: "+getPrecio()
				+"\nCantidad: "+getCantidad()
				+"\nEn venta: "+isEnVenta()
				+"\nDescripcion: "+getDescripcion()
				+"\nCaracteristicas: "+getCaracteristicas()
				+"\nCategoria: "+getCategoria();
	}
	/**
	 * Inicializa todos los atributos del objeto, y coloca su ID irrepetible.
	 */
	public Producto() {
		super();
		ID = getIDnuevoProducto();
		nombre = "";
		vendedor = "";
		precio = 0;
		cantidad = 0;
		enVenta = false;
		descripcion = "";
		categorias = new Categoria();
		caracteristicas = null;//TODO CORREGIR
	}
	/**
	 * inicializa las variables del nuevo producto copiando los elementos del
	 * objeto enviado por parametros(El ID no se copia, sino crea uno nuevo).
	 * @param p : Producto a copiar.
	 */
	public Producto(Producto p) {
		super();
		ID = getIDnuevoProducto();
		setNombre(p.getNombre());
		setCantidad(p.getCantidad());
		setEnVenta(p.isEnVenta());
		setCaracteristicas(p.getCaracteristicas());
		setCategoria(p.getCategoria());
		setDescripcion(p.getDescripcion());
		setPrecio(p.getPrecio());
		setVendedor(p.getVendedor());
	}
	
	/**
	 * Crea un producto desde los valores enviados por parametros.
	 * @param nombre
	 * @param vendedor
	 * @param precio
	 * @param cantidad
	 * @param enVenta
	 * @param tipoProducto
	 * @param descripcion
	 * @param caracteristicas
	 * @param categoria
	 */
	public Producto(String nombre, String vendedor, float precio, int cantidad,boolean enVenta, String descripcion,
			Caracteristica caracteristicas, Categoria categoria) {
		super();
		ID = getIDnuevoProducto();
		setNombre(nombre);
		setCantidad(cantidad);
		setEnVenta(enVenta);
		setCaracteristicas(caracteristicas);
		setCategoria(categoria);
		setDescripcion(descripcion);
		setPrecio(precio);
		setVendedor(vendedor);
	}

	private int getIDnuevoProducto() {
		return (int) (Math.random()*10000);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Caracteristica getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(Caracteristica caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public Categoria getCategoria() {
		return categorias;
	}

	public void setCategoria(Categoria categoria) {
		this.categorias = categoria;
	}

	public int getID() {
		return ID;
	}

	public boolean isEnVenta() {
		return enVenta;
	}

	public void setEnVenta(boolean enVenta) {
		this.enVenta = enVenta;
	}

	
}
