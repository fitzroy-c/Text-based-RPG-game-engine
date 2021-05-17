package AbnormalPoints;

/**
 * This class is to allow NPC and Monster to fit into the same AbnormalPoint list
 * @author yitao chen, Guanming Ou
 */

public class AbnormalPoint extends entity{
    enum AbnormalPointType {
        NPC_TALK,
        NPC_BLESSING,
        NPC_MERCHANT,
        MONSTER;
    }

    public AbnormalPointType abnormalPointType;
}


///**
// * This class deals with NPC and all of their properties.(aka monsters)
// * Any method that changes a NPC interacts with it should be placed within this class.
// * supplement for entity.(For simplicity, we consider all NPCs evil, no friend with main character )
// *
// * Mainly function:
// * a NPC should have an ID to identify
// * 5 extended monster kinds: Giant, Troll, Wolf, Skeleton, Goblin
// * default characteristic: normal (details can be seen in Card.Element)
// * xp, gold to get if we defeat one AbnormalPoint.
// * @author yitao chen, Guanming Ou
// */
//public class AbnormalPoint extends entity{
//    private int xpGain;
//    private String id;
//    public Element element;  //characteristic;
//    public String monsterType;
//
//    /**
//     * Constructor of AbnormalPoint
//     */
//    public AbnormalPoint(){
//        element = Element.Normal;
//    }
//
//    public AbnormalPoint(String abnormalPointID) {
//        this.id = abnormalPointID;
//        element = Element.Normal;
//    }
//
//    public int getXPGain() {
//        return xpGain;
//    } //if card game remove?
//
//    public void setXPGain(int xpGain) {
//        this.xpGain = xpGain;
//    } //if card game remove?
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) { this.id = id;}
//
//    public Element getElement() { return element;}
//
//    public void setElement(Element element) { this.element = element;}
//
//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
//        if (obj instanceof AbnormalPoint) {
//            AbnormalPoint AbnormalPoint = (AbnormalPoint) obj;
//            return AbnormalPoint.monsterType.equals(this.monsterType);
//        }
//        return false;
//    }
//}
