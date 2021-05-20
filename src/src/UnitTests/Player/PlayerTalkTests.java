package Player;

import AbnormalPoints.DialogTree;
import AbnormalPoints.Monster;
import AbnormalPoints.MonsterAttributes;
import AbnormalPoints.NPC_TALK;
import Card.Element;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class PlayerTalkTests {
//    public static void main(String[] args) {
//        HashMap<String, Integer> c1P = new HashMap<>();
//        c1P.put("health", +15);
//        c1P.put("weight", 1);
//        c1P.put("value", 10);
//        Item c1 = new Item("C1", "consumable", "Mushroom", "Restores 15 health", c1P);
//        HashMap<String, Integer> c2P = new HashMap<>();
//
//        c1P.put("health", +50);
//        c1P.put("weight", 1);
//        c1P.put("value", 25);
//        Item c2 = new Item("C2", "consumable", "Lesser Healing Potion", "Restores 50 health", c2P);
//        HashMap<String, Integer> c3P = new HashMap<>();
//        c1P.put("health", +100);
//        c1P.put("weight", 1);
//        c1P.put("value", 50);
//        Item c3 = new Item("C3", "consumable", "Healing Potion", "Restores 100 health", c3P);
//        Bag bag01 = new Bag(100); // has 4 mushroom
//        bag01.put(c1);
//        bag01.put(c1);
//        Bag bag02 = new Bag(100); // has 2 mushroom, 1 Lesser Healing Potion
//        bag02.put(c1);
//        bag02.put(c2);
//
//        DialogTree Bless_tree = new DialogTree();
//        Bless_tree.getRoot().setNpcDialog("I can bless you... to better prepare you for the fight");
//        Bless_tree.getRoot().setIndex(0);
//        Bless_tree.getRoot().setDtype(DialogTree.DialogType.CONTINUE);
//        List<DialogTree.Dialog> blesserDialog = new ArrayList<>();
//        blesserDialog.add(new DialogTree.Dialog(1, "I want to increase my health", "as you wish...", null, DialogTree.DialogType.END_BLESS_HP));
//        blesserDialog.add(new DialogTree.Dialog(2, "I want to increase my armor", "as you wish...", null, DialogTree.DialogType.END_BLESS_ARMOR));
//        blesserDialog.add(new DialogTree.Dialog(3, "I want to increase my damage", "as you wish...", null, DialogTree.DialogType.END_BLESS_DAMAGE));
//
//        Bless_tree.getRoot().setNextDialogs(blesserDialog);
//
//        NPC_TALK l_fairy = new NPC_TALK("Little_Fairy", "Hi, I can permanent increase your power!",
//                100,100,1,100,0,50,0, Element.Normal,
//                Bless_tree, 3,3,3,bag01);
//
//        NPC_TALK t_fairy = new NPC_TALK("Teenager_Fairy", "Hi, I can permanent increase your power!",
//                500,500,1,500,0,100,0, Element.Normal,
//                Bless_tree, 7,6,4,bag02);
//
//        MonsterAttributes giant = new MonsterAttributes("giant","A monster with high health and damage, but low armour.",
//                150, 8, 6, 3,40,3,
//                0.03,50, 3,15, 11, Element.Normal);
//
//
////        // test reply of when abnormal point is empty
//        Player player = new Player("testPlayer");
//        String t = player.talk();
//        assertEquals("There is no one to talk to.", t);
//
//        player.place.addAbnormalPoint(new Monster(giant, 1));
//        String t2 = player.talk();
//        assertEquals("You can't talk to a monster or a merchant.", t2);
//
//        l_fairy.setHasEndedTalk(true);
//        player.place.addAbnormalPoint(l_fairy);
//        String t3 = player.talk();
//        assertEquals("You cannot talk to already talked npc.", t3);
//
//        player.place.removeAbnormalPoint(l_fairy);
//        player.place.addAbnormalPoint(t_fairy);
//        player.talk();
//    }

    public static void main(String[] args) {
        DialogTree guide_tree = new DialogTree();
        guide_tree.getRoot().setNpcDialog("Hi, are you ok?  I am your guide to this land. What do you want to know about?");
        guide_tree.getRoot().setIndex(0);
        guide_tree.getRoot().setDtype(DialogTree.DialogType.CONTINUE);
        List<DialogTree.Dialog> Dialog1 = new ArrayList<>();

        // conversation branch 1
        List<DialogTree.Dialog> controlIntro13 = new ArrayList<>();
        controlIntro13.add(new DialogTree.Dialog(1, "No thanks, I want to earn the gold myself",
                "Okay, enjoy you adventure!.", null, DialogTree.DialogType.END_NONE));
        controlIntro13.add(new DialogTree.Dialog(2, "Sure, thanks again!",
                "Here you go.", null, DialogTree.DialogType.END_GIVE_GOLD));
        controlIntro13.add(new DialogTree.Dialog(3, "Can I get something to eat during my adventure?",
                "Sure, you can have this lesser health potion.", null, DialogTree.DialogType.END_GIVE_ITEM));
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
        Dialog1.add(new DialogTree.Dialog(3, "I already know what to do, bye.", "Bless you healthy!", null, DialogTree.DialogType.END_BLESS_HP));

        guide_tree.getRoot().setNextDialogs(Dialog1);

        HashMap<String, Integer> c2P = new HashMap<>();
        c2P.put("health", +100);
        c2P.put("weight", 1);
        c2P.put("value", 50);
        Bag bag01 = new Bag(100); // 1 Lesser Healing Potion
        Item c2 = new Item("C2", "consumable", "Lesser Healing Potion", "Restores 50 health", c2P);
        bag01.put(c2);
        bag01.put(c2);

        NPC_TALK guide = new NPC_TALK("Guide", "A guide waiting for you",
                100,100,10,3,10,10,0, Element.Normal,
                guide_tree, 5,0,0,bag01);


        Player player = new Player("testPlayer");
        player.place.addAbnormalPoint(guide);
        player.talk();
        // manual test hp bless (chose 3 )
//        player.setMaxHP(100);
//        player.setHP(100);
//        player.talk(); // try 3
//        System.out.println(player.getMaxHP()); // should be 105

        // manual test get gold
//        player.setMoney(0);
//        player.talk(); // try (1,1,1,1,1,1,1,1,1,1,1,1,1,1,2)
//        System.out.println(player.getMoney()); // should be 10

        // manual test get item
//        player.setBag(new Bag(100));
//        guide.getDialogTree().getRoot().setNextDialogs(controlIntro12);
//        player.talk(); // try (1,1,1,1,1,1,1,1,1,1,1,1,1,1,3)
//        player.bag.showMyBag(); // should be 2 lesser health potion
    }


    @Test
    public void testMultipleDialog(){
        DialogTree guide_tree = new DialogTree();
        guide_tree.getRoot().setNpcDialog("Hi, are you ok?  I am your guide to this land. What do you want to know about?");
        guide_tree.getRoot().setIndex(0);
        guide_tree.getRoot().setDtype(DialogTree.DialogType.CONTINUE);
        List<DialogTree.Dialog> Dialog1 = new ArrayList<>();

        // conversation branch 1
        List<DialogTree.Dialog> controlIntro13 = new ArrayList<>();
        controlIntro13.add(new DialogTree.Dialog(1, "No thanks, I want to earn the gold myself",
                "Okay, enjoy you adventure!.", null, DialogTree.DialogType.END_NONE));
        controlIntro13.add(new DialogTree.Dialog(2, "Sure, thanks again!",
                "Here you go.", null, DialogTree.DialogType.END_GIVE_GOLD));
        controlIntro13.add(new DialogTree.Dialog(3, "Can I get something to eat during my adventure?",
                "Sure, you can have this lesser health potion.", null, DialogTree.DialogType.END_GIVE_ITEM));
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
        Dialog1.add(new DialogTree.Dialog(3, "I already know what to do, bye.", "Bless your healthy!", null, DialogTree.DialogType.END_BLESS_HP));

        guide_tree.getRoot().setNextDialogs(Dialog1);

        HashMap<String, Integer> c2P = new HashMap<>();
        c2P.put("health", +100);
        c2P.put("weight", 1);
        c2P.put("value", 50);
        Bag bag01 = new Bag(100); // 1 Lesser Healing Potion
        Item c2 = new Item("C2", "consumable", "Lesser Healing Potion", "Restores 50 health", c2P);
        bag01.put(c2);

        NPC_TALK guide = new NPC_TALK("Guide", "A guide waiting for you",
                100,100,10,3,10,10,0, Element.Normal,
                guide_tree, 5,0,0,bag01);


        Player player = new Player("testPlayer");
        player.place.addAbnormalPoint(guide);

        DialogTree.Dialog nextDialog = guide.getDialogTree().matchDialog(1);
        DialogTree newTree = new DialogTree();
        newTree.setRoot(nextDialog);
        guide.setDialogTree(newTree);

        assertEquals(DialogTree.DialogType.CONTINUE, guide.getDialogTree().getRoot().getDtype());

        nextDialog = guide_tree.matchDialog(3);
        newTree = new DialogTree();
        newTree.setRoot(nextDialog);
        guide.setDialogTree(newTree);

        assertEquals(DialogTree.DialogType.END_BLESS_HP, guide.getDialogTree().getRoot().getDtype());
    }


    @Test
    public void testMatchDialog(){
        DialogTree Bless_tree = new DialogTree();
        Bless_tree.getRoot().setNpcDialog("I can bless you... to better prepare you for the fight");
        Bless_tree.getRoot().setIndex(0);
        Bless_tree.getRoot().setDtype(DialogTree.DialogType.CONTINUE);
        List<DialogTree.Dialog> blesserDialog = new ArrayList<>();
        blesserDialog.add(new DialogTree.Dialog(1, "I want to increase my health", "as you wish...", null, DialogTree.DialogType.END_BLESS_HP));
        blesserDialog.add(new DialogTree.Dialog(2, "I want to increase my armor", "as you wish...", null, DialogTree.DialogType.END_BLESS_ARMOR));
        blesserDialog.add(new DialogTree.Dialog(3, "I want to increase my damage", "as you wish...", null, DialogTree.DialogType.END_BLESS_DAMAGE));
        Bless_tree.getRoot().setNextDialogs(blesserDialog);

        //testHave
        int respondIndex = 1;
        DialogTree.Dialog testDialog = Bless_tree.matchDialog(respondIndex);
        DialogTree.Dialog test1 = new DialogTree.Dialog(1, "I want to increase my health", "as you wish...", null, DialogTree.DialogType.END_BLESS_HP);
        assertEquals(test1.getPlayerReply(), testDialog.getPlayerReply());
        // test have
        respondIndex = 2;
        testDialog = Bless_tree.matchDialog(respondIndex);
        test1 = new DialogTree.Dialog(2, "I want to increase my armor", "as you wish...", null, DialogTree.DialogType.END_BLESS_ARMOR);
        assertEquals(test1.getPlayerReply(), testDialog.getPlayerReply());

        respondIndex = 3;
        testDialog = Bless_tree.matchDialog(respondIndex);
        test1 = new DialogTree.Dialog(3, "I want to increase my damage", "as you wish...", null, DialogTree.DialogType.END_BLESS_DAMAGE);
        assertEquals(test1.getPlayerReply(), testDialog.getPlayerReply());
        // test not have
        respondIndex = 4;
        testDialog = Bless_tree.matchDialog(respondIndex);
        assertEquals(null, testDialog);
    }

    @Test
    public void testIsAllInt(){
        String playerResponse = "1";
        assertTrue(Player.isAllInt(playerResponse));
        playerResponse = "100";
        assertTrue(Player.isAllInt(playerResponse));
        playerResponse = "0";
        assertTrue(Player.isAllInt(playerResponse));
        playerResponse = "sds";
        Boolean t = Player.isAllInt(playerResponse);
        assertFalse(t);
        playerResponse = "iav.;'!";
        assertFalse(Player.isAllInt(playerResponse));
        playerResponse = "-100";
        assertFalse(Player.isAllInt(playerResponse));
    }
}
