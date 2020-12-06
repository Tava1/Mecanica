package mecanica.utils;

import java.io.BufferedReader;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Gustavo Santos
 */
public class LerJson {
    public static JSONArray lerJsonArray(String nomeArray, HttpServletRequest request) {
        try {
            // Ler o JSON
            StringBuilder sb = new StringBuilder();
            String line = null;

            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject json = new JSONObject(sb.toString());
            JSONArray arr = json.getJSONArray(nomeArray);
            return arr;
        } 
        catch (Exception e) {
            return null;
        }
    }
    
    public static JSONObject lerJsonObj(HttpServletRequest request) {
        try {
            // Ler o JSON
            StringBuilder sb = new StringBuilder();
            String line = null;

            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject json = new JSONObject(sb.toString());
            return json;
        } 
        catch (Exception e) {
            return null;
        }
    }
}
