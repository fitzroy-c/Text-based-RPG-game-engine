package AbnormalPoints;

import Card.Element;
import Player.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import navigation.Coordinate;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    /**
     * Save talk npc as json
     * @author Guanming Ou
     */
    public static void saveTALKNPC(HashMap<Coordinate, NPC_TALK> talkNPC){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(FileWriter fw = new FileWriter("json_files/original_data/TalkNPC.json")){ // name json file with player's name
            gson.toJson(talkNPC, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    /**
     * Create json file by instanlising and execute save command inside java
     * @param args .
     * @author Guanming Ou
     */
    public static void main(String[] args) {
        HashMap<String, Integer> c1P = new HashMap<>();
        c1P.put("health", +15);
        c1P.put("weight", 1);
        c1P.put("value", 10);
        Item c1 = new Item("C1", "consumable", "Mushroom", "Restores 15 health", c1P);
        HashMap<String, Integer> c2P = new HashMap<>();

        c1P.put("health", +50);
        c1P.put("weight", 1);
        c1P.put("value", 25);
        Item c2 = new Item("C2", "consumable", "Lesser Healing Potion", "Restores 50 health", c2P);
        HashMap<String, Integer> c3P = new HashMap<>();
        c1P.put("health", +100);
        c1P.put("weight", 1);
        c1P.put("value", 50);
        Item c3 = new Item("C3", "consumable", "Healing Potion", "Restores 100 health", c3P);
        Bag bag01 = new Bag(100); // has 4 mushroom
        bag01.put(c1);
        bag01.put(c1);
        Bag bag02 = new Bag(100); // has 2 mushroom, 1 Lesser Healing Potion
        bag02.put(c1);
        bag02.put(c2);
        Bag bag03 = new Bag(100); // has 2 mushroom, 1 Lesser Healing Potion
        bag03.put(c3);
        bag03.put(c3);

        /*
          Here is the fairy character as a instance
         */
        DialogTree Bless_tree = new DialogTree();
        Bless_tree.getRoot().setNpcDialog("I can bless you... to better prepare you for the fight");
        Bless_tree.getRoot().setIndex(0);
        Bless_tree.getRoot().setDtype(DialogTree.DialogType.CONTINUE);
        List<DialogTree.Dialog> blesserDialog = new ArrayList<>();
        blesserDialog.add(new DialogTree.Dialog(1, "I want to increase my health", "as you wish...", new ArrayList<>(), DialogTree.DialogType.END_BLESS_HP));
        blesserDialog.add(new DialogTree.Dialog(2, "I want to increase my armor", "as you wish...", new ArrayList<>(), DialogTree.DialogType.END_BLESS_ARMOR));
        blesserDialog.add(new DialogTree.Dialog(3, "I want to increase my damage", "as you wish...", new ArrayList<>(), DialogTree.DialogType.END_BLESS_DAMAGE));

        Bless_tree.getRoot().setNextDialogs(blesserDialog);

        NPC_TALK l_fairy = new NPC_TALK("Little_Fairy", "Hi, I can permanent increase your power!",
                100,100,1,100,0,50,0, Element.Normal,
                Bless_tree, 3,3,3,new Bag(10));

        NPC_TALK t_fairy = new NPC_TALK("Teenager_Fairy", "Hi, I can permanent increase your power!",
                500,500,1,500,0,100,0, Element.Normal,
                Bless_tree, 7,6,4,new Bag(10));

        NPC_TALK a_fairy = new NPC_TALK("Adult_Fairy", "Hi, I can permanent increase your power!",
                1000,1000,1,1000,0,500,0, Element.Normal,
                Bless_tree, 13,10,10,new Bag(10));

        NPC_TALK e_fairy = new NPC_TALK("Elder_Fairy", "Hi, I can permanent increase your power!",
                10000,10000,1,2000,0,1000,0, Element.Normal,
                Bless_tree, 50,30,40,new Bag(10));

        /*
          Random dude instance
         */
        DialogTree people_tree = new DialogTree();
        people_tree.getRoot().setNpcDialog("I am just someone wandering around here, whats up dude");
        people_tree.getRoot().setIndex(0);
        people_tree.getRoot().setDtype(DialogTree.DialogType.CONTINUE);
        List<DialogTree.Dialog> peopleDialog = new ArrayList<>();

        List<DialogTree.Dialog> rpeopleHello3 = new ArrayList<>();
        rpeopleHello3.add(new DialogTree.Dialog(1, "Ok, I think it is here to the east",
                "Much thanks, here you go your golds.", new ArrayList<>(), DialogTree.DialogType.END_GIVE_GOLD));
        rpeopleHello3.add(new DialogTree.Dialog(2, "Sorry, it is a secret",
                "I should kill you then!", new ArrayList<>(), DialogTree.DialogType.END_ATTACK));
        List<DialogTree.Dialog> rpeopleHello2 = new ArrayList<>();
        rpeopleHello2.add(new DialogTree.Dialog(1, "Yeah, i think it is around here",
                "Tell me where it is, I can give you gold.", rpeopleHello3, DialogTree.DialogType.CONTINUE));
        rpeopleHello2.add(new DialogTree.Dialog(2, "Haha, just kidding. Bye!",
                "Damn, I thought you mean it for real... ", new ArrayList<>(), DialogTree.DialogType.END_NONE));
        List<DialogTree.Dialog> rpeopleHello1 = new ArrayList<>();
        rpeopleHello1.add(new DialogTree.Dialog(1, "True, I need to go now, to find some treasure",
                "You mean treasure!??", rpeopleHello2, DialogTree.DialogType.CONTINUE));
        rpeopleHello1.add(new DialogTree.Dialog(2, "Yeah, I know that. Bye!",
                "Bye.", new ArrayList<>(), DialogTree.DialogType.END_NONE));

        peopleDialog.add(new DialogTree.Dialog(1, "Hello, how are you sir?", "I am fine, it is a good day today (smiling)", rpeopleHello1, DialogTree.DialogType.CONTINUE));

        peopleDialog.add(new DialogTree.Dialog(2, "You stupid dude, what you doing here", "You are so rude!", new ArrayList<>(), DialogTree.DialogType.END_ATTACK));

        List<DialogTree.Dialog> rpeopleGiveItem1 = new ArrayList<>();
        rpeopleGiveItem1.add(new DialogTree.Dialog(1, "Give me some gold so I can purchase food at the merchant.",
                "Nah, go earn it yoursel!", new ArrayList<>(), DialogTree.DialogType.END_NONE));
        rpeopleGiveItem1.add(new DialogTree.Dialog(2, "Please! Give me some food",
                "... lucky you meet me.", new ArrayList<>(), DialogTree.DialogType.END_GIVE_ITEM));
        peopleDialog.add(new DialogTree.Dialog(3, "hey, I am poor, I need some food to survive", "You do look worry...", rpeopleGiveItem1, DialogTree.DialogType.CONTINUE));

        people_tree.getRoot().setNextDialogs(peopleDialog);

        NPC_TALK rpeople1 = new NPC_TALK("Some dude", "Walk and walk",
                100,100,18,5,10,50,0.02, Element.Normal,
                people_tree, 0,0,0, bag01);

        NPC_TALK rpeople2 = new NPC_TALK("Wander", "Walk and walk",
                500,480,34,10,80,200,0.08, Element.Normal,
                people_tree, 0,0,0, bag02);

        NPC_TALK rpeople3 = new NPC_TALK("Treasure hunter", "Walk and walk",
                1000,970,100,50,200,1000,0.10, Element.Normal,
                people_tree, 0,0,0, bag03);


        /*
         * Here is the guild character as a instance
         */
        DialogTree guide_tree = new DialogTree();
        guide_tree.getRoot().setNpcDialog("Hi, are you ok?  I am your guide to this land. What do you want to know about?");
        guide_tree.getRoot().setIndex(0);
        guide_tree.getRoot().setDtype(DialogTree.DialogType.CONTINUE);
        List<DialogTree.Dialog> Dialog1 = new ArrayList<>();

        // conversation branch 1
        List<DialogTree.Dialog> controlIntro13 = new ArrayList<>();
        controlIntro13.add(new DialogTree.Dialog(1, "No thanks, I want to earn the gold myself",
                "Okay, enjoy you adventure!.", new ArrayList<>(), DialogTree.DialogType.END_NONE));
        controlIntro13.add(new DialogTree.Dialog(2, "Sure, thanks again!",
                "Here you go.", new ArrayList<>(), DialogTree.DialogType.END_GIVE_GOLD));
        controlIntro13.add(new DialogTree.Dialog(3, "Can I get something to eat during my adventure?",
                "Sure, you can have this lesser health potion.", new ArrayList<>(), DialogTree.DialogType.END_GIVE_ITEM));
        List<DialogTree.Dialog> controlIntro12 = new ArrayList<>();
        controlIntro12.add(new DialogTree.Dialog(1, "Oh thanks!  You are nice",
                "It is my pleasure to help you. Do you want some gold?.", controlIntro13, DialogTree.DialogType.CONTINUE));
        controlIntro12.add(new DialogTree.Dialog(1, "Okay, I want to go now, bye...",
                "Goodbye!.", null, DialogTree.DialogType.END_NONE));
        List<DialogTree.Dialog> controlIntro11 = new ArrayList<>();
        controlIntro11.add(new DialogTree.Dialog(1, "I can't remember all that you just said... sadly",
                "Don't worry, you can see what can you do by calling 'help'.", controlIntro12, DialogTree.DialogType.CONTINUE));
        List<DialogTree.Dialog> controlIntro10 = new ArrayList<>();
        controlIntro10.add(new DialogTree.Dialog(1, "sounds cool, anything else?",
                "If you see any people like me, you can 'talk' to them, or 'trade' with aby merchants.", controlIntro11, DialogTree.DialogType.CONTINUE));
        List<DialogTree.Dialog> controlIntro9 = new ArrayList<>();
        controlIntro9.add(new DialogTree.Dialog(1, "How do I check the weight then?",
                "You can check by 'bag' or look at yours 'stat'.", controlIntro10, DialogTree.DialogType.CONTINUE));
        List<DialogTree.Dialog> controlIntro8 = new ArrayList<>();
        controlIntro8.add(new DialogTree.Dialog(1, "I can also 'drop' or 'consume' by item name right?",
                "Yes, you can, but be careful that your bag have a limited weight.", controlIntro9, DialogTree.DialogType.CONTINUE));
        List<DialogTree.Dialog> controlIntro7 = new ArrayList<>();
        controlIntro7.add(new DialogTree.Dialog(1, "I am not easily defeated, I will never think of 'retreat' or 'bribe' from the monster",
                "I believe you won't, but there are items that you might want to 'pick' up if you found any.", controlIntro8, DialogTree.DialogType.CONTINUE));
        List<DialogTree.Dialog> controlIntro6 = new ArrayList<>();
        controlIntro6.add(new DialogTree.Dialog(1, "So I can 'attack' these monster?",
                "Of cause, if you are brave enough, you can gain xp and gold by defeating them.  But if you lose... I might never see you again.", controlIntro7, DialogTree.DialogType.CONTINUE));
        List<DialogTree.Dialog> controlIntro5 = new ArrayList<>();
        controlIntro5.add(new DialogTree.Dialog(1, "That is a cool!",
                "In that way, you can find out monster or any item in that coordinate", controlIntro6, DialogTree.DialogType.CONTINUE));
        List<DialogTree.Dialog> controlIntro4 = new ArrayList<>();
        controlIntro4.add(new DialogTree.Dialog(1, "Don't you dare to scare me!.",
                "Hey, chill dude, if you don't trust me, you can try 'detect' once you walk to a new place", controlIntro5, DialogTree.DialogType.CONTINUE));
        List<DialogTree.Dialog> controlIntro3 = new ArrayList<>();
        controlIntro3.add(new DialogTree.Dialog(1, "Oh, like walking south at here?.",
                "Yeah, you are smart dude.  You might also want to know that here is a dangerous place.", controlIntro4, DialogTree.DialogType.CONTINUE));
        List<DialogTree.Dialog> controlIntro2 = new ArrayList<>();
        controlIntro2.add(new DialogTree.Dialog(1, "yeah, I know that, but where I can't go?.",
                "You will find it out yourself, there is a invisible air wall that stop everyone here from walking outside the world.", controlIntro3, DialogTree.DialogType.CONTINUE));
        List<DialogTree.Dialog> controlIntro = new ArrayList<>();
        controlIntro.add(new DialogTree.Dialog(1, "I want to know about how to walk around here.",
                "Sure.  You can 'go north' or any of the north, east, west, south direction to move around the map", controlIntro2, DialogTree.DialogType.CONTINUE));

        Dialog1.add(new DialogTree.Dialog(1, "Who are you?", "I am your guide to this land, any question?", controlIntro, DialogTree.DialogType.CONTINUE));
        Dialog1.add(new DialogTree.Dialog(2, "I want to know about how to walk around here.", "Sure.  You can 'go north' or any of the north, east, west, south direction to move around the map", controlIntro2, DialogTree.DialogType.CONTINUE));
        Dialog1.add(new DialogTree.Dialog(3, "I already know what to do, bye.", "Bless you healthy!", new ArrayList<>(), DialogTree.DialogType.END_BLESS_HP));

        guide_tree.getRoot().setNextDialogs(Dialog1);

        NPC_TALK guide = new NPC_TALK("Guide", "A guide waiting for you",
                100,100,10,3,10,10,0, Element.Normal,
                guide_tree, 5,0,0,bag01);


        HashMap<Coordinate, NPC_TALK> createNPCJSON = new HashMap<>();
        createNPCJSON.put(new Coordinate(0,0), guide);

        createNPCJSON.put(new Coordinate(0,1), l_fairy);
        createNPCJSON.put(new Coordinate(0,3), l_fairy);
        createNPCJSON.put(new Coordinate(0,8), l_fairy);
        createNPCJSON.put(new Coordinate(1,1), l_fairy);
        createNPCJSON.put(new Coordinate(1,3), l_fairy);
        createNPCJSON.put(new Coordinate(1,8), l_fairy);
        createNPCJSON.put(new Coordinate(1,19), l_fairy);
        createNPCJSON.put(new Coordinate(2,1), l_fairy);
        createNPCJSON.put(new Coordinate(2,3), l_fairy);
        createNPCJSON.put(new Coordinate(3,5), l_fairy);

        createNPCJSON.put(new Coordinate(3,9), t_fairy);
        createNPCJSON.put(new Coordinate(3,18), t_fairy);
        createNPCJSON.put(new Coordinate(4,5), t_fairy);
        createNPCJSON.put(new Coordinate(7,3), t_fairy);
        createNPCJSON.put(new Coordinate(15,23), t_fairy);

        createNPCJSON.put(new Coordinate(16,24), a_fairy);
        createNPCJSON.put(new Coordinate(24,16), a_fairy);
        createNPCJSON.put(new Coordinate(26,3), a_fairy);

        createNPCJSON.put(new Coordinate(29,15), e_fairy);
        createNPCJSON.put(new Coordinate(25,25), e_fairy);

        createNPCJSON.put(new Coordinate(2,2), rpeople1);
        createNPCJSON.put(new Coordinate(4,19), rpeople1);
        createNPCJSON.put(new Coordinate(10,10), rpeople1);
        createNPCJSON.put(new Coordinate(14,13), rpeople1);
        createNPCJSON.put(new Coordinate(18,12), rpeople1);

        createNPCJSON.put(new Coordinate(19,19), rpeople2);
        createNPCJSON.put(new Coordinate(18,18), rpeople2);
        createNPCJSON.put(new Coordinate(29,12), rpeople2);

        createNPCJSON.put(new Coordinate(28,27), rpeople3);
        createNPCJSON.put(new Coordinate(23,21), rpeople3);

        saveTALKNPC(createNPCJSON);
    }
}
