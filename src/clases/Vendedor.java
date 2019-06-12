package clases;
import excepciones.Excepciones;
import org.json.JSONObject;

import interfaces.IJsonObj;

import java.util.ArrayList;

public class Vendedor extends Usuario implements IJsonObj {
    private ArrayList<Producto> listaVenta;
    private double cantProdVendidos;
    private boolean esEmpresa;

    Vendedor() {
        super();
        cantProdVendidos = 0;
        esEmpresa = true;
        listaVenta = new ArrayList<>();
    }

    public Vendedor(String nombre, String apellido, int edad, String documento, String direccion, String nombreUsuario,
                    String correoElectronico, double saldo, String contrasenia, double cantProdVendidos, boolean esEmpresa) {
        super(nombre, apellido, edad, documento, direccion, nombreUsuario, correoElectronico, saldo, contrasenia);
        this.cantProdVendidos = cantProdVendidos;
        this.esEmpresa = esEmpresa;
        listaVenta = new ArrayList<>();
    }

    public double getCantProdVendidos() {
        return cantProdVendidos;
    }

    public void setCantProdVendidos(double cantProdVendidos) {
        this.cantProdVendidos = cantProdVendidos;
    }

    public boolean isEsEmpresa() {
        return esEmpresa;
    }

    public void setEsEmpresa(boolean esEmpresa) {
        this.esEmpresa = esEmpresa;
    }

    public ArrayList<Producto> getListaVenta() {
		return listaVenta;
	}
    
    public void agregarProductoVenta(Producto prod) throws Excepciones {
        if (prod instanceof Producto){
            listaVenta.add(prod);
        }
        else
            throw new Excepciones("Agregue un producto");
    }

    public void quitarProducto(Producto prod) throws Excepciones{
        for (int i = 0; i<listaVenta.size(); i++){
            if (prod == listaVenta.get(i)){
                listaVenta.remove(i);
            }
            else
                throw new Excepciones("Producto no encontrado");
        }
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "cantProdVendidos=" + cantProdVendidos +
                "} " + super.toString();
    }

    public void vender(Producto prod) {
        try {
            if(prod.getCantidad() < 0)
                quitarProducto(prod);
            else
                prod.setCantidad(prod.getCantidad() - 1);
            setSaldo(getSaldo() + prod.getPrecio());

        } catch (Excepciones excepciones) {
            excepciones.printStackTrace();
        }
    }

    @Override
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
        jsonProducto.put("empresa", isEsEmpresa());

        return jsonProducto;
    }
}
