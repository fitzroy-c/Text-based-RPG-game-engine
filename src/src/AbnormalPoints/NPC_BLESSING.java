package AbnormalPoints;

import Card.Element;

public class NPC_BLESSING extends AbnormalPoint{

    public NPC_BLESSING(String name, String intro, int maxHP, int HP, int damage, int armour,
                    int gold, int xpGain, double critChance, Element element) {
        this.abnormalPointType = AbnormalPointType.NPC_BLESSING;
        this.setName(name);
        this.setIntro(intro);
        this.setMaxHP(maxHP);
        this.setHP(HP);
        this.setDamage(damage);
        this.setArmour(armour);
        this.setGold(gold);
        this.setXpGain(xpGain);
        this.setCritChance(critChance);
        this.setElement(element);
    }
}
