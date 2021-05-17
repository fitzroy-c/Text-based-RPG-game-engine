package AbnormalPoints;

import Card.Element;
import Player.Player;

public class NPC_BLESSING extends AbnormalPoint{
    int blessAddMaxHP;
    int blessAddArmour;
    int blessAddDamage;
    boolean hasBlessed;

    /**
     * Constructor of NPC BLESSING
     * @param name name of this npc
     * @param intro intro of this npc
     * @param maxHP this npc maximum health
     * @param HP this npc current health
     * @param damage this npc damage
     * @param armour this npc damage
     * @param gold gold gain by player defeating this npc
     * @param xpGain xp gain by player defeating this npc
     * @param critChance critical chance of attack
     * @param element element type of this npc
     * @param blessAddMaxHP the number of max hp add to player after blessing
     * @param blessAddArmour the number of permanent armor add to player after blessing
     * @param blessAddDamage the number of permanent damage add to player after blessing
     */
    public NPC_BLESSING(String name, String intro, int maxHP, int HP, int damage, int armour, int gold, int xpGain,
                        double critChance, Element element, int blessAddMaxHP, int blessAddArmour, int blessAddDamage) {
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
        this.blessAddMaxHP = blessAddMaxHP;
        this.blessAddArmour = blessAddArmour;
        this.blessAddDamage = blessAddDamage;
        this.hasBlessed = false;
    }

    /**
     * Method that increase a player's stat base on player's choice
     * @param i (1 = increase max hp, 2 = increase armor, 3 = increase damage)
     * @return String that tell how the blessing went
     */
    public String blessByInt(int i, Player player){
        if (i == 1)
            return hpBless(player);
        if (i == 2)
            return armorBless(player);
        if (i == 3)
            return damageBless(player);
        return "invalid choice, try again please";
    }

    /**
     * Increase player's maximum health point
     */
    public String hpBless(Player player){
        player.setMaxHP(player.getMaxHP() + this.blessAddMaxHP);
        this.hasBlessed = true; // restrict bless only to happens once
        return "Your maximum hp has increased to "+player.getMaxHP();
    }

    /**
     * Increase player's armor
     */
    public String armorBless(Player player){
        player.setArmour(player.getArmour() + this.blessAddArmour);
        this.hasBlessed = true; // restrict bless only to happens once
        return "Your armor has increased to "+player.getArmour();
    }

    /**
     * Increase player's damage
     */
    public String damageBless(Player player){
        player.setDamage(player.getDamage() + this.blessAddDamage);
        this.hasBlessed = true; // restrict bless only to happens once
        return "Your attack damage has increased to "+player.getDamage();
    }
}
