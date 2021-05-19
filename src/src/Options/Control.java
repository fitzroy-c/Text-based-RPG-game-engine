package Options;

import AbnormalPoints.AbnormalPoint;
import Player.Player;

public class Control extends BasicOption {
    static AbnormalPoint current;
    Player player;
    public BasicOption currentOption;
    boolean isMonster = false;
    boolean isNpc = false;

    public Control(Player player) throws Exception {
        current = new AbnormalPoint();
        this.player = player;
        printRightOption();
    }

    public void setCurrentOption(AbnormalPoint currentab) throws Exception {
        this.current = currentab;
        printRightOption();
    }

    public void printRightOption() throws Exception {
        System.out.println("Run print");
        switch (current.getString()) {
            case "NPC_TALK" :
            case "NPC_MERCHANT" :
                System.out.println("Control: case is NPC");
                NpcOption p = new NpcOption(current, player);
                this.currentOption = p;
                //this.option = currentOption.option;
                p.printOut();
                break;
            case "MONSTER" :
                System.out.println("Control: case is Monster");
                BattleOption b = new BattleOption(current, player);
                this.currentOption = b;
                b.printOut();
                break;
            default:
                System.out.println("Control: case is normal");
                NormalOption n = new NormalOption(player);
                this.currentOption = n;
                n.printOut();
                break;
        }
    }

    static public void setCurrentAb(AbnormalPoint a) {
        current = a;
    }
}
