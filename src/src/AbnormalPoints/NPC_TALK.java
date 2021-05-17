package AbnormalPoints;

import Card.Element;

/**
 * This class held NPC that will make conversation with player, mainly for the plot of this game
 */
public class NPC_TALK extends AbnormalPoint{

    public NPC_TALK(String name, String intro, int maxHP, int HP, int damage, int armour,
                        int gold, int xpGain, double critChance, Element element) {
        this.abnormalPointType = AbnormalPoint.AbnormalPointType.NPC_TALK;
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
