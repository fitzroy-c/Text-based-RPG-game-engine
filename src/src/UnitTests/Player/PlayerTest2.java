package Player;

import AbnormalPoints.*;
import Card.Element;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import navigation.Coordinate;
import navigation.Place;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerTest2 {

    @Test
    public void updatePlayerAttributeTestNo(){
        Player player = new Player("testOne");
        player.setLevel(2);
        player.setMaxXP(123);
        player.setXp(100);
        player.UpdatePlayerAttribute();
        assertEquals(2, player.getLevel());
    }

    @Test
    public void updatePlayerAttributeTestYes1Level(){
        Player player = new Player("testOne");
        player.setLevel(2);
        player.setMaxXP(123);
        player.setXp(123);
        player.UpdatePlayerAttribute();
        assertEquals(3, player.getLevel());
    }

    @Test
    public void updatePlayerAttributeTestYes2Level(){
        Player player = new Player("testOne");
        player.setLevel(2);
        player.setMaxXP(123);
        player.setXp(266);
        player.UpdatePlayerAttribute();
        assertEquals(4, player.getLevel());
    }

    @Test
    public void updatePlayerAttributeTestYesMultiLevel(){
        Player player = new Player("testOne");
        player.setLevel(2);
        player.setMaxXP(123);
        player.setXp(10000);
        player.UpdatePlayerAttribute();
        assertEquals(36, player.getLevel());
    }

    @Test
    public void testNewPlayerNameWithJson() {
        Player player = new Player( "ddd");
        assertEquals("ddd",player.getName());
    }

    @Test
    public void testLoading(){
        Player p1 = new Player("TestingLoad1");
        p1.save();
        Player p2 = Player.load("TestingLoad1");
    }

    @Test
    public void justTesting() {
        Coordinate a = new Coordinate(0,0);
        Coordinate b = new Coordinate(0,0);
        Boolean f = a.equals(b);

        HashMap<Coordinate, Bag> testBag = new HashMap<>();
        HashMap<String, Integer> c1P = new HashMap<>();
        c1P.put("health", +15);
        c1P.put("weight", 1);
        c1P.put("value", 10);
        Item c1 = new Item("C1", "consumable", "Mushroom", "Restores 15 health", c1P);
        Bag bag01 = new Bag(100); // has 4 mushroom
        bag01.put(c1);
        bag01.put(c1);
        bag01.put(c1);
        bag01.put(c1);
        testBag.put(new Coordinate(0,0), bag01);

        Boolean f2 = testBag.containsKey(a);
        Bag b2 = testBag.get(a);
        int h2 = a.hashCode();

        Player player = new Player( "ddd");

        Boolean f3 = player.getMap_bagData().containsKey(a);
        Bag b3 = player.getMap_bagData().get(a);


//        Bag b3 = player.extractBag(a.toString(), player.getMap_bagData());

        HashMap<Coordinate, Bag> jsonBag = new HashMap<>();
        jsonBag = player.map_bagData;


        int i = 0;
    }

    @Test
    public void createItemJson(){
        Player player = new Player();

        HashMap<Coordinate, Bag> testBag = new HashMap<>();
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
        HashMap<String, Integer> c4P = new HashMap<>();
        c1P.put("health", +150);
        c1P.put("weight", 1);
        c1P.put("value", 100);
        Item c4 = new Item("C4", "consumable", "Greater Healing Potion", "Restores 100 health", c4P);

        Bag bag01 = new Bag(100); // has 4 mushroom
        bag01.put(c1);
        bag01.put(c1);
        bag01.put(c1);
        bag01.put(c1);

        Bag bag02 = new Bag(100); // has 2 mushroom, 1 Lesser Healing Potion
        bag02.put(c1);
        bag02.put(c2);
        bag02.put(c1);

        Bag bag03 = new Bag(100); // has 1 Lesser potion, 1 Healing Potion
        bag03.put(c3);
        bag03.put(c2);

        Bag bag04 = new Bag(100); // has 1 Greater Healing Potion
        bag04.put(c4);

        testBag.put(new Coordinate(0,0), bag01);
        testBag.put(new Coordinate(0,1), bag01);
        testBag.put(new Coordinate(0,2), bag02);
        testBag.put(new Coordinate(0,3), bag01);
        testBag.put(new Coordinate(0,4), bag03);
        testBag.put(new Coordinate(0,5), bag04);
        testBag.put(new Coordinate(1,0), bag01);
        testBag.put(new Coordinate(2,0), bag02);
        testBag.put(new Coordinate(3,0), bag01);
        testBag.put(new Coordinate(4,0), bag03);
        testBag.put(new Coordinate(5,0), bag04);

        player.setMap_bagData(testBag);
//        player.saveItem();


//        HashMap<Coordinate, Bag> items = player.loadOriginalItems();

    }

    @Test
    public void createNPCJson(){
        Player player = new Player();
        HashMap<Coordinate, NPC_TALK> testNPC = new HashMap<>();

        DialogTree Bless_tree = new DialogTree();
        Bless_tree.getRoot().setNpcDialog("I can bless you... to better prepare you for the fight");
        Bless_tree.getRoot().setIndex(0);
        Bless_tree.getRoot().setDtype(DialogTree.DialogType.CONTINUE);
        List<DialogTree.Dialog> blesserDialog = new ArrayList<>();
        blesserDialog.add(new DialogTree.Dialog(1, "I want to increase my health", "as you wish...", null, DialogTree.DialogType.END_BLESS_HP));
        blesserDialog.add(new DialogTree.Dialog(2, "I want to increase my armor", "as you wish...", null, DialogTree.DialogType.END_BLESS_ARMOR));
        blesserDialog.add(new DialogTree.Dialog(3, "I want to increase my damage", "as you wish...", null, DialogTree.DialogType.END_BLESS_DAMAGE));
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


//        DialogTree Bless_tree = new DialogTree();
//        Bless_tree.getRoot().setNpcDialog("I can bless you... to better prepare you for the fight");
//        Bless_tree.getRoot().setIndex(0);
//        Bless_tree.getRoot().setDtype(DialogTree.DialogType.CONTINUE);
//        List<DialogTree.Dialog> blesserDialog = new ArrayList<>();
//        blesserDialog.add(new DialogTree.Dialog(1, "I want to increase my health", "as you wish...", null, DialogTree.DialogType.END_BLESS_HP));
//        blesserDialog.add(new DialogTree.Dialog(2, "I want to increase my armor", "as you wish...", null, DialogTree.DialogType.END_BLESS_ARMOR));
//        blesserDialog.add(new DialogTree.Dialog(3, "I want to increase my damage", "as you wish...", null, DialogTree.DialogType.END_BLESS_DAMAGE))
//        Bless_tree.getRoot().setNextDialogs(blesserDialog);
//
//
//        NPC_TALK guide = new NPC_TALK("Guide", "Hi, I am your guide to this world!",
//                10,9,10,10,10,10,0.01, Element.Normal,
//                null, 0,0,0,null);

        testNPC.put(new Coordinate(0,0), l_fairy);
        testNPC.put(new Coordinate(0,1), l_fairy);
        testNPC.put(new Coordinate(0,2), a_fairy);
        testNPC.put(new Coordinate(0,3), l_fairy);
        testNPC.put(new Coordinate(0,4), a_fairy);
        testNPC.put(new Coordinate(0,5), t_fairy);
        testNPC.put(new Coordinate(1,0), e_fairy);
        testNPC.put(new Coordinate(2,0), l_fairy);
        testNPC.put(new Coordinate(3,0), a_fairy);
        testNPC.put(new Coordinate(4,0), t_fairy);
        testNPC.put(new Coordinate(5,0), e_fairy);

        player.setMap_npcTData(testNPC);
//        player.saveTALKNPC();
//        HashMap<Coordinate, NPC_TALK> npcT = player.loadOriginalTalkNPCs();
    }

    //I need another json file to understand the structure
    @Test
    public void createNPCTalkJsonTest2(){

        Player player = new Player();
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

        DialogTree Bless_tree = new DialogTree();
        Bless_tree.getRoot().setNpcDialog("I can bless you... to better prepare you for the fight");
        Bless_tree.getRoot().setIndex(0);
        Bless_tree.getRoot().setDtype(DialogTree.DialogType.CONTINUE);
        List<DialogTree.Dialog> blesserDialog = new ArrayList<>();
        blesserDialog.add(new DialogTree.Dialog(1, "I want to increase my health", "as you wish...", null, DialogTree.DialogType.END_BLESS_HP));
        blesserDialog.add(new DialogTree.Dialog(2, "I want to increase my armor", "as you wish...", null, DialogTree.DialogType.END_BLESS_ARMOR));
        Bless_tree.getRoot().setNextDialogs(blesserDialog);

        NPC_TALK l_fairy = new NPC_TALK("Little_Fairy", "Hi, I can permanent increase your power!",
                100,100,1,100,0,50,0, Element.Normal,
                Bless_tree, 3,3,3,bag01);

        NPC_TALK t_fairy = new NPC_TALK("Teenager_Fairy", "Hi, I can permanent increase your power!",
                500,500,1,500,0,100,0, Element.Normal,
                Bless_tree, 7,6,4,bag02);

        HashMap<Coordinate, NPC_TALK> testNPC = new HashMap<>();
        testNPC.put(new Coordinate(0,0), l_fairy);
        testNPC.put(new Coordinate(0,1), t_fairy);

        player.setMap_npcTData(testNPC);
        //player.saveTALKNPC();


    }
    @Test
    public void createNPCTalkJsonTest3(){
        Player player = new Player();
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

        /**
         * Here is the fairy character as a instance
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

        /**
         * Random dude instance
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


        /**
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

        player.setMap_npcTData(createNPCJSON);
        player.saveTALKNPC();
    }









//    @Test
//    public void createMapJson(){
//        Player player = new Player();
//        HashMap<Coordinate, Place> testMap = new HashMap<>();
//
//        // Items
//        HashMap<String, Integer> c1P = new HashMap<>();
//        c1P.put("health", +15);
//        c1P.put("weight", 1);
//        c1P.put("value", 10);
//        Item c1 = new Item("C1", "consumable", "Mushroom", "Restores 15 health", c1P);
//        HashMap<String, Integer> c2P = new HashMap<>();
//        c2P.put("health", +50);
//        c2P.put("weight", 1);
//        c2P.put("value", 25);
//        Item c2 = new Item("C2", "consumable", "Lesser Healing Potion", "Restores 50 health", c2P);
//        HashMap<String, Integer> c3P = new HashMap<>();
//        c3P.put("health", +100);
//        c3P.put("weight", 1);
//        c3P.put("value", 50);
//        Item c3 = new Item("C3", "consumable", "Healing Potion", "Restores 100 health", c3P);
//        HashMap<String, Integer> c4P = new HashMap<>();
//        c4P.put("health", +150);
//        c4P.put("weight", 1);
//        c4P.put("value", 100);
//        Item c4 = new Item("C4", "consumable", "Greater Healing Potion", "Restores 100 health", c4P);
//
//        Bag bag01 = new Bag(100); // has 4 mushroom
//        bag01.put(c1);
//        bag01.put(c1);
//        bag01.put(c1);
//        bag01.put(c1);
//
//        Bag bag02 = new Bag(100); // has 2 mushroom, 1 Lesser Healing Potion
//        bag02.put(c1);
//        bag02.put(c2);
//        bag02.put(c1);
//
//        Bag bag03 = new Bag(100); // has 1 Lesser potion, 1 Healing Potion
//        bag03.put(c3);
//        bag03.put(c2);
//
//        Bag bag04 = new Bag(100); // has 1 Greater Healing Potion
//        bag04.put(c4);
//
//        DialogTree Bless_tree = new DialogTree();
//        Bless_tree.getRoot().setNpcDialog("I can bless you... to better prepare you for the fight");
//        Bless_tree.getRoot().setIndex(0);
//        Bless_tree.getRoot().setDtype(DialogTree.DialogType.CONTINUE);
//        List<DialogTree.Dialog> blesserDialog = new ArrayList<>();
//        blesserDialog.add(new DialogTree.Dialog(1, "I want to increase my health", "as you wish...", null, DialogTree.DialogType.END_BLESS_HP));
//        blesserDialog.add(new DialogTree.Dialog(2, "I want to increase my armor", "as you wish...", null, DialogTree.DialogType.END_BLESS_ARMOR));
//        blesserDialog.add(new DialogTree.Dialog(3, "I want to increase my damage", "as you wish...", null, DialogTree.DialogType.END_BLESS_DAMAGE));
//        Bless_tree.getRoot().setNextDialogs(blesserDialog);
//
//        NPC_TALK l_fairy = new NPC_TALK("Little_Fairy", "Hi, I can permanent increase your power!",
//                100,100,1,100,0,50,0, Element.Normal,
//                Bless_tree, 3,3,3,null);
//
//        NPC_TALK t_fairy = new NPC_TALK("Teenager_Fairy", "Hi, I can permanent increase your power!",
//                500,500,1,500,0,100,0, Element.Normal,
//                Bless_tree, 7,6,4,null);
//
//        NPC_TALK a_fairy = new NPC_TALK("Adult_Fairy", "Hi, I can permanent increase your power!",
//                1000,1000,1,1000,0,500,0, Element.Normal,
//                Bless_tree, 13,10,10,null);
//
//        NPC_TALK e_fairy = new NPC_TALK("Elder_Fairy", "Hi, I can permanent increase your power!",
//                10000,10000,1,2000,0,1000,0, Element.Normal,
//                Bless_tree, 50,30,40,null);
//
//        List<AbnormalPoint> f1 = new ArrayList<>();
//        f1.add(l_fairy);
//        List<AbnormalPoint> f2 = new ArrayList<>();
//        f2.add(t_fairy);
//        List<AbnormalPoint> f3 = new ArrayList<>();
//        f3.add(l_fairy);
//        f3.add(t_fairy);
//        List<AbnormalPoint> f4 = new ArrayList<>();
//        f4.add(a_fairy);
//        List<AbnormalPoint> f5 = new ArrayList<>();
//        f5.add(e_fairy);
//
//        Place p00 = new Place(new Coordinate(0,0), "The initial point", 0, bag01, f1);
//        Place p01 = new Place(new Coordinate(0,1), "fairy land", 0, bag02, f2);
//        Place p02 = new Place(new Coordinate(0,2), "some wild area", 0, bag01, new ArrayList<>());
//        Place p03 = new Place(new Coordinate(0,3), "fairy land", 0, bag03, f4);
//        Place p04 = new Place(new Coordinate(0,4), "fairy land", 0, new Bag(100), f5);
//        Place p10 = new Place(new Coordinate(1,0), "fairy land", 0, bag01, f1);
//        Place p11 = new Place(new Coordinate(1,1), "some wild area", 0, new Bag(100), new ArrayList<>());
//        Place p12 = new Place(new Coordinate(1,2), "some wild area", 0, new Bag(100), new ArrayList<>());
//        Place p13 = new Place(new Coordinate(1,3), "fairy land", 0, bag02, f2);
//        Place p14 = new Place(new Coordinate(1,4), "fairy land", 0, bag04, f4);
//        Place p20 = new Place(new Coordinate(1,0), "fairy land", 0, bag01, f1);
//        Place p21 = new Place(new Coordinate(1,1), "some wild area", 0, bag03, new ArrayList<>());
//        Place p22 = new Place(new Coordinate(1,2), "some wild area", 0, bag04, new ArrayList<>());
//        Place p23 = new Place(new Coordinate(1,3), "fairy land", 0, bag01, f2);
//        Place p24 = new Place(new Coordinate(1,4), "fairy land", 0, bag02, f4);
//
//        testMap.put(new Coordinate(0,0), p00);
//        testMap.put(new Coordinate(0,1), p01);
//        testMap.put(new Coordinate(0,2), p02);
//        testMap.put(new Coordinate(0,3), p03);
//        testMap.put(new Coordinate(0,4), p04);
//        testMap.put(new Coordinate(1,0), p10);
//        testMap.put(new Coordinate(1,1), p11);
//        testMap.put(new Coordinate(1,2), p12);
//        testMap.put(new Coordinate(1,3), p13);
//        testMap.put(new Coordinate(1,4), p14);
//        testMap.put(new Coordinate(2,0), p20);
//        testMap.put(new Coordinate(2,1), p21);
//        testMap.put(new Coordinate(2,2), p22);
//        testMap.put(new Coordinate(2,3), p23);
//        testMap.put(new Coordinate(2,4), p24);
//
//
////        DialogTree Bless_tree = new DialogTree();
////        Bless_tree.getRoot().setNpcDialog("I can bless you... to better prepare you for the fight");
////        Bless_tree.getRoot().setIndex(0);
////        Bless_tree.getRoot().setDtype(DialogTree.DialogType.CONTINUE);
////        List<DialogTree.Dialog> blesserDialog = new ArrayList<>();
////        blesserDialog.add(new DialogTree.Dialog(1, "I want to increase my health", "as you wish...", null, DialogTree.DialogType.END_BLESS_HP));
////        blesserDialog.add(new DialogTree.Dialog(2, "I want to increase my armor", "as you wish...", null, DialogTree.DialogType.END_BLESS_ARMOR));
////        blesserDialog.add(new DialogTree.Dialog(3, "I want to increase my damage", "as you wish...", null, DialogTree.DialogType.END_BLESS_DAMAGE))
////        Bless_tree.getRoot().setNextDialogs(blesserDialog);
////
////
////        NPC_TALK guide = new NPC_TALK("Guide", "Hi, I am your guide to this world!",
////                10,9,10,10,10,10,0.01, Element.Normal,
////                null, 0,0,0,null);
//
//
//        player.setMapData(testMap);
//        player.saveMap();
//        HashMap<Coordinate, Place> map = player.loadOriginalMapData();
//        assertEquals(1, 1);
//    }
}
