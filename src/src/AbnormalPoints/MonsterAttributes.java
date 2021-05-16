package AbnormalPoints;

import Card.Element;

public class MonsterAttributes {
    String setMonsterType;
    int setBaseMaxHP;
    int setByLevelMaxHP;
    int setBaseArmor;
    int setByLevelArmor;
    int setBaseDamage;
    int setByLevelDamage;
    double setCritChance;
    int setBaseXPGain;
    int setByLevelXPGain;
    int setBaseGold;
    int setByLevelGold;
    Element setElement;

    public MonsterAttributes(String setMonsterType, int setBaseMaxHP, int setByLevelMaxHP, int setBaseArmor, int setByLevelArmor,
                             int setBaseDamage, int setByLevelDamage, double setCritChance, int setBaseXPGain,
                             int setByLevelXPGain, int setBaseGold, int setByLevelGold, Element setElement) {
        this.setMonsterType = setMonsterType;
        this.setBaseMaxHP = setBaseMaxHP;
        this.setByLevelMaxHP = setByLevelMaxHP;
        this.setBaseArmor = setBaseArmor;
        this.setByLevelArmor = setByLevelArmor;
        this.setBaseDamage = setBaseDamage;
        this.setByLevelDamage = setByLevelDamage;
        this.setCritChance = setCritChance;
        this.setBaseXPGain = setBaseXPGain;
        this.setByLevelXPGain = setByLevelXPGain;
        this.setBaseGold = setBaseGold;
        this.setByLevelGold = setByLevelGold;
        this.setElement = setElement;
    }
}
