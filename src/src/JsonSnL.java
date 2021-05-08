import Card.subCards.Action_cards;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


import Card.*;



// load all Json file and information into here and modify so on

public class JsonSnL {
    // This is just for testing, delete later --------------------------
    public static void main(String[]args){
        JsonSnL jsonsnl = new JsonSnL();

        jsonsnl.cards.add(new Action_cards(1, CardClass.Action, Rarity.Common, "First Card",
                "This card is for testing purpose", "Deal one damage to opponent with no cost",
                "Knight", 1, 1, 0,0,1,0,
                0,0,0));

        jsonsnl.cards.add(new Action_cards(2, CardClass.Action, Rarity.Common, "Second Card",
                "This card is for testing purpose", "Deal two damage to opponent with 1 cost",
                "Knight", 1, 1, 1,0,2,0,
                0,0,0));

        File file = new File("json_files/testing.json");

        // To ensure there is no file previous named the same
        file.delete();

        System.out.println("saving...");
        jsonsnl.saveToJSONFile(file);
        System.out.println("saved");

        System.out.println("loading...");
        List<Action_cards> newCards = jsonsnl.loadFromJSONFile(file);
        System.out.println("loaded");

        for (Action_cards c : newCards){
            System.out.println(c.toString());
        }

    }
    // This is just for testing, delete later --------------------------



    private List<Action_cards> cards;
    public JsonSnL() {
        cards = new ArrayList<>();
    }

    /**
     * Saves this collection to the specified JSON file.
     *
     * @param file The path to a file.
     */
    public void saveToJSONFile(File file) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(FileWriter fw = new FileWriter(file)){
            gson.toJson(cards, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load a pre-existing book collection from a JSON file.
     *
     * @param file The file to load from. This is guaranteed to exist.
     * @return An initialised book collection.
     */
    public static List<Action_cards> loadFromJSONFile(File file) {
        Gson gson = new Gson();
        JsonReader jsonReader = null;

        final Type CUS_LIST_TYPE = new TypeToken<List<Action_cards>>() {}.getType();

        try{
            jsonReader = new JsonReader(new FileReader(file));
        }catch (Exception e) {
            e.printStackTrace();
        }
//        return gson.fromJson(jsonReader, List<Action_cards>)
        return gson.fromJson(jsonReader, CUS_LIST_TYPE);
    }
}
