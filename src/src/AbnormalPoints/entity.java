package AbnormalPoints;

import Card.Element;

/**
 * superclass for all entities (Monster and NPC)
 * - This means both NPC and Monster are allow to be attacked
 * - Main difference is NPC is passive to attack, but Monster will aggressively attack player
 * @author yitao chen, slightly modify by Guanming Ou
 */
public abstract class entity {
    private String name;
    private String intro; //describe the monster or npc
    private int maxHP;
    private int HP;
    private int damage;
    private int armour;

    private int gold;
    private int xpGain;
    private double critChance; //critical hit chance
    private Element element;

    /**
     * Default of a entity
     */
    public entity() {
        this("default", "default intro", 100, 100,10,
                5,3,0.00, Element.Normal);
    }

    /**
     * Constructor of a entity
     * @param name name of the entity
     * @param intro the intro, which describe this entity
     * @param maxHP the maximum HP
     * @param HP the current HP
     * @param damage the damage of this entity can cause
     * @param armour the armor of this entity
     * @param gold the gold this entity have
     * @param critChance the critical damage chance this entity have
     */
    public entity(String name, String intro, int maxHP, int HP, int damage, int armour, int gold,
                  double critChance, Element element) {
        this.name = name;
        this.intro = intro;
        this.maxHP = maxHP;
        this.HP = HP;
        this.damage = damage;
        this.armour = armour;
        this.gold = gold;
        this.critChance = critChance;
        this.element = element;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getDamage() { return damage; }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getArmour() {
        return armour;
    }

    public void setArmour(int armour) {
        this.armour = armour;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public double getCritChance() {
        return critChance;
    }

    public void setCritChance(double critChance) {
        this.critChance = critChance;
    }

    public int getXpGain() {
        return xpGain;
    }

    public void setXpGain(int xpGain) {
        this.xpGain = xpGain;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }
}
