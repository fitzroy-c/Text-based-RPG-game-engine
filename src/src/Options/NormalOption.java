package Options;

import Player.Player;

public class NormalOption extends BasicOption{
    private void buildMenu() {
        this.option.add(new Option("detect", "Detect the room"));
        this.option.add(new Option("pick", "Pick an item"));
        this.option.add(new Option("drop", "drop an item"));
        this.option.add(new Option("exit", "Exit game"));
        this.option.add(new Option("save", "Save game"));
        this.option.add(new Option("north", "Go north"));
        this.option.add(new Option("south", "Go south"));
        this.option.add(new Option("east", "Go east"));
        this.option.add(new Option("west", "Go west"));
        this.option.add(new Option("help", "get help"));

    }

    public NormalOption(Player player) throws Exception {
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
