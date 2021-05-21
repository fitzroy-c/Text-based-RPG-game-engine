package Options;

import CommandParser.Token;
import Player.Player;

/**
 * The class is the usual Option Menu. When player enter the game, this Option Menu
 * will be displayed to him.
 *
 * @author Zihong Yuan
 */
public class NormalOption extends BasicOption{

    /**
     * The method will add some general command to the menu.
     * Allowing players choose their next step.
     *
     * @author Zihong Yuan
     */
    private void buildMenu() {
        this.option.add(new Option("detect", "Detect the room"));
        this.option.add(new Option("see stat", "see my state"));
        this.option.add(new Option("look bag", "show my bag"));
        this.option.add(new Option("save", "Save game"));
        this.option.add(new Option("exit", "Exit game"));
        this.option.add(new Option("help", "get help"));

        this.tokenType.add(Token.Type.DETECT);
        this.tokenType.add(Token.Type.ATTACK);
        this.tokenType.add(Token.Type.TALK);
        this.tokenType.add(Token.Type.TAKE_ACTION);
        this.tokenType.add(Token.Type.DROP_ACTION);
        this.tokenType.add(Token.Type.SAVE);
        this.tokenType.add(Token.Type.EXIT);
        this.tokenType.add(Token.Type.DIRECTION);
        this.tokenType.add(Token.Type.DIRECTION_ACTION);
        this.tokenType.add(Token.Type.VIEW_ACTION);
        this.tokenType.add(Token.Type.CONSUME_ACTION);
        this.tokenType.add(Token.Type.BACKPACK);
        this.tokenType.add(Token.Type.STAT);
        this.tokenType.add(Token.Type.SHOP);

    }

    /**
     * The constructor method initialize NormalOption class.
     * It will check player's HP and print out message
     * when player died.
     *
     * @author Zihong Yuan
     * @param player
     */
    public NormalOption(Player player) {
        buildMenu();
        showMenu();
        if (player.getHP() <= 0) {
            System.err.println("YOU DIE");
            System.out.println("Start again? (Y/N)");
        } else {

        }
    }

}
