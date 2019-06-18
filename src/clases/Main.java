package clases;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import basedatos.Archivos;


public class Main {
	static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
    	InterfazConsola menu = new InterfazConsola();
		File venta = new File ("vendedores.dat");
		File compra = new File("compradores.dat");
		File publi = new File ("publicaiones.dat");
		Archivos archi = new Archivos();
    	Vendedor vendedor = null;
    	Comprador comprador = null;
    	Producto producto = new Producto();
    	Publicacion publicacion = new Publicacion();
    	boolean control = true;
    	ArrayList<Vendedor> listaVendedor = new ArrayList<>();
		ArrayList<Comprador> listaComprador = new ArrayList<>();
		do {
			System.out.println(menu.menuPrincipal());
			switch (sc.nextInt()) {
				case 1:
					System.out.println(menu.menuSesion());
					switch (sc.nextInt()) {
						case 1:
							inicioVendedor(vendedor, archi, venta, listaVendedor, menu);
						break;
						case 2:
							inicioComprador(comprador, archi, compra, listaComprador, menu);
						break;
					}
				case 2:
					System.out.println(menu.menuRegistracion());
						switch (sc.nextInt()) {
							case 1:
								try {
									crearVendedor(archi, venta);
								} catch (IOException e) {
									e.printStackTrace();
								}
								break;
							case 2:
								try {
									crearComprador(archi, compra);
								} catch (IOException e) {
									e.printStackTrace();
								}
								break;
							default:
								System.out.println("Ingrese una opcion correcta");
								break;
						}
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

	public static void inicioVendedor(Vendedor vendedor, Archivos archi, File venta, ArrayList<Vendedor> listaVendedor, InterfazConsola menu){
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
				else {
					System.out.println("Contraseña incorrecta");
				}
			} else
				System.out.println("Usuario no encontrado");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void inicioComprador(Comprador comprador, Archivos archi, File compra, ArrayList<Comprador> listaComprador, InterfazConsola menu){
		try {
			System.out.println("Ingrese nombre usuario");
			comprador = archi.buscarComprador(compra, sc.next(), listaComprador);
			if (comprador != null) {
				System.out.println("Ingrese contraseña");
				if(comprador.getContrasenia().equals(sc.next())){
					System.out.println("Bienvenido");
					System.out.println(menu.menuOpcionesVendedor());
					switch (sc.nextInt()){
						case 1:
							System.out.println(archi.leerComprador(compra, comprador.getNombreUsuario(), listaComprador));
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
		vendedor.setEdad(sc.nextInt());
		System.out.println("Direccion: ");
		vendedor.setDireccion(sc.next());

    	return vendedor;
	}

	public static Comprador pedirDatosC(){
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
		comprador.setEdad(sc.nextInt());
		System.out.println("Direccion: ");
		comprador.setDireccion(sc.next());

		return comprador;
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
    	producto.setDescripcion(sc.next());
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
