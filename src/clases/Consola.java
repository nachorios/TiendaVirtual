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
import excepciones.PublicacionException;
import utils.UtilsClases;

public class Consola {
	Scanner sc = new Scanner(System.in);
	File venta = new File ("usuarios.dat");
	File publi = new File ("publicaiones.dat");
	Archivos archi = new Archivos();
	Publicacion publicacion = new Publicacion();
	public void ejecutarWoshConsola() {
		menuInicial();
	}

	public void menuInicial() {
	    cargarPublicacion();
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
			 		Usuario usuario = ingresarUsuario();
			 	    if (usuario != null) {
			 			menuCompradorVendedor(usuario);
			 		}
			 		break;
			 	case 2:
					try {
						crearUsuario(archi, venta);
					} catch (IOException e) {
						System.out.println("Error al crear usuario");
					}
					break;
			 	case 0:
			 		control = false;
			 		break;
			 	default:
			 }
		 }while(control);
	}
	
	public void menuCompradorVendedor(Usuario usuario) {
		boolean control = true;
		do {
			System.out.println("Menu Principal");
			System.out.println("1. Entrar como Comprador");
			System.out.println("2. Entrar como Vendedor");
			System.out.println("0. Volver al Menu inicial");
			System.out.print("Elije opcion: ");
			int opcion = sc.nextInt();
			switch(opcion) {
				case 1:
					menuComprador(usuario);
					break;
				case 2:
					menuVendedor(usuario);
					break;
				case 0:
					control = false;
					break;
				default:
					System.out.println("Opcion incorrecta.");
			}
		}while(control);
	}
	
	public void menuComprador(Usuario comprador) {
		boolean control = true;
		do{
			System.out.println("Menu Comprador");
			System.out.println("Aqui podras buscar productos, añadirlos a tu cesta y comprarlos!");
			System.out.println("1. Buscar producto");
			System.out.println("2. Cesta de productos");
			System.out.println("3. Realizar compra");
			System.out.println("0. Volver al Menu Principal");
			System.out.print("Elije opcion: ");
			int opcion = sc.nextInt();
			switch(opcion) {
				case 1:
					menuBuscarProductos(comprador);
					break;
				case 2:
					menuCestaProductos(comprador);
					break;
				case 3:
					realizarCompra(comprador);
					break;
				case 0:
					control = false;
					break;
				default:
					System.out.println("Opcion incorrecta.");
			}
		}while(control);
	}
	
	public void realizarCompra(Usuario comprador) {
		ArrayList<Producto> productos = comprador.getCestaCompras().obtenerProductos();
		if (productos.size() > 0) {
			boolean control;
			do {
				for (int i = 0; i < productos.size(); i++) {
					System.out.println(i+1+") "+ productos.toString());
				}
				System.out.println("Tu saldo: "+ comprador.getSaldo());
				System.out.println("Preio de la compra: "+ comprador.getCestaCompras().getPrecioTotal());
				System.out.println("Deseas realizar la compra? S/N");
				control = false;
				switch(sc.next()) {
					case "s":
						System.out.println(publicacion.realizarIntercambio(comprador));
						break;
					case "n":
						System.out.println("Has cancelado la compra.");
						break;
					default:
						control = true;
				}
			} while(control);
		} else {
			System.out.println("No tienes productos en tu cesta de compras.");
		}
		
	}
	
	public void menuCestaProductos(Usuario comprador) {
		boolean control = true;
		do{
			System.out.println("Menu Cesta de Compras");
			System.out.println("1. Ver Cesta de compras");
			System.out.println("2. Quitar producto de la cesta");
			System.out.println("3. Vaciar cesta de compras");
			System.out.println("0. Volver al Menu Principal");
			System.out.print("Elije opcion: ");
			int opcion = sc.nextInt();
			switch(opcion) {
				case 1:
					verCestaCompras(comprador);
					break;
				case 2:
					quitarProductoCestaCompras(comprador);
					break;
				case 3:
					comprador.getCestaCompras().vaciarCesta();
					System.out.println("Has vaciado la cesta de compras!");
					break;
				case 0:
					control = false;
					break;
				default:
					System.out.println("Opcion incorrecta.");
			}
		}while(control);
	}
	
	public void verCestaCompras(Usuario comprador) {
		ArrayList<Producto> productos = comprador.getCestaCompras().obtenerProductos();
		for (int i = 0; i < productos.size(); i++) {
			System.out.println(i+1+") "+ productos.toString());
		}
	}
	
	public void quitarProductoCestaCompras(Usuario comprador) {
		ArrayList<Producto> productos = comprador.getCestaCompras().obtenerProductos();
		if (productos.size()>0) {
			Producto prodAquitar = null;
			for (int i = 0; i < productos.size(); i++) {
				System.out.println(i+1+") "+ productos.get(i).getNombre());
			}
			System.out.print("Elige el producto que desea quitar: ");
			try{
				prodAquitar = productos.get(sc.nextInt()-1);
			}catch(IndexOutOfBoundsException e) {
				System.out.println("Ingrese un producto valido");
			}
			
			if (prodAquitar == null) {
				System.out.println("No se ha podido quitar el producto");
			} else {
				comprador.getCestaCompras().quitarProductoEnCesta(prodAquitar);
				System.out.println("El producto se ha quitado correctamente!");
			}
		}	
	}
	
	
	public void menuBuscarProductos(Usuario comprador) {
		boolean control = true;
		do{
			System.out.println("¿Como deseas buscar el producto?");
			System.out.println("1. Buscar por categoria");
			System.out.println("2. Buscar por nombre");
			System.out.println("3. Buscar por nombre del vendedor");
			System.out.println("4. Busqueda aleatoria");
			System.out.println("5. Ver productos sugeridos");
			System.out.println("0. Volver al Menu Comprador");
			System.out.print("Elije opcion: ");
			int opcion = sc.nextInt();
			switch(opcion) {
				case 1:
					System.out.println("Elije la categoria que desea buscar: ");
			    	for (int i = 0; i < CategoriaType.values().length; i++) {
			    		System.out.println(i+") "+ CategoriaType.values()[i]);
			    	}
					System.out.print("Elije opcion: ");
					CategoriaType categoria = CategoriaType.values()[sc.nextInt()];
					
					buscarProducto(comprador,publicacion.buscarProductosPorCategoria(categoria, comprador.getNombreUsuario()));
					break;
				case 2:
					System.out.print("Ingrese el nombre del producto deseado: ");
					buscarProducto(comprador,publicacion.buscarProductosPorNombre(sc.next(), comprador.getNombreUsuario()));
					break;
				case 3:
					buscarProducto(comprador,publicacion.getArrayListProductosPublicitadosDeUnUsuario(sc.next()));
					break;
				case 4:
					buscarProducto(comprador,publicacion.getArrayListDeProductosVenta());
					break;
				case 5:
					buscarProducto(comprador,publicacion.getArrayListDeProductosSugeridos(comprador.getNombreUsuario()));
					break;
				case 0:
					control = false;
					break;
				default:
					System.out.println("Opcion incorrecta.");
			}
		}while(control);
	}
	
	public void buscarProducto(Usuario comprador, ArrayList<Producto> productosEnVenta) {
		for (int i = 0; i < productosEnVenta.size(); i++) {
			if (!productosEnVenta.get(i).getVendedor().equals(comprador.getNombreUsuario())
					&& !productosEnVenta.get(i).isEnVenta()) {
				System.out.println(i+1+")"+productosEnVenta.get(i));
			}
		}
		System.out.println("0) Para salir");
		System.out.print("Elije el producto que deseas añadir a la cesta: ");
		int opcion = sc.nextInt();
		Producto prodElegido = null;
		if (opcion != 0) {
			prodElegido = productosEnVenta.get(opcion-1);
			
			comprador.agregarProductoCesta(prodElegido);
		} 
	}
	
	public void menuVendedor(Usuario vendedor) {
	    boolean control = true;
		do {
			System.out.println("Menu Vendedor");
			System.out.println("Aqui podras crear, vender, publicitar y modificar tu producto!");
			System.out.println("1. Crear producto");
			System.out.println("2. Publicar a la venta un producto");
			System.out.println("3. Modificar un producto");
			System.out.println("4. Publicitar un producto");
			System.out.println("5. Ver informacion de la cuenta");
			System.out.println("6. Ver productos publicados a la venta");
			System.out.println("7. Ver productos publicitados");
			System.out.println("8. Quitar un producto publicado");
			System.out.println("0. Volver al Menu Principal");
			System.out.print("Elije opcion: ");
			int opcion = sc.nextInt();
			switch(opcion) {
				case 1:
					vendedor.agregarProductoLista(crearProducto(vendedor.getNombre()));
					break;
				case 2:
					publicarEnVentaProducto(vendedor);
					break;
				case 3:
					menuEdicionProducto(vendedor);
					break;
				case 4:
					publicitarProducto(vendedor);
					break;
				case 5:
					verInformacionCuenta(vendedor);
					break;
				case 6:
					verProductosEnVenta(vendedor);
					break;
				case 7:
					verProductosPublicitados(vendedor);
					break;
				case 8:
					quitarProductoEnVenta(vendedor);
					break;
				case 0:
					control = false;
					break;
				default:
					System.out.println("Opcion incorrecta.");
			}
		}while(control);
	}
	
	public void quitarProductoEnVenta(Usuario vendedor) {
		Producto productoSeleccionado = productoSeleccionado = elegirProducto(vendedor, false);
		if (productoSeleccionado != null) {
				Producto productoVendedor = vendedor.getLista().get(vendedor.getLista().indexOf(productoSeleccionado));
				publicacion.quitarProductoEnVenta(productoVendedor);
				System.out.println("Has quitado tu producto de la venta.");
		}
	}
	
	public void verProductosEnVenta(Usuario vendedor) {
		for(Producto p : publicacion.getProductosDeUnUsuario(vendedor.getNombreUsuario())) {
			System.out.println(p);
		}
	}
	
	public void verProductosPublicitados(Usuario vendedor) {
		for(Producto p : publicacion.getArrayListProductosPublicitadosDeUnUsuario(vendedor.getNombreUsuario())) {
			System.out.println(p);
		}
	}
	
	public void verInformacionCuenta(Usuario vendedor) {
		System.out.println(vendedor.toString());
	}
	
	public void publicarEnVentaProducto(Usuario vendedor) {
		Producto productoSeleccionado = productoSeleccionado = elegirProducto(vendedor, true);
		if (productoSeleccionado != null) {
			try {
				Producto productoVendedor = vendedor.getLista().get(vendedor.getLista().indexOf(productoSeleccionado));
				publicacion.publicarProducto(vendedor.getNombreUsuario(), productoVendedor);
				System.out.println("Has colocado a la venta tu producto.");
				archi.guardar(publicacion, publi);
			} catch (PublicacionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
                e.printStackTrace();
            }
        }
		
	}
	
	public void publicitarProducto(Usuario vendedor) {
		Producto productoSeleccionado = productoSeleccionado = elegirProducto(vendedor, true);
		if (productoSeleccionado != null) {
			int opcion;
			do {
				System.out.println("Elije la opcion de publicidad que desees:");
				System.out.println("Opcion 1) 50$ duracion 2 compras del producto.");
				System.out.println("Opcion 2) 100$ duracion 4 compras del producto.");
				System.out.println("Opcion 3) 250$ duracion 8 compras del producto.");
				System.out.println("Opcion 4) 500$ duracion 15 compras del producto.");
				System.out.print("Elije opcion: ");
				opcion = sc.nextInt();
				
			}while(opcion <1 && opcion >4);
			int cantPublicidad = 0;
			switch(opcion) {
				case 1:
					cantPublicidad =2;
					break;
				case 2:
					cantPublicidad =4;
					break;
				case 3:
					cantPublicidad =8;
					break;
				case 4:
					cantPublicidad =15;
					break;
			}
			try {
				publicacion.publicitarProducto(vendedor.getNombre(), productoSeleccionado, cantPublicidad);
				archi.guardar(publicacion, publi);
			} catch (PublicacionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
                e.printStackTrace();
            }
        }
		
	}
	
	public void menuEdicionProducto(Usuario vendedor) {
		Producto productoSeleccionado = null;
		boolean control = true;
		do {
			System.out.println("Menu Edicion");
			System.out.println("Aqui podras editar cada atributo de tu producto que no este en venta.");
			if (productoSeleccionado == null)
				System.out.println("1. Seleccionar producto - Aun no has seleccionado un producto");
			else
				System.out.println("1. Volver a seleccionar producto - Producto Seleccionado: "+ productoSeleccionado.getNombre());
			System.out.println("2. Editar nombre");
			System.out.println("3. Editar precio");
			System.out.println("4. Editar el Stock");
			System.out.println("5. Editar descripcion");
			System.out.println("6. Cambiar la categoria");
			System.out.println("7. Cambiar caracteristicas");
			System.out.println("8. Ver informacion del producto seleccionado");
			System.out.println("0. Volver al menu vendedor");
			System.out.print("Elije opcion: ");
			int opcion = sc.nextInt();
			switch(opcion) {
				case 1:
					productoSeleccionado = elegirProducto(vendedor, false);
					break;
				case 2:
					if (productoSeleccionado(productoSeleccionado))
						editarNombre(vendedor, productoSeleccionado);
					break;
				case 3:
					if (productoSeleccionado(productoSeleccionado))
						editarPrecio(vendedor, productoSeleccionado);
					break;
				case 4:
					if (productoSeleccionado(productoSeleccionado))
						aumentarStock(vendedor, productoSeleccionado);
					break;
				case 5:
					if (productoSeleccionado(productoSeleccionado))
						editarDescripcion(vendedor, productoSeleccionado);
					break;
				case 6:
					if (productoSeleccionado(productoSeleccionado))
						editarCategoria(vendedor, productoSeleccionado);
					break;
				case 7:
					if (productoSeleccionado(productoSeleccionado))
						editarCaracteristicas(vendedor, productoSeleccionado);
					break;
				case 8:
					if (productoSeleccionado(productoSeleccionado))
						System.out.println(productoSeleccionado.toString());
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
	public boolean productoSeleccionado(Producto p) {
		boolean flag = false;
		if (p == null)
			System.out.println("Primero debes seleccionar un producto.");
		else
			flag = true;
		return flag;
	}
	
	public void editarNombre(Usuario vendedor, Producto producto) {
		System.out.println("Nombre anterior:"+ producto.getNombre());
		System.out.print("Nombre deseado:");
		String nuevoNombre = sc.next();
		producto.setNombre(nuevoNombre);
		System.out.println("ï¿½Nombre modificado correctamente!");
	}
	public void editarPrecio(Usuario vendedor, Producto producto) {
		System.out.println("precio anterior:"+ producto.getPrecio());
		System.out.print("precio deseado:");
		float nuevoPrecio = sc.nextFloat();
		producto.setPrecio(nuevoPrecio);
		System.out.println("ï¿½Precio modificado correctamente!");
	}
	public void aumentarStock(Usuario vendedor, Producto producto) {
		System.out.println("Stock anterior:"+ producto.getCantidad());
		System.out.print("Stock deseado:");
		int nuevoStock = sc.nextInt();
		producto.setCantidad(nuevoStock);
		System.out.println("ï¿½Stock modificado correctamente!");
	}
	public void editarDescripcion(Usuario vendedor, Producto producto) {
		System.out.println("Descripcion anterior:"+ producto.getDescripcion());
		System.out.print("Descripcion deseada:");
		String nuevaDescripcion = sc.next();
		producto.setDescripcion(nuevaDescripcion);
		System.out.println("ï¿½Descripcion modificada correctamente!");
	}
	public void editarCategoria(Usuario vendedor, Producto producto) {
		System.out.println("Categoria anterior:"+ producto.getCategoria());
		System.out.println("Elije la nueva categoria de tu producto: ");
    	for (int i = 0; i < CategoriaType.values().length; i++) {
    		System.out.println(i+") "+ CategoriaType.values()[i]);
    	}
    	CategoriaType nuevaCategoria = CategoriaType.values()[sc.nextInt()];
    	producto.setCategoria(new Categoria(nuevaCategoria));
		System.out.println("ï¿½Categoria modificada correctamente!");
		System.out.println("Debido a que has cambiado de categoria, deberï¿½s colocar nuevas caracteristicas.");
		editarCaracteristicas(vendedor, producto);
	}
	public void editarCaracteristicas(Usuario vendedor, Producto producto) {
		System.out.println("Ingrese las nuevas caracteristicas de tu producto:");
		CategoriaType categoria = producto.getCategoria().getTipo();
		String nombreClase = categoria.name().toLowerCase();
    	nombreClase = nombreClase.substring(0, 1).toUpperCase() + nombreClase.substring(1);
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
    	    		} else if (UtilsClases.objectIsFloat(atributo.getType())) {
    	    			attr.add(sc.nextFloat());
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
	
	public Producto elegirProducto(Usuario vendedor, boolean enVenta) {
		ArrayList<Producto> productos = vendedor.getLista();
		Producto productoElegido = null;
		int cantProductosEditables = 0;
		ArrayList<Integer> productosAccesibles = new ArrayList<Integer>();
		int opcion = -1;
		do {
			try{
				for (int i = 0; i < productos.size(); i++) {
					if(productos.get(i).isEnVenta() == enVenta) {
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
					System.out.println("Ahora mismo no tienes productos disponibles");
					break;
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
	

	public Usuario ingresarUsuario() {
		Usuario vendedor = null;
		try {
			System.out.println("Ingrese nombre usuario");
			vendedor = archi.buscar(venta, sc.next());
			if (vendedor != null) {
				System.out.println("Ingrese contraseÃ±a");
				if(vendedor.getContrasenia().equals(sc.next())){
					System.out.println("Bienvenido");
				}
				else {
					System.out.println("ContraseÃ±a incorrecta");
				}
			} else
				System.out.println("Usuario no encontrado");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return vendedor;
	}

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
    	String nombreClase = categoria.name().toLowerCase();
    	nombreClase = nombreClase.substring(0, 1).toUpperCase() + nombreClase.substring(1);
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
    	    		} else if (UtilsClases.objectIsFloat(atributo.getType())) {
    	    			attr.add(sc.nextFloat());
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

	public void crearUsuario(Archivos archi, File file) throws IOException {

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

	public Usuario pedirDatos(){
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
	
	public void limpiarConsola() {
		for(int i = 0; i < 50; i++) {
			System.out.println("");
		}
	}

	public void cargarPublicacion(){
        try {
            publicacion = archi.levantarPubli(publi);
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }
    }
}
