package NPCs;

import Card.Element;

import java.util.ArrayList;

public class Monster {
    String name;
    Element element;

    int maxHP;
    int maxMP;

    int aiLevel;

    public Monster (String name, Element element, int maxHP,
                    int maxMP, int aiLevel){
        this.name = name;
        this.element = element;
        this.maxHP = maxHP;
        this.maxMP = maxMP;
        this.aiLevel = aiLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getAiLevel() {
        return aiLevel;
    }

    public void setAiLevel(int aiLevel) {
        this.aiLevel = aiLevel;
    }
}
