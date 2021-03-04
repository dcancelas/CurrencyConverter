import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Converter {

    //Utiliza la librería gson de Google
    private static final String api = "0beecfce22f8bdc1105c351c";

    public static double convert_currency(String currency1, String currency2, double ammount) throws IOException {
        // Haciendo la peticición
        URL url = new URL("https://v6.exchangerate-api.com/v6/" + api + "/pair/" + currency1 + "/" + currency2);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
        // Convirtiendo a JSON
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();

        //Accediendo al objeto
        double conversionRate = jsonobj.get("conversion_rate").getAsDouble();
        /*String lastUpdate = jsonobj.get("time_last_update_utc").getAsString();
        String nextUpdate = jsonobj.get("time_next_update_utc").getAsString();*/

        return (ammount * conversionRate);
    }
}
