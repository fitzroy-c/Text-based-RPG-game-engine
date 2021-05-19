package navigation;

import AbnormalPoints.AbnormalPoint;
import Player.Bag;
import Player.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * the class is meant to store the player's info : current location(coordinate,description,dangerRate)
 * anything unusual if has.
 * bag.
 * @author yitao chen
 */
public class Place {
    private Coordinate coordinate;
    private String description;
    private int dangerRate;                     // calculate the chance we meet a monster
    private Bag bag;                            // the bag which contains item inside a room
    private List<AbnormalPoint> abnormalPoints; // may have npc and monster

    Random random = new Random();

    /*
    place danger rate 1-5 consider the the plan is to make dangerRate 1(easy)-5(danger)
    6 definite have a monster -- test only
    0 or less definite no monster
     */
    public Place(Coordinate coordinate, String description) {
        this.coordinate = coordinate;
        this.description = description;
        this.dangerRate = random.nextInt(5); // 5 level, 0(safe) - 4 (extremely dangerous)
        this.bag = new Bag(100);
        this.abnormalPoints = new ArrayList<>();
    }

    public Place(Coordinate coordinate, String description, int dangerRate, Bag bag, List<AbnormalPoint> abnormalPoints) {
        this.coordinate = coordinate;
        this.description = description;
        this.dangerRate = dangerRate;
        this.bag = bag;
        this.abnormalPoints = abnormalPoints;
    }

    /**
     * add AbnormalPoint
     * @param abnormalPoint
     */
    public void addAbnormalPoint(AbnormalPoint abnormalPoint) {
        abnormalPoints.add(abnormalPoint);
    }

    /**
     * add list of AbnormalPoint
     * @param abnormalPoints list of AbnormalPoint
     */
    public void addAbnormalPoints(List<AbnormalPoint> abnormalPoints) {
        for (AbnormalPoint abnormalPoint : abnormalPoints) {
            addAbnormalPoint(abnormalPoint);
        }
    }

    /**
     * Remove AbnormalPoint
     * @param abnormalPoint
     */
    public void removeAbnormalPoint(AbnormalPoint abnormalPoint) {
        for (int i = 0; i < abnormalPoints.size(); i++) {
            if (abnormalPoints.get(i).equals(abnormalPoint)) {
                abnormalPoints.remove(i);
            }
        }
    }

    /**
     * return a un-changeable list
     * @return list of AbnormalPoints
     */
    public List<AbnormalPoint> getAbnormalPoints() {
        //refer: https://www.javatpoint.com
        return Collections.unmodifiableList(abnormalPoints);
    }

    /**
     * drop a item from this placement
     */
    public Item removeItem(Item item) {
        return getBag().drop(item);
    }

    /**
     * Insert a item into this placement
     */
    public void addItem(Item item) {
        getBag().put(item);
    }

    /**
     * Display the current coordinate description
     * @return String description
     * - coordinate
     * - description
     * - dangerRate
     * - bag
     * - abnormalPoints
     * @author Guanming Ou
     */
    public String printPlace(){
        return "Coordinate:"+this.getCoordinate()+"\n"+
                "Danger rate:"+this.getDangerRate()+"\n"+
                this.getDescription()+"\n"+
                "Items: "+this.getBag().showPlaceBag()+"\n"+
                "Monster | NPC: "+this.printAbnormalPoints() +"\n";
    }

    /**
     * Print all abnormal points inside the abnormal point list
     * @return string of all the abnormal point name
     * @author Guanming Ou
     */
    public String printAbnormalPoints(){
        String aps = "[";
        for (int i =0; i<this.abnormalPoints.size(); i++){
            if (i == 0)
                aps += this.abnormalPoints.get(i).getName();
            else
                aps += ", "+this.abnormalPoints.get(i).getName();
        }
        return aps+"]";
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getDangerRate() {
        return dangerRate;
    }

    /*
    place danger rate 1-5 consider the the plan is to make dangerRate 1(easy)-5(danger)
    6 definite have a monster -- test only
    0 or less definite no monster
     */
    public void setDangerRate(int dangerRate) {
        this.dangerRate = dangerRate;
    }

    public Bag getBag() {
        return bag;
    }

    public List<Item> getItems() {
        return bag.getItems();
    }

    public void setBag(Bag bag) { this.bag = bag; }
}
