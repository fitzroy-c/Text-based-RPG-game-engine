package AbnormalPoints;

import Card.Element;

/**
 * This monster attribute is to create monster in a easier way
 * This can be improve by json enabling
 * @author Guanming Ou
 */
public class MonsterAttributes {
    String setMonsterName;
    String setMonsterIntro;
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

    public MonsterAttributes(String setMonsterName, String setMonsterIntro, int setBaseMaxHP, int setByLevelMaxHP, int setBaseArmor, int setByLevelArmor,
                             int setBaseDamage, int setByLevelDamage, double setCritChance, int setBaseXPGain,
                             int setByLevelXPGain, int setBaseGold, int setByLevelGold, Element setElement) {
        this.setMonsterName = setMonsterName;
        this.setMonsterIntro = setMonsterIntro;
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
