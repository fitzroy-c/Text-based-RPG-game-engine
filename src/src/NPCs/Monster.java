package NPCs;

import Card.Element;

import java.util.ArrayList;

public class Monster {
    String name;
    NPCType npcType;
    Element element;

    int maxHP;
    int maxMP;
    int[] monsterCardSet;
    int aiLevel;

    public Monster (String name, Element element, int maxHP,
                    int maxMP, int[] monsterCardSet, int aiLevel){
        this.name = name;
        this.npcType = NPCType.Monster;
        this.element = element;
        this.maxHP = maxHP;
        this.maxMP = maxMP;
        this.monsterCardSet = monsterCardSet;
        this.aiLevel = aiLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NPCType getNpcType() {
        return npcType;
    }

    public void setNpcType(NPCType npcType) {
        this.npcType = npcType;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getMaxMP() {
        return maxMP;
    }

    public void setMaxMP(int maxMP) {
        this.maxMP = maxMP;
    }

    public int[] getMonsterCardSet() {
        return monsterCardSet;
    }

    public void setMonsterCardSet(int[] monsterCardSet) {
        this.monsterCardSet = monsterCardSet;
    }

    public int getAiLevel() {
        return aiLevel;
    }

    public void setAiLevel(int aiLevel) {
        this.aiLevel = aiLevel;
    }
}
