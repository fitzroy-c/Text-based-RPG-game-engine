package UnitTests;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import CommandParser.CommandTokenizer;
import CommandParser.Token;
import Player.Player;
import org.junit.Test;

public class GameTest {
//    @Before
//    public void creategame({
//
//    })
    CommandTokenizer t;
    @Test(timeout=1000)
    public void testJson() throws Exception {
    }
    @Test(timeout=1000)
    public void testSaveThePlayerInfo() throws Exception {
        Player p = new Player("Yixiang Yin");
        p.save();
    }

    @Test(timeout=1000)
    public void testTokenizer1() throws Exception {
        t = new CommandTokenizer("consume Mushroom");
        assertTrue(t.current().token().equals("consume"));
        assertTrue(t.current().type()==Token.Type.CONSUME_ACTION);

        assertTrue(t.hasNext());
        t.next();
//        assertEquals("case sensitive","Mushroom", t.current().token());
        assertEquals("wrong token type", t.current().type(), Token.Type.ITEM);
        //todo recommend do a search in the item.json, if found, give item type

    }
//    @Test(timeout=1000)
//    public void testInitializeItems() throws Exception {
//        List<Item> items  = initializeItems();
//        assertTrue(searchInItemJson("Mushroom"));
//
//    }
//    public static void main(String[] args) {
//        searchInItemJson("Mushroom");
//    }
}
