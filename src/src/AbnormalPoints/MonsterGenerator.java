package AbnormalPoints;

import Card.Element;
import Player.Player;

import java.util.Random;

/**
 * The MonsterGenerator generates random monsters appropriately according
 * to the player's level and place
 */
public class MonsterGenerator {
    Random random = new Random();
    public Monster MonsterGenerator(Player player){
        int randomInt = random.nextInt(6) + 1; // 1-5 consider the the plan is to make dangerRate 1(easy)-5(danger)
        if (randomInt <= player.getPlace().getDangerRate()) {
            //randomly choose 1 from 5 type
            int randomInt2 = random.nextInt(6);
            int playerLevel = player.getLevel();
            switch (random.nextInt(6)) {
                case 0:
                    /**
                     * A monster with high health and damage, but low armour.
                     */
                    MonsterAttributes giant = new MonsterAttributes("giant","A monster with high health and damage, but low armour.",
                            150, 8, 6, 3,40,3,
                            0.03,50, 3,15, 11, Element.Normal);
                    return new Monster(giant,playerLevel);
                case 1:
                    /**
                     * A normal monster , with slight armour.
                     */
                    MonsterAttributes goblin = new MonsterAttributes("goblin", "A normal monster , with slight armour.",
                            55,6, 0,3,12,2,
                            0.02,10, 3,0,5, Element.Normal);
                    return new Monster(goblin,playerLevel);
                case 2:
                    /**
                     * A quite weak monster.
                     */
                    MonsterAttributes skeleton = new MonsterAttributes("skeleton", "A quite weak monster.",
                            50,3, 0, 1, 8,1,
                            0.02,10, 3, 0,3,Element.Normal);
                    return new Monster(skeleton,playerLevel);
                case 3:
                    /**
                     * A monster without low damage, but high health and armour.
                     */
                    MonsterAttributes troll = new MonsterAttributes("troll", "A monster without low damage, but high health and armour.",
                            70,11, 0,12,20,3,
                            0.05,75, 3,25,10,Element.Normal);
                    return new Monster(troll,playerLevel);
                case 5:
                    /**
                     * A normal wild creature
                     */
                    MonsterAttributes wolf = new MonsterAttributes("wolf", "A wolf as you see",
                            35,3, 0,0,15,2,
                            0.04,25, 3,0,2,Element.Normal);
                    return new Monster(wolf,playerLevel);
                default: // any non-hostile location
                    return null;
            }
        } else {
            return null;
        }
    }
}
