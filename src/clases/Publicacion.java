package clases;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import excepciones.MaximoProductosPublicitadosException;
import excepciones.MaximoProductosPublicitadosPorUsuarioException;
import excepciones.MaximoProductosVendidosPorUsuarioException;
import excepciones.PublicacionException;
import interfaces.IJsonObj;

import java.util.Stack;

import org.json.JSONArray;
import org.json.JSONObject;

import clases.Categoria.CategoriaType;

/**
 * Clase que almacena los productos productos colocados a la venta,
 * los productos sugeridos a los usuarios y
 * los productos publicitados.
 * @author usuario
 * @version 1.0
 */
public class Publicacion implements IJsonObj{
	private final int maxProductosSugeridos = 5;
	private final int maxProductosPublicitados = 10;
	private final int maxProductosPublicitadosxUsuario = 2;
	private final int maxProductosEnVentaxUsuario = 2;
	
	private HashMap<String,ArrayList<Producto>> listadoDeProductosVenta;
	private HashMap<String,Stack<Producto>> listadoDeProductosSugeridos;
	private HashMap<String,LinkedHashMap<Producto, Integer>> listadoDeProductosPublicitados;
	
	/**
	 * Inicializa las variables de los listados
	 */
	public Publicacion() {
		listadoDeProductosVenta = new HashMap<String,ArrayList<Producto>>();
		listadoDeProductosSugeridos = new HashMap<String,Stack<Producto>>();
		listadoDeProductosPublicitados = new HashMap<String,LinkedHashMap<Producto, Integer>>();
	}
	/**
	 * Busca los productos publicados a la venta por nombre
	 * @param nombre : Nombre del producto que se desea buscar 
	 * @param usuario : Usuario que realiza la busqueda
	 * @return : Retorna el listado de productos encontrados
	 */
	public ArrayList<Producto> buscarProductosPorNombre(String nombre, String usuario) {
		ArrayList<Producto> productos = new ArrayList<>();
		
		ArrayList<Producto> productosEnVenta = getArrayListDeProductosVenta();
		ArrayList<Producto> productosSugeridos = getArrayListDeProductosSugeridos(usuario);
		ArrayList<Producto> productosPublicitados = getArrayListDeProductosPublicitados();

		agregarProductoListaComprobacionesComienzaCon(nombre, productos, productosPublicitados);
		agregarProductoListaComprobacionesComienzaCon(nombre, productos, productosSugeridos);
		agregarProductoListaComprobacionesComienzaCon(nombre, productos, productosEnVenta);

		agregarProductoListaComprobacionesContieneEl(nombre, productos, productosPublicitados);
		agregarProductoListaComprobacionesContieneEl(nombre, productos, productosSugeridos);
		agregarProductoListaComprobacionesContieneEl(nombre, productos, productosEnVenta);
		
		return productos;
	}
	/**
	 * Busca los productos publicados a la venta segun la categoria elegida
	 * @param categoria : Categoria de productos a buscar
	 * @param usuario : Usuario que realiza la busqueda
	 * @return : Retorna el listado de productos encontrados
	 */
	public ArrayList<Producto> buscarProductosPorCategoria(CategoriaType categoria, String usuario) {
		ArrayList<Producto> productos = new ArrayList<>();
		
		ArrayList<Producto> productosEnVenta = getArrayListDeProductosVenta();
		ArrayList<Producto> productosSugeridos = getArrayListDeProductosSugeridos(usuario);
		ArrayList<Producto> productosPublicitados = getArrayListDeProductosPublicitados();
		agregarProductoListaComprobacionesCategoria(categoria, productos, productosPublicitados);
		agregarProductoListaComprobacionesCategoria(categoria, productos, productosSugeridos);
		agregarProductoListaComprobacionesCategoria(categoria, productos, productosEnVenta);
		
		return productos;
	}
	/**
	 * Funcion auxiliar que agrega los elementos segun la categoria
	 * @param categoria : Categoria a buscar
	 * @param productos : Listado de productos en donde se guardaran los productos encontrados
	 * @param productosEnVenta : Listado de todos los productos en venta
	 */
	private void agregarProductoListaComprobacionesCategoria(CategoriaType categoria, ArrayList<Producto> productos, ArrayList<Producto> productosEnVenta) {
		for(Producto p : productosEnVenta) {
			
			if(p.getCategoria().getTipo().equals(categoria) && !productos.contains(p)) {
				productos.add(p);
			}
		}
	}
	/**
	 * Funcion auxiliar que agrega los elementos segun si comienza con el nombre enviado
	 * @param nombre : Nombre a buscar
	 * @param productos : Listado de productos en donde se guardaran los productos encontrados
	 * @param productosEnVenta : Listado de todos los productos en venta
	 */
	private void agregarProductoListaComprobacionesComienzaCon(String nombre, ArrayList<Producto> productos, ArrayList<Producto> productosEnVenta) {
		for(Producto p : productosEnVenta) {
			if(p.getNombre().startsWith(nombre) && !productos.contains(p)) {
				productos.add(p);
			}
		}
	}
	/**
	 * Funcion auxiliar que agrega los elementos segun si contiene con el nombre enviado
	 * @param nombre : Nombre a buscar
	 * @param productos : Listado de productos en donde se guardaran los productos encontrados
	 * @param productosEnVenta : Listado de todos los productos en venta
	 */
	private void agregarProductoListaComprobacionesContieneEl(String nombre, ArrayList<Producto> productos, ArrayList<Producto> productosEnVenta) {
		for(Producto p : productosEnVenta) {
			if(p.getNombre().toLowerCase().contains(nombre.toLowerCase()) && !productos.contains(p)) {
				productos.add(p);
			}
		}
	}
	
