package Player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

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




    }


}
