import java.util.ArrayList;

enum ProductoType {PRODUCTO, SERVICIO}
public class Producto {
	private int ID;
	private String nombre;
	private String vendedor;
	private float precio;
	private int cantidad;
	private boolean enVenta;
	private ProductoType tipoProducto;
	private ArrayList<String> descripcion;
	private Caracteristica caracteristicas;
	private Categoria categoria;
	
	/*public void crearProductoEnConsola() {
		Consola.println("Ingrese el nombre del producto: ");
		setNombre(Consola.pedirString());
	}
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
		return 	"Nombre: "+getNombre()
				+"\nID: "+getID()
				+"\nVendedor: "+getVendedor()
				+"\nPrecio: "+getPrecio()
				+"\nCantidad: "+getCantidad()
				+"\nEn venta: "+isEnVenta()
				+"\nTipo de producto: "+getTipoProducto()
				+"\nDescripcion: "+getDescripcion()
				+"\nCaracteristicas: "+getCaracteristicas()
				+"\nCategorias: "+getCategoria();
	}
	
	public Producto() {
		super();
		ID = getIDnuevoProducto();
		nombre = "";
		vendedor = "";
		precio = 0;
		cantidad = 0;
		enVenta = false;
		tipoProducto = ProductoType.PRODUCTO;
		descripcion = new ArrayList<>();
		caracteristicas = new Caracteristica();
		categoria = new Categoria();
		
	}
	
	public Producto(Producto p) {
		super();
		ID = getIDnuevoProducto();
		setNombre(p.getNombre());
		setCantidad(p.getCantidad());
		setEnVenta(p.isEnVenta());
		setTipoProducto(p.getTipoProducto());
		setCaracteristicas(p.getCaracteristicas());
		setCategoria(p.getCategoria());
		setDescripcion(p.getDescripcion());
		setPrecio(p.getPrecio());
		setVendedor(p.getVendedor());
	}
	
	public Producto(String nombre, String vendedor, float precio, int cantidad,boolean enVenta,ProductoType tipoProducto, ArrayList<String> descripcion,
			Caracteristica caracteristicas, Categoria categoria) {
		super();
		ID = getIDnuevoProducto();
		setNombre(nombre);
		setCantidad(cantidad);
		setEnVenta(enVenta);
		setTipoProducto(tipoProducto);
		setCaracteristicas(caracteristicas);
		setCategoria(categoria);
		setDescripcion(descripcion);
		setPrecio(precio);
		setVendedor(vendedor);
	}

	private int getIDnuevoProducto() {
		return 1;
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

	public ArrayList<String> getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(ArrayList<String> descripcion) {
		this.descripcion = descripcion;
	}

	public Caracteristica getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(Caracteristica caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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

	public ProductoType getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(ProductoType tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	
	
}
