import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class Task2 {
    //Using constants for letter indexes to avoid hardcoded values.
    private static final int TYPE_LETTER_INDEX = 0;
    private static final int DOORS_LETTER_INDEX = 1;
    private static final int TRANSMISSION_LETTER_INDEX = 2;
    private static final int FUEL_CON_LETTER_INDEX = 3;
    private List<JsonObject> vehicles = new ArrayList<>();

    Task2( ArrayList<JsonObject> vehiclesArrayList){
        vehicles = vehiclesArrayList;
    }

    private String getType(char letter){
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

    private String getDoorsType(char letter){
        switch (letter){
            case 'B':
                return "2 doors";
            case 'C':
                return "4 doors";
            case 'D':
                return "5 doors";
            case 'W':
                return "Estate";
            case 'T':
                return "Convertible";
            case 'F':
                return "SUV";
            case 'P':
                return "Pick up";
            case 'V':
                return "Passenger Van";
            default:
                return "Unknown";
        }
    }

    private String getTransmission(char letter){
        switch (letter){
            case 'M':
                return "Manual";
            case 'A':
                return "Automatic";
            default:
                return "Unknown";
        }
    }

    private String getFuelCon(char letter){
        switch(letter){
            case 'N':
                return "Petrol/no AC";
            case 'R':
                return "Petrol/AC";
            default:
                return "Unknown";
        }
    }

    private String buildOutputString(){
        //Reason why StringBuilder is because both + and concat create a new object every time you call them.
        StringBuilder output = new StringBuilder();
        String sipp;
        String line;
        for (int i = 0; i < vehicles.size(); i++) {
            sipp = vehicles.get(i).get("sipp").getAsString();

            line = "{"+vehicles.get(i).get("name").toString()+"}"+" - "+
                    "{"+sipp+"}"+" - "+
                    "{"+getType(sipp.charAt(TYPE_LETTER_INDEX))+"}"+" - "+
                    "{"+getDoorsType(sipp.charAt(DOORS_LETTER_INDEX))+"}"+" - "+
                    "{"+getTransmission(sipp.charAt(TRANSMISSION_LETTER_INDEX))+"}"+" - "+
                    "{"+getFuelCon(sipp.charAt(FUEL_CON_LETTER_INDEX))+"}\n";
            output.append(line);
        }
        return output.toString();
    }

    public String toString() {
        return buildOutputString();
    }

}
