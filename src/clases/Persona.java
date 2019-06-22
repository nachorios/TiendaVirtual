package clases;

import java.util.InputMismatchException;

abstract class Persona {
    private String nombre;
    private String apellido;
    private int edad;
    private String documento;
    private String direccion;

    Persona(){
        nombre = " ";
        apellido = " ";
        edad = 0;
        documento = " ";
        direccion = " ";
    }

    Persona(String nombre, String apellido, int edad, String documento, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.documento = documento;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad){
        this.edad = edad;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
