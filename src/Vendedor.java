public class Vendedor extends Usuario {
    //private listadoProdVenta lista;
    private double cantProdVendidos;

    Vendedor(){
        super();
        cantProdVendidos = 0;
    }

    public Vendedor(String nombre, String apellido, int edad, String documento, String direccion, String nombreUsuario, String correoElectronico, double saldo, String contrasenia, double cantProdVendidos) {
        super(nombre, apellido, edad, documento, direccion, nombreUsuario, correoElectronico, saldo, contrasenia);
        this.cantProdVendidos = cantProdVendidos;
    }

    public double getCantProdVendidos() {
        return cantProdVendidos;
    }

    public void setCantProdVendidos(double cantProdVendidos) {
        this.cantProdVendidos = cantProdVendidos;
    }

    // agregarProducto

    // quitarProducto
}
