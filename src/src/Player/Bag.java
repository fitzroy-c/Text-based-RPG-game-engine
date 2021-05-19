package Player;

import java.util.ArrayList;
import java.util.List;

public class Bag {

    int currentWeight;
    int maxWeight;
    private List<Item> itemList;

    /*
    public Bag(){
        this(5, new ArrayList<Item>());
    }

     */
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
    // 整体， 玩家， 地点
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
     * @param player
     * @return Guanming Ou
     */
    public Bag giveAllItemTo(Player player){
        Bag tempBag = new Bag(100);;
        for (Item npc_item : this.itemList){
            if (player.bag.put(npc_item)){ // add to player's bag
                this.drop(npc_item); // delete from npc's bag
            } else {
                tempBag.put(npc_item);
            }
        }
        if (!tempBag.isEmpty()){
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
            if (i.name.equals(itemName))
                return i;
        }
        // did not find a item
        return null;
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
}
