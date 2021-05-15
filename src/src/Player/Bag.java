package Player;

import java.util.LinkedList;

public class Bag extends LinkedList<Item>{
    int currentWeight;
    int maxWeight;

    public Bag(){
        currentWeight = 0;
        maxWeight = 5; // default 5 unit
    }


    /*
    Put things in the bag
     */
    public String put(Item i){
        int weightOfItem = i.properties.get("weight");
        if (currentWeight+weightOfItem<=maxWeight) {
            this.add(i);
            currentWeight+=weightOfItem;
            return "You've put it in your bag";
        }
        else {
            return "Your bag is out of storage.(You may drop or consume some items or extend your storage)";
        }
    }
    /*
    drop things out of the bag
     */
    public String drop(Item i){
        int weightOfItem = i.properties.get("weight");
        this.remove(i);
        currentWeight-=weightOfItem;
        return "You've removed "+i.name+" .";
    }
    /*
    check if one item is in the bag
    */
    public boolean inMyBag(Item i){
        for (Item item: this){
            if (i.equals(item)) return true;
        }
        return false;
    }

    /*
    show the content in the bag
     */
    public void showMyBag() {
        System.out.println("-----------Your bag-----------");
        for (Item i: this){
            System.out.println(i.name+" : "+i.getDescription());
            System.out.println();
        }
        System.out.println("-------------End--------------");
    }
}
