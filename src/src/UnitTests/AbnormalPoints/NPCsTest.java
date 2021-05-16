package AbnormalPoints;

import Card.Element;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * @author yitao chen
 * test all npcs(monsters)
 */
public class NPCsTest {

    private AbnormalPoint dummyMonster;
    private int playerLevel;

    @Before
    public void setUp() {
        dummyMonster = new AbnormalPoint() { };
        playerLevel = 5;
    }

    @Test
    public void monsterTypeCompareTest() {
        Troll troll = new Troll(playerLevel);
        Troll anotherTroll = new Troll(playerLevel);
        Goblin notTroll = new Goblin(playerLevel);

        assertEquals(troll, anotherTroll);
        assertNotEquals(troll, notTroll);
        assertNotEquals(null, troll);
        assertNotEquals(troll, new Object());
    }


    @Test
    public void XPTest() {
        dummyMonster.setXPGain(1000);
        assertEquals(1000, dummyMonster.getXPGain());
    }

    @Test
    public void monsterGenerateTest() {
        Goblin goblin = new Goblin(playerLevel);
        assertEquals("Goblin", goblin.monsterType);

        Giant giant = new Giant(playerLevel);
        assertEquals("Giant", giant.monsterType);

        Skeleton skeleton = new Skeleton(playerLevel);
        assertEquals("Skeleton", skeleton.monsterType);

        Troll troll = new Troll(playerLevel);
        assertEquals("Troll", troll.monsterType);

        Wolf wolf = new Wolf(playerLevel);
        assertEquals("Wolf", wolf.monsterType);
    }

    @Test
    public void CharacteristicTest() {
        Goblin goblin = new Goblin(playerLevel);
        assertEquals(Element.Normal, goblin.element);

        Goblin goblin2 = new Goblin(playerLevel,Element.Dark);
        assertEquals(Element.Dark, goblin2.element);

        Troll troll = new Troll(playerLevel);
        assertEquals(Element.Normal, troll.element);

        Troll troll2 = new Troll(playerLevel,Element.Dark);
        assertEquals(Element.Dark, troll2.element);

        Skeleton skeleton = new Skeleton(playerLevel);
        assertEquals(Element.Normal,skeleton.element);

        Skeleton skeleton2 = new Skeleton(playerLevel,Element.Light);
        assertEquals(Element.Light,skeleton2.element);

        Wolf wolf = new Wolf(playerLevel);
        assertEquals(Element.Normal,wolf.element);

        Wolf wolf2 = new Wolf(playerLevel,Element.Earth);
        assertEquals(Element.Earth,wolf2.element);

        Giant giant = new Giant(playerLevel);
        assertEquals(Element.Normal,giant.element);

        Giant giant2 = new Giant(playerLevel,Element.Nature);
        assertEquals(Element.Nature,giant2.element);
    }
}
