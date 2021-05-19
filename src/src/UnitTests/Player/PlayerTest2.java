package Player;

import AbnormalPoints.AbnormalPoint;
import AbnormalPoints.DialogTree;
import AbnormalPoints.NPC_TALK;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
    public void testNewplayerWithJson() {
        Player player = new Player( "ddd");
        assertEquals("ddd",player.getName());
//        assertEquals(false, player.getMap_bagData().get(new Coordinate(0,0)).isEmpty());


    }

    public static void main(String[] args) {
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
