package clases;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import excepciones.Excepciones;
import excepciones.MaximoProductosPublicitadosException;
import excepciones.MaximoProductosPublicitadosPorUsuarioException;
import excepciones.MaximoProductosVendidosPorUsuarioException;
import excepciones.PublicacionException;
import interfaces.IJsonObj;

import java.util.Stack;

import org.json.JSONArray;
import org.json.JSONObject;

import clases.Categoria.CategoriaType;

public class Publicacion implements IJsonObj{
	private final int maxProductosSugeridos = 5;
	private final int maxProductosPublicitados = 10;
	private final int maxProductosPublicitadosxUsuario = 2;
	private final int maxProductosEnVentaxUsuario = 2;
	
	private HashMap<String,ArrayList<Producto>> listadoDeProductosVenta;
	private HashMap<String,Stack<Producto>> listadoDeProductosSugeridos;
	private HashMap<String,LinkedHashMap<Integer, Producto>> listadoDeProductosPublicitados;
	
	public Publicacion() {
		listadoDeProductosVenta = new HashMap<String,ArrayList<Producto>>();
		listadoDeProductosSugeridos = new HashMap<String,Stack<Producto>>();
		listadoDeProductosPublicitados = new HashMap<String,LinkedHashMap<Integer, Producto>>();
	}
	
	public ArrayList<Producto> buscarProductosPorNombre(String nombre) {
		ArrayList<Producto> productos = new ArrayList<>();
		
		ArrayList<Producto> productosEnVenta = getArrayListDeProductosVenta();
		ArrayList<Producto> productosSugeridos = getArrayListDeProductosSugeridos();
		ArrayList<Producto> productosPublicitados = getArrayListDeProductosPublicitados();

		agregarProductoListaComprobacionesComienzaCon(nombre, productos, productosPublicitados);
		agregarProductoListaComprobacionesComienzaCon(nombre, productos, productosSugeridos);
		agregarProductoListaComprobacionesComienzaCon(nombre, productos, productosEnVenta);

		agregarProductoListaComprobacionesContieneEl(nombre, productos, productosPublicitados);
		agregarProductoListaComprobacionesContieneEl(nombre, productos, productosSugeridos);
		agregarProductoListaComprobacionesContieneEl(nombre, productos, productosEnVenta);
		
		return productos;
	}
	
	public ArrayList<Producto> buscarProductosPorCategoria(CategoriaType categoria) {
		ArrayList<Producto> productos = new ArrayList<>();
		
		ArrayList<Producto> productosEnVenta = getArrayListDeProductosVenta();
		ArrayList<Producto> productosSugeridos = getArrayListDeProductosSugeridos();
		ArrayList<Producto> productosPublicitados = getArrayListDeProductosPublicitados();

		agregarProductoListaComprobacionesCategoria(categoria, productos, productosPublicitados);
		agregarProductoListaComprobacionesCategoria(categoria, productos, productosSugeridos);
		agregarProductoListaComprobacionesCategoria(categoria, productos, productosEnVenta);
		
		return productos;
	}
	
	private void agregarProductoListaComprobacionesCategoria(CategoriaType categoria, ArrayList<Producto> productos, ArrayList<Producto> productosEnVenta) {
		for(Producto p : productosEnVenta) {
			if(p.getCategoria().getTipo().equals(categoria) && !productos.contains(p)) {
				productos.add(p);
			}
		}
	}
	
	private void agregarProductoListaComprobacionesComienzaCon(String nombre, ArrayList<Producto> productos, ArrayList<Producto> productosEnVenta) {
		for(Producto p : productosEnVenta) {
			if(p.getNombre().startsWith(nombre) && !productos.contains(p)) {
				productos.add(p);
			}
		}
	}
	
	private void agregarProductoListaComprobacionesContieneEl(String nombre, ArrayList<Producto> productos, ArrayList<Producto> productosEnVenta) {
		for(Producto p : productosEnVenta) {
			if(p.getNombre().contains(nombre) && !productos.contains(p)) {
				productos.add(p);
			}
		}
	}
	
	public String removerProducto(Vendedor vendedor,Comprador comprador, Producto producto){
		if(comprador.comprar(producto)){
			vendedor.vender(producto);
			listadoDeProductosVenta.remove(producto);
			return "Producto comprado";
		}
		else {
			return "No es posbile realizar la compra";
		}
	}
	
