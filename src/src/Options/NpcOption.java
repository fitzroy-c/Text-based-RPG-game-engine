package Options;

import AbnormalPoints.AbnormalPoint;
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
     * The method will add some general command to the menu.
     * If the current NPC belongs tp NPC_TALK, then it will
     * display talk option.
     * If the current NPC belongs tp NPC_MERCHANT, then it will
     * display trade option.
     *
     * @author Zihong Yuan
     */
    private void buildMenu() {
        if (npc.getString().equals("NPC_TALK")) {
            this.option.add(new Option("talk", "Talk to NPC"));

            this.tokenType.add(Token.Type.TAKE_ACTION);
        } else if (npc.getString().equals("NPC_MERCHANT")) {
            this.option.add(new Option("shop", "trade with NPC"));

            this.tokenType.add(Token.Type.SHOP);
        }

        this.option.add(new Option("back", "Go back"));
        this.tokenType.add(Token.Type.RETREAT_ACTION);

    }

    /**
     * The constructor method initialize NPCOption class.
     *
     * @author Zihong Yuan
     * @param npc current npc which player can interact with.
     * @param player current player.
     */
    public NpcOption(AbnormalPoint npc, Player player) {
        this.npc = npc;
        buildMenu();
        showMenu();
    }

}
