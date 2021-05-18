package Player;

import AbnormalPoints.AbnormalPoint;
import AbnormalPoints.DialogTree;
import AbnormalPoints.NPC_TALK;
import Card.Element;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import navigation.Coordinate;
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
    public void createItemJson(){
        Player player = new Player();

        HashMap<Coordinate, Bag> testBag = new HashMap<>();
        HashMap<String, Integer> c1P = new HashMap<>();
        c1P.put("health", +15);
        c1P.put("weight", 1);
        c1P.put("value", 10);
        Item c1 = new Item("C1", "consumable", "Mushroom", "Restores 15 health", c1P);
        HashMap<String, Integer> c2P = new HashMap<>();
        c2P.put("health", +50);
        c2P.put("weight", 1);
        c2P.put("value", 25);
        Item c2 = new Item("C2", "consumable", "Lesser Healing Potion", "Restores 50 health", c2P);
        HashMap<String, Integer> c3P = new HashMap<>();
        c3P.put("health", +100);
        c3P.put("weight", 1);
        c3P.put("value", 50);
        Item c3 = new Item("C3", "consumable", "Healing Potion", "Restores 100 health", c3P);
        HashMap<String, Integer> c4P = new HashMap<>();
        c4P.put("health", +150);
        c4P.put("weight", 1);
        c4P.put("value", 100);
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

        player.setStorageInfo(testBag);
        player.saveItem();
        HashMap<Coordinate, Bag> items = player.loadOriginalItems();

    }

    @Test
    public void createNPCJson(){
        Player player = new Player();
        HashMap<Coordinate, AbnormalPoint> testNPC = new HashMap<>();

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
                Bless_tree, 3,3,3,null);

        NPC_TALK t_fairy = new NPC_TALK("Teenager_Fairy", "Hi, I can permanent increase your power!",
                500,500,1,500,0,100,0, Element.Normal,
                Bless_tree, 7,6,4,null);

        NPC_TALK a_fairy = new NPC_TALK("Adult_Fairy", "Hi, I can permanent increase your power!",
                1000,1000,1,1000,0,500,0, Element.Normal,
                Bless_tree, 13,10,10,null);

        NPC_TALK e_fairy = new NPC_TALK("Elder_Fairy", "Hi, I can permanent increase your power!",
                10000,10000,1,2000,0,1000,0, Element.Normal,
                Bless_tree, 50,30,40,null);


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

        player.setNpcInfo(testNPC);
        player.saveNPC();
    }




}
