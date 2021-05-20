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
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class mainly held npc that will trade with player
 */
public class NPC_MERCHANT extends AbnormalPoint{
    public Bag npcBag;
//    public Coordinate coor;
//    player的bag, npc_merchant的bag,

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
//        this.coor =coor;
    }


    //Todo 买卖交互
    //todo initialize npc shop
    /**
     * Player buy items from NPC
     * return true if successful, otherwise, false
     * @author: Yixiang Yin
     **/
    //todo:  show my shop
    //todo:  限制行动
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
        System.out.println(p.money);
        if (succ) return "You've successfully purchased "+itemName+" .Happy to see you soon again!";
        else {
            Item wanted = npcBag.getItemByName(itemName); // guarantee non-null
            int price = wanted.getProperty("value");
            if (p.money<price) return "You don't have enough money to purchase "+itemName+" .";
            return "Sorry, I don't have "+itemName+" .";
        }
    }
    public static HashMap<Coordinate, NPC_MERCHANT> JsonToNpcMerchantHashMapData(JsonObject jo) {
        Gson gson = new Gson();
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
            System.out.println("["+i+"]"+" : "+item.name+" : sdf"+item.getDescription()+" ("+item.getProperty("value")+" coins)");
            System.out.println();
        }
        System.out.println("-----------------End------------------");
        return true;

    }


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
    public static void main(String[] args) {
        Bag bag = new Bag(0,1000,Item.initializeItemBook());
        NPC_MERCHANT shop = new NPC_MERCHANT("Merchant 1","s",100,100,10,10,10,10,10,bag);
        HashMap<Coordinate,NPC_MERCHANT> hp = new HashMap<>();
        Coordinate coor = new Coordinate(0,0);
        hp.put(coor,shop);
//        List<NPC_MERCHANT> shops = new LinkedList<>();
//        shops.add(shop);
        saveMERCHANTNPC(hp);
    }

}
