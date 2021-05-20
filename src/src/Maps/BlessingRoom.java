package Maps;

/**
 * Fairy blessing is passive effect given to player
 * - Player can choose 1 out of 3 blessing that the fairy offers in current level
 * - FairyBlessing will happen no more than once per environment
 * - There are good fairy, which will only hand out positive passives
 * - There are bad fairy, which will give good blessing that comes with a cost.
 *
 * this function is for future development
 */
public class BlessingRoom extends Rooms {
    int[] blessingSet;
    public BlessingRoom(String name, String description) {
        super(name, description);
    }


    class blessing {

    }
}
