package navigation;

/**
 * Three properties: A description (in-game text) and two integers.
 * The integers are added to a coordinate to get the new direction
 * (i.e. to get a north coordinate you add 1 to the y property of a coordinate).
 * @author yitao chen
 */
public enum Direction {
    NORTH("To the North", 0, 1),
    SOUTH("To the South", 0, -1),
    EAST("To the East", 1, 0),
    WEST("To the West", -1, 0);


    private final String description;
    private final int dx;
    private final int dy;


    private Direction(String description, int dx, int dy) {
        this.description = description;
        this.dx = dx;
        this.dy = dy;
    }

    public String getDescription() {
        return description;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

}
