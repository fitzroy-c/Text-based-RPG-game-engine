package AbnormalPoints;

import Card.Element;
import Player.*;

/**
 * This class held NPC that will make conversation with player, mainly for the plot of this game
 */
public class NPC_TALK extends AbnormalPoint{
    DialogTree dialogTree;
    int blessAddMaxHP;
    int blessAddArmour;
    int blessAddDamage;
    boolean hasEndedTalk;
    Bag npcBag;

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
     * @param dialogTree the dialog of this npc
     */
    public NPC_TALK(String name, String intro, int maxHP, int HP, int damage, int armour, int gold, int xpGain,
                    double critChance, Element element, DialogTree dialogTree,int blessAddMaxHP, int blessAddArmour,
                    int blessAddDamage, Bag npcBag) {
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
        this.dialogTree = dialogTree;
        this.blessAddMaxHP = blessAddMaxHP;
        this.blessAddArmour = blessAddArmour;
        this.blessAddDamage = blessAddDamage;
        this.hasEndedTalk = false;
        this.npcBag = npcBag;
    }

//        DialogTree tree = new DialogTree();
//        tree.root.npcDialog = "I can bless you... to better prepare you for the fight";
//        tree.root.index = 0;
//        tree.root.dtype = DialogTree.DialogType.CONTINUE;
//        tree.root.nextDialogs.add(new DialogTree.Dialog(1, "I want to increase my health",
//                "as you wish...", null, DialogTree.DialogType.END_BLESS));
//        tree.root.nextDialogs.add(new DialogTree.Dialog(2, "I want to increase my armor",
//                "as you wish...", null, DialogTree.DialogType.END_BLESS));
//        tree.root.nextDialogs.add(new DialogTree.Dialog(3, "I want to increase my damage",
//                "as you wish...", null, DialogTree.DialogType.END_BLESS));
//        this.dialogTree = tree;

    /**
     * Method that increase a player's stat base on player's choice
     * @param i (1 = increase max hp, 2 = increase armor, 3 = increase damage)
     * @return String that tell how the blessing went
     */
    public String blessByInt(int i, Player player){ // check has blessed
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
        this.hasEndedTalk = true; // restrict bless only to happens once
        return "Your maximum hp has increased to "+player.getMaxHP();
    }

    /**
     * Increase player's armor
     */
    public String armorBless(Player player){
        player.setArmour(player.getArmour() + this.blessAddArmour);
        this.hasEndedTalk = true; // restrict bless only to happens once
        return "Your armor has increased to "+player.getArmour();
    }

    /**
     * Increase player's damage
     */
    public String damageBless(Player player){
        player.setDamage(player.getDamage() + this.blessAddDamage);
        this.hasEndedTalk = true; // restrict bless only to happens once
        return "Your attack damage has increased to "+player.getDamage();
    }

    /**
     * As npc wants to attack player, it will transform into monster
     * @return a monster with same stat as npc
     */
    public Monster transformIntoMonster(){
        MonsterAttributes temp = new MonsterAttributes("temp", "temp",
                1,0, 0,0,0,0,
                0,0, 0,0,0,Element.Normal);

        Monster newMonster = new Monster(temp, 1);
        newMonster.setName("Angry "+ this.getName());
        newMonster.setIntro(this.getIntro());
        newMonster.setMaxHP(this.getMaxHP());
        newMonster.setHP(this.getHP());
        newMonster.setArmour(this.getArmour());
        newMonster.setDamage(this.getDamage());
        newMonster.setCritChance(this.getCritChance());
        newMonster.setXpGain(this.getXpGain());
        newMonster.setGold(this.getGold());
        newMonster.setElement(this.getElement());
        return newMonster;
    }

    public DialogTree getDialogTree() {
        return dialogTree;
    }

    public void setDialogTree(DialogTree dialogTree) {
        this.dialogTree = dialogTree;
    }

    public int getBlessAddMaxHP() {
        return blessAddMaxHP;
    }

    public void setBlessAddMaxHP(int blessAddMaxHP) {
        this.blessAddMaxHP = blessAddMaxHP;
    }

    public int getBlessAddArmour() {
        return blessAddArmour;
    }

    public void setBlessAddArmour(int blessAddArmour) {
        this.blessAddArmour = blessAddArmour;
    }

    public int getBlessAddDamage() {
        return blessAddDamage;
    }

    public void setBlessAddDamage(int blessAddDamage) {
        this.blessAddDamage = blessAddDamage;
    }

    public boolean isHasEndedTalk() {
        return hasEndedTalk;
    }

    public void setHasEndedTalk(boolean hasEndedTalk) {
        this.hasEndedTalk = hasEndedTalk;
    }

    public Bag getNpcBag() { return npcBag; }

    public void setNpcBag(Bag npcBag) { this.npcBag = npcBag; }
}
