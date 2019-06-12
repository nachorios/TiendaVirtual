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

public class Publicacion implements IJsonObj{
	private final int maxProductosSugeridos = 5;
	private final int maxProductosPublicitados = 10;
	private final int maxProductosPublicitadosxUsuario = 2;
	private final int maxProductosEnVentaxUsuario = 2;
	
	HashMap<Usuario,ArrayList<Producto>> listadoDeProductosVenta;//Corregir
	HashMap<Usuario,Stack<Producto>> listadoDeProductosSugeridos;
	HashMap<Usuario,LinkedHashMap<Date, Producto>> listadoDeProductosPublicitados;
	
	public Publicacion() {
		listadoDeProductosVenta = new HashMap<Usuario,ArrayList<Producto>>();
		listadoDeProductosSugeridos = new HashMap<Usuario,Stack<Producto>>();
		listadoDeProductosPublicitados = new HashMap<Usuario,LinkedHashMap<Date, Producto>>();
	}
	
	public void publicitarProducto(Usuario usuario, Producto producto, int nivel, Date fecha) throws PublicacionException{
		
		if (contarProductosPublicitadosDeUnUsuario(usuario)<maxProductosPublicitadosxUsuario) {
			if (contarTodosProductosPublicitados()<maxProductosPublicitados) {
				//TODO fecha = fecha * nivel;
				LinkedHashMap<Date, Producto> productos = getProductosPublicitadosDeUnUsuario(usuario);
				productos.put(fecha, producto);
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
		HashMap<Usuario,LinkedHashMap<Date, Producto>> listado = getListadoDeProductosPublicitados();
		if (listado != null) {
			cantidad = listado.size();
		}
		return cantidad;
	}

	private LinkedHashMap<Date, Producto> getProductosPublicitadosDeUnUsuario(Usuario usuario) {
		LinkedHashMap<Date, Producto> productos = getListadoDeProductosPublicitados().get(usuario);
		if (productos == null) {
			productos = new LinkedHashMap<Date, Producto>();
		}
		
		return productos;
	}
	
	public int contarProductosPublicitadosDeUnUsuario(Usuario usuario) {
		int cantidad = 0;
		LinkedHashMap<Date, Producto> productos = getListadoDeProductosPublicitados().get(usuario);
		if (productos != null) {
			cantidad = productos.size();
		}
		return cantidad;
	}
	
	public void sugerirProducto(Usuario usuario, Producto producto) {
		Stack<Producto> prodSugeridos = buscarProductosASugerir(usuario, producto);
		getListadoDeProductosSugeridos().put(usuario, prodSugeridos);
	}
	
	public void publicarProducto(Usuario usuario, Producto producto) throws PublicacionException{
		if (contarProductosPublicadosDeUnUsuario(usuario)<maxProductosEnVentaxUsuario) {
			agregarProductoEnListadoProductos(usuario, producto);
		} else {
			throw new MaximoProductosVendidosPorUsuarioException("No puedes publicar mas de "+maxProductosEnVentaxUsuario+" productos por usuario.");
		}
	}
	
	public int contarProductosPublicadosDeUnUsuario(Usuario usuario) {
		int cantidad = 0;
		ArrayList<Producto> productos = getProductosDeUnUsuario(usuario);
		if (productos != null) {
			cantidad = productos.size();
		}
		return cantidad;
	}
	
	private Stack<Producto> buscarProductosASugerir(Usuario usuario, Producto producto) {
		ArrayList<Producto> productos = new ArrayList<>();
		Iterator<Entry<Usuario, ArrayList<Producto>>> it = getListadoDeProductosVenta().entrySet().iterator();
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
	
	private Stack<Producto> getProductosSugeridosDeUnUsuario(Usuario usuario) {
		Stack<Producto> productosSugeridos = getListadoDeProductosSugeridos().get(usuario);
		if (productosSugeridos == null) {
			productosSugeridos = new Stack<Producto>();
		}
		return productosSugeridos;
	}

	private void agregarProductoEnListadoProductos(Usuario usuario, Producto producto) {
		ArrayList<Producto> productos = getProductosDeUnUsuario(usuario);
		productos.add(producto);
		getListadoDeProductosVenta().put(usuario, productos);
	}
	
	private ArrayList<Producto> getProductosDeUnUsuario(Usuario usuario) {
		ArrayList<Producto> productos = getListadoDeProductosVenta().get(usuario);
		if (productos == null) {
			productos = new ArrayList<>();
		}
		return productos;
	}
	/*GETTERS Y SETTERS*/
	private HashMap<Usuario, ArrayList<Producto>> getListadoDeProductosVenta() {
		return listadoDeProductosVenta;
	}

	private HashMap<Usuario, LinkedHashMap<Date, Producto>> getListadoDeProductosPublicitados() {
		return listadoDeProductosPublicitados;
	}
	
	private HashMap<Usuario, Stack<Producto>> getListadoDeProductosSugeridos() {
		return listadoDeProductosSugeridos;
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
		
		/*
		JSONArray jsonProdPublicitados= new JSONArray();
		Iterator iterProdPublicitados = getListadoDeProductosPublicitados().entrySet().iterator();
		while (iterProdPublicitados.hasNext()) {
			Map.Entry me = (Map.Entry) iterProdPublicitados.next();
			JSONObject usuarioConProductos = new JSONObject();
			usuarioConProductos.put(me.getKey().toString(), ((Producto)me.getValue()).objetoAJSON());
			JSONObject fechaConProducto = new JSONObject();
			Map.Entry = ((LinkedHashMap<Date, Producto>)me.getValue()).entrySet();
			fechaConProducto.put(, value)
			jsonProdSugerido.put(usuarioConProductos);
		}
		jsonPublicacion.put("listadoProductosEnVenta", jsonProdSugerido);*/
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
