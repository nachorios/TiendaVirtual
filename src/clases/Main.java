package clases;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;

import basedatos.Archivos;
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
    	InterfazConsola menu = new InterfazConsola();
		File venta = new File ("vendedores.dat");
		File compra = new File("compradores.dat");
		File publi = new File ("publicaiones.dat");
		Archivos archi = new Archivos();
    	Vendedor vendedor = null;
    	Comprador comprador = new Comprador();
    	Producto producto = new Producto();
    	Publicacion publicacion = new Publicacion();
    	String control = "s";
    	ArrayList<Vendedor> listaVendedor = new ArrayList<>();
    	do {
			System.out.println(menu.menuPrincipal());
			switch (sc.nextInt()) {
				case 1:
					System.out.println(menu.menuSesion());
					switch (sc.nextInt()) {
						case 1:
							try {
								System.out.println("Ingrese nombre usuario");
								vendedor = archi.buscarVendedor(venta, sc.next(), listaVendedor);
								if (vendedor != null) {
									System.out.println("Ingrese contraseña");
									if(vendedor.getContrasenia().equals(sc.next())){
										System.out.println("Bienvenido");
										System.out.println(menu.menuOpcionesVendedor());
										switch (sc.nextInt()){
											case 1:
												System.out.println(archi.leerVendedor(venta, vendedor.getNombreUsuario(), listaVendedor));
												break;

											default:
												System.out.println("Ingrese una opcion correcta");
												break;
										}
									}
									else
										System.out.println("Incorrecto");
								} else
									System.out.println("Usuario no encontrado");
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						vendedor = null;
						break;

					case 2:
						System.out.println(menu.menuRegistracion());
						switch (sc.nextInt()) {
							case 1:
								//crearVendedor(vendedor, archi, venta);
								try {
									crearVendedor(vendedor, archi, venta, listaVendedor);
								} catch (IOException e) {
									e.printStackTrace();
								}
								break;
							case 2:
								//crearComprador(comprador, archi, compra);
								break;
							default:
								System.out.println("Ingrese una opcion correcta");
								break;
						}
					break;

					case 0:
						control = "n";
					break;

					default:
						System.out.println("Ingrese una opcion correcta");
					break;
			}
			System.out.println("Presione s para continuar");
			//control = sc.next();
		}while (control.equals(sc.next()));

	}



	public static void crearVendedor(Vendedor vendedor, Archivos archi, File file, ArrayList<Vendedor> listaVende) throws IOException {

		listaVende = archi.levantarVendedor(file);
		System.out.println("Ingrese nombre de usuario");
		vendedor.setNombreUsuario(sc.next());
		System.out.println("Ingrese contraseña");
		vendedor.setContrasenia(sc.next());
		listaVende.add(vendedor);
		archi.guardar(listaVende, file);


	}

    /*
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
    */
}
