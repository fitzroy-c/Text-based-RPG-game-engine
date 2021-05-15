package navigation;

import AbnormalPoints.AbnormalPoint;
import Player.Bag;
import Player.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Placement {
    private Coordinate coordinate;
    private String description;
    private int dangerRate; //calculate the chance we meet a monster
    private Bag bag = new Bag(); //TODO maybe conflict with player.class
    private List<AbnormalPoint> abnormalPoints = new ArrayList<>();

    public Placement() {
    }

    public Placement(Coordinate coordinate, String description) {
        this.coordinate = coordinate;
        this.description = description;
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

    public void addAbnormalPoints(List<AbnormalPoint> abnormalPoints) {
        for (AbnormalPoint abnormalPoint : abnormalPoints) {
            addAbnormalPoint(abnormalPoint);
        }
    }

    public void addAbnormalPoint(AbnormalPoint abnormalPoint) {
        abnormalPoints.add(abnormalPoint);
    }

    public void remove(AbnormalPoint abnormalPoint) {
            removeAbnormalPoint(abnormalPoint);
    }
    public void removeAbnormalPoint(AbnormalPoint abnormalPoint) {
        for (int i = 0; i < abnormalPoints.size(); i++) {
            if (abnormalPoints.get(i).equals(abnormalPoint)) {
                abnormalPoints.remove(i);
            }
        }
    }
    public List<AbnormalPoint> getAbnormalPoints() {
        //return a un changeable list
        //refer: https://www.javatpoint.com
        return Collections.unmodifiableList(abnormalPoints);
    }
    public Item removeItem(Item item) {
        return Bag.drop(item);
    }
    public void addItem(Item item) {
        Bag.put(item);
    }
    public void print() {
        // TODO: need a function to print out all we have in this real location (description + npc + ?)
    }

}
