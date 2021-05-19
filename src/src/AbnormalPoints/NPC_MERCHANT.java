package AbnormalPoints;

import Player.Bag;
import Player.Player;
import Player.Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import navigation.Coordinate;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * This class mainly held npc that will trade with player
 */
public class NPC_MERCHANT extends AbnormalPoint{
    public Bag npcBag;
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
            int price = wanted.getProperty("price");
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
        if (succ) return "You've successfully purchased "+itemName+" .Happy to see you soon again!";
        else {
            Item wanted = npcBag.getItemByName(itemName); // guarantee non-null
            int price = wanted.getProperty("price");
            if (p.money<price) return "You don't have enough money to purchase "+itemName+" .";
            return "Sorry, I don't have "+itemName+" .";
        }
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
            System.out.println("["+i+"]"+" : "+npc.npcBag.getItems().get(i).getDescription());
            System.out.println();
        }
        System.out.println("-----------------End------------------");
        return true;

    }


    public static void saveMERCHANTNPC(HashMap<Coordinate,NPC_MERCHANT> hp){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(FileWriter fw = new FileWriter("json_files/original_data/MerchantNPC.json")){ // name json file with player's name
            gson.toJson(hp, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Bag bag = new Bag(0,1000,Item.initializeItemBook());
        NPC_MERCHANT shop = new NPC_MERCHANT("Merchant 1","s",100,100,10,10,10,10,10,bag);
        HashMap<Coordinate,NPC_MERCHANT> hp = new HashMap<>();
        Coordinate coor = new Coordinate(1,1);
        hp.put(coor,shop);
        saveMERCHANTNPC(hp);
    }

}
