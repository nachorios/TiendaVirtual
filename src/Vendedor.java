import java.util.ArrayList;

public class Vendedor extends Usuario implements Transaccion {
    private ArrayList<Producto> listaVenta;
    private double cantProdVendidos;

    Vendedor(){
        super();
        cantProdVendidos = 0;
        listaVenta = new ArrayList<>();
    }

    public Vendedor(String nombre, String apellido, int edad, String documento, String direccion, String nombreUsuario, String correoElectronico, double saldo, String contrasenia, double cantProdVendidos) {
        super(nombre, apellido, edad, documento, direccion, nombreUsuario, correoElectronico, saldo, contrasenia);
        this.cantProdVendidos = cantProdVendidos;
        listaVenta = new ArrayList<>();
    }

    public double getCantProdVendidos() {
        return cantProdVendidos;
    }

    public void setCantProdVendidos(double cantProdVendidos) {
        this.cantProdVendidos = cantProdVendidos;
    }

    // agregarProducto

    // quitarProducto


    @Override
    public String toString() {
        return "Vendedor{" +
                "cantProdVendidos=" + cantProdVendidos +
                "} " + super.toString();
    }

    @Override
    public String mediosDePago(){
        return null;
    }

    @Override
    public void comprar() {

    }

    @Override
    public void vender() {

    }
}
