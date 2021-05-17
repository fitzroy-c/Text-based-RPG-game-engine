package navigation;

import Player.Item;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author yitao chen
 */
public class PlaceTest {
    @Test
    public void newPlace() {
        Coordinate coordinate = new Coordinate(0, 0);
        String description = "Welcome to the magic World, adventurer";
        Place place = new Place(coordinate, description);

        assertEquals(coordinate, place.getCoordinate());
        assertEquals(description, place.getDescription());
    }

    public Place createPlace() {
        Coordinate coordinate = new Coordinate(1, 1);
        String description = "You enter into the adventure forest, quite cold and wet inside here";
        return new Place(coordinate, description);
    }

    @Test
    public void placeItem() {
        Place testPlace = createPlace();
        //String id, String type, String name, String description, Map<String, Integer> properties
        HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
        hashmap.put("weight",1);
        hashmap.put("damage",30);
        Item bottle = new Item("simple bottle", "weapon", "bottle",  "old bottle", hashmap);

        testPlace.addItem(bottle);
        Item found = testPlace.removeItem(bottle);

        assertEquals(bottle.getId(), found.getId());
    }
}