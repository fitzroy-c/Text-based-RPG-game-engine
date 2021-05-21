package DesignForFutureExtension.Maps;

/**
 * Fairy blessing is passive effect given to player
 * - Player can choose 1 out of 3 blessing that the fairy offers in current level
 * - FairyBlessing will happen no more than once per environment
 * - There are good fairy, which will only hand out positive passives
 * - There are bad fairy, which will give good blessing that comes with a cost.
 *
 * this class is for future development
 * @author Guanming Ou
 */
public class BlessingRoom extends Rooms {
    int[] blessingSet;
    public BlessingRoom(String name, String description) {
        super(name, description);
    }


    class blessing {

    }
}
