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

public class Consola {
	Scanner sc = new Scanner(System.in);
	InterfazConsola menu = new InterfazConsola();
	File venta = new File ("vendedores.dat");
	File compra = new File("compradores.dat");
	File publi = new File ("publicaiones.dat");
	Archivos archi = new Archivos();

	Publicacion publicacion = new Publicacion();
	Usuario usuario;
	public void ejecutarWoshConsola() {
		
	}

	public void menuInicial() {
		 boolean control = true;
		 do {
			 System.out.println("Bienvenido a Wosh!");
			 System.out.println("1. Iniciar sesion.");
			 System.out.println("2. Registrarse.");
			 System.out.println("0. Salir del sistema.");
			 System.out.print("Elije opcion: ");
			 int opcion = sc.nextInt();
			 switch(opcion) {
			 	case 1:
			 	//	if (ingresarUsuario()) {
			 			
			 	//	}
			 		break;
			 	case 2:
			 		//CREAR USUARIO
			 		break;
			 	case 0:
			 		break;
			 	default:
			 }
		 }while(control);
	}
	
	public void menuCompradorVendedor() {
		boolean control = true;
		do {
			System.out.println("Bienvenido a Wosh!");
			System.out.println("1. Entrar como Comprador");
			System.out.println("2. Entrar como Vendedor");
			System.out.println("0. Volver al Menu inicial");
			System.out.print("Elije opcion: ");
			int opcion = sc.nextInt();
			switch(opcion) {
				case 1:
					
					break;
				case 2:
					menuVendedor();
					break;
				case 0:
					
					break;
				default:
						
						
			}
		}while(control);
	}
	
	public void menuVendedor() {
		Usuario vendedor = new Usuario(); //= CARGAR DATOS VENDEDOR SEGUN EL NOMBRE DE USUARIO DE EL OBJETO 'usuario';
		boolean control = true;
		do {
			System.out.println("Menu Vendedor");
			System.out.println("Aqui podr�s crear, vender, publicitar y modificar tu producto!");
			System.out.println("1. Crear producto");
			System.out.println("2. Vender producto");
			System.out.println("3. Modificar producto");
			System.out.println("4. Publicitar producto");
			System.out.println("5. Ver informacion de la cuenta");
			System.out.println("0. Volver al Menu Principal");
			System.out.print("Elije opcion: ");
			int opcion = sc.nextInt();
			switch(opcion) {
				case 1:
					vendedor.agregarProductoLista(crearProducto(vendedor.getNombre()));
					break;
				case 2:
					
					break;
				case 3:
					menuEdicionProducto(vendedor);
					break;
				case 4:
					
					break;
				case 5:
					
					break;
				case 0:
					
					break;
				default:
					System.out.println("Opcion incorrecta.");
						
			}
		}while(control);
	}
	
	public void menuEdicionProducto(Usuario vendedor) {
		Producto productoSeleccionado = null;
		boolean control = true;
		do {
			System.out.println("Menu Edicion");
			System.out.println("Aqui podr�s editar cada atributo de tu producto que no este en venta.");
			if (productoSeleccionado == null)
				System.out.println("1. Seleccionar producto");
			else
				System.out.println("1. Volver a seleccionar producto - Producto Seleccionado: "+ productoSeleccionado.getNombre());
			System.out.println("2. Editar nombre");
			System.out.println("3. Editar precio");
			System.out.println("4. Aumentar el Stock");
			System.out.println("5. Editar descripcion");
			System.out.println("6. Cambiar la categoria");
			System.out.println("7. Cambiar caracteristicas");
			System.out.println("0. Volver al menu vendedor");
			System.out.print("Elije opcion: ");
			int opcion = sc.nextInt();
			switch(opcion) {
				case 1:
					productoSeleccionado = elegirProducto(vendedor);
					break;
				case 2:
					editarNombre(vendedor, productoSeleccionado);
					break;
				case 3:
					editarPrecio(vendedor, productoSeleccionado);
					break;
				case 4:
					aumentarStock(vendedor, productoSeleccionado);
					break;
				case 5:
					editarDescripcion(vendedor, productoSeleccionado);
					break;
				case 6:
					editarCategoria(vendedor, productoSeleccionado);
					break;
				case 7:
					editarCaracteristicas(vendedor, productoSeleccionado);
					break;
				case 0:
					control = false;
					break;
				default:
					System.out.println("Opcion incorrecta.");
						
			}
		}while(control);
	}
	///INI-FUNCIONES EDITAR PRODUCTO
	public void editarNombre(Usuario vendedor, Producto producto) {
		System.out.println("Nombre anterior:"+ producto.getNombre());
		System.out.print("Nombre deseado:");
		String nuevoNombre = sc.next();
		producto.setNombre(nuevoNombre);
		System.out.println("�Nombre modificado correctamente!");
	}
	public void editarPrecio(Usuario vendedor, Producto producto) {
		System.out.println("precio anterior:"+ producto.getPrecio());
		System.out.print("precio deseado:");
		float nuevoPrecio = sc.nextFloat();
		producto.setPrecio(nuevoPrecio);
		System.out.println("�Precio modificado correctamente!");
	}
	public void aumentarStock(Usuario vendedor, Producto producto) {
		System.out.println("Stock anterior:"+ producto.getCantidad());
		System.out.print("Stock deseado:");
		int nuevoStock = sc.nextInt();
		producto.setCantidad(nuevoStock);
		System.out.println("�Stock modificado correctamente!");
	}
	public void editarDescripcion(Usuario vendedor, Producto producto) {
		System.out.println("Descripcion anterior:"+ producto.getDescripcion());
		System.out.print("Descripcion deseada:");
		String nuevaDescripcion = sc.nextLine();
		producto.setDescripcion(nuevaDescripcion);
		System.out.println("�Descripcion modificada correctamente!");
	}
	public void editarCategoria(Usuario vendedor, Producto producto) {
		System.out.println("Categoria anterior:"+ producto.getCategoria());
		System.out.println("Elije la nueva categoria de tu producto: ");
    	for (int i = 0; i < CategoriaType.values().length; i++) {
    		System.out.println(i+") "+ CategoriaType.values()[i]);
    	}
    	CategoriaType nuevaCategoria = CategoriaType.values()[sc.nextInt()];
    	producto.setCategoria(new Categoria(nuevaCategoria));
		System.out.println("�Categoria modificada correctamente!");
		System.out.println("Debido a que has cambiado de categoria, deber�s colocar nuevas caracteristicas.");
		editarCaracteristicas(vendedor, producto);
	}
	public void editarCaracteristicas(Usuario vendedor, Producto producto) {
		System.out.println("Ingrese las nuevas caracteristicas de tu producto:");
		CategoriaType categoria = producto.getCategoria().getTipo();
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
	}

