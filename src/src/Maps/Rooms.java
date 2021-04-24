package Maps;

import NPCs.NPC;

/**
 * Basically, player are given option to go which room out of three options
 * Initially the rooms are randommly draw with three option.
 * Player can select which room they go to, either enter combat, shops, found a threasure, or receive blessing from fairies
 * New three options are draw everytime player exit a room
 * while previous options are droped back to the pool, and previous entered room is removed from the pool
 */
public class Rooms {
    String name;
    String description;
    NPC npc;
    public Rooms(String name, String description, NPC npc) {
        this.name = name;
        this.description = description;
        this.npc = npc;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }



}