	public String realizarIntercambio(Comprador comprador){
		StringBuilder resultado = new StringBuilder();
		
		ArrayList<Producto> productosAcomprar = comprador.getCestaCompras().obtenerProductos();
		ArrayList<Producto> productosNoDisponibles = new ArrayList<>();
		for (Producto p : productosAcomprar) {
			if (getArrayListDeProductosVenta().contains(p)) {
				Producto productoEnVenta = getArrayListDeProductosVenta().get(getArrayListDeProductosVenta().indexOf(p));
				if (productoEnVenta.getCantidad() < p.getCantidad()) {
					productosNoDisponibles.add(p);
				} 
			} else {
				productosNoDisponibles.add(p);
			}
		}
		
		if (!productosNoDisponibles.isEmpty()) {
			for (Producto p : productosNoDisponibles) {
				comprador.getCestaCompras().quitarProductoEnCesta(p);
				productosAcomprar.remove(p);
			}
		}
		if (comprador.comprar()) {
			resultado.append("Has realizado la compra.");
			if (!productosNoDisponibles.isEmpty()) {
				resultado.append("No ha habido stock de los siguientes productos: ");
				for (Producto p : productosNoDisponibles) {
					resultado.append(p.getNombre());
					resultado.append(" ,");
				}
			}
		} else {
			resultado.append("No tienes suficiente saldo paraa realizar la compra.");
		}
		return resultado.toString();
	}
	/**
	 * 
	 * @param usuario
	 * @param producto
	 * @param cantPublicidades
	 * @throws PublicacionException
	 */
	public void publicitarProducto(String usuario, Producto producto, int cantPublicidades) throws PublicacionException{
		
		if (contarProductosPublicitadosDeUnUsuario(usuario)<maxProductosPublicitadosxUsuario) {
			if (contarTodosProductosPublicitados()<maxProductosPublicitados) {
				//TODO fecha = fecha * nivel;
				LinkedHashMap<Producto, Integer> productos = getProductosPublicitadosDeUnUsuario(usuario);
				productos.put(producto, cantPublicidades);
				getListadoDeProductosPublicitados().put(usuario, productos);
			} else {
				throw new MaximoProductosPublicitadosException("Se ha alcanzado el limite de productos publicitados.");
			}
		} else {
			throw new MaximoProductosPublicitadosPorUsuarioException("No puedes publicitar mas de "+maxProductosPublicitadosxUsuario+" productos por usuario.");
		}
	}
	/**
	 * 
	 * @return
	 */
	private int contarTodosProductosPublicitados() {
		int cantidad = 0;
		HashMap<String,LinkedHashMap<Producto, Integer>> listado = getListadoDeProductosPublicitados();
		if (listado != null) {
			cantidad = listado.size();
		}
		return cantidad;
	}

	private LinkedHashMap<Producto, Integer> getProductosPublicitadosDeUnUsuario(String usuario) {
		LinkedHashMap<Producto, Integer> productos = getListadoDeProductosPublicitados().get(usuario);
		if (productos == null) {
			productos = new LinkedHashMap<Producto, Integer>();
		}
		
		return productos;
	}
	
