package AbnormalPoints;

import Card.Element;

/**
 * A monster without low damage, but high health and armour.
 * @author yitao chen
 */
public class Troll extends AbnormalPoint {
    public Troll(int playerLevel){
        this.monsterType = "Troll";
        this.setHealthMax(70 + playerLevel * 11);
        this.setHealth(70 + playerLevel * 11);
        this.setArmour(playerLevel + 12);
        this.setDamage(20 + playerLevel * 3);
        this.setCritChance(0.05);
        this.setXPGain(75 + playerLevel * 3);
        this.setGold(25 + playerLevel * 10);
        this.setElement(Element.Normal);
    }
    public Troll(int playerLevel, Element element1){
        this.monsterType = "Troll";
        this.setHealthMax(70 + playerLevel * 11);
        this.setHealth(70 + playerLevel * 11);
        this.setArmour(playerLevel + 12);
        this.setDamage(20 + playerLevel * 3);
        this.setCritChance(0.05);
        this.setXPGain(75 + playerLevel * 3);
        this.setGold(25 + playerLevel * 10);
        this.setElement(element1);
    }
}
