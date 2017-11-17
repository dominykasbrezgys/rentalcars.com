import com.google.gson.JsonObject;
import com.google.gson.JsonIOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Task4 {
    private List<JsonObject> vehicles = new ArrayList<>();
    private List<JsonObject> vehiclesOutput = new ArrayList<>();
    private static final int TRANSMISSION_LETTER_INDEX = 2;
    private static final int FUEL_CON_LETTER_INDEX = 3;

    Task4(ArrayList<JsonObject> vehiclesArrayList) {
        vehicles = vehiclesArrayList;
    }

    private void sortOutputBySumOfScores(){
        Collections.sort( vehiclesOutput, new Comparator<JsonObject>() {
            private static final String KEY_NAME = "Sum of Scores";
            @Override
            public int compare(JsonObject a, JsonObject b) {
                String valA="";
                String valB="";
                try {
                    valA = a.get(KEY_NAME).getAsString();
                    valB = b.get(KEY_NAME).getAsString();
                    //Trick to uniform strings in case number of chars differ.
                    if (valA.length()>valB.length()){
                        String temp = "0";
                        temp+=valB;
                        valB=temp;
                    }
                    if (valA.length()<valB.length()){
                        String temp = "0";
                        temp+=valA;
                        valA=temp;
                    }
                }
                catch (JsonIOException e) {
                    System.err.println("Sorting by Sum of Scores went wrong: " + e.getMessage());
                }
                return -valA.compareTo(valB);
            }
        });

    }

    private float calcVehicleScore(String sipp){
        float score = 0;
        char transmission = sipp.charAt(TRANSMISSION_LETTER_INDEX);
        char airCon = sipp.charAt(FUEL_CON_LETTER_INDEX);

        if (transmission == 'M') score+=1;
        if (transmission == 'A') score+=5;
        if (airCon == 'R') score+=2;
        return score;
    }

    private void buildOutputArrayList(){
        float vehicleScore;
        float sumOfScores;
        String sipp;

        for (int i = 0; i < vehicles.size(); i++) {
            vehiclesOutput.add(vehicles.get(i));
            sipp = vehiclesOutput.get(i).get("sipp").getAsString();
            vehicleScore = calcVehicleScore(sipp);
            vehiclesOutput.get(i).addProperty("Vehicle Score", vehicleScore);
            sumOfScores = vehicleScore + vehiclesOutput.get(i).get("rating").getAsFloat();
            vehiclesOutput.get(i).addProperty("Sum of Scores", sumOfScores);
        }
    }

    private String buildOutputString(){
        //Reason why StringBuilder is that both + and concat create a new object every time you call them
        StringBuilder output = new StringBuilder();
        String line;
        for (int i = 0; i < vehiclesOutput.size(); i++) {
            line = "{"+vehiclesOutput.get(i).get("name").toString()+"}"+" - "+
                    "{"+vehiclesOutput.get(i).get("Vehicle Score").toString()+"}"+" - "+
                    "{"+vehiclesOutput.get(i).get("rating").toString()+"}"+" - "+
                    "{"+vehiclesOutput.get(i).get("Sum of Scores").toString()+"}\n";
            output.append(line);
        }
        return output.toString();
    }

    public String getOutput(){
        buildOutputArrayList();
        sortOutputBySumOfScores();
        return buildOutputString();
    }
}
