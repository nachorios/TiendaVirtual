package basedatos;

import org.json.JSONObject;

import java.io.*;

public class Archivos
{

    public File guardar(JSONObject js, File archi) throws IOException {
        FileWriter fw = new FileWriter(archi.getAbsoluteFile(), true);
        fw.write(js.toString());
        fw.flush();
        fw.close();
        return archi;
    }

    public String leer(File file){

        String linea = " ";
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while ((linea = br.readLine()) != null) {
                return linea;
            }
            br.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return linea;
    }


}
