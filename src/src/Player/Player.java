package Player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import navigation.Placement;

import java.io.*;
import java.lang.reflect.Type;
//todo attack and defense (by Yixiang Yin)
public class Player {
    String name;
    int HP;
    int maxHP;
    int money; // (how many gold he has)
    Bag bag;
    Placement placement;
    public Player(String name, int HP, int maxHP, int money){
        this.name = name;
        this.HP = HP;
        this.maxHP = maxHP;
        this.money = money;
        bag = new Bag();//default bag
        placement = new Placement();
    }

    /*
    Consume an consumable item
     */
    public String consume(Item i){
        if (i.type.equals("consumable") && bag.inMyBag(i)){
            HP+=i.properties.get("health").intValue();
            bag.drop(i);
            return "You've successfully consume "+i.name+" .";
        }
        else if (!i.type.equals("consumable")) return "You can't consume "+i.name+" .";
        else return "You don't have "+i.name+" .";
    }

    /**
     * Save player as json
     */
    // TODO: This method is not tested yet, check and complete if needed - Guanming
    public void save() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(FileWriter fw = new FileWriter(this.name)){ // name json file with player's name
            gson.toJson(this, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * load player from json
     * @param playerName the name of specific player
     */
    // TODO: This method is not tested yet, check and complete if needed - Guanming
    public static Player load(String playerName) {
        File file = new File("json_files/"+ playerName +".json");

        Gson gson = new Gson();
        JsonReader jsonReader = null;

        final Type CUS_LIST_TYPE = new TypeToken<Player>() {}.getType();

        try{
            jsonReader = new JsonReader(new FileReader(file));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return gson.fromJson(jsonReader, CUS_LIST_TYPE);
    }

    /**
     * Check if this room has a mosnter,
     * - if yes, return the danger level and name of the monster
     * - else, return there is no monster
     * @return
     */
    public String checkMonsterType() {
        this.getPlacement().getAbnormalPoints();
        return null;
    };

    /**
     * Take a item from current room, given an item name
     * @param name
     * @return
     */
    public Boolean getItemFromRoom(String name){
        Item item = this.placement.getBag().getItemByName(name);
        if (item == null){
            return false;
        } else {
            this.bag.put(item);
            this.placement.getBag().drop(item);
            return true;
        }
    }

    /**
     * Put back a item from player's bag to room, given an item name
     * @param name
     * @return
     */
    public Boolean dropItemFromBag(String name){
        Item item = this.bag.getItemByName(name);
        if (item == null){
            return false;
        } else {
            this.placement.getBag().put(item);
            this.bag.drop(item);
            return true;
        }
    }

    public String showPlayerStat() {
        return "Player name: "+ getName() +"\n"+
               "CurrentHP: "  + getHP() +"\n"+
               "MaxHP: "      + getMaxHP() +"\n"+
               "Money: "      + getMoney();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Bag getBag() {
        return bag;
    }

    public Placement getPlacement() {
        return placement;
    }
}
