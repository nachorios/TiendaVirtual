package clases;
import interfaces.IJsonObj;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class Usuario extends Persona implements IJsonObj {
    private String nombreUsuario;
    private String correoElectronico;
    private double saldo;
    private ArrayList <Producto> lista;
    private String contrasenia;
    private double cantProducComprados;
    private double cantProdVendidos;


    private Cesta<Producto> cestaCompras;

     public Usuario() {
        super();
        nombreUsuario = " ";
        correoElectronico = " ";
        saldo = 0;
        contrasenia = " ";
        cantProducComprados = 0;
        cantProdVendidos = 0;
        lista = new ArrayList<>();
        cestaCompras = new Cesta<>();
    }

    public Usuario(String nombre, String apellido, int edad, String documento, String direccion,
            String nombreUsuario, String correoElectronico, double saldo, String contrasenia) {
        super(nombre, apellido, edad, documento, direccion);
        this.nombreUsuario = nombreUsuario;
        this.correoElectronico = correoElectronico;
        this.saldo = saldo;
        this.contrasenia = contrasenia;
        lista = new ArrayList<>();
        cantProducComprados = 0;
        cantProdVendidos = 0;
    }

    public double getCantProducComprados() {
        return cantProducComprados;
    }

    public void setCantProducComprados(double cantProducComprados) {
        this.cantProducComprados = cantProducComprados;
    }

    public double getCantProdVendidos() {
        return cantProdVendidos;
    }

    public void setCantProdVendidos(double cantProdVendidos) {
        this.cantProdVendidos = cantProdVendidos;
    }


    public ArrayList<Producto> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Producto> lista) {
		this.lista = lista;
	}

	public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return Double.compare(usuario.saldo, saldo) == 0 &&
                Objects.equals(nombreUsuario, usuario.nombreUsuario) &&
                Objects.equals(correoElectronico, usuario.correoElectronico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreUsuario, correoElectronico, saldo, contrasenia);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", saldo=" + saldo +
                "} ";
    }

    public Cesta<Producto> getCestaCompras() {
        return cestaCompras;
    }

    public void setCestaCompras(Cesta<Producto> cestaCompras) {
        this.cestaCompras = cestaCompras;
    }

    public void cargarSaldo(double dinero){
        setSaldo(getSaldo()+ dinero);
    }

    public void agregarProductoCesta(Producto prod) {
        getCestaCompras().agregarProductoEnCesta(prod);

    }

    public void agregarProductoLista(Producto producto) {
        getLista().add(producto);
    }

    public boolean comprar() {
        if (getSaldo() >= getCestaCompras().getPrecioTotal()){
            setSaldo(getSaldo() - getCestaCompras().getPrecioTotal());
            for (Producto p : getCestaCompras().obtenerProductos()) {
                getLista().add(p);
            }
            setCantProducComprados(getCestaCompras().obtenerProductos().size()+1);
            return true;
        }
        else
            return false;
    }

    public JSONObject objetoAJSON() {
        JSONObject jsonProducto = new JSONObject();
        jsonProducto.put("nombre", getNombre());
        jsonProducto.put("apellido", getApellido());
        jsonProducto.put("edad", getEdad());
        jsonProducto.put("documento", getDocumento());
        jsonProducto.put("direccion", getDireccion());
        jsonProducto.put("nombreUsuario", getNombreUsuario());
        jsonProducto.put("correo", getCorreoElectronico());
        jsonProducto.put("saldo", getSaldo());
        jsonProducto.put("contrasenia", getContrasenia());
        jsonProducto.put("cantidadProdVendidos", getCantProdVendidos());
        jsonProducto.put("cantProducComprados", getCantProducComprados());

        return jsonProducto;
    }

}
