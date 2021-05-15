package AbnormalPoints;

import Card.Element;


/**
 * A normal monster , with slight armour.
 * @author yitao chen
 */
public class Goblin extends AbnormalPoint {

    public Goblin(int playerLevel) {
        this.monsterType = "Goblin";
        this.setHealthMax(55 + playerLevel * 6);
        this.setHealth(55 + playerLevel * 6);
        this.setArmour(playerLevel + 3);
        this.setDamage(12 + playerLevel * 2.5);
        this.setIntelligence(1);
        this.setCritChance(0.02);
        this.setXPGain(10 + playerLevel * 3);
        this.setGold(playerLevel * 5);
        this.setElement(Element.Normal);
    }

    public Goblin(int playerLevel, Element element1) {
        this.monsterType = "Goblin";
        this.setHealthMax(55 + playerLevel * 6);
        this.setHealth(55 + playerLevel * 6);
        this.setArmour(playerLevel + 3);
        this.setDamage(12 + playerLevel * 2.5);
        this.setIntelligence(1);
        this.setCritChance(0.02);
        this.setXPGain(10 + playerLevel * 3);
        this.setGold(playerLevel * 5);
        this.setElement(element1);
    }
}
