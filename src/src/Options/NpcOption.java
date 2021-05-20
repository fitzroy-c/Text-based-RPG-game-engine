package Options;

import AbnormalPoints.AbnormalPoint;
import AbnormalPoints.NPC_MERCHANT;
import CommandParser.Token;
import Player.Player;

/**
 * The class extends basic option menu, and customize it to
 * NPC option menu. It allow player use only NPC options.
 *
 * @author Zihong Yuan
 */
public class NpcOption extends BasicOption{
    AbnormalPoint npc;
    /**
     * @author Zihong Yuan
     */
    private void buildMenu() {
        if (npc.getString().equals("NPC_MERCHANT")) {
            this.option.add(new Option("talk", "Talk to NPC"));
            this.option.add(new Option("back", "Go back"));

            this.tokenType.add(Token.Type.TAKE_ACTION);
        } else if (npc.getString().equals("NPC_TALK")) {
            this.option.add(new Option("shop", "trade with NPC"));
            this.option.add(new Option("back", "Go back"));

            this.tokenType.add(Token.Type.SHOP);
        }

    }

    /**
     *
     * @param npc
     * @param player
     */
    public NpcOption(AbnormalPoint npc, Player player) {
        this.npc = npc;
        buildMenu();
        showMenu();
        //...//
    }

}
