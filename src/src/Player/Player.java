package Player;

import AbnormalPoints.*;
import Card.Element;
import CommandParser.CommandTokenizer;
import CommandParser.Parser;
import Options.Control;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import navigation.Coordinate;
import navigation.Direction;
import navigation.Place;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

/**
 * similar to a extended class from entity
 */
public class Player {

    private String name;
    private int HP;
    private int maxHP;
    public int money; // (how many gold he has)
    private int xp;    // current xp
    private int maxXP; // max xp that upgrade a player's level once reached
    private int xpPerLv;
    private int level;
    private int armour; //defense
    private int damage;
    private double criticalChance;
    private double maxCriticalChance;
    Bag bag;
    Place place; //Coordinate
//    HashMap<Coordinate, Place> mapData;
    HashMap<Coordinate, NPC_TALK> map_npcTData;
    HashMap<Coordinate, NPC_MERCHANT> map_npcMData;
    HashMap<Coordinate, Bag> map_bagData;

    /**
     * Some variables
     */
    Random random = new Random();
    PlayerAttributes pa = PlayerAttributes.loadPlayerAttributes();

    /**
     * Constructor of new player by giving a name
     */
    public Player(String name){
        this.name = name;
        this.maxHP = random.nextInt(pa.initRandomMaxHP) + pa.initBaseMaxHP;
        this.HP = maxHP;
        this.money = random.nextInt(pa.initRandomMoney) + pa.initBaseMoney;
        this.xp = 0; // default 0
        this.maxXP = pa.initMaxXP;
        this.xpPerLv = pa.initXPPerLv;
        this.level = 1;
        this.armour = random.nextInt(pa.initRandomArmor)+ pa.initBaseArmor;
        this.damage = random.nextInt(pa.initRandomDamage)+ pa.initBaseDamage;
        this.criticalChance = pa.initCriticalChance;
        this.maxCriticalChance = pa.initMaxCriticalChance;
        this.bag = new Bag(pa.initBagWeight);
//        this.setMapData(loadOriginalMapData());

        // load json files
//        this.setMap_npcMData(loadOriginalMerchantNPCs());
        this.setMap_npcTData(loadOriginalTalkNPCs());
        this.setMap_bagData(loadOriginalItems());
        // get data from json to generate the initial place (if any)
        this.place = new Place(new Coordinate(pa.initXCoordinate, pa.initYCoordinate), "", 0,
                exactBothNPCs(new Coordinate(pa.initXCoordinate, pa.initYCoordinate), map_bagData),
                exactBothNPCs(new Coordinate(pa.initXCoordinate, pa.initYCoordinate),
                        this.getMap_npcTData(), this.getMap_npcMData()));
//        this.place = getMapData().get(new Coordinate(pa.initXCoordinate, pa.initYCoordinate));
    }

    /**
     * call once each time you attack
     * calculate player's new attribute as level increases, given a player
     */
    public void UpdatePlayerAttribute(){
        while(this.xp >= this.maxXP){
            this.xp -= this.maxXP; // reset xp
            this.level += 1; // increase level
            this.maxXP = maxXP + xpPerLv; // increase xpFactor

            // update other attributes (maxHP, armor, damage, criticalChance, HP)
            this.maxHP += pa.maxHPIncreasePerLv;
            this.armour += pa.armorIncreasePerLv;
            this.damage += pa.damageIncreasePerLv;

            if (this.criticalChance + pa.criticalChanceIncreasePerLv > this.maxCriticalChance)
                this.criticalChance = this.maxCriticalChance;
            else
                this.criticalChance += pa.criticalChanceIncreasePerLv;

            this.HP = maxHP; // recover all hp once upgraded
        }
    }

