package clases;

import java.util.ArrayList;
/**
 * Esta clase almacenerá todos los productos que el usuario desea comprar.
 * @version 1.0
 * @param <T> : Cualquier objeto que herede de Producto.
 */
public class Cesta <T extends Producto> {
	private ArrayList<T> cesta;
	private double precioTotal;
	
	public Cesta() {
		setCesta(new ArrayList<T>());
		precioTotal = 0;
	}
	/**
	 * Añade el producto a la cesta e incrementa el precio total.
	 * @param producto : Cualquier objeto que herede de Producto.
	 */
	public void agregarProductoEnCesta(T producto) {
		cesta.add(producto);
		setPrecioTotal(getPrecioTotal()+producto.getPrecio()*producto.getCantidad());
	}
	/**
	 * Quita un producto de la cestaa y disminuye su precio total.
	 * @param producto : Cualquier objeto que herede de Producto.
	 */
	public void quitarProductoEnCesta(T producto) {
		cesta.remove(producto);
		setPrecioTotal(getPrecioTotal()-producto.getPrecio()*producto.getCantidad());
	}
	/**
	 * Retorna todos los productos que se encuentran en la cesta.
	 * @return : retorna un arraylist de todos los productos de la cesta.
	 */
	public ArrayList<T> obtenerProductos() {
		ArrayList<T> nuevaCesta = getCesta();
		return nuevaCesta;
	}
	/**
	 * Vacia la lista de la cesta y coloca el precio totaal en 0. 
	 */
	public void vaciarCesta() {
		cesta.clear();
		setPrecioTotal(0);
	}
	  
	private ArrayList<T> getCesta() {
		return cesta;
	}

	private void setCesta(ArrayList<T> cesta) {
		this.cesta = cesta;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	  
}
