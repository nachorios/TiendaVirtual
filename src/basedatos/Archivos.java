package basedatos;

import clases.Producto;
import clases.Publicacion;
import clases.Usuario;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.util.ArrayList;

/**
 * Esta clase guarda los datos de los vendedores y compradores en un archivo
 * @author Nacho
 */
public class Archivos
{
    /**
     * guarda los datos de un usuario a un json array que luego lo pasa a un string
     * @param usuario un arraylist que contiene los datos del usuario
     * @param file el archivo donde se guarda los datos
     * @throws IOException
     */
    public void guardar(ArrayList<Usuario> usuario, File file)throws IOException{
        JSONArray jsonArray = new JSONArray();
        for ( Usuario v: usuario){
            jsonArray.put(v.objetoAJSON());
        }
        FileWriter fw = new FileWriter(file);
        fw.write(jsonArray.toString());
        fw.flush();
        fw.close();
    }

    /**
     *
     * @param file
     * @return una lista de vendedores que se encuentran en el archivo
     * @throws IOException
     */
    public ArrayList<Usuario> levantar(File file)throws IOException{
        ArrayList<Usuario> usuario = new ArrayList<>();
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String linea;
        JSONArray jsonArray = null;
        JSONObject json;
        Usuario usuario1;
        while ((linea = br.readLine()) != null){
            jsonArray = new JSONArray(linea);

        }
        for(int i = 0; i < jsonArray.length(); i++){
            usuario1 = new Usuario();

            json = jsonArray.getJSONObject(i);

            usuario1.setNombre(json.getString("nombre"));
            usuario1.setApellido(json.getString("apellido"));
            usuario1.setEdad(json.getInt("edad"));
            usuario1.setDocumento(json.getString("documento"));
            usuario1.setNombreUsuario(json.getString("nombreUsuario"));
            usuario1.setCorreoElectronico(json.getString("correo"));
            usuario1.setSaldo(json.getDouble("saldo"));
            usuario1.setContrasenia(json.getString("contrasenia"));
            usuario1.setCantProdVendidos(json.getDouble("cantidadProdVendidos"));
            usuario.add(usuario1);
        }
        br.close();
        return usuario;
    }
    


    public void guardar(Publicacion publicacion, File file) throws IOException {
        ObjectOutputStream fw = new ObjectOutputStream(new FileOutputStream(file));
        fw.writeObject(publicacion);
        fw.flush();
        fw.close();

    }

    public Publicacion levantarPubli(File file) {
        Publicacion publicacion = null;
        try {
            ObjectInputStream ob = new ObjectInputStream(new FileInputStream(file));
            publicacion = (Publicacion) ob.readObject();
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            publicacion = new Publicacion();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return publicacion;
    }

    public void guardarListaUsuario( ArrayList<Producto> lista, File file) throws IOException {
        ObjectOutputStream fw = new ObjectOutputStream(new FileOutputStream(file));
        fw.writeObject(lista);
        fw.flush();
        fw.close();
    }
    public ArrayList<Producto> levantarLista(File file){
    	ArrayList<Producto> lista = new ArrayList<Producto>();
        ObjectInputStream ob;
		try {
			ob = new ObjectInputStream(new FileInputStream(file)); 
			try {
			lista =(ArrayList<Producto>) ob.readObject();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       
        return lista;
    }
    
}