    /**
     * Save player as json
     * @author Guanming Ou
     */
    public void save() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(FileWriter fw = new FileWriter("json_files/player_save/" + this.name)){ // name json file with player's name
            gson.toJson(this, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save item as json
     * @author Guanming Ou
     */
    public void saveItem(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(FileWriter fw = new FileWriter("json_files/original_data/Items.json")){ // name json file with player's name
            gson.toJson(this.map_bagData, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Save talk npc as json
     * @author Guanming Ou
     */
    public void saveTALKNPC(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(FileWriter fw = new FileWriter("json_files/original_data/TalkNPC.json")){ // name json file with player's name
            gson.toJson(this.map_npcTData, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Save merchant npc as json
     * @author Guanming Ou
     */
    public void saveMERCHANTNPC(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(FileWriter fw = new FileWriter("json_files/original_data/MerchantNPC.json")){ // name json file with player's name
            gson.toJson(this.map_npcMData, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    /**
//     * Not used in normal gave, but is used for updating items in original json file
//     * @author Guanming Ou
//     */
//    public void saveMap(){
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//        try(FileWriter fw = new FileWriter("json_files/original_data/map.json")){ // name json file with player's name
//            gson.toJson(this.mapData, fw);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Not used in normal game, but is used for updating player attributes in original data json file
     * @author Guanming Ou
     */
    public void savePlayerAttributes(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(FileWriter fw = new FileWriter("json_files/original_data/Player_original_attributes.json")){ // name json file with player's name
            gson.toJson(this.pa, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //TODO: test only, delete later
    public Player(){
    }

    /**
     * load player from json
     * @param playerName the name of specific player
     * @author Guanming Ou
     */
    public static Player load(String playerName) {
        File file = new File("json_files/player_save/"+ playerName +".json");

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
     * load original items data from json_files/original_data/
     * @author Guanming Ou
     */
    public static HashMap<Coordinate, Bag> loadOriginalItems() {
        File file = new File("json_files/original_data/Items.json");

        Gson gson = new Gson();
        JsonReader jsonReader = null;

        try{
            jsonReader = new JsonReader(new FileReader(file));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return gson.fromJson(jsonReader, HashMap.class);
    }

    /**
     * load original talk npc data from json_files/original_data/
     * @author Guanming Ou
     */
    public static HashMap<Coordinate, NPC_TALK> loadOriginalTalkNPCs() {
        File file = new File("json_files/original_data/TalkNPC.json");

        Gson gson = new Gson();
        JsonReader jsonReader = null;

        try{
            jsonReader = new JsonReader(new FileReader(file));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return gson.fromJson(jsonReader, HashMap.class);
    }

    /**
     * load original merchant npc data from json_files/original_data/
     * @author Guanming Ou
     */
    public static HashMap<Coordinate, NPC_MERCHANT> loadOriginalMerchantNPCs() {
        File file = new File("json_files/original_data/MerchantNPC.json");

        Gson gson = new Gson();
        JsonReader jsonReader = null;

        try{
            jsonReader = new JsonReader(new FileReader(file));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return gson.fromJson(jsonReader, HashMap.class);
    }

//
//    /**
//     * load original map data from json_files/original_data/
//     * @author Guanming Ou
//     */
//    public static HashMap<Coordinate, Place> loadOriginalMapData() {
//        File file = new File("json_files/original_data/map.json");
//
//        Gson gson = new Gson();
//        JsonReader jsonReader = null;
//
//        try{
//            jsonReader = new JsonReader(new FileReader(file));
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return gson.fromJson(jsonReader, HashMap.class);
//    }

    /**
     * Given data from orginal_data, search hashmap's 'place' by coordinate keys
     * @param coord The coordinate of the place's information that you want to get
     * @return Bag in hashmap or null if not found
     * @author Guanming Ou
     */
    public Place SearchItemFromHashMap(Coordinate coord, HashMap<Coordinate, Place> hashmap){
        return hashmap.get(coord);
    }

    /**
     * Consume an consumable item
     * TODO: more function should be add. if the item has damage or what,should reset the monster.hp player's gold/xp etc.
     * TODO: item should be divided first and then play its function
     * @author: Yixiang Yin
     **/
    public String consume(Item i){
        if (i.type.equals("consumable") && bag.searchInBag(i)){
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
     * @author yitao chen
     * @return
     */

    public String checkMonster() {
        int dangerRate = this.place.getDangerRate();
        String string = "";
        if (dangerRate<=0){
            string+="This place is safe.\n";
        }else if (dangerRate<=2){
            string+="This place is quiet.\n";
        }else if (dangerRate==3){
            string+="You can hear monsters roaring.\n";
        }else {
            string+="Quite a danger zone, Run!\n";
        }
        for (int i = 0; i < this.place.getAbnormalPoints().size(); i++) {
            if (this.place.getAbnormalPoints().get(i).abnormalPointType== AbnormalPoint.AbnormalPointType.MONSTER){
                ///change battleOption here bill
                Control.setCurrentAb(this.place.getAbnormalPoints().get(i));
                ///this give monster list to Option
                Control.addMonsters(this.place.getAbnormalPoints().get(i));
                ///
                return string + "There is a monster: "+this.place.getAbnormalPoints().get(i).getName()+"\n";
            }
        }
        return string + "There is no monster here.\n";
    }

    /**
     * this method here for the menu
     * to check if current location have
     * NPCs
     * @author Zihong Yuan
     */
    public void checkNPCs() {
        for (int i = 0; i < this.place.getAbnormalPoints().size(); i++) {
            AbnormalPoint.AbnormalPointType current =  this.place.getAbnormalPoints().get(i).abnormalPointType;

            if (current == AbnormalPoint.AbnormalPointType.NPC_TALK
                || current == AbnormalPoint.AbnormalPointType.NPC_MERCHANT){
                ///change battleOption here bill
                //Control.setCurrentAb(this.place.getAbnormalPoints().get(i));
                ///this give NPC list to Option
                Control.addNPCs(this.place.getAbnormalPoints().get(i));
                ///
            }
        }
    }

    /**
     * call once each time player moves its place//TODO need add
     * The MonsterGenerator generates random monsters appropriately according
     * to the player's level and place
     * @author yitao chen
     * @return null, if there is not. return the monster if it has
     */
    public void generateMonster(){
        Random random = new Random();
        int randomInt = random.nextInt(6) + 1; // 1-6
        // place danger rate 1-5 consider the the plan is to make dangerRate 1(easy)-5(danger)
        // 6 definite have a monster -- test only
        // 0 or less definite no monster
        if (randomInt <= this.place.getDangerRate()) {
            //randomly choose 1 from 5 type
            //int randomInt2 = random.nextInt(6);
            int playerLevel = this.level;
            switch (random.nextInt(5)) {
                case 0:
                    /**
                     * A monster with high health and damage, but low armour.
                     */
                    MonsterAttributes giant = new MonsterAttributes("giant","A monster with high health and damage, but low armour.",
                            150, 8, 6, 3,40,3,
                            0.03,50, 3,15, 11, Element.Normal);
                    //System.out.println("You are facing a giant");
                    this.place.addAbnormalPoint(new Monster(giant,playerLevel));
                case 1:
                    /**
                     * A normal monster , with slight armour.
                     */
                    MonsterAttributes goblin = new MonsterAttributes("goblin", "A normal monster , with slight armour.",
                            55,6, 0,3,12,2,
                            0.02,10, 3,0,5, Element.Normal);
                    //System.out.println("You are facing a goblin");
                    this.place.addAbnormalPoint( new Monster(goblin,playerLevel));
                case 2:
                    /**
                     * A quite weak monster.
                     */
                    MonsterAttributes skeleton = new MonsterAttributes("skeleton", "A quite weak monster.",
                            50,3, 0, 1, 8,1,
                            0.02,10, 3, 0,3,Element.Normal);
                    //System.out.println("You are facing a skeleton");
                    this.place.addAbnormalPoint( new Monster(skeleton,playerLevel));
                case 3:
                    /**
                     * A monster without low damage, but high health and armour.
                     */
                    MonsterAttributes troll = new MonsterAttributes("troll", "A monster without low damage, but high health and armour.",
                            70,11, 0,12,20,3,
                            0.05,75, 3,25,10,Element.Normal);
                    //System.out.println("You are facing a troll");
                    this.place.addAbnormalPoint( new Monster(troll,playerLevel));
                default:
                    /**
                     * A normal wild creature
                     */
                    MonsterAttributes wolf = new MonsterAttributes("wolf", "A wolf as you see",
                            35,3, 0,0,15,2,
                            0.04,25, 3,0,2,Element.Normal);
                    //System.out.println("You are facing a wolf");
                    this.place.addAbnormalPoint(new Monster(wolf,playerLevel));
            }
        }
        //System.out.println("You are in a safe place");
    }


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
    public boolean dropItemFromBag(String name) {
        Item item = this.bag.getItemByName(name);
        if (item == null) {
            return false;
        } else {
            this.place.getBag().put(item);
            this.bag.drop(item);
            return true;
        }
    }

    /**
     * Update player's place given a direction
     *
     * maxX,maxY describe the size of the map (from [0][0]to[maxX][maxY])
     * @param direction north | east | south | west
     * @return true: update successfully, false: update unsuccessfully
     * @author Yixiang Yin, modified by yitao chen and Guanming Ou
     */

    public String goToDirection(String direction){
        int maxX = 30;
        int maxY = 30;
        Coordinate nextCoord = this.place.getCoordinate();

        switch (direction){
            case "north":
                if (this.place.getCoordinate().y==maxY){
                    return "direction out of map's boundary";
                }
                nextCoord.goNorth();
                goToDirectionHelper(nextCoord);
                this.place.printPlace();
            break;
            case "east":
                if (this.place.getCoordinate().x==maxX){
                    return "direction out of map's boundary";
                }
                nextCoord.goEast();
                goToDirectionHelper(nextCoord);
                this.place.printPlace();
            break;

            case "south":
                if (this.place.getCoordinate().y==0){
                    return "direction out of map's boundary";
                }
                nextCoord.goSouth();
                goToDirectionHelper(nextCoord);
                this.place.printPlace();
            break;

            case "west":
                if (this.place.getCoordinate().x==0){
                    return "direction out of map's boundary";
                }
                nextCoord.goWest();
                goToDirectionHelper(nextCoord);
                this.place.printPlace();
            break;
        }
        return "You've moved to "+direction+" direction";
    }

    /**
     * check if the next coordinate is already store inside the map data, and update if required
     * @param nextCoord the next coordinate that the player wants to move to
     * @author Guanming Ou
     */
    public void goToDirectionHelper(Coordinate nextCoord){
//        HashMap<Coordinate, Place> map = this.getMapData();
        HashMap<Coordinate, NPC_TALK> npc_t = this.map_npcTData;
        HashMap<Coordinate, NPC_MERCHANT> npc_m = this.map_npcMData;
        HashMap<Coordinate, Bag> bag = this.map_bagData;
        // update the npc merchant json of current coordinate
//        if (npc_m.containsKey(this.place.getCoordinate())){ // if there is current coordinate exist in map data
//            NPC_MERCHANT npcm = extractNPCMerchant(); // find npc talk inside the abnormal point list
//            if (npcm != null){
//                npc_m.remove(this.place.getCoordinate()); // remove current coordinate from map data
//                npc_m.put(this.place.getCoordinate(), npcm); // insert current place to map data (one npc talk only)
//            }
//        }
        // update the npc talk json of current coordinate
        if (npc_t.containsKey(this.place.getCoordinate())){ // if there is current coordinate exist in map data
            NPC_TALK npct = extractNPCTalk(); // find npc talk inside the abnormal point list
            if (npct != null){
                npc_t.remove(this.place.getCoordinate()); // remove current coordinate from map data
                npc_t.put(this.place.getCoordinate(), npct); // insert current place to map data (one npc talk only)
            }
        }
        // update the bag json of current coordinate
        if (bag.containsKey(this.place.getCoordinate())){ // if there is current coordinate exist in map data
            bag.remove(this.place.getCoordinate()); // remove current coordinate from map data
            bag.put(this.place.getCoordinate(), this.place.getBag()); // insert current place to map data
        }

//        // update next coordinate inside json
//        if (map.containsKey(nextCoord)) // if (there is next coordinate inside the map data)
//            this.setPlace(map.get(nextCoord)); // update current place with the place inside the map data
        int updated = 0;
        Place nextPlace = new Place(nextCoord, "", 0,new Bag(100), new ArrayList<>());
        nextPlace.setAbnormalPoints(exactBothNPCs(nextCoord, npc_t, npc_m)); // update player place's abnormal point
        if (! nextPlace.getAbnormalPoints().isEmpty()) // check if operation did get npc
            updated++;
        if (bag.containsKey(nextCoord)){ // try and get bag from json if any
            nextPlace.setBag(bag.get(nextCoord)); // update player's place's storage bag
            updated++;
        }

        if (updated == 0){ // randomly generate place named wild area with random danger rate and monster
            nextPlace.setDescription("Wild area");
            nextPlace.setDangerRate(random.nextInt(5));
            generateMonster();
        }
        // update all information
        this.setPlace(nextPlace);
        this.setMap_npcTData(npc_t);
        this.setMap_npcMData(npc_m);
        this.setMap_bagData(bag);
    }

    /**
     * From a list of abnormalpoints, find the talk npc
     * @return NPC_TALK
     * @author Guanming Ou
     */
    public NPC_TALK extractNPCTalk(){
       List<AbnormalPoint> alist = this.place.getAbnormalPoints();
       for (AbnormalPoint a : alist){
           if (a.abnormalPointType == AbnormalPoint.AbnormalPointType.NPC_TALK)
               return (NPC_TALK) a;
       }
       return null;
    }

    /**
     * From a list of abnormalpoints, find the npc merchant
     * @return NPC_MERCHANT
     * @author Guanming Ou
     */
    public NPC_MERCHANT extractNPCMerchant(){
        List<AbnormalPoint> alist = this.place.getAbnormalPoints();
        for (AbnormalPoint a : alist){
            if (a.abnormalPointType == AbnormalPoint.AbnormalPointType.NPC_MERCHANT)
                return (NPC_MERCHANT) a;
        }
        return null;
    }

    /**
     * Extract npcs from hashmaps (load from json), and return as Abnormalpoint List
     * @author Guanming Ou
     */
    public List<AbnormalPoint> exactBothNPCs(Coordinate c, HashMap<Coordinate, NPC_TALK> npc_t, HashMap<Coordinate, NPC_MERCHANT> npc_m){
        List<AbnormalPoint> abpoints = new ArrayList<AbnormalPoint>();

        if (npc_t.containsKey(c)) { // try to get npc merchant from json if any
            abpoints.add(npc_m.get(c));
        }
        if (npc_t.containsKey(c)){ // try to get npc talk from json if any
            abpoints.add(npc_t.get(c));
        }
        return abpoints;
    }
    /**
     * Extract bag from hashmaps (load from json), and return a bag
     * @author Guanming Ou
     */
    public Bag exactBothNPCs(Coordinate c, HashMap<Coordinate, Bag> bag){
        if (bag.containsKey(c)) { // try to get npc merchant from json if any
            return bag.get(c);
        }
        return new Bag(100);
    }



    /**
     * check the player is alive or not
     * built to call before moving to next coordinate
     * alive return true; dead return false
     * @author yitao chen
     */
    public boolean playerSurvive() {
        if (this.HP<=0){
            return false;
        }
        return true;
    }

    /**
     * check if this round is a critical hit
     * @author yitao chen
     * @param criticalChance from 0.00-1.00 min step 0.01
     * @author yitao chen
     */
    public boolean criticalCheck(double criticalChance){
        Random random = new Random();
        int chance = (int)criticalChance*100;
        int randomInt = random.nextInt(100); //
        if (randomInt<=chance){
            return true;}
        else{
            return false;
        }
    }
    /**
     * be used when user want to buy things from the user(user enters "trade" command)
     *  what does it do?
     * - It checks if there is a shop(merchant)
     * - Print the content of the shop
     * - Give user choices to choose from, handle invalid item index
     * (Assumption: user only can purchase one thing each time)
     * @author Yixiang Yin
     */

    public void buyCommandHandler(){
        NPC_MERCHANT shop = (NPC_MERCHANT) SearchInAbnormalPoints(AbnormalPoint.AbnormalPointType.NPC_MERCHANT);
        if (shop==null) {
            System.out.println("There is no one for you to buy things from");
        }
        if (!NPC_MERCHANT.showTheShop(shop)) return; // indicating nothing to buy(shop is empty)
        else {
            System.out.println("Please type the corresponding item index to purchase corresponding item.");
            Scanner s = new Scanner(System.in);
            int userChoice = s.nextInt();
            if (userChoice>=shop.npcBag.getItems().size()) System.out.println("Invalid item index, please try again.");;
            Item itemPuchase = shop.npcBag.getItems().get(userChoice);
            boolean succ = shop.buyFromNPC(this,itemPuchase.name);
            System.out.println(shop.outputStrForBuyFromNPC(this,itemPuchase.name,succ));
        }
    }


    /**
     * Description suggested by the name(include null case)
     * @return boolean: true, if found, else, false
     * @author Yixiang Yin
     */
    public AbnormalPoint SearchInAbnormalPoints(AbnormalPoint.AbnormalPointType type){
        List<AbnormalPoint> APoints =  this.place.getAbnormalPoints();
        if (APoints==null) return null;
        for (AbnormalPoint p:APoints){
            if (p.abnormalPointType==type){
                return p;
            }
        }
        return null;
    }

    /**
     * This talk to the npc at the given coordinate
     * @return string
     * @author Guanming Ou
     */
    public String talk(){
        // check if here can talk
        List<AbnormalPoint> currentPlace =  this.place.getAbnormalPoints();
        if (currentPlace == null)
            return "There is no one to talk to.";
        // check if there is npc that you can talk to
        NPC_TALK npc_t = null;
        for (AbnormalPoint a : currentPlace){
            if (a.abnormalPointType == AbnormalPoint.AbnormalPointType.NPC_TALK){
                npc_t = (NPC_TALK) a;
                break;
            }
        }
        if (npc_t == null)
            return "You can't talk to a monster or a merchant.";
        else { // there is a npc that you can talk to
            // check if npc has talked before
            if (npc_t.isHasEndedTalk())
                return "You cannot talk to already talked npc.";
            // continue dialog for conversation
            boolean continueTalk = true;
            Scanner s = new Scanner(System.in);
            String playerResponse;
            DialogTree.DialogType dType = DialogTree.DialogType.CONTINUE;

            while (continueTalk){
                System.out.println(npc_t.getDialogTree().getRoot().getNpcDialog()); // npc's dialog
                System.out.println("Your response(s):");
                System.out.println(npc_t.getDialogTree().printAvailableDialog()); // show player available response
                playerResponse = s.next(); // get player's response

                // convert player's respond into integer
                if (isAllInt(playerResponse)){
                    int respondIndex = Integer.parseInt(playerResponse);
                    // check if player's response match one of the index
                    DialogTree.Dialog nextDialog = npc_t.getDialogTree().matchDialog(respondIndex);
                    if (nextDialog != null){
                        // check if the conversation meet end state
                        if (nextDialog.getDtype() == DialogTree.DialogType.END_BLESS_HP){
                            System.out.println(nextDialog.getNpcDialog()); // npc dialog
                            return npc_t.hpBless(this); // increase hp, and return final message
                        }
                        else if (nextDialog.getDtype() == DialogTree.DialogType.END_BLESS_ARMOR){
                            System.out.println(nextDialog.getNpcDialog()); // npc dialog
                            return npc_t.armorBless(this); // increase armor, and return final message
                        }
                        else if (nextDialog.getDtype() == DialogTree.DialogType.END_BLESS_DAMAGE) {
                            System.out.println(nextDialog.getNpcDialog()); // npc dialog
                            return npc_t.damageBless(this); // increase damage, and return final message
                        }
                        else if (nextDialog.getDtype() == DialogTree.DialogType.END_GIVE_GOLD){
                            this.money += npc_t.getGold(); // npc give gold
                            npc_t.setGold(0); // clear npc gold
                            npc_t.setHasEndedTalk(true);
                            return nextDialog.getNpcDialog(); // return final npc dialog
                        }
                        else if (nextDialog.getDtype() == DialogTree.DialogType.END_GIVE_ITEM){
                            npc_t.getNpcBag().giveAllItemTo(this); // npc give all item
                            npc_t.setHasEndedTalk(true); // npc ended
                            return nextDialog.getNpcDialog();  // return final npc dialog
                        }
                        else if (nextDialog.getDtype() == DialogTree.DialogType.END_NONE){
                            npc_t.setHasEndedTalk(true); // npc ended
                            return nextDialog.getNpcDialog(); // increase damage, and return final message
                        }
                        else if (nextDialog.getDtype() == DialogTree.DialogType.END_ATTACK){
                            System.out.println(nextDialog.getNpcDialog());
                            Monster npcMonster = npc_t.transformIntoMonster(); // convert this npc into monster
                            this.place.removeAbnormalPoint(npc_t); // remove npc from player's place
                            this.place.addAbnormalPoint(npcMonster); // npc return as monster
                            return this.attack(); // not sure what string place here, as attack may already outputted a string
                        }
                        // this is continue
                        DialogTree newTree = new DialogTree();
                        newTree.setRoot(nextDialog);
                        npc_t.setDialogTree(newTree);
                    }
                }
                else
                    System.out.println("Invalid respond index, please try again");
            }
        }
        return null;
    }

    /**
     * Check a string is completely int, before using toInt method
     */
    public boolean isAllInt(String input){
        input = input.trim(); // remove white space
        if (input.isEmpty())
            return false;
        char firstChar = input.charAt(0);
        if (Character.isDigit(firstChar)){
            int count = 0;
            while (count < input.length()) {
                if (! Character.isLetter(input.charAt(count)))
                    return false;
                count++;
            }
        }
        return true;
    }

    /**
     * Trace down the dialog tree, given the selection of reply by the player
     * @param indexChoice the choice of the player (int)
     * @return a new DialogTree with new npc dialog and children dialogs
     * @author: Guanming Ou
     */
//    public DialogTree applyReply(int indexChoice){
//        if (this.root == null | this.root.nextDialogs == null) return null;
//
//        DialogTree.Dialog currentNode = this.root;
//        List<DialogTree.Dialog> children = currentNode.nextDialogs;
//        //TODO: Make it so that it make to npc do such things following its type
//        for (DialogTree.Dialog d : children){
//            if (d.index == indexChoice){
//                DialogTree t = new DialogTree();
//                t.root = d;
//                t.rootString = d.npcDialog;
//                return t;
//            }
//        }
//        return null;
//    }



    /**
     * you should call generateMonster function before calling the attack function
     * default critical hit = normal *2
     * @author yitao chen
     * @return
     */
    public String attack(){
        String string = "Ready to attack:";
        for (int i = 0; i < this.place.getAbnormalPoints().size(); i++) {
            if (this.place.getAbnormalPoints().get(i).abnormalPointType== AbnormalPoint.AbnormalPointType.MONSTER) {
                Monster monster = (Monster)this.place.getAbnormalPoints().get(i);
                string += monster.getName()+"\n"+monster.getIntro()+"\n";
                Random random = new Random();
                int monsterHP = monster.getHP();
                while(monsterHP>0&&this.HP>0){
                    if (criticalCheck(this.criticalChance)){
                        int realDamage = Math.max(this.damage*2 - monster.getArmour(),0);
                        monsterHP = monsterHP - realDamage;
                        string += "Nice, you have made a critical hit. "+monster.getName()+" -"+realDamage+"HP\n";
                    }
                    int realDamage = Math.max(this.damage - monster.getArmour(),0);
                    monsterHP = monsterHP - realDamage;
                    string += "Normal hit."+monster.getName()+" -"+realDamage+"HP\n";
                    if (criticalCheck(monster.getCritChance())){
                        int realDamage1 = Math.max(monster.getDamage()*2 - this.armour,0);
                        this.HP = this.HP - realDamage1;
                        string += "Sadly. You got a critical hit. "+this.name+" -"+realDamage1+"HP\n";
                    }
                    int realDamage1 = Math.max(monster.getDamage() - this.armour,0);
                    this.HP = this.HP - realDamage1;
                    string += "You got a hit. "+this.name+" -"+realDamage1+"HP\n";
                }
                if (this.HP<=0){
                    return string+"Your adventure journey ended here. The magic world will remember you\n";
                }else{
                    this.xp += monster.getXpGain();
                    this.money += monster.getGold();
                    UpdatePlayerAttribute();
                    return string+"You have defeated the monster, congratulations.\n";
                }
            }
        }
        return string+"There is no monster to attack.\n";
    }

    /**
     * player bribe the monster if he can, if failed ,turn to attack
     * @author yitao chen
     * @return
     */
    public String bribe(){
        String string = "Ready to bribe:\n";
        for (int i = 0; i < this.place.getAbnormalPoints().size(); i++) {
            if (this.place.getAbnormalPoints().get(i).getClass()==Monster.class) {
                Monster monster = (Monster)this.place.getAbnormalPoints().get(i);
                if (this.money>=monster.getGold()){
                    this.money -= monster.getGold();
                    monster.setHP(0); // you can check the monster's hp to see if it succeed as well
                    return string+"Bribe successfully.\n";
                }
                String string1 = attack();
                return string+"Bribe failed. Not enough gold.\n"+string1;
            }
        }
        return string + "There is no monster for you to bribe\n";

    }

    /**
     * player retreat, which allow player to escape from current fight. hold the coordinate
     * @author yitao chen
     * @return
     */
    public String retreat(){
        String string = "Ready to retreat:\n";
        for (int i = 0; i < this.place.getAbnormalPoints().size(); i++) {
            if (this.place.getAbnormalPoints().get(i).getClass()==Monster.class) {
                Monster monster = (Monster)this.place.getAbnormalPoints().get(i);
                while(this.HP>0){
                    if (criticalCheck(0.8)){ //80% chance succeed
                        monster.setHP(0); // you can check the monster's hp to see if it succeed as well
                        return string + "Retreat successfully.\n";
                    }else{
                        this.HP = this.HP - Math.max(monster.getDamage() -this.armour,0);
                        string +="Retreat failed.\n";
                    }
                }
                return string+"Your adventure journey ended here. The magic world will remember you\n";
            }
        }
        return string + "There is no monster for you to retreat\n";


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
                "Coordinate"   + this.place.getCoordinate().toString()+"\n";
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

    public void setBag(Bag bag) {
        this.bag = bag;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public void setMap_npcTData(HashMap<Coordinate, NPC_TALK> map_npcTData) {
        this.map_npcTData = map_npcTData;
    }

    public void setMap_npcMData(HashMap<Coordinate, NPC_MERCHANT> map_npcMData) {
        this.map_npcMData = map_npcMData;
    }

    public void setMap_bagData(HashMap<Coordinate, Bag> map_bagData) {
        this.map_bagData = map_bagData;
    }

    public HashMap<Coordinate, NPC_TALK> getMap_npcTData() {
        return map_npcTData;
    }

    public HashMap<Coordinate, NPC_MERCHANT> getMap_npcMData() {
        return map_npcMData;
    }

    public HashMap<Coordinate, Bag> getMap_bagData() {
        return map_bagData;
    }

    //    public void setMapData(HashMap<Coordinate, Place> mapData) {
//        this.mapData = mapData;
//    }
//
//    public HashMap<Coordinate, Place> getMapData() {
//        return mapData;
//    }

    /**
     * This is a subclass for enabling edit player's origianl generate atrributes via json
     * @author Guanming Ou
     */
    public static class PlayerAttributes {
        int maxHPIncreasePerLv;
        int armorIncreasePerLv;
        int damageIncreasePerLv;
        double criticalChanceIncreasePerLv; //from 0.00-1.00 min step 0.01
        int initRandomMaxHP;
        int initBaseMaxHP;
        int initRandomMoney;
        int initBaseMoney;
        int initMaxXP;
        int initXPPerLv;
        int initRandomArmor;
        int initBaseArmor;
        int initRandomDamage;
        int initBaseDamage;
        double initCriticalChance; //from 0.00-1.00 min step 0.01
        double initMaxCriticalChance; //from 0.00-1.00 min step 0.01
        int initBagWeight;
        int initXCoordinate;
        int initYCoordinate;

        public PlayerAttributes(int maxHPIncreasePerLv, int armorIncreasePerLv, int damageIncreasePerLv, double criticalChanceIncreasePerLv, int initRandomMaxHP, int initBaseMaxHP, int initRandomMoney, int initBaseMoney, int initMaxXP, int initXPPerLv, int initRandomArmor, int initBaseArmor, int initRandomDamage, int initBaseDamage, double initCriticalChance, double initMaxCriticalChance, int initBagWeight, int initXCoordinate, int initYCoordinate) {
            this.maxHPIncreasePerLv = maxHPIncreasePerLv;
            this.armorIncreasePerLv = armorIncreasePerLv;
            this.damageIncreasePerLv = damageIncreasePerLv;
            this.criticalChanceIncreasePerLv = criticalChanceIncreasePerLv;
            this.initRandomMaxHP = initRandomMaxHP;
            this.initBaseMaxHP = initBaseMaxHP;
            this.initRandomMoney = initRandomMoney;
            this.initBaseMoney = initBaseMoney;
            this.initMaxXP = initMaxXP;
            this.initXPPerLv = initXPPerLv;
            this.initRandomArmor = initRandomArmor;
            this.initBaseArmor = initBaseArmor;
            this.initRandomDamage = initRandomDamage;
            this.initBaseDamage = initBaseDamage;
            this.initCriticalChance = initCriticalChance;
            this.initMaxCriticalChance = initMaxCriticalChance;
            this.initBagWeight = initBagWeight;
            this.initXCoordinate = initXCoordinate;
            this.initYCoordinate = initYCoordinate;
        }

        /**
         * load PlayerAttributes from json
         * @author Guanming Ou
         */
        public static PlayerAttributes loadPlayerAttributes() {
            File file = new File("json_files/original_data/Player_original_attributes.json");

            Gson gson = new Gson();
            JsonReader jsonReader = null;

            final Type CUS_LIST_TYPE = new TypeToken<PlayerAttributes>() {}.getType();

            try{
                jsonReader = new JsonReader(new FileReader(file));
            }catch (Exception e) {
                e.printStackTrace();
            }
            return gson.fromJson(jsonReader, CUS_LIST_TYPE);
        }
    }
}
