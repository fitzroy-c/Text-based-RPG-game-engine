package NPCs;


import Card.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * This class deals with Non Player Character (NPC) and all of their properties.
 * Any method that changes a NPC interacts with it should be placed within this class.
 * supplement for entity
 * @author yitao chen
 */
public class npc extends entity{
    private int xpGain;
    private String id;
    private Element element;//characteristic;
    public npc(String npcID, Element npcElement) {
        this.id = npcID;
        this.element = npcElement;
    }
    public int getXPGain() {
        return xpGain;
    } //if card game remove?

    public void setXPGain(int xpGain) {
        this.xpGain = xpGain;
    } //if card game remove?

    public String getId() {
        return id;
    }

    public void setId(String id) { this.id = id;}

    public Element getElement() { return element;}

    public void setElement(Element element) { this.element = element;}

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof npc) {
            npc Npc = (npc) obj;
            return Npc.getId().equals(id);
        }
        return false;
    }
}
