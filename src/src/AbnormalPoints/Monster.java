package AbnormalPoints;
import Card.Element;


/**
 * This is the class of monster where player will have their combat with
 * Use specific MonsterAttributes to generate varies monster
 */
public class Monster extends AbnormalPoint {
    String monsterType;

    /**
     * Constructor of Monster, given a set of attribute
     * @param ma Monster attributes that is a instance use to create specific monster
     * @param playerLevel the current player level, to create suitable monster for player's combat
     */
    public Monster(MonsterAttributes ma, int playerLevel){
        this.abnormalPointType = AbnormalPointType.MONSTER;
        this.monsterType = ma.setMonsterType;
        this.setMaxHP(ma.setBaseMaxHP + playerLevel * ma.setByLevelMaxHP);
        this.setHP(this.getMaxHP());
        this.setArmour(ma.setBaseArmor + playerLevel * ma.setByLevelArmor);
        this.setDamage(ma.setBaseDamage + playerLevel * ma.setByLevelDamage);
        this.setCritChance(ma.setCritChance);
        this.setXpGain(ma.setBaseXPGain + playerLevel * ma.setByLevelXPGain);
        this.setGold(ma.setBaseGold + playerLevel * ma.setByLevelGold);
        this.setElement(ma.setElement);
    }

    /**
     * A monster with high health and damage, but low armour.
     */
    MonsterAttributes giant = new MonsterAttributes("giant", 150, 8,
            6, 3,40,3,0.03,50,
            3,15, 11, Element.Normal);

    /**
     * A normal monster , with slight armour.
     */
    MonsterAttributes goblin = new MonsterAttributes("goblin", 55,6,
            0,3,12,2,0.02,10,
            3,0,5, Element.Normal);

    /**
     * A quite weak monster.
     */
    MonsterAttributes skeleton = new MonsterAttributes("skeleton", 50,3,
            0, 1, 8,1,0.02,10,
            3, 0,3,Element.Normal);

    /**
     * A monster without low damage, but high health and armour.
     */
    MonsterAttributes troll = new MonsterAttributes("troll", 70,11,
            0,12,20,3,0.05,75,
            3,25,10,Element.Normal);

    MonsterAttributes wolf = new MonsterAttributes("wolf", 35,3,
            0,0,15,2,0.04,25,
            3,0,2,Element.Normal);
}