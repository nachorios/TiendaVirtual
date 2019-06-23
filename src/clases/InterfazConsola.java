package clases;

public class InterfazConsola {
    public String menuPrincipal(){
        return  "Bienvenido a Wosh!\n\n"
                + "Para ingresar presione 1\n"
                + "Para registrarse presione 2\n"
                + "Para salir 0";
    }

    public String menuSesion(){
        return "Ingrese su usuario y contraseña";
    }

    public String menuRegistracion(){
        return "Ingrese los siguientes datos";
    }

    public String menuOpciones(){
        return "1. Datos\n2. Vender\n3. Comprar";
    }
}
