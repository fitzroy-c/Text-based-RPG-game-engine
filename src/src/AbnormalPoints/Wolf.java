package AbnormalPoints;

import Card.Element;

/**
 * @author yitao chen
 */

public class Wolf extends AbnormalPoint {

    public Wolf(int playerLevel) {
        this.monsterType = "Wolf";
        this.setHealthMax(35 + playerLevel * 3);
        this.setHealth(35 + playerLevel * 3);
        this.setArmour(0);
        this.setDamage(15 + playerLevel * 2.5);
        this.setCritChance(0.04);
        this.setIntelligence(2);
        this.setXPGain(25 + playerLevel * 3);
        this.setGold(playerLevel * 2);
        this.setElement(Element.Normal);
    }
    public Wolf(int playerLevel, Element element1) {
        this.monsterType = "Wolf";
        this.setHealthMax(35 + playerLevel * 3);
        this.setHealth(35 + playerLevel * 3);
        this.setArmour(0);
        this.setDamage(15 + playerLevel * 2.5);
        this.setCritChance(0.04);
        this.setIntelligence(2);
        this.setXPGain(25 + playerLevel * 3);
        this.setGold(playerLevel * 2);
        this.setElement(element1);
    }
}