	public ArrayList<Producto> getArrayListProductosPublicitadosDeUnUsuario(String usuario) {
		LinkedHashMap<Producto, Integer> productos = getListadoDeProductosPublicitados().get(usuario);
		ArrayList<Producto> productosPublicitados = new ArrayList<Producto>();
		if (productos != null) {
			Iterator it = productos.entrySet().iterator();
			while(it.hasNext()) {
				Map.Entry me = (Map.Entry)it.next();
				productosPublicitados.add((Producto) me.getKey());
			}
		}
		return productosPublicitados;
	}
	
	public int contarProductosPublicitadosDeUnUsuario(String usuario) {
		int cantidad = 0;
		LinkedHashMap<Producto, Integer> productos = getListadoDeProductosPublicitados().get(usuario);
		if (productos != null) {
			cantidad = productos.size();
		}
		return cantidad;
	}
	
	public void sugerirProducto(String usuario, Producto producto) {
		Stack<Producto> prodSugeridos = buscarProductosASugerir(usuario, producto);
		getListadoDeProductosSugeridos().put(usuario, prodSugeridos);
	}
	
	public void publicarProducto(String usuario, Producto producto) throws PublicacionException{
		if (contarProductosPublicadosDeUnUsuario(usuario)<maxProductosEnVentaxUsuario) {
			agregarProductoEnListadoProductos(usuario, producto);
			producto.setEnVenta(true);
		} else {
			throw new MaximoProductosVendidosPorUsuarioException("No puedes publicar mas de "+maxProductosEnVentaxUsuario+" productos por usuario.");
		}
	}
	
	public int contarProductosPublicadosDeUnUsuario(String usuario) {
		int cantidad = 0;
		ArrayList<Producto> productos = getProductosDeUnUsuario(usuario);
		if (productos != null) {
			cantidad = productos.size();
		}
		return cantidad;
	}
	
	private Stack<Producto> buscarProductosASugerir(String usuario, Producto producto) {
		ArrayList<Producto> productos = new ArrayList<>();
		Iterator<Entry<String, ArrayList<Producto>>> it = getListadoDeProductosVenta().entrySet().iterator();
		while (it.hasNext()) {
			@SuppressWarnings("rawtypes")//?
			Map.Entry me = (Map.Entry) it.next();
			for (Producto p : (ArrayList<Producto>)me.getValue()) {
				productos.add(p);
			}
			
		}
		int prodSugeridos = 0;
		
		Stack<Producto> productosSugeridos = getProductosSugeridosDeUnUsuario(usuario);
		
		for (Producto p : productos) {
			if (prodSugeridos < maxProductosSugeridos) {
				if (p.getCategoria() == producto.getCategoria()) {
					productosSugeridos.push(p);
					if (productosSugeridos.size() >= maxProductosSugeridos) {
						productosSugeridos.pop();
					}
					prodSugeridos++;
				}
			} else {
				break;
			}
		}
		return productosSugeridos;
	}
	
	private Stack<Producto> getProductosSugeridosDeUnUsuario(String usuario) {
		Stack<Producto> productosSugeridos = getListadoDeProductosSugeridos().get(usuario);
		if (productosSugeridos == null) {
			productosSugeridos = new Stack<Producto>();
		}
		return productosSugeridos;
	}

	private void agregarProductoEnListadoProductos(String usuario, Producto producto) {
		ArrayList<Producto> productos = getProductosDeUnUsuario(usuario);
		productos.add(producto);
		getListadoDeProductosVenta().put(usuario, productos);
	}
	
	public ArrayList<Producto> getProductosDeUnUsuario(String usuario) {
		ArrayList<Producto> productos = getListadoDeProductosVenta().get(usuario);
		if (productos == null) {
			productos = new ArrayList<>();
		}
		return productos;
	}
	/*GETTERS Y SETTERS*/
	private HashMap<String, ArrayList<Producto>> getListadoDeProductosVenta() {
		return listadoDeProductosVenta;
	}

	private HashMap<String, LinkedHashMap<Producto, Integer>> getListadoDeProductosPublicitados() {
		return listadoDeProductosPublicitados;
	}
	
	private HashMap<String, Stack<Producto>> getListadoDeProductosSugeridos() {
		return listadoDeProductosSugeridos;
	}
	
	private ArrayList<Producto> getArrayListDeProductosVenta() {
		Iterator it = getListadoDeProductosVenta().entrySet().iterator();
		ArrayList<Producto> productos = new ArrayList<Producto>();
		while (it.hasNext()) {
			Map.Entry me = (Map.Entry) it.next();
			for (Producto p : (ArrayList<Producto>) me.getValue()) {
				productos.add(p);
			}
		}
		return productos;
	}

