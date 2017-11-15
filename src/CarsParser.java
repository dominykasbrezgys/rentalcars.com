import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;


public class CarsParser {

    static final String JSON_URL = "http://www.rentalcars.com/js/vehicles.json";//TODO: change to input parameter


    private ArrayList<JsonObject> getVehiclesAsArrayList(String urlString)throws Exception {

        List<JsonObject> jsonAsArrayList = new ArrayList<>();
        BufferedReader reader = null;
        StringBuilder vehiclesStr = new StringBuilder();

        //1. Retrieve JSON String from specified url using BufferReader
        //TODO: see if can be done with no stream mode & TIDY UP comments
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));//Reads text from a character-input stream
            // An InputStreamReader is a bridge from byte streams to character streams

            int read;
            char[] chars = new char[1024]; //each chunk (1024 chars each) of stream is copied to this buffer

            while ((read = reader.read(chars)) != -1) // reader.read(arrayToStoreChars) returns how many chars have been read if -1,
                vehiclesStr.append(chars, 0, read);				// no chars to read

        } finally {
            if (reader != null)
                reader.close();
        }
        //2. Work down the hierarchy to get VehicleList JsonObject
        JsonElement jelement = new JsonParser().parse(vehiclesStr.toString());
        JsonObject rootObject = jelement.getAsJsonObject();
        JsonObject searchObject = rootObject.getAsJsonObject("Search");
        JsonArray vehiclesJSON = searchObject.getAsJsonArray("VehicleList");

        //3. Convert to ArrayList
        for (int i = 0; i < vehiclesJSON.size(); i++) {
            jsonAsArrayList.add(vehiclesJSON.get(i).getAsJsonObject());
        }

        return (ArrayList<JsonObject>) jsonAsArrayList;

    }



    public static void main(String[] args) throws Exception {

        CarsParser cp = new CarsParser();
        ArrayList<JsonObject> vehicles = cp.getVehiclesAsArrayList(JSON_URL);

        Task1 t1 = new Task1(vehicles);

        System.out.println(t1);
    }

}