	public Producto elegirProducto(Usuario vendedor) {
		ArrayList<Producto> productos = vendedor.getLista();
		Producto productoElegido = null;
		int cantProductosEditables = 0;
		ArrayList<Integer> productosAccesibles = new ArrayList<Integer>();
		int opcion = -1;
		do {
			try{
				for (int i = 0; i < productos.size(); i++) {
					if(!productos.get(i).isEnVenta()) {
						System.out.println(i + ") " + productos.get(i).getNombre());
						cantProductosEditables++;
						productosAccesibles.add(i);
					}
				}
				if (cantProductosEditables!=0) {
					System.out.print("Escriba la ID del producto que desea editar: ");
					opcion = sc.nextInt();
					productoElegido = productos.get(opcion);
				} else {
					System.out.println("No tienes productos o todos se encuentran en venta.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Ingrese un numero");
				sc.nextLine();
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Ingrese el numero del producto que desea seleccionar.");
				sc.nextLine();
			} 
		}while(!productosAccesibles.contains(opcion) || opcion == -1);
		
		
		return productoElegido;
	}
	///FIN-FUNCIONES EDITAR PRODUCTO
	
	/*
	public boolean ingresarUsuario() {
		boolean flag = false;
		try {
			System.out.println("Ingrese nombre usuario");
			Usuario vendedor = archi.buscar(venta, sc.next());
			if (vendedor != null) {
				System.out.println("Ingrese contraseña");
				if(vendedor.getContrasenia().equals(sc.next())){
					System.out.println("Bienvenido");
					System.out.println(menu.menuOpciones());
					switch (sc.nextInt()){
						case 1:
							System.out.println(archi.leer(venta, vendedor.getNombreUsuario()));
							break;

						default:
							System.out.println("Ingrese una opcion correcta");
							break;
					}
				}
				else {
					System.out.println("Contraseña incorrecta");
					flag = true;
				}
			} else
				System.out.println("Usuario no encontrado");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	public void crearVendedor() throws IOException {

		ArrayList<Usuario> listaVende = new ArrayList<>();
		Vendedor vendedor;
		boolean flag = true;
		try {
			listaVende = archi.levantar(venta);

		} catch (IOException e) {

			vendedor = crearCuenta();

			listaVende.add(vendedor);
			archi.guardar(listaVende, venta);
			flag = false;
		}
		if (flag){
			vendedor = crearCuenta();
			listaVende.add(vendedor);
			archi.guardar(listaVende, venta);
		}

	}*/
	
	public Producto crearProducto(String nombre) {
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
    	producto.setVendedor(nombre);
    	System.out.println("Datos del producto creado: ");
    	System.out.println(producto.toString());
    	//editar?
    	return producto;
    }
	/*
	public Vendedor crearCuenta() {

    	boolean control = true;
    	Vendedor vendedor = new Vendedor();
		System.out.println("Ingrese nombre de usuario: ");
		vendedor.setNombreUsuario(sc.next());
		System.out.println("Ingrese la Contrase�a: ");
		vendedor.setContrasenia(sc.next());
		System.out.println("Ingrese tu Nombre: ");
		vendedor.setNombre(sc.next());
		System.out.println("Ingrese tu Apellido: ");
		vendedor.setApellido(sc.next());
		System.out.println("Ingrese tu Correo electronico: ");
		vendedor.setCorreoElectronico(sc.next());
		System.out.println("Ingrese tu Documento: ");
		vendedor.setDocumento(sc.next());
		System.out.println("Ingrese tu Edad: ");
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
	
	}*/
}
