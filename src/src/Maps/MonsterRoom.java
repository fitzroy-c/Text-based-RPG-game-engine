package Maps;

/**
 * Monster room is where player take combat with monsters
 * - There exist only 1 monster per room
 * - Gold is gain after defeating monsters (random amount of golds depends on monsters, difficulty and context)
 */
public class MonsterRoom extends Rooms{
    public MonsterRoom(String name, String description) {
        super(name, description);
    }

}
