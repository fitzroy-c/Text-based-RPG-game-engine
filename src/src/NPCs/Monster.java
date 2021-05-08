package NPCs;

import Card.Element;

import java.util.ArrayList;

public class Monster {
    String name;
    String description;
    NPCType npcType;
    Element element;

    int currentHP;
    int maxHP;
    int currentMP;
    int maxMP;
    int[] monsterCardSet;
    ArrayList<Integer> currentHand;
    int aiLevel;

    public Monster (String name, String description, Element element, int currentHP, int maxHP,
                    int currentMP, int maxMP, int[] monsterCardSet, ArrayList<Integer> currentHand, int aiLevel){
        this.name = name;
        this.description = description;
        this.npcType = NPCType.Monster;
        this.element = element;
        this.currentHP = currentHP;
        this.maxHP = maxHP;
        this.currentMP = currentMP;
        this.maxMP = maxMP;
        this.monsterCardSet = monsterCardSet;
        this.currentHand = currentHand;
        this.aiLevel = aiLevel;
    }
}
