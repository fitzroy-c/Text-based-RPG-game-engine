package AbnormalPoints;

//be replaced and divided by 5 specific monsters

import Card.Element;

public class Monster {
    private String name;
    private String intro; //describe the monster

    Element element;

    int maxHP;
    int maxMP;
    int level;
    private int strength;
    private int intelligence;
    private int dexterity;
    private int luck;
    private int stealth;
    private int gold;

    public Monster (String name, Element element, int maxHP,
                    int maxMP, int aiLevel){
        this.name = name;
        this.element = element;
        this.maxHP = maxHP;
        this.maxMP = maxMP;
        this.level = aiLevel;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
