package DesignForFutureExtension.Maps;

import AbnormalPoints.Monster;

/**
 * Monster room is where player take combat with monsters
 * - There exist only 1 monster per room
 * - Gold is gain after defeating monsters (random amount of golds depends on monsters, difficulty and context)
 *
 * this class is for future development
 * @author Guanming Ou
 */
public class MonsterRoom extends Rooms{

    Monster monster;

    public MonsterRoom(String name, String description, Monster monster) {
        super(name, description);
        this.monster = monster;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }
}
