package Player;

import AbnormalPoints.Monster;
import AbnormalPoints.MonsterAttributes;
import Card.Element;
import Options.Control;
import navigation.Coordinate;
import navigation.Place;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author yitao chen
 */
public class PlayerTest {
    @Test
    public void setPlaceTopDangerTest() {
        Coordinate coordinate = new Coordinate(0, 0);
        String description = "A monster is here";
        Place place = new Place(coordinate, description);
        place.setDangerRate(6);
        assertEquals(6, place.getDangerRate());
    }

    /*
    old tes when player.generateMonster() still return monster
    It's updated to renew the player.place.abnormalPoint
    @Test
    public void createMonsterTest(){
        Coordinate coordinate = new Coordinate(0, 0);
        String description = "A monster is here";
        Place place = new Place(coordinate, description);
        place.setDangerRate(6);

        Player player = new Player("test one");
        player.place = place;
        player.generateMonster();
        Monster monster = player.generateMonster();
        assertNotEquals(null, monster);
    }

    @Test
    public void createPlaceSafeTest() {
        Coordinate coordinate = new Coordinate(1, 1);
        String description = "Safe house";
        Place place = new Place(coordinate, description);
        place.setDangerRate(0);

        Player player = new Player("test one");
        player.place = place;
        assertEquals(null, player.generateMonster());
    }

     */

    /**
     * this 2 test is to test the function of checkMonsterTest
     * and generateMonster function as well
     */
    @Test
    public void checkMonsterTest1(){
        Coordinate coordinate = new Coordinate(1, 1);
        System.out.println("--------------------\n safe zone test\n");
        String description = "Safe house";
        Place place1 = new Place(coordinate, description);
        place1.setDangerRate(1);
        Player player = new Player("test one");
        player.place = place1;
        player.generateMonster();
        System.out.println(player.checkMonster()); //should be quiet and little chance to meet a monster
    }
    @Test
    public void checkMonsterTest2(){
        Coordinate coordinate = new Coordinate(1, 1);
        System.out.println("--------------------\n check monster test\n");
        String description = "Danger zone";
        Place place2 = new Place(coordinate, description);
        Player player = new Player("test one");
        place2.setDangerRate(5);
        player.place = place2;
        player.generateMonster();
        System.out.println(player.checkMonster());//should be extremely dangerous and high chance to meet a monster

    }

    @Test
    public void goToDirectionTest1(){
        Player player = new Player("test one");
        assertEquals("direction out of map's boundary", player.goToDirection("south"));
        assertEquals("You've moved to north direction", player.goToDirection("north"));
        assertEquals("direction out of map's boundary", player.goToDirection("west"));
        assertEquals("You've moved to east direction", player.goToDirection("east"));
        //assertEquals("You've moved to wrongOrder direction", player.goToDirection("wrongOrder"));
    }
    @Test
    public void goToDirectionTest2(){
        Player player = new Player("test one");
        player.goToDirection("north");
        player.goToDirection("east");
        Coordinate coordinate = new Coordinate(1, 1);
        assertEquals(coordinate, player.place.getCoordinate());
    }

    @Test
    public void attackTest(){
        Player player = new Player("test one");
        System.out.println("--------------------\n attack test\n");
        System.out.println("show the before stat");
        System.out.println(player.showPlayerStat());
        System.out.println();
        MonsterAttributes goblin = new MonsterAttributes("goblin", "A normal monster , with slight armour.",
                55,6, 0,3,12,2,
                0.02,10, 3,0,5, Element.Normal);
        Monster monster = new Monster(goblin,player.getLevel());
        player.place.addAbnormalPoint(monster);

        System.out.println(player.attack());

        System.out.println("show the current stat");
        System.out.println(player.showPlayerStat());
    }

    @Test
    public void attackCriticalTest(){
        Player player = new Player("test one");
        System.out.println("--------------------\n critical attack test\n");
        System.out.println("show the before stat");
        System.out.println(player.showPlayerStat());
        System.out.println();
        player.setCriticalChance(1);

        MonsterAttributes goblin = new MonsterAttributes("goblin", "A normal monster , with slight armour.",
                55,6, 0,3,12,2,
                0.02,10, 3,0,5, Element.Normal);
        Monster monster = new Monster(goblin,player.getLevel());
        player.place.addAbnormalPoint(monster);

        System.out.println(player.attack());

        System.out.println("show the current stat");
        System.out.println(player.showPlayerStat());
    }

    @Test
    public void attackLoopTest(){
        Player player = new Player("test one");
        System.out.println("--------------------\n attack loop test\n");
        System.out.println("show the before stat");
        System.out.println(player.showPlayerStat());
        System.out.println();
        player.setDamage(5);
        MonsterAttributes goblin = new MonsterAttributes("goblin", "A normal monster , with slight armour.",
                55,6, 5,3,12,2,
                0.02,10, 3,0,5, Element.Normal);

        Monster monster = new Monster(goblin,player.getLevel());
        player.place.addAbnormalPoint(monster);

        System.out.println(player.attack());

        System.out.println("show the current stat");
        System.out.println(player.showPlayerStat());
    }

    @Test
    public void retreatTest() throws Exception {
        Player player = new Player("test one");
        System.out.println("--------------------\n retreat test\n");
        System.out.println("show the before stat");
        System.out.println(player.showPlayerStat());
        System.out.println();
        MonsterAttributes goblin = new MonsterAttributes("goblin", "A normal monster , with slight armour.",
                55,6, 5,3,12,2,
                0.02,10, 3,0,5, Element.Normal);

        Monster monster = new Monster(goblin,player.getLevel());
        player.place.addAbnormalPoint(monster);
        Control control = new Control(player);

        System.out.println(player.retreat());
        System.out.println("show the current stat");
        System.out.println(player.showPlayerStat());
    }

    @Test
    public void bribeTest1() {
        Player player = new Player("test one");
        System.out.println("--------------------\n bribe success test\n");
        System.out.println("show the before stat");
        System.out.println(player.showPlayerStat());
        System.out.println();
        MonsterAttributes goblin = new MonsterAttributes("goblin", "A normal monster , with slight armour.",
                55,6, 5,3,12,2,
                0.02,10, 3,0,5, Element.Normal);

        Monster monster = new Monster(goblin,player.getLevel());
        player.place.addAbnormalPoint(monster);


        System.out.println(player.bribe());

        System.out.println("show the current stat");
        System.out.println(player.showPlayerStat());
    }

    @Test
    public void bribeTest2() {
        Player player = new Player("test one");
        System.out.println("--------------------\n bribe fail test\n");
        System.out.println("show the before stat");
        System.out.println(player.showPlayerStat());
        System.out.println();
        MonsterAttributes goblin = new MonsterAttributes("goblin", "A normal monster , with slight armour.",
                55,6, 5,3,12,2,
                0.02,10, 3,0,5, Element.Normal);

        Monster monster = new Monster(goblin,player.getLevel());
        player.place.addAbnormalPoint(monster);
        player.setMoney(0);//no money

        System.out.println(player.bribe());

        System.out.println("show the current stat");
        System.out.println(player.showPlayerStat());
    }
}