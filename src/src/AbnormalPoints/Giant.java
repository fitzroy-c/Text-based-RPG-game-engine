package AbnormalPoints;

import Card.Element;

/**
 * A monster with high health and damage, but low armour.
 * @author yitao chen
 */
public class Giant extends AbnormalPoint {

    public Giant(int playerLevel) {
        this.monsterType = "Giant";
        this.setHealthMax(150 + playerLevel * 8);
        this.setHealth(150 + playerLevel * 8);
        this.setArmour(6 + playerLevel * 3);
        this.setDamage(40 + playerLevel * 3);
        this.setIntelligence(3);
        this.setCritChance(0.03);
        this.setXPGain(50 + playerLevel * 3);
        this.setGold(15 + playerLevel * 11);
        this.setElement(Element.Normal);
    }
    public Giant(int playerLevel,Element element1) {
        this.monsterType = "Giant";
        this.setHealthMax(150 + playerLevel * 8);
        this.setHealth(150 + playerLevel * 8);
        this.setArmour(6 + playerLevel * 3);
        this.setDamage(40 + playerLevel * 3);
        this.setIntelligence(3);
        this.setCritChance(0.03);
        this.setXPGain(50 + playerLevel * 3);
        this.setGold(15 + playerLevel * 11);
        this.setElement(element1);
    }
}
