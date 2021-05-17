package AbnormalPoints;


import Player.Player;

/**
 * This is the class of monster where player will have their combat with
 * Use specific MonsterAttributes to generate varies monster
 */
public class Monster extends AbnormalPoint {
    /**
     * Constructor of Monster, given a set of attribute
     * @param ma Monster attributes that is a instance use to create specific monster
     * @param playerLevel the current player level, to create suitable monster for player's combat
     */
    public Monster(MonsterAttributes ma, int playerLevel){
        this.abnormalPointType = AbnormalPointType.MONSTER;
        this.setName(ma.setMonsterName);
        this.setIntro(ma.setMonsterIntro);
        this.setMaxHP(ma.setBaseMaxHP + playerLevel * ma.setByLevelMaxHP);
        this.setHP(this.getMaxHP());
        this.setArmour(ma.setBaseArmor + playerLevel * ma.setByLevelArmor);
        this.setDamage(ma.setBaseDamage + playerLevel * ma.setByLevelDamage);
        this.setCritChance(ma.setCritChance);
        this.setXpGain(ma.setBaseXPGain + playerLevel * ma.setByLevelXPGain);
        this.setGold(ma.setBaseGold + playerLevel * ma.setByLevelGold);
        this.setElement(ma.setElement);
    }

    public void attack(Player p){
//        p.
    }
}