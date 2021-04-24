package Maps;

import NPCs.NPC;

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
