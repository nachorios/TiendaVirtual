package clases;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import basedatos.Archivos;
import caracteristicas.Caracteristica;
import clases.Categoria.CategoriaType;
import utils.UtilsClases;


public class Main {
	static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
    //	Consola consola = new Consola();
    //	consola.menuInicial();
    	InterfazConsola menu = new InterfazConsola();
		File users = new File ("usuario.dat");
		File publi = new File ("publicaciones.dat");
		Archivos archi = new Archivos();

    	Producto producto = new Producto();
    	Publicacion publicacion = new Publicacion();
    	boolean control = true;

		do {
			System.out.println(menu.menuPrincipal());
			try {
				switch (sc.nextInt()) {
					case 1:
						System.out.println(menu.menuSesion());
						inicioSesion(archi, users, menu);
						break;
					case 2:
						System.out.println(menu.menuRegistracion());
						try {
							crearUsuario(archi, users);
						} catch (IOException e) {
							System.out.println("Error al crear usuario");
						}
						break;
					case 0:
						control = false;
						break;
					default:
						System.out.println("Ingrese una opcion correcta");
						break;
				}
			}catch (InputMismatchException e) {
				System.out.println("Ingrese un numero");
				sc.nextLine();
			}
		}while (control);

	}


	public static void crearUsuario(Archivos archi, File file) throws IOException {

		ArrayList<Usuario> lista = new ArrayList<>();
		Usuario usuario;
		boolean flag = true;
		try {
			lista = archi.levantar(file);

		} catch (IOException e) {

			usuario = pedirDatos();

			lista.add(usuario);
			archi.guardar(lista, file);
			flag = false;
		}
		if (flag){
			usuario = pedirDatos();
			lista.add(usuario);
			archi.guardar(lista, file);
		}

	}


	public static void inicioSesion(Archivos archi, File file, InterfazConsola menu){
		try {
			System.out.println("Ingrese nombre usuario");
			Usuario usuario = archi.buscar(file, sc.next());
			if (usuario != null) {
				System.out.println("Ingrese contraseña");
				if(usuario.getContrasenia().equals(sc.next())){
					System.out.println("Bienvenido");
					System.out.println(menu.menuOpciones());
					switch (sc.nextInt()){
						case 1:
							System.out.println(archi.leer(file, usuario.getNombreUsuario()));
							break;

						default:
							System.out.println("Ingrese una opcion correcta");
							break;
					}
				}
				else {
					System.out.println("Contraseña incorrecta");
				}
			} else
				System.out.println("Usuario no encontrado");
		} catch (IOException a) {
			a.printStackTrace();
		}

	}


	public static Usuario pedirDatos(){
    	boolean control = true;
    	Usuario usuario = new Usuario();
		System.out.println("Ingrese nombre de usuario: ");
		usuario.setNombreUsuario(sc.next());
		System.out.println("Contraseña: ");
		usuario.setContrasenia(sc.next());
		System.out.println("Nombre: ");
		usuario.setNombre(sc.next());
		System.out.println("Apellido: ");
		usuario.setApellido(sc.next());
		System.out.println("Correo electronico: ");
		usuario.setCorreoElectronico(sc.next());
		System.out.println("Documento: ");
		usuario.setDocumento(sc.next());
		System.out.println("Edad: ");
		do {
			try {
				usuario.setEdad(sc.nextInt());
				control = false;
			} catch (InputMismatchException u) {
				System.out.println("Ingrese un numero");
				sc.nextLine();
			}
		}while (control);

		System.out.println("Direccion: ");
		usuario.setDireccion(sc.next());

    	return usuario;
	}

    public static void crearProducto() {
    	Producto producto = new Producto();
    	
    	System.out.println("Nombre del producto: ");
    	producto.setNombre(sc.next());
    	System.out.println("Descripcion:");
    	producto.setDescripcion(sc.next());
    	System.out.println("Elije la categoria de tu producto: ");
    	for (int i = 0; i < CategoriaType.values().length; i++) {
    		System.out.println(i+") "+ CategoriaType.values()[i]);
    	}
    	CategoriaType categoria = CategoriaType.values()[sc.nextInt()];
    	producto.setCategoria(new Categoria(categoria));
    	System.out.println("Ingrese las caracteristicas de tu producto:");
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
