package navigation;

/**
 * This class takes two possible constructions: either a string or two integers.
 * Trans either way to a coordinate.
 * @author yitao chen
 */
public class Coordinate {
    public final int x;
    public final int y;

    /**
     * a 2D coordinate based on a String.
     *
     * @param rawCoordinate - A String containing two numbers, separated by a comma
     * like {@code 'x, y'}
     */
    public Coordinate(String rawCoordinate) {
        String[] parts = rawCoordinate.split(",");
        this.x = Integer.parseInt(parts[0]);
        this.y = Integer.parseInt(parts[1]);
    }

    /**
     * a 2D coordinate based on a String.
     *
     * @param x - The X position
     * @param y - The Y position
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate getBorderCoordinate(Direction direction) {
        return new Coordinate(x + direction.getDx(), y + direction.getDy());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate target = (Coordinate) o;

        if (x != target.x) return false;
        if (y != target.y) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}
