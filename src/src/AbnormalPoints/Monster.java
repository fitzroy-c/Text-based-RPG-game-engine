package AbnormalPoints;
import Card.Element;
//be replaced and divided by 5 specific monsters

public class Monster extends AbnormalPoint {

    public Monster(MonsterAttributes ma, int playerLevel){
        this.monsterType = ma.setMonsterType;
        this.setMaxHP(ma.setBaseMaxHP + playerLevel * ma.setByLevelMaxHP);
        this.setHP(this.getMaxHP());
        this.setArmour(ma.setBaseArmor + playerLevel * ma.setByLevelArmor);
        this.setDamage(ma.setBaseDamage + playerLevel * ma.setByLevelDamage);
        this.setCritChance(ma.setCritChance);
        this.setXPGain(ma.setBaseXPGain + playerLevel * ma.setByLevelXPGain);
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