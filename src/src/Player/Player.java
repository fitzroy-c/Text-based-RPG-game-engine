package Player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import navigation.Coordinate;
import navigation.Place;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Random;

/**
 * similar to a extended class from entity
 */
public class Player {

    private String name;
    private int HP;
    private int maxHP;
    private int money; // (how many gold he has)
    private int xp;    // current xp
    private int maxXP; // max xp that upgrade a player's level once reached
    private final double xpFactor;
    private int level;
    private int armour; //defense
    private int damage;
    private double criticalChance;
    private final double maxCriticalChance;
    Bag bag;
    Place place;//Coordinate

    /**
     * Some variables
     */
    Random random = new Random();
    int maxHPIncreasePerLv = 10;
    int armorIncreasePerLv = 2;
    int damageIncreasePerLv = 2;
    double criticalChanceIncreasePerLv = 0.005;
    int initRandomMaxHP = 15;
    int initBaseMaxHP = 10;
    int initRandomMoney =  5;
    int initBaseMoney = 10;
    int initMaxXP = 10;
    double initXPFactor = 1.1;
    int initRandomArmor =  5;
    int initBaseArmor = 1;
    int initRandomDamage =  3;
    int initBaseDamage = 2;
    double initCriticalChance = 0.01;
    double initMaxCriticalChance = 1.00;
    int initBagWeight;
    int initXCoordinate = 0;
    int initYCoordinate = 0;

    /**
     * Constructor of new player by giving a name
     */
    public Player(String name){
        this.name = name;
        this.maxHP = random.nextInt(initRandomMaxHP) + initBaseMaxHP;
        this.HP = maxHP;
        this.money = random.nextInt(initRandomMoney) + initBaseMoney;
        this.xp = 0; // default 0
        this.maxXP = initMaxXP;
        this.xpFactor = initXPFactor;
        this.level = 1;
        this.armour = random.nextInt(initRandomArmor)+ initBaseArmor;
        this.damage = random.nextInt(initRandomDamage)+ initBaseDamage;
        this.criticalChance = initCriticalChance;
        this.maxCriticalChance = initMaxCriticalChance;
        this.bag = new Bag(initBagWeight); //default bag capacity 5
        this.place = new Place(new Coordinate(initXCoordinate,initYCoordinate),"player location");
    }

    /**
     * calculate player's new attribute as level increases, given a player
     */
    public void UpdatePlayerAttribute(){
        if(this.xp >= this.maxXP){
            this.xp -= this.maxXP; // reset xp
            this.level += 1; // increase level
            this.maxXP *= xpFactor; // increase xpFactor

            // update other attributes (maxHP, armor, damage, criticalChance, HP)
            this.maxHP += maxHPIncreasePerLv;
            this.armour += armorIncreasePerLv;
            this.damage += damageIncreasePerLv;

            if (this.criticalChance + criticalChanceIncreasePerLv > this.maxCriticalChance)
                this.criticalChance = this.maxCriticalChance;
            else
                this.criticalChance += criticalChanceIncreasePerLv;

            this.HP = maxHP; // recover all hp once upgraded
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
     * Consume an consumable item
     * @author: Yixiang Yin
     **/
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
     * @param itemName item name in string
     * @return string of hint
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
     * Check if this room has a monster,
     * - if yes, return the danger level and name of the monster
     * - else, return there is no monster
     * @return
     */
    public String checkMonsterType() {
        // TODO: save the game and back to the main menu, need player to have location that stores monster
        this.getPlace().getAbnormalPoints();
        //            Monster monster = new Monster();
//            if (monster == null) {
//                System.out.println("There is a "+ monster.getName() +" around you, WATCH OUT!");
//            } else {
//                System.out.println("There is no monster near you, phew～");
//            }
        return null;
    };

    /**
     * Take a item from current room, given an item name
     * @param name
     * @return String
     * @author: Guanming Ou
     */
    public String getItemFromRoom(String name){
        Item item = this.place.getBag().getItemByName(name);
        if (item == null){
            return "There is no item named "+name+" inside current room";
        } else {
            if (this.bag.put(item)){
                this.place.getBag().drop(item);
                return item.name+" is added to your bag";
            }
            return "Can't add item to your bag as the weight of your bag will exceeded";
        }
    }

    /**
     * Put back a item from player's bag to room, given an item name
     * @param name
     * @return true: successfully droped item, false: item not exist.
     * @author: Guanming Ou
     */
    public boolean dropItemFromBag(String name){
        Item item = this.bag.getItemByName(name);
        if (item == null){
            return false;
        } else {
            this.place.getBag().put(item);
            this.bag.drop(item);
            return true;
        }
    }

    /**
     * Update player's placement given a direction
     * @param direction north | east | south | west
     * @return true: update successful, false: update unsuccessful
     * @author Yixiang Yin
     */
    public boolean goToDirection(String direction){
        // TODO: complete this method which make the player go to the direction.
        switch (direction){
            case "north":this.place.getCoordinate().goNorth();
            break;
            case "east":this.place.getCoordinate().goEast();
            break;

            case "south":this.place.getCoordinate().goSouth();
            break;

            case "west":this.place.getCoordinate().goWest();
            break;

        }
        return true;
    }

    /**
     * This talk to the npc at the given coordinate
     * @return
     */
    public String talk(){
        // TODO: complete this method which talk to NPC at the coordinate
        return null;
    }

    /**
     * This attacks the monster at the player's current placement, and monster will attack by respond
     * @return
     */
    public String attack(){
        // TODO: complete this method which attack to the monster
        return null;
    }

    /**
     * player defence, which increase his armor temperally, and monster will attack by respond
     * @return
     */
    public String defence(){
        // TODO: complete this method which let player defense
        return null;
    }

    /**
     * player retreat, which allow player to escape from current fight and move to next coordinate
     * @return
     */
    public String retreat(String direction){
        // TODO: complete this method which may let player escape from the fight
        return null;
    }

    /**
     * Display a player's current stat
     * @return String that display all this player's current stat
     * @author: Guanming Ou
     */
    public String showPlayerStat() {
        return "Player name: "+ getName()+"\n"+
                "Level: "      + getLevel()+"\n"+
                "XP: "         + getXp()+"/"+getMaxXP()+"\n"+
                "HP: "         + getHP()+"/"+getMaxHP()+"\n"+
                "Damage: "     + getDamage()+"\n"+
                "Armor: "      + getArmour()+"\n"+
                "Criticle Chance: " + getCriticalChance()+"/"+getMaxCriticalChance()+"\n"+
                "Money: "      + getMoney()+"\n"+
                "Coordinate"   + this.place.getCoordinate().toString();
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

    public Place getPlace() {
        return place;
    }

    public int getXp() {
        return xp;
    }

    public int getMaxXP() {
        return maxXP;
    }

    public int getLevel() {
        return level;
    }

    public int getArmour() {
        return armour;
    }

    public int getDamage() {
        return damage;
    }

    public double getCriticalChance() {
        return criticalChance;
    }

    public double getMaxCriticalChance() {
        return maxCriticalChance;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setMaxXP(int maxXP) {
        this.maxXP = maxXP;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setArmour(int armour) {
        this.armour = armour;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setCriticalChance(double criticalChance) {
        this.criticalChance = criticalChance;
    }
}
