package navigation;

import AbnormalPoints.AbnormalPoint;
import Player.Bag;
import Player.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Placement {
    private Coordinate coordinate;
    private String description;
    private int dangerRate; //calculate the chance we meet a monster
    private Bag bag; // the bag which contains item inside a room
    private List<AbnormalPoint> abnormalPoints;

    Random random = new Random();

    public Placement() {
    }

    public Placement(Coordinate coordinate, String description) {
        this.coordinate = coordinate;
        this.description = description;
        this.dangerRate = random.nextInt(4) + 1;
        this.bag = new Bag(100);
        this.abnormalPoints = new ArrayList<>();
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
     * Print out the information of current placement
     */
    public void printPlacement() {
        // TODO: need a function to print out all we have in this real location (description + npc + ?)
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

    public void setDangerRate(int dangerRate) {
        this.dangerRate = dangerRate;
    }

    public Bag getBag() {
        return bag;
    }

    public List<Item> getItems() {
        return bag.getItems();
    }

}
