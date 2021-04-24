package Maps;

/**
 * This room allow players to destroy (remove card) from their currently card pool
 *  - The first time enter this room is free (per card destroy room)
 *  - More card destroy require increase gold
 */
public class CardDestroyRoom extends Rooms{
    public CardDestroyRoom(String name, String description) {
        super(name, description);
    }
}
