package Player;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import navigation.Coordinate;

import java.lang.reflect.Type;
import java.util.*;

/**
 * This class contains all the interactions with bag
 * @author Yixiang Yin
 */
public class Bag {

    int currentWeight;
    int maxWeight;
    private List<Item> itemList;
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
    /**
     * put an item in the bag (Yixiang Yin, modified by Yitao)
     */
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
    * check if one item is in the bag
    * @author Yixiang Yin
    */
    public boolean searchInBag(Item i){
        for (Item item: this.itemList){
            if (i.equals(item)) return true;
        }
        return false;
    }

    /**
     * check if one item is in the bag by itemName
     * @author Yixiang Yin
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
    /**
     * convert a json object into a bag in this format -> {current weight: ..., maxweight, item list}
     * @author Yixiang Yin
     */
    public static Bag JsonToBag(JsonObject baginfo){
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
     * show the content in the bag
     * @author Yixiang Yin
     */
    public void showMyBag() {
        System.out.println("-----------Your bag-----------");
        for (Item i: this.itemList){
            System.out.println(i.name+" : "+i.getDescription());
            System.out.println();
        }
        System.out.println("-------------End--------------");
    }
    /**
     * show the content in that place
     * @author Yixiang Yin
     */
    public void showPlaceBagInTheGame() {
        if (this.itemList.size()==0) System.out.println("Nothing on the floor");
        System.out.println("-----------Items on the floor-----------");
        for (Item i: this.itemList){
            System.out.println(i.name+" : "+i.getDescription());
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

    /**
     * convert a json object to a HashMap<Coordinate, Bag>
     * @author Yixiang Yin
     */
    public static HashMap<Coordinate, Bag> JsonToItemsOnTheMapHashMapData(JsonObject jo){
        HashMap<Coordinate, Bag> hp = new HashMap<>();
        for (Map.Entry<String, JsonElement> entry : jo.entrySet()) {
            String coor = entry.getKey();
            JsonObject itemData = entry.getValue().getAsJsonObject();
            Bag bag = JsonToBag(itemData);

            hp.put(Coordinate.fromStringToCoordinate(coor),bag);

        }

        return hp;
    }
}
