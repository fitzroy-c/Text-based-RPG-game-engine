package Player;

import AbnormalPoints.Monster;
import AbnormalPoints.MonsterAttributes;
import Card.Element;
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


    @Test
    public void createMonsterTest(){
        Coordinate coordinate = new Coordinate(0, 0);
        String description = "A monster is here";
        Place place = new Place(coordinate, description);
        place.setDangerRate(6);

        Player player = new Player("test one");
        player.place = place;
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

    @Test
    public void goToDirectionTest1(){
        Player player = new Player("test one");
        assertEquals(false, player.goToDirection("south"));
        assertEquals(true, player.goToDirection("north"));
        assertEquals(false, player.goToDirection("west"));
        assertEquals(true, player.goToDirection("east"));
        assertEquals(true, player.goToDirection("wrongOrder"));
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
    public void attackTest1(){
        Player player = new Player("test one");
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
}