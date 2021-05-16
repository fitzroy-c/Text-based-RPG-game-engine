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

    /*
    Put things in the bag

    public String put(Item i){
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
    //drop things out of the bag

    public String drop(Item i){
        int weightOfItem = i.properties.get("weight");
        this.itemList.remove(i);
        currentWeight-=weightOfItem;
        return "You've removed "+i.name+" .";
    }

     */
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
        if (inMyBag(i)) {
            int weightOfItem = i.properties.get("weight");
            itemList.remove(i);
            currentWeight -= weightOfItem;
            return i;
        }
        return null;
    }

    /**
    * check if one item is in the bag (Yixiang Yin)
    */
    public boolean inMyBag(Item i){
        for (Item item: this.itemList){
            if (i.equals(item)) return true;
        }
        return false;
    }

    /**
     * Check if a bag contains a item of given item name (String)
     * @param itemName
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
}
