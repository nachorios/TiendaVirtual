public class Comprador extends Usuario implements Transaccion {
    private double cantProducComprados;

    Comprador() {
        super();
        cantProducComprados = 0;
    }

    public Comprador(String nombre, String apellido, int edad, String documento, String direccion, String nombreUsuario, String correoElectronico, double saldo, String contrasenia, double cantProducComprados) {
        super(nombre, apellido, edad, documento, direccion, nombreUsuario, correoElectronico, saldo, contrasenia);
        this.cantProducComprados = cantProducComprados;
    }

    public double getCantProducComprados() {
        return cantProducComprados;
    }

    public void setCantProducComprados(double cantProducComprados) {
        this.cantProducComprados = cantProducComprados;
    }

    // agregarProduc

    // quitarProduc


    @Override
    public String toString() {
        return "Comprador{" +
                "cantProducComprados=" + cantProducComprados +
                "} " + super.toString();
    }

    @Override
    public String mediosDePago() {
        return null;
    }

    @Override
    public void comprar() {

    }

    @Override
    public void vender() {

    }
}
