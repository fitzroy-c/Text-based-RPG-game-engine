package navigation;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author yitao chen
 */
public class CoordinateTest {

    @Test
    public void testRawCoordinate() {
        Coordinate coordinate = new Coordinate("1,1");
        assertEquals(1, coordinate.getX());
        assertEquals(1, coordinate.getY());
    }
    @Test
    public void testRawCoordinate1() {
        Coordinate coordinate = new Coordinate(1,-1);
        assertEquals(1, coordinate.getX());
        assertEquals(-1, coordinate.getY());
    }

    @Test
    public void testBorderCoordinateNorth() {
        Coordinate coordinate = new Coordinate("1,1");
        Coordinate borderingCoordinate = coordinate.getBorderCoordinate(Direction.NORTH);
        assertEquals(1, borderingCoordinate.getX());
        assertEquals(2, borderingCoordinate.getY());
    }

    @Test
    public void testBorderingCoordinateSouth() {
        Coordinate coordinate = new Coordinate("1,1");
        Coordinate borderingCoordinate = coordinate.getBorderCoordinate(Direction.SOUTH);
        assertEquals(1, borderingCoordinate.getX());
        assertEquals(0, borderingCoordinate.getY());
    }

    @Test
    public void testBorderingCoordinateWest() {
        Coordinate coordinate = new Coordinate("1,1");
        Coordinate borderingCoordinate = coordinate.getBorderCoordinate(Direction.WEST);
        assertEquals(0, borderingCoordinate.getX());
        assertEquals(1, borderingCoordinate.getY());
    }
    @Test
    public void testBorderingCoordinateEast() {
        Coordinate coordinate = new Coordinate("1,1");
        Coordinate borderingCoordinate = coordinate.getBorderCoordinate(Direction.EAST);
        assertEquals(2, borderingCoordinate.getX());
        assertEquals(1, borderingCoordinate.getY());
    }

}