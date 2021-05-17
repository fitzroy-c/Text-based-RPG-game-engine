package Player;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Collections;
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
    public Map<String, Integer> getProperties() {
        return Collections.unmodifiableMap(properties);// for safe reason unchangeable map
    }
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
     * //Todo initialize first once the game starts
     * search an item in item.json. return true if found, otherwise, false(contain bugs)
     * @author: Yixiang Yin
     **/
    public static boolean searchInItemJson(String itemName){
        Gson gson = new Gson();
        String path = "json_files/Item.json";
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        HashMap<String, String> json = gson.fromJson(bufferedReader, HashMap.class);
        for (String i : json.keySet()){
            System.out.println(i);
        }
        return json.containsKey(itemName);
    }

    public void print() {
        // TODO: need a function to print out all we have in this real location (name + description?)
    }
}
