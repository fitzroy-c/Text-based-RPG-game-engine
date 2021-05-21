package Player;

import AbnormalPoints.NPC_MERCHANT;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import navigation.Coordinate;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
// todo item.json
// todo test for these functions in player, bag,item.class
// todo weapon, considering onetime weapon first

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
    public int getValue(){
        return this.getProperty("value");
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

    //    String s = ""String"";
    /**
     * used to generate Item on the map, and save that into a json file
     * @author: Yixiang Yin
     **/
    public static void generateItemOnTheMapJson(String path){
        HashMap<Coordinate, Bag> hp = new HashMap<>();

        List<Item> items = initializeItemBook();
        int x = 0;
        while (x<30){
            int y = 0;
            while (y<30){
                Bag b = new Bag(10000);
                b.put(items.get((x+y)%items.size()));
                hp.put(new Coordinate(x,y),b);
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
    /**
     * load original items data(hashMap) from json_files/original_data/ in a way makes json satisfied
     * @author Yixiang Yin
     */
    public static HashMap<Coordinate, Bag> loadOriginalItems() {
        File file = new File("json_files/original_data/InitializedItem.json");

        Gson gson = new Gson();
        JsonReader jsonReader = null;

        try{
            jsonReader = new JsonReader(new FileReader(file));

        }catch (Exception e) {
            e.printStackTrace();
        }

        JsonObject jo = gson.fromJson(jsonReader, JsonObject.class);
        HashMap<Coordinate, Bag> hp = new HashMap<>();

        for (Map.Entry<String, JsonElement> entry : jo.entrySet()) {
            String coor = entry.getKey();
            JsonObject itemData = entry.getValue().getAsJsonObject();
            String cw = String.valueOf(itemData.get("currentWeight"));
            String mw = String.valueOf(itemData.get("maxWeight"));


            JsonArray sProps = itemData.get("itemList").getAsJsonArray();
            List<Item> items = new LinkedList<Item>();
            Item item = null;
            for (JsonElement je : sProps){
                JsonObject itemJO = je.getAsJsonObject();
                String id =itemJO.get("id").getAsString();
//                System.out.println(id);
                String type = itemJO.get("type").getAsString();
                String name = itemJO.get("name").getAsString();
                String description = itemJO.get("description").getAsString();
                JsonObject props = itemJO.get("properties").getAsJsonObject();
                Map<String, Integer> properties = new HashMap<>();
                for (Map.Entry<String, JsonElement> entry2 : props.entrySet()) {
                    Integer propValue = entry2.getValue().getAsInt();
                    properties.put(entry2.getKey(), propValue);
                }
                item = new Item(id,type,name,description,properties);
                items.add(item);
            }


            hp.put(Coordinate.fromStringToCoordinate(coor),new Bag(Integer.parseInt(cw),Integer.parseInt(mw), items));

        }

        return hp;
    }
    public void print() {
        // TODO: need a function to print out all we have in this real location (name + description?)
    }
    public static void main(String[] args) {
        // test initializeItems
        generateItemOnTheMapJson("json_files/original_data/InitializedItem.json");
    }
}
