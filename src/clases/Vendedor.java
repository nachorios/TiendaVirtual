package clases;
import org.json.JSONObject;

import interfaces.IJsonObj;

import java.util.ArrayList;

public class Vendedor  {
 /*   //private ArrayList<Producto> listaVenta;
    private double cantProdVendidos;

    public Vendedor() {
        super();
        cantProdVendidos = 0;
        //listaVenta = new ArrayList<>();
    }

    public Vendedor(String nombre, String apellido, int edad, String documento, String direccion, String nombreUsuario,
                    String correoElectronico, double saldo, String contrasenia, double cantProdVendidos) {
        super(nombre, apellido, edad, documento, direccion, nombreUsuario, correoElectronico, saldo, contrasenia);
        this.cantProdVendidos = cantProdVendidos;

        //listaVenta = new ArrayList<>();
    }

    public double getCantProdVendidos() {
        return cantProdVendidos;
    }

    public void setCantProdVendidos(double cantProdVendidos) {
        this.cantProdVendidos = cantProdVendidos;
    }

    /*public ArrayList<Producto> getListaVenta() {
		return listaVenta;
	}
    
    public void agregarProductoVenta(Producto prod) {
        if (prod instanceof Producto){
            listaVenta.add(prod);
        }

    }

    public void quitarProducto(Producto prod){
        for (int i = 0; i<listaVenta.size(); i++){
            if (prod == listaVenta.get(i)){
                listaVenta.remove(i);
            }

        }
    }
     */
/*    @Override
    public String toString() {
        return "Vendedor{" +
                "cantProdVendidos=" + cantProdVendidos +
                "} " + super.toString();
    }
/*///TODO CORREGIR
 /*   public void vender(Producto prod) {
        try {
            if(prod.getCantidad() < 0)
                quitarProducto(prod);
            else
                prod.setCantidad(prod.getCantidad() - 1);
            setSaldo(getSaldo() + prod.getPrecio());

        } catch (Exception excepciones) {
            excepciones.printStackTrace();
        }
    }
*/
 /*   @Override
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

    @Override
    public boolean equals(Object o){
        if(o instanceof Vendedor){
            Vendedor temp = (Vendedor) o;
            if (this.getNombreUsuario().equals(temp.getNombreUsuario()))
                return true;
            else
                return false;
        }
        else
            return false;
    }
*/
}
