package AbnormalPoints;

import Player.Bag;
import Player.Player;
import Player.Item;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import navigation.Coordinate;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class mainly held npc that will trade with player
 * @author: Yixiang Yin and Guanming Ou
 */
public class NPC_MERCHANT extends AbnormalPoint{
    public Bag npcBag;
    public NPC_MERCHANT(String name, String intro, int maxHP, int HP, int damage, int armour,
                        int gold, int xpGain, double critChance, Bag npcBag) {
        this.abnormalPointType = AbnormalPointType.NPC_MERCHANT;
        this.setName(name);
        this.setIntro(intro);
        this.setMaxHP(maxHP);
        this.setHP(HP);
        this.setDamage(damage);
        this.setArmour(armour);
        this.setGold(gold);
        this.setXpGain(xpGain);
        this.setCritChance(critChance);
        this.npcBag = npcBag;
    }

    /**
     * Player buy items from NPC
     * return true if successful, otherwise, false
     * @author: Yixiang Yin
     **/
    public boolean buyFromNPC(Player p, String itemName){
        // assume itemName is valid
        boolean npcHaveIt = npcBag.searchInBagByName(itemName);
        if (npcHaveIt) {
            Item wanted = npcBag.getItemByName(itemName); // guarantee non-null
            int price = wanted.getProperty("value");
            if (p.money>=price){
                p.money-=price;
                p.getBag().put(wanted);
                npcBag.drop(wanted);
                return true;
            }
            else return false; // don't have enough money
        }
        return false; // npc don't have it
    }

    /** (companion with the function above)
     * output the possible str for different circumstances when purchasing
     * @author: Yixiang Yin
     **/
    public String outputStrForBuyFromNPC(Player p, String itemName, boolean succ){
        if (succ) return "You've successfully purchased "+itemName+". Happy to see you soon again!";
        else {
            Item wanted = npcBag.getItemByName(itemName); // guarantee non-null
            int price = wanted.getProperty("value");
            if (p.money<price) return "You don't have enough money to purchase "+itemName+" .";
            return "Sorry, I don't have "+itemName+" .";
        }
    }
    /**
     * Convert NPC Merchant hashMap json object to actual hashMap
     * @author Yixiang Yin
     */

    public static HashMap<Coordinate, NPC_MERCHANT> JsonToNpcMerchantHashMapData(JsonObject jo) {
        HashMap<Coordinate, NPC_MERCHANT> hp = new HashMap<>();

        for (Map.Entry<String, JsonElement> entry : jo.entrySet()) {
            String coor = entry.getKey();
            JsonObject itemData = entry.getValue().getAsJsonObject();
            JsonObject baginfo = itemData.get("npcBag").getAsJsonObject();

            Bag bag = Bag.JsonToBag2(baginfo);
            NPC_MERCHANT shop = new NPC_MERCHANT("Merchant 1","s",100,100,10,10,10,10,10,bag);
            hp.put(Coordinate.fromStringToCoordinate(coor), shop);

        }

        return hp;
    }
    /**
     * print the content of the shop
     * return false if nothing in the shop, else true
     * @author Yixiang Yin
     */

    public static boolean showTheShop(NPC_MERCHANT npc){
        if (npc.npcBag.getItems()==null){ System.out.println("The merchant has nothing. lol");
        return false;}
        System.out.println("-----------------Shop------------------");
        for (int i = 0; i<npc.npcBag.getItems().size();i++){
            Item item =npc.npcBag.getItems().get(i);
            System.out.println("["+i+"]"+" : "+item.name+" : "+item.getDescription()+" ("+item.getProperty("value")+" coins)");
            System.out.println();
        }
        System.out.println("-----------------End------------------");
        return true;

    }

    /**
     * Save merchant npc as json
     * @author Yixiang Yin and Guanming Ou
     */
    public static void saveMERCHANTNPC(HashMap<Coordinate, NPC_MERCHANT> shops){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(FileWriter fw = new FileWriter("json_files/original_data/MerchantNPC.json")){
            gson.toJson(shops, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * load original NPC_MERCHANT data(hashMap) from json_files/original_data/ in a way makes json satisfied
     * @author Yixiang Yin
     */
    public static HashMap<Coordinate, NPC_MERCHANT> loadOriginalNPC_MER() {
        File file = new File("json_files/original_data/MerchantNPC.json");

        Gson gson = new Gson();
        JsonReader jsonReader = null;

        try {
            jsonReader = new JsonReader(new FileReader(file));

        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonObject jo = gson.fromJson(jsonReader, JsonObject.class);
        HashMap<Coordinate, NPC_MERCHANT> hp = new HashMap<>();

        for (Map.Entry<String, JsonElement> entry : jo.entrySet()) {
            String coor = entry.getKey();
            JsonObject itemData = entry.getValue().getAsJsonObject();
            JsonObject baginfo = itemData.get("npcBag").getAsJsonObject();

            Bag bag = Bag.JsonToBag2(baginfo);
            NPC_MERCHANT shop = new NPC_MERCHANT("Merchant 1","s",100,100,10,10,10,10,10,bag);
            hp.put(Coordinate.fromStringToCoordinate(coor), shop);

        }

        return hp;
    }

    /**
     * Create json file by instanlising and execute save command inside java
     * @param args
     * @author Yixiang Yin, add more merchant to more coordinate by Guanming Ou
     */
    public static void main(String[] args) {
        Bag bag = new Bag(0,1000,Item.initializeItemBook());
        NPC_MERCHANT shop = new NPC_MERCHANT("Merchant","I am a merchant",100,100,10,10,250,10,1,bag);
        HashMap<Coordinate,NPC_MERCHANT> hp = new HashMap<>();
        hp.put(new Coordinate(0,0), shop);
        hp.put(new Coordinate(0,11), shop);
        hp.put(new Coordinate(1,4), shop);
        hp.put(new Coordinate(2,2), shop);
        hp.put(new Coordinate(4,29), shop);
        hp.put(new Coordinate(8,8), shop);
        hp.put(new Coordinate(9,9), shop);
        hp.put(new Coordinate(12,0), shop);
        hp.put(new Coordinate(15,5), shop);
        hp.put(new Coordinate(16,19), shop);
        hp.put(new Coordinate(18,28), shop);
        hp.put(new Coordinate(20,4), shop);
        hp.put(new Coordinate(21,9), shop);
        hp.put(new Coordinate(22,24), shop);
        hp.put(new Coordinate(23,25), shop);
        hp.put(new Coordinate(23,12), shop);
        hp.put(new Coordinate(23,3), shop);
        hp.put(new Coordinate(27,13), shop);
        hp.put(new Coordinate(29,15), shop);
        hp.put(new Coordinate(30,30), shop);
        // save to json file
        saveMERCHANTNPC(hp);
    }

}
