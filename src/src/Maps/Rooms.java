package Maps;

/**
 * Basically, player are given option to go which room out of three options
 * Initially the rooms are randommly draw with three option.
 * Player can select which room they go to, either enter combat, shops, found a threasure, or receive blessing from fairies
 * New options are draw everytime player exit a room, but old options are kept
 * while previous entered room is removed from the pool
 *
 * this function is for future development
 */
public class Rooms {
    String name;
    String description;
    public Rooms(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}
