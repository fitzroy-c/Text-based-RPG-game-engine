package Options;

import AbnormalPoints.Monster;
import CommandParser.Token;
import Player.Player;
import AbnormalPoints.AbnormalPoint;

public class BattleOption extends BasicOption {

    private void buildMenu() {
        this.option.add(new Option("attack", "Attack enemy"));
        this.option.add(new Option("retreat", "Retreat from here"));
        this.option.add(new Option("defence", "Defence"));
        this.option.add(new Option("pick", "Pick an item"));

        this.tokenType.add(Token.Type.ATTACK);
        this.tokenType.add(Token.Type.RETREAT_ACTION);
        this.tokenType.add(Token.Type.TAKE_ACTION);

    }

    public BattleOption(AbnormalPoint enemy,Player player) throws Exception {
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

    ///testOnly
    public static void main(String[] args) throws Exception {
        Player p = new Player("P");
        AbnormalPoint a = new AbnormalPoint();
        Monster m = new Monster(null, 12);
        BattleOption b = new BattleOption(m, p);
        b.printOut();
    }
}
