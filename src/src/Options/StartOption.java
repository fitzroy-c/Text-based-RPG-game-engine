package Options;

import Player.Player;

public class StartOption extends BasicOption{
    private void buildMenu() {
        this.option.add(new Option("new", "start the new game"));
        this.option.add(new Option("load", "load the game"));
        this.option.add(new Option("exit", "exit the game"));
    }

    public StartOption(Player player) {
        buildMenu();
        showMenu(this.option);
    }

}
