import java.util.ArrayList;
import java.util.Objects;

abstract class Usuario extends Persona {
    private String nombreUsuario;
    private String correoElectronico;
    private double saldo;
    protected ArrayList <Producto> lista;
    private String contrasenia;

    Usuario() {
        super();
        nombreUsuario = " ";
        correoElectronico = " ";
        saldo = 0;
        contrasenia = " ";
        lista = new ArrayList<>();
    }

    Usuario(String nombre, String apellido, int edad, String documento, String direccion,
            String nombreUsuario, String correoElectronico, double saldo, String contrasenia) {
        super(nombre, apellido, edad, documento, direccion);
        this.nombreUsuario = nombreUsuario;
        this.correoElectronico = correoElectronico;
        this.saldo = saldo;
        this.contrasenia = contrasenia;
        lista = new ArrayList<>();
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

}
