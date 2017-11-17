import com.google.gson.JsonObject;
import com.google.gson.JsonIOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Task1 {

    private List<JsonObject> vehicles = new ArrayList<>();

    Task1( ArrayList<JsonObject> vehiclesArrayList){
        vehicles = vehiclesArrayList;
    }

    private void sortByPrice(){

        Collections.sort( vehicles, new Comparator<JsonObject>() {

            private static final String KEY_NAME = "price";

            @Override
            public int compare(JsonObject a, JsonObject b) {
                String valA="";
                String valB="";

                try {
                    valA = a.get(KEY_NAME).getAsString();
                    valB = b.get(KEY_NAME).getAsString();
                }
                catch (JsonIOException e) {
                    System.err.println("Sorting by price went wrong: " + e.getMessage());
                }

                return valA.compareTo(valB);
            }
        });

    }

    private String buildOutputString(){
        //Reason why StringBuilder is that both + and concat create a new object every time you call them
        StringBuilder output = new StringBuilder();
        String line;
        for (int i = 0; i < vehicles.size(); i++) {
            line = "{"+vehicles.get(i).get("name").toString()+"}"+" - "+
                    "{"+vehicles.get(i).get("price").toString()+"}\n";
            output.append(line);
        }
        return output.toString();
    }

    public String getOutput() {
        sortByPrice();
        return buildOutputString();
    }
}
