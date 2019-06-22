package clases;

public class InterfazConsola {
    public String menuPrincipal(){
        return  "Bienvenido a Wosh!\n\n"
                + "Para ingresar presione 1\n"
                + "Para registrarse presione 2\n"
                + "Para salir 0";
    }

    public String menuSesion(){
        return "Ingrese 1 para vendedor\nIngrese 2 para comprador";
    }

    public String menuRegistracion(){
        return "Ingrese 1 para registrarse como vendedor\nIngrese 2 para registrarse como comprador";
    }

    public String menuOpcionesVendedor(){
        return "1. Datos\n2.Crear producto\n3.Vender";
    }
}