	private ArrayList<Producto> getArrayListDeProductosPublicitados() {
		Iterator it = getListadoDeProductosPublicitados().entrySet().iterator();
		ArrayList<Producto> productos = new ArrayList<Producto>();
		while (it.hasNext()) {
			Map.Entry me = (Map.Entry) it.next();
			LinkedHashMap<Date, Producto> fechasConProducto = (LinkedHashMap<Date, Producto>) me.getValue();
			Iterator it2 = fechasConProducto.entrySet().iterator();
			while (it2.hasNext()) {
				Map.Entry me2 = (Map.Entry) it2.next();
				productos.add((Producto) me2.getKey());
			}
		}
		return productos;
	}
	
	private ArrayList<Producto> getArrayListDeProductosSugeridos(String usuario) {
		Iterator it = getListadoDeProductosSugeridos().entrySet().iterator();
		ArrayList<Producto> productos = new ArrayList<Producto>();
		while (it.hasNext()) {
			Map.Entry me = (Map.Entry) it.next();
			Stack<Producto> pilaProductos = (Stack<Producto>) me.getValue();
			if (me.getKey().equals(usuario)) {
				while (pilaProductos.size() > 0) {
					productos.add(pilaProductos.pop());
				}
			}
		}
		return productos;
	}

	@Override
	public JSONObject objetoAJSON() {
		JSONObject jsonPublicacion = new JSONObject();
		
		JSONArray jsonProdEnVenta = new JSONArray();
		Iterator iterProdEnVenta = getListadoDeProductosVenta().entrySet().iterator();
		while (iterProdEnVenta.hasNext()) {
			Map.Entry me = (Map.Entry) iterProdEnVenta.next();
			JSONObject usuarioConProductos = new JSONObject();
			usuarioConProductos.put(me.getKey().toString(), ((Producto)me.getValue()).objetoAJSON());
			jsonProdEnVenta.put(usuarioConProductos);
		}
		jsonPublicacion.put("listadoProductosEnVenta", jsonProdEnVenta);
		
		
		JSONArray jsonProdSugerido= new JSONArray();
		Iterator iterProdSugerido = getListadoDeProductosSugeridos().entrySet().iterator();
		while (iterProdSugerido.hasNext()) {
			Map.Entry me = (Map.Entry) iterProdSugerido.next();
			JSONObject usuarioConProductos = new JSONObject();
			usuarioConProductos.put(me.getKey().toString(), ((Producto)me.getValue()).objetoAJSON());
			jsonProdSugerido.put(usuarioConProductos);
		}
		jsonPublicacion.put("listadoProductosEnVenta", jsonProdSugerido);
		
		JSONArray jsonProdPublicitados= new JSONArray();// String
		JSONObject jsonLinkedHashmap= new JSONObject();// LinkedHashMap<Producto, Integer>
		JSONObject jsonProdConCantidad = new JSONObject();// Producto, Integer
		
		Iterator iterProdPublicitados = getListadoDeProductosPublicitados().entrySet().iterator();
		while (iterProdPublicitados.hasNext()) {
			Map.Entry me = (Map.Entry) iterProdPublicitados.next(); // LinkedHashMap<Producto, Integer>
			LinkedHashMap<Producto, Integer> productoConCantPublicidad = (LinkedHashMap<Producto, Integer>) me.getValue();
			Iterator it2 = productoConCantPublicidad.entrySet().iterator();
			while (it2.hasNext()) {
				Map.Entry me2 = (Map.Entry) it2.next();
				jsonProdConCantidad.put((String) me2.getKey(), me2.getValue());
			}
			jsonLinkedHashmap.put((String) me.getKey(), jsonProdConCantidad);
			jsonProdPublicitados.put(jsonProdConCantidad);
		}
		jsonPublicacion.put("listadoProductosEnVenta", jsonProdPublicitados);
		
		return jsonPublicacion;
	}
	
	/*private void setListadoDeProductosVenta(HashMap<Usuario, ArrayList<Producto>> listadoDeProductos) {
		this.listadoDeProductosVenta = listadoDeProductos;
	}
	private void setListadoDeProductosSugeridos(HashMap<Usuario, Stack<Producto>> listadoDeProductosSugeridos) {
		this.listadoDeProductosSugeridos = listadoDeProductosSugeridos;
	}
	private void setListadoDeProductosPublicitados(
			HashMap<Usuario, LinkedHashMap<Date, Producto>> listadoDeProductosPublicitados) {
		this.listadoDeProductosPublicitados = listadoDeProductosPublicitados;
	}*/
	
	
}
