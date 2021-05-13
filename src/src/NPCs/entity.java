package NPCs;

import java.util.HashMap;
import java.util.Map;

/**
 * superclass for all entities (monsters...)
 */
public abstract class entity {
    // All entities can attack, have health, have names
    private String name;
    private String intro; //describe the monster
    private int healthMax;
    private int health;
    private int level;
    private int strength;

    private int intelligence;
    private int luck;
    private int gold;

    private double damage = 30;
    private double critChance = 0.0; //critical hit chance

    public entity() {
        this(100, 100, "default", 0);
    }
    public entity(int healthMax, int health, String name, int gold) {
        this.healthMax = healthMax;
        this.health = health;
        this.name = name;
        this.gold = gold;
    }
    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        if (health > healthMax) {
            health = healthMax;
        }
        this.health = health;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIntro() {
        return this.intro;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }
}
