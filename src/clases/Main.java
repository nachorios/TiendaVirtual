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
    	InterfazConsola menu = new InterfazConsola();
		File venta = new File ("vendedores.dat");
		File compra = new File("compradores.dat");
		File publi = new File ("publicaiones.dat");
		Archivos archi = new Archivos();

    	Producto producto = new Producto();
    	Publicacion publicacion = new Publicacion();
    	boolean control = true;

		do {
			System.out.println(menu.menuPrincipal());

			switch (sc.nextInt()) {
				case 1:
					System.out.println(menu.menuSesion());
					do {
						try {
							switch (sc.nextInt()) {
								case 1:
									inicioVendedor(archi, venta, menu);
									break;
								case 2:
									inicioComprador(archi, compra, menu);
									break;
								case 0:
									control = false;
									break;
								default:
									System.out.println("Ingrese una opcion correcta");
							}
						}catch (InputMismatchException e){
							System.out.println("Ingrese un numero");
							sc.nextLine();
						}
					}while (control);
					control = true;
				break;
				case 2:
					System.out.println(menu.menuRegistracion());
						do {
							try {
								switch (sc.nextInt()) {
									case 1:
										try {
											crearVendedor(archi, venta);
										} catch (IOException e) {
											System.out.println("Error al crear usuario");
										}
										break;
									case 2:
										try {
											crearComprador(archi, compra);
										} catch (IOException e) {
											System.out.println("Error al crear usuario");
										}
										break;
									case 0:
										control = false;
									default:
										System.out.println("Ingrese una opcion correcta");
										break;
								}
							}catch (InputMismatchException a){
								System.out.println("Ingrese un numero");
								sc.nextLine();
							}
						}while (control);
						control = true;
					break;
				case 0:
						control = false;
				break;

					default:
						System.out.println("Ingrese una opcion correcta");
					break;
			}
		}while (control);

	}



	public static void crearVendedor(Archivos archi, File file) throws IOException {

		ArrayList<Vendedor> listaVende = new ArrayList<>();
		Vendedor vendedor;
		boolean flag = true;
		try {
			listaVende = archi.levantarVendedor(file);

		} catch (IOException e) {

			vendedor = pedirDatosV();

			listaVende.add(vendedor);
			archi.guardar(listaVende, file);
			flag = false;
		}
		if (flag){
			vendedor = pedirDatosV();
			listaVende.add(vendedor);
			archi.guardar(listaVende, file);
		}

	}

	public static void crearComprador(Archivos archi, File file) throws IOException {

		ArrayList<Comprador> listaCompra = new ArrayList<>();
		Comprador comprador;
		boolean flag = true;
		try {
			listaCompra = archi.levantarComprador(file);

		} catch (IOException e) {

			comprador =  pedirDatosC();
			listaCompra.add(comprador);
			archi.guardarC(listaCompra, file);
			flag = false;
		}
		if (flag){
			comprador =  pedirDatosC();
			listaCompra.add(comprador);
			archi.guardarC(listaCompra, file);
		}

	}

	public static void inicioVendedor(Archivos archi, File venta, InterfazConsola menu){
		try {
			System.out.println("Ingrese nombre usuario");
			Vendedor vendedor = archi.buscarVendedor(venta, sc.next());
			if (vendedor != null) {
				System.out.println("Ingrese contraseña");
				if(vendedor.getContrasenia().equals(sc.next())){
					System.out.println("Bienvenido");
					System.out.println(menu.menuOpcionesVendedor());
					switch (sc.nextInt()){
						case 1:
							System.out.println(archi.leerVendedor(venta, vendedor.getNombreUsuario()));
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
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void inicioComprador(Archivos archi, File compra, InterfazConsola menu){
		try {
			System.out.println("Ingrese nombre usuario");
			Comprador comprador = archi.buscarComprador(compra, sc.next());
			if (comprador != null) {
				System.out.println("Ingrese contraseña");
				if(comprador.getContrasenia().equals(sc.next())){
					System.out.println("Bienvenido");
					System.out.println(menu.menuOpcionesComprador());
					switch (sc.nextInt()){
						case 1:
							System.out.println(archi.leerComprador(compra, comprador.getNombreUsuario()));
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
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Vendedor pedirDatosV(){
    	boolean control = true;
    	Vendedor vendedor = new Vendedor();
		System.out.println("Ingrese nombre de usuario: ");
		vendedor.setNombreUsuario(sc.next());
		System.out.println("Contraseña: ");
		vendedor.setContrasenia(sc.next());
		System.out.println("Nombre: ");
		vendedor.setNombre(sc.next());
		System.out.println("Apellido: ");
		vendedor.setApellido(sc.next());
		System.out.println("Correo electronico: ");
		vendedor.setCorreoElectronico(sc.next());
		System.out.println("Documento: ");
		vendedor.setDocumento(sc.next());
		System.out.println("Edad: ");
		do {
			try {
				vendedor.setEdad(sc.nextInt());
				control = false;
			} catch (InputMismatchException e) {
				System.out.println("Ingrese un numero");
				sc.nextLine();
			}
		}while (control);

		System.out.println("Direccion: ");
		vendedor.setDireccion(sc.next());

    	return vendedor;
	}

	public static Comprador pedirDatosC(){
    	boolean control = true;
		Comprador comprador = new Comprador();
		System.out.println("Ingrese nombre de usuario: ");
		comprador.setNombreUsuario(sc.next());
		System.out.println("Contraseña: ");
		comprador.setContrasenia(sc.next());
		System.out.println("Nombre: ");
		comprador.setNombre(sc.next());
		System.out.println("Apellido: ");
		comprador.setApellido(sc.next());
		System.out.println("Correo electronico: ");
		comprador.setCorreoElectronico(sc.next());
		System.out.println("Documento: ");
		comprador.setDocumento(sc.next());
		System.out.println("Edad: ");
		do {
			try {
				comprador.setEdad(sc.nextInt());
				control = false;
			} catch (InputMismatchException e) {
				System.out.println("Ingrese un numero");
				sc.nextLine();
			}
		}while (control);
		System.out.println("Direccion: ");
		comprador.setDireccion(sc.next());

		return comprador;
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
