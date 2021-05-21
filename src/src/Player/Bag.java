package Player;

import AbnormalPoints.AbnormalPoint;
import AbnormalPoints.DialogTree;
import AbnormalPoints.NPC_MERCHANT;
import AbnormalPoints.NPC_TALK;
import Card.Element;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import navigation.Coordinate;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.*;

public class Bag {

    int currentWeight;
    int maxWeight;
    private List<Item> itemList;
    public Bag() {

    }
    /*
    public Bag(){
        this(5, new ArrayList<Item>());
    }

     */
    public Bag(int currentWeight,int maxWeight, List<Item> items) {
        this.currentWeight = currentWeight;
        this.maxWeight = maxWeight;
        this.itemList = items;
        this.currentWeight = 0;
    }
    public Bag(int maxWeight) {
        this(maxWeight, new ArrayList<Item>());
    }
    public Bag(int maxWeight, List<Item> items) {
        this.maxWeight = maxWeight;
        this.itemList = items;
        this.currentWeight = 0;
    }
    public int getMaxWeight() {
        return maxWeight;
    }
    public int getCurrentWeight(){
        return currentWeight;
    }

    //TODO :I think the out put should be list<item>, the strings can be called when the method is called
    //TODO :i am confused about why the following 2 function presents this way
    //TODO : the function still can't call correctly in Placement.java

    /**
     * put an item in the bag, output string to indicate if it succeed.
     * @param i
     * @return String
     * @author: Yixiang Yin
     */
    public String putS(Item i){
        int weightOfItem = i.getWeight();
        if (currentWeight + weightOfItem <= maxWeight) {
            this.itemList.add(i);
            currentWeight += weightOfItem;
            return "You've put it in your bag";
        }
        else {
            return "Your bag is out of storage.(You may drop or consume some items or extend your storage)";
        }
    }

    /**
     * drop things out of the bag, output string to indicate if it succeed.
     * @param i
     * @return String
     * @author: Yixiang Yin
     */
    public String dropS(Item i){
        int weightOfItem = i.properties.get("weight");
        this.itemList.remove(i);
        currentWeight-=weightOfItem;
        return "You've removed "+i.name+" .";
    }

    /**
     * put an item in the bag (Yixiang Yin, modified by Yitao)
     */
    //玩家， 地点
    public boolean put(Item i){
        int weightOfItem = i.getWeight();
        if (currentWeight + weightOfItem <= maxWeight) {
            itemList.add(i);
            currentWeight += weightOfItem;
            return true;
        }
        return false;
    }
    /**
     * drop things out of the bag (Yixiang Yin, modified by Yitao)
     */
    public Item drop(Item i){
        if (searchInBag(i)) {
            int weightOfItem = i.properties.get("weight");
            itemList.remove(i);
            currentWeight -= weightOfItem;
            return i;
        }
        return null;
    }

    /**
     * give all item from a bag to player's bag, if a player's bag cannot hold it as max weight reached,
     * it will then transfer rest of the npc's item into the room
     * npc's bag is garentee to be cleared
     * @param player
     * @return null if nothing can be done
     * @author Guanming Ou
     */
    public Bag giveAllItemTo(Player player){
        Bag tempBag = new Bag(10000);
        for (Item npc_item : this.itemList){
            if (!player.bag.put(npc_item)){ // add to player's bag
                tempBag.put(npc_item);
            }
        }
        if (!tempBag.isEmpty()){ // if player's bag cannot fit any more, put it to current place's bag
            List<Item> roomItem = player.place.getItems();
            for (Item placeItem : roomItem){
                tempBag.put(placeItem);
            }
            player.place.setBag(tempBag);
        }
        return null;
    }

    /**
    * check if one item is in the bag (Yixiang Yin)
    */
    public boolean searchInBag(Item i){
        for (Item item: this.itemList){
            if (i.equals(item)) return true;
        }
        return false;
    }

    /**
     * check if one item is in the bag by itemName (Yixiang Yin)
     */
    public boolean searchInBagByName(String in){
        for (Item item: this.itemList){
            if (item.name.equals(in)) return true;
        }
        return false;
    }
    /**
     * Check if a bag contains a item of given item name (String)
     * @param itemName the name of a item
     * @return The corresponding item (Item)
     */
    public Item getItemByName(String itemName){
        // if the bag is empty
        if (this.itemList.isEmpty())
            return null;

        for (Item i : this.itemList){
            if (i.name.toLowerCase().equals(itemName.toLowerCase()))
                return i;
        }
        // did not find a item
        return null;
    }
//    public static Bag JsonToBag(JsonObject baginfo){
//
//        String cw = String.valueOf(baginfo.get("currentWeight"));
//        String mw = String.valueOf(baginfo.get("maxWeight"));
//
//        JsonArray sProps = baginfo.get("itemList").getAsJsonArray();
//        List<Item> items = new LinkedList<Item>();
//        Item item = null;
//        for (JsonElement je : sProps) {
//            JsonObject itemJO = je.getAsJsonObject();
//            String id = itemJO.get("id").getAsString();
////                System.out.println(id);
//            String type = itemJO.get("type").getAsString();
//            String name = itemJO.get("name").getAsString();
//            String description = itemJO.get("description").getAsString();
//            JsonObject props = itemJO.get("properties").getAsJsonObject();
//            Map<String, Integer> properties = new HashMap<>();
//            for (Map.Entry<String, JsonElement> entry2 : props.entrySet()) {
//                Integer propValue = entry2.getValue().getAsInt();
//                properties.put(entry2.getKey(), propValue);
//            }
//            item = new Item(id, type, name, description, properties);
//            items.add(item);
//        }
//        Bag bag = new Bag(Integer.parseInt(cw), Integer.parseInt(mw), items);
//        return bag;
//    }
    /// {current weight: ..., maxweight, item list} <- this format
    public static Bag JsonToBag2(JsonObject baginfo){
        Gson gson = new Gson();

        final Type LIST_TYPE = new TypeToken<Bag>() {}.getType();

        return gson.fromJson(baginfo,LIST_TYPE);
    }

