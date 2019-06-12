package clases;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;

import caracteristicas.Caracteristica;
import caracteristicas.listado.Alimento;
import clases.Categoria.CategoriaType;
import excepciones.Excepciones;
import excepciones.PublicacionException;
import interfacesGraficas.Interfaz;
import utils.UtilsClases;

public class Main {
	static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
    	Vendedor vendedor = new Vendedor("Carlitos","Menem",45,"12435784","Mercedes 123","Carli","carlitos@hotmail.com",100.00,"contrasena",0,false);
    	
    	ArrayList<String> descripcion = new ArrayList<>();
    	descripcion.add("bebida deliciosa");
    	Categoria categoria = new Categoria(CategoriaType.ALIMENTO);
    	Producto producto = new Producto("Manaos",vendedor.getNombreUsuario(),14.0f,12,false,
    			descripcion,new Alimento("labichi", 14, "Enlatada", "amargo"),categoria);
    	
    	try {
			vendedor.agregarProductoVenta(producto);
		} catch (Excepciones e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Publicacion publicacion = new Publicacion();
    	try {
			publicacion.publicarProducto(vendedor.getNombreUsuario(), producto);
		} catch (PublicacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//System.out.println(publicacion.getProductosDeUnUsuario(vendedor.getNombreUsuario()));
    	Comprador comprador = new Comprador("Mario","Cansas",29,"18746856","calle 1","Marito","marioNeta@gmail.com",14.0,"contra",0);
   
		System.out.println(publicacion.removerProducto(vendedor, comprador, producto));;
		
    
    	
    	
    }
    
    //prueba
    public static void crearProducto() {
    	Scanner sc = new Scanner(System.in);
    	Producto producto = new Producto();
    	
    	System.out.println("--Crear Pruducto--");
    	System.out.println("Nombre del producto: ");
    	producto.setNombre(sc.next());
    	System.out.println("Descripcion:");
    	String[] descrip = sc.next().split(". ");
    	ArrayList<String> descripcion = new ArrayList<>();
    	for (String s : descrip) {
    		descripcion.add(s);
    	}
    	producto.setDescripcion(descripcion);
    	System.out.println("Elije la categoria de tu producto: ");
    	for (int i = 0; i < CategoriaType.values().length; i++) {
    		System.out.println(i+") "+ CategoriaType.values()[i]);
    	}
    	CategoriaType categoria = CategoriaType.values()[sc.nextInt()];
    	producto.setCategoria(new Categoria(categoria));
    	System.out.println("Ingrese las caracteristicas de tu producto:");
    	Caracteristica caracteristicas;
    	String mayuscula = Character.toString(categoria.name().charAt(0));
    	String nombreClase = categoria.name().toLowerCase().replace(categoria.name().toLowerCase().charAt(0), mayuscula.charAt(0));
    	 try {
    		    Class<?> caracteristica = Class.forName("caracteristicas.listado."+nombreClase);
    		    ArrayList<Object> attr = new ArrayList<>();
    		    for(int i = 0; i < caracteristica.getDeclaredFields().length; i++) {
    		    	
    	    		Field atributo = caracteristica.getDeclaredFields()[i];
    	    		System.out.println(atributo.getName()+": ");
    	    		if(UtilsClases.objectIsInteger(atributo.getType())) {
    	    			attr.add(sc.nextInt());
    	    		} else if (UtilsClases.objectIsString(atributo.getType())) {
    	    			attr.add(sc.next());
    	    		}
    	    	}
    		    Object[] atributos = new Object[attr.size()];
    		    for (int i = 0; i < attr.size(); i++) {
    		    	atributos[i] = attr.get(i);
    		    }
				try {
					Constructor<?> ctor;
					ctor = caracteristica.getConstructors()[0];
					try {
						Object object;
						object = ctor.newInstance(atributos);
						
						producto.setCaracteristicas((Caracteristica) object);
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		 } catch (ClassNotFoundException e) {
    			 // TODO Auto-generated catch block
    			 e.printStackTrace();
    		}
    	System.out.println("Ingrese el precio de tu producto:");
    	producto.setPrecio(sc.nextFloat());
    	System.out.println("Ingrese la cantidad de productos en stock:");
    	producto.setCantidad(sc.nextInt());
    	//se obtiene el nombre del vendedor y se coloca
    	producto.setVendedor("marito");
    	
    	System.out.println(producto.toString());
    	sc.close();
    }
    
}
