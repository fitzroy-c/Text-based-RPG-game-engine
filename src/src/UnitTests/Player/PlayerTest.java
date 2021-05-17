package Player;

import navigation.Coordinate;
import navigation.Direction;
import navigation.Place;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author yitao chen
 */
public class PlayerTest {
    @Test
    public void createPlaceTopDangerTest() {
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
        assertNotEquals(null, player.checkMonster());
    }


    @Test
    public void createPlaceSafeTest() {
        Coordinate coordinate = new Coordinate(1, 1);
        String description = "Safe house";
        Place place = new Place(coordinate, description);
        place.setDangerRate(0);

        Player player = new Player("test one");
        player.place = place;
        assertEquals(null, player.checkMonster());
    }

    @Test
    public void goToDirectionTest(){
        Player player = new Player("test one");
        assertEquals(false, player.goToDirection("south"));
        assertEquals(true, player.goToDirection("north"));
        assertEquals(false, player.goToDirection("west"));
        assertEquals(true, player.goToDirection("east"));
        assertEquals(true, player.goToDirection("wrongOrder"));
    }
    @Test
    public void goToDirectionTest1(){
        Player player = new Player("test one");
        player.goToDirection("north");
        player.goToDirection("east");
        Coordinate coordinate = new Coordinate(1, 1);
        assertEquals(coordinate, player.place.getCoordinate());
    }
}