import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

@Path("/getTaskOutput")
public class CarsParser {

    private String JSON_URL = "http://www.rentalcars.com/js/vehicles.json";

    private ArrayList<JsonObject> getVehiclesAsArrayList()throws Exception {

        List<JsonObject> jsonAsArrayList = new ArrayList<>();
        BufferedReader reader = null;
        StringBuilder vehiclesStr = new StringBuilder();

        //1. Retrieve JSON String from url using BufferReader
        try {
            URL url = new URL(JSON_URL);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));

            int read;
            char[] chars = new char[1024];

            while ((read = reader.read(chars)) != -1)
                vehiclesStr.append(chars, 0, read);

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

    @GET
    @Path("/{TaskNumber}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getTaskOutput(@PathParam("TaskNumber") String Task) {
        ArrayList<JsonObject> vehicles = null;
        try {
            vehicles = getVehiclesAsArrayList();
        } catch (Exception e) {
            return "Couldn't access JSON file. Check URL.";
        }

        switch(Task){
            case "Task1":
                return new Task1(vehicles).getOutput().toString();
            case "Task2":
                return new Task2(vehicles).getOutput().toString();
            case "Task3":
                return new Task3(vehicles).getOutput().toString();
            case "Task4":
                return new Task4(vehicles).getOutput().toString();
            default:
                return "Task was not found";
        }
    }
}
