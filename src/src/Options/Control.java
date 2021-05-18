package Options;

import AbnormalPoints.AbnormalPoint;
import Player.Player;

public class Control extends BasicOption {
    AbnormalPoint current;
    Player player;
    public BasicOption currentOption;

    public Control(AbnormalPoint current, Player player) throws Exception {
        this.current = current;
        this.player = player;
        switch (current.getString()) {
            case "NPC_TALK" :
            case "NPC_MERCHANT" :
                NpcOption p = new NpcOption(current, player);
                this.currentOption = p;
                //this.option = currentOption.option;
                p.printOut(p.option);
                break;
            case "MONSTER" :
                BattleOption b = new BattleOption(current, player);
                this.currentOption = b;
                b.printOut(b.option);
                break;
            default:
                NormalOption n = new NormalOption(player);
                this.currentOption = n;
                n.printOut(n.option);
                break;
        }
    }

}
