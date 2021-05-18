package AbnormalPoints;

import Card.Element;
import Player.Bag;
import Player.Player;
import Player.Item;
/**
 * This class mainly held npc that will trade with player
 */
public class NPC_MERCHANT extends AbnormalPoint{
    Bag npcBag;

    public NPC_MERCHANT(String name, String intro, int maxHP, int HP, int damage, int armour,
                        int gold, int xpGain, double critChance, Element element, Bag npcBag) {
        this.abnormalPointType = AbnormalPointType.NPC_MERCHANT;
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
        this.npcBag = npcBag;
    }


    //Todo 买卖交互
    //todo initialize npc shop
    public boolean buyFromNPC(Player p, Item itemSold){
        return true;
    }
//    public
}
