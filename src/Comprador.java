import org.json.JSONObject;

public class Comprador extends Usuario implements IJSON {
    private double cantProducComprados;

    Comprador() {
        super();
        cantProducComprados = 0;
    }

    public Comprador(String nombre, String apellido, int edad, String documento, String direccion, String nombreUsuario,
                     String correoElectronico, double saldo, String contrasenia, double cantProducComprados) {
        super(nombre, apellido, edad, documento, direccion, nombreUsuario, correoElectronico, saldo, contrasenia);
        this.cantProducComprados = cantProducComprados;
    }

    public double getCantProducComprados() {
        return cantProducComprados;
    }

    public void setCantProducComprados(double cantProducComprados) {
        this.cantProducComprados = cantProducComprados;
    }

    public void comprar(Producto prod) throws Excepciones{
        if (getSaldo()>0 && prod.getCantidad()>0){
            setSaldo(getSaldo() - prod.getPrecio());
            lista.add(prod);
        }
        else
            throw new Excepciones("No tiene saldo");
    }

    public void cargarSaldo(double dinero){
        setSaldo(getSaldo()+ dinero);
    }

    @Override
    public String toString() {
        return "Comprador{" +
                "cantProducComprados=" + cantProducComprados +
                "} " + super.toString();
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
        jsonProducto.put("cantProducComprados", getCantProducComprados());

        return jsonProducto;
    }
}
