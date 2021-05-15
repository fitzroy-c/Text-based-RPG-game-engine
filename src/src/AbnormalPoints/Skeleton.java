package AbnormalPoints;

import Card.Element;
/**
 * A quite weak monster.
 * @author yitao chen
 */
public class Skeleton extends AbnormalPoint {

    public Skeleton(int playerLevel) {
        this.monsterType = "Skeleton";
        this.setHealthMax(50 + (int) Math.pow(playerLevel, 3));
        this.setHealth(50 + (int) Math.pow(playerLevel, 3));
        this.setArmour(0);
        this.setDamage(8 + Math.pow(playerLevel, 1.5));
        this.setCritChance(0.02);
        this.setIntelligence(3);
        this.setXPGain(10 + playerLevel * 3);
        this.setGold(playerLevel * 3);
        this.setElement(Element.Normal);
    }
    public Skeleton(int playerLevel, Element element1) {
        this.monsterType = "Skeleton";
        this.setHealthMax(50 + (int) Math.pow(playerLevel, 3));
        this.setHealth(50 + (int) Math.pow(playerLevel, 3));
        this.setArmour(0);
        this.setDamage(8 + Math.pow(playerLevel, 1.5));
        this.setCritChance(0.02);
        this.setIntelligence(3);
        this.setXPGain(10 + playerLevel * 3);
        this.setGold(playerLevel * 3);
        this.setElement(element1);
    }
}
