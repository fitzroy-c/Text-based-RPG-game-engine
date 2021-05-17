package Options;

import Player.Player;

public class BattleOption extends BasicOption {

    private void buildMenu() {
        this.option.add(new Option("attack", "Attack enemy"));
        this.option.add(new Option("retreat", "Retreat from here"));
        this.option.add(new Option("defence", "Defence"));
        this.option.add(new Option("pick", "Pick an item"));
        this.option.add(new Option("north", "Go north"));
        this.option.add(new Option("south", "Go south"));
        this.option.add(new Option("east", "Go east"));
        this.option.add(new Option("west", "Go west"));
    }

    public BattleOption(Player enemy,Player player) throws Exception {
        buildMenu();
        if (player.getHP() <= 0) {
            System.err.println("YOU DIE");
            System.out.println("Start again? (Y/N)");
        } else {

        }
    }

    ///testOnly
    public static void main(String[] args) throws Exception {
        Player p = new Player("P");
        BattleOption b = new BattleOption(p, p);
        b.printOut(b.option);
    }
}
