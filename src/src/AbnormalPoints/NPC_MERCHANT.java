package AbnormalPoints;

import Card.Element;
import Player.Bag;
import Player.Player;
import Player.Item;

import java.util.List;

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
    /**
     * Player buy items from NPC
     * return true if successful, otherwise, false
     * @author: Yixiang Yin
     **/
    public boolean buyFromNPC(Player p, String itemName){
        // assume itemName is valid
        boolean npcHaveIt = npcBag.searchInBagByName(itemName);
        if (npcHaveIt) {
            Item wanted = npcBag.getItemByName(itemName); // guarantee non-null
            int price = wanted.getProperty("price");
            if (p.money>=price){
                p.money-=price;
                p.getBag().put(wanted);
                return true;
                // might remove the same item from npcbag, but i think it's okay for now
            }
            else return false; // don't have enough money
        }
        return false; // npc don't have it
    }
    /**
     * output the possible str for different circumstances when purchasing
     * @author: Yixiang Yin
     **/
    public String outputStrForBuyFromNPC(Player p, String itemName, boolean succ){
        if (succ) return "You've successfully purchased "+itemName+" .Happy to see you soon again!";
        else {
            Item wanted = npcBag.getItemByName(itemName); // guarantee non-null
            int price = wanted.getProperty("price");
            if (p.money<price) return "You don't have enough money to purchase "+itemName+" .";
            return "Sorry, I don't have "+itemName+" .";
        }
    }
}
