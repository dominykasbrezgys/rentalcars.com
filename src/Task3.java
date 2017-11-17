import com.google.gson.JsonObject;
import com.google.gson.JsonIOException;
import java.util.*;

public class Task3 {
    private List<JsonObject> vehicles = new ArrayList<>();
    private static final int NUMBER_OF_CAR_TYPES = 9;
    private static final int CAR_TYPE_LETTER_INDEX = 0;
    //indexes[] used to store indexes of highest rating per carType in vehicles ArrayList.
    private int[] indexes = new int[NUMBER_OF_CAR_TYPES];

    Task3( ArrayList<JsonObject> vehiclesArrayList){
        vehicles = vehiclesArrayList;
    }

    private String getCarType(char letter){
        switch (letter) {
            case 'M':
                return "Mini";
            case 'E':
                return "Economy";
            case 'C':
                return "Compact";
            case 'I':
                return "Intermediate";
            case 'S':
                return "Standard";
            case 'F':
                return "Full Size";
            case 'P':
                return "Premium";
            case 'L':
                return "Luxury";
            case 'X':
                return "Special";
            default:
                return "Unknown";
        }
    }

    private void sortByRating(){
        Collections.sort( vehicles, new Comparator<JsonObject>() {
            private static final String KEY_NAME = "rating";
            @Override
            public int compare(JsonObject a, JsonObject b) {
                String valA="";
                String valB="";

                try {
                    valA = a.get(KEY_NAME).getAsString();
                    valB = b.get(KEY_NAME).getAsString();
                }
                catch (JsonIOException e) {
                    System.err.println("Sorting by rating went wrong: " + e.getMessage());
                }
                return -valA.compareTo(valB);
            }
        });
    }

    private void findIndexesOfHighestRatingPerCarType(){
        //Starting values for highest ratings per car type are set to 0.
        float highestRatingM = 0;
        float highestRatingE = 0;
        float highestRatingC = 0;
        float highestRatingI = 0;
        float highestRatingS = 0;
        float highestRatingF = 0;
        float highestRatingP = 0;
        float highestRatingL = 0;
        float highestRatingX = 0;

        //Populate indexes array with -1
        Arrays.fill(indexes, -1);

        String sipp;
        char carType;
        for (int i = 0; i<vehicles.size();i++) {
            sipp = vehicles.get(i).get("sipp").getAsString();
            carType = sipp.charAt(CAR_TYPE_LETTER_INDEX);
            switch (carType) {
                case 'M':
                    if (vehicles.get(i).get("rating").getAsFloat() > highestRatingM) {
                        indexes[0] = i;
                        highestRatingM = vehicles.get(i).get("rating").getAsFloat();
                    }
                    break;
                case 'E':
                    if (vehicles.get(i).get("rating").getAsFloat() > highestRatingE) {
                        indexes[1] = i;
                        highestRatingE = vehicles.get(i).get("rating").getAsFloat();
                    }
                    break;
                case 'C':
                    if (vehicles.get(i).get("rating").getAsFloat() > highestRatingC) {
                        indexes[2] = i;
                        highestRatingC = vehicles.get(i).get("rating").getAsFloat();
                    }
                    break;
                case 'I':
                    if (vehicles.get(i).get("rating").getAsFloat() > highestRatingI) {
                        indexes[3] = i;
                        highestRatingI = vehicles.get(i).get("rating").getAsFloat();
                    }
                    break;
                case 'S':
                    if (vehicles.get(i).get("rating").getAsFloat() > highestRatingS) {
                        indexes[4] = i;
                        highestRatingS = vehicles.get(i).get("rating").getAsFloat();
                    }
                    break;
                case 'F':
                    if (vehicles.get(i).get("rating").getAsFloat() > highestRatingF) {
                        indexes[5] = i;
                        highestRatingF = vehicles.get(i).get("rating").getAsFloat();
                    }
                    break;
                case 'P':
                    if (vehicles.get(i).get("rating").getAsFloat() > highestRatingP) {
                        indexes[6] = i;
                        highestRatingP = vehicles.get(i).get("rating").getAsFloat();
                    }
                    break;
                case 'L':
                    if (vehicles.get(i).get("rating").getAsFloat() > highestRatingL) {
                        indexes[7] = i;
                        highestRatingL = vehicles.get(i).get("rating").getAsFloat();
                    }
                    break;
                case 'X':
                    if (vehicles.get(i).get("rating").getAsFloat() > highestRatingX) {
                        indexes[8] = i;
                        highestRatingX = vehicles.get(i).get("rating").getAsFloat();
                    }
                    break;
            }
        }
        //Sort arrays for the output
        Arrays.sort(indexes);
    }

    private String buildOutputString(){
        //Reason why StringBuilder is because both + and concat create a new object every time you call them.
        StringBuilder output = new StringBuilder();
        String line;
        String sipp;
        for(int index : indexes){
            if (index < 0) continue;

            sipp = vehicles.get(index).get("sipp").getAsString();
            line = "{"+vehicles.get(index).get("name").toString()+"}"+" - "+
                    "{"+getCarType(sipp.charAt(CAR_TYPE_LETTER_INDEX))+"}"+" - "+
                    "{"+vehicles.get(index).get("supplier").toString()+"}"+" - "+
                    "{"+vehicles.get(index).get("rating").toString()+"}\n";
            output.append(line);
        }
        return output.toString();
    }

    public String getOutput() {
        sortByRating();
        findIndexesOfHighestRatingPerCarType();
        return buildOutputString();
    }
}
