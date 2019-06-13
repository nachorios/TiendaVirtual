package basedatos;

import clases.Vendedor;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.util.ArrayList;

/*
	cuando crea un vendedor levanta archivo vendedor lo pasa a json
	de json la pasa a un arreglo
	ahi creo el al vendedor lo agrego en el arreglo
	paso el arreglo a json
	y json a archivo
*/
/*
    json array a array de vendedores java


 */
public class Archivos
{

    public File guardar(ArrayList<Vendedor> vendedor, File file)throws IOException{
        JSONArray jsonArray = new JSONArray();
        for ( Vendedor v: vendedor){
            jsonArray.put(v.objetoAJSON());
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
        fw.write(jsonArray.toString());
        fw.flush();
        fw.close();
        return file;
    }

    public ArrayList<Vendedor> levantarVendedor(File file)throws IOException{
        /// al revez
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

    public String leerVendedor(File file, String usuario, ArrayList<Vendedor> listaVenta) throws IOException {
        Vendedor vendedor;
        listaVenta = levantarVendedor(file);
        for (int i =0; i<listaVenta.size(); i++){
            vendedor = listaVenta.get(i);
            if (vendedor.getNombreUsuario().equals(usuario))
                return vendedor.toString();
        }
        return null;
    }

    public Vendedor buscarVendedor(File file, String usuario, ArrayList<Vendedor> vendedorArrayList) throws IOException {
        Vendedor vendedor1 = null;
        vendedorArrayList = levantarVendedor(file);

        for (int i =0; i<vendedorArrayList.size(); i++){
            vendedor1 = vendedorArrayList.get(i);
            if (vendedor1.getNombreUsuario().equals(usuario)){

                return vendedor1;
            }

            else
                return vendedor1;
        }
        return vendedor1;
    }


}
