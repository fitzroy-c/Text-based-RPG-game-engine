package AbnormalPoints;

/**
 * superclass for all entities (monsters...)
 * quite basic
 * @author yitao chen
 */
public abstract class entity {
    // All entities can attack, have HP, have names
    private String name;
    private String intro; //describe the monster
    private int maxHP;
    private int HP;
    private int level;
    private int damage;
    private int armour;

    private int gold;
    private double critChance = 0.0; //critical hit chance

    public entity() {
        this(100, 100, "default", 0);
    }
    public entity(int maxHP, int HP, String name, int gold) {
        this.maxHP = maxHP;
        this.HP = HP;
        this.name = name;
        this.gold = gold;
    }
    public int getHP() {
        return this.HP;
    }

    public void setHP(int HP) {
        if (HP > maxHP) {
            HP = maxHP;
        }
        this.HP = HP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
        if (HP > maxHP) {
            HP = maxHP;
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
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
}
