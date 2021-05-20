package Options;

import CommandParser.Token;
import Player.Player;

public class NormalOption extends BasicOption{
    private void buildMenu() {
//        this.option.add(new Option("detect", "Detect the room"));
        this.option.add(new Option("pick", "Pick an item"));
        this.option.add(new Option("drop", "drop an item"));
        this.option.add(new Option("look bag", "show my bag"));
        this.option.add(new Option("save", "Save game"));
        this.option.add(new Option("exit", "Exit game"));
        this.option.add(new Option("help", "get help"));

        this.tokenType.add(Token.Type.DETECT);
        this.tokenType.add(Token.Type.TAKE_ACTION);
        this.tokenType.add(Token.Type.DROP_ACTION);
        this.tokenType.add(Token.Type.SAVE);
        this.tokenType.add(Token.Type.EXIT);
        this.tokenType.add(Token.Type.DIRECTION);
        this.tokenType.add(Token.Type.DIRECTION_ACTION);
        this.tokenType.add(Token.Type.VIEW_ACTION);
        this.tokenType.add(Token.Type.CONSUME_ACTION);
        this.tokenType.add(Token.Type.BACKPACK);



    }

    public NormalOption(Player player) {
        buildMenu();
        showMenu();
        if (player.getHP() <= 0) {
            System.err.println("YOU DIE");
            System.out.println("Start again? (Y/N)");
        } else {

        }
    }

    ///testOnly
//    public static void main(String[] args) throws Exception {
//        Player p = new Player("P");
//        NormalOption b = new NormalOption(p);
//        b.printOut(b.option);
//    }

}
