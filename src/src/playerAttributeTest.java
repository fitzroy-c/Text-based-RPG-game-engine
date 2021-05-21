import java.util.HashSet;
import java.util.Set;

public class playerAttributeTest {
    private static final Set<String> nullableParametersSet1 = new HashSet<>();
    static {
        nullableParametersSet1.add("maxHPIncreasePerLv");
        nullableParametersSet1.add("damageIncreasePerLv");
        nullableParametersSet1.add("initRandomMaxHP");
        nullableParametersSet1.add("criticalChanceIncreasePerLv");

        nullableParametersSet1.add("hasEndedTalk");
        nullableParametersSet1.add("abnormalPointType");
        nullableParametersSet1.add("name");
        nullableParametersSet1.add("intro");
        nullableParametersSet1.add("maxHP");
        nullableParametersSet1.add("HP");
        nullableParametersSet1.add("damage");
        nullableParametersSet1.add("armour");
        nullableParametersSet1.add("gold");
        nullableParametersSet1.add("xpGain");
        nullableParametersSet1.add("critChance");
        nullableParametersSet1.add("element");


                "initRandomMaxHP": 30,
                "initBaseMaxHP": 120,
                "initRandomMoney": 5,
                "initBaseMoney": 10,
                "initMaxXP": 10,
                "initXPPerLv": 10,
                "initRandomArmor": 4,
                "initBaseArmor": 4,
                "initRandomDamage": 8,
                "initBaseDamage": 12,
                "initCriticalChance": 0.02,
                "initMaxCriticalChance": 1.0,
                "initBagWeight": 10,
                "initXCoordinate": 0,
                "initYCoordinate": 0
    }
}
