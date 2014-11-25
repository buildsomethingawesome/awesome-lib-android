import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AwesomeApis {
    public static String getString(String url) {
        // adapted from http://stackoverflow.com/a/10501619/308930
        try {
            URL u = new URL(url);
            HttpURLConnection c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.connect();
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    br.close();
                    return sb.toString();
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(AwesomeApis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AwesomeApis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static JSONArray getJSONArray(String url) {
        String string = getString(url);
        try {
            return new JSONArray(string);
        } catch (JSONException ex) {
            Logger.getLogger(AwesomeApis.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static JSONObject getJSONObject(String url) {
        String string = getString(url);
        try {
            return new JSONObject(string);
        } catch (JSONException ex) {
            Logger.getLogger(AwesomeApis.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