    public List<Item> getItems() {
        return this.itemList;
    }
    public boolean isEmpty() {
        return this.itemList.isEmpty();
    }
    /**
     * show the content in the bag(Yixiang Yin)
     */
    public void showMyBag() {
        System.out.println("-----------Your bag-----------");
        for (Item i: this.itemList){
            System.out.println(i.name+" : "+i.getDescription());
            System.out.println();
        }
        System.out.println("-------------End--------------");
    }
    public void showPlaceBag2() {
        if (this.itemList.size()==0) System.out.println("Nothing on the floor");
        System.out.println("-----------Items on the floor-----------");
        for (Item i: this.itemList){
            System.out.println(i.name+" : "+i.getDescription());
//            System.out.println();
        }
        System.out.println("-------------End--------------");
    }

    /**
     * show the content in the place's bag (in different format)
     * @author Guanming Ou
     * @return a string description of all item name inside the room
     */
    public String showPlaceBag() {
        String itemNames = "[";
        for (int i =0; i<this.itemList.size(); i++){
            if (i == 0)
                itemNames += this.itemList.get(i).name;
            else
                itemNames += ", "+this.itemList.get(i).name;
        }
        return itemNames+"]";
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }


    public static DialogTree.Dialog JsonToDialog(JsonObject jo){
        Gson gson = new Gson();

        final Type LIST_TYPE = new TypeToken<DialogTree.Dialog>() {}.getType();

        return gson.fromJson(jo,LIST_TYPE);
    }
    public static HashMap<Coordinate, NPC_TALK> JsonToNpcTalkHashMapData(JsonObject jo){
        HashMap<Coordinate, NPC_TALK> hp = new HashMap<>();

        for (Map.Entry<String, JsonElement> entry : jo.entrySet()) {
            String coor = entry.getKey();
            JsonObject dialogTree = entry.getValue().getAsJsonObject();
            JsonObject root = dialogTree.get("dialogTree").getAsJsonObject();
            JsonObject infoOfRoot = root.get("root").getAsJsonObject();
            DialogTree.Dialog dialog = JsonToDialog(infoOfRoot);
            DialogTree dt = new DialogTree(dialog);
            int blessAddMaxHP = dialogTree.get("blessAddMaxHP").getAsInt();
            int blessAddArmour = dialogTree.get("blessAddArmour").getAsInt();
            int blessAddDamage = dialogTree.get("blessAddDamage").getAsInt();
            boolean hasEndedTalk = dialogTree.get("hasEndedTalk").getAsBoolean();
            Bag bag = JsonToBag2(dialogTree.get("npcBag").getAsJsonObject());
            AbnormalPoint.AbnormalPointType abnormalPointType =AbnormalPoint.AbnormalPointType.valueOf(dialogTree.get("abnormalPointType").getAsString());
            String name = dialogTree.get("name").getAsString();
            String intro = dialogTree.get("intro").getAsString();
            int maxHP = dialogTree.get("maxHP").getAsInt();
            int HP = dialogTree.get("HP").getAsInt();
            int damage = dialogTree.get("damage").getAsInt();
            int armour = dialogTree.get("armour").getAsInt();
            int gold = dialogTree.get("gold").getAsInt();
            int xpGain = dialogTree.get("xpGain").getAsInt();
            int critChance = dialogTree.get("critChance").getAsInt();
            Element element = Element.valueOf(dialogTree.get("element").getAsString());

            NPC_TALK npc = new NPC_TALK(name,intro,maxHP,HP,damage,armour,gold,xpGain,critChance,element,dt,blessAddMaxHP,blessAddArmour,blessAddDamage,bag);
            hp.put(Coordinate.fromStringToCoordinate(coor), npc);
        }

        return hp;
    }

    public static HashMap<Coordinate, Bag> JsonToItemsOnTheMapHashMapData(JsonObject jo){
        HashMap<Coordinate, Bag> hp = new HashMap<>();
        for (Map.Entry<String, JsonElement> entry : jo.entrySet()) {
            String coor = entry.getKey();
            JsonObject itemData = entry.getValue().getAsJsonObject();
            Bag bag = JsonToBag2(itemData);

            hp.put(Coordinate.fromStringToCoordinate(coor),bag);

        }

        return hp;
    }
}
