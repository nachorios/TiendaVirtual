package clases;

public class InterfazConsola {
    public String menuPrincipal(){
        return  "Bienvenido a Wosh!\n\n"
                + "Para ingresar presione 1\n"
                + "Para registrarse presione 2\n"
                + "Para salir 0";
    }

    public String menuSesion(){
        return "Ingrese 1 para vendedor o 2 para comprador";
    }

    public String menuRegistracion(){
        return "Ingrese 1 para registrarse como vendedor\n" +
                "Ingrese 2 para registrarse como comprador";
    }

    public String menuOpcionesVendedor(){
        return "1. Datos vendedor\n2. Publicar";
    }
}
