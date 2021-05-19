package Player;

import AbnormalPoints.NPC_MERCHANT;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import navigation.Coordinate;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// todo item.json
// todo test for these functions in player, bag,item.class


// todo weapon, considering onetime weapon first
// i
public class Item {
    public final String id;
    public final String type;
    public final String name;
    private final String description;
    public final Map<String, Integer> properties; // may contain price, effect, weight of this item

    public Item(String id, String type, String name, String description, Map<String, Integer> properties) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.properties = properties;
    }
    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public String getDescription(){ return description; }
    public Integer getWeight() {
        if (properties.containsKey("weight")) {
            return properties.get("weight");
        }
        return Integer.valueOf(0);//in case some special item don't have weight(ability card)
    }

    public Integer getDamage() {
        if (properties.containsKey("damage")) {
            return properties.get("damage");
        }
        return Integer.valueOf(0);//in case some special item don't have weight(gold,treasure,xp spring)
    }
    public int getProperty(String property) {
        if (! properties.containsKey(property)) {
            return 0;
        }
        return properties.get(property);
    }
    // redundant
//    public Map<String, Integer> getProperties() {
//        return Collections.unmodifiableMap(properties);// for safe reason unchangeable map
//    }
    public boolean containsProperty(String key) {
        return properties.containsKey(key);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Item) {
            Item i = (Item) obj;
            return name.equals(i.name);
        }
        return false;
    }
    /**
     * initialize items as a reference for classifying items from user input
     * @author: Yixiang Yin
     **/
    public static List<Item> initializeItemBook(){

        Gson gson = new Gson();
        String path = "json_files/original_data/ItemBook.json";
        JsonReader jr = null;
        final Type LIST_TYPE = new TypeToken<List<Item>>() {}.getType();

        try {
            jr = new JsonReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Item> items = gson.fromJson(jr,LIST_TYPE);
        return items;
    }


    /**
     * used to generate Item on the map, and save that into a json file
     * @author: Yixiang Yin
     **/
    public static void generateItemOnTheMapJson(String path){
        HashMap<Coordinate, Item> hp = new HashMap<>();

        List<Item> items = initializeItemBook();
        int x = 0;
        while (x<30){
            int y = 0;
            while (y<30){
                hp.put(new Coordinate(x,y),items.get((x+y)%items.size()));
                y++;
                y++;
            }
            x++;
            x++;
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileWriter fw = new FileWriter(path)){ // name json file with player's name
            gson.toJson(hp, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        // test initializeItems
        generateItemOnTheMapJson("json_files/original_data/InitializedItem.json");
    }

    public void print() {
        // TODO: need a function to print out all we have in this real location (name + description?)
    }
}
