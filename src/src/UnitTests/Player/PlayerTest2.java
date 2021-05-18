package Player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import navigation.Coordinate;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

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
    public void createJson(){
        Player player = new Player();
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
    }
}
