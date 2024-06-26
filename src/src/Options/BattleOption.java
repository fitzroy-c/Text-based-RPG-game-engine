package Options;

import CommandParser.Token;
import Player.Player;

/**
 * The class is the option menu to display when player
 * encounter with monster and in the battle.
 *
 * @author Zihong Yuan
 */
public class BattleOption extends BasicOption {
    /**
     * The method add options to menu.
     *
     * @author Zihong Yuan
     */
    private void buildMenu() {
        this.option.add(new Option("attack", "Attack enemy"));
        this.option.add(new Option("retreat", "Retreat from here"));
        this.option.add(new Option("defence", "Defence"));
        this.option.add(new Option("pick", "Pick an item"));

        this.tokenType.add(Token.Type.ATTACK);
        this.tokenType.add(Token.Type.RETREAT_ACTION);
        this.tokenType.add(Token.Type.TAKE_ACTION);

    }

    /**
     * The constructor method for BattleOption class.
     * It will check player's HP and print out message
     * when player died.
     *
     * @author Zihong Yuan
     * @param player the current player
     */
    public BattleOption(Player player) {
        if (player.getHP() <= 0) {
            System.out.println("YOU DIE");
            System.out.println("What would you like to do?");
            StartOption start = new StartOption();
            start.printOut();
            Control.death=true;
        } else {
            buildMenu();
            showMenu();
        }
    }

}
