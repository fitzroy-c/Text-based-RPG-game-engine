import AbnormalPoints.MonsterAttributes;
import Card.subCards.Normal_Attack_cards;
import Maps.Environment;
import Maps.MonsterRoom;
import Maps.Rooms;
import AbnormalPoints.Monster;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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

        File file;

        // This is Cards testing json

        List<Cards> cards = new ArrayList<Cards>();

        cards.add(new Normal_Attack_cards(1, CardClass.Normal_Attack, Rarity.Common, "First Card",
                "This card is for testing purpose", "Deal one damage to opponent with no cost",
                "Knight", 1, 1, 0,0,1,0,
                0,0,0));

        cards.add(new Normal_Attack_cards(2, CardClass.Normal_Attack, Rarity.Common, "Second Card",
                "This card is for testing purpose", "Deal ten damage to opponent with 1 cost",
                "Knight", 1, 1, 1,0,10,0,
                0,0,0));

        file = new File("json_files/testingCard.json");

        // To ensure there is no file previous named the same
        //file.delete();

        System.out.println("Cards json");

        System.out.println("saving...");
        saveCardsToJSONFile(file, cards);
        System.out.println("saved");

        System.out.println("loading...");
        List<Cards> newCards = loadCardsFromJSONFile(file);
        System.out.println("loaded");



        // This is environment room testing json

        List<Environment> Environments = new ArrayList<Environment>();
        List<Rooms> forrestRooms = new ArrayList<Rooms>();

        int[] goblinCardSet1 = {1,1,1,1,1,1,1,1,2};

        Monster goblinWorker = new Monster( new MonsterAttributes("goblin", "55",6,
                0,3,12,2,5,0.02,
                3,5,3,2, Element.Normal), 5);
        Monster goblinSoldier = new Monster(new MonsterAttributes("goblin", "55",6,
                0,3,5,6,12,0.02,2,10,
                3,5, Element.Normal) , 10);

        forrestRooms.add(new MonsterRoom("Wolf worker", "small, malicious and greedy goblin worker.  Beware...", goblinWorker));
        forrestRooms.add(new MonsterRoom("Wolf soldier","Strong, malicious and greedy goblin soldier.  Beware...", goblinSoldier));

        Environment forrest = new Environment("Forrest",
                "The light fades as you enter the forest, turning into a strange dark and quiet place, " +
                        "the sounds have all but vanished. The smell of animal or beast hangs in a still air, " +
                        "no flowers seem to help the uneasy odor. In the dim light you see shadows dart about, " +
                        "you barely hear the sound of things moving, and you feel as if everything is watching you.",
                forrestRooms);

        Environments.add(forrest);


        file = new File("json_files/testingEnvironment.json");
        // To ensure there is no file previous named the same
        file.delete();

        System.out.println("Environment json");

        System.out.println("saving...");
        saveEnvironmentToJSONFile(file, Environments);
        System.out.println("saved");

        System.out.println("loading...");
        List<Environment> newEnvironments = loadEnvironmentFromJSONFile(file);
        System.out.println("loaded");
    }
    // This is just for testing, delete later --------------------------

    /**
     * Saves arraylist of cards to the specified JSON file.
     *
     * @param file The path to a file.
     * @param cards The arraylist of cards to be saved
     */
    public static void saveCardsToJSONFile(File file, List<Cards> cards) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(FileWriter fw = new FileWriter(file)){
            gson.toJson(cards, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load a pre-existing card collection from a JSON file.
     *
     * @param file The file to load from. This is guaranteed to exist.
     * @return An initialised arraylist of cards.
     */
    public static List<Cards> loadCardsFromJSONFile(File file) {
        Gson gson = new Gson();
        JsonReader jsonReader = null;

        final Type CUS_LIST_TYPE = new TypeToken<List<Cards>>() {}.getType();

        try{
            jsonReader = new JsonReader(new FileReader(file));
        }catch (Exception e) {
            e.printStackTrace();
        }

        return gson.fromJson(jsonReader, CUS_LIST_TYPE);
    }


    /**
     * Saves arraylist of cards to the specified JSON file.
     *
     * @param file The path to a file.
     * @param environments The arraylist of cards to be saved
     */
    public static void saveEnvironmentToJSONFile(File file, List<Environment> environments) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(FileWriter fw = new FileWriter(file)){
            gson.toJson(environments, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load a pre-existing Environment from a JSON file.
     *
     * @param file The file to load from. This is guaranteed to exist.
     * @return An initialised arraylist of environments.
     */
    public static List<Environment> loadEnvironmentFromJSONFile(File file) {
        Gson gson = new Gson();
        JsonReader jsonReader = null;

        final Type CUS_LIST_TYPE = new TypeToken<List<Environment>>() {}.getType();

        try{
            jsonReader = new JsonReader(new FileReader(file));
        }catch (Exception e) {
            e.printStackTrace();
        }

        return gson.fromJson(jsonReader, CUS_LIST_TYPE);
    }
}
