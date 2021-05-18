package Player;

import org.junit.Test;

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


}
