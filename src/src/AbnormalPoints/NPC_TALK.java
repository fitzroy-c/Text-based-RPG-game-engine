package AbnormalPoints;

import Card.Element;

import java.util.List;

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


//    /**
//     * Trace down the dialog tree, given the selection of reply by the player
//     * @param indexChoice the choice of the player (int)
//     * @return a new DialogTree with new npc dialog and children dialogs
//     * @author: Guanming Ou
//     */
//    public DialogTree applyReply(int indexChoice){
//        if (this.root == null | this.root.nextDialogs == null) return null;
//
//        DialogTree.Dialog currentNode = this.root;
//        List<DialogTree.Dialog> children = currentNode.nextDialogs;
//        //TODO: Make it so that it make to npc do such things following its type
//        for (DialogTree.Dialog d : children){
//            if (d.index == indexChoice){
//                DialogTree t = new DialogTree();
//                t.root = d;
//                t.rootString = d.npcDialog;
//                return t;
//            }
//        }
//        return null;
//    }
}
