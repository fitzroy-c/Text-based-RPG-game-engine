package Player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import navigation.Coordinate;
import navigation.Placement;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * similar to a extended class from entity
 */
public class Player {

    private String name;
    private int HP;
    private int maxHP;
    private int money; // (how many gold he has)
    private int xp; //level=(int)xp/10 + 1
    private int level;
    private int armour; //defense
    private int damage;
    private double criticalChance;

    private int factor = 2; //critical hit factor

    Bag bag;
    Placement placement;//Coordinate
    //NEW A PLAYER
    //TODO need fix
    public Player(String name){
        this.name = name;

        Random random=new Random();

        this.xp = 100;//default 0
        this.money = random.nextInt(20);
        this.level = (int)xp /100;
        this.bag = new Bag(5); //default bag capacity 5

        //TODO don't know if it is right.
        Coordinate coordinate = new Coordinate(0,0);
        this.placement = new Placement(coordinate,"player location");

        this.maxHP = random.nextInt(50)+(level*50);
        this.armour = random.nextInt(10)+(level*5);
        this.damage = random.nextInt(10)+(level*30);
        this.criticalChance = level*0.01; //all these 4 may cause a problem that 1 level up may cause different change

        this.HP = maxHP;
    }
    //CHANGE A PLAYER
//    public Player(String name,int xp, int money, int level, int health, int maxHP){
//        this.name = name;
//
//        Random random=new Random();
//
//
//        this.xp = 100;//default 0
//        this.money = random.nextInt(20);
//        this.level = (int)xp /100;
//        this.bag = new Bag(5); //default bag capacity 5
//
//        //TODO don't know if it is right.
//        Coordinate coordinate = new Coordinate(0,0);
//        this.placement = new Placement(coordinate,"player location");
//
//        this.maxHP = random.nextInt(50)+(level*50);
//        this.armour = random.nextInt(10)+(level*5);
//        this.damage = random.nextInt(10)+(level*30);
//        this.criticalChance = level*0.01; //all these 4 may cause a problem that 1 level up may cause different change
//
//        this.HP = maxHP;
//
//    }

    /**
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
     * Consume a consumable item, given a item name
     * - Consumable item increases the HP of player.
     * @param itemName
     * @return
     */
    public String consumeByItemName(String itemName){
        Item item = this.bag.getItemByName(itemName);
        if (item == null)
            return "You don't have "+ itemName +" in your bag.";
        else {
            if (item.type.equals("consumable")){
                this.HP += item.properties.get("health").intValue();
                this.bag.drop(item);
                return "You've successfully consume "+itemName+" .";
            }
            else {
                return itemName+" is not consumable.";
            }
        }
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
     * Check if this room has a monster,
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
    public String getItemFromRoom(String name){
        Item item = this.placement.getBag().getItemByName(name);
        if (item == null){
            return "There is no item named "+name+" inside current room";
        } else {
            if ((this.bag.currentWeight + item.getWeight()) > this.bag.maxWeight)
                return "Can't add item to your bag as the weight of your bag will exceeded";
            this.bag.put(item);
            this.bag.currentWeight += item.getWeight();
            this.placement.getBag().drop(item);
            return item.name+" is added to your bag";
        }
    }

    /**
     * Put back a item from player's bag to room, given an item name
     * @param name
     * @return
     */
    public boolean dropItemFromBag(String name){
        Item item = this.bag.getItemByName(name);
        if (item == null){
            return false;
        } else {
            this.placement.getBag().put(item);
            this.bag.drop(item);
            this.bag.currentWeight -= item.getWeight();
            return true;
        }
    }

    public boolean goToDirection(String direction){


        return false;
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
