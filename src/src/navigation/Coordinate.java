package navigation;

/**
 * This class takes two possible constructions: either a string or two integers.
 * Trans either way to a coordinate.
 * @author yitao chen
 */
public class Coordinate {
    public int x;
    public int y;

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

    /**
     * put a direction
     * @param direction the direction that play want to move
     * @return A coordinate
     */

    public Coordinate getBorderCoordinate(Direction direction) {
        return new Coordinate(x + direction.getDx(), y + direction.getDy());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    /**
     * @author Yixiang Yin
     */
    public void goNorth(){
        y+=1;
    }
    public void goSouth(){
        y-=1;
    }
    public void goEast(){
        x+=1;
    }
    public void goWest(){
        x-=1;
    }

    /**
     * @author create by Yitao Chen, modified by Yixiang Yin
     */
    @Override
    public boolean equals(Object o) {
        Coordinate target = (Coordinate) o;

        if (this.x==target.x | this.y == target.y) return true;
        return false;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 100 * result + y;
        return result;
    }

    /**
     * Transform from string to coordinate
     * @param string the coordinate in string form e.g. "x,y"
     * @return A coordinate
     * @author Guanming Ou
     */
    public static Coordinate fromStringToCoordinate(String string){
        String[] parts = string.split(",");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        return new Coordinate(x, y);
    }
    @Override
    public String toString() {
        return x + "," + y;
    }
}