	public void publicitarProducto(String usuario, Producto producto, int cantPublicidades) throws PublicacionException{
		
		if (contarProductosPublicitadosDeUnUsuario(usuario)<maxProductosPublicitadosxUsuario) {
			if (contarTodosProductosPublicitados()<maxProductosPublicitados) {
				//TODO fecha = fecha * nivel;
				LinkedHashMap<Integer, Producto> productos = getProductosPublicitadosDeUnUsuario(usuario);
				productos.put(cantPublicidades, producto);
				getListadoDeProductosPublicitados().put(usuario, productos);
			} else {
				throw new MaximoProductosPublicitadosException("Se ha alcanzado el limite de productos publicitados.");
			}
		} else {
			throw new MaximoProductosPublicitadosPorUsuarioException("No puedes publicitar mas de "+maxProductosPublicitadosxUsuario+" productos por usuario.");
		}
	}
	
	private int contarTodosProductosPublicitados() {
		int cantidad = 0;
		HashMap<String,LinkedHashMap<Integer, Producto>> listado = getListadoDeProductosPublicitados();
		if (listado != null) {
			cantidad = listado.size();
		}
		return cantidad;
	}

	private LinkedHashMap<Integer, Producto> getProductosPublicitadosDeUnUsuario(String usuario) {
		LinkedHashMap<Integer, Producto> productos = getListadoDeProductosPublicitados().get(usuario);
		if (productos == null) {
			productos = new LinkedHashMap<Integer, Producto>();
		}
		
		return productos;
	}
	
	public int contarProductosPublicitadosDeUnUsuario(String usuario) {
		int cantidad = 0;
		LinkedHashMap<Integer, Producto> productos = getListadoDeProductosPublicitados().get(usuario);
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
			productos.add((Producto) me);
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

	private HashMap<String, LinkedHashMap<Integer, Producto>> getListadoDeProductosPublicitados() {
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
			productos.add((Producto) me.getValue());
		}
		return productos;
	}

	private ArrayList<Producto> getArrayListDeProductosPublicitados() {
		// HashMap<String,LinkedHashMap<Date, Producto>>
		
		Iterator it = getListadoDeProductosPublicitados().entrySet().iterator();
		ArrayList<Producto> productos = new ArrayList<Producto>();
		while (it.hasNext()) {
			Map.Entry me = (Map.Entry) it.next();
			LinkedHashMap<Date, Producto> fechasConProducto = (LinkedHashMap<Date, Producto>) me.getValue();
			Iterator it2 = fechasConProducto.entrySet().iterator();
			while (it2.hasNext()) {
				Map.Entry me2 = (Map.Entry) it2.next();
				productos.add((Producto) me2.getValue());
			}
		}
		return productos;
	}
	
	private ArrayList<Producto> getArrayListDeProductosSugeridos() {
		Iterator it = getListadoDeProductosSugeridos().entrySet().iterator();
		ArrayList<Producto> productos = new ArrayList<Producto>();
		while (it.hasNext()) {
			Map.Entry me = (Map.Entry) it.next();
			Stack<Producto> pilaProductos = (Stack<Producto>) me.getValue();
			while (pilaProductos.size() > 0) {
				productos.add(pilaProductos.pop());
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
		
		
		JSONArray jsonProdPublicitados= new JSONArray();
		Iterator iterProdPublicitados = getListadoDeProductosPublicitados().entrySet().iterator();
		while (iterProdPublicitados.hasNext()) {
			Map.Entry me = (Map.Entry) iterProdPublicitados.next();
			JSONObject usuarioConProductos = new JSONObject();
			usuarioConProductos.put(me.getKey().toString(), ((Producto)me.getValue()).objetoAJSON());
			JSONObject fechaConProducto = new JSONObject();
			//Map.Entry me2= ((LinkedHashMap<Date, Producto>)me.getValue()).entrySet();
			//fechaConProducto.put(, value)
			jsonProdSugerido.put(usuarioConProductos);
		}
		jsonPublicacion.put("listadoProductosEnVenta", jsonProdPublicitados);
		//TODO LISTADO DE PRODUCTOS PUBLICITADOS
		
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
