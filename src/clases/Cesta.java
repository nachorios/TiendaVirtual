package clases;

import java.util.ArrayList;

public class Cesta <T extends Producto> {
	private ArrayList<T> cesta;
	private double precioTotal;
	
	public Cesta() {
		setCesta(new ArrayList<T>());
		precioTotal = 0;
	}
	
	public void agregarProductoEnCesta(T producto) {
		cesta.add(producto);
		setPrecioTotal(getPrecioTotal()+producto.getPrecio());
	}
	
	public void quitarProductoEnCesta(T producto) {
		cesta.remove(producto);
		setPrecioTotal(getPrecioTotal()-producto.getPrecio());
	}
	
	public ArrayList<T> obtenerProductos() {
		ArrayList<T> nuevaCesta = getCesta();
		return nuevaCesta;
	}
	
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
