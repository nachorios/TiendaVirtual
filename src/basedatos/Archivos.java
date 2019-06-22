package basedatos;

import clases.Comprador;
import clases.Vendedor;
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
     * guarda los datos de un vendedor a un json array que luego lo pasa a un string
     * @param vendedor un arraylist que contiene los datos del vendedor
     * @param file el archivo donde se guarda los datos
     * @throws IOException
     */
    public void guardar(ArrayList<Vendedor> vendedor, File file)throws IOException{
        JSONArray jsonArray = new JSONArray();
        for ( Vendedor v: vendedor){
            jsonArray.put(v.objetoAJSON());
        }
        FileWriter fw = new FileWriter(file);
        fw.write(jsonArray.toString());
        fw.flush();
        fw.close();
    }

    /**
     * guarda los datos de un comprador a un json array que luego lo pasa a un string
     * @param comprador un arraylist que contiene los datos del comprador
     * @param file el archivo donde se guarda los datos
     * @throws IOException
     */
    public void guardarC(ArrayList<Comprador> comprador, File file)throws IOException{
        JSONArray jsonArray = new JSONArray();
        for ( Comprador v: comprador){
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
    public ArrayList<Vendedor> levantarVendedor(File file)throws IOException{
        ArrayList<Vendedor> vendedor = new ArrayList<>();
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String linea;
        JSONArray jsonArray = null;
        JSONObject json;
        Vendedor vendedor1;
        while ((linea = br.readLine()) != null){
            jsonArray = new JSONArray(linea);

        }
        for(int i = 0; i < jsonArray.length(); i++){
            vendedor1 = new Vendedor();

            json = jsonArray.getJSONObject(i);

            vendedor1.setNombre(json.getString("nombre"));
            vendedor1.setApellido(json.getString("apellido"));
            vendedor1.setEdad(json.getInt("edad"));
            vendedor1.setDocumento(json.getString("documento"));
            vendedor1.setNombreUsuario(json.getString("nombreUsuario"));
            vendedor1.setCorreoElectronico(json.getString("correo"));
            vendedor1.setSaldo(json.getDouble("saldo"));
            vendedor1.setContrasenia(json.getString("contrasenia"));
            vendedor1.setCantProdVendidos(json.getDouble("cantidadProdVendidos"));

            vendedor.add(vendedor1);
        }
        br.close();
        return vendedor;
    }

    public ArrayList<Comprador> levantarComprador(File file)throws IOException{
        ArrayList<Comprador> comprador = new ArrayList<>();
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String linea;
        JSONArray jsonArray = null;
        JSONObject json;
        Comprador comprador1;
        while ((linea = br.readLine()) != null){
            jsonArray = new JSONArray(linea);

        }
        for(int i = 0; i < jsonArray.length(); i++){
            comprador1 = new Comprador();

            json = jsonArray.getJSONObject(i);

            comprador1.setNombre(json.getString("nombre"));
            comprador1.setApellido(json.getString("apellido"));
            comprador1.setEdad(json.getInt("edad"));
            comprador1.setDocumento(json.getString("documento"));
            comprador1.setNombreUsuario(json.getString("nombreUsuario"));
            comprador1.setCorreoElectronico(json.getString("correo"));
            comprador1.setSaldo(json.getDouble("saldo"));
            comprador1.setContrasenia(json.getString("contrasenia"));
            comprador1.setCantProducComprados(json.getDouble("cantProducComprados"));

            comprador.add(comprador1);
        }
        br.close();
        return comprador;
    }

    public String leerVendedor(File file, String usuario) throws IOException {
        Vendedor vendedor;
        ArrayList<Vendedor> listaVenta = levantarVendedor(file);
        for (int i =0; i<listaVenta.size(); i++){
            vendedor = listaVenta.get(i);
            if (vendedor.getNombreUsuario().equals(usuario))
                return vendedor.toString();
        }
        return null;
    }

    public String leerComprador(File file, String usuario) throws IOException {
        Comprador comprador;
        ArrayList<Comprador> listaComprador = levantarComprador(file);
        for (int i =0; i<listaComprador.size(); i++){
            comprador = listaComprador.get(i);
            if (comprador.getNombreUsuario().equals(usuario))
                return comprador.toString();
        }
        return null;
    }

    public Vendedor buscarVendedor(File file, String usuario) throws IOException {
        Vendedor vendedor1;
        ArrayList<Vendedor> vendedorArrayList = levantarVendedor(file);

        for (int i =0; i<vendedorArrayList.size(); i++){
            vendedor1 = vendedorArrayList.get(i);
            if (vendedor1.getNombreUsuario().equals(usuario)){

                return vendedor1;
            }

        }
        return vendedor1 = null;
    }

    public Comprador buscarComprador(File file, String usuario) throws IOException {
        Comprador comprador1;
        ArrayList<Comprador> compradorArrayList = levantarComprador(file);

        for (int i =0; i<compradorArrayList.size(); i++){
            comprador1 = compradorArrayList.get(i);
            if (comprador1.getNombreUsuario().equals(usuario)){

                return comprador1;
            }

        }
        return comprador1 = null;
    }


}
