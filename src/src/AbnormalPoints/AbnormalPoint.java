package AbnormalPoints;


/**
 * This class is to allow NPC and Monster to fit into the same AbnormalPoint list
 * @author yitao chen, Guanming Ou
 */

public class AbnormalPoint extends entity{
    // understand abnormal point by its name
    public enum AbnormalPointType {
        NPC_TALK,
        NPC_MERCHANT,
        MONSTER
    }

    public AbnormalPointType abnormalPointType;

    public String getString() {
        return String.valueOf(this.abnormalPointType);
    }

}