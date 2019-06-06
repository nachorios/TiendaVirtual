import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

public class Publicacion {
	private final int maxProductosSugeridos = 5;
	private final int maxProductosPublicitados = 10;
	private final int maxProductosPublicitadosxUsuario = 2;
	private final int maxProductosEnVentaxUsuario = 2;
	
	HashMap<Usuario,ArrayList<Producto>> listadoDeProductosVenta;
	HashMap<Usuario,Stack<Producto>> listadoDeProductosSugeridos;
	HashMap<Usuario,LinkedHashMap<Integer, Producto>> listadoDeProductosPublicitados;
	
	public Publicacion() {
		listadoDeProductosVenta = new HashMap<Usuario,ArrayList<Producto>>();
		listadoDeProductosSugeridos = new HashMap<Usuario,Stack<Producto>>();
		listadoDeProductosPublicitados = new HashMap<Usuario,LinkedHashMap<Integer, Producto>>();
	}
	
	public boolean publicitarProducto(Usuario usuario, Producto producto, int nivel, Date fecha) {
		//TODO
		return false;
	}
	
	public void sugerirProducto(Usuario usuario, Producto producto) {
		Stack<Producto> prodSugeridos = buscarProductosASugerir(usuario, producto);
		getListadoDeProductosSugeridos().put(usuario, prodSugeridos);
	}
	
	public void publicarProducto(Usuario usuario, Producto producto) {
		agregarProductoEnListadoProductos(usuario, producto);
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

	private void setListadoDeProductosVenta(HashMap<Usuario, ArrayList<Producto>> listadoDeProductos) {
		this.listadoDeProductosVenta = listadoDeProductos;
	}

	private HashMap<Usuario, Stack<Producto>> getListadoDeProductosSugeridos() {
		return listadoDeProductosSugeridos;
	}

	private void setListadoDeProductosSugeridos(HashMap<Usuario, Stack<Producto>> listadoDeProductosSugeridos) {
		this.listadoDeProductosSugeridos = listadoDeProductosSugeridos;
	}

	private HashMap<Usuario, LinkedHashMap<Integer, Producto>> getListadoDeProductosPublicitados() {
		return listadoDeProductosPublicitados;
	}

	private void setListadoDeProductosPublicitados(
			HashMap<Usuario, LinkedHashMap<Integer, Producto>> listadoDeProductosPublicitados) {
		this.listadoDeProductosPublicitados = listadoDeProductosPublicitados;
	}
	
	
}
